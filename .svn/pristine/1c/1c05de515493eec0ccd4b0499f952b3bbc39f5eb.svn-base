package com.lingnet.hcm.service.impl.train;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.Region;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.entity.ExcellExitInfo;
import com.lingnet.hcm.entity.basic.TableColumInfo;
import com.lingnet.hcm.service.train.TableColumService;

@Service("tablecolumservice")
public class TableColumServiceImpl extends
        BaseServiceImpl<TableColumInfo, String> implements TableColumService {

    @Resource(name = "tablecolumservice")
    private TableColumService tableService;

    @Override
    public List<TableColumInfo> getshowcolum(String tablename,
            Integer[] showtype) {
        List<TableColumInfo> showlist = this.getList(TableColumInfo.class,
                Restrictions.eq("tablename", tablename),
                Restrictions.in("columstate", showtype),
                Restrictions.ne("pid", "0"));
        return showlist;
    }
 
    @Override
    public HSSFWorkbook excellinfo(List<Object> infolist, String tablename,
            String chinaname, Integer[] showstate) {
        ExcellExitInfo excelinfo = null;
        List<TableColumInfo> columlistall = tableService.getshowcolum(
                chinaname, showstate);
        List<TableColumInfo> columlistF = tableService.getshowcolum(chinaname,
                showstate, null,1);
        List<TableColumInfo> columlistS = tableService.getshowcolum(chinaname,
                showstate, "notnull",1);
        List<ExcellExitInfo> excellist = new ArrayList<ExcellExitInfo>();
        int startnum = 0;
        String[] first = new String[columlistall.size()]; // 总的表头信息
        String[] second = new String[columlistS.size()]; // 双行表头，第二行的信息
        int pnum = 0;
        int cnum = 0;
        int bjth = 0;
        // 从第几行开始，需要几行
        try {
            for (int i = 0; i < columlistall.size(); i++) {
                first[i] = columlistF.get(pnum).getChinaname();
                int xnum = 0;

                for (int j = 0; j < columlistS.size(); j++) {
                    if (columlistS.get(j).getPtype()
                            .equals(columlistF.get(pnum).getId())) {
                        i++;
                        first[i] = "";
                        second[cnum] = columlistS.get(j).getChinaname();
                        cnum++;
                        xnum++;
                        bjth = 1;
                        if (startnum == 0) {
                            startnum = i;
                        }
                    }
                }
                if (bjth == 1) {
                    excelinfo = new ExcellExitInfo();
                    excelinfo.setStartcolm(pnum);
                    excelinfo.setSumcolm(xnum);
                    excellist.add(excelinfo);
                    bjth = 0;
                }
                pnum++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        excelinfo = excellist.get(excellist.size() - 1);
        int startc = excellist.get(0).getStartcolm();
        int endcol = excelinfo.getStartcolm() + excelinfo.getSumcolm();
        HSSFWorkbook hwb = new HSSFWorkbook();
        /*
         * 本年度累计清欠社会保险费金额（万元） 涉及农民工金额 （万元） 涉及职工人数 本年度累计清欠工资金额（万元） 涉及农民工金额 （万元）
         * 涉及职工人数
         */
        HSSFSheet sheet = hwb.createSheet(new Date().getTime() + "report");
        HSSFRow btrow = sheet.createRow(0);
        HSSFRow sjrow = sheet.createRow(1);
        HSSFRow row = sheet.createRow(2);
        HSSFRow row1 = sheet.createRow(3);
        HSSFCell cell;
        HSSFCellStyle stycle = hwb.createCellStyle();
        stycle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 添加第一行标题 -- 2016年11月21日 wangqn添加
        cell = btrow.createCell(0);
        cell.setCellValue("行政处罚（处理）案件台账");
        cell.setCellStyle(stycle);
        sheet.addMergedRegion(new Region((short) 0, (short) 0, (short) 0,
                (short) (first.length - 1)));
        // 添加第二行时间 -- 2016年11月21日 wangqn添加
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String exsj = "时间：" + sdf.format(new Date());
        cell = sjrow.createCell(first.length);
        cell.setCellValue(exsj);
        cell.setCellStyle(stycle);
        sheet.addMergedRegion(new Region((short) 1, (short) endcol, (short) 1,
                (short) (first.length - 1)));
        // 表头标题合并修改 -- 2016年11月21日 wangqn修改

        for (int i = 0; i < first.length; i++) {
            if (i >= startc && i < endcol) {
                for (int j = 0; j < excellist.size(); j++) {
                    if (i == excellist.get(j).getStartcolm()) {
                        cell = row.createCell(i);
                        cell.setCellValue(first[i]);
                        cell.setCellStyle(stycle);
                        sheet.addMergedRegion(new Region((short) 2, (short) i,
                                (short) 2, (short) (i + excelinfo.getSumcolm())));
                        sheet.setColumnWidth((short) i,
                                first[i].getBytes().length * 200);
                    }
                }

                cell = row1.createCell(i);
                cell.setCellValue(second[i - startc]);
                cell.setCellStyle(stycle);
                sheet.setColumnWidth((short) i,
                        second[i - startc].getBytes().length * 200);
            } else {
                cell = row.createCell(i);
                cell.setCellValue(first[i]);
                cell.setCellStyle(stycle);
                sheet.addMergedRegion(new Region((short) 2, (short) i,
                        (short) 3, (short) i));
                sheet.setColumnWidth((short) i,
                        first[i].getBytes().length * 200);
            }
        }

        /*
         * ..........................................赋值开始........................
         * ...............................
         */
        for (int j = 0; j < infolist.size(); j++) {
            Object obj = infolist.get(j);
            Class tclss = obj.getClass();
            Field fields[] = tclss.getClass().getDeclaredFields();// 获得对象所有属性
            String name;
            String varname;
            for (int i = 1; i < fields.length; i++) {
                if (fields[i].getGenericType().toString()
                        .equals("class java.lang.String")) {
                    name = fields[i].getName();
                    try {
                        boolean accessFlag = fields[i].isAccessible();
                        fields[i].setAccessible(true);
                        varname = name.replaceFirst(name.substring(0, 1), name
                                .substring(0, 1).toUpperCase());
                        Method m = null;
                        try {
                            m = tclss.getClass().getMethod(("get" + varname));
                            m.invoke(tclss.newInstance(), null);
                            System.out
                                    .println(varname + "的值是：" + m.invoke(obj));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } catch (IllegalArgumentException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        }
        if (infolist != null && infolist.size() > 0) {
            for (int i = 0; i < infolist.size(); i++) {
                row = sheet.createRow(i + 4);
                TableColumInfo showmap = (TableColumInfo) infolist.get(i);
                row.createCell(0).setCellValue((i + 1) + "");
                for (int j = 0; j < first.length; j++) {
                    // if(showmap.get(first[j])!=null){
                    // row.createCell(j+1).setCellValue(showmap.get(first[j])+"");
                    // }
                }
            }
            return hwb;
        } else {
            return hwb;
        }
    }

    @SuppressWarnings({ "rawtypes", "deprecation" })
    @Override
    public HSSFWorkbook excellmap(List<Map> maplist, String chinaname,
            Integer[] showstate) {
        ExcellExitInfo excelinfo = null;
        List<TableColumInfo> columlistall = tableService.getshowcolum(
                chinaname, showstate);
        List<TableColumInfo> columlistF = tableService.getshowcolum(chinaname,
                showstate, null,1);
        List<TableColumInfo> columlistS = tableService.getshowcolum(chinaname,
                showstate, "notnull",1);
        List<ExcellExitInfo> excellist = new ArrayList<ExcellExitInfo>(); 
        String[] first = new String[columlistall.size()]; // 总的表头信息
        String[] firstcol = new String[columlistall.size()]; // 总的表头信息
        String[] second = new String[columlistS.size()]; // 双行表头，第二行的信息
        String[] secondcol = new String[columlistS.size()]; // 双行表头，第二行的信息
        int pnum = 0;               //父汉字数组的序列
        int pindex=0;                //父列表的循环下标
        int cnum = 0;               //子汉字数组的序列
        int bjth = 0;               //标记是否是一个子
        int xnum = 0;               //数据数组的数据库表字段名数组
        int startnum=0;             //开始节点
        int endnum=0;               //长度
        boolean bjzt = true;
        try {
            for (int i = 0; i < columlistall.size(); i++) {
                /* 循环总字段数 作为总循环次数 */
                /* 取第一个父字段 作为第一次循环，并填写 父汉字数组和父数据库名数据 */
                /*
                 * 判断第一个父字段有没有子字段 如果有子字段，则父汉字数组无操作，父数据库名数组的本条数据变更为本次循环的子的名称
                 */
                first[pnum] = columlistF.get(pindex).getChinaname();
                firstcol[xnum] = columlistF.get(pindex).getColumname();
                TableColumInfo columinfo=columlistF.get(pindex);
                String pid=columinfo.getId();
                pindex++;
                startnum=pnum;
                for (int j = 0; j < columlistS.size(); j++) {
                    if(columlistS.get(j).getPtype().equals(pid)){
                        second[cnum]=columlistS.get(j).getChinaname();
                        if(bjzt){
                            firstcol[xnum]=columlistS.get(j).getColumname();
                            bjzt=false;
                        }else{ 
                            firstcol[xnum]=columlistS.get(j).getColumname();
                            first[pnum] = "";
                        } 
                        endnum++;
                        xnum++;
                        cnum++;
                        pnum++;
                        i++;
                        bjth=1;     //标记这是子的一个开始
                    }
                }
                if(bjzt){
                    pnum++;
                    xnum++; 
                }else{
                    bjzt=true;
                }

                if(bjth==1){
                    excelinfo=new ExcellExitInfo();
                    excelinfo.setStartcolm(startnum);
                    excelinfo.setSumcolm(endnum);
                    endnum=0;
                    excellist.add(excelinfo);
                    bjth=0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        HSSFWorkbook hwb = new HSSFWorkbook();
        /*
         * 本年度累计清欠社会保险费金额（万元） 涉及农民工金额 （万元） 涉及职工人数 本年度累计清欠工资金额（万元） 涉及农民工金额 （万元）
         * 涉及职工人数
         */
        HSSFSheet sheet = hwb.createSheet(new Date().getTime() + "report");
        HSSFRow btrow = sheet.createRow(0);
        HSSFRow sjrow = sheet.createRow(1);
        HSSFRow row = sheet.createRow(2);   
        HSSFRow row1 = sheet.createRow(3);
        HSSFCell cell;
        HSSFCellStyle stycle = hwb.createCellStyle();
        stycle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        // 添加第一行标题 -- 2016年11月21日 wangqn添加
        cell = btrow.createCell(0);
        cell.setCellValue(chinaname+"报表");
        cell.setCellStyle(stycle);
        sheet.addMergedRegion(new Region((short) 0, (short) 0, (short) 0,
                (short) (pnum-1)));
        // 添加第二行时间 -- 2016年11月21日 wangqn添加
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String exsj = "时间：" + sdf.format(new Date());
        cell = sjrow.createCell(0);
        cell.setCellValue(exsj);
        cell.setCellStyle(stycle);
        sheet.addMergedRegion(new Region((short) 1, (short) 0, (short) 1, (short) (pnum - 1)));
        // 表头标题合并修改 -- 2016年11月21日 wangqn修改
        Integer cxhindex=0;
        int startc = 0;
        int endcol =0;
        if(excellist!=null&&excellist.size()>0){ 
            excelinfo = excellist.get(excellist.size() - 1);
             startc = excellist.get(0).getStartcolm();
             endcol = excelinfo.getStartcolm() + excelinfo.getSumcolm()-1;
        }
        
        for (int i = 0; i < pnum; i++) {
            int startint=isshow(i, excellist);
            if (startint>=0) {
                for (int j = 0; j < excellist.size(); j++) {
                    if (i == excellist.get(j).getStartcolm()) {
                        cell = row.createCell(i);
                        cell.setCellValue(first[i]);
                        cell.setCellStyle(stycle);
                        sheet.addMergedRegion(new Region((short) 2, (short) i,
                                (short) 2, (short) (i + excelinfo.getSumcolm()-1)));
                        sheet.setColumnWidth((short) i,
                                first[i].getBytes().length * 200);
                    }
                } 
                cell = row1.createCell(i);
                cell.setCellValue(second[cxhindex]);
                cell.setCellStyle(stycle);
                sheet.setColumnWidth((short) i,
                        second[i - startint].getBytes().length * 200);
                cxhindex++;
            } else {
                cell = row.createCell(i);
                cell.setCellValue(first[i]);
                cell.setCellStyle(stycle);
                sheet.addMergedRegion(new Region((short)2, (short)i,
                        (short) 3, (short) i));
                sheet.setColumnWidth((short)i,
                        first[i].getBytes().length * 200);
            }
        }
        /*
         * ..........................................赋值开始........................
         * 
         */
        if (maplist != null && maplist.size() > 0) {
            for (int i = 0; i < maplist.size(); i++) {
                cnum = 0;
                row = sheet.createRow(i + 4);
                Map showmap = maplist.get(i);
//                row.createCell(0).setCellValue((i + 1) + "");
                for (int j = 0; j < pnum; j++) { 
                    if(showmap.get(firstcol[j])==null||"null".equals(showmap.get(firstcol[j]).toString().trim())){
                        row.createCell(j).setCellValue("");  
                    }else{
                        row.createCell(j).setCellValue(
                                showmap.get(firstcol[j]) + "");
                    }
                }
            }
            return hwb;
        } else {
            return hwb;
        }
    }

    private Integer isshow(Integer bjsz,List<ExcellExitInfo> excellist){
        for (int i = 0; i < excellist.size(); i++) {
            ExcellExitInfo excellinfo=excellist.get(i);
            System.out.println(bjsz+":"+excellinfo.getStartcolm()+"//"+(excellinfo.getStartcolm()+excellinfo.getSumcolm()-1));
            if(bjsz>=excellinfo.getStartcolm()&&bjsz<=(excellinfo.getStartcolm()+excellinfo.getSumcolm()-1)){
                return excellinfo.getStartcolm();
            }
        }
        return -1;
    }
    @Override
    public List<TableColumInfo> getshowcolum(String tablename,
            Integer[] showtype, String ptype,Integer usingtype) {
        List<TableColumInfo> showlist = null;
        String orderstring="exportsort";
        if(usingtype==null||usingtype==0){
            orderstring="listsort";
        }
        if (ptype == null || "".equals(ptype.trim())) {
            showlist = this.getOrderList(TableColumInfo.class,Order.asc(orderstring),
                    Restrictions.eq("tablename", tablename),
                    Restrictions.in("columstate", showtype),
                    Restrictions.isNull("ptype"), Restrictions.ne("pid", "0"));
        } else if ("notnull".equals(ptype)) {
            showlist = this.getOrderList(TableColumInfo.class,Order.asc(orderstring),
                    Restrictions.eq("tablename", tablename),
                    Restrictions.in("columstate", showtype),
                    Restrictions.isNotNull("ptype"),
                    Restrictions.ne("pid", "0"));
        } else {
            showlist = this.getOrderList(TableColumInfo.class,Order.asc(orderstring),
                    Restrictions.eq("tablename", tablename),
                    Restrictions.in("columstate", showtype),
                    Restrictions.eq("ptype", ptype),
                    Restrictions.ne("pid", "0"));
        }
        return showlist;
    }

}
