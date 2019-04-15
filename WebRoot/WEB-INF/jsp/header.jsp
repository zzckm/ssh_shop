<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
			<s:if test="#session.existUser!=null">
			<li id="headerLogin" class="headerLogin" style="display: list-item;">
				<s:property value="#session.existUser.name"/>
			</li>
			<li id="headerLogout" class="headerRegister" style="display: list-item;"> 
				<a href="${pageContext.request.contextPath}/user_quit.action">退出</a>|
			</li>
			</s:if>
			<s:else>
			<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/user_loginPage.action">登录</a>|
				</li>
				<li id="headerRegister" class="headerRegister" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/user_registPage.action">注册</a>|
				</li>
				</s:else>
				<li id="headerUsername" class="headerUsername"></li>
				
						<li>
							<a>会员中心</a>
							|
						</li>
						<li>
							<a>购物指南</a>
							|
						</li>
						<li>
							<a>关于我们</a>
							
						</li>
			</ul>
		</div>
		<s:if test="#session.existUser!=null">
		

		<div class="cart" style="margin-left:70px; width: 100px;float: left;">
			<a href="${pageContext.request.contextPath}/cart_myCart.action" style="margin-right: 5px;">购物车</a>
			<a href="${pageContext.request.contextPath}/order_findByUid.action">我的订单</a>
		</div>
		</s:if>
			<div class="phone">
				客服热线:
				<strong>025/58242112</strong>
			</div>
	</div>
</body>
</html>