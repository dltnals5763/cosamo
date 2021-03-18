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
<title>Mypage Info</title>
<link type ="text/css" rel="stylesheet" href="${path}/mypage/cssfile.css">
<script type="text/javascript" 
	src="/jspexp/a00_com/jquery-3.5.1.js"></script>
</head>
<body>
<h3 align="center">나의 정보</h3>
<div id="frame01">
	<div class="frame02">
	<form id="info_form">
		<table class="myInfoBox">
			<input type="hidden" name="isUpdate" value=""/>
			<tr><th>아이디</th><td><input type="text" style="border:none;" maxlength="16" readonly="readonly" name="id" value="${userInfo.id}"/></td></tr>
			<tr><th>비밀번호</th><td><input type="password" maxlength="16" name="pass" value="${userInfo.pass}"/></td></tr>
			<tr><th>이메일</th><td><input type="text" maxlength="16" name="email" value="${userInfo.email}"/></td></tr>
			<tr><th>전화번호</th><td><input type="text" maxlength="16" name="phonNum" value="${userInfo.phonNum}"/></td></tr>
			<tr><th>주소</th><td><input type="text" maxlength="16" name="address" value="${userInfo.address}"/></td></tr>
		</table>
		</form>
		<div class="footer">
			<a href="" class="btn_main">메인으로</a>
			<input type="submit" id="update" value="정보수정"></input>
		</div>
	</div>
</div>
<script type="text/javascript">
		var result = "${result}";
		if(result=="true"){
			alert("수정이 완료되었습니다.");
		}
	$('#update').on('click', function(){
		$('[name=isUpdate]').val('true');
		$('#info_form').submit();
	});
</script>
</body>
</html>