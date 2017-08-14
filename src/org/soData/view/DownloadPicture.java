package org.soData.view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.soData.model.CrawlPicService;
import org.soData.model.DownloadPic;
import org.soData.model.JdownloadPic;
import org.soData.pojo.LinkContent;
import org.soData.pojo.SOURL;

public class DownloadPicture extends JFrame implements ActionListener{
	SOURL url = new SOURL();
	List<LinkContent> links;
	CrawlPicService service = new CrawlPicService();
	DownloadPic downloadPic = new DownloadPic();
	JButton button;
	JTextField text;
	JTextArea area = new JTextArea();
	JPanel p = new JPanel();
	JScrollPane jsp;
	public DownloadPicture(){
		super("图片批量下载");
		Container c = this.getContentPane();
		button = new JButton("开始");
		text  = new JTextField("http://www.mmtt33.link/fbuqae_590096.htm",30);
		p.add(text);p.add(button);
		button.addActionListener(this);
		jsp = new JScrollPane(area);
		c.add(p,"North");
		c.add(jsp,"Center");
		setSize(500,400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==button){
			url.setUrl(text.getText());
			url.setResultTagName("img");
			url.setType(SOURL.SELECTION);
			links = CrawlPicService.CrawlPage(url);
			System.out.println(links.size());
//			for(LinkContent l:links){
//				
//				area.append(l.getLinkHref()+"\n");
//			}
			if(links!=null){
				download();
			}
		}
	}
	private void download() {
		// TODO Auto-generated method stub
//		int i = 1;
//		for(LinkContent l:links){
//			if(i>5)break;
//			String f = new String("E:\\csexe\\"+0+".jpg");
//			i++;
//			LinkContent l = links.get(0);
			try {
				JdownloadPic.download(links);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		}
	}


}
