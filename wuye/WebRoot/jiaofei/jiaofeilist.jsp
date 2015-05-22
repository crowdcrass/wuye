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
               缴费信息管理</th>
        </tr>
        
    </table>
    <br>
    <form action="method!jiaofeilist" method="post">
<div align="left">


业主姓名：<input type="text" name="huzhuxingming" value="${huzhuxingming}">

门牌号：<input type="text" name="menpaihao" value="${menpaihao}">

缴费类型: <select name="leixing">

 		<option value="" >所有类型</option>
      <option value="水费" <c:if test="${leixing=='水费' }">selected</c:if> >水费</option>
      <option value="电费"  <c:if test="${leixing=='电费' }">selected</c:if>>电费</option>
      <option value="宽带"  <c:if test="${leixing=='宽带' }">selected</c:if>>宽带</option>
      <option value="垃圾清运费"  <c:if test="${leixing=='垃圾清运费' }">selected</c:if>>垃圾清运费</option>
      <option value="物业管理费"  <c:if test="${leixing=='物业管理费' }">selected</c:if>>物业管理费</option>
      </select>
      
缴费状态: <select name="shoufeizhuangtail">
<option value="" >所有状态</option>
      <option value="未缴费" <c:if test="${shoufeizhuangtail=='未缴费' }">selected</c:if> >未缴费</option>
      <option value="已缴费"  <c:if test="${shoufeizhuangtail=='已缴费' }">selected</c:if>>已缴费</option>
    
      </select>

<input type="submit" value="搜索">
</div>
</form>
    
  <a href="method!jiaofeiadd"><span style="font-size: 25px;">添加新的缴费信息</span></a>  
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
      <a href="method!jiaofeiupdate?id=${bean.id }"><span style="font-size: 15px;">更新缴费信息</span></a>
      <a href="method!jiaofeidelete?id=${bean.id }"><span style="font-size: 15px;">删除缴费信息</span></a>
      </td>
    </tr>
    </c:forEach>
    
    
    
  
    
  </tbody>
</table>
${pagerinfo }
</body>
</html>
