package br.com.Books.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.Books.DAO.ConnectionFactory;

public class SalesFact {
	private String ReferenceDate;
	private String BookCode;
	private int SalesCount;
	private float LeastPrice;
	private float MaximumPrice;
	private float AveragePrice;
	private int CategoryCode;
	/**
	 * @return the referenceDate
	 */
	public String getReferenceDate() {
		return ReferenceDate;
	}
	/**
	 * @param referenceDate the referenceDate to set
	 */
	public void setReferenceDate(String referenceDate) {
		ReferenceDate = referenceDate;
	}
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
	 * @return the salesCount
	 */
	public int getSalesCount() {
		return SalesCount;
	}
	/**
	 * @param salesCount the salesCount to set
	 */
	public void setSalesCount(String salesCount) {
		SalesCount = Integer.parseInt(salesCount);
	}
	/**
	 * @return the leastPrice
	 */
	public float getLeastPrice() {
		return LeastPrice;
	}
	/**
	 * @param leastPrice the leastPrice to set
	 */
	public void setLeastPrice(String leastPrice) {
		LeastPrice = Float.parseFloat(leastPrice.replace(",", "."));
	}
	/**
	 * @return the maximumPrice
	 */
	public float getMaximumPrice() {
		return MaximumPrice;
	}
	/**
	 * @param maximumPrice the maximumPrice to set
	 */
	public void setMaximumPrice(String maximumPrice) {
		MaximumPrice = Float.parseFloat(maximumPrice.replace(",", "."));
	}
	/**
	 * @return the averagePrice
	 */
	public float getAveragePrice() {
		return AveragePrice;
	}
	/**
	 * @param averagePrice the averagePrice to set
	 */
	public void setAveragePrice(String averagePrice) {
		AveragePrice = Float.parseFloat(averagePrice.replace(",", "."));
	}
	/**
	 * @return the categoryCode
	 */
	public int getCategoryCode() {
		return CategoryCode;
	}
	/**
	 * @param categoryCode the categoryCode to set
	 * @throws SQLException 
	 */
	public void setCategoryCode(String categoryCode) throws SQLException {
	    Connection con = new ConnectionFactory().getConnection();
	    String query = "select idCategoryDimension, Category from BooksAnalytics.CategoryDimension where Category = '"+categoryCode+"';";
	    PreparedStatement st = con.prepareStatement(query);
	    ResultSet rs = st.executeQuery();
	    while(rs.next()){
	    	CategoryCode = Integer.parseInt(rs.getString(1)) ;
	    }
		rs.close();
		st.close();
		con.close();
	}
	
	
}
