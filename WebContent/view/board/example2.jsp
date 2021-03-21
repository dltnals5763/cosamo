<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    import="jspexp.z01_vo.*"
    import="jspexp.a03_database.*"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<c:set var="path" value="${pageContext.request.contextPath}"/> 
<fmt:requestEncoding value="UTF-8" /> 
<c:set var="readcnt" value="${dao.updateCount(dto.num) }"></c:set>
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
	if(proc=="upt"){
		if(confirm("수정되었습니다.\n조회화면으로 이동하시겠습니까?")){
			location.href='${path}/noticeList.do';
		}	
	}
	if(proc=="del"){
		alert('삭제되었습니다.');
		location.href='${path}/noticeList.do';
	}
	
	$(document).ready(function(){
		$("#uptBtn").on("click",function(){
			if(confirm("수정하시겠습니까?")){
				$("[name=proc]").val("upt");
				$('form').submit();
			}
		});
		$("#delBtn").on("click",function(){
			if(confirm("삭제하시겠습니까?")){
				$("[name=proc]").val("del");
				$('form').submit();
			}
		});
	});	
</script>
</head>
<%

%>
<body>

<div class="row">
    <div class="col-xs-2 col-md-2"></div>
    <div class="col-xs-8 col-md-8">
    <h2 class="text-center">게시글 보기</h2><p>&nbsp;</p>
    <div class="table table-responsive">
    <form method="post">
    <input type="hidden" name="proc" value=""/>
        <table class="table">
        
        <tr>
            <th class="success">글번호</th>
            <td>${dto.num }</td>
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
            <th class="success" id="title">제목</th>
            <td><input type="text" name="title" value="${dto.title }"/></td>
            <th class="success">추천수</th>
            <td>${dto.favor }</td>
        </tr>
         
        <tr height="250px">
            <th class="success" id="content">글 내용</th>
            <td colspan="3"><input type="text" name="content" value="${dto.content }"/></td>
        </tr>
		<tr>
			<td colspan="4" align="right">
				<input type="hidden" name="num" value="${dto.num }">
				<input type="hidden" name="category" value="${dto.category }">
				<input type="hidden" name="title" value="${dto.title }">
				<input type="hidden" name="content" value="${dto.content }">
				<c:if test="${id eq dto.id }">
				<input type="button" value="삭제" id="delBtn">
				<input type="button" value="수정" id="uptBtn">
				</c:if>
			</td>
		</tr>
        </table>
	</form>
	
</div>
</body>
</html>