package com.application.Infibeam.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.bind.DatatypeConverter;

import org.apache.tomcat.util.codec.binary.Base64;

public class ImageProcess {
	
	public static String encodeFileToBase64Binary(File file){
        String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = new String(Base64.encodeBase64(bytes),"UTF-8");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return encodedfile;
    }
	
	public static String DecodeFile(String file) {
		 byte[] byteArray = Base64.decodeBase64(file.getBytes());
		 String decodedString = new String(byteArray);
		
		 
		 return decodedString;
	}
	
	
	public static String getPath(String str) {
		String savePath=null;
		try {
		String[] strings = str.split(",");
		byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
		String img=GenRandom.GenRandomStr(5);
	 
        savePath="D:\\Rajan Data\\mscit\\training_rajan\\EcommerceProject\\java\\Infibeam\\src\\main\\resources\\images\\"+img+".jpg";
		// savePath="/resources/images/"+img+".jpg";
        File file = new File(savePath);
           
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
        outputStream.write(data);
           
        outputStream.close();
		
        return savePath;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		
	}

	

}
