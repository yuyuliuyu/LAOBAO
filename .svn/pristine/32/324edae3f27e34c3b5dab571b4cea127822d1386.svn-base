package com.lingnet.qxgl.action.dictionary;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.common.service.BaseService;
import com.lingnet.qxgl.entity.QxDictionary;
import com.lingnet.qxgl.service.BackendDictionaryService;
import com.lingnet.util.JsonUtil;

/** 
 * @ClassName: BackendDictionaryAction 
 * @Description: 
 * @author 张明峰
 * @date 2013-7-31 上午10:02:29 
 *  
 */

public class BackendDictionaryAction extends BaseAction{

    private static final long serialVersionUID = 8449131240538811776L;
    
    @Resource(name="backendDictionaryService")
    private BackendDictionaryService backendDictionaryService;
    
    @SuppressWarnings("rawtypes")
    @Resource(name="baseService")
    private BaseService baseService;
    
    private QxDictionary dictionary;
    
    private String data;
    private String flg;
    
    /*跳转到展现页面*/
    public String list(){
        
        return "list";
    }
    
    /*获取字典展现信息*/
    @SuppressWarnings("rawtypes")
    public String getdata(){
        List data = backendDictionaryService.getdata();
        
        String json = JsonUtil.Encode(data);
        
        return ajax(Status.success,json);
    }
    
    /*根据id获取字典展现信息*/
    public String view(){
        String json = "";
        try{
            dictionary = backendDictionaryService.get(QxDictionary.class, Restrictions.eq("id", id));
            
            dictionary.setFlg(dictionary.getFlg());
            
            json = JsonUtil.Encode(dictionary);
        }catch(Exception e){
            return ajax(Status.error, e.toString());
        }
        return ajax(Status.success,json);
    }
    
    /*根据id删除字典展现信息*/
    public String remove(){
        dictionary = backendDictionaryService.get(QxDictionary.class, Restrictions.eq("id", id));
        
        if(dictionary.getIsSystem()){
            return ajax(Status.error,"该字典信息为系统内置信息，不允许删除，操作失败！");
        }
        
        baseService.operate("参数设置", "删除", dictionary.getName());
        
        backendDictionaryService.delete(id);
        
        return ajax(Status.success,"success");
    }
    
    /*保存修改字典信息*/
    // 整理代码将实现方法放到impl中  栾胜鹏  2014-10-20
    public String save(){
        String message="";
        
        try {
            message = backendDictionaryService.savedata(data,flg);
        } catch (Exception e) {
            return ajax(Status.error, e.toString().substring(e.toString().indexOf(":")+1));
        }
        if(message.equals("success")){
            return ajax(Status.success,"success");
        }else{
            return ajax(Status.error,message); 
        }
    }
    
    //////////////////////////////////////////////////////////////////
    public BackendDictionaryService getBackendDictionaryService() {
        return backendDictionaryService;
    }

    public void setBackendDictionaryService(
            BackendDictionaryService backendDictionaryService) {
        this.backendDictionaryService = backendDictionaryService;
    }

    public QxDictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(QxDictionary dictionary) {
        this.dictionary = dictionary;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }
}
