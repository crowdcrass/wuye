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
               缴费信息查询</th>
        </tr>
        
    </table>
    <br>
     
    <table class="table" cellspacing="1" cellpadding="2" width="100%" align="center" border="0">
  <tbody>
    
    
    <tr>
     <td >业主身份证</td>
     <td >业主姓名</td>
     <td >门牌号</td>
     <td >住址</td>
     <td >缴费类型</td>
     <td >缴费名称</td>
     <td >费用</td>

     <td >缴费状态</td>
      <td >备注</td>

      <td class="td_bg" >操作</td>
    </tr>
    <c:forEach items="${list}" var="bean">
    <tr>
      <td >${bean.fangchan.zhuhu.huzhusfz }</td>
      <td >${bean.fangchan.zhuhu.huzhuxingming }</td>
      <td >${bean.fangchan.menpaihao }</td>
      <td >${bean.fangchan.zhuzhi }</td>
      <td >${bean.leixing }</td>
      <td >${bean.feiyongzhouqi }</td>
      <td >${bean.feiyong }</td>
       <td >${bean.shoufeizhuangtail }</td>
      <td >${bean.beizhu }</td>

      <td class="td_bg" >
      <a href="method!jiaofeiupdate3?id=${bean.id }"><span style="font-size: 15px;">查看缴费信息</span></a>
   
      </td>
    </tr>
    </c:forEach>
    
    
    
  
    
  </tbody>
</table>
${pagerinfo }
</body>
</html>
