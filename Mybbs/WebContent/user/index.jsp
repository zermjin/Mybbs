<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bbs.service.TopicService" %>
<%@ page import="com.bbs.bean.BbsTopicEX" %>
<%@ page import="com.bbs.bean.BbsUser" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>用户中心</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="keywords" content="fly,layui,前端社区">
<meta name="description"
	content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
<link rel="stylesheet" href="../res/layui/css/layui.css">
<link rel="stylesheet" href="../res/css/global.css">
</head>
<body>

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
						src="${pageContext.request.contextPath }/upload/${loginuser.headUrl}">
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

	<div class="main fly-user-main layui-clear">
		<ul class="layui-nav layui-nav-tree layui-inline" lay-filter="user">
			<li class="layui-nav-item"><a href="home.jsp"> <i
					class="layui-icon">&#xe609;</i> 我的主页
			</a></li>
			<li class="layui-nav-item layui-this"><a href="index.jsp">
					<i class="layui-icon">&#xe612;</i> 用户中心
			</a></li>
			<li class="layui-nav-item"><a href="set.jsp"> <i
					class="layui-icon">&#xe620;</i> 基本设置
			</a></li>
			<li class="layui-nav-item"><a href="message.jsp"> <i
					class="layui-icon">&#xe611;</i> 我的消息
			</a></li>
		</ul>

		<div class="site-tree-mobile layui-hide">
			<i class="layui-icon">&#xe602;</i>
		</div>
		<div class="site-mobile-shade"></div>

		<div class="fly-panel fly-panel-user" pad20>
			<!--
    <div class="fly-msg" style="margin-top: 15px;">
      您的邮箱尚未验证，这比较影响您的帐号安全，<a href="activate.html">立即去激活？</a>
    </div>
    -->			<%
					BbsUser loginuser = (BbsUser)session.getAttribute("loginuser");
					TopicService ts = new TopicService();
					List<BbsTopicEX> list = ts.selectTopicById(loginuser.getId());
					session.setAttribute("list", list);
					List<BbsTopicEX> list2 = ts.getCollectTopicById(loginuser.getId());
					session.setAttribute("clist", list2);
				%>
			<div class="layui-tab layui-tab-brief" lay-filter="user">
				<ul class="layui-tab-title" id="LAY_mine">
					<li data-type="mine-jie" lay-id="index" class="layui-this">我发的帖（<span>${list.size()}</span>）
					</li>
					
					<li data-type="collection" data-url="/collection/find/"
						lay-id="collection">我收藏的帖（<span>${clist.size() }</span>）
					</li>
				</ul>
				
				<div class="layui-tab-content" style="padding: 20px 0;">
					<div class="layui-tab-item layui-show">
						<ul class="mine-view jie-row">
						<c:forEach items="${list}" var="Temp">
							<li><a class="jie-title" href="../TopicDetailServlet?id=${Temp.getId() }"
								target="_blank">${Temp.getTitle() }</a> 
								<span style="text-align: right">发表于${Temp. getCreatetime()}</span>
								&nbsp;&nbsp;
								<a class="mine-edit" href="../jie/edit.jsp?id=${Temp.id }">编辑</a> <em>${Temp.getViewCount() }阅/${Temp.commentTotal }答</em>
							
							</li>
						
						</c:forEach>
						</ul>
						<div id="LAY_page"></div>
					</div>
					
					<div class="layui-tab-item">
						<ul class="mine-view jie-row">
						<c:forEach items="${clist}" var="Temp">
							<li><a class="jie-title"
								href="../TopicDetailServlet?id=${Temp.getId() }" target="_blank">
									${Temp.getTitle() }</a> 
									<span style="text-align: right">收藏于${Temp.collectTime }</span></li>
						
						</c:forEach>
							
						</ul>
						<div id="LAY_page1"></div>
					</div>
				</div>
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