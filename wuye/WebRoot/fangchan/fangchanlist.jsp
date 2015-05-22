<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
    <link href="css/main.css" type="text/css" rel="stylesheet">
</head>
<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
    <br>
    <table class="usertableborder" cellspacing="1" cellpadding="3" width="96%" align="center"
        border="0">
        <tr>
            <th colspan="3">
                房产信息管理</th>
        </tr>
        
    </table>
    <br>
    <form action="method!fangchanlist" method="post">
<div align="left">


业主姓名：<input type="text" name="huzhuxingming" value="${huzhuxingming}">

门牌号：<input type="text" name="menpaihao" value="${menpaihao}">

<input type="submit" value="搜索">
</div>
</form>
    
  <a href="method!fangchanadd"><span style="font-size: 25px;">添加新的房产信息</span></a>  
    <table class="table" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">
  <tbody>
    
    
    <tr>
      <td >业主身份证</td>
      <td >业主姓名</td>
      <td >门牌号</td>
      <td >住址</td>
      <td >房屋结构</td>
      <td >设备</td>
      <td >房屋面积</td>
      <td >备注</td>

      <td class="td_bg" >操作</td>
    </tr>
    <c:forEach items="${list}" var="bean">
    <tr>
      <td >${bean.zhuhu.huzhusfz }</td>
      <td >${bean.zhuhu.huzhuxingming }</td>
      <td >${bean.menpaihao }</td>
      <td >${bean.zhuzhi }</td>
      <td >${bean.fangwujiegou }</td>
      <td >${bean.shebei }</td>
      <td >${bean.mianji }</td>
      <td >${bean.beizhu }</td>

      <td class="td_bg" >
      <a href="method!fangchanupdate3?id=${bean.id }"><span style="font-size: 15px;">查看房屋信息</span></a>
      <a href="method!fangchanupdate?id=${bean.id }"><span style="font-size: 15px;">更新房屋信息</span></a>
      <a href="method!fangchandelete?id=${bean.id }"><span style="font-size: 15px;">删除房屋信息</span></a>
      </td>
    </tr>
    </c:forEach>
    
    
    
  
    
  </tbody>
</table>
${pagerinfo }
</body>
</html>
