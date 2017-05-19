package com.ibm.watson.developer_cloud.visual_recognition.canaray;

import java.util.List;

public class DataTagsImageURLs {
	private String  tagName;
	private List<String> imageURLs;
	
	
	public DataTagsImageURLs() {
	}


	public DataTagsImageURLs(String tagName, List<String> imageURLs) {
		super();
		this.tagName = tagName;
		this.imageURLs = imageURLs;
	}


	public String getTagName() {
		return tagName;
	}


	public void setTagName(String tagName) {
		this.tagName = tagName;
	}


	public List<String> getImageURLs() {
		return imageURLs;
	}


	public void setImageURLs(List<String> imageURLs) {
		this.imageURLs = imageURLs;
	}


	@Override
	public String toString() {
		return "DataTagsImageURLs [tagName=" + tagName + ", imageURLs=" + imageURLs + "]";
	}
	
	
}
