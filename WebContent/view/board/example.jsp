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
   $(document).ready(function(){
		$("#btnEdit").on("click",function(){
			location.href="${path}/writeDelete.do";
		})
   });
</script>
</head>
<body>

<div class="row">
    <div class="col-xs-2 col-md-2"></div>
    <div class="col-xs-8 col-md-8">
    <h2 class="text-center">게시글 보기</h2><p>&nbsp;</p>
    <div class="table table-responsive">
    <form action="boardList.do" method="post">
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
            <th class="success">제목</th>
            <td colspan="2">${dto.title }</td>
            <td id="favor">
            	<img style="cursor:pointer;" src="image/image-board/thumb.png" 
            		width="30px" id="thumb">
            </td>
        </tr>
         
        <tr>
            <th class="success">글 내용</th>
            <td colspan="3">${dto.content }</td>
        </tr>
		<tr>
			<th class="success">첨부파일</th>
			<td colspan="3">
				<c:if test="${dto.filesize>0 }">
				<a href="${path }/board_servlet/download.do?num=${dto.num}">
					${dto.filename }
					(${dto.filesize } bytes)
				</a>
				</c:if>
			</td>
		</tr>
		<tr>
			<td>
				<input type="hidden" name="num" value="${dto.num }">
				<input type="button" value="삭제" id="btnEdit">
			</td>
		</tr>
        </table>
	</form>
	
</div>
</body>
</html>