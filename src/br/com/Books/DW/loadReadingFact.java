package br.com.Books.DW;

import java.sql.SQLException;

import br.com.Books.DAO.ConnectionFactory;

public class loadReadingFact {
	protected ConnectionFactory cf = new ConnectionFactory();
	public void loadReanding()
	{
	  try {
		cf.loadSalesFact();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println(e.toString());
	}
	}


}
