<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>基于Layui的轻量级问答社区页面模版</title>
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
	<%
		String action = request.getParameter("action");
		session.setAttribute("action", action);
	%>
	<div class="main layui-clear">
		<div class="wrap">
			<div class="content" style="margin-right: 0">
				<div class="fly-tab fly-tab-index">
					<span > 
						<a ${empty action? "style=\"background-color: #199F93\"":"" } href="${pageContext.request.contextPath }/TopicByPageServlet">全部</a> 
						<a ${action==1? "style=\"background-color: #199F93\"":"" }href="${pageContext.request.contextPath }/TopicByPageServlet?action=1">未结帖</a>
						<a ${action==2? "style=\"background-color: #199F93\"":"" }href="${pageContext.request.contextPath }/TopicByPageServlet?action=2">已采纳</a>
						<a ${action==3? "style=\"background-color: #199F93\"":"" }href="${pageContext.request.contextPath }/TopicByPageServlet?action=3">精帖</a>
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
				<c:if test="${empty action }">
				<ul class="fly-list">
					
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
										href="${pageContext.request.contextPath }/user/home.jsp">${Temp.nickname}</a></span>
									<span>${Temp.getCreatetime()}</span> <span>${Temp.category_name}</span>
									<span class="fly-list-hint"> <!-- 	<i class="iconfont" title="回答">&#xe60c;</i> ${Temp.getViewCount()} -->
										<i class="iconfont" title="人气">&#xe60b;</i>
										${Temp.getViewCount()}
									</span>
								</p></li>
						</c:if>
					</c:forEach>
					
				</ul>
				<ul class="fly-list">
					<c:forEach items="${list}" var="Temp">
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
										href="${pageContext.request.contextPath }/user/home.jsp">${Temp.nickname}</a></span>
									<span>${Temp.getCreatetime()}</span> <span>${Temp.category_name}</span>
									<span class="fly-list-hint"> 
									<!-- 	<i class="iconfont" title="回答">&#xe60c;</i> ${Temp.getViewCount()}  -->
										<i class="iconfont" title="人气">&#xe60b;</i> ${Temp.getViewCount()}
									</span>
								</p></li>
						</c:if>
					</c:forEach>
				</ul></c:if>
				<c:if test="${action==1 }">
				<ul class="fly-list">
					
					<c:forEach items="${list}" var="Temp">
						
						<c:if test="${Temp.getIsTop()!=0&&Temp.isEnd==0}">
							<li class="fly-list-li"><a
								href="${pageContext.request.contextPath }/user/home.jsp"
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
										href="${pageContext.request.contextPath }/user/home.jsp">${Temp.nickname}</a></span>
									<span>${Temp.getCreatetime()}</span> <span>${Temp.category_name}</span>
									<span class="fly-list-hint"> <!-- 	<i class="iconfont" title="回答">&#xe60c;</i> ${Temp.getViewCount()} -->
										<i class="iconfont" title="人气">&#xe60b;</i>
										${Temp.getViewCount()}
									</span>
								</p></li>
						</c:if>
					</c:forEach>
					
				</ul>
				<ul class="fly-list">
					<c:forEach items="${list}" var="Temp">
						<c:if test="${Temp.getIsTop()==0&&Temp.isEnd==0}">

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
										href="${pageContext.request.contextPath }/user/home.jsp">${Temp.nickname}</a></span>
									<span>${Temp.getCreatetime()}</span> <span>${Temp.category_name}</span>
									<span class="fly-list-hint"> 
									<!-- 	<i class="iconfont" title="回答">&#xe60c;</i> ${Temp.getViewCount()}  -->
										<i class="iconfont" title="人气">&#xe60b;</i> ${Temp.getViewCount()}
									</span>
								</p></li>
						</c:if>
					</c:forEach>
				</ul>
				</c:if>
					
				<c:if test="${action==2 }">
				<ul class="fly-list">
					
					<c:forEach items="${list}" var="Temp">
						
						<c:if test="${Temp.getIsTop()!=0&&Temp.isEnd==1}">
							<li class="fly-list-li"><a
								href="${pageContext.request.contextPath }/user/home.jsp"
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
										href="${pageContext.request.contextPath }/user/home.jsp">${Temp.nickname}</a></span>
									<span>${Temp.getCreatetime()}</span> <span>${Temp.category_name}</span>
									<span class="fly-list-hint"> <!-- 	<i class="iconfont" title="回答">&#xe60c;</i> ${Temp.getViewCount()} -->
										<i class="iconfont" title="人气">&#xe60b;</i>
										${Temp.getViewCount()}
									</span>
								</p></li>
						</c:if>
					</c:forEach>
					
				</ul>
				<ul class="fly-list">
					<c:forEach items="${list}" var="Temp">
						<c:if test="${Temp.getIsTop()==0&&Temp.isEnd==1}">

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
										href="${pageContext.request.contextPath }/user/home.jsp">${Temp.nickname}</a></span>
									<span>${Temp.getCreatetime()}</span> <span>${Temp.category_name}</span>
									<span class="fly-list-hint"> 
									<!-- 	<i class="iconfont" title="回答">&#xe60c;</i> ${Temp.getViewCount()}  -->
										<i class="iconfont" title="人气">&#xe60b;</i> ${Temp.getViewCount()}
									</span>
								</p></li>
						</c:if>
					</c:forEach>
				</ul>
				</c:if>
				<c:if test="${action==3 }">
				<ul class="fly-list">
					
					<c:forEach items="${list}" var="Temp">
						
						<c:if test="${Temp.getIsTop()!=0&&Temp.getIsGood()!=0}">
							<li class="fly-list-li"><a
								href="${pageContext.request.contextPath }/user/home.jsp"
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
										href="${pageContext.request.contextPath }/user/home.jsp">${Temp.nickname}</a></span>
									<span>${Temp.getCreatetime()}</span> <span>${Temp.category_name}</span>
									<span class="fly-list-hint"> <!-- 	<i class="iconfont" title="回答">&#xe60c;</i> ${Temp.getViewCount()} -->
										<i class="iconfont" title="人气">&#xe60b;</i>
										${Temp.getViewCount()}
									</span>
								</p></li>
						</c:if>
					</c:forEach>
					
				</ul>
				<ul class="fly-list">
					<c:forEach items="${list}" var="Temp">
						<c:if test="${Temp.getIsTop()==0&&Temp.getIsGood()!=0}">

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
										href="${pageContext.request.contextPath }/user/home.jsp">${Temp.nickname}</a></span>
									<span>${Temp.getCreatetime()}</span> <span>${Temp.category_name}</span>
									<span class="fly-list-hint"> 
									<!-- 	<i class="iconfont" title="回答">&#xe60c;</i> ${Temp.getViewCount()}  -->
										<i class="iconfont" title="人气">&#xe60b;</i> ${Temp.getViewCount()}
									</span>
								</p></li>
						</c:if>
					</c:forEach>
				</ul>
				</c:if>
				<!-- <div class="fly-none">并无相关数据</div> -->

				<div style="text-align: center">
					<div class="laypage-main">
					<!--  	<span class="laypage-curr">1</span><a href="/jie/page/2/">2</a><a
							href="/jie/page/3/">3</a><a href="/jie/page/4/">4</a><a
							href="/jie/page/5/">5</a><span>…</span><a href="/jie/page/148/"
							class="laypage-last" title="尾页">尾页</a><a href="/jie/page/2/"
							class="laypage-next">下一页</a>-->
							<c:forEach begin="1" end="${pageCount }" var="p">
									<!-- 如果p是当前页 那就直接输出页数 -->
									<c:if test="${p==pageNum }">
										<span class="laypage-curr">${p }</span>

									</c:if>
									<!-- 如果p不是当前页 那就是其他几页的链接（用于显示其他页的分页数据） -->
									<c:if test="${p!=pageNum }">
										<a href="<%=request.getContextPath()%>/TopicByPageServlet?pageNum=${p }&action=${action}">${p }</a>
									</c:if>
							</c:forEach>
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
		layui.cache.page = 'jie';
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