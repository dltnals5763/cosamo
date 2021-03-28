<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    import="java.util.*"
    import="jspexp.z01_vo.*"
    import="jspexp.a03_database.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<c:set var="path" value="${pageContext.request.contextPath}"/> 
<fmt:requestEncoding value="utf-8" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/a00_com/reset.css">
<link rel="stylesheet" href="${path}/a00_com/main.css">
<style>

</style>
<script type="text/javascript" 
  src="${path}/a00_com/jquery-3.5.1.js"></script>
<script type="text/javascript">
</script>
</head>
<%
	String logout=request.getParameter("logout");
	if(logout!=null&&logout.equals("y")){
		session.setAttribute("member",null);
		session.setAttribute("grade",null);
	}
%>
<body>
	<div id="container">
		<div id="banner">
			<img id="banner" src="${path}/image/image-main/cosamo.jpg" onclick="location.href='${path}/mainLeft.do'">
		</div>
		<%--  banner --%>
		<div id="main">
			<div id="left">
				<div id="info">
					<div id="info-title">
						나의 활동
					</div>
					<hr id="hr1">
					<%--  info-title --%>
					<div id="info-data1">
						<ul>
							<c:if test="${member!=null}"><li><span id="span-id">${member.id }</span>님</li></c:if>
							<c:if test="${member==null }"><li><span id="span-id"> - </span></li></c:if>
							<li>LEVEL<span id="span-level">${grade.grade}</span></li>
							<li>게시글 수<span id="span-write">${member.postcnt}</span></li>
							<li>댓글 수<span id="span-comment">${member.commentcnt }</span></li>
						</ul>
					</div>
					<%--  info-data1 --%>
					<c:if test="${member==null }">
					<div id="join-box">
						<div id="join-text" onclick="location.href='login/signUp.jsp'">카페 가입하기</div>
					</div>
					</c:if>
					<c:if test="${member!=null}"><hr></c:if>
					<%--  join-box --%>
					<div id="board-intro">
						<ul>
							<li>전체글보기</li>
							<li>베스트게시글</li>
						</ul>
						<hr id="hr2">
					</div>
					<%--  board-info --%>
					<div id="board-list">
						<ul>
							<li onclick="location.href='noticeList.do'">공지사항</li>
							<li onclick="location.href='boardList.do?category=사담'">
								자유게시판</li>
							<li onclick="location.href='boardList.do?category=Java'">
								Java</li>
							<li onclick="location.href='boardList.do?category=Python'">
								Python</li>
							<li onclick="location.href='boardList.do?category=Javascript'">
								JavaScript</li>
							<li onclick="location.href='boardList.do?category=html'">
								html</li>
							<li onclick="location.href='boardList.do?category=C/C++'">
								C/C++</li>
							<li onclick="location.href='boardList.do?category=Spring'">
								Spring</li>								
						</ul>
						<hr>
					</div>
					<%-- board-list --%>
					<div id="latest-comment">
						<div id="latest-comment-title">최근댓글</div>
						<ul>
							<li>견과류 멸치볶음..</li>
							<li>시지프스 논란..</li>
							<li>영화 저 산 너머..</li>
						</ul>
					</div>
					<%--  latest-comment --%>
					<div id="admin-info">
						<div id="admin-info-title">사업자정보</div>
						<div id="admin-info-data1">
						<ul>
							<li>상호  <span>코딩을 사랑하는 사람들</span></li>
							<li>대표  <span>3조</span></li>
							<li>메일  <span>3jo@gmail.com</span></li>
							<li>계좌  <span>신한 110-333-333333</span></li>
							<li>사업자번호  <span>3333</span></li>
							<li>이용약관  <span id="terms">이용약관보기</span></li>
						</ul>
						</div>
						<%--  admin-info-data1 --%>
					</div>
					<%--  admin-info --%>
				</div>
				<%--  info --%>
			</div>
			<%--  left --%>
			<div id="mid">
				<c:if test="${member!=null }"><div id="logout" onclick="logout()">로그아웃</div></c:if>
				<c:if test="${member==null }"><div id="login" onclick="location.href='${path}/mainLeft.do'">로그인</div></c:if>
			</div>
			<%--  mid --%>
		</div>
		<%--  main --%>
	</div>
	<%--  container --%>
</body>
<script>
	function logout(){
		alert("로그아웃되었습니다.");
		location.href="main_include.jsp?logout=y";
	}
</script>
</html>








