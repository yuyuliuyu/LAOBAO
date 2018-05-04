package com.lingnet.util;

import java.awt.Color;
import java.awt.Graphics2D; 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO; 
import com.swetake.util.Qrcode;

/**
 * 二维码工具类
 * 
 * @ClassName: QrcodeUtil
 * @Description: TODO
 * @date 2014-11-6 上午11:09:53
 * 
 */
public class QrcodeUtil {
	/**
	 * 创建二维码
	 * 
	 * @Title: createQrcode void
	 * @throws IOException 
	 * @since 2014-11-6 V 1.0 codeContent:二维码内容 path:路径
	 */
	public static String createQrcode(String codeContent, String path) throws IOException {
				Qrcode qrcode = new Qrcode();
		
				qrcode.setQrcodeErrorCorrect('M');
		
				qrcode.setQrcodeEncodeMode('B');
		
				qrcode.setQrcodeVersion(14);
		
				String testString = codeContent;
		
				byte[] d = testString.getBytes("UTF-8");
		
				BufferedImage bi = new BufferedImage(225, 225,
						BufferedImage.TYPE_INT_RGB);
		
				// createGraphics
		
				Graphics2D g = bi.createGraphics();
		
				// set background
		
				g.setBackground(Color.WHITE);
		
				g.clearRect(0, 0, 250, 250);
		
				g.setColor(Color.BLACK);
		
				if (d.length > 0 && d.length < 123) {
		
					boolean[][] b = qrcode.calQrcode(d);
		
					for (int i = 0; i < b.length; i++) {
		
						for (int j = 0; j < b.length; j++) {
		
							if (b[j][i]) {
		
								g.fillRect(j * 3 + 2, i * 3 + 2, 3, 3);
		
							}
		
						}
		
					}
		
				}
				
				 /**HttpServletRequest request = ServletActionContext.getRequest();
	            String separator=File.separator;//获取当前操作系统的分隔符
	            String bath = request.getSession().getServletContext().getRealPath("");
	            String ccbPath =separator + "template"+ separator + "logo"+ separator+"qc_code_logo.jpeg"; 

	            Image logo = ImageIO.read(new File(bath+ccbPath));//实例化一个Image对象。 
		        int widthLogo = logo.getWidth(null)>bi.getWidth()*2/10?(bi.getWidth()*2/10):logo.getWidth(null),   
		            heightLogo = logo.getHeight(null)>bi.getHeight()*2/10?(bi.getHeight()*2/10):logo.getWidth(null);  
		         
		         
		           * logo放在中心 
		          	        int x = (bi.getWidth() - widthLogo) / 2;  
		        int y = (bi.getHeight() - heightLogo) / 2;
		        g.drawImage(logo, x, y, widthLogo, heightLogo, null);  
		        g.dispose();    
		        bi.flush(); */  
		        String FilePath = path;
				
				File f = new File(FilePath);
				
				if(!f.exists()){
				    f.mkdirs();
				}
				
				if(!f.getParentFile().exists()){
				    f.getParentFile().mkdir();
				}
			
			ImageIO.write(bi, "png", f);
	
			return "success";
	}
	
	/**
	 * 删除文件
	 * @param sPath
	 * @return
	 */
	public static boolean deleteFile(String sPath) {  
		boolean flag = false;  
		File file = new File(sPath);  
		if(file.isFile()&&file.exists()){
			file.delete();
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 文件是否存在
	 * @param path
	 * @return
	 */
	public static boolean exist(String path){
		boolean flag = false;
		File file = new File(path);
		if(file.exists()){
			flag = true;
		}
		return flag;
	}

}
