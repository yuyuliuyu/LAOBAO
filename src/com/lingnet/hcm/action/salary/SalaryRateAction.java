package com.lingnet.hcm.action.salary;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.salary.Rate;
import com.lingnet.hcm.service.salary.RateService;
import com.lingnet.util.JsonUtil;

/**
 * 税率管理
 * @ClassName: SalaryRateAction 
 * @Description: 税率管理
 * @author zhanghj
 * @date 2017年3月6日 下午4:24:10 
 *
 */
public class SalaryRateAction extends BaseAction {

    private static final long serialVersionUID = 8601228601683546158L;

    private Rate rate;

    @Resource(name="rateService")
    private RateService rateService;

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
     * @Title: 修改页 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String edit() {
        rate = rateService.get(Rate.class, Restrictions.eq("id", id));
        return "edit";
    }

    /**
     * @Title: 获取所有税率设置
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月7日 V 1.0
     */
    public String getAllRateListData() {
    	return ajax(Status.success, JsonUtil.Encode(rateService.getAllRateListData()));
    }

    /**
     * @Title: 保存税率
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月23日 V 1.0
     */
    public String saveOrUpdate() {
        try {
            return ajax(Status.success, rateService.saveOrUpdate(formdata, griddata));
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }

    /**
     * @Title: 获取税率具体内容 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月23日 V 1.0
     */
    public String getRateStaticListData() {
        return ajax(Status.success, JsonUtil.Encode(rateService.getRateStaticListData(id)));
    }

    /**
     * @Title: 获取劳务所得税 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月23日 V 1.0
     */
    public String getLaoWuData() {
        Rate rate = rateService.get(Rate.class, Restrictions.eq("id", id));
        return ajax(Status.success, JsonUtil.Encode(rate==null? "" : rate));
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

}
