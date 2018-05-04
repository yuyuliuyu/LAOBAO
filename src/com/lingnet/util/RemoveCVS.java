package com.lingnet.util;

/** 
 * @ClassName: RemoveCVS 
 * @Description: 删除版本信息 
 * @author 张明峰
 * @date 2014-4-15 下午3:20:35 
 *  
 */

import java.io.File;
import java.util.LinkedList;



public class RemoveCVS {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public RemoveCVS(String srcFolderPath) {
        LinkedList folderList = new LinkedList();
        File firstLevel = new File(srcFolderPath);
        File fileList[] = firstLevel.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isDirectory()) {
                if (!fileList[i].getName().equals("CVS")) {
                    folderList.add(fileList[i]);
                } else {
                    removeDir(fileList[i]);
                }
            } else {
                System.out.println(fileList[i].getAbsolutePath());
            }
        }
        File tempFile;
        while (!folderList.isEmpty()) {
            tempFile = (File) folderList.removeFirst();
            if (tempFile.isDirectory()) {
                fileList = tempFile.listFiles();
                if (fileList == null) {
                    continue;
                }
                for (int i = 0; i < fileList.length; i++) {
                    if (fileList[i].isDirectory()) {
                        if (!fileList[i].getName().equals("CVS")) {
                            folderList.add(fileList[i]);
                        } else {
                            removeDir(fileList[i]);
                        }
                    } else {
                        System.out.println(fileList[i].getAbsolutePath());
                    }
                }
            } else {
                System.out.println(tempFile.getAbsolutePath());
            }
        }
    }
    private void removeDir(File cvsFolder) {
        File[] fileList = cvsFolder.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            File file = fileList[i];
            if (file.isDirectory()) {
                removeDir(file);
            } else {
                file.delete();
            }
        }
        cvsFolder.delete();
    }
    public static void main(String[] args) {
        new RemoveCVS("E:/mywork/hcm");
    }
    
    
/*public String testSaveOrUpdate() {
        
        locMovem =  JsonUtil.toObject(formdata, LocMoveM.class);
        locMovem.setIndate(subTime(locMovem.getIndate()));

        List<Map<String, String>> gridMap = JsonUtil.getMapList(griddata);
        
        int i = 0;
        try{
            long avgtime = 0;
            while(true){
                i++;
                Date time0 = new Date();
                Long time1= time0.getTime();
                 locMoveService.saveOrUpdate(locMovem, gridMap,ahead);
                Date time2 = new Date();
                Long time3= time2.getTime();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
                avgtime = (avgtime+ (time3-time1));
                System.out.println(format.format(time2)+":1条数据第"+i+"次执行时间间隔为："+(time3-time1)+",平均间隔为："+avgtime/i);
            }
            
            
            
        }catch(Exception e){
            
            return ajax(Status.error, e.toString().substring(e.toString().indexOf(":")+1));
        }
           
         
    }*/
}

