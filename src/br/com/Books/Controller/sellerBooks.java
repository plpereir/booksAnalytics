/**
 * 
 */
package br.com.Books.Controller;

/**
 * <b>Author</b> Pedro Luiz da Silva Pereira<Br>
 * <b>Date:</b> 28/02/2019 <Br>
 * <b>About:</b> This class has the responsibility to perform seller books informations<Br>
 * informations: idGoogle, seller, price and rating.*/
public class sellerBooks {

	/*Attributes of Class*/
	/** this is key if id of class Books */
	private String idGoogle;
	/** reference of seller/vendor book */
	private String seller;
	/** reference of price from seller/vendor book */
	private String price;
	/** reference of evaluate/rating to seller/vendor book */
	private String rating;
	
	/*Methods of Class start by getters and setters*/
	/**
	 * @return the idGoogle
	 */
	public String getIdGoogle() {
		return idGoogle;
	}
	/**
	 * @param idGoogle the idGoogle to set
	 */
	public void setIdGoogle(String idGoogle) {
		this.idGoogle = idGoogle;
	}
	/**
	 * @return the seller
	 */
	public String getSeller() {
		return seller;
	}
	/**
	 * @param seller the seller to set
	 */
	public void setSeller(String seller) {
		this.seller = seller;
	}
	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}
	/**
	 * @param rating the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}
	

}
