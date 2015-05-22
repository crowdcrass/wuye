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
                添加新房产信息</th>
        </tr>
        
    </table>
    <br>
    <form method="post"   action="method!fangchanadd2">
    <table class="table" cellspacing="1" cellpadding="2" width="80%" align="center" border="0">
  <tbody>
    
    
    <tr>
      <td>业主信息:</td>
      <td>
      <select name="zhuhu">
      <c:forEach items="${list2}" var="bean2">
      <option value="${bean2.id }">业主身份证：${bean2.huzhusfz }&nbsp;&nbsp;&nbsp;业主姓名：${bean2.huzhuxingming }</option>
      </c:forEach>
      
      </select>
      </td>
      
    </tr>
    
    <tr>
      <td>门牌号:</td>
      <td><input type="text" name="menpaihao" /></td>
      
    </tr>
    <tr>
      <td>住址:</td>
      <td><input type="text" name="zhuzhi" /></td>
      
    </tr>
     <tr>
      <td>房屋结构:</td>
      <td><input type="text" name="fangwujiegou" /></td>
      
    </tr>
     <tr>
      <td>设备:</td>
      <td><input type="text" name="shebei" /></td>
      
    </tr>
     <tr>
      <td>房屋面积:</td>
      <td><input type="text" name="mianji" /></td>
      
    </tr>
    
    <tr>
      <td>备注:</td>
      <td>
      <textarea rows="7" cols="50" name="beizhu"></textarea>
      </td>
      
    </tr>
    
     <tr>
      <td>操作:</td>
      <td>  <input type="submit" value="提交"/>
      &nbsp; &nbsp; &nbsp; &nbsp;
      <input type="reset" value="重置"/>
       &nbsp; &nbsp; &nbsp; &nbsp;
      <input  onclick="javascript:history.go(-1);"  type="button" value="返回" /></td>
      
    </tr>
    
  
    
  </tbody>
</table>
</form>
</body>
</html>






