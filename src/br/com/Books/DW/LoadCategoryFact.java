package br.com.Books.DW;

import java.sql.SQLException;

import br.com.Books.DAO.ConnectionFactory;

public class LoadCategoryFact {

	protected ConnectionFactory cf = new ConnectionFactory();
	
	public void loadCategories()
	{
	  try {
		cf.loadCategoryItems(cf.getColumnListValues("PublishNews","Category"));
	//	cf.loadCategoryItems(cf.getColumnListValues("Books","Categories"));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println(e.toString());
	}
	}
	
}
