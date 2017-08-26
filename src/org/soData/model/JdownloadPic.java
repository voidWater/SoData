package org.soData.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.soData.pojo.LinkContent;


public class JdownloadPic implements Runnable{
	public static Map<String,String> map= new HashMap<String,String>();
	/**
	 * @param args
	 */
	public static void downloadByList(List<LinkContent> list,String path){
		int i = 1;
		for(LinkContent l : list){
			//if(i>10)break;
			System.out.println(i+":"+l.getLinkHref());
			
			try {
				download(l.getLinkHref(),path+"\\"+i+".jpg");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				map.put(path+"\\"+i+".jpg", l.getLinkHref());
				e.printStackTrace();
			}
			i++;
		}
		
		if(!map.isEmpty()){
			round();
		}
	} 
	public static void round(){
		System.out.println("round:"+map.size());
		List<String> list = new ArrayList<String>();
		Set s = map.keySet();
		Iterator i = s.iterator();
		while(i.hasNext()){
			String file = (String) i.next();
			String path = map.get(file);
			System.out.println(file+":"+path);
			try {
				list.add(file);
				download(path,file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				list.remove(list.size()-1);
			}
			
		}
		for(String i1:list){
			map.remove(i1);
		}
		if(map.size()!=0){
			round();
		}
	}
	public static  void download(String args,String file) throws IOException {
		// TODO Auto-generated method stub
		Response resultImageResponse;
		
		// output here
		FileOutputStream out;
		try {
			resultImageResponse = Jsoup.connect(args)
					.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
					//.userAgent("Opera/9.80 (Android 2.3.4; Linux; Opera Mobi/build-1107180945; U; en-GB) Presto/2.8.149 Version/11.10")
					.ignoreContentType(true)
					.execute();
			out = (new FileOutputStream(new java.io.File(file)));
			out.write(resultImageResponse.bodyAsBytes());           
			// resultImageResponse.body() is where the image's contents are.
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
