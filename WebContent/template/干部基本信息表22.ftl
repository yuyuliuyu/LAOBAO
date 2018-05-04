<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8"/>
<title>无标题文档</title>
<style type="text/css">
	td{ border-left:1px solid #000; border-bottom:1px solid #000; vertical-align:middle; !important;}
	table{ border-top:1px solid #000; border-right:1px solid #000; width:100%; margin:0 auto;}
</style>
</head>
<body style="font-size:12.0pt; font-family:微软雅黑">
<h1 align="center" ><strong>员工登记表</strong><strong> </strong></h1>

<table border=1 cellspacing=0 cellpadding=0>
  <tr >
    <td width="92" height="60px" ><p align="center" style="height:60px" >姓名</p></td>
    <td width="73"  ><p align="center" >${name} </p></td>
    <td width="64"  colspan="2" ><p align="center" >性别</p></td>
    <td width="135"  ><p align="center" >${sex} </p></td>
    <td width="62"  ><p align="center" >出生年月</p></td>
    <td width="128"  colspan="3" ><p align="center" >${borthDate} </p></td>
    <td width="131"  rowspan="6" >
    	<p align="center" ><img src="${img}" />&nbsp;</p>
    </td>
  </tr>

  <tr >
    <td width="92"  height="60px"><p align="center" >民族</p></td>
    <td width="73"  ><p align="center" >${nation} </p></td>
    <td width="64"  colspan="2" ><p align="center" >籍贯</p></td>
    <td width="135"  ><p align="center" >${nativePlace} </p></td>
    <td width="62"  ><p align="center" >员工类别</p></td>
    <td width="128"  colspan="3" ><p align="center" >${empType}</p></td>
  </tr>
  <tr>
	<td width="92"  height="60px"><p align="center" >政治面貌</p></td>
    <td width="73"  ><p align="center" >&nbsp;</p></td>
    <td width="64"  height="60px" colspan="2"><p align="center" >入党时间</p></td>
    <td width="135"  ><p align="center" >&nbsp;</p></td>
	<td width="62"  height="60px"><p align="center" >集团工号</p></td>
    <td width="128"  colspan="3"><p align="center" >${jobNumberJt}</p></td>
  </tr>
  <tr>
  <td width="92" ><p align="center" >参加工<br/>作时间</p></td>
  <td width="73"  ><p align="center" >${setWorkDate} </p></td>
  
  <td width="64"  height="60px" colspan="2"><p align="center" >入司<br/>时间1</p></td>
    <td width="135"  ><p align="center" >${inCompany}</p></td>
	<td width="62"  height="60px"><p align="center" >入港<br/>时间1</p></td>
    <td width="128"  colspan="3"><p align="center" >${inPort}</p></td>
  </tr>
  <tr>
  <td width="92" ><p align="center" >原身份</p></td>
  <td width="73"  ><p align="center" >${formerIdentity} </p></td>
  
  <td width="64"  height="60px" colspan="2"><p align="center" >入司<br/>时间2</p></td>
    <td width="135"  ><p align="center" >${inCompany2}</p></td>
	<td width="62"  height="60px"><p align="center" >入港<br/>时间2</p></td>
    <td width="128"  colspan="3"><p align="center" >${inPort2}</p></td>
  </tr>
  <tr >
    <td width="92"  height="60px"><p align="center" >专业技<br/>
      术职务</p></td>
    <td width="137"  colspan="3" ><p align="center" >&nbsp;</p></td>
    <td width="135"  ><p align="center" >熟悉专业<br/>
      有何特长</p></td>
    <td width="191"  colspan="4" ><p align="center" >&nbsp;</p></td>
  </tr>
  <tr >
    <td width="92"  rowspan="2" height="120px"><p align="center" >&nbsp;</p>
      <p align="center" >学历<br/>
        学位</p></td>
    <td width="95"  colspan="2" height="60px"><p align="center" >全日制教育</p></td>
    <td width="177"  colspan="2" ><p align="center" >${educationLever} <br/>
      ${degree} </p></td>
    <td width="86"  colspan="2" ><p align="center" >毕业院校及专业</p></td>
    <td width="235"  colspan="3" ><p align="center" >${school} <br/>
      ${profession} </p></td>
  </tr>
  <tr >
    <td width="95"  colspan="2" ><p align="center" >在职教育</p></td>
    <td width="177"  colspan="2" ><p align="center" >${educationLever2} <br/>
      ${degree2}</p></td>
    <td width="86"  colspan="2" ><p align="center" >毕业院校及专业</p></td>
    <td width="235"  colspan="3" ><p align="center" >${school2} <br/>
      ${profession2} </p></td>
  </tr>
  <tr >
    <td width="187"  colspan="3" height="100px"><p align="center" >现任职务</p></td>
    <td width="500"  colspan="7" ><p align="center" >${dep} &nbsp;
      ${positionName} </p></td>
  </tr>
  <tr >
    <td width="187"  colspan="3" height="100px"><p align="center" ></p>
      <p align="center" >简    历</p>
      <p align="center" ></p></td>
    <td width="500"  colspan="7" ><p align="center" >
		<#list career as careerlist> 
			${careerlist}<br/>
		</#list>
	</p></td>
  </tr>
  <tr >
    <td width="187"  colspan="3" height="100px"><p align="center" ></p>
      <p align="center" >奖    惩<br/>
        情    况</p>
        <p align="center" ></p></td>
    <td width="500"  colspan="7" ><p align="center" > </p></td>
  </tr>
  <tr >
    <td width="187"  colspan="3" height="100px"><p align="center" ></p>
      <p align="center" >社会关系</p>
      <p align="center" ></p></td>
    <td width="500"  colspan="7" ><p align="center" ></p></td>
  </tr>
  <tr >
    <td width="187"  colspan="3" height="100px"><p align="center" ></p>
      <p align="center" >呈报单位</p>
      <p align="center" ></p></td>
    <td width="500"  colspan="7" ><p align="center" >&nbsp;</p>
      <p align="right" >日期 ：             </p></td>
  </tr>
  <tr >
    <td width="187"  colspan="3" >
      <p align="center" >审批机关意见</p></td>
    <td width="177"  colspan="2" ><p align="center" >&nbsp;</p>
      <p align="center" >&nbsp;</p>
      <p align="center" >盖章： <br/>
        日期： </p></td>
    <td width="91"  colspan="3" >
      <p align="center" >行政机关<br/>
        任免意见</p></td>
    <td width="231"  colspan="2" ><p align="right" >&nbsp;</p>
      <p align="center" >&nbsp;</p>
      <p align="center" >盖章： <br/>
        日期： </p></td>
  </tr>
</table>
</body>
</html>