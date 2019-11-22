package br.com.Books.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.Books.DAO.ConnectionFactory;

public class BooksDimensionFrom {
	private String BookCode;
	private String BookTitle;
	private String BookDescription;
	private int CountPage;
	private int PrintType;
	private String Authors;
	private int SalesAvailable;
	private int PublicDomain;
	private int PreviewAvaliable;
	private int BookCategory;
	/**
	 * @return the bookCode
	 */
	public String getBookCode() {
		return BookCode;
	}
	/**
	 * @param bookCode the bookCode to set
	 */
	public void setBookCode(String bookCode) {
		BookCode = bookCode;
	}
	/**
	 * @return the bookTitle
	 */
	public String getBookTitle() {
		return BookTitle;
	}
	/**
	 * @param bookTitle the bookTitle to set
	 */
	public void setBookTitle(String bookTitle) {
		BookTitle = bookTitle;
	}
	/**
	 * @return the bookDescription
	 */
	public String getBookDescription() {
		return BookDescription;
	}
	/**
	 * @param bookDescription the bookDescription to set
	 */
	public void setBookDescription(String bookDescription) {
		BookDescription = bookDescription;
	}
	/**
	 * @return the countPage
	 */
	public int getCountPage() {
		return CountPage;
	}
	/**
	 * @param countPage the countPage to set
	 */
	public void setCountPage(String countPage) {
		try
		{
			CountPage = Integer.parseInt(countPage) ;
		}catch(Exception ex)
		{
			CountPage = 0;
			System.out.println("BookCode"+ this.BookCode+" - "+ex.toString());
		}
	}
	/**
	 * @return the printType
	 * (0-paper 1-e-book 2-both)
	 */
	public int getPrintType() {
		return PrintType;
	}
	/**
	 * @param printType the printType to set
	 * (0-paper 1-e-book 2-both)
	 */
	public void setPrintType(String printType) {
		if (printType.contains("false"))
			{
				PrintType = 0;
			}
		if (printType.contains("true"))
			{
				PrintType = 1;
			}
	}
	/**
	 * @return the authors
	 */
	public String getAuthors() {
		return Authors;
	}
	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(String authors) {
		Authors = authors;
	}
	/**
	 * @return the salesAvailable
	 */
	public int isSalesAvailable() {
		return SalesAvailable;
	}
	/**
	 * @param salesAvailable the salesAvailable to set
	 */
	public void setSalesAvailable(String salesAvailable) {
		if (salesAvailable.contains("NOT_FOR_SALE"))
		{
			SalesAvailable = 0;
		}
		if (salesAvailable.contains("FOR_SALE"))
		{
			SalesAvailable = 1;
		}
	}
	/**
	 * @return the publicDomain
	 */
	public int isPublicDomain() {
		return PublicDomain;
	}
	/**
	 * @param publicDomain the publicDomain to set
	 */
	public void setPublicDomain(String publicDomain) {
		
		if (publicDomain.contains("false"))
		{
			PublicDomain = 0;
		}
		if (publicDomain.contains("false"))
		{
			PublicDomain = 1;
		}
	}
	/**
	 * @return the previewAvaliable
	 */
	public int isPreviewAvaliable() {
		return PreviewAvaliable;
	}
	/**
	 * @param previewAvaliable the previewAvaliable to set
	 */
	public void setPreviewAvaliable(String previewAvaliable) {
		if (previewAvaliable.contains("NONE"))
		{
			PreviewAvaliable = 0;
		}
		if (previewAvaliable.contains("SAMPLE"))
		{
			PreviewAvaliable = 1;
		}

	}
	/**
	 * @return the bookCategory
	 */
	public int getBookCategory() {
		return BookCategory;
	}
	/**
	 * @param bookCategory the bookCategory to set
	 * @throws SQLException 
	 */
	public void setBookCategory(String bookCategory) throws SQLException {
		
	    Connection con = new ConnectionFactory().getConnection();
	    String query = "select idCategoryDimension, Category from BooksAnalytics.CategoryDimension where Category = '"+bookCategory+"';";
	    PreparedStatement st = con.prepareStatement(query);
	    ResultSet rs = st.executeQuery();
	    while(rs.next()){
	    	BookCategory = Integer.parseInt(rs.getString(1)) ;
	    }
		rs.close();
		st.close();
		con.close();

	}
	
	
}
