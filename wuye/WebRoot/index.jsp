<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
if((model.User)session.getAttribute("user")==null){
	response.sendRedirect("login.jsp");
	return;
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
    <title>小区物业管理系统</title>
    <link href="css/style.css" type="text/css" rel="stylesheet">
    <link href="css/default.css" type="text/css" rel="stylesheet">

    <script language="javascript" src="js/menu.js" type="text/javascript"></script>

</head>
<body onLoad="javascript:border_left('left_tab1','left_menu_cnt1');">
    <form id="form1" runat="server">
        <table id="indextablebody" cellpadding="0">
            <thead>
                <tr>
                    <th>
                    <span style="font-size: 20px;font-weight: bold;">
                        小区物业管理系统
                       </span>
                    </th>
                    <th>
                        <a style="color: #16547E">用户 ：${user.username }</a>&nbsp;&nbsp; <a style="color: #16547E">
                        身份 ：<c:if test="${user.role==1}">系统管理员</c:if>
                        <c:if test="${user.role==0}">普通用户</c:if> </a>&nbsp;&nbsp;
                       
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td class="menu">
                        <ul class="bigbtu">
                            <li id="now01"><a title="安全退出" href="method!loginout">安全退出</a></li>
                            
                            
                        </ul>
                    </td>
                    <td class="tab">
                        <ul id="tabpage1">
                           
                           
                        </ul>
                    </td>
                </tr>
                <tr>
                    <td class="t1">
                        <div id="contents">
                            <table cellpadding="0">
                                <tr class="t1">
                                    <td>
                                        <div class="menu_top">
                                        </div>
                                    </td>
                                </tr>
                                <tr class="t2">
                                    <td>
                                        <div id="menu" class="menu">
                                            
                                            
                                            <c:if test="${user.role==1}">
                                            <ul class="tabpage2">
                                                <li id="left_tab1" title="操作菜单" onClick="javascript:border_left('left_tab1','left_menu_cnt1');"><span>基本</span></li>
                                            </ul>
                                            <div id="left_menu_cnt1" class="left_menu_cnt">
                                                <ul id="dleft_tab1">
                                                    <li id="now11"><a  href="method!zhuhulist" target="content3"><span>业主信息管理</span></a></li>
                                                   <li id="now11"><a  href="method!fangchanlist" target="content3"><span>房产信息管理</span></a></li>
                                                    <li id="now11"><a  href="method!jiaofeilist" target="content3"><span>缴费信息管理</span></a></li>
                                                    
                                                </ul>
                                            </div>
                                            <div class="clear">
                                            </div>
                                            
                                            
                                           
                                            
                                            <ul class="tabpage2">
                                                <li id="left_tab2" onClick="javascript:border_left('left_tab2','left_menu_cnt2');" title="操作菜单"><span>运营</span></li>
                                            </ul>
                                            <div id="left_menu_cnt2" class="left_menu_cnt">
                                                <ul id="dleft_tab2">
                                                    <li id="now11"><a  href="method!jiaofeilist2" target="content3"><span>收费管理</span></a></li>
                                                    <li id="now11"><a  href="method!weixiulist2" target="content3"><span>维修管理</span></a></li>
                                                    <li id="now11"><a  href="method!tousulist2" target="content3"><span>投诉管理</span></a></li>
                                                </ul>
                                            </div>
                                            <div class="clear">
                                            </div>
                                            
                                            <ul class="tabpage2">
                                                <li id="left_tab3" onClick="javascript:border_left('left_tab3','left_menu_cnt3');" title="操作菜单"><span>系统</span></li>
                                            </ul>
                                            <div id="left_menu_cnt3" class="left_menu_cnt">
                                                <ul id="dleft_tab3">
                                                    <li id="now11"><a  href="method!userlist" target="content3"><span>系统账号管理</span></a></li>
                                                     <li id="now11"><a  href="method!changepwd" target="content3"><span>修改系统密码</span></a></li>
                                                </ul>
                                            </div>
                                            <div class="clear">
                                            </div>
                                            
                                            </c:if>
                                            
                                            <c:if test="${user.role==0}">
                                            <ul class="tabpage2">
                                                <li id="left_tab1" title="操作菜单" onClick="javascript:border_left('left_tab1','left_menu_cnt1');"><span>查询</span></li>
                                            </ul>
                                            <div id="left_menu_cnt1" class="left_menu_cnt">
                                                <ul id="dleft_tab1">
                                                    <li id="now11"><a  href="method!zhuhulist2" target="content3"><span>业主信息查询</span></a></li>
                                                   <li id="now11"><a  href="method!fangchanlist2" target="content3"><span>房产信息查询</span></a></li>
                                                    <li id="now11"><a  href="method!jiaofeilist3" target="content3"><span>缴费信息查询</span></a></li>
                                                    
                                                </ul>
                                            </div>
                                            <div class="clear">
                                            </div>
                                            
                                            
                                           
                                            
                                            <ul class="tabpage2">
                                                <li id="left_tab2" onClick="javascript:border_left('left_tab2','left_menu_cnt2');" title="操作菜单"><span>业主</span></li>
                                            </ul>
                                            <div id="left_menu_cnt2" class="left_menu_cnt">
                                                <ul id="dleft_tab2">
                                                    <li id="now11"><a  href="method!weixiulist" target="content3"><span>维修管理</span></a></li>
                                                    <li id="now11"><a  href="method!tousulist" target="content3"><span>投诉管理</span></a></li>
                                                </ul>
                                            </div>
                                            <div class="clear">
                                            </div>
                                            
                                            <ul class="tabpage2">
                                                <li id="left_tab3" onClick="javascript:border_left('left_tab3','left_menu_cnt3');" title="操作菜单"><span>系统</span></li>
                                            </ul>
                                            <div id="left_menu_cnt3" class="left_menu_cnt">
                                                <ul id="dleft_tab3">
                                                    <li id="now11"><a  href="method!userlist2" target="content3"><span>系统账号查询</span></a></li>
                                                     <li id="now11"><a  href="method!changepwd" target="content3"><span>修改系统密码</span></a></li>
                                                </ul>
                                            </div>
                                            <div class="clear">
                                            </div>
                                            
                                            
                                            </c:if>
                                            
                                            
                                        </div>
                                        <tr class="t3">
                                            <td>
                                                <div class="menu_end">
                                                </div>
                                            </td>
                                        </tr>
                            </table>
                        </div>
                    </td>
                    <td class="t2">
                        <div id="cnt">
                            <div id="dtab1">
                                <iframe name="content3" src="main.jsp" frameborder="0"></iframe>
                            </div>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>

        <script>
        //修改标题
        function show_title(str){
	        document.getElementById("spanTitle").innerHTML=str;
        }
        </script>
    </form>
</body>
</html>

