/**
 * 
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
import java.util.List;

import br.com.Books.Controller.sellerBooks;


//import br.com.Books.Controller.sellerBooks;

/**<b>Author</b>: Pedro Luiz da Silva Pereira<br>
 * <b>Date</b>: 07/03/2019<br>
 * <b>About</b>: This class has the goals:<br>
 * <ul>
 * 	<li>Read the HTML file with details about book sellers;</li>
 * 	<li>Prepare data from files, organize informations and call DAO package to persistence informations;<li>
 * </ul>
 */
public class extractSellerInformations {
	
	private static String pathFiles = "Scripts/Downloads/Sellers/";
//	private static String fileName = "booksID__UqCxWBKtUC.html";
	
	public List<sellerBooks> readHTMLSellerInformations(String idGoogle)  throws UnsupportedEncodingException {
		List<sellerBooks>sellerBooksList = new ArrayList<sellerBooks>();

	/** set File with informations about files HTML storage after script execution */
	File htmlFile = new File(pathFiles+"booksID"+idGoogle+".html");
	/** check file and directory exists physical, after start read file */
	if(htmlFile.exists() && !htmlFile.isDirectory()) { 
	    /** read file and set data in object Books from file */
		// Open the file
		FileInputStream fstream;
		try {
			fstream = new FileInputStream(htmlFile);
			InputStreamReader isr = new InputStreamReader(fstream, "UTF8");
			BufferedReader br = new BufferedReader(isr);
			String strLine, seller, price, rating = null;
			
			//Read File Line By Line
			try {
				while ((strLine = br.readLine()) != null)   
				{
					if (strLine.contains("<table cellspacing=0><tr><th class=column>Vendedor</th>"))
					{
						strLine = strLine.substring(strLine.indexOf("<table cellspacing=0><tr><th class=column>Vendedor</th>"), strLine.lastIndexOf("</table>")+8).replaceAll("Sem pre", "Sem preo").replaceAll("Sem avalia", "Sem avalia‹o");
						break;
					}
				}
				
				try
				{
					while (strLine.indexOf("dir=ltr>") !=-1)
					{
						sellerBooks sellerbooks = new sellerBooks();
						rating = strLine.substring(strLine.lastIndexOf("rating")+7,strLine.lastIndexOf("</span></td></tr>")-3);
						strLine = strLine.substring(0, strLine.lastIndexOf("rating"));
						if (strLine.substring(strLine.lastIndexOf("price")+6,strLine.lastIndexOf("</span></td>")-2).contains(","))
						{
							price = strLine.substring(strLine.lastIndexOf("price")+6+3,strLine.lastIndexOf("</span></td>"));
						}
						else
						{
							price = strLine.substring(strLine.lastIndexOf("price")+6,strLine.lastIndexOf("</span></td>")-2);
						}
						strLine = strLine.substring(0, strLine.lastIndexOf("price"));
						seller = strLine.substring(strLine.lastIndexOf("dir=ltr>")+8,strLine.lastIndexOf("</a></td>"));
						strLine = strLine.substring(0, strLine.lastIndexOf("dir=ltr>")-9);
						System.out.println("Seller: "+seller+" - Price: "+price+" - Rating: "+rating);
						sellerbooks.setIdGoogle(idGoogle);
						sellerbooks.setPrice(price);
						sellerbooks.setRating(rating);
						sellerbooks.setSeller(seller);
						sellerBooksList.add(sellerbooks);
						
					}
				}catch(Exception e)
				{
					System.out.println("Not found seller informations: "+e.toString());
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
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
		return sellerBooksList;
	}
	
}
