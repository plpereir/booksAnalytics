/**
 * 
 */
package br.com.Books.ETL;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.Books.DAO.ConnectionFactory;

/**
 * @author plpereir
 *
 */
public class generateARFF {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		PrintWriter writer = new PrintWriter("/Users/plpereir/OneDrive/MackBook Pro - plpereir/Documents/workspace/BooksAnalytics/DataMining/books.arff", "UTF-8");
		ConnectionFactory cf = new ConnectionFactory();
		ConnectionFactory cn = new ConnectionFactory();

		try {
			ArrayList<String> listColumns  = cn.getColumnNames("BooksAnalytics", "Books");
			System.out.println("@relation Books");
			writer.println("@relation Books");
			for (String element : listColumns)
			{
				if (allowFields(element))
				{
					ArrayList<String> listFieldValues = cf.getColumnListValues("Books",element);
					String attribute = new String("@attribute "+element+" {");
					for (String values : listFieldValues)
					{
						attribute = attribute + values + ",";
					}
					attribute = attribute.substring(0, attribute.length()-1) + "}";
					writer.println(attribute);
					System.out.println(attribute);
				}
			}
			writer.println("@data");
			System.out.println("@data");
			
			ArrayList<String> listFieldValues = cn.getTableSelect(sqlBooks());;
			for (String values : listFieldValues)
			{
				writer.println(values);
				System.out.println(values);
			}
			
			writer.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public ArrayList<String> getAttributesValues(String field)
	{
		ArrayList<String> listFieldValues = new ArrayList<String>();
		
		ConnectionFactory cn = new ConnectionFactory();
		try {
			listFieldValues = cn.getColumnListValues("Books",field);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			listFieldValues = null;
		}
		
		return listFieldValues;
	}
	
	public static boolean allowFields(String field)
	{
		boolean allowReturn = false;
		String allow = new String();
	    allow = allow + "idGoogle,"; 
		allow = allow + "AccessInfoCountry, ";
		allow = allow + "AccessInfoEmbeddable,";
		allow = allow + "AccessInfoPublicDomain,";
		allow = allow + "AccessInfoViewAbility,";
		allow = allow + "AccessViewStatus,";
		allow = allow + "AllowAnonLogging,";
		//allow = allow + "Authors,";
		allow = allow + "AverageRatin,";
		//allow = allow + "Categories, ";
		allow = allow + "EpubIsAvailable,";
		//allow = allow + "Etag, ";
		allow = allow + "ISBN10,";
		allow = allow + "ISBN13, ";
		allow = allow + "Kind, ";
		allow = allow + "Language,";
		allow = allow + "MaturityRating,";
		allow = allow + "PageCount,";
		allow = allow + "PfIsAvailable,";
		allow = allow + "PrintType,";
		allow = allow + "PublishedDate,";
		//allow = allow + "Publisher,";
		allow = allow + "QuoteSharingAllowed,";
		allow = allow + "RatingsCount,";
		allow = allow + "ReadingModesImage,";
		allow = allow + "ReadingModesText,";
		allow = allow + "SaleInfoCountry,";
		allow = allow + "SaleInfoisEbook,";
		allow = allow + "SaleInfoSaleability,";
		allow = allow + "UploadDate"; 		
		
		if (allow.contains(field))
		{
			allowReturn = true;
		}
		
		return allowReturn;
	}
	public static String sqlBooks()
	{
		String allow = new String();
	    allow = allow + "Select "; 
		allow = allow + "AccessInfoCountry, ";
		allow = allow + "AccessInfoEmbeddable,";
		allow = allow + "AccessInfoPublicDomain,";
		allow = allow + "AccessInfoViewAbility,";
		allow = allow + "AccessViewStatus,";
		allow = allow + "AllowAnonLogging,";
		//allow = allow + "Authors,";
		allow = allow + "AverageRatin,";
		//allow = allow + "Categories, ";
		allow = allow + "EpubIsAvailable,";
		//allow = allow + "Etag, ";
		allow = allow + "id, ";
		allow = allow + "idGoogle, ";
		allow = allow + "ISBN10,";
		allow = allow + "ISBN13, ";
		allow = allow + "Kind, ";
		allow = allow + "Language,";
		allow = allow + "MaturityRating,";
		allow = allow + "PageCount,";
		allow = allow + "PfIsAvailable,";
		allow = allow + "PrintType,";
		allow = allow + "PublishedDate,";
		//allow = allow + "Publisher,";
		allow = allow + "QuoteSharingAllowed,";
		allow = allow + "RatingsCount,";
		allow = allow + "ReadingModesImage,";
		allow = allow + "ReadingModesText,";
		allow = allow + "SaleInfoCountry,";
		allow = allow + "SaleInfoisEbook,";
		allow = allow + "SaleInfoSaleability,";
		allow = allow + "UploadDate  from BooksAnalytics.Books"; 		

		return allow;
	}

}
