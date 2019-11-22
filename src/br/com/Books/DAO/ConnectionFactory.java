package br.com.Books.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import br.com.Books.Controller.Books;
import br.com.Books.Controller.BooksDimensionFrom;
import br.com.Books.Controller.DashBoard;
import br.com.Books.Controller.PublishNews;
import br.com.Books.Controller.ReadingFact;
import br.com.Books.Controller.SalesFact;
import br.com.Books.Controller.sellerBooks;
/**<b>Author</b>: Pedro Luiz da Silva Pereira<br>
 * <b>Date</b>: 28/02/2019<br>
 * <b>About</b>: This class has the goals:<br>
 * <ul>
 * 	<li>- management data from Books object and MySQL Database;</li>
 * 	<li>- management data from BooksSeller object and MySQL Database;</li>
 *	<li>- Providing data to BI Analysis;</li>
 * </ul>
 */

public class ConnectionFactory {
	public	Connection	getConnection() {
		Properties properties = new Properties();
		properties.setProperty("user", "xxxxxxx");
		properties.setProperty("password", "xxxxxx");
		properties.setProperty("useSSL", "false");
		properties.setProperty("autoReconnect", "true");
		try	{
			return	DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/BooksAnalytics",properties);
			
		}	catch	(SQLException	e)	{
			throw new	RuntimeException(e);
		}
	}
	
	
	public void insertDataBooks (List<Books> bookslist)
	{
		for(Books tmpBook : bookslist) {

	//	Books tmpBook = new Books();
		/** Connection database books Analysis*/
		Connection con = new ConnectionFactory().getConnection();
		
		/** Create a preparedStatement */
		
		String sql = "INSERT INTO BooksAnalytics.Books "+
					 "(idGoogle, "
					 + "AccessInfoCountry,"
					 + "AccessInfoEmbeddable,"
					 + "AccessInfoPublicDomain,"
					 + "AccessInfotextToSpeechPermission, "
					 + "AccessInfoViewAbility, "
					 + "AccessViewStatus, "
					 + "AllowAnonLogging, "
					 + "Authors,"
					 + "AverageRatin,"
					 + "CanonicalVolumeLink,"
					 + "Categories,"
					 + "ContentVersion, "
					 + "Description, "
					 + "EpubIsAvailable,"
					 + "Etag,"
					 + "ImagesLinksSmallThumbnails, "
					 + "ImagesLinksThubmnail, "
					 + "InfoLink, "
					 + "ISBN10, "
					 + "ISBN13,"
					 + "OTHER, "
					 + "Kind, "
					 + "Language, "
					 + "MaturityRating, "
					 + "PageCount, "
					 + "PfIsAvailable, "
					 + "PreviewLink, "
					 + "PrintType, "
					 + "PublishedDate, "
					 + "Publisher, "
					 + "QuoteSharingAllowed, "
					 + "RatingsCount, "
					 + "ReadingModesImage, "
					 + "ReadingModesText, "
					 + "SaleInfoCountry, "
					 + "SaleInfoisEbook, "
					 + "SaleInfoSaleability, "
					 + "SearchInfoTextSnippet, "
					 + "SelfLink, "
					 + "SubTitle, "
					 + "Title, "
					 + "WebReaderLink, "
					 + "UploadDate) "
					 + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			/** fill the values */
			stmt.setString(1, tmpBook.getId());
			stmt.setString(2, tmpBook.getAccessInfoCountry());
			stmt.setString(3, tmpBook.getAccessInfoEmbeddable());
			stmt.setString(4, tmpBook.isAccessInfoPublicDomain());
			stmt.setString(5, tmpBook.getAccessInfotextToSpeechPermission());
			stmt.setString(6, tmpBook.getAccessInfoViewAbility());
			stmt.setString(7, tmpBook.getAccessViewStatus());
			stmt.setString(8, tmpBook.isAllowAnonLogging());
			stmt.setString(9, tmpBook.getAuthors());
			stmt.setFloat(10, tmpBook.getAverageRatin());
			stmt.setString(11, tmpBook.getCanonicalVolumeLink());
			stmt.setString(12, tmpBook.getCategories());
			stmt.setString(13, tmpBook.getContentVersion());
			stmt.setString(14, tmpBook.getDescription());
			stmt.setString(15, tmpBook.isEpubIsAvailable());
			stmt.setString(16, tmpBook.getEtag());
			stmt.setString(17, tmpBook.getImagesLinksSmallThumbnails());
			stmt.setString(18, tmpBook.getImagesLinksThubmnail());
			stmt.setString(19, tmpBook.getInfoLink());
			stmt.setString(20, tmpBook.getISBN10());
			stmt.setString(21, tmpBook.getISBN13());
			stmt.setString(22, tmpBook.getOTHER());
			stmt.setString(23, tmpBook.getKind());
			stmt.setString(24, tmpBook.getLanguage());
			stmt.setString(25, tmpBook.getMaturityRating());
			stmt.setInt(26, Integer.parseInt(tmpBook.getPageCount()));
			stmt.setString(27, tmpBook.isPfIsAvailable());
			stmt.setString(28, tmpBook.getPreviewLink());
			stmt.setString(29, tmpBook.getPrintType());
			stmt.setString(30, tmpBook.getPublishedDate());
			stmt.setString(31, tmpBook.getPublisher());
			stmt.setString(32, tmpBook.isQuoteSharingAllowed());
			stmt.setInt(33, tmpBook.getRatingsCount());
			stmt.setString(34, tmpBook.getReadingModesImage());
			stmt.setString(35, tmpBook.getReadingModesText());
			stmt.setString(36, tmpBook.getSaleInfoCountry());
			stmt.setString(37, tmpBook.isSaleInfoisEbook());
			stmt.setString(38, tmpBook.getSaleInfoSaleability());
			stmt.setString(39, tmpBook.getSearchInfoTextSnippet());
			stmt.setString(40, tmpBook.getSelfLink());
			stmt.setString(41, tmpBook.getSubTitle());
			stmt.setString(42, tmpBook.getTitle());
			stmt.setString(43, tmpBook.getWebReaderLink());
			stmt.setDate(44, new	java.sql.Date(
					Calendar.getInstance().getTimeInMillis()));			
			
				//insertBooks.setSellerbooks(null);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
		    String query = "select count(*) from BooksAnalytics.Books where (idGoogle='"+tmpBook.getId()+"' and UploadDate = '"+dateFormat.format(Calendar.getInstance().getTimeInMillis())+"');";
		    PreparedStatement st = con.prepareStatement(query);
		    ResultSet rs = st.executeQuery();
		    int numberRow = 0;
		    while(rs.next()){
		      numberRow = rs.getInt(1);
		    }

			if (numberRow == 0)
			{
			  //  System.out.println("New Record query: "+query+" - rs.getInt(1): "+numberRow);
				stmt.execute();
			} else {
			   // System.out.println("Not Save Record query: "+query+" - rs.getInt(1): "+numberRow);
			}
				rs.close();
				st.close();
				stmt.close();
			con.close();
			
			   // System.out.println("new record successfully inserted: "+tmpBook.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.toString());
		}
		

		}
	}
	
	
	public void loadReadingFact() throws SQLException
	{
		ReadingFact salesfact = new ReadingFact();
	    String query = "select distinct A.RankingNumber as RankingNumber, A.RankingVolume as RankingVolume, A.ReferenceDate as ReferenceDate, A.ISBN as BookCode, A.Category as CategoryCode, B.AverageRatin as AverageRating, B.RatingsCount as CountRating";
	    query = query + " from BooksAnalytics.PublishNews as A, BooksAnalytics.Books as B ";
	    query = query + " where ((A.ISBN = B.ISBN10) or (A.ISBN = B.ISBN13)) order by A.ISBN desc LIMIT 100000;";
		Connection con = new ConnectionFactory().getConnection();
	    PreparedStatement st = con.prepareStatement(query);
	    ResultSet rs = st.executeQuery();

	    int records = 0;
	    while(rs.next()){
	    	salesfact.setRankingNumber(rs.getString(1));
	    	salesfact.setRankingVolume(rs.getString(2));
	    	salesfact.setReferenceDate(rs.getString(3));
	    	salesfact.setBookCode(rs.getString(4));
	    	salesfact.setCategoryCode(rs.getString(5));
	    	salesfact.setAverageRating(rs.getString(6));
	    	salesfact.setCountRating(rs.getString(7));

	      
		String sql = "insert into BooksAnalytics.ReadingFact  "+
					 " (RankingNumber, RankingVolume, ReferenceDate, "
					 + "BookCode, CategoryCode, AverageRating, CountRating)" +
					 " Values (?,?,?,?,?,?,?)";
				PreparedStatement stmt;

				stmt = con.prepareStatement(sql);
			/** fill the values */
				stmt.setLong(1,salesfact.getRankingNumber());
				stmt.setLong(2,salesfact.getRankingVolume());
				stmt.setString(3,salesfact.getReferenceDate());
				stmt.setString(4,salesfact.getBookCode());
				stmt.setLong(5,salesfact.getCategoryCode());
				stmt.setFloat(6,salesfact.getAverageRating());
				stmt.setFloat(7,salesfact.getCountRating());
				stmt.execute();
				stmt.close();
				
	      records = records+1;
	      System.out.println("ISBN incluso: "+salesfact.getBookCode()+" - record: "+records);
	    }	

		con.close();
	}


	public void loadSalesFact() throws SQLException
	{
		SalesFact salesfact = new SalesFact();
	    String query = "select distinct A.ReferenceDate as ReferenceDate, A.ISBN as BookCode, A.Category as CategoryCode, B.idGoogle ";
	    query = query + " from BooksAnalytics.PublishNews as A, BooksAnalytics.Books as B ";
	    query = query + " where ((A.ISBN = B.ISBN10) or (A.ISBN = B.ISBN13))  order by A.ReferenceDate, A.ISBN desc LIMIT 100000;";
		Connection con = new ConnectionFactory().getConnection();
	    PreparedStatement st = con.prepareStatement(query);
	    ResultSet rs = st.executeQuery();

	    int records = 0;
	    while(rs.next()){
	    	salesfact.setReferenceDate(rs.getString(1));
	    	salesfact.setBookCode(rs.getString(2));
	    	salesfact.setCategoryCode(rs.getString(3));
	    	
	    	/*
	    	 * selecionando as tabelas de vendas 
	    	 * */
	    	String querySellers = "Select UploadDate, idGoogle, min(Price), max(Price), avg(Price), count(*) as Qty from BooksSellers where Price <> 'Sem Preo' and idGoogle='"+rs.getString(4)+"' group by UploadDate, idGoogle order by Qty desc;";
			Connection conSellers = new ConnectionFactory().getConnection();
		    PreparedStatement stSellers = conSellers.prepareStatement(querySellers);
		    ResultSet rsSellers = stSellers.executeQuery();

		    while(rsSellers.next())
		    {
		    	salesfact.setLeastPrice(rsSellers.getString(3));
		    	salesfact.setMaximumPrice(rsSellers.getString(4));
		    	salesfact.setAveragePrice(rsSellers.getString(5));
		    	salesfact.setSalesCount(rsSellers.getString(6));
		    }
		    rsSellers.close();
		    stSellers.close();
		    conSellers.close();
	    	
		String sql = "insert into BooksAnalytics.SalesFact  "+
					 " (ReferenceDate, BookCode, SalesCount, LeastPrice, "
					 + "MaximumPrice, AveragePrice, CategoryCode) " +
					 " Values (?,?,?,?,?,?,?)";
				PreparedStatement stmt;

				stmt = con.prepareStatement(sql);
			/** fill the values */
				stmt.setString(1,salesfact.getReferenceDate());
				stmt.setString(2,salesfact.getBookCode());
				stmt.setInt(3,salesfact.getSalesCount());
				stmt.setFloat(4,salesfact.getLeastPrice());
				stmt.setFloat(5,salesfact.getMaximumPrice());
				stmt.setFloat(6,salesfact.getAveragePrice());
				stmt.setInt(7,salesfact.getCategoryCode());
				
				stmt.execute();
				stmt.close();
				
	      records = records+1;
	      System.out.println("ISBN incluso: "+salesfact.getBookCode()+" - record: "+records);
	    }	

		con.close();
	}
	/**
	 * This method select categories data from table Books to insert table DW CategoryFact
	 * @return
	 * @throws SQLException 
	 */
	
	public void loadBookDimension() throws SQLException
	{
		BooksDimensionFrom booksDimension = new BooksDimensionFrom();
	    String query = "select distinct A.ISBN as BookCode, A.Name as BookTitle, A.Description as BookDescription, A.Pages as CountPage, B.SaleInfoisEbook as PrintType , A.Author as Authors, B.SaleInfoSaleability as SalesAvailable, B.AccessInfoPublicDomain as PublicDomain, B.AccessViewStatus as PreviewAvaliable, A.Category as BookCategory ";
	    query = query + " from BooksAnalytics.PublishNews as A, BooksAnalytics.Books as B ";
	    query = query + " where ((A.ISBN = B.ISBN10) or (A.ISBN = B.ISBN13)) order by A.ISBN desc LIMIT 100000;";
		Connection con = new ConnectionFactory().getConnection();
	    PreparedStatement st = con.prepareStatement(query);
	    ResultSet rs = st.executeQuery();

	    int records = 0;
	    while(rs.next()){
	      booksDimension.setBookCode(rs.getString(1));
	      booksDimension.setBookTitle(rs.getString(2));
	      booksDimension.setBookDescription(rs.getString(3));
	      booksDimension.setCountPage(rs.getString(4));
	      booksDimension.setPrintType(rs.getString(5));
	      booksDimension.setAuthors(rs.getString(6));
	      booksDimension.setSalesAvailable(rs.getString(7));
	      booksDimension.setPublicDomain(rs.getString(8));
	      booksDimension.setPreviewAvaliable(rs.getString(9));
	      booksDimension.setBookCategory(rs.getString(10));
	      
		String sql = "insert into BooksAnalytics.BookDimension  "+
					 " (BookCode, BookTitle, BookDescription, CountPage, PrintType, Authors, "
					 + "SalesAvailable,PublicDomain, PreviewAvaliable, BookCategory)" +
					 " Values (?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement stmt;

				stmt = con.prepareStatement(sql);
			/** fill the values */
				stmt.setString(1,booksDimension.getBookCode());
				stmt.setString(2,booksDimension.getBookTitle());
				stmt.setString(3,booksDimension.getBookDescription());
				stmt.setLong(4,booksDimension.getCountPage());
				stmt.setLong(5,booksDimension.getPrintType());
				stmt.setString(6,booksDimension.getAuthors());
				stmt.setInt(7,booksDimension.isSalesAvailable());
				stmt.setInt(8,booksDimension.isPublicDomain());
				stmt.setInt(9,booksDimension.isPreviewAvaliable());
				stmt.setLong(10,booksDimension.getBookCategory());
				stmt.execute();
				stmt.close();
				
	      records = records+1;
	      System.out.println("ISBN incluso: "+booksDimension.getBookCode()+" - record: "+records);
	    }	

		con.close();
	}
	
	public void loadCategoryItems(ArrayList<String> categories)
	{
		for (String category : categories)
		{
			/** Connection database books Analysis*/
			Connection con = new ConnectionFactory().getConnection();
			String sql = "INSERT INTO BooksAnalytics.CategoryDimension "+
						 "(Category) Values (?)";
			PreparedStatement stmt;
			try {
				stmt = con.prepareStatement(sql);
				/** fill the values */
				try{
					stmt.setString(1,category.substring(0, category.lastIndexOf("    ],")-1));
				}catch(Exception ex)
				{
					stmt.setString(1, category);
				}
			    String query = "select count(*) from BooksAnalytics.CategoryDimension where (Category='"+category+"');";
			    PreparedStatement st = con.prepareStatement(query);
			    ResultSet rs = st.executeQuery();
			    int numberRow = 0;
			    while(rs.next()){
			      numberRow = rs.getInt(1);
			    }

				if (numberRow == 0)
				{
				    System.out.println("New Record category: "+category+" - rs.getInt(1): "+numberRow);
					stmt.execute();
				} else {
				    System.out.println("Not Save Record category: "+category+" - rs.getInt(1): "+numberRow);
				}
				stmt.close();
				con.close();
				
				   // System.out.println("new record successfully inserted: "+tmpBook.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.toString() + " category: "+category+" sql: "+sql);
			}
		}
	}

	public void insertDataBooksSeller (List<sellerBooks> sellerBooksList)
	{
		for(sellerBooks tmpSellerBooks : sellerBooksList) {

	//	Books tmpBook = new Books();
		/** Connection database books Analysis*/
		Connection con = new ConnectionFactory().getConnection();
		
		/** Create a preparedStatement */
		
		String sql = "INSERT INTO BooksAnalytics.BooksSellers "+
					 "(idGoogle, "
					 + "Seller,"
					 + "Price,"
					 + "Rating,"
					 + "UploadDate) "
					 + "VALUES (?,?,?,?,?) ";
		
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			/** fill the values */
			stmt.setString(1, tmpSellerBooks.getIdGoogle());
			stmt.setString(2, tmpSellerBooks.getSeller());
			stmt.setString(3, tmpSellerBooks.getPrice());
			stmt.setString(4, tmpSellerBooks.getRating());
			stmt.setDate(5, new	java.sql.Date(
					Calendar.getInstance().getTimeInMillis()));			
			stmt.execute();
			stmt.close();
			con.close();
			
			   // System.out.println("new record successfully inserted: "+tmpBook.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.toString());
		}
		}
	}
	
	public void insertPublishNews (List<PublishNews> publishNewsList)
	{
		for(PublishNews tmpPublishNews : publishNewsList) {

	//	Books tmpBook = new Books();
		/** Connection database books Analysis*/
		Connection con = new ConnectionFactory().getConnection();
		
		/** Create a preparedStatement */
		
		String sql = "INSERT INTO BooksAnalytics.PublishNews "+
					 "(ReferenceDate, "
					 + "Author,"
					 + "Category,"
					 + "Description,"
					 + "image,"
					 + "ISBN,"
					 + "Name,"
					 + "Pages,"
					 + "Price,"
					 + "Publisher,"
					 + "RankingNumber,"
					 + "RankingVolume,"
					 + "Translate) "
					 + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			/** fill the values */
				stmt.setString(1, tmpPublishNews.getReferenceDate());
				stmt.setString(2, tmpPublishNews.getAuthor());
				stmt.setString(3, tmpPublishNews.getCategory());
				stmt.setString(4, tmpPublishNews.getDescription());		
				stmt.setString(5, tmpPublishNews.getImage());
				stmt.setString(6, tmpPublishNews.getISBN());
				stmt.setString(7, tmpPublishNews.getName());
				try{
					stmt.setInt(8, tmpPublishNews.getPages());
					
				}catch (Exception ex)
				{
					stmt.setInt(8, 0);
					
				}
				try{
					stmt.setFloat(9, tmpPublishNews.getPrice());
					
				}catch (Exception ex)
				{
					stmt.setFloat(9, 0);
				}
					
				stmt.setString(10, tmpPublishNews.getPublisher());
				try{
					stmt.setInt(11, tmpPublishNews.getRankingNumber());
					
				}catch (Exception ex)
				{
					stmt.setInt(11,0);
				}
				try{
					stmt.setInt(12, tmpPublishNews.getRankingVolume());					
				}catch (Exception ex)
				{
					stmt.setInt(12, 0);
				}					

				stmt.setString(13, tmpPublishNews.getTranslate());

			stmt.execute();
			stmt.close();
			con.close();
			
			   // System.out.println("new record successfully inserted: "+tmpBook.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.toString());
		}
		}
	}
	
	
	public ArrayList<String> getColumnListValues(String getTable, String getcolumn) throws SQLException
	{
		ArrayList<String> getcolumnlistalues = new ArrayList<String>();
	    Connection con = new ConnectionFactory().getConnection();
	    String query = "select " +getcolumn+", count(*) as Qty from BooksAnalytics."+getTable+" where "+getcolumn+" is not null group by "+getcolumn+" order by Qty desc LIMIT 100000;";
	  //  String query = "select distinct " +getcolumn+" from BooksAnalytics."+getTable+" order by "+getcolumn+" asc";
	    PreparedStatement st = con.prepareStatement(query);
	    ResultSet rs = st.executeQuery();
	    while(rs.next()){
	    	getcolumnlistalues.add(rs.getString(1));
	    }

		rs.close();
		st.close();
		con.close();
		
		return getcolumnlistalues;
	}
	
	public ArrayList<String> getColumnNames(String getSchema, String getTable) throws SQLException
	{
		ArrayList<String> getcolumnnames = new ArrayList<String>();
	    Connection con = new ConnectionFactory().getConnection();
	    String query = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = '"+getSchema+"' AND TABLE_NAME = '"+getTable+"';";
	    PreparedStatement st = con.prepareStatement(query);
	    ResultSet rs = st.executeQuery();
	    while(rs.next()){
	    	getcolumnnames.add(rs.getString(1));
	    }

		rs.close();
		st.close();
		con.close();
		
		return getcolumnnames;
	}
	
	public ArrayList<String> getTableSelect(String sql) throws SQLException
	{
		ArrayList<String> getSelectRows = new ArrayList<String>();
	    Connection con = new ConnectionFactory().getConnection();
	    String query = sql;
	    PreparedStatement st = con.prepareStatement(query);
	    ResultSet rs = st.executeQuery();
	    //from result set give metadata
	    ResultSetMetaData rsmd = rs.getMetaData();

	    //columns count from metadata object
	    int numOfCols = rsmd.getColumnCount();
	    while(rs.next()){
	    	String strLine = new String();
	    	for(int i = 1; i <= numOfCols; i++)
	    	{
	    		strLine = strLine +","+ rs.getString(i);
	    	}
    	    System.out.println(strLine.substring(1, strLine.length()));
    	    getSelectRows.add(strLine.substring(1, strLine.length()));
	    }

		rs.close();
		st.close();
		con.close();
		
		return getSelectRows;
	}
	public List<DashBoard> getLinesDashboard(String sql) throws SQLException
	{
		List<DashBoard> getSelectRows = new ArrayList<DashBoard>();
	    Connection con = new ConnectionFactory().getConnection();
	    String query = "select "+sql+", sum(RatingsCount) as \"Rating Total\", FORMAT(avg(AverageRatin),1) as \"Average Rating\" from BooksAnalytics.Books where "+sql+" is not null group by "+sql+" Order by sum(RatingsCount) desc limit 3";
;
	    PreparedStatement st = con.prepareStatement(query);
	    ResultSet rs = st.executeQuery();
	    //from result set give metadata
	    float ratingPerc = 0;
	    while(rs.next()){
	    	ratingPerc = ratingPerc + Float.parseFloat(rs.getString(2));
	    }
	    PreparedStatement st2 = con.prepareStatement(query);
	    ResultSet rs2 = st2.executeQuery();

	    while(rs2.next()){
			DashBoard dashboard = new DashBoard();
	    	dashboard.setItem(rs2.getString(1));
	    	dashboard.setRatingTotal(Float.parseFloat(rs2.getString(2)));
	    	dashboard.setRatingAverage(Float.parseFloat(rs2.getString(3)));
	    	dashboard.setRatingPerc(Integer.parseInt(rs2.getString(2))/ratingPerc*100);
    	    getSelectRows.add(dashboard);
	    }

		rs.close();
		st.close();
		con.close();
		
		return getSelectRows;
	}
	
	public List<DashBoard> getLinesDashboardWithPrice() throws SQLException
	{
		List<DashBoard> getSelectRows = new ArrayList<DashBoard>();
	    Connection con = new ConnectionFactory().getConnection();
	    String query = "select a.title, sum(a.RatingsCount) as \"Rating Total\", FORMAT(avg(a.AverageRatin),1) as \"Average Rating\", b.Seller, b.rating as \"Rating Seller\", b.price as \"Price Seller\" from BooksAnalytics.Books as a, BooksAnalytics.BooksSellers as b where (a.idGoogle=b.idGoogle) and (b.Price <> 'Sem preo') group by a.title, b.seller, b.rating, b.price order by sum(a.RatingsCount) desc limit 100";
	    //from result set give metadata
	    PreparedStatement st2 = con.prepareStatement(query);
	    ResultSet rs2 = st2.executeQuery();

	    while(rs2.next()){
			DashBoard dashboard = new DashBoard();
	    	dashboard.setItem(rs2.getString(1));
	    	dashboard.setRatingTotal(Float.parseFloat(rs2.getString(2)));
	    	dashboard.setRatingAverage(Float.parseFloat(rs2.getString(3)));
	    	dashboard.setSeller(rs2.getString(4));
	    	dashboard.setSellerrating(rs2.getString(5));
	    	dashboard.setSellerprice(rs2.getString(6));

    	    getSelectRows.add(dashboard);
	    }

		rs2.close();
		st2.close();
		con.close();
		
		return getSelectRows;
	}
}

