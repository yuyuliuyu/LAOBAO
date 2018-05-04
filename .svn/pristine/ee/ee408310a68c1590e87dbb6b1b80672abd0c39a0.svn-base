/**
 * 项目名：
 * 文件名：ToolUtil.java
 * 创建日期：2015-12-23 上午10:25:19
 * 创建人：Administrator
 * cvs版本：$Reversion$ $Date: 2016/11/14 03:39:34 $
 * 修改记录：无
 *     [参考格式：修改人：Administrator 修改时间：2015-12-23 上午10:25:19 修改内容：X]
 */
package com.lingnet.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.lingnet.qxgl.dao.UserDao;
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.qxgl.entity.QxRoles;
import com.lingnet.qxgl.entity.QxUser;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.qxgl.service.AdminService;

/**
 * 类名称：[中文类名] 作者： Administrator
 */

@Service("toolUtil")
public class ToolUtil {
    @Resource(name = "adminService")
    public AdminService adminService;
    @Resource(name = "userDao")
    private UserDao userDao;

    /** 获取当前用户名 **/
    public static String userName() {
        Object oPrincipal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String userName = ((UserDetails) oPrincipal).getUsername();// 用户名
        return userName;
    }

    /**
     * 
     * @Title: getUserId 获取当前用户登录ID
     * @return String
     * @author adam
     * @since 2015-10-8 V 1.0
     */
    @SuppressWarnings({ "static-access" })
    public String getUserId() {
        String userName = this.userName();
        QxUsers user = adminService.getUserByName(userName);
        String userId = "";
        if (user != null) {
            userId = user.getId();
        }
        return userId;
    }

    /**
     * 
     * @Title: getUserDeptId 获取当前用户部门id
     * @return String
     * @author adam
     * @since 2015-10-8 V 1.0
     */
    @SuppressWarnings({ "static-access" })
    public String getUserDeptId() {
        String deptId = "";
        String userName = this.userName();
        QxUsers user = adminService.getUserByName(userName);
        if (user != null) {
            deptId = user.getDepId();
        }
        return deptId;
    }

    /**
     * 获取六位邀请码或者六位短信验证码
     * 
     * @Title: invate
     * @return String
     * @since 2015-10-20 V 1.0
     */
    /*
     * public static String invate(){ String[] array =
     * {"0","1","2","3","4","5","6","7","8","9",
     * "A","B","C","D","E","F","G","H","I","J","K","L",
     * "M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"}; Random random =
     * new Random(); for(int i= 36 ; i > 1 ; i--){//将array进行字符串交换 int rand
     * =random.nextInt(i); String tem = array[rand]; array[rand] = array[i-1];
     * array[i-1] = tem; } String code = ""; for(int j = 0 ; j < 6 ; j++){ code
     * = array[j]+code; }
     * 
     * return code; }
     */

    /**
     * 获取8为用户注册code
     * 
     * @Title: invate String T 前缀 int P 随机码位数
     * @return String
     * @since 2015-10-20 V 1.0
     */
    public static String invate(String T, int P) {
        String[] array = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        Random random = new Random();
        String[] arrayP = new String[P];
        for (int i = 0; i < P; i++) {// 将array进行字符串交换
            int rand = random.nextInt(10);
            String tem = array[rand];
            arrayP[i] = tem;
        }
        String code = "";
        for (int j = 0; j < P; j++) {
            code = arrayP[j] + code;
        }

        return T + code;
    }

    public static String getWeather(String province, String city,
            String cityName) throws Exception {
        ClassLoader cld = Thread.currentThread().getContextClassLoader();
        InputStream io = null;
        io = cld.getResourceAsStream("weather.properties");

        Properties pro = new Properties();
        pro.load(io);
        String httpUrl = pro.getProperty("httpUrl");
        String ak = pro.getProperty("ak");
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?location=" + URLEncoder.encode(cityName, "UTF-8")
                + "&output=json&ak=" + ak;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            // connection.setRequestProperty("apikey",
            // "83644eb5a6c4f15d58182c488d374c0d");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static String
            cityCode(String province, String city, String cityName)
                    throws Exception {
        ClassLoader cld = Thread.currentThread().getContextClassLoader();
        InputStream io = cld.getResourceAsStream("/weather.properties");
        Properties po = new Properties();
        po.load(io);
        String httpUrl = po.getProperty("httpUrlCity");
        String httpArg = po.getProperty("httpArg");
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + httpArg + URLEncoder.encode(cityName, "UTF-8");

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey",
                    "83644eb5a6c4f15d58182c488d374c0d");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map map = JsonUtil.parseProperties(result);
        List<HashMap> list = JsonUtil.toList(map.get("retData").toString(),
                HashMap.class);

        String code = "";
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, String> dataMap = list.get(i);
                if (province.indexOf(dataMap.get("province_cn")) != -1) {
                    if (city.indexOf(dataMap.get("district_cn")) != -1) {
                        if (cityName.equals(dataMap.get("name_cn"))) {
                            code = dataMap.get("area_id");
                        }
                    }
                }
            }
        }
        return code;
    }

    /**
     * 获取配置文件里的配置 String property 配置文件名称 String name 需要获取的配置参数名称 return String
     * 配置参数
     * **/
    public static String getPropert(String property, String name) {
        String url = "";
        try {
            ClassLoader cls = Thread.currentThread().getContextClassLoader();
            InputStream in = cls.getResourceAsStream(property);
            Properties p = new Properties();

            p.load(in);
            if (p.containsKey(name)) {
                url = p.getProperty(name);
            }
        } catch (IOException e) {
        }

        return url;
    }

    /**
     * 获取距离
     * 
     * @Title: getDistance
     * @param qjd
     *            起点经度
     * @param qwd
     *            起点纬度
     * @param zjd
     *            终点经度
     * @param zwd
     *            终点纬度
     * @param qcity
     *            起点城市
     * @param zcity
     *            终点城市
     * @return HashMap
     * @author lsp
     * @throws Exception
     * @throws Exception
     * @since 2016-1-21 V 1.0
     */
    @SuppressWarnings("rawtypes")
    public static String getDistance(String qjd, String qwd, String zjd,
            String zwd, String qcity, String zcity) throws Exception {

        HashMap map = new HashMap();
        Double value = 0d;
        String http_header = ToolUtil.getPropert("filepath.properties",
                "baidumapapi");
        String http_end = ToolUtil.getPropert("filepath.properties",
                "baidumapapi_end");
        String origin = URLEncoder.encode(qwd + "," + qjd, "UTF-8");
        String destination = URLEncoder.encode(zwd + "," + zjd, "UTF-8");
        if (null != qcity && !"".equals(qcity)) {
            qcity = URLEncoder.encode(qcity, "UTF-8");
        } else {
            qcity = "";
        }
        if (null != zcity && !"".equals(zcity)) {
            zcity = URLEncoder.encode(zcity, "UTF-8");
        } else {
            zcity = "";
        }

        String httpUrl = http_header + origin + "&destination=" + destination
                + "&origin_region=" + qcity + "&destination_region=" + zcity
                + http_end;
        URL url = new URL(httpUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.connect();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        String lines = "";
        String ss = "";
        while ((lines = in.readLine()) != null) {
            ss += lines;
        }
        in.close();
        map = (HashMap) JsonUtil.parseProperties(ss);
        if (map.get("status").equals("0") && map.get("type").equals("2")) {
            HashMap map1 = (HashMap) JsonUtil.parseProperties(map.get("result")
                    .toString());
            System.out.println(map1);
            List<HashMap> list = JsonUtil.toList(map1.get("routes").toString(),
                    HashMap.class);
            for (HashMap hashmap : list) {
                value = Double.parseDouble(hashmap.get("distance").toString()) / 1000;
                break;
            }
        }
        return String.valueOf(value);

    }

    /**
     * 获取所有的map
     * 
     * @Title: getMap
     * @param map
     * @param resMap
     * @param _key
     * @return HashMap
     * @author lsp
     * @since 2016-1-22 V 1.0
     */
    @SuppressWarnings({ "rawtypes", "unchecked", "unused" })
    private static HashMap getMap(HashMap map, HashMap resMap, String _key) {
        Set<Entry> set = map.entrySet();
        for (Entry entry : set) {
            String key = (String) entry.getKey();
            String value = entry.getValue().toString();
            if (value.contains("{")) {
                if (value.startsWith("[")) {
                    List<Map<String, String>> list = JsonUtil.getMapList(value);
                    for (Map<String, String> hmap : list) {
                        if (_key != null && !"".equals(_key)) {
                            ToolUtil.getMap((HashMap) hmap, resMap, _key + "@"
                                    + key);
                        } else {
                            ToolUtil.getMap((HashMap) hmap, resMap, key);
                        }
                    }
                } else {
                    if (_key != null && !"".equals(_key)) {
                        ToolUtil.getMap(
                                (HashMap) JsonUtil.parseProperties(value),
                                resMap, _key + "@" + key);
                    } else {
                        ToolUtil.getMap(
                                (HashMap) JsonUtil.parseProperties(value),
                                resMap, key);
                    }
                }

            } else {
                /*
                 * if(resMap.containsKey(key)){ resMap.put(_key+"@"+key, value);
                 * }else{ resMap.put(key, value); }
                 */
                if (_key != null && !"".equals(_key)) {
                    resMap.put(_key + "@" + key, value);
                } else {
                    resMap.put(key, value);
                }

            }
        }
        System.out.println("====================================");
        System.out.println(resMap);
        return resMap;
    }

    /**
     * 传入要下载的图片的url列表，将url所对应的图片下载到本地
     * 
     * @param url
     *            图片路径
     */
    public static void downloadPicture(String url) throws Exception {
        // 获取当前目录的图片路径
        // String
        // path=this.getServletContext().getRealPath("/image/tuxing.png");
        String path = ServletActionContext.getServletContext().getRealPath(url);
        // 获取文件名
        String fileName = path.substring(path.lastIndexOf("\\") + 1);
        // 制定浏览器头
        // 在下载的时候这里是英文是没有问题的
        // resp.setHeader("content-disposition",
        // "attachment;fileName="+fileName);
        // 如果图片名称是中文需要设置转码
        HttpServletResponse resp = ServletActionContext.getResponse();
        ServletActionContext.getResponse().setHeader("content-disposition",
                "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
        InputStream reader = null;
        OutputStream out = null;
        byte[] bytes = new byte[1024];
        int len = 0;
        try {
            // 读取文件
            reader = new FileInputStream(path);
            // 写入浏览器的输出流
            out = resp.getOutputStream();

            while ((len = reader.read(bytes)) > 0) {
                out.write(bytes, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (out != null)
                out.close();
        }

    }

    /**
     * 两个时间差值
     * 
     * @time1
     * @time2
     */
    public static Long timedif(String time1, String time2) throws Exception {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");
        java.util.Date now = df.parse(time1);
        java.util.Date date = df.parse(time2);
        long l = now.getTime() - date.getTime();

        return l;
    }

    public static String getImg() throws Exception {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        InputStream in = cl.getResourceAsStream("filepath.properties");
        Properties p = new Properties();
        p.load(in);
        String url = "";
        if (p.containsKey("pic.url")) {
            url = p.getProperty("pic.url");
        }
        return url;
    }

    public static String code(int P) {
        String[] array = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        Random random = new Random();
        String[] arrayP = new String[P];
        for (int i = 0; i < P; i++) {// 将array进行字符串交换
            int rand = random.nextInt(10);
            String tem = array[rand];
            arrayP[i] = tem;
        }
        String code = "";
        for (int j = 0; j < P; j++) {
            code = arrayP[j] + code;
        }

        return code;
    }

    // 上次base64图片获取路径
    public static String imgUpload(Object image, String path) throws Exception {
        String img = "";
        if (image != null && !"".equals(image)) {
            img = image.toString();
        }
        // data.remove("img");
        // 头像修改
        Map<String, byte[]> picMap = UploadImageUtil.constructImageData(img);

        // 保存图片
        UploadImageUtil.uploadImage(picMap, path);
        String imgs[] = UploadImageUtil.getImageNames(picMap).split(",");

        return imgs[0];
    }

    public static Double gsh(Double data, int ws) {
        if (data != 0) {
            data = new BigDecimal(data)
                    .setScale(ws, BigDecimal.ROUND_HALF_EVEN).doubleValue();
        }
        return data;

    }

    /**
     * 获取汉字的拼音
     * 
     * @Title: getGuihua
     * @param src
     * @return String
     * @author yinzl
     * @since 2016-4-13 V 1.0
     */
    public static String getGuihua(String src) {
        char[] t1 = null;
        t1 = src.toCharArray();
        String[] t2 = new String[t1.length];
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                if (java.lang.Character.toString(t1[i]).matches(
                        "[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    t4 += t2[0];
                } else {
                    t4 += java.lang.Character.toString(t1[i]);
                }
            }
            return t4;
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return t4;
    }

    /**
     * 获取汉字的拼音首字母
     * 
     * @Title: getPinYinHeadChar
     * @param str
     * @return String
     * @author yinzl
     * @since 2016-4-13 V 1.0
     */
    public static String getGuihuaHeadChar(String str) {
        String convert = "";
        str = str.trim();
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = null;
            try {
                pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word,
                        new HanyuPinyinOutputFormat());
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert.trim().toUpperCase();
    }

    /**
     * 获取中文编码集
     * 
     * @Title: getChinese
     * @param s
     * @return String
     * @author yinzl
     * @since 2016-4-14 V 1.0
     */
    public static String getChinese(String s) {

        try {

            return new String(s.getBytes("gb2312"), "iso-8859-1");

        } catch (UnsupportedEncodingException e) {

            return s;

        }

    }

    @SuppressWarnings("static-access")
	public String getCId() {
        String userName = this.userName();
        QxUsers user = adminService.getUserByName(userName);
        String cId = "";
        if (user != null) {
           cId = user.getCid();
        }
        return cId;
    }
    public static HashMap<String,String> getProperties(String path) throws Exception {
    	HashMap<String,String> result=new HashMap<String, String>();
    	InputStream io = null;
    	 try {
	        ClassLoader cld = Thread.currentThread().getContextClassLoader();
	        io = cld.getResourceAsStream(path);
	        Properties pro = new Properties();
	        pro.load(io);
	        for(Object key:pro.keySet()){
	        	result.put(key.toString(), pro.getProperty(key.toString()));
	        }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	if(io!=null){
        		io.close();
        	}
        }
        return result;
    }
    
    /**
     * 返回当前数据库分中心ID
     * @Title: getDefaultCid 
     * @return 
     * String 
     * @author xuhp
     * @since 2016-11-7 V 1.0
     */
    public String getDefaultCid(){
    	Branch branch=adminService.get(Branch.class, Restrictions.eq("isDefault",1),Restrictions.eq("isDelete",0));
    	return branch==null?null:branch.getId();
    }
    /**
     * 修改PACS图片路径为映射路径
     * @Title: getPacsPath 
     * @param path
     * @return 
     * String 
     * @author xuhp
     * @since 2016-11-7 V 1.0
     */
    public static String getPacsPath(String path){
    	if(path!=null){
    		if(path.indexOf("\\\\192.168.6.200\\image$")!=-1){
    			path=path.replace("\\\\192.168.6.200\\image$", "oldpacs");
    		}else if(path.indexOf("\\\\192.168.200.193\\UserUpload")!=-1){
    			path=path.replace("\\\\192.168.200.193\\UserUpload", "newpacs");
    		}
    		path=path.trim();
    	}
    	return path;
    }
    /**
     * 修改PACS图片路径为映射路径
     * @Title: getPacsPathTwo 
     * @param path
     * @return 
     * String 
     * @author xuhp
     * @since 2016-12-6 V 1.0
     */
    public static String getPacsPathTwo(String path){
    	if(path!=null){
    		if(path.indexOf("\\\\192.168.6.200\\image$")!=-1){
    			path=path.replace("\\\\192.168.6.200\\image$", getPropert("doc_config.properties","oldpacs"));
    		}else if(path.indexOf("\\\\192.168.200.193\\UserUpload")!=-1){
    			path=path.replace("\\\\192.168.200.193\\UserUpload",getPropert("doc_config.properties","newpacs") );
    		}
    		path=path.trim();
    	}
    	return path;
    }
    /**
     * 判断是否拥有某权限
     * @Title: ownRole 
     * @param roleName
     * @return 
     * boolean 
     * @author xuhp
     * @since 2016-11-29 V 1.0
     */
    public final boolean ownRole(String roleName){
    	QxUser user=getCurrentUser();
    	if(user!=null){
	    	Set<QxRoles> roles=user.getQxRoles();
	    	for(QxRoles role:roles){
	    		if(roleName.equals(role.getName())){
	    			return true;
	    		}
	    	}
    	}
    	return false;
    }
    /**
     * 是否领导
     * @Title: isLeader 
     * @return 
     * String 
     * @author xuhp
     * @since 2016-11-29 V 1.0
     */
    public final String isLeader(){
    	QxUser user=getCurrentUser();
    	if(user==null){
    		return "0";
    	}else{
    		return user.getIsleader();
    	}
    }
    /**
     * 获取当前用户
     * @Title: getCurrentUser 
     * @return 
     * QxUsers 
     * @author xuhp
     * @since 2016-11-29 V 1.0
     */
    public final QxUser getCurrentUser(){
    	String username=userName();
    	userDao.get(Restrictions.eq("username", username));
    	return userDao.get(Restrictions.eq("username", username));
    }
}
