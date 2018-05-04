package com.lingnet.qxgl.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.qxgl.dao.BackendDictionaryDao;
import com.lingnet.qxgl.dao.BackendResourceDao;
import com.lingnet.qxgl.entity.QxDictionary;
import com.lingnet.qxgl.entity.QxResource;
import com.lingnet.qxgl.service.BackendDictionaryService;
import com.lingnet.util.JsonUtil;


/** 
 * @ClassName: BackendDictionaryServiceImpl 
 * @Description: 
 * @author 张明峰
 * @date 2013-7-31 上午8:21:53 
 *  
 */
@Service("backendDictionaryService")
public class BackendDictionaryServiceImpl extends BaseServiceImpl<QxDictionary, String>
                                            implements BackendDictionaryService{

    @Resource(name = "backendResourceDao")
    private BackendResourceDao backendResourceDao;
    /*以下是代码整理：将放在action中的实现方法放到impl中  栾胜鹏*/
    
    /**
     * 保存修改字典信息
     */
    @SuppressWarnings({ "static-access", "rawtypes" })
    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String savedata(String data,String flg) throws Exception {
        JsonUtil jsonUtils = new JsonUtil();
        
        QxDictionary dictionary = new QxDictionary();
        
        Map listdata = jsonUtils.parseProperties(data);
        
        if(listdata.get("id").toString()!=null&&!listdata.get("id").toString().equals("")&&!listdata.get("id").toString().equals("null")){
            //修改字典信息
            QxDictionary qd =  this.getAllByName(listdata.get("type").toString());
            if(qd!=null&&!qd.getId().equals(listdata.get("id"))){
                throw new Exception("该字典名称已经存在，请重新录入！");
            }
            String oldflg = qd.getFlg();
            dictionary = this.get(QxDictionary.class, Restrictions.eq("id", listdata.get("id").toString()));
            dictionary.setFlg(flg);
            dictionary.setName(listdata.get("name").toString());
            dictionary.setType(listdata.get("type").toString());
            dictionary.setIsSystem(false);
            Date now=new Date(System.currentTimeMillis());
            dictionary.setModifyDate(now);
            String[] types = {"生产","质检","车间","应收应付","资产","成本","财务"};
            if(listdata.get("type").toString().equals("进销存")){
            if(!oldflg.equals(flg)){
                    //当进销存的开关为T时，types对应的模块要关闭，所以要将flg置反
                    if(flg.equals("T")){
                        flg  = "F";
                    }else{
                        flg = "T";
                    }
                    onlyShow(types,flg);
                }
             }else{
                for(int i = 0 ; i<types.length; i++){
                    if(listdata.get("type").equals(types[i])){
                        if(!oldflg.equals(flg)){
                            resName(listdata.get("type").toString(),flg); 
                        }
                    }
                }
            }
            this.update(dictionary);
            
            //operate("参数设置", "编辑", dictionary.getName());
        }else{
            //新增字典信息
            QxDictionary qd =  this.getAllByName(listdata.get("name").toString());
            if(qd!=null){
                throw new Exception("该字典名称已经存在，请重新录入！");
            }
            dictionary.setFlg(flg);
            dictionary.setIsSystem(false);
            dictionary.setName(listdata.get("name").toString());
            dictionary.setType(listdata.get("type").toString());
            Date now=new Date(System.currentTimeMillis());
            dictionary.setCreateDate(now);
            String[] types = {"生产","质检","车间","应收应付","资产","成本","财务"};
            if(listdata.get("type").toString().equals("进销存")){
                //当进销存的开关为T时，types对应的模块要关闭，所以要将flg置反
                if(flg.equals("T")){
                    flg  = "F";
                }else{
                    flg = "T";
                }
                onlyShow(types,flg);
            }else{
                for(int i = 0 ; i<types.length; i++){
                    if(listdata.get("type").equals(types[i])){
                       
                        resName(listdata.get("type").toString(),flg);
                    }
                } 
            }
            this.save(dictionary);
            
            //operate("参数设置", "增加", dictionary.getName());
        }
        return "success";
    }
    
    /*以上是代码整理：将放在action中的实现方法放到impl中  栾胜鹏*/
    
    @Resource(name="backendDictionaryDao")
    private BackendDictionaryDao backendDictionaryDao;
    
    /** 
     * 根据类型获取所有的对应字典内容
     */
    @Override
    public List<QxDictionary> getAllByType(String type) {
        
        return backendDictionaryDao.getAllByType(type);
        
    }
    
    /** 
     * 根据类型获取所有的对应字典内容
     */
    @Override
    public List<HashMap<String, String>> getdata(){
        
        List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        
        List<QxDictionary> list = this.getList(QxDictionary.class);
        
        for(int i=0;i<list.size();i++){
            
            HashMap<String,String> record = new HashMap<String,String>();
            
            QxDictionary diction = list.get(i);
            
            record.put("id", diction.getId());
            record.put("name", diction.getName());
            record.put("type", diction.getType());
            record.put("flg", diction.getFlg());
            
            SimpleDateFormat mat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            record.put("time", mat.format(diction.getCreateDate()));
            
            data.add(record);
        }
        
        return data;
    }
    
    /** 
     * 当为“进销存”时调用递归之前的过度方法
     * @Title: onlyShow 
     * @param types 
     * void 
     * @author 栾胜鹏
     * @since 2014-12-1 V 1.0
     */
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    private void onlyShow(String[] types,String flg) {
        
       for(int i = 0 ; i < types.length ;i++){
           String pname = types[i];
           QxDictionary qd =  this.getAllByName(pname);
           //当标志相同的时候不需要重复操作
           if(qd.getFlg().equals(flg)){
               continue;
           }else{
               qd.setFlg(flg);
               update(qd);
               resName(pname,flg);
           }
       }
        
    }


    /** 递归方法 当flg为"T"时则对应的模块显示否则不显示
     * @Title: resName 
     * @param pname 
     * void 
     * @author 栾胜鹏
     * @since 2014-12-1 V 1.0
     */
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    private void resName(String pname,String flg) {
        QxResource qr = get(QxResource.class,  Restrictions.eq("resourcename", pname));
        //设置模块是否显示
        if(flg.equals("T")){
            qr.setState("1");
        }else{
            qr.setState("0");
        }
        backendResourceDao.update(qr);
        List<QxResource> list = this.getList(QxResource.class, Restrictions.eq("presource", pname));
        if(list.size()>0){
            for(QxResource cqr:list){
                resName(cqr.getResourcename(),flg);
            }
        }else{
            return;
        }
        
    }
    
    /*根据用户名获取字典信息*/
    public QxDictionary getAllByName(String name){
        
        return backendDictionaryDao.getAllByName(name);
    }
    
    @Resource(name="backendDictionaryDao")
    public void setBackendDictionaryDao(BackendDictionaryDao backendDictionaryDao) {
        super.setBaseDao(backendDictionaryDao);
        this.backendDictionaryDao = backendDictionaryDao;
    }
    
    public BackendDictionaryDao getBackendDictionaryDao() {
        return backendDictionaryDao;
    }
}
