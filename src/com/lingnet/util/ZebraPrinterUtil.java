/**
 * @ZebraPrinter.java
 * @com.lingnet.mec.action.receptionist
 * @Description：
 * 
 * @author zhanghj
 * @copyright  2016
 * @version V
 * @since 2016-9-28
 */
package com.lingnet.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

import org.apache.commons.lang.StringUtils;

/** 条码打印机
 * @ClassName: ZebraPrinter
 * @Description: 条码打印机
 * @author zhanghj
 * @date 2016-9-28 下午6:28:46
 *  
 */
public class ZebraPrinterUtil {

    //标签格式以^XA开始
    private String begin = "^XA";

    //标签格式以^XZ结束 
    private String end = "^XZ";
    // 条码字符串
    private String text = "";
    // 条码指令
    private String content = "";
    // 条码样式模板
    private String barStyle = "";
    private byte[] dotFont;
    private PrintService service = null;

    public ZebraPrinterUtil() {
    }

    public ZebraPrinterUtil(String text) {
        this.text = text;
        barStyle = "^FO110,110^BY6,3.0,280^BCN,,Y,N,N^FD${data}^FS";
        setBarcode(barStyle);
    }

    public ZebraPrinterUtil(String text, String barStyle) {
        this.text = text;
        this.barStyle = barStyle;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * @Title: 条码打印机初始化、加载lib
     * @param libPath lib地址
     * @param index 打印机顺序
     * @param name 打印机名称
     * void
     * @author zhanghj
     * @since 2016-9-28 V 1.0
     */
    public String init(String libPath, int index, String name) {
        // lib地址
        File file = new File(libPath);
        if (!file.exists()) {
            return "ts24.lib文件不存在！";
        }
        // 读取lib
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            dotFont = new byte[fis.available()];
            fis.read(dotFont);
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        // 选择打印机
        PrintService[] psZebra = PrintServiceLookup.lookupPrintServices(null, null);
        if (psZebra == null) {
            return "没有发现打印机！";
        } else {
            for (int i = 0; i < psZebra.length; i++) {
                //if (index == i) {
            	if(psZebra[i].toString().indexOf(name)!=-1){
            		service = psZebra[i];
            		System.out.println(service+"===========================");
            		break;
            	}
                //}
            }
            // 按照顺序找不到打印机再根据名字找
            if (service == null) {
                if (StringUtils.isBlank(name)) {
                    return "打印机名称不能为空！";
                }
                for (PrintService printService : psZebra) {
                    // 根据名字匹配
                    if (name.equals(printService.getName())) {
                        service = printService;
                        break;
                    }
                }
            }
            if (service == null) {
                return "打印机顺序和名称设置有问题！";
            }
        }

        return "success";
    }

    /** 
     * 设置条形码 
     * @param zpl 条码样式模板 
     */  
    public void setBarcode(String zpl) {
        content += zpl.replace("${data}", this.text);
    }

    /**
     * @Title: 添加英文、数字
     * @param str 英文、数字
     * @param x x坐标
     * @param y y坐标
     * @param width 宽度
     * @param height 高度
     * void
     * @author zhanghj
     * @since 2016-9-28 V 1.0
     */
    public void setChar(String str, int x, int y, int width, int height) {
        content += "^FO" + x + "," + y + "^A0," + height + "," + width + "^FD" + str + "^FS";
    }

    /** 
     * 添加英文、数字，顺时针旋转90度
     * @param str 英文、数字
     * @param x x坐标
     * @param y y坐标
     * @param width 宽度
     * @param height 高度
     */  
    public void setCharR(String str, int x, int y, int width, int height) {
        content += "^FO" + x + "," + y + "^A0R," + height + "," + width + "^FD" + str + "^FS";
    }

    /** 
     * 添加中文字符、英文字符、数字混合
     * @param str 中文、英文、数字
     * @param x x坐标
     * @param y y坐标
     * @param enWidth 英文字体宽度width
     * @param enHeight 英文字体高度height
     * @param es 英文字体间距spacing
     * @param mx 中文x轴字体图形放大倍率。范围1-10，默认1
     * @param my 中文y轴字体图形放大倍率。范围1-10，默认1
     * @param ms 中文字体间距。24是个比较合适的值。
     */ 
    public void setMore(String str, int x, int y, int enWidth, int enHeight, int es, int mx, int my, int ms) {
        byte[] ch = str2bytes(str);
        for (int off = 0; off < ch.length;) {
            if (((int) ch[off] & 0x00ff) >= 0xA0) {
                int qcode = ch[off] & 0xff;
                int wcode = ch[off + 1] & 0xff;
                content += String.format("^FO%d,%d^XG0000%01X%01X,%d,%d^FS\n", x, y, qcode, wcode, mx, my);
                begin += String.format("~DG0000%02X%02X,00072,003,\n", qcode, wcode);
                qcode = (qcode + 128 - 32) & 0x00ff;
                wcode = (wcode + 128 - 32) & 0x00ff;
                int offset = ((int) qcode - 16) * 94 * 72 + ((int) wcode - 1) * 72;
                for (int j = 0; j < 72; j += 3) {
                    qcode = (int) dotFont[j + offset] & 0x00ff;
                    wcode = (int) dotFont[j + offset + 1] & 0x00ff;
                    int qcode1 = (int) dotFont[j + offset + 2] & 0x00ff;
                    begin += String.format("%02X%02X%02X\n", qcode, wcode, qcode1);
                }
                x = x + ms * mx;
                off = off + 2;
            } else if (((int) ch[off] & 0x00FF) < 0xA0) {
                setChar(String.format("%c", ch[off]), x, y, enHeight, enWidth);
                x = x + es;
                off++;
            }
        }
    }

    /**
     * @Title: 获取完整的ZPL
     * @return
     * String
     * @author zhanghj
     * @since 2016-9-28 V 1.0
     */
    public String getZpl() {
        return begin + content + end;
    }

    /**
     * @Title: 打印条码
     * @throws Exception
     * Boolean
     * @author zhanghj
     * @since 2016-9-28 V 1.0
     */
    public Boolean print() throws Exception {
        return this.print(getZpl());
    }

    /**
     * @Title: 打印条码
     * @param zpl 完整的打印指令
     * @throws Exception
     * Boolean
     * @author zhanghj
     * @since 2016-9-28 V 1.0
     */
    public Boolean print(String zpl) throws Exception {
        if (null == service) {
            throw new NullPointerException("打印机不能为空！");
        }
        DocPrintJob job = service.createPrintJob();
        byte[] by = zpl.getBytes();
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc doc = new SimpleDoc(by, flavor, null);
        job.print(doc, null);

        return true;
    }

    /**
     * @Title: 重置ZPL指令,多张打印时需要调用
     * void
     * @author zhanghj
     * @since 2016-9-28 V 1.0
     */
    public void resetZpl() {
        begin = "^XA";
        end = "^XZ";
        content = "";
    }

    /**
     * @Title: 字符串转byte[]
     * @param s
     * @return
     * byte[]
     * @author zhanghj
     * @since 2016-9-28 V 1.0
     */
    private byte[] str2bytes(String s) {
        if (null == s || "".equals(s)) {
            return null;
        }
        byte[] abytes = null;
        try {
            abytes = s.getBytes("gb2312");
        } catch (UnsupportedEncodingException ex) {
        }

        return abytes;
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) throws Exception {
        String path = "D:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 7.0\\webapps\\medical\\template\\plugin\\Zebra\\ts24.lib";
        // 打印单个条码
        ZebraPrinterUtil zebraPrinter = new ZebraPrinterUtil("");
       // zebraPrinter.init(path, 1, "ZDesigner 888-TT");
       // Boolean result = zebraPrinter.print();
       // System.out.println("result:"+result);

        // 清空
      //  zebraPrinter.resetZpl();

        // 打印中、英、数字、条码混合
        zebraPrinter = new ZebraPrinterUtil();
        zebraPrinter.init(path, 1, "ZDesigner 888-TT");
        //左边的条码  
        String bar1 = "07";  
        zebraPrinter.setChar(bar1, 190, 130, 60, 60);
        String bar1Zpl = "^FO100,200^BY8,3.0,240^BCR,,N,N,N^FD${data}^FS";//条码样式模板  
        zebraPrinter.setText(bar1);
        zebraPrinter.setBarcode(bar1Zpl);  
        //下边的条码  
        String bar2 = "00000999990018822969";//20位  
        String bar2Paper = "^FO380,600^BY3,3.0,100^BCN,,Y,N,N^FD${data}^FS";//条码样式模板  
        zebraPrinter.setText(bar2);
        zebraPrinter.setBarcode(bar2Paper);  
          
        zebraPrinter.setMore("国药控股湖南有限公司", 380, 40, 60, 60, 30, 2, 2, 24);  
        zebraPrinter.setChar("CSS0BPKPPR", 380, 100, 60, 60);  
          
        zebraPrinter.setMore("09件",940, 80, 60, 60, 30, 2, 2, 24);  
        zebraPrinter.setMore("补", 1100, 80, 60, 60, 30, 2, 2, 24);  
          
        zebraPrinter.setMore("公司自配送 公路", 380, 180, 80, 80, 30, 3, 3, 24);  
        zebraPrinter.setChar("03231151",940, 187, 40, 40);  
        zebraPrinter.setChar("2015-10-10",940, 227, 30, 30);  
          
        zebraPrinter.setMore("湖南六谷大药房连锁有限公司", 380, 260, 60, 60, 30, 2, 2, 24);  
          
        zebraPrinter.setMore("长沙市开福区捞刀河镇中岭村258号", 380, 320, 60, 60, 30, 2, 2, 22);  
          
        zebraPrinter.setMore("多SKU", 800, 485, 60, 60, 30, 2, 2, 24);  
          
        zebraPrinter.setMore("库位:49", 380, 420, 56, 56, 30, 2, 2, 24);  
        zebraPrinter.setMore("品类:感冒胶囊", 380, 485, 56, 56, 30, 2, 2, 24);  
          
        zebraPrinter.setMore("批号:", 380, 550, 56, 56, 30, 2, 2, 24);  
        zebraPrinter.setChar("78787878788", 500, 560, 40, 40);  
          
        String zpl2 = zebraPrinter.getZpl();  
        System.out.println(zpl2);  
        boolean result2 = zebraPrinter.print(zpl2);
        System.out.println("result2:"+result2);

        // 自测(128 code)
        String zpl = "^FO100,200^BCN,100,Y,N,N,N^FD${data}^FS";
    }
}
