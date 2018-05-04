package com.lingnet.hcm.action.salary;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.salary.SalaryDeptDictionary;
import com.lingnet.hcm.service.salary.SalaryDeptDictionaryService;
import com.lingnet.util.JsonUtil;

/**
 * 薪酬岗位字典
 * @ClassName: SalaryDeptAction 
 * @Description: 薪酬岗位字典 
 * @author zhanghj
 * @date 2017年5月16日 上午9:04:41 
 *
 */
public class SalaryDeptAction extends BaseAction {

    private static final long serialVersionUID = 8601228601683546158L;

    private SalaryDeptDictionary salaryDeptDictionary;
    @Resource(name="salaryDeptDictionaryService")
    private SalaryDeptDictionaryService salaryDeptDictionaryService;

    /**
     * @Title: 列表展现页 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String list() {
        return "list";
    }

    /**
     * @Title: 列表增加页 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String add() {
        return "add";
    }

    /**
     * @Title: 列表编辑页 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String edit() {
        salaryDeptDictionary = getBeanByCriteria(SalaryDeptDictionary.class, Restrictions.eq("id", id), Restrictions.eq("isDelete", 0));

        return "add";
    }

    /**
     * @Title: 薪酬岗位保存更新
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月13日 V 1.0
     */
    public String saveOrUpdate() {
        try {
            SalaryDeptDictionary dictionary = JsonUtil.toObject(formdata, SalaryDeptDictionary.class);
            if (dictionary != null) {
                if (StringUtils.isBlank(dictionary.getId())) {
                    SalaryDeptDictionary newFor = getBeanByCriteria(SalaryDeptDictionary.class, Restrictions.eq("name", dictionary.getName()), Restrictions.eq("isDelete", 0));
                    if (newFor !=null) {
                        return ajax(Status.error, dictionary.getName() + "岗位名称已经存在");
                    }
                    dictionary.setIsDelete(0);
                    save(dictionary);
                } else {
                    SalaryDeptDictionary newFor = getBeanByCriteria(SalaryDeptDictionary.class, Restrictions.ne("id", dictionary.getId()),
                            Restrictions.eq("name", dictionary.getName()), Restrictions.eq("isDelete", 0));
                    if (newFor !=null) {
                        return ajax(Status.error, dictionary.getName() + "岗位名称已经存在");
                    }
                    SalaryDeptDictionary newFormula = getBeanByCriteria(SalaryDeptDictionary.class, Restrictions.eq("id", dictionary.getId()),
                            Restrictions.eq("isDelete", 0));
                    if (newFormula == null) {
                        return ajax(Status.error, dictionary.getName() + "岗位不存在，已经被删除");
                    }
                    dictionary.setIsDelete(0);
                    newFormula.copyFrom(dictionary);
                    update(newFormula);
                }
            } else {
                return ajax(Status.success, "信息不能为空");
            }
        } catch (Exception e) {
            return ajax(Status.error, "发生异常");
        }

        return ajax(Status.success, "success");
    }

    /**
     * @Title: 薪酬岗位删除
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月24日 V 1.0
     */
    public String remove() {
        String[] idArray = ids.split(",");
        for (int i = 0, l = idArray.length; i < l; i++) {
            SalaryDeptDictionary deptDictionary = getBeanByCriteria(SalaryDeptDictionary.class, Restrictions.eq("id", idArray[i]), Restrictions.eq("isDelete", 0));
            if (deptDictionary != null) {
                deptDictionary.setIsDelete(1);
                this.update(deptDictionary);
            } else {
                return ajax(Status.success, "第"+(i+1)+"条记录不存在");
            }
        }

        return ajax(Status.success, "success");
    }

    /**
     * @Title: 获取薪酬岗位字典数据
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月30日 V 1.0
     */
    public String getDataList() {
        return ajax(Status.success, JsonUtil.Encode(salaryDeptDictionaryService.getDataList(searchData)));
    }

    public SalaryDeptDictionary getSalaryDeptDictionary() {
        return salaryDeptDictionary;
    }

    public void setSalaryDeptDictionary(SalaryDeptDictionary salaryDeptDictionary) {
        this.salaryDeptDictionary = salaryDeptDictionary;
    }

}
