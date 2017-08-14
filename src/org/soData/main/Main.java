package org.soData.main;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.soData.model.CrawlService;
import org.soData.model.DoubanModel;
import org.soData.model.DownloadPic;
import org.soData.model.JdownloadPic;
import org.soData.pojo.LinkContent;
import org.soData.pojo.SOURL;
import org.soData.view.DownloadPicture;

public class Main {
	public static void main(String[] args){
//		DownloadPic downloadPic = new DownloadPic();
//		String f = new String("E:\\csexe\\12.jpg");
//		try {
//			JdownloadPic.download("http://i.imagseur.com/uploads/2017-08/13/37db2afb2374f49613bfee11d653dfbf.jpg", f);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		new DownloadPicture();
	}
	private static void te() {
		DoubanModel ds = new DoubanModel("https://movie.douban.com/subject/27068480/comments",
									null,null,"",SOURL.CLASS,SOURL.GET);
		ds.crawlPage("?start=0&limit=20&sort=new_score&status=F");
		HashSet s =ds.user;
		Iterator i = s.iterator();
		while(i.hasNext()){
			HashMap<String,String> hm = (HashMap<String, String>) i.next();
			for(String key : hm.keySet()){
				System.out.println(key+":"+hm.get(key));
			}
		}
		for(String link:ds.link){
			System.out.println(link);
		}
		System.out.println(ds.user.size());
	}
	static void displayAll(Collection col) {
	      Iterator itr = col.iterator();
	      while (itr.hasNext()) {
	         String str = (String) itr.next();
	         System.out.print(str + " ");
	      }
	      System.out.println();
	 }
	private static void sss() {
				SOURL url = new SOURL(  
		                "https://www.douban.com/people/wutong666/",  
		        new String[] {}, new String[] {},  
		                "pl", SOURL.CLASS, SOURL.GET);  
		        List<LinkContent> extracts = CrawlService.CrawlPage(url);  
		        printf(extracts);
			}
	public static void printf(List<LinkContent> datas)  
    {  
        for (LinkContent data : datas)  
        {  
            System.out.println(data.getLinkText());  
            System.out.println(data.getLinkHref());  
            System.out.println("***********************************");  
        }  
  
    }  
}
