import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

public class SimplePrint {
	byte[] dotfont;
	String s_prt = "^XA", s_prt_buffer="";

	public static void main(String[] args) {

		try {
			SimplePrint a = new SimplePrint();
                        a.setCommand();
			String str = a.getCommand();
			System.out.println(str);
			a.print(str );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SimplePrint() throws Exception {
		File file = new File("ts24.lib");
		FileInputStream fis = new FileInputStream(file);
		dotfont = new byte[fis.available()];
		fis.read(dotfont);
		fis.close();

	}

	protected void setCommand() {
		String barcode = "ABCD1234567890";			
		printCN("���ֲ���ABCD", 10, 30,30,34);		
		printCN("�л����񹲺͹�", 10, 60,30,34);
		printChar(barcode,30,30,30,50);
		printBarcode(barcode, 30, 60,60);	
	}
	protected String getCommand() {	
		return s_prt + s_prt_buffer+"^XZ";
	}
	
	public void print(String str) throws PrintException {

		PrintService psZebra = PrintServiceLookup.lookupDefaultPrintService();
		if (psZebra == null) {
			System.out.println("û�з��������ӡ��.");
			return;
		}
		DocPrintJob job = psZebra.createPrintJob();
		byte[] by = str.getBytes();
		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		Doc doc = new SimpleDoc(by, flavor, null);
		job.print(doc, null);
	}


	
/**
 * ��ӡ������ 
 * @param barcode �ַ���
 * @param xx x����
 * @param yy y����
 * @param height �߶�
 */
	protected void printBarcode(String barcode,int x, int y) {
		System.out.println(barcode);
//	s_prt_buffer +=  "^BY3^FS^FO"+x+","+y+"^BEN,"+h+"^FD" + barcode + "\n";
	s_prt_buffer +=  "^FO"+x+","+y+"^BY2,3,60^BCN,,Y,N^FD" + barcode + "\n";
}

/**
 * ��ӡӢ���ַ�������
 * @param str
 * @param x
 * @param y
 */
	protected void printChar(String str,int x, int y,int h,int w) {
		System.out.println(str);
	s_prt_buffer +="^FO"+x+","+y+"^A0,"+h+","+w+"^FD"+str+"^FS";
}
	protected void printCharR(String str,int x, int y,int h,int w) {
		System.out.println(str);
	s_prt_buffer +="^FO"+x+","+y+"^A0R,"+h+","+w+"^FD"+str+"^FS";
}
/**
 * ��ӡ�����ַ���
 * @param strCN
 * @param x
 * @param y
 */
	protected void printCN(String strCN, int x, int y,int h,int w,int b) {
		System.out.println(strCN);
		byte[] ch = str2bytes(strCN);
		for (int off = 0; off < ch.length;) {
			if (((int) ch[off] & 0x00ff) >= 0xA0) {
				int qcode = ch[off] & 0xff;
				int wcode = ch[off + 1] & 0xff;
				s_prt_buffer = s_prt_buffer
						+ String.format("^FO%d,%d^XG0000%01X%01X,%d,%d^FS\n", x,
								y, qcode, wcode,b,b);
				s_prt +=  String.format("~DG0000%02X%02X,00072,003,\n", qcode,
								wcode);
				qcode = (qcode + 128 - 32) & 0x00ff;
				wcode = (wcode + 128 - 32) & 0x00ff;
				int offset = ((int) qcode - 16) * 94 * 72 + ((int) wcode - 1)
						* 72;
				for (int j = 0; j < 72; j += 3) {
					qcode = (int) dotfont[j + offset] & 0x00ff;
					wcode = (int) dotfont[j + offset + 1] & 0x00ff;
					int qcode1 = (int) dotfont[j + offset + 2] & 0x00ff;
					s_prt +=  String.format("%02X%02X%02X\n", qcode, wcode,
									qcode1);
				}
				x = x + 25*b;
				off = off + 2;
			} else if (((int) ch[off] & 0x00FF) < 0xA0) {
				;
				printChar(String.format("%c",ch[off]), x,  y, h, w);
//				s_prt_buffer += String.format("^FO"+x+","+y+"^A0,"+h+","+w+"^FD%c^FS\n",ch[off]);
//				s_prt_buffer += "^FO"+x+","+y+"^A0,"+h+",20^FD"+ch[off]+"^FS\n";
				x = x + 15;
				off++;
			}
		}
	}

	byte[] str2bytes(String s) {
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
}