<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import ="java.util.*"
    import="jspexp.a03_database.*"
    %>
<% request.setCharacterEncoding("UTF-8");
	String path = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=path%>/a00_com/a00_com.css">
<style>

</style>
<script type="text/javascript" src="<%=path%>/a00_com/jquery-3.5.1.js"></script>
<script type="text/javascript">
/* 
 
 
*/
//

   $(document).ready(function(){
      $("h2").text("시작");
   });
</script>
</head>
<body>
   <%
   	session.invalidate();
   	//response.sendRedirect("/project/index.jsp");
   %>
   <script>
   	alert('로그아웃 되었습니다');
   	location.href= "<%=path%>/index.jsp";
   </script>
   
</body>
</html>