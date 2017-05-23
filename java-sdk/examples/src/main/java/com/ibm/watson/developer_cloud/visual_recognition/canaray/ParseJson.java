package com.ibm.watson.developer_cloud.visual_recognition.canaray;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
 * Convert json data in the form of String to JSON Object
 * 
 */

public class ParseJson {

	JSONObject jsonObject = null;
	public List<DataTagsImageURLs> doJsonParsing(String url){
		
		try{	
			jsonObject = new JSONObject(url);
			//System.out.println(jsonObject.;

		} catch(JSONException e){
			return null;
		}
		
		List<DataTagsImageURLs> listofUrls = new ArrayList<DataTagsImageURLs>();
		
		Iterator<String> keys = jsonObject.keys();
		while( keys.hasNext() ) {
		    String key = (String) keys.next();
		    DataTagsImageURLs obj1 = new DataTagsImageURLs();
		    obj1.setTagName(key);
		    
		    JSONArray jsonArray = (JSONArray) jsonObject.get(key);
		    

			List<String> stringUrls = new ArrayList<String>();
			
		    for(int i = 0; i < jsonArray.length(); i++)
		    {
				//JSONObject objectInArray = jsonArray.getJSONObject(i);
				
				//System.out.println("each url is" + jsonArray.get(i));
			    stringUrls.add(jsonArray.get(i).toString());
		    }
		    obj1.setImageURLs(stringUrls);
		    listofUrls.add(obj1);
		    
		}
		
		return listofUrls;
	}
}
