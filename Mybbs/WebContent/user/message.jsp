<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bbs.service.TopicService"%>
<%@ page import="com.bbs.bean.BbsTopicEX"%>
<%@ page import="com.bbs.service.CommentService"%>
<%@ page import="com.bbs.bean.BbsCommentEX"%>
<%@ page import="com.bbs.bean.BbsUser"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>我的消息</title>
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
			<a class="logo"
				href="${pageContext.request.contextPath }/TopicIndexListServlet"
				title="Fly">Fly社区</a>
			<div class="nav">
				<a class="nav-this" href="${pageContext.request.contextPath }/TopicByPageServlet"> <i
					class="iconfont icon-wenda"></i>问答
				</a> <a href="http://www.layui.com/" target="_blank"> <i
					class="iconfont icon-ui"></i>框架
				</a>
			</div>

			<div class="nav-user">
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
			<li class="layui-nav-item"><a href="index.jsp"> <i
					class="layui-icon">&#xe612;</i> 用户中心
			</a></li>
			<li class="layui-nav-item"><a href="set.jsp"> <i
					class="layui-icon">&#xe620;</i> 基本设置
			</a></li>
			<li class="layui-nav-item layui-this"><a href="message.jsp">
					<i class="layui-icon">&#xe611;</i> 我的消息
			</a></li>
		</ul>

		<div class="site-tree-mobile layui-hide">
			<i class="layui-icon">&#xe602;</i>
		</div>
		<div class="site-mobile-shade"></div>
		<%
			BbsUser loginuser = (BbsUser) session.getAttribute("loginuser");
			CommentService cs = new CommentService();
			List<BbsCommentEX> list = cs.getMessageById(loginuser.getId());
			session.setAttribute("messlist", list);
		%>
		<script type="text/javascript">
		function deleteAllMess(id) {
			var str = id + ",all";
			window.location.href = "../DeleteMessageServlet?id="+str;
		}
		function deleteMess(id) {
			var str = id + ",one";
			window.location.href = "../DeleteMessageServlet?id="+str;
		}
		</script>
		<div class="fly-panel fly-panel-user" pad20>
			<div class="layui-tab layui-tab-brief" lay-filter="user" id="LAY_msg"
				style="margin-top: 15px;">
				<input type="button" class="layui-btn layui-btn-danger" value="清空全部消息" onclick="deleteAllMess(${loginuser.id});">
				<!-- <button class="layui-btn layui-btn-danger" id="LAY_delallmsg">清空全部消息</button> -->
				<div id="LAY_minemsg" style="margin-top: 10px;">
					<c:if test="${messlist.size()==0}">
							<div class="fly-none">您暂时没有最新消息</div>
					</c:if>
					<c:if test="${messlist.size()!=0}">
					<ul class="mine-msg">
						<c:forEach items="${messlist }" var="Temp">
						<c:if test="${Temp.isTopic==0 }">
						<li data-id="123">
							<blockquote class="layui-elem-quote">
								<a href="${pageContext.request.contextPath }/user/home.jsp?id=${Temp.userid}" target="_blank"><cite>${Temp.nickname }</cite></a>回答了您的求解<a
									target="_blank"
									href="${pageContext.request.contextPath }/TopicDetailServlet?id=${Temp.getTopicOrCommentId()}"><cite>${Temp.title }</cite></a>
							</blockquote>
							<p>
								<span>${Temp.getCommentTime() }</span>
								<input type="button" onclick="deleteMess(${Temp.getId()});" 
									class="layui-btn layui-btn-small layui-btn-danger " value="删除">
							</p>
						</li></c:if>
						<c:if test="${Temp.isTopic==1 }">
						<li data-id="123">
							<blockquote class="layui-elem-quote">
								<a href="${pageContext.request.contextPath }/user/home.jsp?id=${Temp.userid}" target="_blank"><cite>${Temp.nickname }</cite></a>回复了你在<a
									target="_blank"
									href="${pageContext.request.contextPath }/TopicDetailServlet?id=${Temp.getTopicOrCommentId()}"><cite>${Temp.title }</cite></a>
							中的回答
							</blockquote>
							<p>
								<span>${Temp.getCommentTime() }</span>
								<input type="button" onclick="deleteMess(${Temp.getId()});" 
									class="layui-btn layui-btn-small layui-btn-danger " value="删除">
							</p>
						</li></c:if>
						</c:forEach>
					</ul></c:if>
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