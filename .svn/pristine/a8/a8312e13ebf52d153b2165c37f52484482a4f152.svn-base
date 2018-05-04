package com.lingnet.hcm.action.salary;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.salary.SalaryBaseDictionary;
import com.lingnet.hcm.entity.salary.SalaryDeptDictionary;
import com.lingnet.util.JsonUtil;

/**
 * 基础工资
 * @ClassName: SalaryBaseAction 
 * @Description: 基础工资 
 * @author zhanghj
 * @date 2017年5月16日 上午10:12:46 
 *
 */
public class SalaryBaseAction extends BaseAction {

    private static final long serialVersionUID = 8601228601683546158L;

    private SalaryBaseDictionary salaryBaseDictionary;

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
        salaryBaseDictionary = getBeanByCriteria(SalaryBaseDictionary.class, Restrictions.eq("id", id), Restrictions.eq("isDelete", 0));

        return "add";
    }

    /**
     * @Title: 基础工资保存更新
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
     * @Title: 基础工资删除
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
     * @Title: 获取基础工资字典数据
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月30日 V 1.0
     */
    public String getDataList() {
        List<SalaryBaseDictionary> dictionaries = getBeanListByCriteria(SalaryBaseDictionary.class, Restrictions.eq("isDelete", 0));
        return ajax(Status.success, JsonUtil.Encode(dictionaries));
    }

    public SalaryBaseDictionary getSalaryBaseDictionary() {
        return salaryBaseDictionary;
    }

    public void setSalaryBaseDictionary(SalaryBaseDictionary salaryBaseDictionary) {
        this.salaryBaseDictionary = salaryBaseDictionary;
    }

}
