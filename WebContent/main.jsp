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
<%--
 
 
--%>
//
</script>
</head>
<%
	String id = (String)session.getAttribute("id");
	String logout=request.getParameter("logout");
	if(logout!=null&&logout.equals("y")){
		session.setAttribute("id",null);
		// response.sendRedirect("login/login.jsp");
	}
%>
<body>
	<div id="container">
		<div id="banner">
			<img id="banner" src="image/image-main/cosamo.jpg" onclick="location.href='${path}/main.jsp'">
		</div>
		<!-- banner -->
		<div id="main">
			<div id="left">
				<div id="info">
					<div id="info-title">
						나의 활동
					</div>
					<hr id="hr1">
					<!-- info-title -->
					<div id="info-data1">
						<ul>
							<c:if test="${id!=null}"><li><span id="span-id">${id }</span>님</li></c:if>
							<c:if test="${id==null }"><li><span id="span-id"> - </span></li></c:if>
							<li>LEVEL<span id="span-level">GOLD</span></li>
							<li>게시글 수<span id="span-write">10</span></li>
							<li>댓글 수<span id="span-comment">20</span></li>
						</ul>
					</div>
					<!-- info-data1 -->
					<c:if test="${id==null }">
					<div id="join-box">
						<div id="join-text" onclick="location.href='login/signUp.jsp'">카페 가입하기</div>
					</div>
					</c:if>
					<c:if test="${id!=null}"><hr></c:if>
					<!-- join-box -->
					<div id="board-intro">
						<ul>
							<li>전체글보기</li>
							<li>베스트게시글</li>
						</ul>
						<hr id="hr2">
					</div>
					<!-- board-info -->
					<div id="board-list">
						<ul>
							<li>공지사항</li>
							<li>자유게시판</li>
							<li>JAVA</li>
							<li>HTML/CSS</li>
							<li>JavaScript</li>
							<li>JQUERY</li>
							<li>취업뽀개기</li>
						</ul>
						<hr>
					</div>
					<!-- board-list -->
					<div id="latest-comment">
						<div id="latest-comment-title">최근댓글</div>
						<ul>
							<li>견과류 멸치볶음..</li>
							<li>시지프스 논란..</li>
							<li>영화 저 산 너머..</li>
						</ul>
					</div>
					<!-- latest-comment -->
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
						<!-- admin-info-data1 -->
					</div>
					<!-- admin-info -->
				</div>
				<!-- info -->
			</div>
			<!-- left -->
			<div id="mid">
			
				<c:if test="${id!=null }"><div id="logout" onclick="logout()">로그아웃</div></c:if>
				<c:if test="${id==null }"><div id="login" onclick="location.href='${path}/login/login.jsp'">로그인</div></c:if>
				<div id="list">
				<div id="announce">
					<div id="announce-text">
						공지사항 
					</div>
					<!-- post-all-text -->
					<table id="post-all-tab">
						
						<tr><th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th><th>좋아요</th></tr>
						<tr><td>5</td><td>[필독] 코사모 커뮤니티 규정</td><td>ADMIN</td><td>2020-03-03</td><td>20</td><td>5</td></tr>
						<tr><td>4</td><td>[필독] 회원 등급별 규정</td><td>ADMIN</td><td>2020-03-03</td><td>20</td><td>5</td></tr>
						<tr><td>3</td><td>[공지]	 통합공지 </td><td>ADMIN</td><td>2020-03-03</td><td>20</td><td>5</td></tr>
						<tr><td>2</td><td>이벤트 공지 </td><td>ADMIN</td><td>2020-03-03</td><td>20</td><td>5</td></tr>
						<tr><td>1</td><td>개정 규정 공지 </td><td>ADMIN</td><td>2020-03-03</td><td>20</td><td>5</td></tr>
					</table>
				</div>
				<!-- announce -->
				<div id="post-all">
					<div id="post-all-text">
						전체글 
					</div>
					<!-- post-all-text -->
					<table id="post-all-tab">
						
						<tr><th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th><th>좋아요</th></tr>
						<tr><td>10</td><td>404에러 났는데 함 봐주세요.. </td><td>HONG</td><td>2020-03-03</td><td>20</td><td>5</td></tr>
						<tr><td>10</td><td>500에러 ...</td><td>HONG</td><td>2020-03-03</td><td>20</td><td>5</td></tr>
						<tr><td>10</td><td>자바공부 어떻게 하시나요</td><td>HONG</td><td>2020-03-03</td><td>20</td><td>5</td></tr>
						<tr><td>10</td><td>스터디 구합니다 </td><td>HONG</td><td>2020-03-03</td><td>20</td><td>5</td></tr>
						<tr><td>10</td><td>에러좀 봐주실분 </td><td>HONG</td><td>2020-03-03</td><td>20</td><td>5</td></tr>
						<tr><td>10</td><td>취업스터디 모집합니다 </td><td>HONG</td><td>2020-03-03</td><td>20</td><td>5</td></tr>
					</table>
				</div>
				<!-- post-all -->
				
				</div>
				<!-- list -->
			</div>
			<!-- mid -->
		</div>
		<!-- main -->
		<div id ="footer">
			
		</div>
		<!-- footer -->
	</div>
	<!-- container -->
</body>
<script>
	function logout(){
		alert("로그아웃되었습니다.");
		location.href="main.jsp?logout=y";
	}
</script>
</html>








