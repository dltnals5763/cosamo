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
<div id="announce">
		<div id="announce-text">
			공지사항 
		</div>
		<!-- post-all-text -->
		<table id="post-all-tab">
			<tr><th>번호</th><th>카테고리</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th><th>좋아요</th></tr>
			<c:forEach var="b" items="${blist }">
			<tr><td>${b.num }</td><td>${b.category }</td><td>${b.title }</td><td>${b.id }</td><td>${b.reg_date }</td><td>${b.readcount }</td><td>${b.favor }</td></tr>
			</c:forEach>
		</table>
	</div>
	<!-- announce -->
	<div id="post-all">
		<div id="post-all-text">
			전체글 
		</div>
		<!-- post-all-text -->
		<table id="post-all-tab">
			
			<tr><th>번호</th><th>카테고리</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th><th>좋아요</th></tr>
			<tr><td>10</td><td>공지</td><td>404에러 났는데 함 봐주세요.. </td><td>HONG</td><td>2020-03-03</td><td>20</td><td>5</td></tr>
			<tr><td>10</td><td>JAVA</td><td>500에러 ...</td><td>HONG</td><td>2020-03-03</td><td>20</td><td>5</td></tr>
			<tr><td>10</td><td>자유게시판</td><td>자바공부 어떻게 하시나요</td><td>HONG</td><td>2020-03-03</td><td>20</td><td>5</td></tr>
			<tr><td>10</td><td>HTML</td><td>스터디 구합니다 </td><td>HONG</td><td>2020-03-03</td><td>20</td><td>5</td></tr>
			<tr><td>10</td><td>js</td><td>에러좀 봐주실분 </td><td>HONG</td><td>2020-03-03</td><td>20</td><td>5</td></tr>
			<tr><td>10</td><td>공지</td><td>취업스터디 모집합니다 </td><td>HONG</td><td>2020-03-03</td><td>20</td><td>5</td></tr>
		</table>
	</div>
	<!-- post-all -->
</body>
<script>
</script>
</html>








