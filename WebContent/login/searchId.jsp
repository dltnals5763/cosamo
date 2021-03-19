<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    import="jspexp.z01_vo.*"%>
<% request.setCharacterEncoding("UTF-8");
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%-- path기준으로 모든 자원(css,img,js)를 접근하여 사용할 수 있다. --%>
<link rel="stylesheet" href="<%=path %>/css/reset.css"> 
<title>Insert title here</title>
<style>
html, body
{
    height: 100%;
}

body
{
    display: table;
    margin: 0 auto;
}

.main
{
    height: 200px;
    width: 200px;
    background-color: blue;     
}
#searchbox
{
	padding:70px;
    height: 100%;
    display: center;
    text-align:center;
    vertical-align: middle;
    margin:0 auto;
}

#search_text{
	text-align:center;
	font-size:20px; 
	margin-bottom:40px;
}

#input_name, #input_email{
	width:250px; 
	height:50px;
	color:gray;
}


#input_searchId{
	margin-top:20px;
	margin-bottom:10px;
	width:258px; 
	height:50px; 
	color: white; 
	background-color:#334858; 
	border-color:#334858"
}
#name_text, #email_text{
padding-left:325px;
text-align:left;
margin-bottom:2px;
margin-top:10px;
font-size:14px;
}
</style>
<link type ="text/css" rel="stylesheet" href="../a00_com/main.css">  
</head>
<body>
<div id="banner">
	<img id="banner" src="../image/image-main/cosamo.jpg" onclick="location.href='../main_include.jsp'">
</div>
<div id="align">

	<div id="searchbox">
		<div id="search_text">아이디찾기</div>
		<form>
			<div id="name_text">이름</div>
			<input id="input_name" type="text" name="name" placeholder="고객님의 이름을 입력해주세요" ><br>
			<div id="email_text">이메일</div>
			<input id="input_email" type="text" name="email" placeholder="가입 시 등록하신 이메일을 입력해주세요" ><br>
			<input id="input_searchId" type="button" value="아이디찾기" onclick="searchId()" >
		</form>
	</div>
	<!-- searchbox -->
	</div>
</body>
</html>