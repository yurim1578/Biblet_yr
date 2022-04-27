<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>main</title>
</head>
<body>
		<!-- gnb 영역 -->
		<!-- 로그인 / 회원가입 -->
		<!-- 로그아웃 / 마이페이지  -->

		<!-- 비로그인 시 홈 페이지 -->
		<c:if test = "${ commandAuthInfo != null}"> <!-- 비로그인 -->
			<div class="login_button"><a href="loginForm">로그인</a></div>
			<span><a href="joinForm">회원가입</a></span>
		
		</c:if>
		
		<!-- 로그인 시 홈 페이지 -->
		<c:if test = "${ commandAuthInfo == null}"> <!-- 로그인 -->
			<div class="mypage_button"><a href="mypage">마이페이지</a></div>
			<span><a href="logout">로그아웃</a></span>
		</c:if>
		
		<!-- 관리자 로그인 -->
		<!-- 관리자 로그인 시 관리자 페이지로 이동 -->
		<!-- SECURITYCODE != null -->
		<!-- 로그인 -->
		
		<!-- 도서 검색 -->
</body>
</html>