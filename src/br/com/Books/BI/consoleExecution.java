package br.com.Books.BI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.Books.Controller.Books;
import br.com.Books.DAO.ConnectionFactory;
import br.com.Books.ETL.extractFromFiles;
/**<b>Author</b>: Pedro Luiz da Silva Pereira<br>
 * <b>Date</b>: 28/02/2019<br>
 * <b>About</b>: This class has the goals:<br>
 * <ul>
 * 	<li>console execution to execute Terminal;</li>
 * 	<li>other tests;</li>
 
 * </ul>
 */
public class consoleExecution {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
        BufferedWriter output = null;
        try {
            File file = new File("Logs/log_Google.txt");
            output = new BufferedWriter(new FileWriter(file));
            
		/** set File with informations about files JSON storage after script execution */
		File jsonFile = new File("/Users/plpereir/Downloads/jsonFileList.txt");
		/** check file and directory exists physical, after start read file */
		if(jsonFile.exists() && !jsonFile.isDirectory()) { 
		    /** read file and set data in object Books from file */
			// Open the file
			FileInputStream fstream;
			try {
				fstream = new FileInputStream(jsonFile);
				InputStreamReader isr = new InputStreamReader(fstream, "UTF8");
				BufferedReader br = new BufferedReader(isr);
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				output.write(dateFormat.format(date)+" - Open file /Users/plpereir/Downloads/jsonFileList.txt");
				output.newLine();
				String strLine;
				//Read File Line By Line
				try {
					while ((strLine = br.readLine()) != null)   
					{
						System.out.println("Start extract from file: "+strLine);

						dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						date = new Date();
						output.write(dateFormat.format(date)+" - Start extract from file: "+strLine);
						output.newLine();

						Books book = new Books();
						extractFromFiles extractfromfiles = new extractFromFiles();
						ConnectionFactory connection	= new ConnectionFactory();
						connection.insertDataBooks(extractfromfiles.readBookFromJSON(strLine, book));
						
					}
						//Close the input stream
						fstream.close();
						System.out.println("Completed! All records inserted in database.");
						dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						date = new Date();
						output.write(dateFormat.format(date)+" - Completed! All records inserted in database.");
						output.newLine();
					} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			}
		 output.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
		}
}
