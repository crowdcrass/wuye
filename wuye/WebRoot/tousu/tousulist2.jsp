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
                处理投诉信息</th>
        </tr>
        
    </table>
    <br>
    <form action="method!tousulist2" method="post">
<div align="left">


业主姓名：<input type="text" name="huzhuxingming" value="${huzhuxingming}">



<input type="submit" value="搜索">
</div>
</form>
    
 
    <table class="table" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">
  <tbody>
    
    
    <tr>
      <td >业主身份证</td>
      <td >业主姓名</td>
      <td >投诉标题</td>
     
      <td >处理状态</td>
      <td >添加时间</td>

      <td class="td_bg" >操作</td>
    </tr>
    <c:forEach items="${list}" var="bean">
    <tr>
      <td >${bean.zhuhu.huzhusfz }</td>
      <td >${bean.zhuhu.huzhuxingming }</td>
      <td >${bean.title }</td>
      <td >${bean.chulijieguo }</td>
     
      <td >${fn:substring(bean.createtime,0, 19)}</td>

      <td class="td_bg" >
      <a href="method!tousuupdate3?id=${bean.id }"><span style="font-size: 15px;">查看投诉信息</span></a>
      <a href="method!tousuupdate5?id=${bean.id }"><span style="font-size: 15px;">处理投诉信息</span></a>

      </td>
    </tr>
    </c:forEach>
    
    
    
  
    
  </tbody>
</table>
${pagerinfo }
</body>
</html>
