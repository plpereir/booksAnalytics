package br.com.Books.ETL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import br.com.Books.Controller.PublishNews;

public class ExtractPublishNewsInformations {
	private static String pathFiles = "Scripts/Downloads/PublishNews/";
	
	public List<PublishNews> readHTMLPublishNews(Integer categoryID, Integer ano, Integer mes){
		List<PublishNews>publishNewsList = new ArrayList<PublishNews>();
		
		
		/** set File with informations about files HTML storage after script execution */
		File htmlFile = new File(pathFiles+"PublishNewsRanking"+categoryID.toString()+ano.toString()+mes.toString()+".html");
		/** check file and directory exists physical, after start read file */
		if(htmlFile.exists() && !htmlFile.isDirectory()) { 
		    /** read file and set data in object Books from file */
			// Open the file
			FileInputStream fstream;
			try {
				fstream = new FileInputStream(htmlFile);
				InputStreamReader isr = new InputStreamReader(fstream, "UTF8");
				BufferedReader br = new BufferedReader(isr);
				float price = 0;
				Integer Pages, RankingNumber, RankingVolume = 0;
				String strLine, author,category,description,image,ISBN,name,publisher,translate = "";
				PublishNews publishNews = new PublishNews();
				
				//Read File Line By Line
				try {
					while ((strLine = br.readLine()) != null)   
					{
						if (strLine.contains("<div class=\"pn-ranking-livros-posicao-numero\">"))
						{
							publishNews = new PublishNews();
							RankingNumber = Integer.parseInt(strLine.substring(strLine.indexOf("numero\">")+8,strLine.length()-6));
							publishNews.setRankingNumber(RankingNumber);
							System.out.println("RankingNumber: "+RankingNumber);
						}

						if (strLine.contains("div class=\"pn-ranking-livros-posicao-volume\">"))
						{
							RankingVolume = Integer.parseInt(strLine.substring(strLine.indexOf("volume\">")+8,strLine.length()-6).replace(".", ""));
							publishNews.setRankingVolume(RankingVolume);
							System.out.println("RankingVolume: "+RankingVolume);
						}

						if (strLine.contains("<div class=\"pn-ranking-livro-capa\">"))
						{
							image = strLine.substring(strLine.indexOf("<img src=")+10,strLine.length()-27);
							publishNews.setImage(image);
							System.out.println("image: "+image);
						}

						if (strLine.contains("<div class=\"pn-ranking-livro-nome\">"))
						{
							try
							{
								name = strLine.substring(strLine.indexOf("nome\">")+6,strLine.indexOf("<img"));
							}catch (Exception ex)
							{
								name = strLine.substring(strLine.indexOf("nome\">")+6,strLine.length()-6);
							}
							publishNews.setName(name);
							System.out.println("nome: "+name);
							
							if (mes<10)
							{
								publishNews.setReferenceDate((Integer.toString(ano)+"0"+Integer.toString(mes)).toString());								
							}else
							{
								publishNews.setReferenceDate((Integer.toString(ano)+Integer.toString(mes)).toString());								
							}

							System.out.println("Reference Date: "+publishNews.getReferenceDate());

						}
						
						if (strLine.contains("div class=\"pn-ranking-livro-autor\">"))
						{
							author = strLine.substring(strLine.indexOf("autor\">")+7,strLine.length()-6);
							publishNews.setAuthor(author);
							System.out.println("author: "+author);
						}
						
						if (strLine.contains("div class=\"pn-ranking-livro-editora\">"))
						{
							publisher = strLine.substring(strLine.indexOf("editora\">")+9,strLine.length()-6);
							publishNews.setPublisher(publisher);
							System.out.println("publisher: "+publisher);
						}
						
						if (strLine.contains("div class=\"pn-ranking-livro-resumo\">"))
						{
							description = strLine.substring(strLine.indexOf("resumo\"><p>")+11,strLine.length()-10);
							publishNews.setDescription(description);
							System.out.println("description: "+description);
						}
						
						if (strLine.contains("div class=\"pn-ranking-livro-isbn\">"))
						{
							ISBN = strLine.substring(strLine.indexOf("ISBN <strong>")+13,strLine.length()-15).replaceAll("-","");
							publishNews.setISBN(ISBN);
							System.out.println("ISBN: "+ISBN);
						}
						
						if (strLine.contains("div class=\"pn-ranking-livro-categoria\">"))
						{
							category = strLine.substring(strLine.indexOf("Categoria <strong>")+18,strLine.length()-15);
							publishNews.setCategory(category);
							System.out.println("category: "+category);
						}
						
						if (strLine.contains("div class=\"pn-ranking-livro-paginas\">"))
						{
							try{
								Pages = Integer.parseInt(strLine.substring(strLine.indexOf("Páginas <strong>")+17,strLine.length()-15));
								publishNews.setPages(Pages);
							}catch (Exception ex)
							{
								publishNews.setPages(0);
							}
							System.out.println("Pages: "+publishNews.getPages());
						}
						
						if (strLine.contains("div class=\"pn-ranking-livro-preco\">"))
						{
							price = Float.parseFloat(strLine.substring(strLine.indexOf("Preço <strong>R$ ")+18,strLine.length()-15).replace(",", ""));
							publishNews.setPrice(price);
							System.out.println("price: "+price);
						}
						if (strLine.contains("div class=\"pn-ranking-livro-tradutor\">"))
						{
							translate = strLine.substring(strLine.indexOf("Tradução <strong>")+17,strLine.length()-15);
							publishNews.setTranslate(translate);
							System.out.println("translate: "+translate);
						}
						if ((publishNews.getName()!=null) && (strLine.contains("<div class=\"pn-ranking-livros-posicao clearfix\">")))
						{
							publishNewsList.add(publishNews);
						}
						}
					}catch(Exception e)
					{
						System.out.println("Not found PublishNews informations: "+e.toString());
					}				
					//Close the input stream
					fstream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return publishNewsList;
	}
}
