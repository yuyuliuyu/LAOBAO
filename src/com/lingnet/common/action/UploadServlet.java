package com.lingnet.common.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class UploadServlet extends HttpServlet{
    
    private static final long serialVersionUID = 8065370068094461840L;
    
    @SuppressWarnings({ "unchecked", "unused" })
	public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
         boolean status = false;
        // 使用Apache Common组件中的fileupload进行文件上传  
        FileItemFactory factory = new DiskFileItemFactory();  
        ServletFileUpload upload = new ServletFileUpload(factory);  
        String newFileName = null;  
        try {  
	            String fileName = null;  
	            FileItem uplFile = null;  
	            List<FileItem> itemList = upload.parseRequest(request);  
	            // Iterator iter = items.iterator();  
	  
	         // 如果大于1说明是分片处理
	            String id = "";
	            int chunks = 1;
	            int chunk = 0;
	            FileItem tempFileItem = null;
	
	            for (FileItem fileItem : itemList) {
	                 if (fileItem.getFieldName().equals("name")) {
	                    fileName = new String(fileItem.getString().getBytes(
	                            "ISO-8859-1"), "UTF-8");
	                }else if (fileItem.getFieldName().equals("file")) {
	                    tempFileItem = fileItem;
	                } /*else if (fileItem.getFieldName().equals("chunks")) {
	                    chunks = NumberUtils.toInt(fileItem.getString());
	                } else if (fileItem.getFieldName().equals("chunk")) {
	                    chunk = NumberUtils.toInt(fileItem.getString());
	                } else if (fileItem.getFieldName().equals("id")) {
	                    id = fileItem.getString();
	                }*/ 
	            }
	            // end of for  
	            // CEKditor中file域的name值是upload  
	  
	            // 获取文件名(无扩展名)  
	            // 设定上传文件路径  
	            String currentPath = "/template/image/";  
	            // 获得web应用的上传路径  
	           // String currentDirPath = getServletContext().getRealPath("\\upload\\");  
	            // 判断文件夹是否存在，不存在则创建 
	           // String tempFileDir = currentDirPath+ File.separator + id;
	            
	            String tempFileDir = getServletContext().getRealPath(currentPath);
	            File dirTest = new File(tempFileDir);  
	            if (!dirTest.exists()) {  
	                dirTest.mkdirs();  
	            }  
	            
	            
	            newFileName = createNewFileName(fileName);  
	  
	            File pathToSave = new File(tempFileDir, newFileName);  
	  
	            // 如果文件名相同，则重写且名字  
	            int counter = 1;  
	            while (pathToSave.exists()) {  
	                if (counter == 10) {  
	                    throw new IOException("名称重复：" + counter);  
	                }  
	                newFileName = createNewFileName(fileName);  
	                pathToSave = new File(tempFileDir, newFileName);  
	                counter++;  
	            }  
	  
	            tempFileItem.write(pathToSave);  
	            status = true;  
	  
	        // 以Jsong格式为输出信息  
	  
	        // response.setContentType("text/html; charset=UTF-8");  
	        response.setContentType("application/json;charset=UTF-8");  
	        response.setHeader("Pragma", "No-cache");  
	        response.setHeader("Cache-Control", "no-cache");  
	        response.setDateHeader("Expires", 0);  
	  
	        Map<String, String> data = new HashMap<String, String>();  
	        data.put("newFileName", currentPath+newFileName);  
	        String result = JSONObject.fromObject(data).toString();  
	        response.getWriter().write(result);  
	        response.getWriter().flush();  
	        response.getWriter().close();  
        } catch (Exception ex) {  
        	    response.setContentType("application/json;charset=UTF-8");  
	 	        response.setHeader("Pragma", "No-cache");  
	 	        response.setHeader("Cache-Control", "no-cache");  
	 	        response.setDateHeader("Expires", 0);  
	 	  
	 	        Map<String, String> data = new HashMap<String, String>();  
	 	        data.put("newFileName", "false");  
	 	        String result = JSONObject.fromObject(data).toString();  
	 	        response.getWriter().write(result);  
	 	        response.getWriter().flush();  
	 	        response.getWriter().close();
            throw new IOException(ex.getMessage());  
        }  
  
    }  
  
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        doPost(request, response);  
    }  
  
    private static String createNewFileName(String oldFileName) {  
        java.util.Date dt = new java.util.Date(System  
                .currentTimeMillis());  
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
        
        String time = fmt.format(dt);  
        String ext = getExtension(oldFileName);// 获取文件扩展名  
        String newName = time + "." + ext;  
        return newName;  
    }  
  
    /** 
     * 获取扩展名的方法 
     */  
    private static String getExtension(String fileName) {  
        return fileName.substring(fileName.lastIndexOf(".") + 1);  
    }  
  
    /** 
     * Servlet初始化方法 
     */  
    public void init() throws ServletException {  
  
    }  
}
