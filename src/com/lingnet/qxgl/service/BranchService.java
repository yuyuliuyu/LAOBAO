package com.lingnet.qxgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lingnet.common.service.BaseService;
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.qxgl.entity.QxUser;
import com.lingnet.util.Pager;
/**
 * 分区service
 * @ClassName: BranchService 
 * @Description: TODO 
 * @author lsp
 * @date 2016-8-3 下午1:52:49 
 *
 */
public interface BranchService extends BaseService<Branch, String>{

    /**
     * 获取分区树
     * @Title: treeList 
     * @return 
     * String 
     * @author lsp		
     * @since 2016-8-3 V 1.0
     */
    public String treeList();
    /**
     * 获取分区树
     * @Title: treeList 
     * @return 
     * String 
     * @author lsp		
     * @since 2016-8-3 V 1.0
     */
    public String getBranchList();
    /**
     * 获取分区数据
     * @Title: getTreedata 
     * @param pager
     * @return 
     * String 
     * @author lsp		
     * @since 2016-8-4 V 1.0
     */
    public String getTreedata(Pager pager);
    /**
     * 保存
     * @Title: saveOrUpdata 
     * @param formdata
     * @param child
     * @return
     * @throws Exception 
     * String 
     * @author lsp		
     * @since 2016-8-17 V 1.0
     */
    public String saveOrUpdata(String formdata,
            String child,String img,QxUser qxuser)throws Exception;
    /**
     * 删除
     * @Title: remove 
     * @param id
     * @return 
     * String 
     * @author lsp		
     * @since 2016-8-4 V 1.0
     */
    public String remove(String id)throws Exception ;
    
    /**
     * 查询分中心信息
     * @Title: getBranchData 
     * @return 
     * String 
     * @author mbx
     * @since 2016-8-5 V 1.0
     */
    public String getBranchData();
    /**
     * 获取分中心部门
     * @Title: depData 
     * @param cid
     * @return 
     * String 
     * @author lsp		
     * @param pager 
     * @since 2016-8-11 V 1.0
     */
    public String depData(String cid);
    /**
     * 保存分中心部门表
     * @Title: cenDepSaveOrUpdate 
     * @return 
     * String 
     * @author lsp      
     * @since 2016-8-11 V 1.0
     */
    public String cenDepSaveOrUpdate(String formdata,QxUser userinfo)throws Exception ;
    /**
     * 删除分中心部门
     * @Title: depRemove 
     * @param id 分中心部门id
     * @param cid 分中心id
     * @return 
     * String 
     * @author lsp		
     * @since 2016-8-12 V 1.0
     */
    public String depRemove(String id, String cid)throws Exception;
    
    /**
     * 获取分中心信息,进行分页展示
     * @Title: getBranchData 
     * @param pager
     * @param key
     * @return 
     * String 
     * @author mbx
     * @since 2016年9月27日 V 1.0
     */
    public String getBranchData(Pager pager,String key);
    /**
     * 
     * @Title: setDefault 
     * @param cid
     * @return 
     * String 
     * @author xuhp
     * @since 2016-11-7 V 1.0
     */
    public String setDefault(String cid);
    /**
     * 获取当前用户有数据权限的部门树 数据
     * @Title: getTreeListByUser 
     * @return 
     * String 
     * @author xuhp
     * @since 2017-3-16 V 1.0
     */
    public String getTreeListByUser();
    /**
     * 获取当前用户有数据权限的部门树 数据
     * @Title: getTreeListByUser 
     * @return 
     * String 
     * @author xuhp
     * @since 2017-3-16 V 1.0
     */
    public String getTreecompByUser();
    
    /**
     * 获取所有数据权限的部门树数据
     * @Title: getAllTreeList 
     * @return 
     * @author wangqiang
     * @since 2017年3月27日 V 1.0
     */
    public String getAllTreeList();
    /**
     * 
     * @Title: 历史沿革数据
     * @return 
     * List<HashMap> 
     * @author duanjj
     * @param flg  1部门 2公司
     * @since 2017年4月12日 V 1.0
     */
    @SuppressWarnings("rawtypes")
    public List<HashMap> findHistortData(String flg,String deptid);
    /**
     * 公司树
     * @Title: compList 
     * @return 
     * String 
     * @author duanjj
     * @since 2017年4月14日 V 1.0
     */
    public String compList();
    /**
     * 获取个人可看到的组织架构（带人员）
     * @Title: getTreeShowByUser 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年7月4日 V 1.0
     */
    public String  getTreeShowByUser();

    /**
     * @Title: 获取当前用户数据权限树
     * @param userId
     * @return 
     * Object 
     * @author zhanghj
     * @since 2017年8月6日 V 1.0
     */
    public List<Map<String, Object>> getDataAuth(String userId);
}
