package com.lingnet.hcm.service.post;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.PostDuty;
import com.lingnet.util.Pager;

public interface PostService extends BaseService<PostDuty,String> {

    /**
     * 职务族保存方法
     * @Title: dutySave 
     * @param formdata
     * @return
     * @throws Exception 
     * String 
     * @author duanjj
     * @since 2017年3月29日 V 1.0
     */
    public String dutySave(String formdata) throws Exception;

    /**
     * 职务族信息列表
     * @Title: treeList 
     * @return 
     * String 
     * @author duanjj
     * @since 2017年3月29日 V 1.0
     */
    public String treeList();

    /**
     * 岗位信息列表
     * @Title: positionList 
     * @return 
     * String 
     * @author duanjj
     * @param id 
     * @param pager 
     * @since 2017年3月29日 V 1.0
     */
    public String positionList(String id, Pager pager);

    /**
     * 职务族删除
     * @Title: deleteDuty 
     * @param id
     * @return 
     * String 
     * @author duanjj
     * @since 2017年3月29日 V 1.0
     */
    public String deleteDuty(String id);

    /**
     * 岗位增加修改保存方法
     * @Title: postionSave 
     * @param formdata
     * @return
     * @throws Exception 
     * String 
     * @author duanjj
     * @since 2017年3月30日 V 1.0
     */
    public String postionSave(String formdata) throws Exception;

    /**
     * 岗位删除
     * @Title: postionDelete 
     * @param id
     * @return 
     * String 
     * @author duanjj
     * @since 2017年3月30日 V 1.0
     */
    public String postionDelete(String id);

    /**
     * 部门岗位列表
     * @Title: positionDeptList 
     * @param id
     * @return 
     * String 
     * @author duanjj
     * @since 2017年3月31日 V 1.0
     */
    public String positionDeptList(String id, Pager pager) ;

    /**
     * 部门岗位增加修改保存方法
     * @Title: postionDeptSave 
     * @param formdata
     * @return 
     * String 
     * @author duanjj
     * @since 2017年4月7日 V 1.0
     */
    public String postionDeptSave(String formdata) throws Exception ;

    /**
     * 
     * @Title: 根据职务族查询部门岗位
     * @param id
     * @return 
     * String 
     * @author duanjj
     * @since 2017年4月7日 V 1.0
     */
    public String postionDeptDelete(String id);

    /**
     * 
     * @Title: depPositionList 
     * @param id
     * @param pager
     * @return 
     * String 
     * @author duanjj
     * @since 2017年4月7日 V 1.0
     */
    public String depPositionList(String id, Pager pager);

    /**
     * 
     * @Title: personList 
     * @param id
     * @param pager
     * @return 
     * String 
     * @author duanjj
     * @since 2017年4月11日 V 1.0
     */
    public String personList(String id, Pager pager);

}
