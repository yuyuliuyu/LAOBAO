package com.lingnet.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

/**
 * 图片上传工具类
 * @ClassName: UploadImageUtil 
 * @author 李志新
 * @date 2015-1-9 上午11:58:32 
 */
public class UploadImageUtil {
	
	/**
	 * @Title: uploadImage 
	 * @param map 是key为图片名，value为图片字节数组格式的map
	 * @param destPath  是图片保存在本地硬盤的路徑，如
	 * 					D:/tomcat/Tomcat 6.0/lcsp_img/creative4list
	 * @author 李志新
	 * @throws IOException 
	 * @since 2015-1-9 V 1.0
	 */
	public static void uploadImage(Map<String,byte []> map, String destPath) throws IOException {
		
		String fileName ;//文件名
		
		byte [] buf ;//读写图片数据
		
		FileOutputStream fos = null;
		
		BufferedOutputStream bos = null;
		
		//循环读取文件并上传
		for ( Entry<String, byte[]> entry : map.entrySet() ){
			
			fileName = entry.getKey();
			
			buf = entry.getValue();
	
			File destDir = new File(destPath);
	
			if (!destDir.exists())
				destDir.mkdirs();
	
			// 保存图片到指定路径
			File file = new File(destDir, fileName);
			fos = new FileOutputStream(file);
	
			bos = new BufferedOutputStream(fos);
			bos.write(buf);
		    
            //bos.flush();
    
            bos.close();
			 
	         /*   Image src = ImageIO.read(file);
	            int wideth = src.getWidth(null);
	            int height = src.getHeight(null);
	            BufferedImage image = new BufferedImage(wideth, height,
	                    BufferedImage.TYPE_INT_RGB);
	            Graphics g = image.createGraphics();
	            g.drawImage(src, 0, 0, wideth, height, null);
			 //水印文件
            File _filebiao = new File("D:img.png");
            Image src_biao = ImageIO.read(_filebiao);
            int wideth_biao = src_biao.getWidth(null);
            int height_biao = src_biao.getHeight(null);
            g.drawImage(src_biao, (wideth - wideth_biao)+5,
                    (height - height_biao)+5, wideth_biao, height_biao, null);
            //水印文件结束
            g.dispose();
			
            FileOutputStream out = new FileOutputStream(file);
            
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();*/
		}

	}

	/**
	 * 将用户提交上来的图片信息构造成可以存入数据库和本地硬盘的有效信息
	 * @Title: constructImageData 
	 * @param pictures
	 * @return key为图片名，value为经过Base64Decoder处理的图片字节数组格式的map
	 * @throws IOException 
	 * String[] 
	 * @author 李志新
	 * @since 2015-1-9 V 1.0
	 */
	public static Map<String,byte []> constructImageData(String pictures) throws IOException {

		//List<Map<String,byte []>> list_picture = new ArrayList<Map<String,byte []>>();
		Map<String,byte []> map_picture = new HashMap<String,byte []>();
		//先解析上传的图片pictures
		String [] images = pictures.split(",");
		for (int i = 0 ; i <images.length;i++){
			if(images[i]!=null&&!"".equals(images[i])&&!"null".equals(images[i])){

			    String image = images[i];
				//map_picture = new HashMap<String,byte []>();
				//构造key为图片名，value为图片字节数组格式的map
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			    String dateStr = sdf.format(new Date());
			    Random random = new Random();
			    dateStr +=random.nextInt(4);
				map_picture.put("imag"+dateStr+".jpg",
						Base64Util.transferImageStringToByteArray(image));
				
				//放入集合
				//list_picture.add(map_picture);
			
			}
		}
		return map_picture;
		
	}
	
	/**
	 * 获取所有图片的名称
	 * @Title: getImageNames 
	 * @param map
	 * @return String 
	 * @author 李志新
	 * @since 2015-1-9 V 1.0
	 */
	public static String getImageNames(Map<String,byte []> map){
		String names = "";
		for ( String str : map.keySet() ) {
			names += "".equals(names)?str:","+str;
		}
		return names;
	}
	
	/**
	 * 删除图片
	 * @Title: deleteFile 
	 * @param wholeFilePath 
	 * void 
	 * @author 李志新
	 * @since 2015-1-12 V 1.0
	 */
	public static Boolean deleteFile(String wholeFilePath){
		File file = new File (wholeFilePath);
		if (file.exists()){
			file.delete();
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 复制图片
	 * @Title: copy 
	 * @param fileFrom
	 * @param fileTo
	 * @throws IOException 
	 * void 
	 * @author 李志新
	 * @since 2015-2-2 V 1.0
	 */
	public static void copy(String fileFrom, String fileTo) throws IOException{
		
		FileInputStream fis = new FileInputStream(fileFrom);
		
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		FileOutputStream fos = new FileOutputStream(fileTo);
		
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		
		int d = -1;
		
		while ((d = bis.read()) != -1){
			
			bos.write(d);
		}
		
		bos.close();
		
		bis.close();
		
	}

}
