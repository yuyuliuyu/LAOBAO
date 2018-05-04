package com.lingnet.util.test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import com.lingnet.util.ExcelUtil;

public class ExcelFormulaTest {

    public static void main(String[] args) {
//        File file = new File("D:\\工伤缴费明细.xls");
//        try {
//            List<String[]> list = ExcelUtil.readData(0, 1, ".xls", file);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        String cntMath = "if (c1 == 2) {c3=9} else if (c1==4) {c3 = 1111} else {c3=999}";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("c1", "2");
        map.put("c2", 3);
        map.put("c3", "");
        JexlEngine jexl=new JexlEngine();  
        Expression e = jexl.createExpression(cntMath);
        JexlContext jc = new MapContext(map);
        e.evaluate(jc);
        Object result = jc.get("c3");
        System.out.println("result:" + result);
    }

}
