<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>发表问题</title>
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
		<div class="fly-panel" pad20>
			<h2 class="page-title">发表问题</h2>

			<!-- <div class="fly-none">并无权限</div> -->

			<div class="layui-form layui-form-pane">
				<form action="${pageContext.request.contextPath }/AddTopicServlet"
					method="post" onsubmit="return subm(${loginuser.kissNum });">
					<div class="layui-form-item">
						<label for="L_title" class="layui-form-label">标题</label>
						<div class="layui-input-block">
							<input type="text" id="L_title" name="title" required
								lay-verify="required" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item layui-form-text">
						<div class="layui-input-block">
							<textarea id="L_content" name="content" required
								lay-verify="required" placeholder="请输入内容"
								class="layui-textarea fly-editor" style="height: 260px;"></textarea>
						</div>
						<label for="L_content" class="layui-form-label" style="top: -2px;">描述</label>
					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">所在类别</label>
							<div class="layui-input-block">
								<select lay-verify="required" name="category">

									<option value="1">提问</option>
									<option value="2">分享</option>
									<option value="3">讨论</option>
									<option value="4">建议</option>
								</select>
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">悬赏飞吻</label>
							<div class="layui-input-block">
								<select name="kiss" id="kiss" >
									<option value="5" selected>5</option>
									<option value="20">20</option>
									<option value="50">50</option>
									<option value="100">100</option>
								</select>
							</div>
						</div>
					</div>
					<script type="text/javascript">
						
						function subm(k){
							var numkiss = document.getElementById('kiss').value;
							if(numkiss>k){
								layer.msg('飞吻数不足');
								return false;
							}else{
								//layer.msg('发布成功');
								return true;
							}
						}
					</script>
					<div class="layui-form-item">
						<input type="submit" class="layui-btn" lay-filter="*" value="立即发布"></input>

						<!--  <button class="layui-btn" lay-filter="*" lay-submit>立即发布</button> -->
					</div>
					<input type="hidden" value="${loginuser.id }" name="userid">
				</form>
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
		layui.cache.page = 'jie';
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