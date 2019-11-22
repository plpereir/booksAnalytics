package br.com.Books.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.Books.DAO.ConnectionFactory;

public class ReadingFact {
	private int RankingNumber;
	private int RankingVolume;
	private String ReferenceDate;
	private String BookCode;
	private int CategoryCode;
	private float AverageRating;
	private float CountRating;
	/**
	 * @return the rankingNumber
	 */
	public int getRankingNumber() {
		return RankingNumber;
	}
	/**
	 * @param rankingNumber the rankingNumber to set
	 */
	public void setRankingNumber(String rankingNumber) {
		
		RankingNumber = Integer.parseInt(rankingNumber);
	}
	/**
	 * @return the rankingVolume
	 */
	public int getRankingVolume() {
		return RankingVolume;
	}
	/**
	 * @param rankingVolume the rankingVolume to set
	 */
	public void setRankingVolume(String rankingVolume) {
		RankingVolume =Integer.parseInt( rankingVolume);
	}
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
	/**
	 * @return the averageRating
	 */
	public float getAverageRating() {
		return AverageRating;
	}
	/**
	 * @param averageRating the averageRating to set
	 */
	public void setAverageRating(String averageRating) {
		AverageRating = Float.parseFloat(averageRating);
	}
	/**
	 * @return the countRating
	 */
	public float getCountRating() {
		return CountRating;
	}
	/**
	 * @param countRating the countRating to set
	 */
	public void setCountRating(String countRating) {
		CountRating = Float.parseFloat(countRating);
	}

	
	
}
