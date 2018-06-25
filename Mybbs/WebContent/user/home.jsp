<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bbs.service.TopicService" %>
<%@ page import="com.bbs.bean.BbsTopicEX" %>
<%@ page import="com.bbs.service.CommentService" %>
<%@ page import="com.bbs.service.UserService" %>
<%@ page import="com.bbs.bean.BbsCommentEX" %>
<%@ page import="com.bbs.bean.BbsUser" %>
<%@ page import="java.util.List" %>
<%	String id = request.getParameter("id");
	//System.out.println(id);
	BbsUser user = null;
	UserService us = new UserService();
	if(id!=null&&!id.equals("")){
		user = us.selectUser(Integer.parseInt(id));
	}else{
		user = (BbsUser)session.getAttribute("loginuser");
	}
	//System.out.println(user);
	session.setAttribute("user", user);
	TopicService ts = new TopicService();
	List<BbsTopicEX> list1 = ts.selectTopicById(user.getId());
	CommentService cs = new CommentService();
	List<BbsCommentEX> list2 = cs.selectAllById(user.getId());
	session.setAttribute("topiclist", list1);
	session.setAttribute("commentlist", list2);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>用户主页</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="keywords" content="fly,layui,前端社区">
<meta name="description"
	content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
<link rel="stylesheet" href="../res/layui/css/layui.css">
<link rel="stylesheet" href="../res/css/global.css">
</head>
<body style="margin-top: 65px;">

	<div class="header">
		<div class="main">
			<a class="logo" href="${pageContext.request.contextPath }/TopicIndexListServlet" title="Fly">Fly社区</a>
			<div class="nav">
				<a class="nav-this" href="${pageContext.request.contextPath }/TopicByPageServlet"> <i
					class="iconfont icon-wenda"></i>问答
				</a> <a href="http://www.layui.com/" target="_blank"> <i
					class="iconfont icon-ui"></i>框架
				</a>
			</div>

			<div class="nav-user">
				<!-- 登入后的状态 -->
				<c:if test="${!empty loginuser}">

					<a class="avatar"
						href="${pageContext.request.contextPath }/user/index.jsp"> <img
						src="${pageContext.request.contextPath }/upload/${user.headUrl}">
						<cite>${loginuser.nickname}</cite> <i>VIP2</i>
					</a>
					<div class="nav">
						<a href="${pageContext.request.contextPath }/user/set.jsp"><i
							class="iconfont icon-shezhi"></i>设置</a> <a
							href="${pageContext.request.contextPath }/LogoutServlet"><i
							class="iconfont icon-tuichu" style="top: 0; font-size: 22px;"></i>退了</a>
					</div>
				</c:if>

			</div>
		</div>
	</div>

	<div class="fly-home" style="background-image: url();">
		<img src="${pageContext.request.contextPath }/upload/${loginuser.headUrl}"
			alt="图片加载错误">
		<h1>
			${user.nickname}
			<c:if test="${user.sex == 1 }">
			<i class="iconfont icon-nan"></i></c:if>
			<c:if test="${user.sex == 0 }">
			<i class="iconfont icon-nv"></i></c:if>
			<!-- <i class="iconfont icon-nv"></i> -->

			<!-- <span style="color:#c00;">（超级码农）</span>
    <span style="color:#5FB878;">（活雷锋）</span>
    <span>（该号已被封）</span> -->
		</h1>
		<p class="fly-home-info">
			<i class="iconfont icon-zuichun" title="飞吻"></i><span
				style="color: #FF7200;">${user.kissNum }飞吻</span> 
				<!--<i class="iconfont icon-shijian"></i><span>2015-06-17 加入</span>   -->
				<i class="iconfont icon-chengshi"></i><span>来自${user.city }</span>
		</p>
		<p class="fly-home-sign">（${user.signName }）</p>
	</div>
	
	<div class="main fly-home-main">
		<div class="layui-inline fly-home-jie">
			<div class="fly-panel">
				<h3 class="fly-panel-title">${user.nickname} 最近的提问</h3>
				<c:if test="${!empty topiclist }">
				<ul class="jie-row">
				<c:forEach items="${topiclist}" var="Temp">
					<li>
					<c:if test="${Temp.getIsGood()== 1 }">
					<span class="fly-jing">精</span> </c:if>
					<a href="${pageContext.request.contextPath }/TopicDetailServlet?id=${Temp.getId() }" class="jie-title">${Temp.getTitle()} </a> 
					<i>${Temp.getCreatetime() }</i> <em>${Temp.getViewCount()}阅/${Temp.commentTotal}答</em></li>
				</c:forEach>
				
				</ul></c:if>
				<c:if test="${empty topiclist }">
				 <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;"><i style="font-size:14px;">没有发表任何求解</i></div>
				</c:if>
			</div>
		</div>

		<div class="layui-inline fly-home-da">
			<div class="fly-panel">
				<h3 class="fly-panel-title">${loginuser.nickname}最近的回答</h3>
				<c:if test="${!empty commentlist }">
				<ul class="home-jieda">
				<c:forEach items="${commentlist}" var="Temp">
					<li>
						<p>
							<span>${Temp.getCommentTime() }</span> 
							在<a href="${pageContext.request.contextPath }/TopicDetailServlet?id=${Temp.getTopicOrCommentId()}" target="_blank">${Temp.title }</a>中回答：
						</p>
						<div class="home-dacontent">
							<!-- <pre>full: true</pre> -->
							${Temp.getContent() }
						</div>
					</li>
				</c:forEach>
					
				</ul></c:if>
				<c:if test="${empty commentlist }">
				 <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;"><span>没有回答任何问题</span></div> 
				</c:if>
			</div>
		</div>

	</div>

	<div class="footer">
		<p>
			<a href="http://fly.layui.com/">Fly社区</a> 2017 &copy; <a
				href="http://www.layui.com/">layui.com</a>
		</p>
		<p>
			<a href="http://fly.layui.com/auth/get" target="_blank">产品授权</a> <a
				href="http://fly.layui.com/jie/8157.html" target="_blank">获取Fly社区模版</a>
			<a href="http://fly.layui.com/jie/2461.html" target="_blank">微信公众号</a>
		</p>
	</div>
	<script src="../res/layui/layui.js"></script>
	<script>
		layui.cache.page = 'user';
		layui.cache.user = {
			username : '游客',
			uid : -1,
			avatar : '../res/images/avatar/00.jpg',
			experience : 83,
			sex : '男'
		};
		layui.config({
			version : "2.0.0",
			base : '../res/mods/'
		}).extend({
			fly : 'index'
		}).use('fly');
	</script>

</body>
</html>