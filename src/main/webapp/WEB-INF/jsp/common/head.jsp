<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${pageTitle }</title>
<!-- 테일윈드 -->
<script src="https://cdn.tailwindcss.com"></script>
<!-- 폰트어썸 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"></script>
<!-- 제이쿼리 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- 공통 css -->
<link rel="stylesheet" href="/resource/common.css" />
</head>
<body>
	<div class="flex flex-row justify-between px-32 py-6 text-3xl">
		<div><a href="/"><span>로고</span></a></div>
		
		<ul class="flex flex-row">
			<li class="hover:underline pl-4"><a href="/"><span>HOME</span></a></li>
			<li class="hover:underline pl-4"><a href="/usr/article/list"><span>LIST</span></a></li>
		</ul>
	</div>
	
	<section class="px-32 pb-6">
		<div class="text-2xl">
			<div>${pageTitle }&nbsp;PAGE</div>
		</div>
	</section>