package com.lingnet.common.entity;

import java.io.Serializable;

public class ResultEntity implements Serializable {

	private static final long serialVersionUID = -546772579454685270L;

	/**
	 *webservice处理成功与否的标志  
	 *0（失败）；
	 *1（成功）；
	 *2（我关注的商品删除成功）；
	 *3（我关注的商品删除失败）；
	 *4（加入购物车成功）；
	 *5（加入购物车失败）；
	 *6（修改购物车成功）
	 *7（修改购物车失败）
	 *8（删除购物车信息成功）
	 *9（删除购物车信息失败）
	 *10（购物车中订单提交成功）
	 *11（购物车中订单提交失败）
	 *12  消息删除成功 
	 *13  消息删除失败 
	 *14 商品收藏（关注）成功
	 *15 商品收藏失败
	 *16 商品点赞成功
	 *17 商品点赞失败
	 *18 商品评论成功
	 *19 商品评论失败
	 *20 取消商品关注成功
	 *21 取消商品关注失败
	 *22 获取众筹商品回报方式成功
	 *23 获取众筹商品回报方式失败
	 *24 添加/修改收货人信息成功
	 *25 添加/修改收货人信息失败
	 *26 删除收货人信息成功
	 *27 删除收货人信息失败
	 *28 提现成功
	 *29 提现失败
	 *30 商品点赞超出10次
	 *31 订单删除成功
	 *32 订单删除失败
	 *33 购物车中商品转入收藏成功
	 *34 购物车中商品转入收藏失败
	 *35 换货完成（确认收货）成功
	 *36 换货完成（确认收货）失败
	 *37 付款成功
	 *38 付款失败
	 *39 消息管理中回复消息成功
	 *40 消息管理中回复消息失败
	 *41 获取商品评论列表成功
	 *42 获取商品评论列表失败
	 *43 众筹商品我要支持成功
	 *44 众筹商品我要支持失败
	 *45 赠送好友最后一步填写地址成功
	 *46 赠送好友最后一步填写地址失败
	 *47 创意币方式付款成功
	 *48 创意币方式付款失败
	 *49 增送好友流程最后一步（好友填写完地址后，用户点击确认）操作成功
	 *50 增送好友流程最后一步（好友填写完地址后，用户点击确认）操作失败
	 *51 从订单页面支付时获取流水号成功
	 *52 从订单页面支付时获取流水号失败
	 */
	private Integer result;
	
	private ErrorEntity error;//错误实体类 
	
	private String data;//程序无误后返回的查询数据
	
	/**
	 * 无参构造器
	 */
	public ResultEntity() {
		super();
	}

	/**
	 * 有参构造器
	 */
	public ResultEntity(Integer result, ErrorEntity error,
			String data) {
		super();
		this.result = result;
		this.error = error;
		this.data = data;
	}

	public Integer getResult() {
		return result;
	}
	
	public void setResult(Integer result) {
		this.result = result;
	}

	public ErrorEntity getError() {
		return error;
	}

	public void setError(ErrorEntity error) {
		this.error = error;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	
}
