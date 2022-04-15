<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.tab_wrap {
	max-width: 800px;
	margin: 50px auto 0;
	padding: 0 15px;
}

.tab_wrap .tit_list {
	position: relative;
	font-size: 0;
}

.tab_wrap .tit_list:before {
	content: '';
	position: absolute;
	bottom: 0;
	left: 0;
	width: 100%;
	height: 1px;
	background: #ddd;
	z-index: 1;
}

.tab_wrap .tit_list>li {
	display: inline-block;
	vertical-align: top;
	margin-right: 3px;
}

.tab_wrap .tit_list>li a {
	display: inline-block;
	padding: 10px 15px;
	border: 1px solid #fff;
	border-radius: 4px 4px 0 0;
	font-size: 14px;
	color: #000;
	text-decoration: none;
}

.tab_wrap .tit_list>li a:hover {
	background: #efefef;
	border-color: #efefef;
}

.tab_wrap .tit_list>li.active a {
	position: relative;
	border-color: #ddd;
	border-bottom: 1px solid #fff;
	background: #fff;
	color: #337ab7;
	z-index: 2;
}

.tab_wrap .tab_con {
	border: 1px solid #ddd;
	border-top: none;
}

.tab_wrap .tab_con .tab_list {
	display: none;
	height: 200px;
	padding: 15px;
}
</style>
<script src="http://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>임시 관리자 페이지</title>
</head>
<body>
	<div class="tab_wrap">
		<!-- 탭메뉴 제목 -->
		<ul class="tit_list">
			<!-- 디폴트 선택 li에 active 클래스 추가 -->
			<li class="active"><a href="#con01">가입 회원 현황</a></li>
			<li><a href="#con02">총 평가</a></li>
			<li><a href="#con03">총 코멘트</a></li>
			<li><a href="#con04">관리자 정보</a></li>
		</ul>
		<!-- 탭메뉴 컨텐츠 -->
		<div class="tab_con">
			<div id="con01" class="tab_list">
				<form name="member" action="<c:url value='/search'/>" method="get">
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
								<td><c:if test="${member.authstatus==1 }">이메일 인증 완료 회원</c:if>
									<c:if test="${member.authstatus==0 }">이메일 인증 미완료 회원</c:if></td>
								<td><a
									href="<c:url value="/adminPage/deleteMember/${member.mem_num }"/>"><button>강제
											탈퇴</button></a></td>
							</tr>
						</c:forEach>
					</c:if>

				</table>
				남은 할일 : 페이징 처리/비밀번호 안보이게 처리
			</div>

			<div id="con02" class="tab_list">
				총 평가 수 :
				<table border="1">
					<tr>
						<th>회원 아이디</th>
						<th>별점</th>
						<th>책 제목</th>
						<th>출판사</th>
					</tr>
					<tr>
						<td>00님의 평가, 별점, 책제목, 출판사</td>
					</tr>
				</table>
				남은 할일 : 페이징 처리
			</div>

			<div id="con03" class="tab_list">
				총 코멘트 수 :
				<table border="1">
					<tr>
						<td>00님의 코멘트, 책제목, 출판사, 코멘트, 삭제</td>
					</tr>
				</table>
				남은 할일 : 페이징 처리
			</div>

			<div id="con04" class="tab_list">
				<form name="admin" action="<c:url value='/searchAdm'/>" method="post">
					가입 관리자 검색 &nbsp:&nbsp <select name="admOption">
						<option value="adm_num">관리자 번호</option>
						<option value="adm_name">관리자 이름</option>
						<option value="adm_email">관리자 이메일</option>
					</select> <input type="text" name="admKeyword"
						placeholder="관리자 번호, 이름, 이메일로 검색" style="width: 30%;"> <input
						type="submit" value="검색">
				</form>

				가입 회원 수 : ${admcount} <br> 관리자 코드 : ABC
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
			</div>
		</div>
	</div>

	<script>
		$(document).ready(function() {
			tab();
		})

		function tab() {
			//탭메뉴 클릭할 때 실행
			$('.tab_wrap .tit_list > li a').on(
					'click',
					function(e) {
						e.preventDefault();

						//초기화
						$('.tab_wrap .tit_list > li').removeClass('active');
						$('.tab_wrap .tab_list').hide();

						//실행
						$(this).parent().addClass('active');
						var activeTab = $(this).attr('href');
						$(activeTab).show();

						//파라미터 확인
						urlParam = location.search.substr(location.search
								.indexOf("?") + 1);
						if (urlParam != '') {
							urlParam = '?' + urlParam;
						}

						//파라미터 변경
						getNewUrl('tabName', urlParam); //(변경·추가할 파라미터 이름, 현재 파라미터)
						function getNewUrl(paramName, oldUrl) {
							var newUrl;
							var urlChk = new RegExp('[?&]' + paramName
									+ '\\s*=');
							var urlChk2 = new RegExp('(?:([?&])' + paramName
									+ '\\s*=[^?&]*)')

							if (urlChk.test(oldUrl)) { //해당 파라미터가 있을 때
								newUrl = oldUrl
										.replace(urlChk2, "$1" + paramName
												+ "=" + activeTab.substr(1));
							} else if (/\?/.test(oldUrl)) { //해당 파라미터가 없고 다른 파라미터가 있을 때
								newUrl = oldUrl + "&" + paramName + "="
										+ activeTab.substr(1);
							} else { //파라미터가 없을 때
								newUrl = oldUrl + "?" + paramName + "="
										+ activeTab.substr(1);
							}

							history.pushState(null, null, newUrl);
						}
					});

			//파라미터 값 검사
			function getParameter(name) {
				name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
				var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"), results = regex
						.exec(location.search);
				return results === null ? "" : decodeURIComponent(results[1]
						.replace(/\+/g, " "));
			}

			var getParam = getParameter('tabName'); //선택한 탭 파라미터
			var loadChk = getParameter('loadChk'); //첫 로드 여부 체크

			if (getParam != '') { //파라미터 값이 있으면 파라미터 값 기준으로 탭메뉴 선택
				$('.tab_wrap .tit_list > li a[href="#' + getParam + '"]')
						.parent().addClass('active');
				$('.tab_wrap .tit_list > li a[href="#' + getParam + '"]')
						.trigger('click');

				if (loadChk == 'on') { //처음 로드되었으면 스크롤 이동
					//탭 위치로 이동
					var tabTop = $('.tab_wrap').offset().top;
					$(window).scrollTop(tabTop - 100);

					//파라미터 확인
					var urlParam = location.search.substr(location.search
							.indexOf("?") + 1);
					if (urlParam != '') {
						urlParam = '?' + urlParam;
					}

					//loadChk 파라미터 값 변경
					loadChange('loadChk', urlParam);
					function loadChange(paramName, oldUrl) {
						var newUrl;
						var urlChk = new RegExp('[?&]' + paramName + '\\s*=');
						var urlChk2 = new RegExp('(?:([?&])' + paramName
								+ '\\s*=[^?&]*)')
						newUrl = oldUrl.replace(urlChk2, "$1" + paramName
								+ "=off");
						history.pushState(null, null, newUrl);
					}
				}
			} else { //파라미터 값이 없으면 active 클래스 기준으로 탭메뉴 선택
				var activeChk = 0;
				$('.tab_wrap .tit_list > li').each(function(i) {
					if ($(this).hasClass('active')) {
						$(this).addClass('active');
						$(this).find('a').trigger('click');
						activeChk++
					}
				});

				//active 지정 안했을 시 첫 탭메뉴 선택
				if (activeChk == 0) {
					$('.tab_wrap .tit_list > li:first-child a')
							.trigger('click');
				}
			}

			//뒤로가기 탭메뉴 복구
			window.onpopstate = function(event) {
				//초기화
				$('.tab_wrap .tit_list > li').removeClass('active');
				$('.tab_wrap .tab_list').hide();
				var getParam2 = getParameter('tabName'); //선택한 탭 파라미터

				//탭메뉴 열기
				if (getParam2 != '') {
					$('.tab_wrap .tit_list > li a[href="#' + getParam2 + '"]')
							.parent().addClass('active');
					$('#' + getParam2).show()
				} else {
					$('.tab_wrap .tit_list > li:first-child')
							.addClass('active');
					$('.tab_wrap .tab_list:first-of-type').show();
				}
			};
		}
	</script>
</body>
</html>