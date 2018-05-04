package com.lingnet.common.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.stereotype.Repository;

import com.lingnet.common.service.UploadService;
import com.lingnet.util.Base64Util;
import com.lingnet.util.ToolUtil;

@Repository("uploadService")
public class UploadServiceImpl extends BaseServiceImpl<T, String>
    implements UploadService {

    @SuppressWarnings("rawtypes")
    @Override
    public HashMap upload(MultiPartRequestWrapper wrapper) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        HashMap<String, String> map = new HashMap<String, String>();
        File f = wrapper.getFiles("file")[0];
        String fname = wrapper.getFileNames("file")[0];
        String currentPath = "/template/image/division/";  
        String tempFileDir = ServletActionContext.getServletContext().getRealPath(currentPath);
        String name = sdf.format(new Date(System.currentTimeMillis()))+fname.substring(fname.lastIndexOf("."));
        File file = new File(tempFileDir, name);
        FileInputStream fis=null;;
        FileOutputStream fos=null;
        try {
            fis = new FileInputStream(f);
            fos = new FileOutputStream(file);
            byte[] bs = new byte[2014];
            while(fis.read(bs)!=-1){
                fos.write(bs, 0, bs.length);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("data", "false");
        }finally{
            fos.close();
            fis.close();
        }
       if(!map.isEmpty()){
           return map;
       }else{
           map.put("data", currentPath+name);
           return map;
       }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public HashMap upload(String path, String[] fileNames, String fieldName, MultiPartRequestWrapper wrapper) {
        List filePathList = new ArrayList();
        HashMap map = new HashMap();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            // 获取文件
            File[] files = wrapper.getFiles((null == fieldName || "".equals(fieldName)) ? "file" : fieldName);
            // 文件路径拼接合成
            // 虚拟路径
            String virtualPath = ToolUtil.getPropert("doc_config.properties", "real_path");
            String newPath = path;
            File dir = new File(virtualPath + newPath);
            // 文件不存在则创建
            if (!dir.exists()) {
                dir.mkdirs();
            }
            // 存在文件
            if (files.length > 0) {
                // 循环遍历
                for (int i = 0; i < files.length; i++) {
                    File file = new File(dir, (null == fileNames[i] || "".equals(fileNames[i]))? "" : fileNames[i]);
                    fos = new FileOutputStream(file);
                    bis = new BufferedInputStream(new FileInputStream(files[i]));
                    bos = new BufferedOutputStream(fos);
                    int b = 0;
                    while ((b = bis.read()) != -1) {
                        bos.write(b);
                    }

                    // 记录保存位置
                    filePathList.add(newPath + File.separator + fileNames[i]);
                }
                map.put("success", true);
                map.put("filepath", filePathList);

                return map;
            } else {
                map.put("success", false);
                map.put("msg", "文件不存在");

                return map;
            }
        } catch (Exception e) {
            // 失败
            map.put("success", false);
            map.put("msg", e.getMessage());

            return map;
        } finally {
            try {
                if (null != fos) {
                    fos.close();
                }
                if (null != bos) {
                    bos.close();
                }
                if (null != bis) {
                    bis.close();
                }
            } catch (IOException e) {
                map.put("success", false);
                map.put("msg", e.getMessage());

                return map;
            }
        }
    }

    @Override
    public String uploadFile(MultiPartRequestWrapper wrapper, String path) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String stamp = sdf.format(new Date());
      
            File[] files = wrapper.getFiles("file");
            String[] names = wrapper.getFileNames("file");
            File dir = new File(path+"/");
            if(!dir.exists()){
                dir.mkdirs();
            }
            String fileurl = "";
            if(files.length>0)
            {
                for(int i = 0 ; i < files.length;i++){
                    String str = names[i].substring(names[i].lastIndexOf("."));
//                    if (!".docx".equals(str)) {//如果不是docx则返回标识，提醒上传错误
//                        return "not_docx";
//                    }
                      fileurl = stamp+str;//+"&#&"+names[i];
                      File file = new File(dir, fileurl)   ;
                      FileOutputStream fos = new FileOutputStream(file);
                      BufferedInputStream bis  = new BufferedInputStream(new FileInputStream(files[i]));
                      BufferedOutputStream bos = new BufferedOutputStream(fos);
                      int b =0;
                      while ((b=bis.read())!=-1) {
                        bos.write(b);
                    }
                      bos.close();
                      bis.close();
                }
            }
            
     return fileurl;
    }

    @Override
    public HashMap<String, Object> uploadBase64(String imgStr, String filePath, String fileName) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        // 图像数据为空
        if (StringUtils.isBlank(imgStr)) {
            map.put("success", false);
            map.put("msg", "上传头像失败：头像不能为空！");

            return map;
        }
        // 文件路径拼接合成
        // 虚拟路径
        String newPath = filePath;
        File dir = new File(newPath);
        // 文件不存在则创建
        if (!dir.exists()) {
            dir.mkdirs();
        }
        OutputStream out= null;
        try {
            // Base64解码
            byte[] bytes = Base64Util.transferImageStringToByteArray(imgStr);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            File file = new File(dir, fileName);
            out = new FileOutputStream(file);
            out.write(bytes);
            out.flush();
            map.put("success", true);
            map.put("filepath", newPath + File.separator + fileName);

            return map;
        } catch (Exception e) {
            map.put("success", false);
            map.put("msg", "上传头像失败：" + e.getMessage());

            return map;
        } finally {
            try {
                if(out!=null){
                    out.close();
                }
            } catch (IOException e) {
                map.put("success", false);
                map.put("msg", e.getMessage());

                return map;
            }
        }
    }

}
