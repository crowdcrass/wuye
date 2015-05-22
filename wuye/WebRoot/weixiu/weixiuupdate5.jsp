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
               处理维修信息</th>
        </tr>
        
    </table>
    <br>
    <form method="post"   action="method!weixiuupdate6">
    <table class="table" cellspacing="1" cellpadding="2" width="80%" align="center" border="0">
  <tbody>
    
    
    <input type="hidden" name="id" value="${bean.id }"/>
    
     <tr>
      <td>维修标题:</td>
      <td><input type="text" name="title" value="${bean.title }" readonly="readonly"/></td>
      
    </tr>

    
    <tr>
      <td>维修内容:</td>
      <td>
      <textarea rows="7" cols="50" name="content" readonly="readonly">${bean.content }</textarea>
      </td>
      
    </tr>
    
    
    <tr>
      <td>维修处理结果:</td>
      <td>
      <select name="chulijieguo">
      <option value="未处理" <c:if test="${bean.chulijieguo=='未处理' }">selected</c:if> >未处理</option>
      <option value="正在处理" <c:if test="${bean.chulijieguo=='正在处理' }">selected</c:if> >正在处理</option>
       <option value="处理完成" <c:if test="${bean.chulijieguo=='处理完成' }">selected</c:if> >处理完成</option>
      </select>
      </td>
      
    </tr>

    
    <tr>
      <td>维修处理反馈:</td>
      <td>
      <textarea rows="7" cols="50" name="chulifankui">${bean.chulifankui }</textarea>
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






