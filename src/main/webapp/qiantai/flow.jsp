<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<link href="/qiantai/css/style.css" rel="stylesheet" type="text/css" />
	<script>
		function clearCar(){
			window.location.href = "${pageContext.request.contextPath}/qiantai/svl/clearGood";

		}
	</script>
</head>
<body>
<!--LOGO欢迎信息和登陆注册功能-->
<div class="block clearfix">
	<div class="f_l">
		<a href="index.php.htm" name="top"><img src="/qiantai/images/logo.gif"/></a>
	</div>
 	<div class="f_r log">
		<ul>
	    	<li class="userInfo">
  				<font id="ECS_MEMBERZONE">
					<div id="append_parent"></div>
 					欢迎光临本店&nbsp;&nbsp;&nbsp;&nbsp;
 				</font>
   			</li>
      	</ul>
 	</div>
</div>
<div  class="blank"></div>

<!--顶层功能导航栏-->
<div id="mainNav" class="clearfix">
	<a href="${pageContext.request.contextPath}/qiantai/svl/open" class="cur">首页<span></span></a>
	<a href="###">买家必看<span></span></a>
	<a href="###">优惠活动<span></span></a>
	<a href="${pageContext.request.contextPath}/qiantai/svl/openFlow">查看购物车<span></span></a>
	<a href="###">报价单<span></span></a>
	<a href="###">留言板<span></span></a>
	<a href="###">团购商品<span></span></a>
</div>
<div class="block">
	<div class="box">
		<div class="helpTitBg clearfix"></div>
	</div>
</div>
<div class="blank"></div>

<!--购物车数据显示区-->
<div class="flowBox">
	<h6><span>商品列表</span></h6>
	<form id="formCart" name="formCart" method="post" action="${pageContext.request.contextPath}/qiantai/svl/modGood">
<%--		<input type="hidden" name="bs" value="modGood"/>--%>
		<table width="99%" align="center" border="0" cellpadding="5" cellspacing="1" bgcolor="#dddddd">
			<tr>
				<th bgcolor="#ffffff">商品编号</th>
				<th bgcolor="#ffffff">商品名称</th>
				<th bgcolor="#ffffff">本店单价</th>
				<th bgcolor="#ffffff">购买数量</th>
				<th bgcolor="#ffffff">小计</th>
				<th bgcolor="#ffffff">操作</th>
			</tr>
			<c:forEach items="${goods}" var="good">
			<tr>
				<td bgcolor="#ffffff" align="center">${good.id}<input type="hidden" name="id" value="${good.id}"> </td>
				<td bgcolor="#ffffff" align="center">${good.goodname}</td>
				<td bgcolor="#ffffff" align="center">${good.price}</td>
				<td bgcolor="#ffffff" align="center"><input type="text" name="amount" value="${good.amount}"/></td>
				<td bgcolor="#ffffff" align="center">${good.amount*good.price}</td>
				<td bgcolor="#ffffff" align="center"><a href="${pageContext.request.contextPath}/qiantai/svl/delGood?id=${good.id}">删除</a></td>
			</tr>
			</c:forEach>
		</table>
		<table width="99%" align="center" border="0" cellpadding="5" cellspacing="1" bgcolor="#dddddd">
			<tr>
				<td bgcolor="#ffffff">
					购物金额总计计 ￥${balance}元
				</td>
				<td align="right" bgcolor="#ffffff">
					<input type="button" value="清空购物车" class="bnt_blue_1" onclick="clearCar()" />
					<input name="submit" type="submit" class="bnt_blue_1" value="更新购物车" />
				</td>
			</tr>
		</table>
	</form>
	<table width="99%" align="center" border="0" cellpadding="5" cellspacing="0" bgcolor="#dddddd">
		<tr>
			<td bgcolor="#ffffff">
				<a href="${pageContext.request.contextPath}/qiantai/svl/open"><img src="/qiantai/images/continue.gif" alt="continue" /></a>
			</td>
			<td bgcolor="#ffffff" align="right">
				<a href="flow.php-step=checkout.htm"><img src="/qiantai/images/checkout.gif" alt="checkout" /></a>
			</td>
		</tr>
	</table>
</div>
<div class="blank5"></div>

<!--友情连接区-->
<div id="bottomNav" class="box">
	<div class="box_1">
		<div class="links clearfix">
			[<a href="###" target="_blank" title="友情连接1">友情连接1</a>]
			[<a href="###" target="_blank" title="友情连接1">友情连接2</a>]
			[<a href="###" target="_blank" title="友情连接1">友情连接3</a>]
			[<a href="###" target="_blank" title="友情连接1">友情连接4</a>]
			[<a href="###" target="_blank" title="友情连接1">友情连接5</a>]
		</div>
	</div>
</div>
<div class="blank"></div>

<!--底层导航栏-->
<div id="bottomNav" class="box">
	<div class="box_1">
		<div class="bNavList clearfix">
   			<div class="f_l"></div>
   			<div class="f_r">
				<a href="#top"><img src="/qiantai/images/bnt_top.gif" /></a>
				<a href="###"><img src="/qiantai/images/bnt_home.gif"/></a>
   			</div>
  		</div>
 	</div>
</div>
<div class="blank"></div>

<!--版权信息栏-->
<div class="text"align="center">
 &copy; 2010-2015 网上商城 版权所有，并保留所有权利。<br />
          E-mail: 123456@qq.com<br />
    ICP备案证书号:<a href="###" target="_blank">粤ICP备1234568</a><br />
          <div align="center"  id="rss"><a href="###"><img src="/qiantai/images/xml_rss2.gif"alt="rss" /></a></div>
</div>
</body>
</html>
