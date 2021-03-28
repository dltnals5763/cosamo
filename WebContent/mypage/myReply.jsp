<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/> 
<fmt:requestEncoding value="UTF-8" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type ="text/css" rel="stylesheet" href="${path}/mypage/cssfile.css">
<link rel="stylesheet" href="${path}/a00_com/jquery-ui.css" >
<script src="${path}/a00_com/jquery.min.js"></script>
<script src="${path}/a00_com/popper.min.js"></script>
<%--
<script src="${path}/a00_com/bootstrap.min.js"></script>
 --%>
<script src="${path}/a00_com/jquery-ui.js"></script>
</head>
<body>
<h3 align="center">내가 쓴 댓글</h3>
   <div class="wFrame1">
      <table class="text-List">
         <col width="10%"><col width="20%"><col width="50%"><col width="20%">
         <tr><th>게시판</th><th>글인덱스</th><th>분류</th><th>작성일</th><tr>
         <%--
         <c:forEach var="border" items="${boardList3}" varStatus="vs">
          --%>
         <c:forEach var="border" items="${comList}" varStatus="vs">
         <c:if test="${member.id == border.guest_id}">
         <tr>
         	<td>
         	<c:choose>
         	<c:when test="${border.table_id == 1}">자유게시판</c:when>
         	<c:when test="${border.table_id == 2}">Java</c:when>
         	<c:when test="${border.table_id == 3}">Python</c:when>
         	<c:when test="${border.table_id == 4}">Javascript</c:when>
         	<c:when test="${border.table_id == 5}">html</c:when>
         	<c:when test="${border.table_id == 6}">C/C++</c:when>
         	<c:when test="${border.table_id == 7}">Spring</c:when>
         	</c:choose>
            </td>
            <td>${border.text_id}</td>
            <td>
            <c:choose>
            <c:when test="${border.comment_type == 1}">댓글</c:when>
            <c:when test="${border.comment_type == 2}">대댓글</c:when>
            </c:choose>
            </td>
            <td>><fmt:formatDate type="both" pattern="yyyy/MM/dd HH:mm:ss" value="${border.written_date}" /></td>
         <tr>      
         </c:if>
         </c:forEach>
      </table>
      <c:set var="cnt" value="${count}"/>
      <c:set var="page" value="${(empty param.p)?1:param.p}" />
      <c:set var="startNum" value="${page-(page-1)%5}"/>
      <c:set var="lastNum" value="${fn:substringBefore(Math.ceil(cnt/5), '.')}"/>
      <form method="get">
      <div class="paging">
            <input type="button" href="#" class="" name="pre" value="&laquo;"/>
            <c:forEach begin="${startNum}" end="${startNum+4}"
               varStatus="status">
               <c:if test="${lastNum >= status.current}">
                  <button type="button"
                     class="btn btn-${((page)==(status.current))?'':'outline-'}primary"
                     onclick="paging(${status.current})">${status.index}</button>
               </c:if>
            </c:forEach>
            <input type="button" href="#" class="" name="next" value="&raquo;"/><br><br>
      <a href="${path}/mySelector.do" id="gogo"><span class="text">마이페이지 목록</span></a>
      <a href="${path}/mainLeft.do" id="gogo"><span class="text">메인 화면</span></a>
      </div>
      </form>
   </div>
   <script type="text/javascript">
   function paging(page){
      location.href="?p="+page;
   }
   </script>
</body>
</html>