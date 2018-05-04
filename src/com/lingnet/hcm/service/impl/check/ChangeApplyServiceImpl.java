package com.lingnet.hcm.service.impl.check;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.entity.check.ChangeApply;
import com.lingnet.hcm.service.check.ChangeApplyService;

@Service("changeapplyservice")
public class ChangeApplyServiceImpl extends BaseServiceImpl<ChangeApply, String> implements ChangeApplyService{

    @Override
    public ChangeApply getchangeapply(String month, String day, String chackno,
            String state) { 
        this.get(ChangeApply.class, Restrictions.eq("", ""), Restrictions.eq("", ""));
        return null;
    }

}
