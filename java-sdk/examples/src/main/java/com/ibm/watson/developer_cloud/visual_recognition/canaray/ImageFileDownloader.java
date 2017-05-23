package com.ibm.watson.developer_cloud.visual_recognition.canaray;

import org.json.JSONException;
import org.json.JSONObject;

/*
 * Convert json data in the form of String to JSON Object
 * 
 */

public class ImageFileDownloader {

	JSONObject jsonObject = null;
	public JSONObject ImageFileDownloader(String url){
		
		try{	
			jsonObject = new JSONObject(url);
			//System.out.println(jsonObject.;

		} catch(JSONException e){
			return null;
		}
		
		System.out.println(jsonObject.getLong(arg0));
		
		return jsonObject;
	}
}
