<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*" %>
<% request.setCharacterEncoding("UTF-8");
	String path = request.getContextPath();
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mypage Info</title>
<link type ="text/css" rel="stylesheet" href="${path}/inflearn/proView/cssfile.css">
<script type="text/javascript" 
	src="/jspexp/a00_com/jquery-3.5.1.js"></script>
<style type="text/css">
#frame01{
	width: 70%;
	margin:0 auto;
    padding-bottom: 120px;
}
.frame02{
	width: 1050px;
    margin: 0 auto;
}
.frame02 #info_form .myInfoBox{
    width: 700px;
    border-top: 2px solid black;
    margin:0 auto;
}
.frame02 #info_form .myInfoBox input[type=text]{
	width: 332px;
	height: 44px;
    padding: 0 14px;
    border: 1px solid #ccc;
    font-size: 14px;
    color: #333;
    line-height: 20px;
    border-radius: 3px;
    background: #fff;
    outline: none;
    vertical-align: top;
}
.frame02 #info_form .myInfoBox input[type=password]{
	width: 332px;
	height: 44px;
    padding: 0 14px;
    border: 1px solid #ccc;
    font-size: 14px;
    color: #333;
    line-height: 20px;
    border-radius: 3px;
    background: #fff;
    outline: none;
    vertical-align: top;
}
.frame02 #info_form .myInfoBox th{
    width: 159px;
    padding: 20px 0 0 20px;
    font-weight: 700;
    font-size: 14px;
    color: #333;
    line-height: 20px;
    vertical-align: top;
    text-align: center;
}
.frame02 #info_form .myInfoBox td{
    padding: 10px 0;
    border-top: 0;
    font-size: 14px;
    vertical-align: top;
    text-align: center;
}
.frame02 .footer{
	padding-top: 40px;
	text-align: center;
}
.frame02 .btn_main{
    border: 0;
    border-radius: 3px;
    font-weight: 700;
    font-size: 14px;
    line-height: 40px;
    text-align: center;
    display: inline-block;
    width: 300px;
    margin-right: 20px;
    border: 1px solid #cd595a;
    background-color: #cd595a;
    color: #fff;
}
.frame02 #update{
	border: 0;
    border-radius: 3px;
    font-weight: 700;
    font-size: 14px;
    line-height: 40px;
    text-align: center;
    display: inline-block;
    width: 300px;
    margin-left: 20px;
    border: 1px solid #cd595a;
    background-color: #cd595a;
    color: #fff;
}

</style>
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