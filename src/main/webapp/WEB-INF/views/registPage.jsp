<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BiBlet 회원가입</title>
<script type="text/javascript">
	function fnChkByte(obj, maxByte,id) {
		var str = obj.value;
		var str_len = str.length;
		var input = document.getElementById(id);
		var rbyte = 0;
		var rlen = 0;
		var one_char = "";
		var str2 = "";

		for (var i = 0; i < str_len; i++) {
			one_char = str.charAt(i);
			if (escape(one_char).length > 4) {
				rbyte += 3; //한글3Byte
			} else {
				rbyte++; //영문 등 나머지 1Byte
			}
			if (rbyte <= maxByte) {
				rlen = i + 1; //return할 문자열 갯수
			}
		}
		if (rbyte > maxByte) {
			// alert("한글 "+(maxByte/3)+"자 / 영문 "+maxByte+"자를 초과 입력할 수 없습니다.");
			alert("메세지는 최대 " + maxByte + "byte를 초과할 수 없습니다.")
			str2 = str.substr(0, rlen); //문자열 자르기
			obj.value = str2;
			fnChkByte(obj, maxByte);
		} else {
			input.innerText = rbyte;
			
		}
	}
</script>
</head>
<body>
	<h1>회원 가입</h1>
	<form:form commandName="member">
		<div>
			<label>이름</label>
			<form:input path="mem_name" limitbyte="50"
				placeholder="한글 6자, 영문50자 이내"
				onKeyUp="javascript:fnChkByte(this,'50','byteInfo')" />
			<span id="byteInfo">0</span> / 50bytes
			<form:errors path="mem_name" />
		</div>
		<div>
			<label>아이디</label>
			<form:input path="mem_id" limitbyte="100"
				placeholder="한글 33자, 영문100자 이내" 
				onKeyUp="javascript:fnChkByte(this,'100','byteInfo2')"/>
				<span id="byteInfo2">0</span> / 100bytes
			<form:errors path="mem_id" />
		</div>
		<div>
			<label>비밀번호</label>
			<form:input type="password" path="mem_pass" limitbyte="200"
				placeholder="한글66자, 영문200자 이내" 
				onKeyUp="javascript:fnChkByte(this,'200','byteInfo3')"/>
				<span id="byteInfo3">0</span> / 200bytes
			<form:errors path="mem_pass" />
		</div>
		<div>
			<label>이메일</label>
			<form:input path="mem_email"/>
			<form:errors path="mem_email" />
		</div>

		<div>
			<input type="submit" value="회원가입" />
		</div>
	</form:form>
</body>
</html>