package com.lingnet.hcm.action.empdata;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.person.Attention;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.entity.person.Talk;
import com.lingnet.hcm.service.empdata.AttentionService;
import com.lingnet.hcm.service.empdata.TalkService;
import com.lingnet.util.JsonUtil;

/**
 * 
 * @ClassName: EmpSkillAction 
 * @Description: 员工关注、员工访谈Action 
 * @author zrl
 * @date 2017年3月10日 上午10:56:46 
 *
 */
public class AttentionAction extends BaseAction{

	private static final long serialVersionUID = -1699152317232566479L;
	private String flag;//标志位
    private String formdata;
    private String personId;
    
    private BasicInformation info;
    private Talk talk;
    private Attention attention;
    
    @Resource(name="attentionService")
    private AttentionService attentionService;
    
    @Resource(name="talkService")
    private TalkService talkService;
    
    /**
     * 列表页面
     */
    public String talklist(){
        return "talklist";
    }
    /**
     * 员工关注列表页面
     */
    public String attentionlist(){
        return "attentionlist";
    }
    /**
     * 列获取数据
     */
    public String getTalkListData() {
    	if(personId!=null&&!"".equals(personId)){
    		Map<String,String> dataMap = JsonUtil.parseProperties(searchData);
    		if(dataMap==null){
    			dataMap=new HashMap<String,String>();
    		}
			dataMap.put("personId", personId);
			
    		searchData=JsonUtil.Encode(dataMap);
    	}
        return ajax(Status.success, JsonUtil.Encode(talkService.getListData(pager,searchData)));
    }
    /**
     * 列获取数据
     */
    public String getAttentionListData() {
    	if(personId!=null&&!"".equals(personId)){
    		Map<String,String> dataMap = JsonUtil.parseProperties(searchData);
    		if(dataMap==null){
    			dataMap=new HashMap<String,String>();
    		}
			dataMap.put("personId", personId);
			
    		searchData=JsonUtil.Encode(dataMap);
    	}
        return ajax(Status.success, JsonUtil.Encode(attentionService.getListData(pager,searchData)));
    }
    
    /**
     * 添加页面
     */
    public String talkadd(){
    	talk=this.getBeanByCriteria(Talk.class, Restrictions.eq("id", id));
    	String personId="";
    	if(talk!=null){
    		personId=talk.getPersonId();
    	}
    	info=this.getBeanByCriteria(BasicInformation.class, Restrictions.eq("id", personId));
        return "talkadd";
    }
    /**
     * 添加页面
     */
    public String attentionadd(){
    	attention=this.getBeanByCriteria(Attention.class, Restrictions.eq("id", id));
    	String personId="";
    	if(attention!=null){
    		personId=attention.getPersonId();
    	}
    	info=this.getBeanByCriteria(BasicInformation.class, Restrictions.eq("id", personId));
        return "attentionadd";
    }

    public String saveOrUpdateAttention(){
        if(formdata==null||formdata==""){
            return ajax(Status.success,"数据错误请联系管理员!");
        }else{
        	attention=JsonUtil.toObject(formdata, Attention.class);
        } 
        try { 
            if(attention.getId()==null||"".equals(attention.getId().trim())){
                this.save(attention);
            }else{
            	Attention oldInfo=this.getBeanByCriteria(Attention.class, Restrictions.eq("id", attention.getId()));
            	if (oldInfo == null) {
                    throw new Exception("该条记录不存在");
                }
            	oldInfo.copyFrom(attention);
                this.update(oldInfo);
            }
        } catch (Exception e) {
            return ajax(Status.success,"保存/修改失败");
        }
        return ajax(Status.success,"success");
    } 
    
    public String saveOrUpdateTalk(){
        if(formdata==null||formdata==""){
            return ajax(Status.success,"数据错误请联系管理员!");
        }else{
        	talk=JsonUtil.toObject(formdata, Talk.class);
        } 
        try { 
            if(talk.getId()==null||"".equals(talk.getId().trim())){
                this.save(talk);
            }else{
            	Talk oldInfo=this.getBeanByCriteria(Talk.class, Restrictions.eq("id", talk.getId()));
            	if (oldInfo == null) {
                    throw new Exception("该条记录不存在");
                }
            	oldInfo.copyFrom(talk);
                this.update(oldInfo);
            }
        } catch (Exception e) {
            return ajax(Status.success,"保存/修改失败");
        }
        return ajax(Status.success,"success");
    }

    
    /**
     * 删除方法
     * @Title: remove 
     * @return 
     * String 
     * @author zrl
     * @since 2017年3月8日 V 1.0
     */
    public String removeattention() {
    	if (ids != null && !"".equals(ids)){
    		String[] idArrs = ids.split(",");
    		try {
    			attentionService.delete(Attention.class, idArrs);
            } catch (Exception e) {
                e.printStackTrace();
            }
    	}
        return ajax(Status.success, "success");
    }
    /**
     * 删除方法
     * @Title: remove 
     * @return 
     * String 
     * @author zrl
     * @since 2017年3月8日 V 1.0
     */
    public String removetalk() {
    	if (ids != null && !"".equals(ids)){
    		String[] idArrs = ids.split(",");
    		try {
    			talkService.delete(Talk.class, idArrs);
            } catch (Exception e) {
                e.printStackTrace();
            }
    	}
        return ajax(Status.success, "success");
    }
   //**********************set/get**********************//
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getFormdata() {
		return formdata;
	}
	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}
	public BasicInformation getInfo() {
		return info;
	}
	public void setInfo(BasicInformation info) {
		this.info = info;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public Talk getTalk() {
		return talk;
	}
	public void setTalk(Talk talk) {
		this.talk = talk;
	}
	public Attention getAttention() {
		return attention;
	}
	public void setAttention(Attention attention) {
		this.attention = attention;
	}

}
