<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${pageTitle }</title>
<!-- 테일윈드, daisyUI -->
<link href="https://cdn.jsdelivr.net/npm/daisyui@4.12.8/dist/full.min.css" rel="stylesheet" type="text/css" />
<script src="https://cdn.tailwindcss.com"></script>
<!-- 폰트어썸 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
<!-- 제이쿼리 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- 공통 css -->
<link rel="stylesheet" href="/resource/common.css" />
</head>
<body>
	<div class="h-20 flex container mx-auto text-3xl">
		<div>
			<a class="h-full px-3 flex items-center" href="/">
				<span>
					<i class="fa-solid fa-house"></i>
				</span>
			</a>
		</div>
		<div class="grow"></div>
		<ul class="flex">
			<li class="hover:underline"><a class="h-full px-3 flex items-center" href="/"><span>HOME</span></a></li>
			<li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/article/list"><span>LIST</span></a></li>
			<c:if test="${rq.loginedMemberId == 0}">
				<li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/member/login"><span>LOGIN</span></a></li>
			</c:if>
			<c:if test="${rq.loginedMemberId != 0}">
				<li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/member/doLogout"><span>LOGOUT</span></a></li>
			</c:if>
		</ul>
	</div>
	
	<section class="my-3 text-2xl">
		<div class="container mx-auto px-3">
			<div>${pageTitle }&nbsp;PAGE</div>
		</div>
	</section>