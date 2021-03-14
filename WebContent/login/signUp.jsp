<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    import="jspexp.z01_vo.*"
    import="project.dao.login.*"
    import="project.vo_join.*"%>
<% request.setCharacterEncoding("UTF-8");
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%-- path기준으로 모든 자원(css,img,js)를 접근하여 사용할 수 있다. --%>
<link rel="stylesheet" href="../a00_com/reset.css"> 
<link rel="stylesheet" href="../a00_com/jquery-3.5.1.js"> 
<title>Insert title here</title>
<style>
html, body
{
    height: 100%;
}
.main
{
    height: 200px;
    width: 200px;
    background-color: blue;     
}
#signupbox
{
	padding-top:20px;
	position:absolute; 
    top:20%; left:40%;
}
#signup_text{
	padding-left:87px;
	padding-bottom:20px;
	margin-top:40px;
	font-size:20px; 
	margin-bottom:40px;
}

#input_name, #input_id,#input_pass,#input_pass_confirm{
	width:250px; 
	height:35px;
	color:gray;
	margin-bottom:30px;
}
 #input_email1, #input_email2{
 	width:120px; 
	height:35px;
	color:gray;
	margin-bottom:30px;
 }
#input_phone1, #input_phone2, #input_phone3{
	width:90px; 
	height:35px;
	color:gray;
	margin-bottom:30px;
}
#input_signUp{
	margin-top:50px;
	margin-bottom:10px;
	margin-left:10px;
	width:300px; 
	height:50px; 
	color: white; 
	background-color:#334858; 
	border-color:#334858";
}
#postcode, #extraAddress, #detailAddress{
	width:200px;
	height:35px;
}
#address{
	width:410px;
	height:35px;
}
#name_text, #email_text, #id_text,#pass_text,#pass_confirm_text,#terms_text, #phone_text, #address_text{
	margin-bottom:2px;
	margin-top:10px;
	font-size:14px;
}

#terms{
	padding:5px;
	border:1px solid gray;
	font-size:12px;
	width:300px;
}
#tab_terms{
	
}
#check, #check_all{
	margin_bottom:20px;
}
#select_email{
	width:120px;
	height:40px;
}
#strong{
	display:inline;
	color:red;
}

#check_id{	
	width:120px; 
	height:40px;
	color:white;
	background-color:#334858; 
	border-color:#334858";
	margin-bottom:30px;
	margin-left:20px;
}
#searchCode{
	width:120px; 
	height:40px;
	color:white;
	background-color:#334858; 
	border-color:#334858";
	margin-bottom:30px;
	margin-left:20px;
}

#check1, #check2, #check3, #check4, #check_all{
	width:20px;
	height:20px;
	margin-right:10px;
}
</style>
<link type ="text/css" rel="stylesheet" href="../a00_com/main.css"> 
</head>
<%
String name= request.getParameter("name");
if(name==null) name ="";
String customer_id = request.getParameter("customer_id");
if(customer_id==null) customer_id = "";
String pw = request.getParameter("pw");
if(pw==null) pw ="";
String pw_confirm = request.getParameter("pw_confirm");
if(pw_confirm==null) pw_confirm="";
String email1 = request.getParameter("email1");
String email2 = request.getParameter("email2");
if(email1==null) email1="";
if(email2==null) email2="";
String email = email1+"@"+email2;
String phone1 = request.getParameter("phone1");
String phone2 = request.getParameter("phone2");
String phone3 = request.getParameter("phone3");
if(phone1==null) phone1="";
if(phone2==null) phone2="";
if(phone3==null) phone3="";
String phone = phone1+"-"+phone2+"-"+phone3;
String postcode = request.getParameter("postcode");
String address1 = request.getParameter("address1");
String detailAddress = request.getParameter("detailAddress");
String extraAddress = request.getParameter("extraAddress");
if(address1==null) address1="";
if(extraAddress==null) extraAddress="";
if(detailAddress==null) detailAddress="";
String address = address1+" "+detailAddress;
log("#name: "+name);
log("#id: "+customer_id);
log("#pw: "+pw);
log("#phone: "+phone);
boolean isInit = true;
boolean hasId = false;
boolean forJoin = false;  // 회원가입완료와 중복확인 구분 
if(!customer_id.equals("")){
	isInit = false;
	DAO_login checkdao = new DAO_login();
	hasId = checkdao.checkId(customer_id);
}
if((!name.equals(""))&&(!customer_id.equals(""))&&(!pw.equals(""))&&(!pw_confirm.equals(""))&&(!email.equals(""))&&(!phone.equals(""))&&(!address.equals(""))){
	Customer cus = new Customer(customer_id, pw,name, address,email, phone);
	DAO_login dao = new DAO_login();
	dao.insertCustomer(cus);
	forJoin = true;  // 회원가입 완료 시 . 
}

%>
<body>
<script type="text/javascript" src="../a00_com/jquery-3.5.1.js"></script>
<script>
var forJoin = <%=forJoin%>;
if(forJoin){
	if(confirm("회원가입완료\n로그인페이지로 이동합니다!")){
		location.href="login.jsp";
	}
}
</script>
<div id="banner">
	<img id="banner" src="../image/image-main/cosamo.jpg" onclick="location.href='../main.jsp'">
</div>
	<div id="signupbox">
		
		<div id="signup_text">회원가입</div>
		<form method="post" id="frm">
			<div id="name_text">이름</div>
			<input id="input_name" type="text" name="name" placeholder="이름을 입력해주세요" value=""><br>

			<div id="id_text">아이디</div>
			<input id="input_id" type="text" name="customer_id" placeholder="아이디를 입력해주세요"  value="">
			<input id="check_id" type="button" value="중복확인" onclick="checkId()"><br>
		
			<div id="pass_text">비밀번호</div>
			<input id="input_pass" type="password" name="pw" placeholder="비밀번호는 8자 이상 입력해주세요"  value=""><br>
			<div id="pass_confirm_text">비밀번호확인</div>
			<input id="input_pass_confirm" type="password" name="pw_confirm" placeholder="비밀번호 확인"  value="">
			<br>
			<div id="email_text">이메일</div>
			<input id="input_email1" type="text" name="email1" placeholder="이메일을 입력해주세요"  value="">
				@
			<input id="input_email2" name="email2" type="text"  value="">
			<select name ="select_email" id="select_email" onchange="setEmail()">
				<option value="">선택하세요</option>
				<option value="naver.com">naver.com</option>
				<option value="gmail.com">gmail.com</option>
				<option value="hanmail.net">hanmail.net</option>
				<option value="daum.net">daum.net</option>
				<option value="1">직접입력</option>
			</select>
			<div id="phone_text">휴대폰 번호</div>
			<input id="input_phone1" size="20" name="phone1" value="010" > -
			<input id="input_phone2"  name="phone2" value=""  > -
			<input id="input_phone3"  name="phone3" value="" >
			<br>
			<div id="address_text">주소</div>
			<input type="text" id="postcode" name="postcode" placeholder="우편번호">
			<input type="button" id="searchCode" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br><br>
			<input type="text" id="address" name="address1" placeholder="주소"><br><br>
			<input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소">
			<input type="text" id="extraAddress" name="extraAddress" placeholder="참고항목">
			<br>
			<br>
			<div id="terms_text">약관 동의</div>
			<div id="terms">
				<input id="check_all" type="checkbox" name="check">전체동의<br>
				<hr>
				<input id="check1" type="checkbox" class="check" name="check" >만 14세 이상입니다.<p id="strong">(필수)</p> <br>
				<input id="check2" type="checkbox" class="check"  name="check" >이용약관 <p id="strong">(필수)</p> <br>
				<input id="check3" type="checkbox" class="check"  name="check"  >개인정보처리방침 <p id="strong">(필수)</p>  <br>
				<input id="check4" type="checkbox" class="check" name="check" >이벤트, 프로모션 알림 메일 및 SMS 수신 (선택)<br>
			</div>
			<input id="input_signUp" type="button" value="회원가입 완료" onclick="signUp()">
			</form>
			<jsp:useBean id="c1" class="project.vo_join.Customer"/>
  			<jsp:setProperty property="customer_id" name="c1"/>
  			<jsp:setProperty property="name" name="c1"/>
	</div>
	<!-- signupbox -->
</body>
<script>
function setEmail(emailValue){
	var select = document.querySelector("[name=select_email]");
	var input = document.querySelector("[name=email2]");
	if(select.value=='1'){
		input.readOnly = false;
        input.value = '';
        input.focus();
	}else{
		input.readOnly = true;
        input.value = select.value;
	}
}


function signUp(){
	var name = document.querySelector("[name=name]").value; 
	if(name.trim()==""){
		alert("이름을 입력해주세요");
		return;
	}
	var customer_id = document.querySelector("[name=customer_id]").value; 
	if(customer_id.trim()==""){
		alert("아이디를 입력해주세요");
		return;
	}
	
	var pw = document.querySelector("[name=pw]").value;
	if(pw.length<8){
		alert("비밀번호는 8자 이상 입력해주세요");
		return;
	}
	
	var check1 = document.getElementById("check1");
	var check2 = document.getElementById("check2");
	var check3 = document.getElementById("check3");
	if(!check1.checked||!check2.checked||!check3.checked){
		alert("필수약관에 동의해주세요");
		return;
	}
	
	// 유효성 체크 후 최종적으로 전송처리 
	document.querySelector("#frm").submit();
}

var isInit = <%=isInit%>
var hasId = <%=hasId%>
if(!isInit&&!forJoin){	//요청값이 있을 때 , = id를 확인했을 때  && 회원가입완료 아닐 때 
	if(hasId){
		alert("이미 존재하는 id입니다.")
		$("[name=name]").val("<%=c1.getName()%>");
		$("[name=customer_id]").focus();
	}else{
		alert("등록가능한 id 입니다.");
		$("[name=customer_id]").val("<%=c1.getCustomer_id()%>");
		$("[name=name]").val("<%=c1.getName()%>");
	}
}
function checkId(){
	document.querySelector("#frm").submit();
}

$("#check_all").click(function(){
	if($("#check_all").is(":checked")){
		$(".check").prop("checked",true);
	}else{
		$(".check").prop("checked",false);
	}
});

$(".check").click(function(){
	if($("input[name='check']:checked").length==3){
		$("#check_all").prop("checked",true);
	}else{
		$("#check_all").prop("checked",false);
	}
});


$("#input_pass_confirm").focusout(function(){
	var pass = document.getElementById("input_pass").value;
	var pass_confirm = document.getElementById("input_pass_confirm").value;
	if(pass==pass_confirm){
		$("#pass_confirm_text").text("비밀번호 확인").css("color","black");
	}else{
		$("#pass_confirm_text").text("비밀번호가 일치하지 않습니다.").css("color","red");
	}
});

</script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode;
                document.getElementById("address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("detailAddress").focus();
            }
        }).open();
    }
 
</script>
</html>