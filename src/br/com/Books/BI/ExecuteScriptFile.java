package br.com.Books.BI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.com.Books.Controller.Books;
import br.com.Books.DAO.ConnectionFactory;
import br.com.Books.DW.LoadFacts;
import br.com.Books.ETL.ExtractPublishNewsInformations;
import br.com.Books.ETL.extractFromFiles;
import br.com.Books.ETL.extractSellerInformations;

public class ExecuteScriptFile {
	public static LoadFacts loadFacts = new LoadFacts();
	public static int[ ] cat = {0,9,13,5,11,8};
	public static String[] GoogleKey = {
        "AIzaSyAkLxVjRJTJ734-5-u2aiLM8pgnw27xYYs",
        "AIzaSyCFz22SsbRengPEZ90stg7TJI_sePrc76A",
        "AIzaSyDkUarP28yQfWJZbWxsid0Ix3wyqcdc8Eg",
        "AIzaSyAXe79kss51xGB1T9hjUi41YL_oZxcitNY", //active
        "AIzaSyDGtD9Y-Z3dWvoez415sNSfRDJRuC5qQSc", //active
        "AIzaSyDqFTjDBy4-ZZW6qbyHIfqcaO05QgLpBkk", //active
        "AIzaSyDC3RdBaW2krTsilZpk1p26zaGw5qikUyA" //active
	};
	
	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub
		Date data = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss");


		System.out.println("Start load tables PublishNews, Google and Sellers execution Script: "+ dateFormat.format(data));
		// readBashScript();
		// ExecuteLoadPublishNews();

		// loadGoogleInformationsFromISBNByPublishnews();
		// loadGoogleInformationsFromNameByPublishnews();
		// ExecuteLoadDataBaseGoogleInformationsByISBN();
		// ExecuteLoadDataBaseGoogleInformationsByName();
		// loadSellersInformationsFromGoogle();
		// ExecuteLoadDataBaseSellersInformations();
		data = new Date();
		System.out.println("Start Load DW : "+ dateFormat.format(data));
			loadFacts.loadAllFacts();
		data = new Date();
		System.out.println("Finish Load DW : "+ dateFormat.format(data));
		data = new Date();
		System.out.println("Stop execution load tables PublishNews, Google and Sellers Script: "+ dateFormat.format(data));

	}

	public static void readBashScript() {
		Date data = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		int year = Integer.parseInt(format.format(data));
		data = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss");
		System.out
				.println("Starting data download of the historical monthly from PublishNews "
						+ dateFormat.format(data));
		for (int x = 2010; x < year + 1; x++) {
			for (int z = 1; z < 13; z++) {
				for (int y = 0; y < 6; y++) {
					try {
						Process proc = Runtime.getRuntime().exec(
								"Scripts/App/publishNews.sh " + cat[y] + " " + x
										+ " " + z); // Whatever you want to
													// execute
						BufferedReader read = new BufferedReader(
								new InputStreamReader(proc.getInputStream()));
						System.out.println("Download cat" + cat[y] + ": " + x + "-"
								+ z + ": " + proc.toString());
						try {
							proc.waitFor();
						} catch (InterruptedException e) {
							System.out.println(e.getMessage());
						}
						while (read.ready()) {
							System.out.println(read.readLine());
						}
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				}
			}
		}
		data = new Date();
		System.out
				.println("Finished data download of the historical monthly from PublishNews "
						+ dateFormat.format(data));
	}

	public static void ExecuteLoadPublishNews() {
		Date data = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		int year = Integer.parseInt(format.format(data));
		int count = 0;
		data = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss");

		System.out
				.println("Starting insert data in database of the historical monthly from PublishNews "
						+ dateFormat.format(data));

		data = new Date();
		for (int x = 2010; x < year + 1; x++) {
			for (int z = 1; z < 13; z++) {
				for (int y = 0; y < 6; y++) {
					count = count + 1;
					ExtractPublishNewsInformations extractPublishNewsInformations = new ExtractPublishNewsInformations();
					ConnectionFactory cn = new ConnectionFactory();
					cn.insertPublishNews(extractPublishNewsInformations
							.readHTMLPublishNews(cat[y], x, z));
				}
			}
		}
		data = new Date();
		System.out
				.println("Finished insert "
						+ count
						+ " records at database of the historical monthly from PublishNews "
						+ dateFormat.format(data));
	}

	public static void loadGoogleInformationsFromISBNByPublishnews()
			throws SQLException {
		Date data = new Date();

		ConnectionFactory cn = new ConnectionFactory();
		ArrayList<String> ISBNList = cn.getColumnListValues("PublishNews",
				"ISBN");
		data = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss");
		System.out.println("Starting data download from Google "
				+ dateFormat.format(data));
		int i=1;//voltar para 0
		int count=0;
		for (String element : ISBNList) {
			count = count +1;
			if (count > 900)
			{
				i = 2;
			}
			if (count > 1900)
			{
				i = 3;
			}	
	
			System.out.println("Qty: "+count+" ISBN: "+element+" GoogleKey: "+GoogleKey[i]);
			try {
				Process proc = Runtime.getRuntime().exec(
						"Scripts/App/googleinformations.sh " + element+" "+GoogleKey[i]); // Whatever
																			// you
																			// want
																			// to
																			// execute
				BufferedReader read = new BufferedReader(new InputStreamReader(
						proc.getInputStream()));
				System.out.println("Download file GoogleBooks" + element
						+ " : " + proc.toString());
				try {
					proc.waitFor();
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
				while (read.ready()) {
					System.out.println(read.readLine());
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

		}
	}
	public static void loadGoogleInformationsFromNameByPublishnews()
			throws SQLException {
		Date data = new Date();

		ConnectionFactory cn = new ConnectionFactory();
		ArrayList<String> ISBNList = cn.getColumnListValues("PublishNews",
				"Name");
		data = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss");
		System.out.println("Starting data download from Google "
				+ dateFormat.format(data));
		int i=4;
		int count=0;
		for (String element : ISBNList) {
			count = count +1;
			if (count > 900)
			{
				i = 5;
			}
			if (count > 1900)
			{
				i = 6;
			}	
	
			System.out.println("Qty: "+count+" Name: "+element+" GoogleKey: "+GoogleKey[i]);
			try {
				Process proc = Runtime.getRuntime().exec(
						"Scripts/App/googleinformationsName.sh " + element.replace(" ", "%20")+" "+GoogleKey[i]); // Whatever
																			// you
																			// want
																			// to
																			// execute
				BufferedReader read = new BufferedReader(new InputStreamReader(
						proc.getInputStream()));
				System.out.println("Download file GoogleBooks" + element
						+ " : " + proc.toString());
				try {
					proc.waitFor();
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
				while (read.ready()) {
					System.out.println(read.readLine());
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

		}
	}
	public static void ExecuteLoadDataBaseGoogleInformationsByISBN()
			throws SQLException, IOException {
		Date data = new Date();

		ConnectionFactory cn = new ConnectionFactory();
		ArrayList<String> ISBNList = cn.getColumnListValues("PublishNews",
				"ISBN");
		data = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss");
		System.out.println("Starting data download from Google "
				+ dateFormat.format(data));
		BufferedWriter output = null;
		File file = new File("Logs/log_Google.txt");
		output = new BufferedWriter(new FileWriter(file));

		for (String element : ISBNList) {

			dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			data = new Date();
			output.write(dateFormat.format(data)
					+ " - Start extract from file: " + "GoogleBooks" + element
					+ ".json");
			output.newLine();
			Books book = new Books();
			extractFromFiles extractfromfiles = new extractFromFiles();
			ConnectionFactory connection = new ConnectionFactory();
			connection.insertDataBooks(extractfromfiles.readBookFromJSON(
					"GoogleBooks" + element + ".json", book));

		}
		System.out.println("Completed! All records inserted in database.");
		dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		data = new Date();
		output.newLine();
		output.write(dateFormat.format(data)
				+ " - Completed! All records inserted in database.");
		output.close();

	}
	
	public static void ExecuteLoadDataBaseGoogleInformationsByName()
			throws SQLException, IOException {
		Date data = new Date();

		ConnectionFactory cn = new ConnectionFactory();
		ArrayList<String> NameList = cn.getColumnListValues("PublishNews",
				"Name");
		data = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss");
		System.out.println("Starting data download from Google "
				+ dateFormat.format(data));
		BufferedWriter output = null;
		File file = new File("Logs/log_Google.txt");
		output = new BufferedWriter(new FileWriter(file));

		for (String element : NameList) {

			dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			data = new Date();
			output.write(dateFormat.format(data)
					+ " - Start extract from file: " + "GoogleBooks" + element
					+ ".json");
			output.newLine();
			Books book = new Books();
			extractFromFiles extractfromfiles = new extractFromFiles();
			ConnectionFactory connection = new ConnectionFactory();
			connection.insertDataBooks(extractfromfiles.readBookFromJSON(
					"GoogleBooks" + element.replace(" ", "%20") + "Name.json", book));

		}
		System.out.println("Completed! All records inserted in database.");
		dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		data = new Date();
		output.newLine();
		output.write(dateFormat.format(data)
				+ " - Completed! All records inserted in database.");
		output.close();

	}
	public static void loadSellersInformationsFromGoogle() throws SQLException {
		Date data = new Date();

		ConnectionFactory cn = new ConnectionFactory();
		ArrayList<String> ISBNList = cn
				.getColumnListValues("Books", "idGoogle");
		data = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss");
		System.out.println("Starting Sellers data download from Google "
				+ dateFormat.format(data));

		for (String element : ISBNList) {
			try {
				Process proc = Runtime.getRuntime().exec(
						"Scripts/App/sellersinformations.sh " + element); // Whatever
																			// you
																			// want
																			// to
																			// execute
				BufferedReader read = new BufferedReader(new InputStreamReader(
						proc.getInputStream()));
				System.out.println("Download file Sellers " + element + " : "
						+ proc.toString());
				try {
					proc.waitFor();
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
				while (read.ready()) {
					System.out.println(read.readLine());
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

		}
	}

	public static void ExecuteLoadDataBaseSellersInformations()
			throws SQLException, IOException {

		BufferedWriter output = null;
		try {
			File file = new File("Logs/log_Sellers.txt");
			output = new BufferedWriter(new FileWriter(file));

			extractSellerInformations extractsellerinformations = new extractSellerInformations();
			ConnectionFactory connectionFactory = new ConnectionFactory();
			try {
				ArrayList<String> idGoogle = new ArrayList<String>();
				idGoogle = connectionFactory.getColumnListValues("Books",
						"idGoogle");
				DateFormat dateFormat = new SimpleDateFormat(
						"yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				output.write(dateFormat.format(date)
						+ " - Starting save data Books Sellers");
				output.newLine();
				System.out.println("Starting save data Books Sellers");
				for (String element : idGoogle) {
					dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					date = new Date();
					output.write(dateFormat.format(date)
							+ " - Open file extract idGoogle: " + element);
					output.newLine();

					System.out.println("extract idGoogle: " + element);
					connectionFactory
							.insertDataBooksSeller(extractsellerinformations
									.readHTMLSellerInformations(element));
				}
				dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				date = new Date();
				output.write(dateFormat.format(date)
						+ " - Finished save data Books Sellers");
				output.newLine();
				System.out.println("Finished save data Books Sellers");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
