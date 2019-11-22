package br.com.Books.DW;

import java.text.SimpleDateFormat;
import java.util.Date;



public class LoadFacts {

	private LoadCategoryFact loadCategoryFact = new LoadCategoryFact();
	private loadBooksDimension loadBooksDimension = new loadBooksDimension();
	private loadReadingFact loadReadingFact = new loadReadingFact();
	private loadSalesFact loadSalesFact = new loadSalesFact();
	
	public void loadAllFacts()
	{
		Date data = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss");
		System.out.println("Start Load Category Dimension DW : "+ dateFormat.format(data));

	//	loadCategoryFact.loadCategories();
		
		data = new Date();
		System.out.println("Finish Load Category Dimension DW : "+ dateFormat.format(data));

		data = new Date();
		System.out.println("Start Load Books Dimension DW : "+ dateFormat.format(data));

	//	loadBooksDimension.loadBooks();
		
		data = new Date();
		System.out.println("Finish Load Books Dimension DW : "+ dateFormat.format(data));

		data = new Date();
		System.out.println("Start Load Reading Fact DW : "+ dateFormat.format(data));

	//	loadReadingFact.loadReanding();
		
		data = new Date();
		System.out.println("Finish Load Reading Fact DW : "+ dateFormat.format(data));

		data = new Date();
		System.out.println("Start Load Sales Fact DW : "+ dateFormat.format(data));

		loadSalesFact.loadSales();
		
		data = new Date();
		System.out.println("Finish Load Sales Fact DW : "+ dateFormat.format(data));

	}

}
