<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MEMBER LOGIN" />

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
				<div>
					<label class="input input-primary input-bordered flex w-3/6 items-center gap-2 mb-2">
					  <svg
					    xmlns="http://www.w3.org/2000/svg"
					    viewBox="0 0 16 16"
					    fill="currentColor"
					    class="h-4 w-4 opacity-70">
					    <path
					      d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6ZM12.735 14c.618 0 1.093-.561.872-1.139a6.002 6.002 0 0 0-11.215 0c-.22.578.254 1.139.872 1.139h9.47Z" />
					  </svg>
					  <input type="text" class="grow" name="loginId" placeholder="아이디를 입력해주세요." />
					</label>
					<label class="input input-primary input-bordered flex w-3/6 items-center gap-2 mb-2">
					  <svg
					    xmlns="http://www.w3.org/2000/svg"
					    viewBox="0 0 16 16"
					    fill="currentColor"
					    class="h-4 w-4 opacity-70">
					    <path
					      fill-rule="evenodd"
					      d="M14 6a4 4 0 0 1-4.899 3.899l-1.955 1.955a.5.5 0 0 1-.353.146H5v1.5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-2.293a.5.5 0 0 1 .146-.353l3.955-3.955A4 4 0 1 1 14 6Zm-4-2a.75.75 0 0 0 0 1.5.5.5 0 0 1 .5.5.75.75 0 0 0 1.5 0 2 2 0 0 0-2-2Z"
					      clip-rule="evenodd" />
					  </svg>
					  <input type="password" name="loginPw" class="grow" placeholder="비밀번호를 입력해주세요."/>
					</label>
					<div class="container flex mx-auto">
						<button class="btn btn-outline btn-primary">로그인</button>
						<button type="button" class="btn btn-outline btn-primary ml-2" onclick="history.back();"> 뒤로가기</button>
					</div>
				</div>
			</form>
		</div>
	</section>
<%@ include file="../../common/foot.jsp" %>