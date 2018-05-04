package com.lingnet.hcm.entity.basic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;
/**
 * 动态表头
 * @ClassName: TableColumInfo 
 * @Description: TODO 
 * @author 马晓鹏
 * @date 2017年3月29日 上午8:59:21 
 *
 */
@Entity
@Table(name = "JC_TABLE_COLUM")
public class TableColumInfo  extends BaseEntity implements Serializable { 
    private static final long serialVersionUID = -7563857459247285776L;
  
    private String tablename;           //表名
    private String columname;           //字段名
    private String chinaname;           //中文名
    private Integer columtype;          //字段类型         1字符型；2。整型；3时间类型；4。浮点型
    private Integer columlength;        //字段长度
    private Integer columstate;         //展示状态          0.不显示；1显示；
    private String  pid;                //展示状态          0.不显示；   其他：子树
    private String  ptype;              //父级分类（双行表头的时候使用）
    private Integer listsort;           //列表展示顺序
    private Integer exportsort;         //导出顺序
    private String sjkType;           //子数据库编码
    public TableColumInfo() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Column(name = "TABLE_NAME")
    public String getTablename() {
        return tablename;
    }
    public void setTablename(String tablename) {
        this.tablename = tablename;
    }
    @Column(name = "COLUM_NAME")
    public String getColumname() {
        return columname;
    }
    public void setColumname(String columname) {
        this.columname = columname;
    }
    @Column(name = "CHINA_NAME")
    public String getChinaname() {
        return chinaname;
    }
    public void setChinaname(String chinaname) {
        this.chinaname = chinaname;
    }
    @Column(name = "COLUM_TYPE")
    public Integer getColumtype() {
        return columtype;
    }
    public void setColumtype(Integer columtype) {
        this.columtype = columtype;
    }
    @Column(name = "COLUM_LENGH")
    public Integer getColumlength() {
        return columlength;
    }
    public void setColumlength(Integer columlength) {
        this.columlength = columlength;
    }
    @Column(name = "COLUM_STATE")
    public Integer getColumstate() {
        return columstate;
    }
    public void setColumstate(Integer columstate) {
        this.columstate = columstate;
    }
    @Column(name = "PID")
    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }
    @Column(name = "LISTSORT")
    public Integer getListsort() {
        return listsort;
    }
    public void setListsort(Integer listsort) {
        this.listsort = listsort;
    }
    @Column(name = "EXPORTSORT")
    public Integer getExportsort() {
        return exportsort;
    }
    public void setExportsort(Integer exportsort) {
        this.exportsort = exportsort;
    }
    @Column(name = "PTYPE")
    public String getPtype() {
        return ptype;
    }
    public void setPtype(String ptype) {
        this.ptype = ptype;
    }
    @Column(name = "SJK_TYPE")
	public String getSjkType() {
		return sjkType;
	}
	public void setSjkType(String sjkType) {
		this.sjkType = sjkType;
	} 
} 