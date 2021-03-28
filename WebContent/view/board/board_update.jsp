<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    import="jspexp.z01_vo.*"
    import="jspexp.a03_database.*"
    import="java.util.*"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="form"
      uri="http://www.springframework.org/tags/form"%>   
<c:set var="path" value="${pageContext.request.contextPath}"/> 
<fmt:requestEncoding value="UTF-8" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/a00_com/bootstrap.min.css" >
<link rel="stylesheet" href="${path}/a00_com/jquery-ui.css" >
<style>
    #contentForm {
      width: 100%;
      margin: 0 auto;
      padding-top: 12%;
    }
 
    .table > thead > tr > th, .table > tbody > tr > th {
      background-color: #eeeeee;
      text-align: center;
      width:20%;
    }
    .table > thead > tr > td, .table > tbody > tr > td {
      width:30%;
    }
</style>
<script src="${path}/a00_com/jquery.min.js"></script>
<script src="${path}/a00_com/popper.min.js"></script>
<script src="${path}/a00_com/bootstrap.min.js"></script>
<script src="${path}/a00_com/jquery-ui.js"></script>
<script type="text/javascript">
<%--
 
 
--%>
//
   var proc="${param.proc}";
   var num ="${param.num}";
   var categoty="${param.category}";
   if(proc=="upt"){
      
   }
    $(document).ready(function(){
      $("#uptBtn").on("click",function(){
         $("[name=proc]").val("upt");
         if(confirm("수정하시겠습니까?")){
            $('form').submit();
         }
      });
   });

</script>
</head>
<jsp:useBean id="now" class="java.util.Date"></jsp:useBean>
<body>
<%
   Calendar cal = Calendar.getInstance();
%>
<div class="row">
    <div class="col-xs-2 col-md-2"></div>
    <div class="col-xs-8 col-md-8">
    <h2 class="text-center">글 수정</h2><p>&nbsp;</p>
    <div class="table table-responsive">
    <form method="post" action="${path}/boardDetail.do">
    <input type="hidden" name="proc" value=""/>
        <table class="table">
        
        <tr>
            <th class="success">카테고리(글번호)</th>
            <td>${dto.category }(${dto.num })</td>
            <th class="success">조회수</th>
            <td>${dto.readcount }</td>
        </tr>
           
         
        <tr>
            <th class="success">작성자</th>
            <td>${dto.id }</td>
            <th class="success">작성일</th>
            <td>${dto.reg_date }</td>
        </tr>         
        <tr>
            <th class="success">제목</th>
            <td><input type="text" name="title" value="${dto.title }"/></td>
            <th class="success">추천수</th>
            <td>${dto.favor }</td>
        </tr>
         
        <tr height="250px">
            <th class="success">글 내용</th>

            <td colspan="3"><textarea rows="7" cols="90" name="content">${dto.content}
               </textarea></td>   
        </tr>
      <tr>
         <td colspan="4" align="right">
            <input type="hidden" name="num" value="${dto.num }">
            <input type="hidden" name="category" value="${dto.category }">
            <input type="hidden" name="title" value="${param.title }">
            <input type="hidden" name="content" value="${param.content }">
            <input type="hidden" name="proc" value=""/>
            <input type="button" value="수정완료" id="uptBtn">
         </td>
      </tr>
        </table>
   </form>
   
</div>
</body>
</html>