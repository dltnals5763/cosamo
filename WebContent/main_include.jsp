<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    import="java.util.*"
    import="jspexp.z01_vo.*"
    import="jspexp.a03_database.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<c:set var="path" value="${pageContext.request.contextPath}"/> 
<fmt:requestEncoding value="utf-8" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/a00_com/reset.css">
<link rel="stylesheet" href="${path}/a00_com/main.css">
<style>

</style>
<script type="text/javascript" 
  src="${path}/a00_com/jquery-3.5.1.js"></script>
<script type="text/javascript">
<%--
 
 
--%>
//
</script>
<body>
<jsp:include page="main.jsp" flush="false"/>
<div id="mainlist">
<div id="announce">
		<div id="announce-text">
			공지사항 
		</div>
		<%-- post-all-text --%>
		<table id="post-all-tab">
			<tr><th>번호</th><th>카테고리</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th><th>좋아요</th></tr>
			<c:forEach var="a" items="${alist }" varStatus="sts">
			<tr><td id="acnt${sts.index }"></td><td>${a.category }</td><td>${a.title }</td><td>${a.id }</td><td>${a.reg_date }</td><td>${a.readcount }</td><td>${a.favor }</td></tr>
			</c:forEach>
		</table>
	</div>
	<%--  announce --%>
	<div id="post-all">
		<div id="post-all-text">
			전체글 
		</div>
		<%--  post-all-text --%>
		<table id="post-all-tab">
			<tr><th>번호</th><th>카테고리</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th><th>좋아요</th></tr>
			<c:forEach var="b" items="${blist }" varStatus="sts">
			<c:if test="${sts.index<=10 }">
			<tr><td id="bcnt${sts.index }"></td><td>${b.category }</td><td>${b.title }</td><td>${b.id }</td><td>${b.reg_date }</td><td>${b.readcount }</td><td>${b.favor }</td></tr>
			</c:if>
			</c:forEach>
		</table>
	</div>
	<%--  post-all --%>
	</div>
	<%--  mainlist --%>
</body>
<script>
	var acnt = ${alist.size()};
	var idxcnt1 = acnt;
	for(var idx=0;idx < acnt;idx++){
		document.getElementById("acnt"+idx).innerHTML = idxcnt1--;
	}
	
	var bcnt = ${blist.size()};
	var idxcnt2 = bcnt;
	for(var idx=0;idx < bcnt;idx++){
		document.getElementById("bcnt"+idx).innerHTML = idxcnt2--;
	}
</script>
</html>








