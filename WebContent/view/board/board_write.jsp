<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>    
<!--<c:set var="path" value="${pageContext.request.contextPath}"/> -->
<fmt:requestEncoding value="UTF-8" /> 
<!DOCTYPE html> 
<%request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
<meta charset="UTF-8">
<!-- 루트 폴더에 부트스트랩을 참조하는 링크 -->
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<title>글쓰기</title>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" 
  src="${path}/a00_com/jquery-3.5.1.js"></script>
<script type="text/javascript">
<%--
 
 
--%>
//	
/* 	$(document).ready(function(){
		$("#insBtn").on("click",function(){
			if($("[name=title]").val()==""){
				alert('글 제목을 입력하세요.');
				$("[name=title]").focus();
				return false;
			}
			if($("[name=content]").val()==""){
				alert('글 내용을 입력하세요.');
				$("[name=content]").focus();
				return false;
			}
			$("form").submit();
			$(location).attr("href","boardList.do");
		})
	}); */
</script>
<style>
* {
	margin: 4px 0;
}

.controller {
	padding: 25px 0;
	margin: auto;
	width: 840px;
}

#wriTitle{
	text-align: center;
	background-color: rgb(100, 100, 100);
	width: 800px;
	height: 20px;
	padding: 12px 0;
	color: white;
}
table {
	width: 840px;
	margin: 25px 0;
	padding: 20px;
	border-collapse: collapse;
}

#category {
	width: 100px;
	height: 30px;
}

#title {
	width: 700px;
	height: 24px;
}

textarea {
	width: 800px;
	height: 400px;
}

.button {
	width: 100px;
	padding: 5px 12px;
	border: none;
	background-color: rgb(150, 60, 60);
	color: white;
}

button {
	padding: 5px 12px;
	background-color: white;
	border-color: rgb(180, 180, 180);
	border-width: 1px;
}

textarea {
	resize: none;
}
</style>
</head>
<body>
	<!-- 게시판 글쓰기 양식 영역 시작 -->
	<div class="controller">
	<div id="wriTitle">게시글 작성</div>
		<form method="post" ENCTYPE="multipart/form-data">
			<table>
				<tr>
					<td width="110px">카테고리</td>
					<td>
					<select name="category" required id="category">
						<option value="category">사담</option>
						<option value="category">Java</option>
						<option value="category">Python</option>
						<option value="category">Javascript</option>	
						<option value="category">html</option>
						<option value="category">C/C++</option>
						<option value="category">Spring</option>
					</select>
					</td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="title" id="title"/></td>
				</tr>	
				<tr>
					<td colspan="2">
						<textarea rows="12" cols="50" name="content" id="content"></textarea>
					</td>	
				</tr>
				<tr>
					<td><input type="file" name="uploadFile" id="uploadFile"/>
				</tr>
				<tr>
					<td align="center"><input type="submit" value="작성" class="button"></td>
				</tr>
			</table>
		</form>
		<div>
			<a href="bbs.do">
				<button>게시판</button>
			</a>
		</div>
		<div>
			<a href="home.do">
				<button>홈으로</button>
			</a>
		</div>
	
	</div>
	
	<!-- 부트스트랩 참조 영역 -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script>

	</script>
</body>
</html>




