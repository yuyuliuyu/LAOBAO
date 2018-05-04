package com.lingnet.util.async;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class DownTest {

    /** 
     * @Title: main
     * @param args
     * void
     * @author zhanghj
     * @throws IOException 
     * @since 2017-1-4 V 1.0
     */
    @SuppressWarnings({ "unused", "resource" })
    public static void main(String[] args) throws IOException {
    	
        String[] files= {"onehealth.docx","sjnk1.docx","wk.docx"};
        for (String string : files) {
//            DownloadInfo bean = new DownloadInfo("http://localhost:8080/medical/template/wordmodel/"+string, string, "e://", 4);
//            AsyncDownFile down = new AsyncDownFile(bean);
//            new Thread(down).start();
//            ExecutorService pool = Executors.newCachedThreadPool();
//            pool.execute(down);
//            URL url = new URL("http://localhost:8080/medical/template/wordmodel/"+string);
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
////            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//            InputStream is = urlConnection.getInputStream();
//            String line;
//            byte[] buff = new byte[1024];
//            int length = -1;
//            RandomAccessFile itemFile = new RandomAccessFile("e://"+string, "rw");
//            itemFile.seek(0);
//            while ((length = is.read(buff)) != -1) {
//                itemFile.write(buff, 0, length);
//            }
            String path = "E:/mec/mec/file/sell/customer";
            String netVirtual = "http://localhost/mec/";
                String[] urls = {"file/sell/customer/职业体检名单模板.xlsx","file/sell/customer/a.txt"};
                for (String str : urls) {
                	int bytesum = 0;
                    int byteread = 0;
                	 String str1 = "http://localhost/medical/system/xiazai!fileDownload.action?name=bdModel_jk";
                	 URL url = new URL(str1);
                	 
                	 URLConnection conn = url.openConnection();
                     InputStream inStream = conn.getInputStream();
                     File f = new File(path);
                     if (!f.exists()) {
                         f.mkdirs();
                     }
                     File file = new File("E:/mec/mec"+File.separator+str); 
                     //FileInputStream fis = new FileInputStream(file);  

                     //HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(fis)); 
                    // wb.write(inStream);
                    // saveUrlAs("http://localhost/medical/system/xiazai!fileDownload.action?name=bdModel_jk",file);
                     FileOutputStream fos = new FileOutputStream(file);
                     byte[] buffer = new byte[1204];
                     int length;
                     while ((byteread = inStream.read(buffer)) != -1) {
                         bytesum += byteread;
                         System.out.println(bytesum);
                         fos.write(buffer, 0, byteread);
                     }
                	 /*
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream is = urlConnection.getInputStream();
                    byte[] buff = new byte[1024];
                    int length = -1;
                    File f = new File(path);
                    if (!f.exists()) {
                        f.mkdirs();
                    }
                    RandomAccessFile itemFile = new RandomAccessFile(path+File.separator+str, "rw");
                    itemFile.seek(0);
                    while ((length = is.read(buff)) != -1) {
                        itemFile.write(buff, 0, length);
                    }*/
                }
        }
    }
    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = String.valueOf(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();

    }
    
    public static void saveUrlAs(String Url, File fileName){
        //此方法只能用HTTP协议
        //保存文件到本地
        //Url是文件下载地址,fileName 为一个全名(路径+文件名)文件
        URL url;
        DataOutputStream out = null;
        DataInputStream in = null;
        try {
            url = new URL(Url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            in = new DataInputStream(connection.getInputStream());
            out = new DataOutputStream(new FileOutputStream(fileName));
            byte[] buffer = new byte[4096];
            int count = 0;
                 while ((count = in.read(buffer)) > 0) {
                     out.write(buffer, 0, count);
                 }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(out != null){
                    out.close();
                }
                if(in != null){
                    in.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
