package com.lingnet.hcm.dao.impl.post;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.dao.post.PositionGradeDao;
import com.lingnet.hcm.entity.PositionGrade;

/**
 * 
 * @ClassName: PositionGradeDaoImpl 
 * @Description: 职等DAO实现类
 * @author duanjj
 * @date 2017年4月14日 上午9:53:47 
 *
 */
@Repository("positionGradeDao")
public class PositionGradeDaoImpl extends BaseDaoImpl<PositionGrade,String> implements
        PositionGradeDao {

}
