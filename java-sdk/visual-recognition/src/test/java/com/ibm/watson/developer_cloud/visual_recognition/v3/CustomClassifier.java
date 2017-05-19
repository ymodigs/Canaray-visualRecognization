package com.ibm.watson.developer_cloud.visual_recognition.v3;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ibm.watson.developer_cloud.visual_recognition.canaray.ClassifierMaker;
import com.ibm.watson.developer_cloud.visual_recognition.canaray.ZipMaker;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifierOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifierOptions.Builder;

public class CustomClassifier {

	public static boolean isZipMade = false;  

	
	
	public static void main(String args[]) throws Exception
	{
		
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter the path:");
		String path = br.readLine();
		File f = new File(path);
		
		ArrayList<File> allFolderOrFiles = new ArrayList<File>(Arrays.asList(f.listFiles()));
		
		
		// zip maker thead
		 Thread thread_zipmaker = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				// making zip files
				 for (  File file : allFolderOrFiles) {
					 	
				    	if(file.isDirectory())
				    	{
				    		new ZipMaker(file.getPath(), file.getParent() + "//" +  file.getName() +  ".zip");
					}
				}
			}
		 });
		 
		 thread_zipmaker.setName("zip maker thread");
		 thread_zipmaker.start();
		 
		 //Thread.sleep(5000);
		 
		 // custom classifer make thread
		 Thread thread_classifier = new Thread(new Runnable() {

				@Override
				public void run() {
					 // filters zip files and add it to allZipPath 
					while(thread_zipmaker.getState()==Thread.State.WAITING){}
					List<File> allZipPath = new ArrayList<File>();
					for(int i=0; i<allFolderOrFiles.size();i++)
					{
						
						if(allFolderOrFiles.get(i).getPath().endsWith(".zip"))
						{  
							
							System.out.println("Files are " + allFolderOrFiles.get(i));
							
							allZipPath.add(new File(allFolderOrFiles.get(i).toString()));
						
						}
				
					}
					
					ClassifierMaker c1 = new ClassifierMaker(allZipPath, "Canaray Classifer");
					
					
					System.out.println("Total zip filesmad are: " + allZipPath.size());
				}
			 });
			 
		 thread_classifier.setName("classifier thread");
		 thread_classifier.start();
		 
		 
		
	}	
}
