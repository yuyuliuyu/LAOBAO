package com.lingnet.qxgl.action.system;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.ToolUtil;
/**
 * 下载上传模板
 * @ClassName: XiazaiAction 
 * @Description: 下载上传模板 
 * @author 
 * @date 2014-8-19 下午6:19:56 
 *
 */
public class XiazaiAction extends BaseAction{
    /**
     * 
     */
    private static final long serialVersionUID = -387604801268572518L;
    
    private String name;//模板名称

    @Action(value = "/fileDownload")
    public String fileDownload(){
        HttpServletResponse response = getResponse();
        HttpServletRequest request = getRequest();
        try {
            
            String path = null;
            String path1 = null;
            if(name.equals("highCamera")){// 高拍仪SDK
                path = "捷宇科技DoccameraOcx控件_V3.7.160919.zip";
                path1 = "plugin/idcard/DoccameraOcx.zip";
            } else if(name.equals("lodop_32")){// lodop打印控件
                path = "install_lodop32.exe";
                path1 = "plugin/print/install_lodop32.exe";
            } else if(name.equals("lodop_64")){// lodop打印控件(64位)
                path = "install_lodop64.exe";
                path1 = "plugin/print/install_lodop64.exe";
            } else if(name.equals("CLodop")){// CLodop
                path = "CLodop_Setup_for_Win32NT.exe";
                path1 = "plugin/print/CLodop_Setup_for_Win32NT.exe";
            } else if(name.equals("jfmxmb")){// 缴费明细模板
                path = "缴费明细模板.zip";
                path1 = "file/excel/缴费明细模板.zip";
            } else if(name.equals("avgSalary")){// 平均工资模板
                path = "导入平均工资模板.xls";
                path1 = "file/excel/导入平均工资模板.xls";
            } else if(name.equals("salaryItem")){// 薪资项目模板
                path = "导入薪资项目模板.xls";
                path1 = "file/excel/导入薪资项目模板.xls";
            } else if(name.equals("adjustFixed")){// 调整员工薪资模板
                path = "调整员工薪酬模板.xls";
                path1 = "file/excel/调整员工薪酬模板.xls";
            } else if(name.equals("adjustStand")){// 员工薪酬标准模板
                path = "员工薪酬标准模板 .xls";
                path1 = "file/excel/员工薪酬标准模板 .xls";
            } else if(name.equals("secondAdjust")){// 二次分配模板
                path = "二次分配模板.xls";
                path1 = "file/excel/二次分配模板.xls";
            } else if(name.equals("checkModel")){// 核算数据模板
                path = "导入核算数据模板.xls";
                path1 = "file/excel/导入核算数据模板.xls";
            }

            File fileToDown = new File(ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/")+"template/"+path1);
            
            FileInputStream fis = new FileInputStream(fileToDown);
            
            BufferedInputStream bis = new BufferedInputStream(fis);
            
            response.setContentType("application/x-msdownload");
            // 解决中文文件名乱码问题  
            if (request.getHeader("User-Agent").toLowerCase()  
                    .indexOf("firefox") > 0) {  
            	path = new String(path.getBytes("UTF-8"), "ISO8859-1"); // firefox浏览器  
            } else if (request.getHeader("User-Agent").toUpperCase()  
                    .indexOf("MSIE") > 0) {  
            	path = URLEncoder.encode(path, "UTF-8");// IE浏览器  
            }else if (request.getHeader("User-Agent").toUpperCase()  
                    .indexOf("CHROME") > 0) {  
            	path = new String(path.getBytes("UTF-8"), "ISO8859-1");// 谷歌  
            } else {
            	path = URLEncoder.encode(path, "UTF-8");
            }
            response.setHeader("Content-Disposition", "attachment;filename="+path);
            // 输出文件流
            ServletOutputStream out;
            out = response.getOutputStream();
            int len = -1;
            while ((len = bis.read()) != -1) {
                out.write(len);
            }
            bis.close();
            out.close();
            return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ajax(Status.success, "系统找不到指定文件！");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return ajax(Status.success, "不支持页面编码方式！");
        } catch (IOException e) {
            e.printStackTrace();
            return ajax(Status.success, "下载异常！");
        }
    }

    public String download(){
    		HttpServletResponse response = getResponse();
    		HttpServletRequest request = getRequest();
    		
            FileInputStream fis= null;
            BufferedInputStream bis= null;
            ServletOutputStream out = null;
			try {
				String real = ToolUtil.getPropert("config.properties","real_path");
				fis = new FileInputStream(real+name);
				bis = new BufferedInputStream(fis);
	            
	            response.setContentType("application/x-msdownload");
	            // 解决中文文件名乱码问题  
	            if (request.getHeader("User-Agent").toLowerCase()  
	                    .indexOf("firefox") > 0) {  
	            	name = new String(name.getBytes("UTF-8"), "ISO8859-1"); // firefox浏览器  
	            } else if (request.getHeader("User-Agent").toUpperCase()  
	                    .indexOf("MSIE") > 0) {  
	            	name = URLEncoder.encode(name, "UTF-8");// IE浏览器  
	            }else if (request.getHeader("User-Agent").toUpperCase()  
	                    .indexOf("CHROME") > 0) {  
	            	name = new String(name.getBytes("UTF-8"), "ISO8859-1");// 谷歌  
	            } else {
	            	name = URLEncoder.encode(name, "UTF-8");
	            }
	            response.setHeader("Content-Disposition", "attachment;filename="+name);
	            // 输出文件流
	            out = response.getOutputStream();
	            int len = -1;
	            while ((len = bis.read()) != -1) {
	                out.write(len);
	            }
	            return null;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
                try {
                    if (out != null) out.close();
                    if (bis != null) bis.close();
                    if (fis != null) fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
			}
            
        return null;    
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
