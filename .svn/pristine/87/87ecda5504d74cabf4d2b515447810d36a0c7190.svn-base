package com.lingnet.common.entity;

import java.io.Serializable;

public class ErrorEntity implements Serializable {
	
	private static final long serialVersionUID = -5683288130687062759L;

	/**
	 * 错误代号：
	 * 0001 该账号（邮箱或手机）已经被注册
	 * 0002 程序抛出异常
	 * 0003 未查到数据
	 * 0004 用户名或密码错误
	 * 0005 数据格式错误
	 * 0006 已经提交过问卷或产品命名，不允许重复提交；或者已经收藏过商品，不允许重复收藏
	 * 0007 ---------- 正确代号！购物车列表中的totalNum，总数量（为方便IOS读取数据而设定）
	 * 						    消息管理列表中的totalNum，总数量（为方便IOS读取数据而设定）
	 * 0008 更新数据失败
	 * 0009 该手机已经被占用，不允许重复绑定
	 * 0010 该用户没有粉丝/好友
	 * 0011 点赞按钮已经点过或者用户根据ID删除或更新数据时无程序错误，但无对应记录或已经删除。一般因为用户网络卡多点了几次
	 * 0012 当前操作不处于对应的退货环节
	 * 0013 未按指定格式传递参数
	 * 0014 用户通过邮箱找回密码时输入的用户名与邮箱不匹配，不存在该用户
	 * 0015 用户注册时填写了已存在的昵称
	 * 0016 用户对商品的当天投票（点赞）次数超过10次
	 * 0017 用户对创意的当天投票（点赞）次数超过10次
	 * 0018 用户对某个创意提交的命名数量超过3个
	 * 0019 因用户没有设置别名而导致推送给用户的消息发送失败
	 * 0020 用户提交问卷时已经超出允许提交的人数>=100个
	 * 0021 ---------- 正确代号！账单管理详情列表页面的创意币/现金 的 收入/支出 数量
	 */
	private String errorCode;
	
	private String errorInfo;//错误信息
	
	/**
	 * 无参构造器
	 */
	public ErrorEntity() {
		super();
	}

	/**
	 * 无参构造器
	 */
	public ErrorEntity(String errorCode, String errorInfo) {
		super();
		this.errorCode = errorCode;
		this.errorInfo = errorInfo;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
}
