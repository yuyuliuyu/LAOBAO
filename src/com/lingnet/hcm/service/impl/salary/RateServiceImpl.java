package com.lingnet.hcm.service.impl.salary;

import java.util.ArrayList;
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
import com.lingnet.hcm.entity.salary.Rate;
import com.lingnet.hcm.entity.salary.Ratedata;
import com.lingnet.hcm.service.salary.RateDataService;
import com.lingnet.hcm.service.salary.RateService;
import com.lingnet.util.JsonUtil;

/**
 * 税率表
 * @ClassName: RateServiceImpl 
 * @Description: 税率表
 * @author zhanghj
 * @date 2017年3月21日 下午2:46:24 
 *
 */
@Service("rateService")
public class RateServiceImpl extends BaseServiceImpl<Rate, String>
              implements RateService {

    @Resource(name="rateDataService")
    private RateDataService rateDataService;

    @Override
    public List<Rate> getAllRateListData() {
        List<Rate> list = getList(Rate.class);

        return list;
    }

    @Override
    public List<Map<String, Object>> getRateStaticListData(String id) {
        List<Ratedata> ratedata = getList(Ratedata.class, Restrictions.eq("rateId", id));
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Ratedata r : ratedata) {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("id", r.getId());
            m.put("low", r.getLow());
            if (r.getHigh() == -1) {
                m.put("high", "正无穷");
            } else m.put("high", r.getHigh());
            m.put("rate", r.getRate());
            m.put("kcs", r.getKcs());
            list.add(m);
        }

        return list;
    }

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String saveOrUpdate(String formdata, String griddata) throws Exception {
        Rate rate = JsonUtil.toObject(formdata, Rate.class);
        if (rate != null) {
            this.update(rate);
            String id = rate.getId();
            List<Map<String, String>> list = JsonUtil.getMapList(griddata);
            List<Ratedata> ratedatas = new ArrayList<Ratedata>();
            for (int i =0, l = list.size(); i < l; i++) {
                Map<String, String> m = list.get(i);
                if (m.get("high").equals("正无穷")) {
                    m.put("high", "-1");
                }
                Ratedata bean = JsonUtil.toObject(JsonUtil.Encode(m), Ratedata.class);
                bean.setRateId(id);
                if ("removed".equals(m.get("_state"))) {
                    Ratedata ratedata = get(Ratedata.class, Restrictions.eq("rateId", id), Restrictions.eq("id", m.get("id")));
                    if (ratedata != null) {
                        rateDataService.delete(ratedata);
                    }
                } else if ("modified".equals(m.get("_state"))) {
                    Ratedata ratedata = get(Ratedata.class, Restrictions.eq("rateId", id), Restrictions.eq("id", m.get("id")));
                    if (ratedata != null) {
                        ratedata.copyFrom(bean);
                        rateDataService.update(ratedata);
                    } else throw new Exception("发生异常");
                    ratedatas.add(ratedata);
                } else if ("added".equals(m.get("_state"))) {
                    if (StringUtils.isBlank(rateDataService.save(bean))) {
                        throw new Exception("保存失败");
                    }
                    ratedatas.add(bean);
                }
            }
            Map<String, String> reInfo = compareMoney(ratedatas);
            if (reInfo.get("success").equals("false")) {
                throw new Exception(reInfo.get("info"));
            }
        }

        return "success";
    }

    /**
     * @Title: 所得额下限比较 
     * @param list
     * @return 
     * Map<String,String> 
     * @author zhanghj
     * @since 2017年3月23日 V 1.0
     */
    private Map<String, String> compareMoney(List<Ratedata> list) {
        Map<String, String> m = new HashMap<String, String>();
        for (int i =0, l = list.size(); i < l; i++) {
            if (i < l-1) {
                if (list.get(i).getLow() > list.get(i+1).getLow()) {
                    m.put("success", "false");
                    m.put("info", "应纳税所得额下限须按升序排列");

                    return m;
                }
            }
        }

        m.put("success", "true");
        m.put("info", "应纳税所得额下限须按升序排列");

        return m;
    }

}
