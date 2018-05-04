package com.lingnet.hcm.service.impl.salary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.salary.SalaryOrgInsuranceDao;
import com.lingnet.hcm.entity.salary.SalaryOrgInsurance;
import com.lingnet.hcm.entity.salary.SalaryPerInsurance;
import com.lingnet.hcm.service.salary.SalaryOrgInsuranceService;
import com.lingnet.hcm.service.salary.SalaryPerInsuranceService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

/**
 * 团体商业保险缴费
 * @ClassName: SalaryOrgInsuranceServiceImpl 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年6月14日 下午4:32:25 
 *
 */
@Service("salaryOrgInsuranceService")
public class SalaryOrgInsuranceServiceImpl extends BaseServiceImpl<SalaryOrgInsurance, String>
              implements SalaryOrgInsuranceService {

    @Resource(name="salaryPerInsuranceService")
    private SalaryPerInsuranceService salaryPerInsuranceService;
    @Resource(name="salaryOrgInsuranceDao")
    private SalaryOrgInsuranceDao salaryOrgInsuranceDao;

    @Override
    public String remove(String ids) {
        String[] idsArray = ids.split(",");
        List<SalaryOrgInsurance> list = new ArrayList<SalaryOrgInsurance>();
        for (int i = 0; i < idsArray.length; i++) {
            SalaryOrgInsurance insurance = get(SalaryOrgInsurance.class, Restrictions.eq("id", idsArray[i]),
                    Restrictions.eq("isDelete", 0));
            if (insurance == null) {
                return "选中的第"+(i+1)+"条记录不存在，已经被删除";
            }
            insurance.setIsDelete(1);
            list.add(insurance);
        }
        if (list.size() > 0) saveBatch(list);

        return "success";
    }

    @Override
    public String saveOrUpdate(String formdata) {
        SalaryOrgInsurance insurance = JsonUtil.toObject(formdata, SalaryOrgInsurance.class);
        if (StringUtils.isBlank(insurance.getId())) {
            insurance.setIsDelete(0);
            String id = save(insurance);
            if (StringUtils.isBlank(id)) {
                return "保存失败";
            }
        } else {
            SalaryOrgInsurance orgInsurance = get(SalaryOrgInsurance.class,
                    Restrictions.eq("id", insurance.getId()),
                    Restrictions.eq("isDelete", 0));
            if (orgInsurance != null) {
                orgInsurance.copyFrom(insurance);
                update(orgInsurance);
            } else
                return "该信息不存在";
        }

        return "success";
    }

    @Override
    public Map<String, Object> getListData(String searchData, Pager pager) {
        return salaryOrgInsuranceDao.getListData(searchData, pager);
    }

    @Override
    public String saveOrUpdateToPer(String formdata) {
        SalaryPerInsurance insurance = JsonUtil.toObject(formdata, SalaryPerInsurance.class);
        if (StringUtils.isBlank(insurance.getId())) {
            insurance.setIsDelete(0);
            String id = salaryPerInsuranceService.save(insurance);
            if (StringUtils.isBlank(id)) {
                return "保存失败";
            }
        } else {
            SalaryPerInsurance orgInsurance = get(SalaryPerInsurance.class,
                    Restrictions.eq("id", insurance.getId()),
                    Restrictions.eq("isDelete", 0));
            if (orgInsurance != null) {
                orgInsurance.copyFrom(insurance);
                salaryPerInsuranceService.update(orgInsurance);
            } else
                return "该信息不存在";
        }

        return "success";
    }

    @Override
    public String removeToPer(String ids) {
        String[] idsArray = ids.split(",");
        List<SalaryPerInsurance> list = new ArrayList<SalaryPerInsurance>();
        for (int i = 0; i < idsArray.length; i++) {
            SalaryPerInsurance insurance = get(SalaryPerInsurance.class, Restrictions.eq("id", idsArray[i]),
                    Restrictions.eq("isDelete", 0));
            if (insurance == null) {
                return "选中的第"+(i+1)+"条记录不存在，已经被删除";
            }
            insurance.setIsDelete(1);
            list.add(insurance);
        }
        if (list.size() > 0) salaryPerInsuranceService.saveBatch(list);

        return "success";
    }

    @Override
    public Map<String, Object> getPerListData(String searchData, Pager pager) {
        return salaryOrgInsuranceDao.getPerListData(searchData, pager);
    }

    @Override
    public Map<String, Object> getAllPersonalListData(String searchData, Pager pager) {
        return salaryOrgInsuranceDao.getAllPersonalListData(searchData, pager);
    }

}
