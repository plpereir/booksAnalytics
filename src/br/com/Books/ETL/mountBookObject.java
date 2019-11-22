/**
 * 
 */
package br.com.Books.ETL;

import br.com.Books.Controller.Books;

/**<b>Author</b>: Pedro Luiz da Silva Pereira<br>
 * <b>Date</b>: 28/02/2019<br>
 * <b>About</b>: This class has the goals:<br>
 * <ul>
 * 	<li>read lines from JSON File;</li>
 * 	<li>get Substrings from lines;</li>
 * 	<li>set attributes in Books object from subStrings lines;<li>
 * </ul>
 */
public class mountBookObject {

	public Books setAttributesBook(String strLine, Books book, String type)
	{
		Books tmpBook = null;
		if (type == "others")
		{
			if (strLine.contains("\"kind\":"))
			{
				try{
				book.setKind(strLine.substring(strLine.indexOf(":")+3, strLine.lastIndexOf(",")-1));
				} catch (Exception ex){
					book.setKind(null);
				}
			//	System.out.println ("kind ->"+book.getKind());
			}
			
			if (strLine.contains("\"id\":"))//get ID
			{
				try{
					book.setId(strLine.substring(strLine.lastIndexOf(":")+3, strLine.lastIndexOf(",")-1));
				} catch (Exception ex){
					book.setId(null);
				}
			//	System.out.println ("id ->"+book.getId());
			}
			
			if (strLine.contains("\"etag\":"))
			{
				try{
					book.setEtag(strLine.substring(strLine.indexOf(":")+3, strLine.lastIndexOf(",")-1));
				} catch (Exception ex){
					book.setEtag(null);
				}
			//	System.out.println ("etag ->"+book.getEtag());
			}
			
			if (strLine.contains("\"selfLink\":"))
			{
				try{
					book.setSelfLink(strLine.substring(strLine.indexOf(":")+3, strLine.lastIndexOf(",")-1));
				} catch (Exception ex){
					book.setSelfLink(null);
				}
			//	System.out.println ("selfLink ->"+book.getSelfLink());
			}		
			
			if (strLine.contains("\"title\":"))
			{
				try{
					if (strLine.substring(strLine.indexOf(":")+3, strLine.lastIndexOf(",")-1).length() < 1499)
					{
						book.setTitle(strLine.substring(strLine.indexOf(":")+3, strLine.lastIndexOf(",")-1));
						
					}else
					{
						book.setTitle(strLine.substring(strLine.indexOf(":")+3, 1499));
					}
				} catch (Exception ex){
					book.setTitle(null);
				}
			//	System.out.println ("title ->"+book.getTitle());
			}
			
			if (strLine.contains("\"subtitle\":"))
			{
				try{
					if (strLine.substring(strLine.indexOf(":")+3, strLine.lastIndexOf(",")-1).length()<1499)
					{
						book.setSubTitle(strLine.substring(strLine.indexOf(":")+3, strLine.lastIndexOf(",")-1));
					}else
					{
						book.setSubTitle(strLine.substring(strLine.indexOf(":")+3, 1499));
					}
				} catch (Exception ex){
					book.setSubTitle(null);
				}
			//	System.out.println ("subtitle ->"+book.getSubTitle());
			}
			
			if (strLine.contains("\"publisher\":"))
			{
				try{
					book.setPublisher(strLine.substring(strLine.indexOf(":")+3, strLine.lastIndexOf(",")-1));
				} catch (Exception ex){
					book.setPublisher(null);
				}
			//	System.out.println ("publisher ->"+book.getPublisher());
			}
			
			if (strLine.contains("\"publishedDate\":"))
			{
				try{
					book.setPublishedDate(strLine.substring(strLine.indexOf(":")+3, strLine.lastIndexOf(",")-1));
				} catch (Exception ex){
					book.setPublishedDate(null);
				}
			//	System.out.println ("publishedDate ->"+book.getPublishedDate());
			}
			
			if (strLine.contains("\"description\":"))
			{
				try{
					if (strLine.substring(strLine.indexOf(":")+3, strLine.lastIndexOf(",")-1).length()<2999)
					{
						book.setDescription(strLine.substring(strLine.indexOf(":")+3, strLine.lastIndexOf(",")-1));
						
					}else
					{
						book.setDescription(strLine.substring(strLine.indexOf(":")+3, 2999));
					}
				} catch (Exception ex){
					book.setDescription(null);
				}
			//	System.out.println ("description ->"+book.getDescription());
			}
			
			if (strLine.contains("\"text\":"))
			{
				try{
					book.setReadingModesText(strLine.substring(strLine.indexOf(":")+2, strLine.lastIndexOf(",")));
				} catch (Exception ex){
					book.setReadingModesText(null);
				}
			//	System.out.println ("ReadingModesText ->"+book.getReadingModesText());
			}			

			if (strLine.contains("\"image\":"))
			{
				try{
					book.setReadingModesImage(strLine.substring(strLine.indexOf(":")+2, strLine.lastIndexOf("e")+1));
				} catch (Exception ex){
					book.setReadingModesImage(null);
				}
			//	System.out.println ("ReadingModesImage ->"+book.getReadingModesImage());
			}			

			if (strLine.contains("\"pageCount\":"))
			{
				try{
					book.setPageCount(strLine.substring(strLine.indexOf(":")+2, strLine.lastIndexOf(",")));
				} catch (Exception ex){
					book.setPageCount(null);
				}
			//	System.out.println ("pageCount ->"+book.getPageCount());
			}			

			if (strLine.contains("\"printType\":"))
			{
				try{
					book.setPrintType(strLine.substring(strLine.indexOf(":")+2, strLine.lastIndexOf(",")).replaceAll("\"", ""));
				} catch (Exception ex){
					book.setPrintType(null);
				}
			//	System.out.println ("printType ->"+book.getPrintType());
			}		
			if (strLine.contains("\"averageRating\":"))
			{
				try{
					book.setAverageRatin(Float.parseFloat(strLine.substring(strLine.indexOf(":")+2, strLine.lastIndexOf(",")).replaceAll("\"", "")));
				} catch (Exception ex){
					book.setAverageRatin(0);
				}
			//	System.out.println ("maturityRating ->"+book.getMaturityRating());
			}
			if (strLine.contains("\"ratingsCount\":"))
			{
				try{
					book.setRatingsCount(Integer.parseInt(strLine.substring(strLine.indexOf(":")+2, strLine.lastIndexOf(",")).replaceAll("\"", "")));
				} catch (Exception ex){
					book.setRatingsCount(0);
				}
			//	System.out.println ("maturityRating ->"+book.getMaturityRating());
			}			
			if (strLine.contains("\"maturityRating\":"))
			{
				try{
					book.setMaturityRating(strLine.substring(strLine.indexOf(":")+2, strLine.lastIndexOf(",")).replaceAll("\"", ""));
				} catch (Exception ex){
					book.setMaturityRating(null);
				}
			//	System.out.println ("maturityRating ->"+book.getMaturityRating());
			}				
			
			if (strLine.contains("\"allowAnonLogging\":"))
			{
				try{
					book.setAllowAnonLogging((strLine.substring(strLine.indexOf(":")+2, strLine.lastIndexOf(",")).replaceAll("\"", "")));
				} catch (Exception ex){
					book.setAllowAnonLogging("false");
				}
			//	System.out.println ("allowAnonLogging ->"+book.isAllowAnonLogging());
			}				
			
			if (strLine.contains("\"contentVersion\":"))
			{
				try{
					book.setContentVersion(strLine.substring(strLine.indexOf(":")+2, strLine.lastIndexOf(",")).replaceAll("\"", ""));
				} catch (Exception ex){
					book.setContentVersion(null);
				}
			//	System.out.println ("contentVersion ->"+book.getContentVersion());
			}		
			
			if (strLine.contains("\"smallThumbnail\":"))
			{
				try{
					book.setImagesLinksSmallThumbnails(strLine.substring(strLine.indexOf(":")+2, strLine.lastIndexOf(",")).replaceAll("\"", ""));
				} catch (Exception ex){
					book.setImagesLinksSmallThumbnails(null);
				}
			//	System.out.println ("smallThumbnail ->"+book.getImagesLinksSmallThumbnails());
			}	

			if (strLine.contains("\"thumbnail\":"))
			{
				try{
					book.setImagesLinksThubmnail(strLine.substring(strLine.indexOf(":")+2, strLine.lastIndexOf("\"")).replaceAll("\"", ""));
				} catch (Exception ex){
					book.setImagesLinksThubmnail(null);
				}
			//	System.out.println ("thumbnail ->"+book.getImagesLinksThubmnail());
			}	
			
			if (strLine.contains("\"language\":"))
			{
				try{
					book.setLanguage(strLine.substring(strLine.indexOf(":")+2, strLine.lastIndexOf("\"")).replaceAll("\"", ""));
				} catch (Exception ex){
					book.setLanguage(null);
				}
			//	System.out.println ("language ->"+book.getLanguage());
			}				
			
			if (strLine.contains("\"previewLink\":"))
			{
				try{
					book.setPreviewLink(strLine.substring(strLine.indexOf(":")+2, strLine.lastIndexOf("\"")).replaceAll("\"", ""));
				} catch (Exception ex){
					book.setPreviewLink(null);
				}
			//	System.out.println ("previewLink ->"+book.getPreviewLink());
			}				
			
			if (strLine.contains("\"infoLink\":"))
			{
				try{
					book.setInfoLink(strLine.substring(strLine.indexOf(":")+2, strLine.lastIndexOf("\"")).replaceAll("\"", ""));
				} catch (Exception ex){
					book.setInfoLink(null);
				}
			//	System.out.println ("infoLink ->"+book.getInfoLink());
			}				
			
			if (strLine.contains("\"canonicalVolumeLink\":"))
			{
				try{
					book.setCanonicalVolumeLink(strLine.substring(strLine.indexOf(":")+2, strLine.lastIndexOf("\"")).replaceAll("\"", ""));
				} catch (Exception ex){
					book.setCanonicalVolumeLink(null);
				}
			//	System.out.println ("canonicalVolumeLink ->"+book.getCanonicalVolumeLink());
			}	
			
			if (strLine.contains("\"country\":"))
			{
				try{
					book.setAccessInfoCountry(strLine.substring(strLine.indexOf(":")+2, strLine.lastIndexOf("\"")).replaceAll("\"", ""));
				} catch (Exception ex){
					book.setAccessInfoCountry(null);
				}
			//	System.out.println ("AccessInfo_country ->"+book.getAccessInfoCountry());
			}	
			
			if (strLine.contains("\"viewability\":"))
			{
				try{
					book.setAccessInfoViewAbility(strLine.substring(strLine.indexOf(":")+2, strLine.lastIndexOf("\"")).replaceAll("\"", ""));
				} catch (Exception ex){
					book.setAccessInfoViewAbility(null);
				}
			//	System.out.println ("AccessInfo_viewability ->"+book.getAccessInfoViewAbility());
			}	
			
			if (strLine.contains("\"embeddable\":"))
			{
				try{
					book.setAccessInfoEmbeddable(strLine.substring(strLine.indexOf(":")+2, strLine.lastIndexOf(",")).replaceAll("\"", ""));
				} catch (Exception ex){
					book.setAccessInfoEmbeddable(null);
				}
			//	System.out.println ("AccessInfo_embeddable ->"+book.getAccessInfoEmbeddable());
			}	
			
			if (strLine.contains("\"publicDomain\":"))
			{
				try{
					book.setAccessInfoPublicDomain( (strLine.substring(strLine.indexOf(":")+2, strLine.lastIndexOf(",")).replaceAll("\"", "")));
				} catch (Exception ex){
					book.setAccessInfoPublicDomain("false");
				}
			//	System.out.println ("AccessInfo_publicDomain ->"+book.isAccessInfoPublicDomain());
			}
			
			if (strLine.contains("\"textToSpeechPermission\":"))
			{
				try{
					book.setAccessInfotextToSpeechPermission(strLine.substring(strLine.indexOf(":")+2, strLine.lastIndexOf(",")).replaceAll("\"", ""));
				} catch (Exception ex){
					book.setAccessInfotextToSpeechPermission(null);
				}
			//	System.out.println ("AccessInfo_textToSpeechPermission ->"+book.getAccessInfotextToSpeechPermission());
			}

			if (strLine.contains("\"webReaderLink\":"))
			{
				try{
					book.setWebReaderLink(strLine.substring(strLine.indexOf(":")+2, strLine.lastIndexOf(",")).replaceAll("\"", ""));
				} catch (Exception ex){
					book.setWebReaderLink(null);
				}
			//	System.out.println ("webReaderLink ->"+book.getWebReaderLink());
			}

			if (strLine.contains("\"accessViewStatus\":"))
			{
				try{
					book.setAccessViewStatus(strLine.substring(strLine.indexOf(":")+2, strLine.lastIndexOf(",")).replaceAll("\"", ""));
				} catch (Exception ex){
					book.setAccessViewStatus(null);
				}
			//	System.out.println ("accessViewStatus ->"+book.getAccessViewStatus());
			}

			if (strLine.contains("\"quoteSharingAllowed\":"))
			{
				try{
					book.setQuoteSharingAllowed( (strLine.substring(strLine.indexOf(":")+2, strLine.lastIndexOf("e")+1).replaceAll("\"", "")));
				} catch (Exception ex){
					book.setQuoteSharingAllowed("false");
				}
			//	System.out.println ("quoteSharingAllowed ->"+book.isQuoteSharingAllowed());
			}

			if (strLine.contains("\"textSnippet\":"))
			{
				try{
					if (strLine.substring(strLine.indexOf(":")+2, strLine.lastIndexOf("e")).replaceAll("\"", "").length()<2999)
					{
						book.setSearchInfoTextSnippet(strLine.substring(strLine.indexOf(":")+2, strLine.lastIndexOf("e")+1).replaceAll("\"", ""));
					}else
					{
						book.setSearchInfoTextSnippet(strLine.substring(strLine.indexOf(":")+2, 2999));
					}
				} catch (Exception ex){
					book.setSearchInfoTextSnippet(null);
				}
			//	System.out.println ("SearchInfoTextSnippet ->"+book.getSearchInfoTextSnippet());
				tmpBook = book;
			}
		}
		else
		{
			if (type == "authors")
			{
				try{
					if (strLine.contains("[") && strLine.contains("]"))
					{
						if (strLine.substring(strLine.lastIndexOf("[")+1, strLine.lastIndexOf("]")).length() < 999)
						{
							if (strLine.substring(strLine.lastIndexOf("[")+1, strLine.lastIndexOf("]")).contains("{") ||strLine.substring(strLine.lastIndexOf("[")+1, strLine.lastIndexOf("]")).contains("(") )
							{
								book.setAuthors(null);
							}else
							{
								book.setAuthors(strLine.substring(strLine.lastIndexOf("[")+1, strLine.lastIndexOf("]")).replace(",", "|"));
							}
							
						}else
						{
							if (strLine.substring(strLine.lastIndexOf("[")+1, strLine.lastIndexOf("]")).contains("{") ||strLine.substring(strLine.lastIndexOf("[")+1, strLine.lastIndexOf("]")).contains("(") )
							{
								book.setAuthors(null);
							}else
							{
								book.setAuthors(strLine.substring(strLine.lastIndexOf("[")+1, 999).replace(",", "|"));
							}
						}
						
					}
				} catch (Exception e)
				{
					book.setAuthors(null);
				}
			//	System.out.println ("authors ->"+book.getAuthors());
			}
			
			if (type == "ISBN_10")
			{
				try{
					book.setISBN10(strLine.substring(strLine.indexOf(":")+3, strLine.lastIndexOf("\"")-0));
				} catch (Exception e)
				{
					book.setISBN10(null);
				}
			//	System.out.println ("ISBN_10 ->"+book.getISBN10());
			}
			
			if (type == "ISBN_13")
			{
				try{
					book.setISBN13(strLine.substring(strLine.indexOf(":")+3, strLine.lastIndexOf("\"")-0));
				} catch (Exception e)
				{
					book.setISBN13(null);
				}
			//	System.out.println ("ISBN_13 ->"+book.getISBN13());
			}
			
			if (type == "OTHER")
			{
				try{
					System.out.println(strLine.substring(strLine.indexOf(":")+3, strLine.lastIndexOf("\"")-0));
					book.setOTHER(strLine.substring(strLine.indexOf(":")+3, strLine.lastIndexOf("\"")-0));
				} catch (Exception e)
				{
					book.setOTHER(null);
				}
			//	System.out.println ("ISBN_13 ->"+book.getISBN13());
			}
			
			if (type == "categories")
			{
				try{
					book.setCategories(strLine.substring(strLine.lastIndexOf("[")+1, strLine.lastIndexOf("]")));
				} catch (Exception e)
				{
					book.setCategories(null);
				}
			//	System.out.println ("categories ->"+book.getCategories());
			}	
			
			if (type == "saleInfo_Country")
			{
				try{
					book.setSaleInfoCountry(strLine.substring(strLine.lastIndexOf(":")+3, strLine.lastIndexOf("\"")));
				} catch (Exception e)
				{
					book.setSaleInfoCountry(null);
				}
			//	System.out.println ("saleInfo_Country ->"+book.getSaleInfoCountry());
			}	
			
			if (type == "saleInfo_saleability")
			{
				try{
					book.setSaleInfoSaleability(strLine.substring(strLine.lastIndexOf(":")+3, strLine.lastIndexOf("\"")));
				} catch (Exception e)
				{
					book.setSaleInfoSaleability(null);
				}
			//	System.out.println ("saleInfo_saleability ->"+book.getSaleInfoSaleability());
			}	
			
			if (type == "saleInfo_isEbook")
			{
				try{
					book.setSaleInfoisEbook( (strLine.substring(strLine.lastIndexOf(":")+1, strLine.lastIndexOf("e")+1)));
				} catch (Exception e)
				{
					book.setSaleInfoisEbook("false");
				}
			//	System.out.println ("saleInfo_isEbook ->"+book.isSaleInfoisEbook());
			}	
			
			if (type == "epub")
			{
				try{
					book.setEpubIsAvailable( (strLine.substring(strLine.lastIndexOf(":")+1, strLine.lastIndexOf("e")+1)));
				} catch (Exception e)
				{
					book.setEpubIsAvailable("false");
				}
			//	System.out.println ("epub ->"+book.isEpubIsAvailable());
			}	
			
			if (type == "pdf")
			{
				try{
					book.setPfIsAvailable( (strLine.substring(strLine.lastIndexOf(":")+1, strLine.lastIndexOf("e")+1)));
				} catch (Exception e)
				{
					book.setPfIsAvailable("false");
				}
			//	System.out.println ("pdf ->"+book.isPfIsAvailable());
			}	
		}
		return tmpBook;
	}
}
