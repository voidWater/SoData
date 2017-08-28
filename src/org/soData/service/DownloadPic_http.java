package org.soData.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadPic_http {
	
	public static void getImages(String urlPath,File fileName) throws Exception{
        URL url = new URL(urlPath);//：获取的路径
        //:http协议连接对象
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        
        conn.setReadTimeout(6 * 10000);
        if (conn.getResponseCode() <10000){
            InputStream inputStream = conn.getInputStream();
            byte[] data = readStream(inputStream);
            if(data.length>(1024*10)){
                FileOutputStream outputStream = new FileOutputStream(fileName);
                outputStream.write(data);
                System.err.println("图片下载成功");
                outputStream.close();
            }
        }
         
    }
	public static byte[] readStream(InputStream inputStream) throws Exception{
		System.out.println(inputStream.available());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while((len = inputStream.read(buffer)) !=-1){
            outputStream.write(buffer, 0, len);
        }
        outputStream.close();
        inputStream.close();
        return outputStream.toByteArray();
    }
}
