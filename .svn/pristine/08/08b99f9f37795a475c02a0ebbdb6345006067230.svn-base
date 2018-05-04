package com.lingnet.qxgl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lingnet.common.service.BaseService;
import com.lingnet.qxgl.entity.QxDepartment;
import com.lingnet.util.Pager;


/** 
 * @ClassName: BackendDepService 
 * @Description: 部门业务接口类
 * @author 张明峰
 * @date 2013-6-17 上午9:08:08 
 *  
 */


public interface BackendDepService extends BaseService<QxDepartment,String> {
    
    public List<HashMap<String, String>> getAll();
    
    public List<HashMap<String, String>> getTreeAll();
    public List<HashMap<String, String>> getTreeAll2();
    
    public List<HashMap<String, String>> getTreeList();
    
    //public QxDepartment getByName(String name);

    /** 
     * 添加机构部门
     * @Title: saveOrUpdata 
     * @param id
     * @param name
     * @param description
     * @param parent
     * @param child
     * @return 
     * String 
     * @author 栾胜鹏 代码整理实现方法放到impl中 
     * @param cid 
     * @since 2014-10-22 V 1.0
     */
    public String saveOrUpdata(String id, String name, String description,
            String parent, String child, String cid);
    /**
     * 获取部门的treegrid数据
     * @Title: treeGridData 
     * @return 
     * String 
     * @author lsp		
     * @param map 
     * @param pager 
     * @since 2016-8-3 V 1.0
     */
    public String treeGridData(HashMap<String, String> map);

    @SuppressWarnings("rawtypes")
    public List getTreeAll1(String cid);

    public String saveOrUpdata(String formData, String child)throws Exception;
    /**
     * 删除
     * @Title: remove 
     * @param ids
     * @return 
     * String 
     * @author lsp		
     * @since 2016-8-11 V 1.0
     */
    public String remove(Pager pager,String ids)throws Exception;

    
    
    /**
     * 获取所有的功能部门（科室）
     * @Title: findAllDepartment 
     * @return 
     * List<QxDepartment>
     * @author yinzl
     * @since 2016-8-7 V 1.0
     */
    public String getAllDepartment(Pager pager,String key);
    /**
     *获取所有功能科室
     * @Title: getSyks 
     * @return 
     * String 
     * @author lsp      
     * @since 2016-8-16 V 1.0
     */
    public String getStyks(String key);
    /**
     *获取所有科室
     * @Title: getSyks 
     * @return 
     * String 
     * @author lsp      
     * @since 2016-8-16 V 1.0
     */
    public String getAllks(String key);
    /**
     * 获取开单医师  所有的销售 本分中心的客服
     * @Title: getKdys 
     * @param key
     * @return 
     * String 
     * @author lsp		
     * @since 2016-8-18 V 1.0
     */
    public String getKdys(String key);
    /**
     * 获取加项医师 本分中心所有的功能科室的
     * @Title: getJxys 
     * @param key
     * @return 
     * String 
     * @author lsp		
     * @since 2016-8-18 V 1.0
     */
    public String getJxys(String key);
    /**
     * 递归获取部门id
     * @Title: getQids 
     * @param id
     * @param arrayList
     * @return 
     * ArrayList 
     * @author lsp		
     * @since 2016-8-19 V 1.0
     */
    @SuppressWarnings("rawtypes")
    public ArrayList getQids(Object id, ArrayList arrayList);
    
    /**
     * 判断是否为销售部
     * @Title: isXsb 
     * @param pid
     * @return 
     * String 
     * @author mbx
     * @since 2016-8-23 V 1.0
     */
    public String isXsb(String pid,String did);
    /**
     * 获取分中心下某部门下的所有人员信息
     * @Title: getCenDepUserData 
     * @param pager
     * @param map
     * @return 
     * String 
     * @author lsp		
     * @since 2016-8-24 V 1.0
     */
    public String getCenDepUserData(Pager pager, HashMap<String, String> map);
    
    /**
     * 
     * @Title: getDepListByBranchId 
     * @param branchId
     * @return 
     * @author wangqiang
     * @since 2017年4月19日 V 1.0
     */
    public String getDepListByBranchId(String branchId);
}
