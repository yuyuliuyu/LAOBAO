package com.lingnet.hcm.service.impl.salary;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.salary.SalaryGroupWageDao;
import com.lingnet.hcm.entity.salary.Formula;
import com.lingnet.hcm.service.salary.CustomizeService;
import com.lingnet.util.ExcelUtil;
import com.lingnet.util.Pager;
import com.lingnet.util.ToolUtil;

/**
 * 自定义sql查询
 * @ClassName: CustomizeServiceImpl 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年6月29日 下午10:09:49 
 *
 */
@Service("customizeService")
public class CustomizeServiceImpl extends BaseServiceImpl<Formula, String>
              implements CustomizeService {

    @Resource(name="salaryGroupWageDao")
    private SalaryGroupWageDao salaryGroupWageDao;

    @Override
    public Map<String, Object> getCustomizeListData(String sql, Pager pager) {
        return salaryGroupWageDao.getCustomizeListData(sql);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void export(String sql) {
        try {
            sql = URLDecoder.decode(sql, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String, Object> mapData = salaryGroupWageDao.getCustomizeListData(sql);
        if (mapData != null) {
            List<Map<String, Object>> summaryList = (List<Map<String, Object>>) mapData.get("data");
            ArrayList<ArrayList> tableData = new ArrayList<ArrayList>();

            // 获取标题
            String[] itemNames = null;
            if (summaryList.size() > 0) {
                Map<String, Object> obj = summaryList.get(0);
                itemNames = new String[obj.entrySet().size()];
                ArrayList rowList=new ArrayList();
                rowList.add(1);//序号
                int i = 0;
                for(Entry<String, Object> entry : obj.entrySet()) {
                    itemNames[i++] = entry.getKey();
                    rowList.add(entry.getValue());
                }
                tableData.add(rowList);
            }
            for (int i = 1, l = summaryList.size(); i < l; i++) {
                ArrayList rowList=new ArrayList();
                Map<String, Object> obj = summaryList.get(i);
                rowList.add(i+1);//序号
                for(Entry<String, Object> entry : obj.entrySet()) {
                    rowList.add(entry.getValue());
                }
                tableData.add(rowList);
            }

            //表格标题
            String[] tableCaption ={"序号"};
            int len1 = tableCaption.length;
            if (itemNames != null) {
                int len2 = itemNames.length;

                // 扩容第一个数组
                tableCaption = Arrays.copyOf(tableCaption, len1 + len2);
                System.arraycopy(itemNames, 0, tableCaption, len1, len2);
            }

            //表格脚部分
            String dcr=ToolUtil.userName();
            Date dcrq=new Date();
            ArrayList footData=new ArrayList();
            footData.add("导出人： "+ ExcelUtil.toString(dcr));
            footData.add("导出日期： "+ ExcelUtil.toString(dcrq));
            ExcelUtil.export("自定义数据", null, tableCaption, tableData, footData);
        }
    }
}
