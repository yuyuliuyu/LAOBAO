package com.lingnet.hcm.service.impl.laobao;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.laobao.WupinhistoryDao;
import com.lingnet.hcm.entity.laobao.History;
import com.lingnet.hcm.service.laobao.WupinhistoryService;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.util.Pager;


@Service("wupinhistoryService")
public  class WupinhistoryServiceImpl extends BaseServiceImpl<History, String> implements WupinhistoryService{

	
	@Resource(name="wupinhistoryDao")
	private WupinhistoryDao wupinhistoryDao;
	
	//人员基本信息list页面
	@SuppressWarnings({ "rawtypes" })
	@Override
	public HashMap getPersonByDepId(Pager pager, String searchData,String ids) {
																																			
		return wupinhistoryDao.getPersonByDepId(pager, searchData,ids);
	}

	/*以下是代码整理：将放在action中的实现方法放到impl中  栾胜鹏*/
/*    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public void saveOrUpdate(String id, String ids) throws Exception {
    	List<Map<String, String>> listMap =null;
        List<Map<String, String>> listData =null;
        List<Map<String, String>> listFun =null;
        
        Map map = new HashMap();
        
        String[] sourceStrArray=ids.split(",");
        BiaoZhun biaozhun =new BiaoZhun();
        for(int i=0;i<sourceStrArray.length;i++){
        	biaozhun.setWp_id(sourceStrArray[i]);
        	biaozhun.setIssue_id(id);
        	this.save(biaozhun);
        }

    }*/
	@Override
	public HSSFWorkbook exportInfos() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Pager findPagerByOrder(Class<?> clazz, Pager pager, Order order,
			Criterion... criterions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public History get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> O get(Class<O> clazz, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public History get(Criterion c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> O get(Class<O> clazz, Object... cList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<History> getList(Object... cList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> List<O> getList(Class<O> clazz, Object... cList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> List<O> getOrderList(Class<O> clazz, Order order,
			Object... cList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> List<O> getFromList(Class<O> clazz, List list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<History> getAllList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> List<O> getAllList(Class<O> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <O> void deleteAll(Class<O> clazz) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByCriteria(Criterion c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <O> void deleteByCriteria(Class<O> clazz, Object... c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getTotalCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save(History entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(History entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(History entity) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public <O> void delete(Class<O> clazz, String[] ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void evict(Object object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pager findPager(Class<?> clazz, Pager pager, Object... criterions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<History> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEntityClass(Class<History> entityClass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<History> findBySql(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pager findPagerBySql(Pager pager, String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pager findPagerBySql(Pager pager, String sql,
			HashMap<String, Object> mapPara) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String operate(String djType, String czType, String czdj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QxUsers getCurrentUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBatch(List<Object> objs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveBatch(Collection<History> cols) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pager findPagerByOrders(Class<?> clazz, Pager pager, Order[] orders,
			Criterion... criterions) {
		// TODO Auto-generated method stub
		return null;
	}

}
