package com.ibm.watson.developer_cloud.visual_recognition.v3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.List;

import com.ibm.watson.developer_cloud.visual_recognition.canaray.DataTagsImageURLs;

public class FileDownloader {
	
	public FileDownloader(List<DataTagsImageURLs> dataTagsImageURLs, String destinationPath)
	{
		for(int i=0;i<dataTagsImageURLs.size();i++)
		{
			File folderName = new File(destinationPath + dataTagsImageURLs.get(i).getTagName());
			folderName.mkdirs();
			
			for(int j=0;j<dataTagsImageURLs.get(i).getImageURLs().size();j++)
			{
				String imageName = dataTagsImageURLs.get(i).getImageURLs().get(j).substring(dataTagsImageURLs.get(i).getImageURLs().get(j).lastIndexOf("/") + 1);
				downloadFileFromURL(dataTagsImageURLs.get(i).getImageURLs().get(j), new File(destinationPath + dataTagsImageURLs.get(i).getTagName() + "\\" + imageName));

			}
		}
	}

	public static void downloadFileFromURL(String urlString, File destination) {    
        try {
            URL website = new URL(urlString);
            ReadableByteChannel rbc;
            rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(destination);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
