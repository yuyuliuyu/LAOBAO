package com.lingnet.hcm.service.impl.laobao;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.laobao.WupinhistoryDao;
import com.lingnet.hcm.dao.laobao.WupininputDao;
import com.lingnet.hcm.entity.laobao.Phone;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.service.laobao.WupinInputService;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.util.ExcelUtil;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;




@Service("wupinInputService")
public class WupinInputServiceImpl extends BaseServiceImpl<Phone, String> implements
WupinInputService {
	
	
	@Resource(name="wupininputDao")
	private WupininputDao wupininputDao;

	
	
	
	//人员基本信息list页面
	@SuppressWarnings({ "rawtypes" })
	@Override
	public HashMap getPersonByDepId(Pager pager, String searchData,String ids) {
																																			
		return wupininputDao.getPersonByDepId(pager, searchData,ids);
	}
/*	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<HashMap> getListData(Pager pager) {

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		pager = this.findPager(Phone.class, pager);

		List<HashMap> data = ReflectionUtil.getMapList(pager.getResult());
		List<HashMap> mapData = new ArrayList<HashMap>();

		for (HashMap map : data) {
			map.put("jgrq", sdf1.format(map.get("jgrq")));
			map.put("createdate", map.get("createdate"));

			if ("0".equals(map.get("jgzt"))) {
				map.put("jgzt", "未确认");
			} else {
				map.put("jgzt", "已确认");
			}

			Customer customer = customerDao.get(Restrictions.eq("id",
					map.get("mckh")));
			map.put("mckh",
					customer == null ? "" : customer.getCustomerShortname());
			Customer customer1 = customerDao.get(Restrictions.eq("id",
					map.get("mrkh")));
			map.put("mrkh",
					customer1 == null ? "" : customer1.getCustomerShortname());
			StorageLocation storageLocation = storageLocationDao
					.get(Restrictions.eq("id", map.get("cw")));
			map.put("cw",
					storageLocation == null ? "" : storageLocation
							.getPositionName());

			mapData.add(map);
		}
		return mapData;
	}*/

	
	@Override
	public String saveImportInfos(String endSuffix, File uploadFile)
			throws Exception {
		// 读取数据
        List<String[]> jblist = ExcelUtil.readDataTOSheet(1, "青港物流混合", endSuffix, uploadFile);
        // 第一行内容
        String[] jbfirstRow = {"部门名称", "岗位", "使用人", "电话号码", "类别"};
        // 单独把第一行抽取出来
        String[] jbfirstValues = jblist.get(0);
        
      for (int i = 0, z = jbfirstRow.length; i < z; i++) {
            if (!jbfirstRow[i].trim().equals(jbfirstValues[i].trim())) {
                return "文件导入失败：导入名单标题存在问题！<br/><font color='red'>" + jbfirstValues[i].trim() + "</font>列必须是 " + jbfirstRow[i].trim();
            }
        }
       //List<BasicInformation> importData = new ArrayList<BasicInformation>();
        // 匹配消息
        String msg = StringUtils.EMPTY;

        
        // 循环遍历存储《基础数据》Excel值
        for (int i = 1; i < jblist.size(); i++) {
            // 组装列
            String[] columns = new String[5];//17列
            String[] values = jblist.get(i);
            // 判断是否整行是空行
            Boolean isEmpty= true;
            for(int q=0;q<values.length;q++){
                if (q < 17) {
                    columns[q]=values[q].trim();
                    if (!StringUtils.isBlank(columns[q])) {
                        isEmpty = false;
                    }
                } else continue;
            }
            // 存在空白行
            if (isEmpty) {
                // 文档结束
                continue;
            }
            for(int k=values.length;k<17;k++){
                columns[k]="";
            }
            Phone ph = new Phone();// 存储话费信息
            BasicInformation infos = new BasicInformation();// 存储人员基本信息
            ph.setDepartment(columns[0]);
            ph.setPostname(columns[1]);
            ph.setUsername(columns[2]);
            ph.setPhonenumber(columns[3]);
            ph.setType(columns[4]);
           String id="";
           /* 		try {
				id = wupininputDao.save(ph);
			} catch (Exception e) {
			
				e.printStackTrace();
			}*/
            if (StringUtils.isBlank(id))throw new Exception("文件导入失败： 第"+ (i+1) +"行，保存基本信息失败！");

            break;
        }
    
    
    // 循环遍历存储《员工部门信息》Excel值
    return "success";

 /*           //部门名称
            if (!StringUtils.isBlank(columns[0])) {
            	ph.setDepartment(columns[0]);
            }
            	QxDepartment dep=this.get(QxDepartment.class, Restrictions.eq("description", columns[0]));
                if(dep!=null){
               	ph.setDepartment(dep.getName());

                }else throw new Exception("文件导入失败： 第"+ (+1) +"行，部门编号填写不正确！");
            }else throw new Exception("文件导入失败： 第"+ (+1) +"行，部门编号不能为空！");
            //部门岗位
            if (!StringUtils.isBlank(columns[1])) {
            	ph.setPostname(columns[1]);
            	DeptPosition dep=this.get(DeptPosition.class, Restrictions.eq("positionName", columns[1]));
            	//ph.setPostname(dep.getPositionName());
            }
            	                if(dep!=null){
                	ph.setPostname(dep.getPositionName());
                }else throw new Exception("文件导入失败： 第"+ (i+1) +"行，部门岗位编码填写不正确！");
            }else throw new Exception("文件导入失败： 第"+ (i+1) +"行，部门岗位编码不能为空！");
            // 姓名验证
            if (!StringUtils.isBlank(columns[2])) {
               ph.setUsername(columns[2]);
            } else throw new Exception("文件导入失败： 第"+ (i+1) +"行，名字不能为空！");
            //电话号码验证
            if (!StringUtils.isBlank(columns[3])) {
            	String message = checkLength(columns[3], "电话号码长度不能超过18位", 18);
            	ph.setPhonenumber(columns[3]);
            	
            	if (!StringUtils.isBlank(message)) {
                    throw new Exception("文件导入失败： 第"+ (i+1) +"行，"+message);
                }
            	BasicInformation oldinfo=null;
				try {
					oldinfo = this.get(Restrictions.eq("idNumber", columns[0]));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                if (oldinfo!=null) {
                    throw new Exception("文件导入失败： 第"+ (i+1) +"行，证件号码存在重复问题"+message);
                }
                infos.setIdNumber(columns[4]);
            }
            //类别验证
            if (!StringUtils.isBlank(columns[7])) {
                ph.setType(columns[7]);
            }

                String id="";
				try {
					id = wupininputDao.save(ph);
				} catch (Exception e) {
				
					e.printStackTrace();
				}
                if (StringUtils.isBlank(id))throw new Exception("文件导入失败： 第"+ (i+1) +"行，保存基本信息失败！");

                break;
            }
        
        
        // 循环遍历存储《员工部门信息》Excel值
        return "success";*/
	}



	@Override
	public Pager findPagerByOrder(Class<?> clazz, Pager pager, Order order,
			Criterion... criterions) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Phone get(String id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public <O> O get(Class<O> clazz, String id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Phone get(Criterion c) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public <O> O get(Class<O> clazz, Object... cList) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Phone> getList(Object... cList) {
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
	public List<Phone> getAllList() {
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
	public String save(Phone entity) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void update(Phone entity) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void delete(Phone entity) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void delete(String id) {
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
	public Class<Phone> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void setEntityClass(Class<Phone> entityClass) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public List<Phone> findBySql(String sql) {
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
	public void saveBatch(Collection<Phone> cols) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public Pager findPagerByOrders(Class<?> clazz, Pager pager, Order[] orders,
			Criterion... criterions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap> getListData(Pager pager) {
		// TODO Auto-generated method stub
		return null;
	}



	
}
