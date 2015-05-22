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
 

<body>


<table class="usertableborder" cellspacing="1" cellpadding="3" width="96%" align="center"
        border="0">
        <tr>
            <th colspan="3">
                查看业主信息</th>
        </tr>
        
    </table>
    <br>
    <form method="post"   action="method!zhuhuupdate2">
    <table class="table" cellspacing="1" cellpadding="2" width="30%" align="center" border="0">
  <tbody>
     <input type="hidden" name="id" value="${bean.id }"/>
    
    <tr>
      <td>业主身份证:</td>
      <td>
      <input type="text" name="huzhusfz" value="${bean.huzhusfz }" readonly="readonly"/>
      </td>
      
    </tr>
    <tr>
      <td>业主姓名:</td>
      <td><input type="text" name="huzhuxingming" value="${bean.huzhuxingming }" readonly="readonly"/></td>
      
    </tr>
     <tr>
      <td>电话号码:</td>
      <td><input type="text" name="dianhuahaoma" value="${bean.dianhuahaoma }" readonly="readonly"/></td>
      
    </tr>
     <tr>
      <td>住址:</td>
      <td><input type="text" name="zhuzhi" value="${bean.zhuzhi }" readonly="readonly"/></td>
      
    </tr>
     <tr>
      <td>入住时间:</td>
      <td><input type="text" name="ruzhushijian" value="${bean.ruzhushijian }" readonly="readonly"/></td>
      
    </tr>
    
     <tr>
      <td>操作:</td>
      <td> 

      <input  onclick="javascript:history.go(-1);"  type="button" value="返回" /></td>
      
    </tr>
    
  
    
  </tbody>
</table>
</form>
</body>
</html>






