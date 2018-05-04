package com.lingnet.hcm.action.laobao;

import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.DeptPosition;
import com.lingnet.hcm.entity.check.CkStandard;
import com.lingnet.hcm.entity.laobao.Fafangbiaozhun;
import com.lingnet.hcm.entity.laobao.Wupin;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.service.laobao.WupinService;
import com.lingnet.qxgl.security.manage.metadata.BackendSecurityMetadataSource;
import com.lingnet.util.JsonUtil;

@Controller
public class WuPinAction extends BaseAction{
	
    private static final long serialVersionUID = 3704206385083610381L;

    @Resource(name="wupinService")
    private WupinService wupinService;
	
	private Wupin wupin;
	private String wpmc;
	private String ggxh;
	private String jldw;
	private String gys;

	private String name;
	private String maqm;
	private String daqm;
	private String cxgz;
	private String dxgz;
	private String yrf;
	private String yy;
	private String yx;
	private String maqx;
	private String jyx;
	private String jyst;
	private String dhst;
	private String node1;
	private String node2;
	private Fafangbiaozhun fafangbiaozhun;
	
    private BackendSecurityMetadataSource backendSecurityMetadataSource;
    
    public BackendSecurityMetadataSource getBackendSecurityMetadataSource() {
        return backendSecurityMetadataSource;
    }
    
    public void setBackendSecurityMetadataSource(
            BackendSecurityMetadataSource backendSecurityMetadataSource) {
        this.backendSecurityMetadataSource = backendSecurityMetadataSource;
    }
    /**
     * 删除
     */
    public String delete(){
        try {
            /*将实现方法放到del中*/
			wupinService.delete(id);
        } catch (Exception e) {
            return ajax(Status.error,e.toString().substring(e.toString().indexOf(":")+1));
        }
        return ajax(Status.success, "success");
    }
    
    /**
     * 角色保存或修改方法
     */
    public String saveOrUpdate(){
        return ajax(Status.success, wupinService.saveOrUpdate(formdata));
    }
/*    public String saveOrUpdate1(){
        try{
        	wupinService.saveOrUpdate(name,maqm,daqm,cxgz,dxgz,yrf,yy,yx,maqx,jyx,jyst,dhst,backendSecurityMetadataSource);
        }catch (Exception e){
        	e.printStackTrace();
            return ajax(Status.success, e.toString().substring(e.toString().indexOf(":")+1));
        }
        return ajax(Status.success, "success");
    }*/
    /**
     * 物品发放标准保存
     */
/*    public String saveOrUpdate1(){
        try{
        	wupinService.saveOrUpdate1(name,maqm,daqm,cxgz,dxgz,yrf,yy,yx,maqx,jyx,jyst,dhst,backendSecurityMetadataSource);
        }catch (Exception e){
        	e.printStackTrace();
            return ajax(Status.success, e.toString().substring(e.toString().indexOf(":")+1));
        }
        return ajax(Status.success, "success");
    }*/
    
	
    /** 
    * 跳转到角色管理增加页
    */
    public String function(){
   	   wupin = wupinService.get(Wupin.class, Restrictions.eq("id", id));
   	   if (null != wupin && !StringUtils.isBlank(wupin.getId())) {
   	       DeptPosition position = getBeanByCriteria(DeptPosition.class, Restrictions.eq("id", wupin.getPostId()));
   	       if (null != position)
   	           data = position.getPositionName();
   	   }
       
       return "function";
       
    }

    //进入物品管理界面

    public String guanli(){
        return "guanli";
    }
    //进入物品发放界面
    public String fafang(){
        return "fafang";
    }

    //跳转到发放标准页面
    public String ffbiaozhun(){
    	return "ffbiaozhun";
    }
    //进入添加劳保物品标准信息界面
    public String ffbzadd(){
    	return "ffbzadd";
    }
    //进入编辑劳保物品标准信息界面
    public String ffbzedit(){
        fafangbiaozhun = getBeanByCriteria(Fafangbiaozhun.class, Restrictions.eq("id", id));
        if (null != fafangbiaozhun) {
            DeptPosition position = getBeanByCriteria(DeptPosition.class, Restrictions.eq("id", fafangbiaozhun.getName()));
            if (null != position)
                data = position.getPositionName();
        }
        return "ffbzadd";
    }
    //进入编辑界面
    public String edit() {
    	wupin = wupinService.get(id);
    	return "ffbzadd";
    }
    /**
     * @Title: 岗位
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月24日 V 1.0
     */
    public String post() {
        return "post";
    }
    /**
     * 删除劳保发放标准信息
     * @Title: remove 
     * @return 
     * @author wangqiang
     * @since 2017年4月5日 V 1.0
     */
    public String remove() {
    	if (ids != null && !"".equals(ids)){
    		String[] idArrs = ids.split(",");
    		wupinService.delete(CkStandard.class, idArrs);
    	}
        return ajax(Status.success, "success");
    }
    /**
     * 导出劳保标准信息
     * @Title: exportStandardInfo  
     * @author wangqiang
     * @since 2017年4月6日 V 1.0
     */
    public void exportStandardInfo(){
    	HttpServletResponse resp = getResponse(); 
    	try {
            HSSFWorkbook hwb = wupinService.exportInfos();
            resp.setContentType("application/x-msdownload");
            resp.setHeader("Content-Disposition", "attachment;filename=\""
            		+ new String("劳保标准信息".getBytes("gb2312"), "ISO8859-1")+ ".xls" + "\"");
            OutputStream out = resp.getOutputStream();
            hwb.write(out);
            out.flush();
            out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
    }

    /**
     * 获取角色信息
     */
    // 整理代码将实现方法放到impl中  栾胜鹏  2014-10-20
    public String json(){
        String json = wupinService.json(key,pager);
        
        return ajax(Status.success, json);
    }

    /**
     * @Title: 物品发放页面
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月18日 V 1.0
     */
    public String reply() {
        // 查找该员工的岗位
        BasicInformation information = getBeanByCriteria(BasicInformation.class, Restrictions.eq("id", id));
        if (null != information) data = information.getSpecificPostId();
        return "reply";
    }

    /**
     * @Title: 发放标准数据保存或者更新
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月25日 V 1.0
     */
    public String saveOrUpdateBz() {
        return ajax(Status.success, wupinService.saveOrUpdateBz(formdata));
    }

    /**
     * @Title: 获取需要审批的领取物品
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月25日 V 1.0 
     */
    public String getNeedCheckWp() {
        return ajax(Status.success, JsonUtil.Encode(wupinService.getNeedCheckWp(name,id, pager)));
    }

    /**
     * @Title: 同意申请物品
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月26日 V 1.0
     */
    public String updateToAgree() {
        return ajax(Status.success, wupinService.updateToAgree(ids));
    }

    /**
     * @Title: 不同意申请物品
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月26日 V 1.0
     */
    public String updateToDisAgree() {
        return ajax(Status.success, wupinService.updateToDisAgree(ids));
    }

    /**
     * @Title: 物品发放历史页面
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月18日 V 1.0
     */
    public String history() {
        return "history";
    }

	public Wupin getWupin() {
		return wupin;
	}

	public void setWupin(Wupin wupin) {
		this.wupin = wupin;
	}


	public String getWpmc() {
		return wpmc;
	}


	public void setWpmc(String wpmc) {
		this.wpmc = wpmc;
	}


	public String getGgxh() {
		return ggxh;
	}


	public void setGgxh(String ggxh) {
		this.ggxh = ggxh;
	}


	public String getJldw() {
		return jldw;
	}


	public void setJldw(String jldw) {
		this.jldw = jldw;
	}


	public String getGys() {
		return gys;
	}


	public void setGys(String gys) {
		this.gys = gys;
	}

    public Fafangbiaozhun getFafangbiaozhun() {
        return fafangbiaozhun;
    }

    public void setFafangbiaozhun(Fafangbiaozhun fafangbiaozhun) {
        this.fafangbiaozhun = fafangbiaozhun;
    }

	public String getNode1() {
		return node1;
	}

	public void setNode1(String node1) {
		this.node1 = node1;
	}

	public String getNode2() {
		return node2;
	}

	public void setNode2(String node2) {
		this.node2 = node2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
