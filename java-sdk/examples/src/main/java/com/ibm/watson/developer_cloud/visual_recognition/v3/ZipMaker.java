package com.ibm.watson.developer_cloud.visual_recognition.v3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipMaker {
	
	private String path;
	private ZipProducedEvent zipProducedEvent;
	
	ZipMaker()
	{
		
	}
	
	
	public ZipMaker(String path, ZipProducedEvent zipProducedEvent )
	{
		this.path = path;
		this.zipProducedEvent = zipProducedEvent;
		
		// call makeZip() to zip the files
		makeZip();
	}
	
	private void makeZip()
    {
		System.out.println("yes I am running with thread: " + Thread.currentThread().getName());
        final int BUFFER = 2048;
        File f = new File(path);
        
        try
        {
	    
	        File allSubDirectoryList[];
	        
	        File sourcePath = f;
	        
	        if(sourcePath.exists() && sourcePath.isDirectory())
	        {
        		allSubDirectoryList = sourcePath.listFiles();
        		
        		
        		// looping through each folder who's zip we have to make
        		for(int i=0;i<allSubDirectoryList.length;i++)
        		{
        			//System.out.println("Working on: " + allSubDirectoryList[i].toString() );
        			if(allSubDirectoryList[i].isDirectory())
        			{
						FileOutputStream fos = new FileOutputStream(allSubDirectoryList[i].getPath() + ".zip");
						ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(fos));
						byte data[] = new byte[BUFFER];
						
						// adding each images to zip
						File eachSubFolderContent[] = allSubDirectoryList[i].listFiles();
						for(int j=0;j<eachSubFolderContent.length;j++)
		        		{
							ZipEntry entry = new ZipEntry(eachSubFolderContent[j].getName());
                            zos.putNextEntry(entry);
                            //isEntry = true;
                            
                            //System.out.println("Adding " + eachSubFolderContent[j].toString());
                            
                            FileInputStream fileInputStream = new FileInputStream(eachSubFolderContent[j].toString());
                            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, BUFFER);
                            int size = -1;
                            while(  (size = bufferedInputStream.read(data, 0, BUFFER)) != -1  )
                            {
                                zos.write(data, 0, size);
                            }
                            bufferedInputStream.close();
		        		}
						zos.close();
						fos.close();
        			}
        		}
        		
        		// do callback as zip are produced
        		zipProducedEvent.zipProduced();
        		
	        }
        }
        catch(Exception e)
        {}
    }

}
