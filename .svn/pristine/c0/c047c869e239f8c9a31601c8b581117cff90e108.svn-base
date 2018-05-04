/**
 * 文件名：InitHashtable.java
 * 创建日期：2016-2-15 上午9:31:14
 * 创建人：Administrator
 * cvs版本：$Reversion$ $Date: 2016/04/05 07:55:23 $
 * 修改记录：无
 *     [参考格式：修改人：Administrator 修改时间：2016-2-15 上午9:31:14 修改内容：X]
 */
package com.lingnet.util;

import java.util.Hashtable;

import javax.annotation.PostConstruct;

/**
 * 类名称：[中文类名]
 * 作者： Administrator
 */
public class InitHashtable {
	@SuppressWarnings("rawtypes")
    private Hashtable dtable;//待处理数据存储器
	@SuppressWarnings("rawtypes")
    private Hashtable htable;//处理中数据存储器
	@SuppressWarnings("rawtypes")
    private Hashtable stable;//处理成功数据存储器
	@SuppressWarnings("rawtypes")
    private Hashtable ftable;//处理失败数据存储器
	
	
	@SuppressWarnings({ "rawtypes", "unused" })
    private Hashtable dtableTmp;//待处理数据存储器
	@SuppressWarnings({ "rawtypes", "unused" })
    private Hashtable htableTmp;//处理中数据存储器
	@SuppressWarnings({ "rawtypes", "unused" })
    private Hashtable stableTmp;//处理成功数据存储器
	@SuppressWarnings({ "rawtypes", "unused" })
    private Hashtable ftableTmp;//处理失败数据存储器
	
	@SuppressWarnings("rawtypes")
	@PostConstruct
	public void init() {
		dtable = new Hashtable();
		htable = new Hashtable();
		stable = new Hashtable();
		ftable = new Hashtable();
	}
	
	@SuppressWarnings("rawtypes")
    public Hashtable getDtable(){
		return dtable;
	}
	
	@SuppressWarnings("rawtypes")
    public Hashtable getHtable(){
		return htable;
	}
	
	@SuppressWarnings("rawtypes")
    public Hashtable getStable(){
		return stable;
	}
	
	@SuppressWarnings("rawtypes")
    public Hashtable getFtable(){
		return ftable;
	}
	
}
