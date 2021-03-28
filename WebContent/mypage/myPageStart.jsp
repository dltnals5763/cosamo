<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<c:set var="path" value="${pageContext.request.contextPath}"/>
<% request.setCharacterEncoding("UTF-8");
   String path = request.getContextPath();
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mypage select</title>
<link type ="text/css" rel="stylesheet" href="${path}/mypage/cssfile.css">
</head>
<body>

<h3 align="center">Mypage</h3>
<div class="selectBox">
   <a href="http://localhost:7080/cosa/mypage.do" class="goMyInfo"><span class="text">나의 정보</span></a>
   <a href="http://localhost:7080/cosa/myboader.do" class="goMyWrite"><span class="text">내가 쓴 게시글</span></a>
   <a href="http://localhost:7080/cosa/myreply.do" class="goMyReply"><span class="text">내가 쓴 댓글</span></a>
</div>
</body>
</html>