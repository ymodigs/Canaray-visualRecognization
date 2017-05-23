package com.ibm.watson.developer_cloud.visual_recognition.v3;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ibm.watson.developer_cloud.visual_recognition.canaray.ClassifierMaker;
import com.ibm.watson.developer_cloud.visual_recognition.canaray.GetJsonFromURL;
import com.ibm.watson.developer_cloud.visual_recognition.canaray.ParseJson;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifierOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifierOptions.Builder;


class DoZipAndUpload implements ZipProducedEvent
{
	private ZipMaker zipmaker;
	private File rootFolderPath;
	
	DoZipAndUpload(File rootFolderPath)
	{
		this.rootFolderPath = rootFolderPath;
		zipmaker = new ZipMaker(rootFolderPath.getPath(), this);
	}
		
   @Override
	public void zipProduced() {
		// TODO Auto-generated method stub
		List<File> allZipPath = new ArrayList<File>();
		
		ArrayList<File> allFolderOrFiles = new ArrayList<File>(Arrays.asList(rootFolderPath.listFiles()));
		for(int i=0; i<allFolderOrFiles.size();i++)
		{
			
			if(allFolderOrFiles.get(i).getPath().endsWith(".zip"))
			{  
				System.out.println("Zip files are " + allFolderOrFiles.get(i));
				allZipPath.add(new File(allFolderOrFiles.get(i).toString()));
			}
		}
		
		//creating classifier and uploading all files
		new ClassifierMaker(allZipPath, "Canaray Classifer");	
	}
}

public class CustomClassifier {
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		//System.out.print("Enter the path:");
		System.out.print("Enter the json URL:");
		String jsonURL = br.readLine();
		
		String gotJsonData = new GetJsonFromURL().GetJsonFromURL(jsonURL);
		
		new FileDownloader(new ParseJson().doJsonParsing(gotJsonData),"C:\\Users\\specsy\\Desktop\\demo\\");
		
		System.out.println("I am done with downloading");

		File rootFolderPath = new File("C:\\Users\\specsy\\Desktop\\demo\\");
		
		new DoZipAndUpload(rootFolderPath);	
	}	
}
