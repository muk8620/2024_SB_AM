<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE DETAIL" />

<%@ include file="../../common/head.jsp" %>
	<script>
	  const loginForm_onSubmit = function(form) {
	    let loginId = form.loginId.value.trim();
	    if (loginId.length == 0) {
	      alert('아이디를 입력해주세요.');
	      form.loginId.focus();
	      return;
	    }
	    
	    let loginPw = form.loginPw.value.trim();
	    if (loginPw.length == 0) {
	      alert('비밀번호를 입력해주세요.');
	      form.loginPw.value = "";
	      form.loginPw.focus();
	      return;
	    }
	    
	    form.submit();
	  }
	</script>
	
	<section class="mt-8">
		<div class="container mx-auto px-3">
			<form action="doLogin" method="post" onsubmit="loginForm_onSubmit(this); return false;">
				<div class="table-box-type">
					<table class="border-4 border-indigo-500/100">
						<tr>
							<td class="border border-indigo-500/75">아이디</td>
							<td class="border border-indigo-500/75"><input type="text" name="loginId" placeholder="아이디를 입력해주세요."/></td>
						</tr>
						<tr>
							<td class="border border-indigo-500/75">비밀번호</td>
							<td class="border border-indigo-500/75"><input type="password" name="loginPw" placeholder="비밀번호를 입력해주세요."/></td>
						</tr>
						<tr>
							<td colspan="2"><div class="btns"><button class="bg-indigo-500 rounded-md p-4">로그인</button></div></td>
						</tr>
					</table>
				</div>
			</form>
			<div class="btns container flex justify-end mx-auto mt-1">
				<button class="bg-indigo-500 rounded-md p-4 ml-2" onclick="history.back();"> 뒤로가기</button>
			</div>
		</div>
	</section>
<%@ include file="../../common/foot.jsp" %>