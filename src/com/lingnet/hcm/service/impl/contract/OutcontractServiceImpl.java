package com.lingnet.hcm.service.impl.contract;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.contract.OutcontractDao;
import com.lingnet.hcm.entity.LaborOutsourceApp;
import com.lingnet.hcm.entity.Outcontract;
import com.lingnet.hcm.service.contract.OutcontractService;
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;
import com.lingnet.util.ReflectionUtil;

/**
 * 
 * @ClassName: OutcontractServiceImpl 
 * @Description: 外包合同service 实现类
 * @author duanjj
 * @date 2017年4月26日 下午4:18:18 
 *
 */
@Service("outcontractService")
public class OutcontractServiceImpl extends BaseServiceImpl<Outcontract,String> implements
        OutcontractService {

    @Resource(name="outcontractDao")
    private OutcontractDao outcontractDao;
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public String listdata(Pager pager) {
        // 根据
        pager = this.findPager(Outcontract.class, pager);

        List<HashMap> mapList = ReflectionUtil.getMapList(pager.getResult());
        for (HashMap map : mapList) {
            if (map.get("contractorJia") != null) {
                Branch branch = this.get(Branch.class, map.get("contractorJia")
                        .toString());
                if (branch != null) {
                    map.put("contractorNameJia", branch.getFzx());
                }
            }
            if (map.get("contractorYi") != null) {
                Branch branch = this.get(Branch.class, map.get("contractorYi")
                        .toString());
                if (branch != null) {
                    map.put("contractorNameYi", branch.getFzx());
                }
            }
            if (map.get("applyId") != null) {
                LaborOutsourceApp app = this.get(LaborOutsourceApp.class, map.get("applyId")
                        .toString());
                if (app != null) {
                    map.put("applyCode", app.getProjectCode());
                    if(app.getApplyCom()!=null){
                        Branch branch = this.get(Branch.class, app.getApplyCom());
                        if (branch != null) {
                            map.put("applyComp", branch.getFzx());
                        }
                    }
                }
            }
        }
        HashMap result=new HashMap();
        result.put("data", mapList);
        result.put("total", pager.getTotalCount());
        return JsonUtil.toJson(result);  
    }

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String saveOrUpdata(String formdata) throws Exception {
        Outcontract bean = JsonUtil.toObject(formdata, Outcontract.class);
        if(bean.getId()!=null&&!"".equals(bean.getId())){
            Outcontract oldBean = this.get(Outcontract.class, bean.getId());
            if(oldBean==null){
                throw new Exception("数据已经不存在，请关闭页面！");
            }
            oldBean.copyFrom(bean);
            outcontractDao.update(oldBean);
        }else{
            //新增
            outcontractDao.save(bean);
            
        }
        return "success";
    }

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String deleteByIds(String id) throws Exception {
        String[] ids = id.split(",");
        for (int i = 0; i < ids.length; i++) {
            Outcontract bean = this.get(Outcontract.class, ids[i]);
            
            outcontractDao.delete(bean);
        }
        return "success";
    }

}
