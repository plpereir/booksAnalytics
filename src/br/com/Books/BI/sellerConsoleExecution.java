package br.com.Books.BI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.com.Books.DAO.ConnectionFactory;
import br.com.Books.ETL.extractSellerInformations;

public class sellerConsoleExecution {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
        BufferedWriter output = null;
        try {
            File file = new File("Logs/log_Sellers.txt");
            output = new BufferedWriter(new FileWriter(file));

		extractSellerInformations extractsellerinformations = new extractSellerInformations();
		ConnectionFactory connectionFactory = new ConnectionFactory();
		try {
			ArrayList<String> idGoogle = new ArrayList<String>();
		     idGoogle = connectionFactory.getColumnListValues("Books","idGoogle");
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			output.write(dateFormat.format(date)+" - Starting save data Books Sellers");
			output.newLine();
		   System.out.println("Starting save data Books Sellers");
			for (String element : idGoogle) {
				dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				date = new Date();
				output.write(dateFormat.format(date)+" - Open file extract idGoogle: "+element);
				output.newLine();

				System.out.println("extract idGoogle: "+element);
			 connectionFactory.insertDataBooksSeller(extractsellerinformations.readHTMLSellerInformations(element));
			}
			dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			date = new Date();
			output.write(dateFormat.format(date)+" - Finished save data Books Sellers");
			output.newLine();
			   System.out.println("Finished save data Books Sellers");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 output.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
	}

}
