/**
 * This package is about ETL (Extract, Transform and Load) Process.
 */
package br.com.Books.ETL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import br.com.Books.Controller.Books;

/**<b>Author</b>: Pedro Luiz da Silva Pereira<br>
 * <b>Date</b>: 28/02/2019<br>
 * <b>About</b>: This class has the goals:<br>
 * <ul>
 * 	<li>Read JSON files after extractJson.sh has been finished;</li>
 * 	<li>Read the HTML file with details about book sellers;</li>
 * 	<li>Prepare data from files, organize informations and call DAO package to persistence informations;<li>
 * </ul>
 */

public class extractFromFiles {
	
	/** Path where files are storage */
	private static String pathFiles = "Scripts/Downloads/GoogleBooks/";
	/** Set name of the file with record JSON list updated after script execution. */
	//private static String jsonFileList = "jsonFileList.txt";
	/**This method read JSON File and returning object of the type Books.
	 * with this Item is possible saving data in database 
	 * @throws UnsupportedEncodingException */
	public ArrayList<Books> listBooks = new ArrayList<Books>();
	public ArrayList<Books> readBookFromJSON(String fileName,Books book) throws UnsupportedEncodingException {
		/** set object type books to return books after read file */
		Books tmpBook = null;
		mountBookObject mountbookobject = new mountBookObject();
		String strExceptions = null;
		Boolean items = false;
		Boolean authors = false;
		Boolean industryIdentifiers = false;
		Boolean ISBN_10 = false;
		Boolean ISBN_13 = false;
		Boolean OTHER = false;
		Boolean categories = false;
		Boolean saleInfo = false;
		Boolean epub = false;
		Boolean pdf = false;
		/** set File with informations about files JSON storage after script execution */
		File jsonFile = new File(pathFiles+fileName);
		/** check file and directory exists physical, after start read file */
		if(jsonFile.exists() && !jsonFile.isDirectory()) { 
		    /** read file and set data in object Books from file */
			// Open the file
			FileInputStream fstream;
			try {
				fstream = new FileInputStream(jsonFile);
				InputStreamReader isr = new InputStreamReader(fstream, "UTF8");
				BufferedReader br = new BufferedReader(isr);
				String strLine;
				//Read File Line By Line
				try {
					while ((strLine = br.readLine()) != null)   
					{
					  //associate line from file to Attribute Books Object
						if (strLine.contains("\"items\":"))
						{
							items = true;
						}
						
						if (strLine.contains("\"saleInfo\":"))
						{
							saleInfo = true;
						}		
						
						if (strLine.contains("\"epub\":"))
						{
							epub = true;
						}				
						
						if (strLine.contains("\"pdf\":"))
						{
							pdf = true;
						}	
						
						if (epub)
						{
							if (strLine.contains("\"isAvailable\":"))
							{
								tmpBook = mountbookobject.setAttributesBook(strLine, book, "epub");
								epub = false;
							}
						}

						if (pdf)
						{
							if (strLine.contains("\"isAvailable\":"))
							{
								tmpBook = mountbookobject.setAttributesBook(strLine, book, "pdf");
								pdf = false;
							}
						}
						
						
						if (saleInfo)
						{
							if (strLine.contains("},"))
							{
								saleInfo = false;
							} else
							{
								if (strLine.contains("\"country\":"))
								{
									tmpBook = mountbookobject.setAttributesBook(strLine, book, "saleInfo_Country");
								}
								
								if (strLine.contains("\"saleability\":"))
								{
									tmpBook = mountbookobject.setAttributesBook(strLine, book, "saleInfo_saleability");
								}
								
								if (strLine.contains("\"isEbook\":"))
								{
									tmpBook = mountbookobject.setAttributesBook(strLine, book, "saleInfo_isEbook");
								}								
							}
						}
						
						if ((strLine.contains("\"authors\": [")) || (authors)){
							authors = true;
							if (strLine.contains("],"))
							{
								authors = false;
								tmpBook = mountbookobject.setAttributesBook(strExceptions.replaceAll("\"", "").replaceAll("     ", ""), book, "authors");
								strExceptions = null;
							} 
							else
							{
								strExceptions = strExceptions + strLine;
							}
						} 
						
						if ((strLine.contains("\"industryIdentifiers\":")) || (industryIdentifiers))
							{
								industryIdentifiers = true;
								if (strLine.contains("ISBN_10") || (ISBN_10))
								{
									ISBN_10 = true;
									if (strLine.contains("\"identifier\""))
									{
										tmpBook = mountbookobject.setAttributesBook(strLine, book, "ISBN_10");
										ISBN_10 = false;
									}
								}
								if (strLine.contains("ISBN_13") || (ISBN_13))
								{
									ISBN_13 = true;
									if (strLine.contains("\"identifier\""))
									{
										tmpBook = mountbookobject.setAttributesBook(strLine, book, "ISBN_13");
										ISBN_13 = false;
										industryIdentifiers=false;
									}
								}

							} 
						
						if (strLine.contains("\"OTHER\",") || (OTHER))
						{
							OTHER = true;
							if (strLine.contains("\"identifier\""))
							{
								//System.out.println("OTHER CODE");
								tmpBook = mountbookobject.setAttributesBook(strLine, book, "OTHER");
								OTHER = false;
							}
						}	
							if ((strLine.contains("categories")) || (categories)){
									categories = true;
									if (strLine.contains("maturityRating"))
									{
										categories = false;
										tmpBook = mountbookobject.setAttributesBook(strExceptions.replaceAll("\"", "").replaceAll("     ", ""), book, "categories");
										strExceptions = null;
									} 
									else
									{
										strExceptions = strExceptions + strLine;
									}
								}

							if ( (items == true) &&
							 (authors == false) &&
							 (industryIdentifiers == false) &&
							 (ISBN_10 == false) &&
							 (ISBN_13 == false) &&
							 (saleInfo == false) &&
							 (epub == false) &&
							 (pdf == false) &&
							 (OTHER == false) &&
							 (categories == false))
						{
								tmpBook = mountbookobject.setAttributesBook(strLine, book, "others");
						}

							if (tmpBook != null)
							{
								Books insertBooks = new Books();
								insertBooks.setId(tmpBook.getId());
								insertBooks.setAccessInfoCountry(tmpBook.getAccessInfoCountry());
								insertBooks.setAccessInfoEmbeddable(tmpBook.getAccessInfoEmbeddable());
								insertBooks.setAccessInfoPublicDomain(tmpBook.isAccessInfoPublicDomain());
								insertBooks.setAccessInfotextToSpeechPermission(tmpBook.getAccessInfotextToSpeechPermission());
								insertBooks.setAccessInfoViewAbility(tmpBook.getAccessInfoViewAbility());
								insertBooks.setAccessViewStatus(tmpBook.getAccessViewStatus());
								insertBooks.setAllowAnonLogging(tmpBook.isAllowAnonLogging());
								insertBooks.setAuthors(tmpBook.getAuthors());
								insertBooks.setAverageRatin(tmpBook.getAverageRatin());
								insertBooks.setCanonicalVolumeLink(tmpBook.getCanonicalVolumeLink());
								insertBooks.setCategories(tmpBook.getCategories());
								insertBooks.setContentVersion(tmpBook.getContentVersion());
								insertBooks.setDescription(tmpBook.getDescription());
								insertBooks.setEpubIsAvailable(tmpBook.isEpubIsAvailable());
								insertBooks.setEtag(tmpBook.getEtag());
								insertBooks.setImagesLinksSmallThumbnails(tmpBook.getImagesLinksSmallThumbnails());
								insertBooks.setImagesLinksThubmnail(tmpBook.getImagesLinksThubmnail());
								insertBooks.setInfoLink(tmpBook.getInfoLink());
								insertBooks.setISBN10(tmpBook.getISBN10());
								insertBooks.setISBN13(tmpBook.getISBN13());
								insertBooks.setOTHER(tmpBook.getOTHER());
								insertBooks.setKind(tmpBook.getKind());
								insertBooks.setLanguage(tmpBook.getLanguage());
								insertBooks.setMaturityRating(tmpBook.getMaturityRating());
								insertBooks.setPageCount(tmpBook.getPageCount());
								insertBooks.setPfIsAvailable(tmpBook.isPfIsAvailable());
								insertBooks.setPreviewLink(tmpBook.getPreviewLink());
								insertBooks.setPrintType(tmpBook.getPrintType());
								insertBooks.setPublishedDate(tmpBook.getPublishedDate());
								insertBooks.setPublisher(tmpBook.getPublisher());
								insertBooks.setQuoteSharingAllowed(tmpBook.isQuoteSharingAllowed());
								insertBooks.setRatingsCount(tmpBook.getRatingsCount());
								insertBooks.setReadingModesImage(tmpBook.getReadingModesImage());
								insertBooks.setReadingModesText(tmpBook.getReadingModesText());
								insertBooks.setSaleInfoCountry(tmpBook.getSaleInfoCountry());
								insertBooks.setSaleInfoisEbook(tmpBook.isSaleInfoisEbook());
								insertBooks.setSaleInfoSaleability(tmpBook.getSaleInfoSaleability());
								insertBooks.setSearchInfoTextSnippet(tmpBook.getSearchInfoTextSnippet());
								insertBooks.setSelfLink(tmpBook.getSelfLink());
								insertBooks.setSellerbooks(null);
								insertBooks.setSubTitle(tmpBook.getSubTitle());
								insertBooks.setTitle(tmpBook.getTitle());
								insertBooks.setWebReaderLink(tmpBook.getWebReaderLink());
								
								listBooks.add(insertBooks);
								tmpBook = null;
							}
							
					}
					//Close the input stream
					fstream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("size: "+listBooks.size());
		return listBooks;
	}

}
