package br.com.Books.BI;

/*importando pacotes para leitura de arquivos*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;




/*importando pacotes do Weka*/
import weka.clusterers.*;
import weka.core.*;
import weka.associations.*;

/*The most common components you might want to use are
Instances - your data
Filter - for preprocessing the data
Classifier/Clusterer - built on the processed data
Evaluating - how good is the classifier/clusterer?
Attribute selection - removing irrelevant attributes from your data*/

public class DataMining {

	public static void main(String[] args) throws Exception {
	 
		/*start Cluster  EM*/
		EM();
		
		/*start Associate Apriori*/
		Apriori();
		
	}

	public static void Apriori() throws Exception
	{
		// TODO Auto-generated method stub
		 PrintWriter writer = new PrintWriter("/Users/plpereir/OneDrive/MackBook Pro - plpereir/Documents/workspace/BooksAnalytics/DataMining/associationApriori_Books.txt", "UTF-8");
		System.out.println("Job Start Apriori");
		/*Lendo o arquivo arff*/
		 BufferedReader reader = new BufferedReader(
		 new FileReader("/Users/plpereir/OneDrive/MackBook Pro - plpereir/Documents/workspace/BooksAnalytics/DataMining/books.arff"));
		 
		 /*Criando uma inst‰ncia do Weka*/
		 Instances data = new Instances(reader);
		 reader.close();
		 
		 /*weka.clusterers.Apriori -I 100 -N -1 -M 1.0E-6 -S 100*/
		 Apriori apriori = new Apriori();
		 
		 String[] options = new String[16];
		 options[0] = "-N";
		 options[1] = "10";
		 options[2] = "-T";
		 options[3] = "-0";
		 options[4] = "-C";
		 options[5] = "0.9";
		 options[6] = "-D";
		 options[7] = "0.05";
		 options[8] = "-U";
		 options[9] = "1.0";
		 options[10] = "-M";
		 options[11] = "0.1";
		 options[12] = "-S";
		 options[13] = "-1.0";
		 options[14] = "-c";
		 options[15] = "-1";		 
		 
		 apriori.setOptions(options);
		 apriori.buildAssociations(data);
		 System.out.println("getRevision: "+apriori.getRevision());
		 writer.println("getRevision: "+apriori.getRevision());

		 System.out.println("globalInfo: "+apriori.globalInfo());
		 writer.println("globalInfo: "+apriori.globalInfo());

		 System.out.println("getCapabilities: "+apriori.getCapabilities());
		 writer.println("getCapabilities: "+apriori.getCapabilities());

		 System.out.println("getOptions: "+apriori.getOptions());
		 writer.println("getOptions: "+apriori.getOptions());

		 System.out.println("getClusterModelsNumericAtts"+apriori.outputItemSetsTipText());
		 writer.println("getClusterModelsNumericAtts"+apriori.outputItemSetsTipText());

		 System.out.println("toString: " + apriori.toString() );
		 writer.println("toString: " + apriori.toString());

		 
			System.out.println("Finish Job Apriori, Completed proccess, check de files.");
		writer.close();
	}
	
	public static void EM() throws Exception
	{
		// TODO Auto-generated method stub
		 PrintWriter writer = new PrintWriter("/Users/plpereir/OneDrive/MackBook Pro - plpereir/Documents/workspace/BooksAnalytics/DataMining/clusterEM_Books.txt", "UTF-8");

		System.out.println("Job Start EM");
		/*Lendo o arquivo arff*/
		 BufferedReader reader = new BufferedReader(
		 new FileReader("/Users/plpereir/OneDrive/MackBook Pro - plpereir/Documents/workspace/BooksAnalytics/DataMining/books.arff"));
		 
		 /*Criando uma inst‰ncia do Weka*/
		 Instances data = new Instances(reader);
		 reader.close();
		 
		 /*weka.clusterers.EM -I 100 -N -1 -M 1.0E-6 -S 100*/
		 EM em = new EM();
		 
		 String[] options = new String[8];
		 options[0] = "-I";
		 options[1] = "100";
		 options[2] = "-N";
		 options[3] = "-1";
		 options[4] = "-M";
		 options[5] = "1.0E-6";
		 options[6] = "-S";
		 options[7] = "100";

		 em.setOptions(options);
		 em.buildClusterer(data);

		 System.out.println("debugTipText: "+em.debugTipText());
		 writer.println("debugTipText: "+em.debugTipText());

		 System.out.println("getRevision: "+em.getRevision());
		 writer.println("getRevision: "+em.getRevision());

		 System.out.println("globalInfo: "+em.globalInfo());
		 writer.println("globalInfo: "+em.globalInfo());

		 System.out.println("getCapabilities: "+em.getCapabilities());
		 writer.println("getCapabilities: "+em.getCapabilities());

		 System.out.println("getOptions: "+em.getOptions());
		 writer.println("getOptions: "+em.getOptions());

		 System.out.println("getClusterModelsNumericAtts"+em.getClusterModelsNumericAtts());
		 writer.println("getClusterModelsNumericAtts"+em.getClusterModelsNumericAtts());

		 System.out.println("toString: " + em.toString() );
		 writer.println("toString: " + em.toString());

		 
			System.out.println("Finish Job EM, Starting now Job Apriori.");
		writer.close();
	}

}
