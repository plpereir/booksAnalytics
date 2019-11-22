package br.com.Books.DW;

import java.sql.SQLException;

import br.com.Books.DAO.ConnectionFactory;

public class loadBooksDimension {
	protected ConnectionFactory cf = new ConnectionFactory();
	
	public void loadBooks()
	{
	  try {
		cf.loadBookDimension();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println(e.toString());
	}
	}


}
