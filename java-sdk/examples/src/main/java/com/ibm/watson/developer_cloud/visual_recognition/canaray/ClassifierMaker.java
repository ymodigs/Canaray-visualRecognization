package com.ibm.watson.developer_cloud.visual_recognition.canaray;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifierOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifierOptions.Builder;

public class ClassifierMaker {
	private  String myFilePath = "C:/Users/specsy/Documents/GitHub/WatsonAPI/java-sdk/visual-recognition/src/test/resources/Test/TEST-zip/";
	private  String classifierID = null;
	
	
	
	// class instance required for Watson
	private VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
	private  VisualClassifier result = null;
	private Builder classBuilder;
	private ClassifierOptions createCanaryOptions;
	
	private List<File> allZipPath = new ArrayList<File>();
	private String classifierName;
	
	public ClassifierMaker()
	{
		
	}
	
	public ClassifierMaker(List<File> allZipPath, String classifierName)
	{
		service.setApiKey("caa6385d06cc80c1e01694a8a1e85342383a1cc0");
		this.classifierName = classifierName;
		this.allZipPath = allZipPath;
		
		// now call createCustomClassifier()
		createCustomClassifier();
	}
	
	private void createCustomClassifier()
	{		
		Builder classBuilder = new ClassifierOptions.Builder();
		
		for(int i=0; i<allZipPath.size(); i++){
	
			//Take the name and add as a name to the class
			classBuilder.addClass(allZipPath.get(i).getName().substring(0, allZipPath.get(i).getName().indexOf('.')) , allZipPath.get(i));	
		}
		System.out.println("and here it is " + Thread.currentThread().getName());
		ClassifierOptions createCanaryOptions = classBuilder.classifierName(classifierName).build();
		
	//   you can add negative zip by using ".negativeExamples(new File(myFilePath +"cats.zip")).build()" 
		result = service.createClassifier(createCanaryOptions).execute();
		System.out.println(result); 
	}
}