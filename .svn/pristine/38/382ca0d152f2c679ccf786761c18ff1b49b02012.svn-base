package com.lingnet.common.service;

import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.formula.functions.T;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

public interface UploadService extends BaseService<T, String> {

    /**
     * @Title: 上传文件 
     * @param wrapper
     * @return
     * @throws IOException 
     * HashMap 
     * @author zhanghj
     * @since 2016-8-15 V 1.0
     */
    @SuppressWarnings("rawtypes")
    public HashMap upload(MultiPartRequestWrapper wrapper) throws IOException;
    /**
     *  上传文件 (任意格式)
     * @Title: uploadFile 
     * @param wrapper
     * @return 
     * HashMap
     * @author yinzl
     * @param stamp 
     * @param path 
     * @throws Exception 
     * @since 2016年10月8日 V 1.0
     */
    public String uploadFile(MultiPartRequestWrapper wrapper, String path)  throws Exception ;

    /**
     * @Title: 多文件上传  
     * @param path 存放路径
     * @param fileName 文件名集合
     * @param fieldName 文件上传控件name值
     *  <p>后台默认为file，如果页面上传控件name="file"，这里的参数可设置为null 或者 ""</p>
     * @param wrapper
     * @return 
     * HashMap 
     * @action 例如：
     *  <pre>
     *      MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) this.getRequest();
     *      // 上传文件存储路径
     *      String path="customer";
     *      // 文件名集合获取
     *      String[] fileName=wrapper.getFileNames("file");
     *  </pre>
     * @author zhanghj
     * @since 2016-8-4 V 1.0
     */
    @SuppressWarnings("rawtypes")
    public HashMap upload(String path, String[] fileNames, String fieldName, MultiPartRequestWrapper wrapper);

    /**
     * @Title: 上传base64图片 
     * @param imgStr
     * @param filePath
     * @param fileName
     * @return 
     * HashMap<String, Object> 
     * @author zhanghj
     * @since 2017年3月10日 V 1.0
     */
    public HashMap<String, Object> uploadBase64(String imgStr, String filePath, String fileName);

}
