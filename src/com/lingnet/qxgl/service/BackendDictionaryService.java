package com.lingnet.qxgl.service;

import java.util.List;

import com.lingnet.common.service.BaseService;
import com.lingnet.qxgl.entity.QxDictionary;


/** 
 * @ClassName: BackendDictionaryService 
 * @Description: 字典 
 * @author 张明峰
 * @date 2013-7-31 上午8:16:20 
 *  
 */

public interface BackendDictionaryService extends BaseService<QxDictionary,String> {

    /*根据类型获取所有的对应字典内容*/
    public List<QxDictionary> getAllByType(String type);
    
    @SuppressWarnings("rawtypes")
    public List getdata();
    
    
    public QxDictionary getAllByName(String name);

    /** 
     * @Title: 保存修改字典信息 
     * @param data 
     * void 
     * @author 栾胜鹏 代码整理实现方法放到impl中 
     * @param flg 
     * @since 2014-10-20 V 1.0
     */
    public String savedata(String data, String flg)throws Exception;
}
