<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>无标题文档</title>
<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
}
.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}
.STYLE6 {color: #000000; font-size: 12px; }
.STYLE10 {color: #000000; font-size: 12px; }
.STYLE19 {
	color: #344b50;
	font-size: 12px;
}
.STYLE21 {
	font-size: 12px;
	color: #3b6375;
}
.STYLE22 {
	font-size: 12px;
	color: #295568;
}
-->
  a:link{color: black;}
</style>
  <script>
    function goPage() {
        var page = document.getElementById("page").value;
        if (page != "") {
          location.href = "/houtai/goodsvl/queryall?pageNow=" + page;
        }
    }

    function delGood(id,pageNow) {
        if(window.confirm("删除"+id+"的商品吗？")){
            location.href = "/houtai/goodsvl/delete?id="+id+"&pageNow="+pageNow;
        }
    }
  </script>
</head>

<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="/houtai/images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="STYLE1"> 商品基本信息列表</span></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      <tr>
        <td width="4%" height="20" bgcolor="d3eaef" class="STYLE10"><div align="center">
          <input type="checkbox" name="checkbox" id="checkbox" />
        </div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">商品编号</span></div></td>
        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">商品名称</span></div></td>
        <td width="14%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">商品类别</span></div></td>
        <td width="16%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">价格</span></div></td>
        <td width="16%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">操作</span></div></td>
      </tr>
      <c:forEach items="${goods}" var="good">
        <tr>
          <td height="20" bgcolor="#FFFFFF"><div align="center">
            <input type="checkbox" name="checkbox2" id="checkbox2" />
          </div></td>
          <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">${good.id}</span></div></td>
          <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${good.goodname}</div></td>
          <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${good.goodtype}</div></td>
          <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${good.price}</div></td>
          <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">
            <a href="javascript:delGood(${good.id},${pagenow})">删除</a>  |
            <a href="/houtai/goodsvl/mod?pageNow=${pagenow}&id=${good.id}">修改</a>
                </div>
          </td>
        </tr>
      </c:forEach>
    </table></td>
  </tr>
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="33%"><div align="left"><span class="STYLE22">&nbsp;&nbsp;&nbsp;&nbsp;共有<strong> ${totalcount}</strong> 条记录，当前第<strong>${pagenow}</strong> 页，共 <strong>${pagecount}</strong> 页</span></div></td>
        <td width="67%"><table width="312" border="0" align="right" cellpadding="0" cellspacing="0">
          <tr >
            <td width="49"><div  class="STYLE22" align="center" style="color: black;"><a href="/houtai/goodsvl/queryall?pageNow=1">首页</a> </div></td>
            <td width="49"><div class="STYLE22" align="center"><a href="/houtai/goodsvl/queryall?pageNow=${pagenow-1}">上一页</a></div></td>
            <td width="49"><div class="STYLE22" align="center"><a href="/houtai/goodsvl/queryall?pageNow=${pagenow+1}">下一页</a></div></td>
            <td width="49"><div class="STYLE22" align="center"><a href="/houtai/goodsvl/queryall?pageNow=${pagecount}">末页</a></div></td>
            <td width="37" class="STYLE22"><div align="center">转到</div></td>
            <td width="22"><div align="center">
              <input type="text" name="page" id="page"  style="width:20px; height:12px; font-size:12px; border:solid 1px #7aaebd;"/>
            </div></td>
            <td width="22" class="STYLE22"><div align="center">页</div></td>
            <td width="35"><button onclick="goPage()">go</button></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
