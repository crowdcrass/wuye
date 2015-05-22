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
                更新缴费信息</th>
        </tr>
        
    </table>
    <br>
    <form method="post"   action="method!jiaofeiupdate2">
    <table class="table" cellspacing="1" cellpadding="2" width="80%" align="center" border="0">
  <tbody>
    
    
    <tr>
      <td>业主房产信息:</td>
      <td>
      
      业主身份证：${bean.fangchan.zhuhu.huzhuxingming }&nbsp;&nbsp;&nbsp;门牌号：${bean.fangchan.menpaihao }
     
      </td>
      
    </tr>
    
    <tr>
      <td>缴费类型:</td>
      <td>
      ${bean.leixing }
      
      </td>
      
    </tr>
    <tr>
      <td>缴费名称:</td>
      <td><input type="text" name="feiyongzhouqi" value="${bean.feiyongzhouqi }"/></td>
      
    </tr>
     <tr>
      <td>费用:</td>
      <td><input type="text" name="feiyong" value="${bean.feiyong }"/></td>
      
    </tr>
    
    <input type="hidden" name="id" value="${bean.id }"/>
    <tr>
      <td>备注:</td>
      <td>
      <textarea rows="7" cols="50" name="beizhu">${bean.beizhu }</textarea>
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






