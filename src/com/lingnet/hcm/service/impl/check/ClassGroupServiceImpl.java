package com.lingnet.hcm.service.impl.check;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.check.ClassGroupDao;
import com.lingnet.hcm.dao.personnel.PersonnelDao;
import com.lingnet.hcm.entity.check.CkClass;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.service.check.ClassGroupService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: ClassGroupServiceImpl 
 * @Description: 班组信息service实现类 
 * @author wangqiang
 * @date 2017年3月22日 下午2:13:12 
 *
 */
@Service("classGroupService")
public class ClassGroupServiceImpl extends BaseServiceImpl<CkClass, String> implements ClassGroupService{
	
	@Resource(name = "classGroupDao")
	private ClassGroupDao classGroupDao;
	
	@Resource(name="personnelDao")
	private PersonnelDao personnelDao;

	@Override
	public List<CkClass> getAllClassInfos() {
        String userId = LingUtil.userName();
        BasicInformation baseinfo=personnelDao.get(
                Restrictions.or(
                        Restrictions.eq("jobNumber", userId),
                        Restrictions.eq("practiceNum", userId))); 
        String departid=baseinfo.getDepartId();
		List<CkClass> classList = classGroupDao.getEntityListByUserId(departid);
		return classList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> getDataByCond(Pager pager) { 
        String userId = LingUtil.userName();
        BasicInformation baseinfo=personnelDao.get(
                Restrictions.or(
                        Restrictions.eq("jobNumber", userId),
                        Restrictions.eq("practiceNum", userId))); 
        String departid=baseinfo.getDepartId();
		List<Map<String, Object>> datas = classGroupDao.getInfoByCond(pager, departid);
        HashMap result = new HashMap();
        result.put("data", datas);
        result.put("total", pager.getTotalCount());
		return result;
	}

	@Override
	public void saveOrUpdateInfo(String formdata) throws Exception { 
	    String userId = LingUtil.userName();
	    BasicInformation baseinfo=personnelDao.get(
	            Restrictions.or(
	                    Restrictions.eq("jobNumber", userId),
	                    Restrictions.eq("practiceNum", userId)));
	    String checkUnitId =baseinfo.getCheckUnitId();
	    String departid=baseinfo.getDepartId();
//		String checkUnitId = personnelDao.getCheckUnitIdById(userId);//获得考勤部门
		Map<String,String> dataMap = JsonUtil.parseProperties(formdata);
		if (!dataMap.isEmpty()){
			if (!"".equals(dataMap.get("id"))){//修改
				CkClass group = classGroupDao.get(dataMap.get("id"));
				group.setClassName(dataMap.get("className"));
				group.setInstituteId(dataMap.get("instituteId"));
				classGroupDao.update(group);
			} else {//保存
				CkClass group = new CkClass();
				group.setClassNo(dataMap.get("classNo"));
				group.setClassName(dataMap.get("className"));
				group.setInstituteId(dataMap.get("instituteId"));
				group.setUserId(departid);
				group.setDepId(checkUnitId);
				group.setIsDelete(0);
				classGroupDao.save(group);
			}
		}
	}

	@Override
	public HSSFWorkbook exportInfos() {
		String[] cellname={
				"序号","班号","班组名称","班制名称"
                };
        HSSFWorkbook hwb = new HSSFWorkbook();
        HSSFSheet sheet = hwb.createSheet("考勤班组信息");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell;
        HSSFCellStyle stycle = hwb.createCellStyle();
        stycle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        for (int i = 0; i < cellname.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(cellname[i]);
            cell.setCellStyle(stycle);
            sheet.setColumnWidth((short) i, cellname[i].getBytes().length * 600);
        }
        //查询班制名称信息
        String userId = toolUtil.getUserId();
        List<Map<String, String>> list = classGroupDao.getAllInfoList(userId);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
            	Map<String, String> info = list.get(i);
                row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(i+1+"");
                row.createCell(1).setCellValue(info.get("classNo")); 
                row.createCell(2).setCellValue(info.get("className")); 
                row.createCell(3).setCellValue(info.get("instituteName"));
            }
        }
        return hwb;
	}

	@Override
	public CkClass getValidateClassNo(String classNo) {
		return classGroupDao.getInfoByClassNo(classNo);
	}
	
	
}
