package com.lingnet.hcm.service.impl.laobao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.laobao.WupingangweiDao;
import com.lingnet.hcm.entity.laobao.BiaoZhun;
import com.lingnet.hcm.entity.laobao.History;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.service.laobao.BiaoZhunService;
import com.lingnet.hcm.service.laobao.HistoryService;
import com.lingnet.hcm.service.laobao.WupingangweiService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;


@Service("wupingangweiService")
public class WupingangweiServiceImpl extends BaseServiceImpl<BiaoZhun, String> implements WupingangweiService{

	
    @Resource(name="biaoZhunService")
    private BiaoZhunService biaoZhunService;
    @Resource(name="historyService")
    private HistoryService historyService;
	@Resource(name="wupingangweiDao")
	private WupingangweiDao wupingangweiDao;
	
	//人员基本信息list页面
	@SuppressWarnings({ "rawtypes" })
	@Override
	public HashMap getPersonByDepId(Pager pager, String searchData) {
																																			
		return wupingangweiDao.getPersonByDepId(pager, searchData);
	}

	/*以下是代码整理：将放在action中的实现方法放到impl中  栾胜鹏*/
    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public void saveOrUpdate(String gwId, String griddata) throws Exception {
        List<Map<String, String>> list = JsonUtil.getMapList(griddata);
        for (int i = 0, l = list.size(); i < l; i++) {
            Map<String, String> map = list.get(i);
            if ("removed".equals(map.get("_state"))) {
                BiaoZhun biaoZhun = get(BiaoZhun.class, Restrictions.eq("id", map.get("id")));
                if (biaoZhun != null)
                    biaoZhunService.delete(biaoZhun);
            }
            else if ("modified".equals(map.get("_state"))) {
                BiaoZhun biaoZhun = get(BiaoZhun.class, Restrictions.eq("id", map.get("id")));
                if (biaoZhun != null) {
                    biaoZhun.setWpId(map.get("wpId"));
                    biaoZhun.setLqjg(Integer.valueOf(map.get("lqjg")));
                    biaoZhun.setLqdw(Integer.valueOf(map.get("lqdw")));
                } else throw new Exception("第"+(i+1)+"条记录不存在，已经被删除！");
            }
            else if ("added".equals(map.get("_state"))) {
                // 查找是否存在相同的物品
                BiaoZhun bz = get(BiaoZhun.class, Restrictions.eq("wpId", map.get("wpId")), Restrictions.eq("gwId", gwId));
                if (null != bz) throw new Exception("该物品"+map.get("wpmc")+"已经存在");
                BiaoZhun biaoZhun = new BiaoZhun();
                biaoZhun.setGwId(gwId);
                biaoZhun.setWpId(map.get("wpId"));
                biaoZhun.setLqjg(Integer.valueOf(map.get("lqjg")));
                biaoZhun.setLqdw(Integer.valueOf(map.get("lqdw")));
                String id = biaoZhunService.save(biaoZhun);
                if (StringUtils.isBlank(id)) {
                    throw new Exception("第"+(i+1)+"条记录保存失败！");
                }
            }
        }
    }

	@Override
	public HSSFWorkbook exportInfos() {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public Map<String, Object> getGwAndWpData(String searchData, Pager pager) {
        StringBuilder sql= new StringBuilder();
        sql.append("   SELECT                                                      ");
        sql.append("       B.ID,B.WP_ID, W.WPMC,W.GGXH,W.JLDW,W.GYS,B.LQJG, B.LQDW,B.CREATEDATE ");
        sql.append("   FROM BIAOZHUN B                                             ");
        sql.append("   LEFT JOIN WUPIN W                                           ");
        sql.append("   ON B.WP_ID = W.ID                                           ");
        sql.append("   WHERE 1=1                                           ");

        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        if (mapData != null) {
            if (!StringUtils.isBlank(mapData.get("depId"))) {
                sql.append("   AND B.GW_ID ='"+mapData.get("depId")+"'           ");
            }
/*    			if (mapData.get("wpmc") != null && !"".equals(mapData.get("wpmc"))){
    				sql.append(" and W.WPMC like '%"+mapData.get("wpmc")+"%'");
    			}
    			if (mapData.get("ggxh") != null && !"".equals(mapData.get("ggxh"))){
    				sql.append(" and W.GGXH like '%"+mapData.get("ggxh")+"%'");
    			}*/
            

        }

        pager = findPagerBySql(pager, sql.toString());
        List<?> list = pager.getResult();
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("wpId", obj[1]);
            map.put("wpmc", obj[2]);
            map.put("ggxh", obj[3]);
            map.put("jldw", obj[4]);
            map.put("gys", obj[5]);
            map.put("lqjg", obj[6]);
            map.put("lqdw", obj[7]);
            map.put("createDate", obj[8]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());

        return map;
    }


    @Override
    public Map<String, Object> getCurStaffWupinData(String staffId, String departId,String searchData, Pager pager) {
        StringBuilder sql= new StringBuilder();
        sql.append("   SELECT TDATA.ID, TDATA.WP_ID, TDATA.WPMC, TDATA.GGXH, TDATA.JLDW,           ");
        sql.append("          TDATA.GYS, TDATA.LQJG, TDATA.LQDW, TDATA.CREATEDATE,TDATA.GW_ID                 ");
        sql.append("     FROM (SELECT B.ID, B.WP_ID, W.WPMC, W.GGXH, W.JLDW, W.GYS,      ");
        sql.append("                  B.LQJG, B.LQDW, B.CREATEDATE,B.GW_ID                                                 ");
        sql.append("             FROM BIAOZHUN B                                                                    ");
        sql.append("             LEFT JOIN WUPIN W                                                  ");
        sql.append("             ON B.WP_ID = W.ID                                                ");
        sql.append("           ) TDATA                                                                              ");
       sql.append("WHERE");
        // sql.append("     LEFT JOIN HISTORY H                                                                        ");
       // sql.append("     ON TDATA.ID = H.BIAO_ZHUN_ID                                                                   ");
       // sql.append("    WHERE CASE WHEN NVL2(H.ID, 1, 0) = 0 THEN 1                                                 ");
       // sql.append("               WHEN TO_CHAR(SYSDATE, 'YYYY-MM-DD') >= TO_CHAR(H.NEXTDATE, 'YYYY-MM-DD') THEN 1  ");
     //   sql.append("               ELSE 0 END = 1                                                                   ");

        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
		if (mapData != null) {
		    if (mapData.get("depId") != null && !"".equals(mapData.get("depId"))){
		        String[] depIdArrs = mapData.get("depId").split(",");
		        
		        String  resql="";
		        for (int i = 0; i < depIdArrs.length; i++){
		            if (!"".equals(depIdArrs[i])){
		                resql+="'" + depIdArrs[i] + "', ";
		            }
		        }
		        resql = resql.substring(0, resql.length()-2);
		        
		        sql.append(" and DEPART_ID in("+resql+")");
		        
		    }
        	// 部门岗位ID
            if (!StringUtils.isBlank(mapData.get("departId"))){
                sql.append("   AND TDATA.GW_ID ='"+mapData.get("departId")+"'           ");
            }
      
        	if (!StringUtils.isBlank(mapData.get("postId"))) {
                sql.append("    TDATA.GW_ID ='"+mapData.get("postId")+"'           ");
            }
        }

        pager = findPagerBySql(pager, sql.toString());
        List<?> list = pager.getResult();
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("wpId", obj[1]);
            map.put("wpmc", obj[2]);
            map.put("ggxh", obj[3]);
            map.put("jldw", obj[4]);
            map.put("gys", obj[5]);
            map.put("lqjg", obj[6]);
            map.put("lqdw", obj[7]);
            map.put("createDate", obj[8]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());

        return map;
    }

    @Override
    public String saveLingquWp(String griddata, String staffId) {
        BasicInformation info = get(BasicInformation.class,
                Restrictions.eq("id", staffId),
                Restrictions.eq("isDelete", 0));
        if (null == info) {
            return "该用户不存在";
        }
        List<History> histories = new ArrayList<History>();
        List<Map<String, String>> list = JsonUtil.getMapList(griddata);
        for (int i = 0, l = list.size(); i < l; i++) {
            Map<String, String> map = list.get(i);
            History history = new History();
            history.setName(info.getName());
            history.setBiaoZhunId(map.get("id"));
            history.setWpId(map.get("wpId"));
            history.setWpmc(map.get("wpmc"));
            history.setGgxh(map.get("ggxh"));
            history.setJldw(map.get("jldw"));
            history.setCount(Integer.valueOf(map.get("count")));
            history.setStaffId(staffId);

            // 计算下次领取时间
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int time = Integer.valueOf(map.get("lqjg"));
            if (1 == Integer.valueOf(map.get("lqdw"))) {
                cal.add(Calendar.YEAR, time);
            } else if (2 == Integer.valueOf(map.get("lqdw"))) {
                cal.add(Calendar.MONTH, time);
            }
            history.setNextdate(cal.getTime());
            histories.add(history);
        }
        if (histories.size() > 0) historyService.saveBatch(histories);

        return "success";
    }

}
