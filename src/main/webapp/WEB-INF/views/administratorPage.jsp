<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style type="text/css">
dl {
	position: relative;
	width: 1000px;
	height: 100px;
}

dt {
	height: 30px;
	float: left;
	width: 250px;
	z-index: 9;
	position: relative;
	text-align: center;
	background: #ddd;
}

dd {
	position: absolute;
	padding-top: 30px;
	background-color: #f3f3f3;
	width: 1000px;
	height: 100px;
	margin: 0;
}

dd.hidden {
	display: none;
}
</style>
<script src="http://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>

<title>관리자 페이지</title>
</head>
<body>

	<dl>
		<dt>가입 회원 현황</dt>
		<dd>
			<form name="member" action="<c:url value='/search'/>" method="post">
				가입 회원 검색 &nbsp:&nbsp <select name="option">
					<option value="mem_num">회원 번호</option>
					<option value="mem_name">회원 이름</option>
					<option value="mem_email">회원 이메일</option>
				</select> <input type="text" name="keyword" placeholder="회원 번호, 이름, 이메일로 검색"
					style="width: 30%;"> <input type="submit" value="검색">
			</form>

			가입 회원 수 : ${memcount}
			<table border="1">
				<tr>
					<th>회원 번호</th>
					<th>이름</th>
					<th>아이디</th>
					<th>비밀번호</th>
					<th>가입일</th>
					<th>이메일</th>
					<th>이메일 인증 완료 여부</th>
					<th>회원 강제 탈퇴 버튼</th>
				</tr>
				<c:forEach var="member" items="${memberList}">
					<tr>
						<td>${member.mem_num }</td>
						<td>${member.mem_name }</td>
						<td>${member.mem_id }</td>
						<td id="hide_pass">${member.mem_pass }</td>
						<!-- 로그인과 합쳤을 때 session정보가지고 관리자 비번확인해서 값 보이도록 하기(https://esckey.tistory.com/66) -->
						<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
								value="${member.mem_regdate }" /></td>
						<td>${member.mem_email }</td>
						<td><c:if test="${member.authstatus==1 }">완료</c:if> <c:if
								test="${member.authstatus==0 }">미완료</c:if></td>
						<td><a
							href="<c:url value="/adminPage/deleteMember/${member.mem_num }"/>"><button>강제
									탈퇴</button></a></td>
					</tr>
				</c:forEach>

				<c:if test="${! empty searchList }">
					<c:forEach var="member" items="${searchList}">
						<tr>
							<td>${member.mem_num }</td>
							<td>${member.mem_name }</td>
							<td>${member.mem_id }</td>
							<td id="hide_pass">${member.mem_pass }</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
									value="${member.mem_regdate }" /></td>
							<td>${member.mem_email }</td>
							<td><c:if test="${member.authstatus==1 }">완료</c:if> <c:if
									test="${member.authstatus==0 }">미완료</c:if></td>
							<td><a
								href="<c:url value="/adminPage/deleteMember/${member.mem_num }"/>"><button>강제
										탈퇴</button></a></td>
						</tr>
					</c:forEach>
				</c:if>

			</table>
		</dd>

		<dt>총 평가</dt>
		<dd class="hidden">
			총 평가 수 :
			<div id="starcount" name="starcount" value="${starcount}">${starcount}</div>
			<table border="1">
				<tr>
					<th>회원 아이디</th>
					<th>책 제목</th>
					<th>작가</th>
					<th>출판사</th>
					<th>별점</th>

				</tr>
				<c:forEach var="appr" items="${apprList}">

					<c:if test="${0 ne appr.star}">
						<input type="hidden" name="isbn" id="isbn" value="${appr.isbn}" />
						<tr>
							<td>${appr.mem_id }님의평가입니다.</td>
							<td><div id="book_name"></div></td>
							<td><div id="author"></div></td>
							<td><div id="publisher"></div></td>
							<td><c:if test="${appr.star==1 }">★☆☆☆☆</c:if> <c:if
									test="${appr.star==2 }">★★☆☆☆</c:if> <c:if
									test="${appr.star==3 }">★★★☆☆</c:if> <c:if
									test="${appr.star==4 }">★★★★☆</c:if> <c:if
									test="${appr.star==5 }">★★★★★</c:if></td>
						</tr>
					</c:if>

				</c:forEach>

				<c:if test="${! empty searchApprList }">

					<c:forEach var="appr" items="${searchApprList}">
						<c:if test="${0 ne appr.star}">
							<input type="hidden" name="isbn" id="isbn" value="${appr.isbn}" />
							<tr>
								<td>${appr.mem_id }님의평가입니다.</td>
								<td><div id="book_name"></div></td>
								<td><div id="author"></div></td>
								<td><div id="publisher"></div></td>
								<td><c:if test="${appr.star==1 }">★☆☆☆☆</c:if> <c:if
										test="${appr.star==2 }">★★☆☆☆</c:if> <c:if
										test="${appr.star==3 }">★★★☆☆</c:if> <c:if
										test="${appr.star==4 }">★★★★☆</c:if> <c:if
										test="${appr.star==5 }">★★★★★</c:if></td>
							</tr>
						</c:if>
					</c:forEach>

				</c:if>
			</table>
		</dd>

		<dt>총 코멘트</dt>
		<dd class="hidden">
			<form name="appr" action="<c:url value='/search'/>" method="post">
				코멘트 검색 &nbsp:&nbsp <select name="CoOption">
					<option value="mem_id">회원 아이디</option>
					<option value="comment">코멘트</option>
				</select> <input type="text" name="CoKeyword"
					placeholder="회원 아이디, 코멘트 내용으로 검색" style="width: 30%;"> <input
					type="submit" value="검색">
			</form>
			총 코멘트 수 : ${commentcount}
			<table border="1">
				<tr>
					<th>회원 아이디</th>
					<th>책 제목</th>
					<th>작가</th>
					<th>출판사</th>
					<th>코멘트</th>
				</tr>

				<c:forEach var="comm" items="${commentList}">
					<c:if test="${! empty comm.book_comment}">
						<input type="hidden" name="isbn_c" id="isbn_c"
							value="${comm.isbn}" />
						<tr>
							<td>${comm.mem_id }님의코멘트입니다.</td>
							<td><div id="book_name_c"></div></td>
							<td><div id="author_c"></div></td>
							<td><div id="publisher_c"></div></td>
							<td>${comm.book_comment}</td>
						</tr>
					</c:if>
				</c:forEach>

				<c:if test="${! empty searchApprList }">
					<c:forEach var="comm" items="${searchApprList}">
						<c:if test="${! empty comm.book_comment}">
							<input type="hidden" name="isbn_c" id="isbn_c"
								value="${comm.isbn}" />
							<tr>
								<td>${comm.mem_id }님의코멘트입니다.</td>
								<td><div id="book_name_c"></div></td>
								<td><div id="author_c"></div></td>
								<td><div id="publisher_c"></div></td>
								<td>${comm.book_comment}</td>
							</tr>
						</c:if>
					</c:forEach>
				</c:if>
			</table>
		</dd>

		<dt>관리자 정보</dt>
		<dd class="hidden">
			<form name="admin" action="<c:url value='/search'/>" method="post">
				가입 관리자 검색 &nbsp:&nbsp <select name="admOption">
					<option value="adm_num">관리자 번호</option>
					<option value="adm_name">관리자 이름</option>
					<option value="adm_email">관리자 이메일</option>
				</select> <input type="text" name="admKeyword"
					placeholder="관리자 번호, 이름, 이메일로 검색" style="width: 30%;"> <input
					type="submit" value="검색">
			</form>

			가입 관리자 수 : ${admcount} <br> 관리자 코드 : ABC
			<table border="1">
				<tr>
					<th>관리자 번호</th>
					<th>이름</th>
					<th>아이디</th>
					<th>가입일</th>
					<th>이메일</th>
					<th>이메일 인증 완료 여부</th>
				</tr>
				<c:forEach var="admin" items="${adminList}">
					<tr>
						<td>${admin.adm_num }</td>
						<td>${admin.adm_name }</td>
						<td>${admin.adm_id }</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
								value="${admin.adm_regdate }" /></td>
						<td>${admin.adm_email }</td>
						<td><c:if test="${admin.adm_authstatus==1 }">완료</c:if> <c:if
								test="${admin.adm_authstatus==0 }">미완료</c:if></td>
					</tr>
				</c:forEach>

				<c:if test="${! empty searchAdmList }">
					<c:forEach var="admin" items="${searchAdmList}">
						<tr>
							<td>${admin.adm_num }</td>
							<td>${admin.adm_name }</td>
							<td>${admin.adm_id }</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
									value="${admin.adm_regdate }" /></td>
							<td>${admin.adm_email }</td>
							<td><c:if test="${admin.adm_authstatus==1 }">완료</c:if> <c:if
									test="${admin.adm_authstatus==0 }">미완료</c:if></td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
			남은 할일 : 페이징 처리
		</dd>
	</dl>
	<script>
		var $menuEle = $('dt'); // 탭메뉴를 변수에 지정
		$menuEle.click(function() { // 탭메뉴 클릭 이벤트
			$('dd').addClass('hidden');
			$(this).next().removeClass('hidden');
		})
	</script>
	<script>
		$(document).ready(function() {

			var pageNum = 1;

			$.ajax({ //카카오 검색요청 / [요청]
				method : "GET",
				url : "https://dapi.kakao.com/v3/search/book",
				data : {
					query : $("#isbn").val(),
					page : pageNum
				},
				headers : {
					Authorization : "KakaoAK 6f9ab74953bbcacc4423564a74af264e"
				}

			}).done(function(msg) { //검색 결과 담기 / [응답]
				console.log(msg);
				$("#book_name").append(msg.documents[0].title); //제목
				$("#author").append(msg.documents[0].authors); //저자	
				$("#publisher").append(msg.documents[0].publisher); //출판사

			});

		})
		$(document).ready(function() {
			var pageNum = 1;
			$.ajax({ //카카오 검색요청 / [요청]
				method : "GET",
				url : "https://dapi.kakao.com/v3/search/book",
				data : {
					query : $("#isbn_c").val(),
					page : pageNum
				},
				headers : {
					Authorization : "KakaoAK 6f9ab74953bbcacc4423564a74af264e"
				}
			})

			.done(function(msg) { //검색 결과 담기 / [응답]
				console.log(msg);
				$("#book_name_c").append(msg.documents[0].title); //제목
				$("#author_c").append(msg.documents[0].authors); //저자	
				$("#publisher_c").append(msg.documents[0].publisher); //출판사
			});

		})
		<!-- jquery로 ajax요청해서 받아온 값으로 테이블에 값넣기-->
	</script>
</body>
</html>