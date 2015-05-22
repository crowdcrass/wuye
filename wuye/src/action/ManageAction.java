package action;


import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Fangchan;
import model.Jiaofei;
import model.Tousu;
import model.User;
import model.Weixiu;
import model.Zhuhu;

import org.apache.struts2.ServletActionContext;

import util.Pager;
import util.Util;

import com.opensymphony.xwork2.ActionSupport;

import dao.FangchanDao;
import dao.JiaofeiDao;
import dao.TousuDao;
import dao.UserDao;
import dao.WeixiuDao;
import dao.ZhuhuDao;

public class ManageAction extends ActionSupport{
	
	
	private static final long serialVersionUID = -4304509122548259589L;
	
	private UserDao userDao;
	
	private String url = "./";
	
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	//程序入口界面
	public String index(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Util.init(request);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null){
			return "success2";
		}else{
			return "success1";
		}
	}
	
	
	//用户登录操作
	public String login() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		User user = userDao.selectBean(" where username = '"+username +"' and password= '"+password +"' and userlock=0 and role="+role);
		if (user!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			this.setUrl("index");
			return "redirect";
		} else {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('用户名或者密码错误');window.location.href='login.jsp';</script>");
		}
		return null;
	}
	
	//用户退出操作
	public String loginout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		this.setUrl("login.jsp");
		return SUCCESS;
	}
	
	
	//跳转到修改密码页面
	public String changepwd() {
		this.setUrl("user/password.jsp");
		return SUCCESS;
	}
	
	//修改密码操作
	public void changepwd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		User bean = userDao.selectBean(" where username= '"+u.getUsername()+"' and password= '"+password1+"'");
		if(bean!=null){
			bean.setPassword(password2);
			userDao.updateBean(bean);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('修改成功');</script>");
		}else{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('用户名或者密码错误');</script>");
		}
	}
	
	
	private ZhuhuDao zhuhuDao;


	public ZhuhuDao getZhuhuDao() {
		return zhuhuDao;
	}

	public void setZhuhuDao(ZhuhuDao zhuhuDao) {
		this.zhuhuDao = zhuhuDao;
	}
	
	
	//业主列表
	public String zhuhulist()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		String huzhuxingming = request.getParameter("huzhuxingming");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(huzhuxingming!=null&&!"".equals(huzhuxingming)){
			sb.append("huzhuxingming like '%"+huzhuxingming+"%'");
			sb.append(" and ");
			sb2.append("huzhuxingming like '%"+huzhuxingming+"%'");
			sb2.append(" and ");

			request.setAttribute("huzhuxingming", huzhuxingming);
		}
		sb.append(" zhuhulock=0 order by id desc");
		String where = sb.toString();
		sb2.append(" zhuhulock=0 ");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = zhuhuDao.selectBeanCount(where2);
		request.setAttribute("list", zhuhuDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!zhuhulist", "共有" + total + "条记录"));
		this.setUrl("zhuhu/zhuhulist.jsp");
		return SUCCESS;
	}

//跳转到添加业主页面
	public String zhuhuadd() {
		this.setUrl("zhuhu/zhuhuadd.jsp");
		return SUCCESS;
	}
//添加业主操作
	public void zhuhuadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String dianhuahaoma = request.getParameter("dianhuahaoma");
		String huzhusfz = request.getParameter("huzhusfz");
		String huzhuxingming = request.getParameter("huzhuxingming");
		String ruzhushijian = request.getParameter("ruzhushijian");
		String zhuzhi = request.getParameter("zhuzhi");
		User user = userDao.selectBean(" where username='"+username+"'  and userlock=0");
		if(user!=null){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('操作失败，该身份证已经存在');window.location.href='method!zhuhulist';</script>");
			return;
		}
		
		Zhuhu bean = new Zhuhu();
		bean.setDianhuahaoma(dianhuahaoma);
		bean.setHuzhusfz(huzhusfz);
		bean.setHuzhuxingming(huzhuxingming);
		bean.setRuzhushijian(ruzhushijian);
		bean.setZhuzhi(zhuzhi);
		zhuhuDao.insertBean(bean);
		user = new User();
		user.setCreatetime(new Date());
		user.setPassword("111111");
		user.setTruename(huzhuxingming);
		user.setUsername(username);
		user.setZhuhu(bean);
		userDao.insertBean(user);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!zhuhulist';</script>");

		
	}
//跳转到更新业主页面
	public String zhuhuupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Zhuhu bean = zhuhuDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("zhuhu/zhuhuupdate.jsp");
		return SUCCESS;
	}
//更新业主操作
	public void zhuhuupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String dianhuahaoma = request.getParameter("dianhuahaoma");
		String huzhusfz = request.getParameter("huzhusfz");
		String huzhuxingming = request.getParameter("huzhuxingming");
		String ruzhushijian = request.getParameter("ruzhushijian");
		String zhuzhi = request.getParameter("zhuzhi");
		Zhuhu bean = zhuhuDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setDianhuahaoma(dianhuahaoma);
		bean.setHuzhusfz(huzhusfz);
		bean.setHuzhuxingming(huzhuxingming);
		bean.setRuzhushijian(ruzhushijian);
		bean.setZhuzhi(zhuzhi);
		zhuhuDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!zhuhulist';</script>");
	}

//查看业主操作
	public String zhuhuupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Zhuhu bean = zhuhuDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("zhuhu/zhuhuupdate3.jsp");
		return SUCCESS;
	}
//删除业主操作
	public void zhuhudelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Zhuhu bean = zhuhuDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setZhuhulock(1);
		zhuhuDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!zhuhulist';</script>");
	}
	
	
	private FangchanDao fangchanDao;


	public FangchanDao getFangchanDao() {
		return fangchanDao;
	}

	public void setFangchanDao(FangchanDao fangchanDao) {
		this.fangchanDao = fangchanDao;
	}
	
	
	//房产列表
	public String fangchanlist()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		String huzhuxingming = request.getParameter("huzhuxingming");
		String menpaihao = request.getParameter("menpaihao");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(huzhuxingming!=null&&!"".equals(huzhuxingming)){
			sb.append(" zhuhu.huzhuxingming like '%"+huzhuxingming+"%'");
			sb.append(" and ");
			sb2.append(" zhuhu.huzhuxingming like '%"+huzhuxingming+"%'");
			sb2.append(" and ");

			request.setAttribute("huzhuxingming", huzhuxingming);
		}
		
		
		if(menpaihao!=null&&!"".equals(menpaihao)){
			sb.append(" menpaihao like '%"+menpaihao+"%'");
			sb.append(" and ");
			sb2.append(" menpaihao like '%"+menpaihao+"%'");
			sb2.append(" and ");

			request.setAttribute("menpaihao", menpaihao);
		}
		sb.append(" fangchanlock=0 order by id desc");
		String where = sb.toString();
		sb2.append(" fangchanlock=0 ");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = fangchanDao.selectBeanCount(where2);
		request.setAttribute("list", fangchanDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!fangchanlist", "共有" + total + "条记录"));
		this.setUrl("fangchan/fangchanlist.jsp");
		return SUCCESS;
	}

//跳转到添加房产页面
	public String fangchanadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list2", zhuhuDao.selectBeanList(0, 9999, " where  zhuhulock=0"));
		this.setUrl("fangchan/fangchanadd.jsp");
		return SUCCESS;
	}
//添加房产操作
	public void fangchanadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String beizhu = request.getParameter("beizhu");
		String fangwujiegou = request.getParameter("fangwujiegou");
		String menpaihao = request.getParameter("menpaihao");
		String shebei = request.getParameter("shebei");
		String zhuhu = request.getParameter("zhuhu");
		String mianji = request.getParameter("mianji");
		String zhuzhi = request.getParameter("zhuzhi");
		Fangchan bean = new Fangchan();
		bean.setBeizhu(beizhu);
		bean.setCreatetime(new Date());
		bean.setFangwujiegou(fangwujiegou);
		bean.setMenpaihao(menpaihao);
		bean.setMianji(mianji);
		bean.setShebei(shebei);
		bean.setZhuhu(zhuhuDao.selectBean(" where id= "+zhuhu));
		bean.setZhuzhi(zhuzhi);
		fangchanDao.insertBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!fangchanlist';</script>");

		
	}
//跳转到更新房产页面
	public String fangchanupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Fangchan bean = fangchanDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("list2", zhuhuDao.selectBeanList(0, 9999, " where  zhuhulock=0"));
		this.setUrl("fangchan/fangchanupdate.jsp");
		return SUCCESS;
	}
//更新房产操作
	public void fangchanupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String beizhu = request.getParameter("beizhu");
		String fangwujiegou = request.getParameter("fangwujiegou");
		String menpaihao = request.getParameter("menpaihao");
		String shebei = request.getParameter("shebei");
		String mianji = request.getParameter("mianji");
		String zhuzhi = request.getParameter("zhuzhi");
		Fangchan bean = fangchanDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setBeizhu(beizhu);
		bean.setFangwujiegou(fangwujiegou);
		bean.setMenpaihao(menpaihao);
		bean.setMianji(mianji);
		bean.setShebei(shebei);
		bean.setZhuzhi(zhuzhi);
		fangchanDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!fangchanlist';</script>");
	}

//查看房产操作
	public String fangchanupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Fangchan bean = fangchanDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("fangchan/fangchanupdate3.jsp");
		return SUCCESS;
	}
//删除房产操作
	public void fangchandelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Fangchan bean = fangchanDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setFangchanlock(1);
		fangchanDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!fangchanlist';</script>");
	}
	
	private JiaofeiDao jiaofeiDao;


	public JiaofeiDao getJiaofeiDao() {
		return jiaofeiDao;
	}

	public void setJiaofeiDao(JiaofeiDao jiaofeiDao) {
		this.jiaofeiDao = jiaofeiDao;
	}
	
	
	//缴费信息列表
	public String jiaofeilist()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		String huzhuxingming = request.getParameter("huzhuxingming");
		String menpaihao = request.getParameter("menpaihao");
		String leixing = request.getParameter("leixing");
		String shoufeizhuangtail = request.getParameter("shoufeizhuangtail");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(huzhuxingming!=null&&!"".equals(huzhuxingming)){
			sb.append(" fangchan.zhuhu.huzhuxingming like '%"+huzhuxingming+"%'");
			sb.append(" and ");
			sb2.append(" fangchan.zhuhu.huzhuxingming like '%"+huzhuxingming+"%'");
			sb2.append(" and ");

			request.setAttribute("huzhuxingming", huzhuxingming);
		}
		
		if(menpaihao!=null&&!"".equals(menpaihao)){
			sb.append(" fangchan.menpaihao like '%"+menpaihao+"%'");
			sb.append(" and ");
			sb2.append(" fangchan.menpaihao like '%"+menpaihao+"%'");
			sb2.append(" and ");

			request.setAttribute("menpaihao", menpaihao);
		}
		
		if(leixing!=null&&!"".equals(leixing)){
			sb.append(" leixing like '%"+leixing+"%'");
			sb.append(" and ");
			sb2.append(" leixing like '%"+leixing+"%'");
			sb2.append(" and ");

			request.setAttribute("leixing", leixing);
		}
		
		if(shoufeizhuangtail!=null&&!"".equals(shoufeizhuangtail)){
			sb.append(" shoufeizhuangtail like '%"+shoufeizhuangtail+"%'");
			sb.append(" and ");
			sb2.append(" shoufeizhuangtail like '%"+shoufeizhuangtail+"%'");
			sb2.append(" and ");

			request.setAttribute("shoufeizhuangtail", shoufeizhuangtail);
		}
		
		
		sb.append(" jiaofeilock=0 order by id desc");
		String where = sb.toString();
		sb2.append(" jiaofeilock=0 ");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = jiaofeiDao.selectBeanCount(where2);
		request.setAttribute("list", jiaofeiDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!jiaofeilist", "共有" + total + "条记录"));
		this.setUrl("jiaofei/jiaofeilist.jsp");
		return SUCCESS;
	}

//跳转到添加缴费信息页面
	public String jiaofeiadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list2", fangchanDao.selectBeanList(0, 9999, " where  fangchanlock=0"));
		this.setUrl("jiaofei/jiaofeiadd.jsp");
		return SUCCESS;
	}
//添加缴费信息操作
	public void jiaofeiadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String beizhu = request.getParameter("beizhu");
		String leixing = request.getParameter("leixing");
		String fangchan = request.getParameter("fangchan");
		String feiyong = request.getParameter("feiyong");
		String feiyongzhouqi = request.getParameter("feiyongzhouqi");
		Jiaofei bean = new Jiaofei();
		bean.setBeizhu(beizhu);
		bean.setCreatetime(new Date());
		bean.setFangchan(fangchanDao.selectBean(" where id= "+fangchan));
		bean.setLeixing(leixing);
		bean.setShoufeizhuangtail("未缴费");
		bean.setFeiyong(feiyong);
		bean.setFeiyongzhouqi(feiyongzhouqi);
		bean.setZhuhu(fangchanDao.selectBean(" where id= "+fangchan).getZhuhu());
		jiaofeiDao.insertBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!jiaofeilist';</script>");

		
	}
//跳转到更新缴费信息页面
	public String jiaofeiupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Jiaofei bean = jiaofeiDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("list2", fangchanDao.selectBeanList(0, 9999, " where  fangchanlock=0"));
		this.setUrl("jiaofei/jiaofeiupdate.jsp");
		return SUCCESS;
	}
//更新缴费信息操作
	public void jiaofeiupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String beizhu = request.getParameter("beizhu");
		String feiyong = request.getParameter("feiyong");
		String feiyongzhouqi = request.getParameter("feiyongzhouqi");
		Jiaofei bean = jiaofeiDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setBeizhu(beizhu);
		bean.setFeiyong(feiyong);
		bean.setFeiyongzhouqi(feiyongzhouqi);
		jiaofeiDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!jiaofeilist';</script>");
	}

//查看缴费信息操作
	public String jiaofeiupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Jiaofei bean = jiaofeiDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("jiaofei/jiaofeiupdate3.jsp");
		return SUCCESS;
	}
//删除缴费信息操作
	public void jiaofeidelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Jiaofei bean = jiaofeiDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setJiaofeilock(1);
		jiaofeiDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!jiaofeilist';</script>");
	}
	
	//收费管理
	public String jiaofeilist2()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		String huzhuxingming = request.getParameter("huzhuxingming");
		String menpaihao = request.getParameter("menpaihao");
		String leixing = request.getParameter("leixing");
		String shoufeizhuangtail = request.getParameter("shoufeizhuangtail");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(huzhuxingming!=null&&!"".equals(huzhuxingming)){
			sb.append(" fangchan.zhuhu.huzhuxingming like '%"+huzhuxingming+"%'");
			sb.append(" and ");
			sb2.append(" fangchan.zhuhu.huzhuxingming like '%"+huzhuxingming+"%'");
			sb2.append(" and ");

			request.setAttribute("huzhuxingming", huzhuxingming);
		}
		
		if(menpaihao!=null&&!"".equals(menpaihao)){
			sb.append(" fangchan.menpaihao like '%"+menpaihao+"%'");
			sb.append(" and ");
			sb2.append(" fangchan.menpaihao like '%"+menpaihao+"%'");
			sb2.append(" and ");

			request.setAttribute("menpaihao", menpaihao);
		}
		
		if(leixing!=null&&!"".equals(leixing)){
			sb.append(" leixing like '%"+leixing+"%'");
			sb.append(" and ");
			sb2.append(" leixing like '%"+leixing+"%'");
			sb2.append(" and ");

			request.setAttribute("leixing", leixing);
		}
		
		if(shoufeizhuangtail!=null&&!"".equals(shoufeizhuangtail)){
			sb.append(" shoufeizhuangtail like '%"+shoufeizhuangtail+"%'");
			sb.append(" and ");
			sb2.append(" shoufeizhuangtail like '%"+shoufeizhuangtail+"%'");
			sb2.append(" and ");

			request.setAttribute("shoufeizhuangtail", shoufeizhuangtail);
		}
		
		
		sb.append(" jiaofeilock=0 order by id desc");
		String where = sb.toString();
		sb2.append(" jiaofeilock=0 ");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = jiaofeiDao.selectBeanCount(where2);
		request.setAttribute("list", jiaofeiDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!jiaofeilist2", "共有" + total + "条记录"));
		this.setUrl("jiaofei/jiaofeilist2.jsp");
		return SUCCESS;
	}
	
	//确认收费操作
	public void jiaofeidelete2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Jiaofei bean = jiaofeiDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setShoufeizhuangtail("已缴费");
		jiaofeiDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!jiaofeilist2';</script>");
	}
	
	
	//系统账号管理
	public String userlist()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String truename = request.getParameter("truename");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(truename!=null&&!"".equals(truename)){
			sb.append(" zhuhu.huzhuxingming like '%"+truename+"%'");
			sb.append(" and ");
			sb2.append(" zhuhu.huzhuxingming like '%"+truename+"%'");
			sb2.append(" and ");

			request.setAttribute("truename", truename);
		}
		
		
		sb.append(" 0=0 and role=0 order by id desc");
		String where = sb.toString();
		sb2.append(" 0=0 and role=0");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = userDao.selectBeanCount(where2);
		request.setAttribute("list", userDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!userlist", "共有" + total + "条记录"));
		this.setUrl("user/userlist.jsp");
		return SUCCESS;
	}
	
	//停用账号操作
	public void userdelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		User bean = userDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setUserlock(1);
		userDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!userlist';</script>");
	}
	
	//启用账号操作
	public void userdelete2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		User bean = userDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setUserlock(0);
		userDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!userlist';</script>");
	}
	
	
	//业主信息查询
	public String zhuhulist2()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		String huzhuxingming = request.getParameter("huzhuxingming");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(huzhuxingming!=null&&!"".equals(huzhuxingming)){
			sb.append(" zhuhu.huzhuxingming like '%"+huzhuxingming+"%'");
			sb.append(" and ");
			sb2.append(" zhuhu.uzhuxingming like '%"+huzhuxingming+"%'");
			sb2.append(" and ");

			request.setAttribute("huzhuxingming", huzhuxingming);
		}
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		sb.append(" id="+user.getId()+"   order by id desc");
		String where = sb.toString();
		sb2.append(" id="+user.getId() );
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = userDao.selectBeanCount(where2);
		request.setAttribute("list", userDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!zhuhulist2", "共有" + total + "条记录"));
		this.setUrl("zhuhu/zhuhulist2.jsp");
		return SUCCESS;
	}
	
	
	//房产查询
	public String fangchanlist2()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		User u = userDao.selectBean(" where id= "+user.getId());
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		
		sb.append(" fangchanlock=0  and zhuhu.id="+u.getZhuhu().getId()+" order by id desc");
		String where = sb.toString();

		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = fangchanDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", fangchanDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!fangchanlist2", "共有" + total + "条记录"));
		this.setUrl("fangchan/fangchanlist2.jsp");
		return SUCCESS;
	}
	
	
	//缴费信息查询
	public String jiaofeilist3()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		String huzhuxingming = request.getParameter("huzhuxingming");
		String menpaihao = request.getParameter("menpaihao");
		String leixing = request.getParameter("leixing");
		String shoufeizhuangtail = request.getParameter("shoufeizhuangtail");
		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(huzhuxingming!=null&&!"".equals(huzhuxingming)){
			sb.append(" fangchan.zhuhu.huzhuxingming like '%"+huzhuxingming+"%'");
			sb.append(" and ");
			sb2.append(" fangchan.zhuhu.huzhuxingming like '%"+huzhuxingming+"%'");
			sb2.append(" and ");

			request.setAttribute("huzhuxingming", huzhuxingming);
		}
		
		if(menpaihao!=null&&!"".equals(menpaihao)){
			sb.append(" zhuhu.menpaihao like '%"+menpaihao+"%'");
			sb.append(" and ");
			sb2.append(" zhuhu.menpaihao like '%"+menpaihao+"%'");
			sb2.append(" and ");

			request.setAttribute("menpaihao", menpaihao);
		}
		
		if(leixing!=null&&!"".equals(leixing)){
			sb.append(" leixing like '%"+leixing+"%'");
			sb.append(" and ");
			sb2.append(" leixing like '%"+leixing+"%'");
			sb2.append(" and ");

			request.setAttribute("leixing", leixing);
		}
		
		if(shoufeizhuangtail!=null&&!"".equals(shoufeizhuangtail)){
			sb.append(" shoufeizhuangtail like '%"+shoufeizhuangtail+"%'");
			sb.append(" and ");
			sb2.append(" shoufeizhuangtail like '%"+shoufeizhuangtail+"%'");
			sb2.append(" and ");

			request.setAttribute("shoufeizhuangtail", shoufeizhuangtail);
		}
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		User u = userDao.selectBean(" where id= "+user.getId());
		
		sb.append(" jiaofeilock=0 and zhuhu.id="+u.getZhuhu().getId()+" order by id desc");
		String where = sb.toString();
		sb2.append(" jiaofeilock=0 and zhuhu.id="+u.getZhuhu().getId());
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = jiaofeiDao.selectBeanCount(where2);
		request.setAttribute("list", jiaofeiDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!jiaofeilist3", "共有" + total + "条记录"));
		this.setUrl("jiaofei/jiaofeilist3.jsp");
		return SUCCESS;
	}
	
	
	//系统账号查询
	public String userlist2()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String truename = request.getParameter("truename");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(truename!=null&&!"".equals(truename)){
			sb.append(" zhuhu.huzhuxingming like '%"+truename+"%'");
			sb.append(" and ");
			sb2.append(" zhuhu.huzhuxingming like '%"+truename+"%'");
			sb2.append(" and ");

			request.setAttribute("truename", truename);
		}
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		sb.append(" id="+user.getId()+" and role=0 order by id desc");
		String where = sb.toString();
		sb2.append(" id="+user.getId()+" and role=0");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = userDao.selectBeanCount(where2);
		request.setAttribute("list", userDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!userlist2", "共有" + total + "条记录"));
		this.setUrl("user/userlist2.jsp");
		return SUCCESS;
	}
	
	private WeixiuDao weixiuDao;


	public WeixiuDao getWeixiuDao() {
		return weixiuDao;
	}

	public void setWeixiuDao(WeixiuDao weixiuDao) {
		this.weixiuDao = weixiuDao;
	}
	
	
	//维修列表
	public String weixiulist()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		String huzhuxingming = request.getParameter("huzhuxingming");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(huzhuxingming!=null&&!"".equals(huzhuxingming)){
			sb.append(" zhuhu.huzhuxingming like '%"+huzhuxingming+"%'");
			sb.append(" and ");
			sb2.append(" zhuhu.huzhuxingming like '%"+huzhuxingming+"%'");
			sb2.append(" and ");

			request.setAttribute("huzhuxingming", huzhuxingming);
		}
		
		
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		sb.append(" weixiulock=0  and zhuhu.id="+user.getZhuhu().getId()+" order by id desc");
		String where = sb.toString();
		sb2.append(" weixiulock=0 and zhuhu.id="+user.getZhuhu().getId());
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = weixiuDao.selectBeanCount(where2);
		request.setAttribute("list", weixiuDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!weixiulist", "共有" + total + "条记录"));
		this.setUrl("weixiu/weixiulist.jsp");
		return SUCCESS;
	}

//跳转到添加维修页面
	public String weixiuadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list2", zhuhuDao.selectBeanList(0, 9999, " where  zhuhulock=0"));
		this.setUrl("weixiu/weixiuadd.jsp");
		return SUCCESS;
	}
//添加维修操作
	public void weixiuadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String content = request.getParameter("content");
		String title = request.getParameter("title");
		Weixiu bean = new Weixiu();
		bean.setChulijieguo("未处理");
		bean.setContent(content);
		bean.setCreatetime(new Date());
		bean.setTitle(title);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		bean.setZhuhu(user.getZhuhu());
		weixiuDao.insertBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!weixiulist';</script>");

		
	}
//跳转到更新维修页面
	public String weixiuupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Weixiu bean = weixiuDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("list2", zhuhuDao.selectBeanList(0, 9999, " where  zhuhulock=0"));
		this.setUrl("weixiu/weixiuupdate.jsp");
		return SUCCESS;
	}
//更新维修操作
	public void weixiuupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String content = request.getParameter("content");
		String title = request.getParameter("title");
		Weixiu bean = weixiuDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setContent(content);
		bean.setTitle(title);
		weixiuDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!weixiulist';</script>");
	}

//查看维修操作
	public String weixiuupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Weixiu bean = weixiuDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("weixiu/weixiuupdate3.jsp");
		return SUCCESS;
	}
//删除维修操作
	public void weixiudelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Weixiu bean = weixiuDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setWeixiulock(1);
		weixiuDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!weixiulist';</script>");
	}

	private TousuDao tousuDao;


	public TousuDao getTousuDao() {
		return tousuDao;
	}

	public void setTousuDao(TousuDao tousuDao) {
		this.tousuDao = tousuDao;
	}
	
	//投诉列表
	public String tousulist()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		String huzhuxingming = request.getParameter("huzhuxingming");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(huzhuxingming!=null&&!"".equals(huzhuxingming)){
			sb.append(" zhuhu.huzhuxingming like '%"+huzhuxingming+"%'");
			sb.append(" and ");
			sb2.append(" zhuhu.huzhuxingming like '%"+huzhuxingming+"%'");
			sb2.append(" and ");

			request.setAttribute("huzhuxingming", huzhuxingming);
		}
		
		
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		sb.append(" tousulock=0  and zhuhu.id="+user.getZhuhu().getId()+" order by id desc");
		String where = sb.toString();
		sb2.append(" tousulock=0 and zhuhu.id="+user.getZhuhu().getId());
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = tousuDao.selectBeanCount(where2);
		request.setAttribute("list", tousuDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!tousulist", "共有" + total + "条记录"));
		this.setUrl("tousu/tousulist.jsp");
		return SUCCESS;
	}

//跳转到添加投诉页面
	public String tousuadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list2", zhuhuDao.selectBeanList(0, 9999, " where  zhuhulock=0"));
		this.setUrl("tousu/tousuadd.jsp");
		return SUCCESS;
	}
//添加投诉操作
	public void tousuadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String content = request.getParameter("content");
		String title = request.getParameter("title");
		Tousu bean = new Tousu();
		bean.setChulijieguo("未处理");
		bean.setContent(content);
		bean.setCreatetime(new Date());
		bean.setTitle(title);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		bean.setZhuhu(user.getZhuhu());
		tousuDao.insertBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!tousulist';</script>");

		
	}
//跳转到更新投诉页面
	public String tousuupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Tousu bean = tousuDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("list2", zhuhuDao.selectBeanList(0, 9999, " where  zhuhulock=0"));
		this.setUrl("tousu/tousuupdate.jsp");
		return SUCCESS;
	}
//更新投诉操作
	public void tousuupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String content = request.getParameter("content");
		String title = request.getParameter("title");
		Tousu bean = tousuDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setContent(content);
		bean.setTitle(title);
		tousuDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!tousulist';</script>");
	}

//查看投诉操作
	public String tousuupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Tousu bean = tousuDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		this.setUrl("tousu/tousuupdate3.jsp");
		return SUCCESS;
	}
//删除投诉操作
	public void tousudelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Tousu bean = tousuDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setTousulock(1);
		tousuDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!tousulist';</script>");
	}
	
	
	//维修列表
	public String weixiulist2()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		String huzhuxingming = request.getParameter("huzhuxingming");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(huzhuxingming!=null&&!"".equals(huzhuxingming)){
			sb.append(" zhuhu.huzhuxingming like '%"+huzhuxingming+"%'");
			sb.append(" and ");
			sb2.append(" zhuhu.huzhuxingming like '%"+huzhuxingming+"%'");
			sb2.append(" and ");

			request.setAttribute("huzhuxingming", huzhuxingming);
		}
		
		
		
		
		
		sb.append(" weixiulock=0   order by id desc");
		String where = sb.toString();
		sb2.append(" weixiulock=0 ");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = weixiuDao.selectBeanCount(where2);
		request.setAttribute("list", weixiuDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!weixiulist2", "共有" + total + "条记录"));
		this.setUrl("weixiu/weixiulist2.jsp");
		return SUCCESS;
	}
	
	
	//跳转到处理维修页面
	public String weixiuupdate5() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Weixiu bean = weixiuDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("list2", zhuhuDao.selectBeanList(0, 9999, " where  zhuhulock=0"));
		this.setUrl("weixiu/weixiuupdate5.jsp");
		return SUCCESS;
	}
//处理维修操作
	public void weixiuupdate6() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String chulijieguo = request.getParameter("chulijieguo");
		String chulifankui = request.getParameter("chulifankui");
		Weixiu bean = weixiuDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setChulijieguo(chulijieguo);
		bean.setChulifankui(chulifankui);
		weixiuDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!weixiulist2';</script>");
	}
	
	
	
	//投诉列表
	public String tousulist2()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		String huzhuxingming = request.getParameter("huzhuxingming");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" where ");

		if(huzhuxingming!=null&&!"".equals(huzhuxingming)){
			sb.append(" zhuhu.huzhuxingming like '%"+huzhuxingming+"%'");
			sb.append(" and ");
			sb2.append(" zhuhu.huzhuxingming like '%"+huzhuxingming+"%'");
			sb2.append(" and ");

			request.setAttribute("huzhuxingming", huzhuxingming);
		}
		
		
		
		
		
		sb.append(" tousulock=0   order by id desc");
		String where = sb.toString();
		sb2.append(" tousulock=0 ");
		String where2 = sb2.toString();
		
		int currentpage = 1;
		int pagesize =10;
		if(request.getParameter("pagenum")!=null){
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = tousuDao.selectBeanCount(where2);
		request.setAttribute("list", tousuDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!tousulist2", "共有" + total + "条记录"));
		this.setUrl("tousu/tousulist2.jsp");
		return SUCCESS;
	}
	
	
	//跳转到处理投诉页面
	public String tousuupdate5() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Tousu bean = tousuDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("list2", zhuhuDao.selectBeanList(0, 9999, " where  zhuhulock=0"));
		this.setUrl("tousu/tousuupdate5.jsp");
		return SUCCESS;
	}
//处理投诉操作
	public void tousuupdate6() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String chulijieguo = request.getParameter("chulijieguo");
		String chulifankui = request.getParameter("chulifankui");
		Tousu bean = tousuDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setChulijieguo(chulijieguo);
		bean.setChulifankui(chulifankui);
		tousuDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!tousulist2';</script>");
	}

	
}
