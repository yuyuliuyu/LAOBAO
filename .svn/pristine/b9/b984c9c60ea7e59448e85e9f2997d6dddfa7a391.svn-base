package com.lingnet.common.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.stereotype.Controller;

import com.lingnet.common.service.UploadService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.ToolUtil;

@SuppressWarnings("serial")
@Controller
public class UploadAction extends BaseAction {

    @Resource(name = "uploadService")
    private UploadService uploadService;
    
    private String path;

    public String upLoad() throws IOException {
        MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) this
                .getRequest();
        return ajax(Status.success,
                JsonUtil.Encode(uploadService.upload(wrapper)));
    }

    /**
     * 上传所有类型文件
     * 
     * @Title: upLoadFile
     * @return String
     * @author yinzl
     * @since 2016年10月8日 V 1.0
     */
    public String upLoadFile() {
        MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) this
                .getRequest();

        String conf_path = ToolUtil.getPropert(
                "config.properties",
                "real_path");
        
        try {
        	path = path.trim();
            String fileurl = uploadService.uploadFile(wrapper, conf_path.trim()+path);
            return ajax(path+"/"+fileurl);
        } catch (Exception e) {
            e.printStackTrace();
            return ajax("");
        }

    }

    /**
     * @Title: 保存base64图片 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月10日 V 1.0
     */
    public String uploadBase64() {
        String filePath = ToolUtil.getPropert("config.properties", "real_path");;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String stamp = sdf.format(new Date()) + ".png";

        return ajax(Status.success, JsonUtil.Encode(uploadService.uploadBase64(formdata, filePath.trim()+path, stamp)));
    }

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
    
}
