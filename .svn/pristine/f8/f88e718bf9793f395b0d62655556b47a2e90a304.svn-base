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
import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.check.InstituteDao;
import com.lingnet.hcm.dao.check.InstitutionDao;
import com.lingnet.hcm.dao.personnel.PersonnelDao;
import com.lingnet.hcm.entity.check.CkInstitution;
import com.lingnet.hcm.service.check.InstitutionService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: InstitutionServiceImpl 
 * @Description: 班制名称service实现类 
 * @author wangqiang
 * @date 2017年3月31日 上午11:05:23 
 *
 */
@Service("institutionService")
public class InstitutionServiceImpl extends BaseServiceImpl<CkInstitution, String> 
	implements InstitutionService{
	
	@Resource(name = "institutionDao")
	private InstitutionDao institutionDao;
	
	@Resource(name="personnelDao")
	private PersonnelDao personnelDao;
	
	@Resource(name = "instituteDao")
	private InstituteDao instituteDao;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> getDataByCond(Pager pager) {
		List<Map<String, Object>> datas = institutionDao.getInfoByCond(pager);
        HashMap result = new HashMap();
        result.put("data", datas);
        result.put("total", pager.getTotalCount());
		return result;
	}

	@Override
	public void saveOrUpdateInfo(String formdata) throws Exception {
		Map<String,String> dataMap = JsonUtil.parseProperties(formdata);
		if (!dataMap.isEmpty()){
			if (!"".equals(dataMap.get("id"))){//修改
				CkInstitution institution = institutionDao.get(dataMap.get("id"));
				institution.setInstituteName(dataMap.get("instituteName"));
				if (dataMap.get("overtimeModulus") != null && !"".equals(dataMap.get("overtimeModulus"))){
					institution.setOvertimeModulus(Double.valueOf(dataMap.get("overtimeModulus")));
				}
				if (dataMap.get("vacationModulus") != null && !"".equals(dataMap.get("vacationModulus"))){
					institution.setVacationModulus(Double.valueOf(dataMap.get("vacationModulus")));
				}
				institutionDao.update(institution);
			} else {//保存
				CkInstitution institution = new CkInstitution();
				institution.setInstituteName(dataMap.get("instituteName"));
				if (dataMap.get("overtimeModulus") != null && !"".equals(dataMap.get("overtimeModulus"))){
					institution.setOvertimeModulus(Double.valueOf(dataMap.get("overtimeModulus")));
				}
				if (dataMap.get("vacationModulus") != null && !"".equals(dataMap.get("vacationModulus"))){
					institution.setVacationModulus(Double.valueOf(dataMap.get("vacationModulus")));
				}
				institution.setIsDelete(0);
				institutionDao.save(institution);
			}
		}
	}

	@Override
	public HSSFWorkbook exportInfos() {
		String[] cellname={
				"序号",
                "班制名称"};
        HSSFWorkbook hwb = new HSSFWorkbook();
        HSSFSheet sheet = hwb.createSheet("班制名称信息");
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
        List<Map<String, String>> list = institutionDao.getAllInfoList();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
            	Map<String, String> info = list.get(i);
                row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(i+1+"");
                if(info.get("instituteName") != null && !"null".equals(info.get("instituteName"))){ 
                	row.createCell(1).setCellValue(info.get("instituteName")); 
                }
//                if(info.get("overtimeModulus") != null && !"null".equals(info.get("overtimeModulus"))){ 
//                	row.createCell(2).setCellValue(info.get("overtimeModulus")); 
//                }
//                if(info.get("vacationModulus") != null && !"null".equals(info.get("vacationModulus"))){ 
//                	row.createCell(3).setCellValue(info.get("vacationModulus")); 
//                }
            }
        }
        return hwb;
	}

	@Override
	public List<Map<String, String>> getInstitutionList(String dayFlag) {
		//获得所有班制名称下拉
		List<Map<String, String>> institutions = institutionDao.getInstitutionList(dayFlag);
		return institutions;
	}

	@Override
	public CkInstitution getValidateInstitueName(String institueName) {
		return institutionDao.getInfoByName(institueName);
	}
	
}
