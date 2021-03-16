<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    import="jspexp.z01_vo.*"
    import ="project.vo.login.*"
    import ="project.dao.login.*"    
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<c:set var="path" value="${pageContext.request.contextPath}"/> 
<fmt:requestEncoding value="utf-8" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%-- path기준으로 모든 자원(css,img,js)를 접근하여 사용할 수 있다. --%>
<link rel="stylesheet" href="${path}/a00_com/reset.css">
<link rel="stylesheet" href="${path}/a00_com/main.css">
<title>Insert title here</title>
<style>
html, body
{
    height: 100%;
}

body
{
    margin: 0 auto;
}

.main
{
    height: 200px;
    width: 200px;
    background-color: blue;     
}

#loginbox
{	
	padding:70px;
    height: 100%;
    display: center;
    text-align:center;
    vertical-align: middle;
    margin:0 auto;
}
#login_text{
	text-align:center;
	font-size:20px; 
	margin-bottom:20px;
}

#input_id, #input_pass{
	width:250px; 
	height:50px;
}

#search_id{
	display:inline-block;
	widgh:120px;
	height:10px;
	font-size:14px;
	padding:0px 0px 0px 110px;
	margin-top:20px;
	margin-bottom:15px;
}
#search_pass
{
	display:inline-block;
	widgh:120px;
	height:10px;
	font-size:14px;
	padding:0px 0px 0px 10px;
}
#input_login, #input_signUp{
	margin-bottom:10px;
	width:258px; 
	height:50px; 
	color: white; 
	background-color:#334858; 
	border-color:#334858;
}
#align{
	text-align:right;
}
</style>

<script>
   window.onload=function(){

   };
</script> 
</head>
 <%
      String id = request.getParameter("id");
      String pass= request.getParameter("pass");
      
      // customer 테이블에 있는지 체크 return booelan
      customer_dao dao = new customer_dao();
      boolean check = dao.isInTable(id, pass);
      if(id!=null && pass!=null){
         if(check){
            if(id.equals("admin")){
               session.setAttribute("id",id);
               session.setAttribute("pw",pass);
               response.sendRedirect("../../admin/admin01.jsp");
            }else{
               session.setAttribute("id",id);
               session.setAttribute("pw",pass);
               // 로그인 id 쏴줘야함
               response.sendRedirect("../main.jsp");
            }
         }
         else {
%>
         <script>
            alert("로그인 정보가 올바르지 않습니다.");
            location.href="${path}/mainLeft.do";
         </script>
<%
         }
      }  
%>
<body>
 <div id="banner">
	<img id="banner" src="${path }/image/image-main/cosamo.jpg" onclick="location.href='${path}/main.jsp'">
</div>
   <div id = "align">
   <div id ="loginbox">
  	
      <div id="login_text">로그인</div>
      <form id="frm" method="post">
         <input id="input_id" type="text" name="id" value="아이디" onfocus="this.value=''" ><br>
         <input id="input_pass" type="text" name="pass" value="비밀번호" onfocus="this.value=''; type='password';">
         <div id="search">
            <p id="search_id" onclick="searchId()">아이디찾기</p>
            <p id="search_pass" onclick="searchPass()">비밀번호찾기</p>
         </div>
         <!-- search -->
         <input id="input_login" type="button" value="로그인" onclick="login1()"> <br>
         <input id="input_signUp" type="button" value="회원가입" onclick="signUp()" >
      </form>
   </div>
   <!-- loginbox -->
   </div>
</body>

            
<script>
   function login1(){
      var id = document.querySelector("[name=id]").value;
      var pass = document.querySelector("[name=pass]").value;
      var frm = document.querySelector("#frm");
      if(id=='아이디'|id==''){
         alert('아이디를 입력해주세요');
      }else if(pass=='비밀번호'|pass==''){
         alert('비밀번호를 입력해주세요');
      }else{
         if(id=='admin'){
            frm.submit();
            
         }else{
            frm.submit();
            
         }
      }
   }
   
   
   function signUp(){
      location.href="signUp.jsp";
   }
   function searchId(){
      location.href="searchId.jsp";
   }
   function searchPass(){
      location.href="searchPass.jsp";
   }
</script>
</html>