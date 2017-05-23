package com.ibm.watson.developer_cloud.visual_recognition.canaray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

/*  
 * get the json data from URL
 * */
public class GetJsonFromURL {
	
	public String GetJsonFromURL()
	{
		return "";
	}
	public String GetJsonFromURL(String url){
		
		InputStream is = null;
		String result = "";
		JSONObject jsonObject = null;
		
		//HTTP 
		try{
			HttpClient httpclient = new DefaultHttpClient(); // for port 80 requests!
			HttpPost httppost = new HttpPost(url);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			
		} catch (Exception e){
			return null;
		}
		
		//Read Response to string
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"),8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null){
				sb.append(line + '\n');
			}
			is.close();
			result = sb.toString();
		} catch(Exception e) {
			return null;
		}
		
		//Convert string to object	
		/*try{	
			jsonObject = new JSONObject(result);
			

		} catch(JSONException e){
			return null;
		}*/
		
		
//		String key = "";
//		
//		System.out.println(jsonObject.);
////				getString("Clear left maxillary sinus"));
//		System.out.println(jsonObject);
//		System.out.println(result);
		return result;
		
		
	}

}
