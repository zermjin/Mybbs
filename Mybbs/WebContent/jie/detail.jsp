<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<title>Fly Template v2.0，基于 layui 的轻量级社区模版</title>
<script src="/Mybbs/res/jquery-1.11.2.min.js" type="text/javascript"></script>
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
				<a class="nav-this"
					href="${pageContext.request.contextPath }/TopicByPageServlet.html">
					<i class="iconfont icon-wenda"></i>问答
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

	<script type="text/javascript">
		function deleteTopic(id) {
			var str = id + ",delete";
			window.location.href = "../AdminTopicServlet?action="+str;
		}
		function goodTopic(id) {
			var str = id + ",good";
			window.location.href = "../AdminTopicServlet?action="+str;
		}
		function TopTopic(id) {
			var str = id + ",top";
			window.location.href = "../AdminTopicServlet?action="+str;
		}
	</script>
	<div class="main layui-clear">
		<div class="wrap">
			<div class="content detail">
				<div class="fly-panel detail-box">
					<h1>${topic.title}</h1>
					<div class="fly-tip fly-detail-hint" data-id="{{rows.id}}">
						<c:if test="${topic.isTop==1 }">
							<span class="fly-tip-stick">置顶帖</span>
						</c:if>
						<c:if test="${topic.isGood==1 }">
							<span class="fly-tip-jing">精帖</span>
						</c:if>
						<c:if test="${topic.isEnd==0 }">
							<span>未结贴</span>
						</c:if>
						<c:if test="${topic.isEnd==1 }">
							<span class="fly-tip-jie">已采纳</span>
						</c:if>
						<c:if test="${(!empty loginuser)&&(loginuser.id==1)}">
							<input type="button" onclick="deleteTopic(${topic.id})"
								class="layui-btn layui-btn-small layui-btn-danger " value="删除">
							<c:if test="${topic.isTop==0 }">
								<input type="button" onclick="TopTopic(${topic.id})"
									class="layui-btn layui-btn-small " value="置顶">
							</c:if>
							<c:if test="${topic.isGood==0 }">
								<input type="button" onclick="goodTopic(${topic.id})"
									class="layui-btn layui-btn-small " value="加精">
							</c:if>
							<c:if test="${topic.isTop==1 }">
								<input type="button" onclick="TopTopic(${topic.id})"
									class="layui-btn layui-btn-small " value="取消置顶">
							</c:if>
							<c:if test="${topic.isGood==1 }">
								<input type="button" onclick="goodTopic(${topic.id})"
									class="layui-btn layui-btn-small " value="取消加精">
							</c:if>
							<!-- <span class="jie-admin" type="del" style="margin-left: 20px;">删除</span> 
			            <span class="jie-admin" type="set" field="stick" rank="1">置顶</span> 
			            <span class="jie-admin" type="set" field="stick" rank="0" style="background-color:#ccc;">取消置顶</span>
			            <span class="jie-admin" type="set" field="status" rank="1">加精</span> 
			            <span class="jie-admin" type="set" field="status" rank="0" style="background-color:#ccc;">取消加精</span>-->
						</c:if>
						<div class="fly-list-hint">
							<i class="iconfont" title="回答">&#xe60c;</i> ${topic.commentTotal}
							<i class="iconfont" title="人气">&#xe60b;</i> ${topic.viewCount}
						</div>
					</div>
					<div class="detail-about">
						<a class="jie-user"
							href="${pageContext.request.contextPath }/user/home.jsp?id=${Temp.userid}">
							<img
							src="${pageContext.request.contextPath }/upload/${topic.head_url}"
							alt="图片加载失败"> <cite> ${topic.nickname } <em>${topic.createtime }</em>
						</cite>
						</a>
						<div class="detail-hits" data-id="{{rows.id}}">
							<span style="color: #FF7200">悬赏：${topic.kiss }飞吻</span>
							<c:if test="${topic.userid == loginuser.id }">
								<span class="layui-btn layui-btn-mini jie-admin" type="edit">
									<a href="edit.jsp">编辑此贴</a>
								</span>
							</c:if>
							<c:if test="${!empty loginuser}">
								<span class="layui-btn layui-btn-mini jie-admin " 
								onclick="collect(${topic.id},${loginuser.id});">收藏</span>
							</c:if>
							<!--<span class="layui-btn layui-btn-mini jie-admin  layui-btn-danger" type="collect" data-type="add">取消收藏</span>-->
						</div>
					</div>
					<script type="text/javascript">
					function collect(topic,user) {
						var str = topic + "," + user;
						//alert(str);
						$.ajax({
							   type: "POST",
							   url: "/Mybbs/CollectTopicServlet",
							   data: {"action":str},
							   success: function(data){
							     var n = Number(data);
							     if(n == 1){
							    	 layer.msg('收藏成功');
							     }else if(n==2){
							    	 layer.msg('已收藏');
							     }
							     else{
							    	 layer.msg('请求错误');
							     }
							   }
							});
					}
					</script>
					<div class="detail-body photos" style="margin-bottom: 20px;">
				
						${topic.content }
					</div>
				</div>
				<script type="text/javascript">
					function deleteComm(id) {
						var str = id + ",delete,"+0;
						window.location.href = "../AdminCommentServlet?action="+str;
					}
					function acceptComm(id,tid,uid,kiss) {
						var str = id + ",accept,"+tid+","+uid+","+kiss;
						window.location.href = "../AdminCommentServlet?action="+str;
					}
					function editComm(id) {
						var d=$('#editanswer'+id).css("display");
						//alert(d);
						if(d=="none")
							$('#editanswer'+id).css("display","");
						else if(d==""||d=="block")
							$('#editanswer'+id).css("display","none");
					}
				</script>
				<div class="fly-panel detail-box" style="padding-top: 0;">

					<ul class="jieda photos" id="jieda">
						<c:if test="${comment.size()==0}">
							<li class="fly-none">没有任何回答</li>
						</c:if>
						<c:if test="${comment.size()!=0}">


							<c:forEach items="${comment}" var="Temp">
								<c:if test="${Temp.isAccept==1}">
									<li data-id="${Temp.id }" class="jieda-daan">
										<!--  	<a name="item-121212121212"></a>-->
										<div class="detail-about detail-about-reply">
											<a class="jie-user"
												href="${pageContext.request.contextPath }/user/home.jsp?id=${Temp.userid}">
												<img
												src="${pageContext.request.contextPath }/upload/${Temp.head_url}"
												alt=""> <cite> <i>${Temp.nickname}</i> <c:if
														test="${Temp.userid==topic.userid }">
														<em>(楼主)</em>
													</c:if> <!--<em style="color:#5FB878">(管理员)</em>
                 							<em style="color:#FF9E3F">（活雷锋）</em>
                  							<em style="color:#999">（该号已被封）</em> -->
											</cite>
											</a>
											<div class="detail-hits">
												<span>${Temp.commentTime }</span>
											</div>
											<i class="iconfont icon-caina" title="最佳答案"></i>

										</div>
										<div class="detail-body jieda-body">
											<p>${Temp.content }</p>
										</div>
										<div class="jieda-reply">
											<span class="jieda-zan " onclick="addzan(${Temp.getId()});"
												id="zan${Temp.getId()}"> <i class="iconfont icon-zan"></i><em
												id="agreenum${Temp.getId()}">${Temp.getAgreeNum()}</em></span> <span
												 onclick="reply(${Temp.getId()},'${Temp.nickname}');"><i
												class="iconfont icon-svgmoban53"></i>回复</span>

										</div> <c:forEach items="${Temp.commentlist }" var="cl">
											<div>
												<div class="detail-about detail-about-reply"
													style="margin-left: 50px">
													<a class="jie-listuser"
														href="${pageContext.request.contextPath }/user/home.jsp?id=${Temp.userid}">
														<img
														src="${pageContext.request.contextPath }/upload/${cl.head_url}"
														alt=""> <cite> <i>${cl.nickname}</i> <c:if
																test="${cl.userid==topic.userid }">
																<em>(楼主)</em>
															</c:if> <!-- 	<em style="color:#5FB878">(管理员)</em>
                 							<em style="color:#FF9E3F">（活雷锋）</em>
                  							<em style="color:#999">（该号已被封）</em> -->
													</cite>
													</a>
													<div class="detail-hits">
														<span>${cl.commentTime }</span>
													</div>
												</div>
												<div class="detail-body jieda-body"
													style="margin-left: 50px">
													<p>${cl.content }</p>
												</div>
											</div>
										</c:forEach>
									</li>
								</c:if>
							</c:forEach>


							<!-- <a id="answer" href=""></a> -->
							<c:forEach items="${comment}" var="Temp" varStatus="status">
								<c:if test="${Temp.isAccept==0}">
									<li data-id="${Temp.id }" class="jieda-daan">
										<c:if test="${status.index + 2==comment.size() }">
										<a id="answer" href=""></a>
										</c:if> 
										<!--  	<a name="item-121212121212"></a>-->
										<div class="detail-about detail-about-reply">
											<a class="jie-user"
												href="${pageContext.request.contextPath }/user/home.jsp?id=${Temp.userid}">
												<img
												src="${pageContext.request.contextPath }/upload/${Temp.head_url}"
												alt=""> <cite> <i>${Temp.nickname}</i> <c:if
														test="${Temp.userid==topic.userid }">
														<em>(楼主)</em>
													</c:if> <!--<em style="color:#5FB878">(管理员)</em>
                 							<em style="color:#FF9E3F">（活雷锋）</em>
                  							<em style="color:#999">（该号已被封）</em> -->
											</cite>
											</a>
											<div class="detail-hits">
												<span>${Temp.commentTime }</span>
											</div>
										</div>
										<div class="detail-body jieda-body">
											<p>${Temp.content }</p>
										</div>
										<div class="jieda-reply">
											<span class="jieda-zan " onclick="addzan(${Temp.getId()});"
												id="zan${Temp.getId()}"> <i class="iconfont icon-zan"></i><em
												id="agreenum${Temp.getId()}">${Temp.getAgreeNum()}</em></span> <span
												 onclick="reply(${Temp.getId()},'${Temp.nickname }');"><i
												class="iconfont icon-svgmoban53"></i>回复</span>
											<c:if test="${Temp.userid==loginuser.id }">
												<div class="jieda-admin">
													<span onclick="editComm(${Temp.id});">编辑</span> <span
														onclick="deleteComm(${Temp.id})">删除</span>

												</div>
											</c:if>
											<c:if
												test="${(topic.isEnd==0)&&(loginuser.id==topic.userid)}">
												<span class="jieda-accept"
													onclick="acceptComm(${Temp.id},${topic.id },${Temp.userid },${topic.kiss })">采纳</span>
											</c:if>
										</div> 
										<c:if test="${Temp.userid==loginuser.id }">
										<div class="layui-form layui-form-pane" id="editanswer${Temp.id }" style="display: none">
											<form
												action="${pageContext.request.contextPath }/EditCommentServlet?id=${Temp.id}&tid=${topic.id}"
												method="post">
												<div class="layui-form-item layui-form-text">
													<div class="layui-input-block">
														<textarea id="L_content${Temp.id }" name="content" required
															lay-verify="required" placeholder=""
															class="layui-textarea fly-editor" style="height: 150px;">${Temp.content }</textarea>
													</div>
												</div>
												
												
												<div class="layui-form-item">
													<!-- lay-submit -->
														<input type="submit" class="layui-btn" lay-filter="*"
															value="提交回答"></input>
													
												</div>
											</form>
										</div>
										</c:if>
										
										<c:forEach items="${Temp.commentlist }" var="cl">
											<div>
												<div class="detail-about detail-about-reply"
													style="margin-left: 50px">
													<a class="jie-listuser"
														href="${pageContext.request.contextPath }/user/home.jsp?id=${Temp.userid}">
														<img
														src="${pageContext.request.contextPath }/upload/${cl.head_url}"
														alt=""> <cite> <i>${cl.nickname}</i> <c:if
																test="${cl.userid==topic.userid }">
																<em>(楼主)</em>
															</c:if> <!-- 	<em style="color:#5FB878">(管理员)</em>
                 							<em style="color:#FF9E3F">（活雷锋）</em>
                  							<em style="color:#999">（该号已被封）</em> -->
													</cite>
													</a>
													<div class="detail-hits">
														<span>${cl.commentTime }</span>
													</div>
												</div>
												<div class="detail-body jieda-body"
													style="margin-left: 50px">
													<p>${cl.content }</p>
												</div>
												
											</div>
										</c:forEach>

										
									</li>
								</c:if>
							</c:forEach>
						</c:if>


					</ul>
					
					<a id="anchor_scroll" href="#answer" style="display:none">跳转</a>
					<script>
						function reply(id,nick) {
							$('#L_content').html("");
							$('#L_content').html('@'+nick+' ');
							document.getElementById('anchor_scroll').click();
							//window.location.href('index.jsp');
							//window.loaction.href("#L_content");
							document.getElementById('commentid').value = id;
							document.getElementById('istopic').value = '1';
						}
						function addzan(id){
							//alert($("#zan").hasClass("zanok"));
							if($("#zan"+id).hasClass("zanok")){
								var str = id + ",ok";
								$.ajax({
									   type: "POST",
									   url: "/Mybbs/ThumbsUpServlet",
									   data: {"id":str},
									   success: function(data){
									     var n = Number(data);
									     if(n == 1){
									    	 var a = (Number)($("#agreenum"+id).html())-1;
									    	 $("#zan"+id).removeClass("zanok");
									    	 $("#agreenum"+id).html(a);
									     }else{
									    	 layer.msg('请求错误');
									     }
									   }
									});
							}else{
								var str = id + ",no";
								$.ajax({
									   type: "POST",
									   url: "/Mybbs/ThumbsUpServlet",
									   data: {"id":str},
									   success: function(data){
									     var n = Number(data);
									     if(n == 1){
									    	 var a = (Number)($("#agreenum"+id).html())+1;
									    	 $("#zan"+id).addClass("zanok");
									    	 $("#agreenum"+id).html(a);
									     }else{
									    	 layer.msg('请求错误');
									     }
									   }
									});
							}
							
						}
					</script>
					<div class="layui-form layui-form-pane" id="answerb">
						<form
							action="${pageContext.request.contextPath }/SubmitCommentServlet"
							method="post">
							<div class="layui-form-item layui-form-text">
								<div class="layui-input-block">
									<textarea id="L_content" name="content" required
										lay-verify="required" placeholder="我要回答"
										class="layui-textarea fly-editor" style="height: 150px;"></textarea>
								</div>
							</div>
							<input type="hidden" name="userid" value="${loginuser.id}">
							<input type="hidden" name="topicid" value="${topic.id}">
							<input type="hidden" name="commentid" value="" id="commentid">
							<input type="hidden" name="istopic" value="0" id="istopic">
							<script>
								 function hint1() {
							        //提示层
							        layer.msg('未登录');
							    }
							</script>
							<div class="layui-form-item">
								<input type="hidden" name="topic_id" value="${topic.id }">
								<input type="hidden" name="jid" value="{{rows.id}}">
								<!-- lay-submit -->
								<c:if test="${!empty loginuser}">
									<input type="submit" class="layui-btn" lay-filter="*"
										value="提交回答"></input>
								</c:if>
								<c:if test="${empty loginuser}">
									<input type="button" class="layui-btn" lay-filter="*"
										value="提交回答" onclick="hint1();">
								</c:if>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<div class="edge">
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
		}).use('fly', function() {
			var fly = layui.fly;
			//如果你是采用模版自带的编辑器，你需要开启以下语句来解析。
			/*
			$('.detail-body').each(function(){
			  var othis = $(this), html = othis.html();
			  othis.html(fly.content(html));
			});
			 */
		});
	</script>

</body>
</html>