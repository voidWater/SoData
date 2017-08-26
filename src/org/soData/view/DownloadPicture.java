package org.soData.view;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
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
	//DownloadPic downloadPic = new DownloadPic();
	JButton button;
	JTextField text;
	
	JButton fileButton;
	JTextField fileText;
	
	JTextArea area = new JTextArea();
	JPanel p = new JPanel();
	JPanel subp1 = new JPanel();
	JPanel subp2 = new JPanel();
	JScrollPane jsp;
	public DownloadPicture(){
		super("图片批量下载");
		Container c = this.getContentPane();
		button = new JButton("开始");
		text  = new JTextField("http://www.mmtt33.link/fbuqae_532190.htm",30);
		fileButton = new JButton("path");
		fileText  = new JTextField("E:\\csexe\\",30);
		subp1.add(text);subp1.add(button);
		subp2.add(fileText);subp2.add(fileButton);
		p.setLayout(new GridLayout(2,1));
		p.add(subp1,"North");p.add(subp2,"Center");
		
		button.addActionListener(this);
		fileButton.addActionListener(this);
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
			url.setRequestMoethod(SOURL.GET);
			links = CrawlPicService.CrawlPage(url);
			System.out.println(links.size());
			if(links!=null&&fileText.getText()!=null){
				download(links,fileText.getText());
			}else{
				System.out.println("url and path is null");
			}
		}else if(e.getSource() == fileButton){
			JFileChooser fd;
			if(fileText.getText()!=null){
				 fd = new JFileChooser(fileText.getText());
			}else{
				 fd = new JFileChooser("E:\\csexe\\");
			}
			  
        	fd.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        	fd.showOpenDialog(null);  
        	File f = fd.getSelectedFile();  
        	if(f != null){
        		fileText.setText(f.getPath());
        	}
		}
	}
	private void download(List<LinkContent> links,String path) {
		// TODO Auto-generated method stub
			File  file = new File(path);
			if(!file.exists()){
				file.mkdirs();
			}
			try {
				JdownloadPic.downloadByList(links,path);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
