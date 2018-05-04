package com.lingnet.hcm.service.train;
 
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.lingnet.common.service.BaseService; 
import com.lingnet.hcm.entity.basic.TableColumInfo;

public interface TableColumService extends BaseService<TableColumInfo, String>{
    /**
     * 获得要展示的列表/导出的头字段信息
     * @Title: getshowcolum 
     * @param tablename
     * @param showtype
     * @return 
     * List<TableColumInfo> 
     * @author 马晓鹏
     * @since 2017年3月31日 V 1.0
     */
    public List<TableColumInfo> getshowcolum(String tablename,Integer[] showtype);
    /**
     * 获得要展示的列表/导出的头字段信息《第一级》
     * @Title: getshowcolum 
     * @param tablename
     * @param showtype
     * @return 
     * List<TableColumInfo> 
     * @author 马晓鹏
     * @since 2017年3月31日 V 1.0
     */
    public List<TableColumInfo> getshowcolum(String tablename,Integer[] showtype,String ptype,Integer usingtype);
    /**
     * 导出报表 根据实体类进行展示
     * @Title: excellinfo 
     * @return 
     * HSSFWorkbook 
     * @author 马晓鹏
     * @since 2017年3月31日 V 1.0
     */
    public HSSFWorkbook excellinfo(List<Object> infolist, String tablename,String chinaname,Integer[] showstate);
    
    /**
     * 导出报表 根据map进行导出
     * @Title: excellinfo 
     * @return 
     * HSSFWorkbook 
     * @author 马晓鹏
     * @since 2017年3月31日 V 1.0
     */
    @SuppressWarnings("rawtypes")
    public HSSFWorkbook excellmap(List<Map> maplist,String chinaname,Integer[] showstate);
    
}
