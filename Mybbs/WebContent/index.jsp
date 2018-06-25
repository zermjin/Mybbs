<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>基于 layui 的极简社区页面模版</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="keywords" content="fly,layui,前端社区">
<meta name="description"
	content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/res/layui/css/layui.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/res/css/global.css">
</head>
<body>

	<div class="header">
		<div class="main">
			<!-- 相当于刷新 -->
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
				<!-- 未登入状态 -->
				<c:if test="${empty loginuser}">
					<a class="unlogin"
						href="${pageContext.request.contextPath }/user/login.jsp"><i
						class="iconfont icon-touxiang"></i></a>
					<span><a
						href="${pageContext.request.contextPath }/user/login.jsp">登入</a><a
						href="${pageContext.request.contextPath }/user/reg.jsp">注册</a></span>
					<p class="out-login">
						<a href=""
							onclick="layer.msg('正在通过QQ登入', {icon:16, shade: 0.1, time:0})"
							class="iconfont icon-qq" title="QQ登入"></a> <a href=""
							onclick="layer.msg('正在通过微博登入', {icon:16, shade: 0.1, time:0})"
							class="iconfont icon-weibo" title="微博登入"></a>
					</p>
				</c:if>
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


	<div class="main layui-clear">
		<div class="wrap">
			<div class="content">
				<div class="fly-tab fly-tab-index">
					<span> 
						<a href="${pageContext.request.contextPath }/TopicByPageServlet">全部</a> 
						<a href="${pageContext.request.contextPath }/TopicByPageServlet?action=1">未结帖</a>
						<a href="${pageContext.request.contextPath }/TopicByPageServlet?action=2">已采纳</a>
						<a href="${pageContext.request.contextPath }/TopicByPageServlet?action=3">精帖</a>
						<c:if test="${!empty loginuser}">
						<a href="${pageContext.request.contextPath }/user/index.jsp">我的帖</a>
						</c:if>
					</span>
					<form action="http://cn.bing.com/search" class="fly-search">
						<i class="iconfont icon-sousuo"></i> <input class="layui-input"
							autocomplete="off" placeholder="搜索内容，回车跳转" type="text" name="q">
					</form>
					<c:if test="${empty loginuser}">
					<a href="${pageContext.request.contextPath }/user/login.jsp"
						class="layui-btn jie-add">发布问题</a>
					</c:if>
					<c:if test="${!empty loginuser}">
					<a href="${pageContext.request.contextPath }/jie/add.jsp"
						class="layui-btn jie-add">发布问题</a>
					</c:if>
				</div>

				<ul class="fly-list fly-list-top">
					<c:forEach items="${list}" var="Temp">
						<c:if test="${Temp.getIsTop()!=0}">
							<li class="fly-list-li"><a
								href="${pageContext.request.contextPath }/user/home.jsp?id=${Temp.userid}"
								class="fly-list-avatar"> <img
									src="${pageContext.request.contextPath }/upload/${Temp.head_url}"
									alt="">
							</a>
								<h2 class="fly-tip">
									<a
										href="${pageContext.request.contextPath }/TopicDetailServlet?id=${Temp.id}">${Temp.title}</a>
									<span class="fly-tip-stick">置顶</span> 
									<c:if test="${Temp.getIsGood()!=0}">
									<span class="fly-tip-jing"> 加精</span></c:if>
								</h2>
								<p>
									<span> <a
										href="${pageContext.request.contextPath }/user/home.jsp?id=${Temp.userid}">${Temp.nickname}</a></span>
									<span>${Temp.getCreatetime()}</span> <span>${Temp.category_name}</span>
									<span class="fly-list-hint"> <!-- 	<i class="iconfont" title="回答">&#xe60c;</i> ${Temp.getViewCount()} -->
										<i class="iconfont" title="人气">&#xe60b;</i>
										${Temp.getViewCount()}
									</span>
								</p></li>
						</c:if>
					</c:forEach>
				</ul>
				<!-- 	<ul class="fly-list fly-list-top">
					<li class="fly-list-li"><a
						href="${pageContext.request.contextPath }/user/home.html"
						class="fly-list-avatar"> <img
							src="http://tp4.sinaimg.cn/1345566427/180/5730976522/0" alt="">
					</a>
						<h2 class="fly-tip">
							<a href="${pageContext.request.contextPath }/jie/detail.html">基于
								layui的轻量级问答社区页面模版 V2版本</a> <span class="fly-tip-stick">置顶</span> <span
								class="fly-tip-jing">精帖</span>
						</h2>
						<p>
							<span><a
								href="${pageContext.request.contextPath }/user/home.html">贤心</a></span>
							<span>刚刚</span> <span>layui框架综合</span> <span
								class="fly-list-hint"> <i class="iconfont" title="回答">&#xe60c;</i>
								317 <i class="iconfont" title="人气">&#xe60b;</i> 6830
							</span>
						</p></li>
					<li class="fly-list-li"><a href="user/home.html"
						class="fly-list-avatar"> <img
							src="${pageContext.request.contextPath }/res/images/avatar/00.jpg"
							alt="">
					</a>
						<h2 class="fly-tip">
							<a href="${pageContext.request.contextPath }/jie/detail.html">基于
								layui的轻量级问答社区页面模版 V2版本</a> <span class="fly-tip-stick">置顶</span>
						</h2>
						<p>
							<span><a
								href="${pageContext.request.contextPath }/user/home.html">纸飞机</a></span>
							<span>30分钟前</span> <span>技术闲谈</span> <span class="fly-list-hint">
								<i class="iconfont" title="回答">&#xe60c;</i> 502 <i
								class="iconfont" title="人气">&#xe60b;</i> 81689
							</span>
						</p></li>
				</ul>

				<ul class="fly-list">
					<li class="fly-list-li"><a
						href="${pageContext.request.contextPath }/user/home.html"
						class="fly-list-avatar"> <img
							src="${pageContext.request.contextPath }/res/images/avatar/default.png"
							alt="">
					</a>
						<h2 class="fly-tip">
							<a href="${pageContext.request.contextPath }/jie/detail.html">关于layui
								引用其他插件，扩展模块 的用法</a>
						</h2>
						<p>
							<span><a
								href="${pageContext.request.contextPath }/user/home.html">用户昵称</a></span>
							<span>1小时前</span> <span>layui框架综合</span> <span
								class="fly-list-hint"> <i class="iconfont" title="回答">&#xe60c;</i>
								8 <i class="iconfont" title="人气">&#xe60b;</i> 106
							</span>
						</p></li>
					<li class="fly-list-li"><a href="user/home.html"
						class="fly-list-avatar"> <img
							src="${pageContext.request.contextPath }/res/images/avatar/default.png"
							alt="">
					</a>
						<h2 class="fly-tip">
							<a href="${pageContext.request.contextPath }/jie/detail.html">关于layui
								引用其他插件，扩展模块 的用法</a>
						</h2>
						<p>
							<span><a
								href="${pageContext.request.contextPath }/user/home.html">用户昵称</a></span>
							<span>1小时前</span> <span>layui框架综合</span> <span
								class="fly-list-hint"> <i class="iconfont" title="回答">&#xe60c;</i>
								8 <i class="iconfont" title="人气">&#xe60b;</i> 106
							</span>
						</p></li>

				</ul>-->
				<ul class="fly-list">
					<c:forEach items="${list}" var="Temp" begin="0" end="9">
						<c:if test="${Temp.getIsTop()==0}">
							<li class="fly-list-li"><a
								href="${pageContext.request.contextPath }/user/home.jsp"
								class="fly-list-avatar"> <img
									src="${pageContext.request.contextPath }/upload/${Temp.head_url}"
									alt="">
							</a>
								<h2 class="fly-tip">
									<a
										href="${pageContext.request.contextPath }/TopicDetailServlet?id=${Temp.id}">${Temp.getTitle()}</a>
									<c:if test="${Temp.getIsGood()!=0}">
									<span class="fly-tip-jing"> 加精</span></c:if>
								</h2>
								<p>
									<span><a
										href="${pageContext.request.contextPath }/user/home.jsp?id=${Temp.userid}">${Temp.nickname}</a></span>
									<span>${Temp.getCreatetime()}</span> <span>${Temp.category_name}</span>
									<span class="fly-list-hint"> 
									<!-- 	<i class="iconfont" title="回答">&#xe60c;</i> ${Temp.getViewCount()}  -->
										<i class="iconfont" title="人气">&#xe60b;</i> ${Temp.getViewCount()}
									</span>
								</p></li>
						</c:if>
					</c:forEach>
				</ul>
				<div style="text-align: center">
					<div class="laypage-main">
						<a href="${pageContext.request.contextPath }/TopicByPageServlet"
							class="laypage-next">更多求解</a>
					</div>
				</div>

			</div>
		</div>

		<div class="edge">
			<div class="fly-panel leifeng-rank">
				<h3 class="fly-panel-title">近一月回答榜 - TOP 12</h3>
				
				<dl>
				<c:forEach items="${ulist }" var="Temp">
				<dd>
						<a href="user/home.jsp"> <img
							src="${pageContext.request.contextPath }/upload/${Temp.headUrl}">
							<cite>${Temp.nickname }</cite> <i>${Temp.answer }次回答</i>
						</a>
					</dd>
				</c:forEach>
					

				</dl>
			</div>

			<dl class="fly-panel fly-list-one">
				<dt class="fly-panel-title">最近热帖</dt>
				<c:forEach items="${vlist }" var="Temp">
				<dd>
					<a href="TopicDetailServlet?id=${Temp.id }">${Temp.title }</a> <span><i
						class="iconfont">&#xe60b;</i> ${Temp.viewCount }</span>
				</dd>
				</c:forEach>
				
			</dl>

			<dl class="fly-panel fly-list-one">
				<dt class="fly-panel-title">近期热议</dt>
				<c:forEach items="${alist }" var="Temp">
				<dd>
					<a href="TopicDetailServlet?id=${Temp.id }">${Temp.title }</a> <span><i
						class="iconfont">&#xe60c;</i> ${Temp.commentTotal }</span>
				</dd>
				</c:forEach>
				
			</dl>

			<div class="fly-panel fly-link">
				<h3 class="fly-panel-title">友情链接</h3>
				<dl>
					<dd>
						<a href="http://www.layui.com/" target="_blank">layui</a>
					</dd>
					<dd>
						<a href="http://layim.layui.com/" target="_blank">LayIM</a>
					</dd>
					<dd>
						<a href="http://layer.layui.com/" target="_blank">layer</a>
					</dd>
				</dl>
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
	<script src="${pageContext.request.contextPath }/res/layui/layui.js"></script>
	<script>
		layui.cache.page = '';
		layui.cache.user = {
			username : '游客',
			uid : -1,
			avatar : '${pageContext.request.contextPath }/res/images/avatar/00.jpg',
			experience : 83,
			sex : '男'
		};
		layui.config({
			version : "2.0.0",
			base : '${pageContext.request.contextPath }/res/mods/'
		}).extend({
			fly : 'index'
		}).use('fly');
	</script>

</body>
</html>