<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    import="board.dto.*"
    import="board.dao.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% request.setCharacterEncoding("UTF-8");
%>    
 <jsp:useBean id="dao" class="board.dao.ListDao"></jsp:useBean>
 <c:set var="categoryList" value="${dao.categoryList(param.category)}"></c:set>
 {"tot":${categoryList.size()},"categoryList":[
 	<c:forEach var="dto" items="${categoryList}" varStatus="sts">
 		{"num":${dto.num},"category":"${dto.category}","title":"${dto.title}","writer":"${dto.writer}", "reg_date":"${dto.reg_date }","readcount":${dto.readcount}}
 		<c:if test="${!sts.last}">,</c:if>
 	</c:forEach>
 ]}
