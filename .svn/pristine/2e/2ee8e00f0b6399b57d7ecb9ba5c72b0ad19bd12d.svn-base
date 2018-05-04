package com.lingnet.hcm.service.impl.salary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.salary.SalaryPartDao;
import com.lingnet.hcm.entity.salary.PartSalary;
import com.lingnet.hcm.entity.salary.SalaryPart;
import com.lingnet.hcm.service.salary.PartSalaryService;
import com.lingnet.hcm.service.salary.SalaryPartService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

/**
 * 工资条与薪资项目关联表
 * @ClassName: SalaryPartServiceImpl 
 * @Description: 工资条与薪资项目关联表 
 * @author zhanghj
 * @date 2017年4月6日 下午4:25:44 
 *
 */
@Service("salaryPartService")
public class SalaryPartServiceImpl extends BaseServiceImpl<SalaryPart, String>
              implements SalaryPartService {

    @Resource(name="partSalaryService")
    private PartSalaryService partSalaryService;
    @Resource(name="salaryPartDao")
    private SalaryPartDao salaryPartDao;

    @Override
    public Map<String, Object> getSalaryWageListData(String id, String searchData, Pager pager) {
        pager = findPager(SalaryPart.class, pager, Restrictions.eq("groupWageAndTypeId", id), Restrictions.eq("isDelete", 0));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", pager.getResult());
        map.put("total", pager.getTotalCount());

        return map;
    }

    @Override
    public Map<String, Object> getSalaryWageItemsListData(String id, String searchData) {
        return salaryPartDao.getWageTypeListData(id, searchData);
    }

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String saveOrUpdate(String formdata, String griddata) throws Exception {
        SalaryPart items = JsonUtil.toObject(formdata, SalaryPart.class);
        if (items != null) {
            String id ="";
            if (StringUtils.isBlank(items.getId())) {
                SalaryPart itemsMore = get(SalaryPart.class,
                        Restrictions.eq("companyId", items.getCompanyId()),
                        Restrictions.eq("groupWageAndTypeId", items.getGroupWageAndTypeId()),
                        Restrictions.eq("isDelete", 0),
                        Restrictions.eq("name", items.getName()));
                if (itemsMore != null) {
                    return "存在名称相同的工资条";
                }

                // 查找默认
                SalaryPart def = get(SalaryPart.class,
                        Restrictions.eq("companyId", items.getCompanyId()),
                        Restrictions.eq("groupWageAndTypeId", items.getGroupWageAndTypeId()),
                        Restrictions.eq("isDefault", 1),
                        Restrictions.eq("isDelete", 0));
                if (def == null) {
                    items.setIsDefault(1);
                } else {
                    items.setIsDefault(0);
                }
                items.setIsDelete(0);
                id = save(items);
                if (StringUtils.isBlank(id)) {
                    throw new Exception("保存失败");
                }
            } else {
                SalaryPart itemsMore = get(SalaryPart.class, Restrictions.eq("companyId", items.getCompanyId()),
                        Restrictions.ne("id", items.getId()),
                        Restrictions.eq("groupWageAndTypeId", items.getGroupWageAndTypeId()),
                        Restrictions.eq("isDelete", 0),
                        Restrictions.eq("name", items.getName()));
                if (itemsMore != null) {
                    return "存在名称相同的工资条";
                }

                // 查找默认
                SalaryPart def = get(SalaryPart.class,
                        Restrictions.ne("id", items.getId()),
                        Restrictions.eq("groupWageAndTypeId", items.getGroupWageAndTypeId()),
                        Restrictions.eq("isDefault", 1),
                        Restrictions.eq("isDelete", 0));
                if (def == null) {
                    items.setIsDefault(1);
                } else {
                    items.setIsDefault(0);
                }
                SalaryPart newItems = get(SalaryPart.class,
                        Restrictions.eq("id", items.getId()),
                        Restrictions.eq("isDelete", 0));
                if (newItems == null) {
                    return "该工资条不存在";
                }
                items.setIsDelete(0);
                newItems.copyFrom(items);
                update(newItems);
                id = newItems.getId();
            }
            List<Map<String, String>> list = JsonUtil.getMapList(griddata);
            for (int i =0, l = list.size(); i < l; i++) {
                Map<String, String> m = list.get(i);
                PartSalary bean = JsonUtil.toObject(JsonUtil.Encode(m), PartSalary.class);
                bean.setPartId(id);
                if ("removed".equals(m.get("_state"))) {
                    PartSalary itemAndType = get(PartSalary.class,
                            Restrictions.eq("id", m.get("id")), Restrictions.eq("partId", id), Restrictions.eq("isDelete", 0));
                    if (itemAndType != null) {
                        itemAndType.setIsDelete(1);
                        partSalaryService.update(itemAndType);
                    }
                } else if ("modified".equals(m.get("_state"))) {
                    PartSalary itemAndType = get(PartSalary.class,
                            Restrictions.eq("id", m.get("id")), Restrictions.eq("partId", id), Restrictions.eq("isDelete", 0));
                    if (itemAndType != null) {
                        itemAndType.copyFrom(bean);
                        partSalaryService.update(itemAndType);
                    } else throw new Exception("发生异常");
                } else if ("added".equals(m.get("_state"))) {
                    bean.setIsDelete(0);
                    String result = partSalaryService.save(bean);
                    if (StringUtils.isBlank(result))
                        throw new Exception("保存失败");
                }
            }
        } else {
            return "信息不能为空";
        }

        return "success";
    }
    
    @Override
    public String remove(String ids) {
        String[] idss = ids.split(",");
        if (idss.length > 0 && !StringUtils.isBlank(idss[0])) {
            for (int i =0, l = idss.length; i < l; i++) {
                SalaryPart items = get(SalaryPart.class, Restrictions.eq("id", idss[i]), Restrictions.eq("isDelete", 0));
                if (null == items) {
                    return "选中的第 "+(i+1)+" 个工资条不存在";
                }
                items.setIsDelete(1);
                update(items);
            }
        }

        return "success";
    }

    @Override
    public String saveDefault(String id) {
        SalaryPart salaryPart = get(SalaryPart.class, Restrictions.eq("id", id), Restrictions.eq("isDelete", 0));
        if (salaryPart != null) {
            SalaryPart def = get(SalaryPart.class,
                    Restrictions.ne("id", salaryPart.getId()),
                    Restrictions.eq("isDefault", 1),
                    Restrictions.eq("isDelete", 0));
            if (def != null) {
                def.setIsDefault(0);
            }
            update(def);
            salaryPart.setIsDefault(1);

            update(salaryPart);
        } else {
            return "该条信息不存在";
        }

        return "success";
    }

}
