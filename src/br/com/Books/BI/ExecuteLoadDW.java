package br.com.Books.BI;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.Books.DW.LoadFacts;

public class ExecuteLoadDW {

	public static LoadFacts loadFacts = new LoadFacts();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date data = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss");
		System.out.println("Start Load DW : "+ dateFormat.format(data));
		
			loadFacts.loadAllFacts();
			
		data = new Date();
		System.out.println("Finish Load DW : "+ dateFormat.format(data));
	}

}
