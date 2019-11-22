package br.com.Books.BI;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.Books.Controller.DashBoard;
import br.com.Books.DAO.ConnectionFactory;

public class dashboardGenerate {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		System.out.println("Start Job");
		ConnectionFactory cn = new ConnectionFactory();
		 PrintWriter writer = new PrintWriter("Dashboard/Analytics.html", "UTF-8");
		 writer.println(loadHeader());
		 
			List<DashBoard> dashboard = new ArrayList<DashBoard>();
		    try {
				dashboard = cn.getLinesDashboard("Authors");
				writer.println("<div class='panel panel-info'>"+
                                    "<div class='panel-heading'>"+
                                     	"<h3 class='panel-title'>Authors:</h3>"+
                                        "<span class='pull-right clickable'><i class='glyphicon glyphicon-chevron-up'></i></span>"+
                                    "</div>");
				writer.println("<div class='panel-body'>");
				for (DashBoard element : dashboard)
				{
					writer.println("<span class='text text-info'>");
					writer.println("<strong>"+element.getItem()+"</strong>: Rating Total: "+element.getRatingTotal()+" Average Rating: "+element.getRatingAverage());
					writer.println("</span>");
					writer.println("<div class='progress'>");
					writer.println("<div class='progress-bar progress-bar-success' role='progressbar' aria-valuenow='"+element.getRatingPerc()+"'");
					writer.println("aria-valuemin='0' aria-valuemax='100' style='width:"+element.getRatingPerc()+"%'>");
					writer.println(element.getRatingPerc());
					writer.println("</div></div>");
				}
				writer.println("</div></div>");
				 
				dashboard = new ArrayList<DashBoard>();

					dashboard = cn.getLinesDashboard("Categories");
					writer.println("<div class='panel panel-info'>"+
	                                    "<div class='panel-heading'>"+
	                                     	"<h3 class='panel-title'>Categories:</h3>"+
	                                        "<span class='pull-right clickable'><i class='glyphicon glyphicon-chevron-up'></i></span>"+
	                                    "</div>");
					writer.println("<div class='panel-body'>");
					for (DashBoard element : dashboard)
					{
						writer.println("<span class='text text-info'>");
						writer.println("<strong>"+element.getItem()+"</strong>: Rating Total: "+element.getRatingTotal()+" Average Rating: "+element.getRatingAverage());
						writer.println("</span>");
						writer.println("<div class='progress'>");
						writer.println("<div class='progress-bar progress-bar-success' role='progressbar' aria-valuenow='"+element.getRatingPerc()+"'");
						writer.println("aria-valuemin='0' aria-valuemax='100' style='width:"+element.getRatingPerc()+"%'>");
						writer.println(element.getRatingPerc());
						writer.println("</div></div>");
					}
					writer.println("</div></div>");

					dashboard = new ArrayList<DashBoard>();

					dashboard = cn.getLinesDashboard("Publisher");
					writer.println("<div class='panel panel-info'>"+
	                                    "<div class='panel-heading'>"+
	                                     	"<h3 class='panel-title'>Publisher:</h3>"+
	                                        "<span class='pull-right clickable'><i class='glyphicon glyphicon-chevron-up'></i></span>"+
	                                    "</div>");
					writer.println("<div class='panel-body'>");
					for (DashBoard element : dashboard)
					{
						writer.println("<span class='text text-info'>");
						writer.println("<strong>"+element.getItem()+"</strong>: Rating Total: "+element.getRatingTotal()+" Average Rating: "+element.getRatingAverage());
						writer.println("</span>");
						writer.println("<div class='progress'>");
						writer.println("<div class='progress-bar progress-bar-success' role='progressbar' aria-valuenow='"+element.getRatingPerc()+"'");
						writer.println("aria-valuemin='0' aria-valuemax='100' style='width:"+element.getRatingPerc()+"%'>");
						writer.println(element.getRatingPerc());
						writer.println("</div></div>");
					}
					writer.println("</div></div>");
					dashboard = new ArrayList<DashBoard>();

					dashboard = cn.getLinesDashboard("Title");
					writer.println("<div class='panel panel-info'>"+
	                                    "<div class='panel-heading'>"+
	                                     	"<h3 class='panel-title'>Title:</h3>"+
	                                        "<span class='pull-right clickable'><i class='glyphicon glyphicon-chevron-up'></i></span>"+
	                                    "</div>");
					writer.println("<div class='panel-body'>");
					for (DashBoard element : dashboard)
					{
						writer.println("<span class='text text-info'>");
						writer.println("<strong>"+element.getItem()+"</strong>: Rating Total: "+element.getRatingTotal()+" Average Rating: "+element.getRatingAverage());
						writer.println("</span>");
						writer.println("<div class='progress'>");
						writer.println("<div class='progress-bar progress-bar-success' role='progressbar' aria-valuenow='"+element.getRatingPerc()+"'");
						writer.println("aria-valuemin='0' aria-valuemax='100' style='width:"+element.getRatingPerc()+"%'>");
						writer.println(element.getRatingPerc());
						writer.println("</div></div>");
					}
					writer.println("</div></div>");
				writer.println(loadHeaderTable());
				dashboard = new ArrayList<DashBoard>();

				dashboard = cn.getLinesDashboardWithPrice();
				

				for (DashBoard element : dashboard)
				{
					writer.println("<tr>");
					writer.println("<td>"+element.getItem()+"</td>");
					writer.println("<td>"+element.getRatingTotal()+"</td>");
					writer.println("<td>"+element.getRatingAverage()+"</td>");
					writer.println("<td>"+element.getSeller()+"</td>");
					writer.println("<td>"+element.getSellerrating()+"</td>");
					writer.println("<td>"+element.getSellerprice()+"</td>");
					writer.println("</tr>");
				}
				

				writer.print(loadFooterTable());
				writer.println(loadFooter());
				writer.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Finish Job");

	}
	
	public static String loadHeader()
	{
		String header = 
"<!DOCTYPE html>"+
"<html lang='en'>"+
"<head>"+
 "   <meta charset='utf-8'>"+
 "   <meta name='robots' content='noindex, nofollow'>"+

 "   <title>Books Analytics - DashBoards</title>"+
 "       <meta name='viewport' content='width=device-width, initial-scale=1'>"+
 "   <link href='bootstrap.min.css' rel='stylesheet' id='bootstrap-css'>"+
 "   <style type='text/css'>"+
 "   .row{"+
 "   margin-top:40px;"+
 "   padding: 0 10px;"+
"}"+

".clickable{"+
"    cursor: pointer;"+   
"}"+

".panel-heading span {"+
"	margin-top: -20px;"+
"	font-size: 15px;"+
"}    </style>"+
"    <script src='jquery-1.11.1.min.js'></script>"+
"    <script src='bootstrap.min.js'></script>"+
"    <script type='text/javascript'>"+
"        window.alert = function(){};"+
"        var defaultCSS = document.getElementById('bootstrap-css');"+
"        function changeCSS(css){"+
"            if(css) $('head > link').filter(':first').replaceWith('<link rel='stylesheet' href=''+ css +'' type='text/css' />'); "+
"            else $('head > link').filter(':first').replaceWith(defaultCSS); "+
"        }"+
"        $( document ).ready(function() {"+
"          var iframe_height = parseInt($('html').height());"+ 
"          window.parent.postMessage( iframe_height, 'https://bootsnipp.com');"+
"        });"+
"    </script>"+
"</head>"+
"<body>"+
"    <div class='container'>"+
"        <h3>Book Analytics</h3>"+
"        <hr>"+
"	<div class='row'>"+
"            <div class='col-md-6'>";
		return header;
	}
	
	public static String loadFooter()
	{
		String footer = 
	"</div>"+
    "</div>"+   
	"<hr>"+
"</div>	<script type='text/javascript'>"+
"	$(document).on('click', '.panel-heading span.clickable', function(e){"+
 "   var $this = $(this);"+
	"if(!$this.hasClass('panel-collapsed')) {"+
	"	$this.parents('.panel').find('.panel-body').slideUp();"+
	"	$this.addClass('panel-collapsed');"+
	"	$this.find('i').removeClass('glyphicon-chevron-up').addClass('glyphicon-chevron-down');"+
	"} else {"+
	"	$this.parents('.panel').find('.panel-body').slideDown();"+
	"	$this.removeClass('panel-collapsed');"+
	"	$this.find('i').removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up');"+
	"}"+
"})	</script>"+
"</body>"+
"</html>";
		return footer;
	}
	
	public static String loadHeaderTable()
	{
		String tmp =  
				"<hr>"+
   " <h2>Sellers Informations by Title</h2>"+
   " <p>The list with top 100 titles sort by total of rating count</p> "+           
   " <table class='table table-striped'>"+
   "   <thead>"+
    "    <tr>"+
     "     <th>Title</th>"+
      "    <th>Rating Total</th>"+
      "    <th>Average Rating</th>"+
      "    <th>Seller</th>"+
      "    <th>Seller's Rating</th>"+
      "    <th>Seller's Price</th>"+
      "  </tr>"+
     " </thead>"+
     " <tbody>";
		return tmp;
	}
	
	public static String loadFooterTable()
	{
		String tmp = "</tbody></table>";
		return tmp;
	}
}
