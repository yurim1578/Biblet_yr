<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BiBlet 회원가입</title>
</head>
<body>
	<h1>회원 가입</h1>
	<form:form commandName="member">
		<div>
			<label>이름</label>
			<form:input path="mem_name" />
		</div>
		<div>
			<label>아이디</label>
			<form:input path="mem_id" />
		</div>
		<div>
			<label>비밀번호</label>
			<form:input path="mem_pass" />
		</div>
		<div>
			<label>닉네임</label>
			<form:input path="mem_nickname" />
		</div>
		<div>
			<label>이메일</label>
			<form:input path="mem_email" />
		</div>
		
		<div>
			<input type="submit" value="회원가입" />
		</div>
	</form:form>
</body>
</html>