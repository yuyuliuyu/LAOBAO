package com.lingnet.hcm.service.impl.laobao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.entity.laobao.Fafangbiaozhun;
import com.lingnet.hcm.entity.laobao.History;
import com.lingnet.hcm.entity.laobao.Wupin;
import com.lingnet.hcm.service.laobao.FafangbiaozhunService;
import com.lingnet.hcm.service.laobao.HistoryService;
import com.lingnet.hcm.service.laobao.WupinService;

import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

@Service("wupinService")
public class WupinServiceImpl extends BaseServiceImpl<Wupin, String> implements WupinService{

    @Resource(name="fafangbiaozhunService")
    private FafangbiaozhunService fafangbiaozhunService;
    @Resource(name="historyService")
    private HistoryService historyService;
	private String searchData;
	private Pager pager;
	private String node2;
	private String node1;
	
	/*以下是代码整理：将放在action中的实现方法放到impl中  栾胜鹏*/
    @Override
    public String saveOrUpdate(String formdata){
       Wupin  wupin = JsonUtil.toObject(formdata, Wupin.class);
       if (StringUtils.isBlank(wupin.getId())) {
           String id = this.save(wupin);
           if (StringUtils.isBlank(id)) return "保存失败";
           
       } else {
           Wupin wupinNew = get(Wupin.class, Restrictions.eq("id", wupin.getId()));
           if (StringUtils.isBlank(wupinNew.getId()))  return "该物品不存在";
           wupinNew.copyFrom(wupin);
           update(wupinNew);
       }

       return "success";
    }
	
	/**
     * 获取角色信息 
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public String json(String key, Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                          ");
        sql.append("       W.ID,W.WPMC,W.GGXH,W.JLDW,W.KCSL,W.GYS,W.NEXTDATE, ");
        sql.append("       W.CREATEDATE ");
        sql.append("   FROM WUPIN W                                                    ");
/*        sql.append("where 1=1");
		if (key != null){
			Map<String,String> dataMap = JsonUtil.parseProperties(key);
			if (null != dataMap) {
			    
			}
			if (dataMap.get("wpmc") != null && !"".equals(dataMap.get("wpmc"))){
				sql.append(" and W.WPMC like '%"+dataMap.get("wpmc")+"%'");
			}*/
        
			sql.append("   ORDER BY W.CREATEDATE DESC                                      ");

        pager = findPagerBySql(pager, sql.toString());
        List<?> list = pager.getResult();
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("wpmc", obj[1]);
            map.put("ggxh", obj[2]);
            map.put("jldw", obj[3]);
            map.put("kcsl", obj[4]); 
            map.put("gys", obj[5]);
            map.put("nextdate", obj[6]);
            map.put("createDate", obj[7]);
            dataList.add(map);
        }
        Map result = new HashMap();
        result.put("data", dataList);
        result.put("total", pager.getTotalCount());

        return JsonUtil.Encode(result);
    }

	@Override
	public HSSFWorkbook exportInfos() {
		return null;
/*		String[] cellname={
				"序列",
                "职工号",
                "姓名",
                "物品名称",
                "领取数量",
                "规格型号",
                "计量单位",
                "供应商",
                "创建时间",
                "下次领取时间"};
        HSSFWorkbook hwb = new HSSFWorkbook();
        HSSFSheet sheet = hwb.createSheet("劳保标准信息");
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
        //获得当前用户的所有权限部门集合
        String depIds = getAllDepIdStrs();
       
		//查询人员考勤信息
        HashMap<String, Object> list  = WupinhistoryServiceImpl.getPersonByDepId(pager, searchData);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
            	Map<String, String> info = list.get(i);
                row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(i+1+"");
                if(info.get("jobNumber") != null && !"null".equals(info.get("jobNumber"))){ 
                	row.createCell(1).setCellValue(info.get("jobNumber")); 
                }
                if(info.get("name") != null && !"null".equals(info.get("name"))){ 
                	row.createCell(2).setCellValue(info.get("name")); 
                }
                if(info.get("wpmc") != null && !"null".equals(info.get("wpmc"))){ 
                	row.createCell(3).setCellValue(info.get("wpmc")); 
                }
                if(info.get("count") != null && !"null".equals(info.get("count"))){ 
                	row.createCell(4).setCellValue(info.get("count")); 
                }
                if(info.get("ggxh") != null && !"null".equals(info.get("ggxh"))){ 
                	row.createCell(5).setCellValue(info.get("ggxh")); 
                }
                if(info.get("jldw") != null && !"null".equals(info.get("jldw"))){ 
                	row.createCell(6).setCellValue(info.get("jldw")); 
                }
                if(info.get("gys") != null && !"null".equals(info.get("gys"))){ 
                	row.createCell(7).setCellValue(info.get("gys")); 
                }
                if(info.get("createdate") != null && !"null".equals(info.get("createdate"))){ 
                	row.createCell(8).setCellValue(info.get("createdate")); 
                }
                if(info.get("nextdate") != null && !"null".equals(info.get("nextdate"))){ 
                	row.createCell(9).setCellValue(info.get("nextdate")); 
                }

            }
        }
        return hwb;*/
	}

    private String getAllDepIdStrs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public String saveOrUpdateBz(String formdata) {
        Fafangbiaozhun fafangbiaozhun = JsonUtil.toObject(formdata, Fafangbiaozhun.class);
        if (StringUtils.isBlank(fafangbiaozhun.getId())) {
            String id = fafangbiaozhunService.save(fafangbiaozhun);
            if (StringUtils.isBlank(id)) return "保存失败";
            
        } else {
            Fafangbiaozhun wupinNew = get(Fafangbiaozhun.class, Restrictions.eq("id", fafangbiaozhun.getId()));
            if (StringUtils.isBlank(wupinNew.getId()))  return "该标准不存在";
            wupinNew.copyFrom(fafangbiaozhun);
            fafangbiaozhunService.update(wupinNew);
        }

        return "success";
    }

    @Override
    public Map<String, Object> getNeedCheckWp(String name,String fkId, Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                              ");
        sql.append("   H.ID,H.NAME,H.WPMC,H.COUNT,H.GGXH,H.JLDW,         ");
        sql.append("   H.IS_SP,H.CREATEDATE         ");
        sql.append("   FROM HISTORY H                      ");
        sql.append("   LEFT JOIN BIAOZHUN B  ");
        sql.append("   ON H.BIAO_ZHUN_ID = B.ID              ");
        sql.append("   WHERE NVL(H.IS_SP, 0) = 0              ");

        if(StringUtils.isNotBlank(fkId)) {
        	sql.append("    AND B.GW_ID = '"+ fkId +"'              ");
        }
        if(StringUtils.isNotBlank(name)) {
        	sql.append(" and H.NAME like '%"+ name +"%'");
        }
        
        pager = findPagerBySql(pager, sql.toString());
        List<?> list = pager.getResult();
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
          
            map.put("id", obj[0]);
            map.put("name", obj[1]);
            map.put("wpmc", obj[2]);
            map.put("count", obj[3]);
            map.put("ggxh", obj[4]);
            map.put("jldw", obj[5]);
            map.put("isSp", obj[6]);
           map.put("createDate", obj[7]);

            dataList.add(map);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", dataList);
        result.put("total", pager.getTotalCount());

        return result;
    }

    @Override
    public String updateToAgree(String ids) {
        String[] idsArr = ids.split(",");
        List<History> list = new ArrayList<History>();
        for (int i = 0, l = idsArr.length; i < l; i++) {
            History history = get(History.class, Restrictions.eq("id", idsArr[i]));
            if (history == null) {
                return "选择的第"+(i+1)+"条记录不存在";
            }
            history.setIsSp(1);
            list.add(history);
        }
        if (list.size() > 0) historyService.saveBatch(list);

        return "success";
    }

    @Override
    public String updateToDisAgree(String ids) {
        String[] idsArr = ids.split(",");
        List<History> list = new ArrayList<History>();
        for (int i = 0, l = idsArr.length; i < l; i++) {
            History history = get(History.class, Restrictions.eq("id", idsArr[i]));
            if (history == null) {
                return "选择的第"+(i+1)+"条记录不存在";
            }
            history.setIsSp(0);
            list.add(history);
        }
        if (list.size() > 0) historyService.saveBatch(list);

        return "success";
    }

/*	@Override
	public void saveOrUpdate(String name, String maqm, String daqm,
			String cxgz, String dxgz, String yrf, String yy, String yx,
			String maqx, String jyx, String jyst, String dhst,
			BackendSecurityMetadataSource backendSecurityMetadataSource) {
		// TODO Auto-generated method stub
		
	}*/

/*	@Override
	public void saveOrUpdate(String name, String maqm, String daqm,String cxgz,String dxgz,String yrf,String yy,String yx,String maqx,String jyx,String jyst,String dhst,BackendSecurityMetadataSource backendSecurityMetadataSource) {
    	List<Map<String, String>> listMap =null;
        List<Map<String, String>> listData =null;
        List<Map<String, String>> listFun =null;
        

        
        Map map = new HashMap();
        
       // if(id!=null&&!"null".equals(id)){
            //保存权限
        	//Branch2 backendRole = get(Branch2.class,Restrictions.eq("id", id));
            
            //backendRole.setName(name);
            
          //  backendRole.setDescription(desc);
          // QxRoles backendRole = this.findRoleByName(name);
          // if(backendRole!=null){
          //     throw new Exception("该角色名称已经存在，请重新录入！");
          // }
           Fafangbiaozhun  fafangbiaozhun = new Fafangbiaozhun();
           fafangbiaozhun.setName(name);
           fafangbiaozhun.setMaqm(maqm);
           fafangbiaozhun.setDaqm(daqm);
           fafangbiaozhun.setCxgz(cxgz);
           fafangbiaozhun.setDxgz(dxgz);
           fafangbiaozhun.setYrf(yrf);
           fafangbiaozhun.setYy(yy);
           fafangbiaozhun.setYx(yx);
           fafangbiaozhun.setMaqx(maqx);
           fafangbiaozhun.setJyx(jyx);
           fafangbiaozhun.setJyst(jyst);
           fafangbiaozhun.setDhst(dhst);
           this.save(fafangbiaozhun);
            
           // operate("角色管理", "增加",backendRole.getName());
       // }
        
      //  backendSecurityMetadataSource.refresh();
    }
	
	private void save(Fafangbiaozhun fafangbiaozhun) {
		// TODO Auto-generated method stub
		
	}

	*//**
     * 获取角色信息 
	 * @param <Wupinfafang>
     *//*
    @SuppressWarnings("unchecked")
    public  String json1(String key, Pager pager) {
        @SuppressWarnings("rawtypes")
        ArrayList data = new ArrayList();
        
        pager = this.findPager(WuPinAction.class, pager);
      
        @SuppressWarnings("rawtypes")
        List list = pager.getResult();
        //获取角色详细数据转成json
        for (int i = 0; i < list.size(); i++) {
            HashMap<String,String> record = new HashMap<String,String>();
            Fafangbiaozhun qx = (Fafangbiaozhun) list.get(i);
            
            record.put("name",  qx.getName());
            record.put("maqm",  qx.getMaqm());
//            if(qx.getIsSystem()){
//                record.put("isSystem", "√");
//            }else{
//                record.put("isSystem", "×");
//            }
           
            record.put("daqm", qx.getDaqm());
            record.put("cxgz", qx.getCxgz());
            record.put("dxgz", qx.getDxgz());
            record.put("yrf", qx.getYrf());
            record.put("yy", qx.getYy());
            record.put("yx", qx.getYx());
            record.put("maqx", qx.getMaqx());
            record.put("jyx", qx.getJyx());
            record.put("jyst", qx.getJyst());
            record.put("dhst", qx.getDhst());


            data.add(record);
        }
           @SuppressWarnings("rawtypes")
           HashMap result = new HashMap();
           
           result.put("data", data);
           result.put("total", pager.getTotalCount());
           
           String json = JsonUtil.Encode(result);
           
           return json;
    }*/

}

 