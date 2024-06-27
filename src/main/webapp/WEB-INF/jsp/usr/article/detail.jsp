<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE DETAIL" />

<%@ include file="../../common/head.jsp" %>
	<section class="mt-8">
		<div class="container mx-auto px-3">
			<div class="table-box-type">
				<table class="">
					<tr> 
						<td class="">번호</td>
						<td class="">${article.id }</td>
					</tr>
					<tr>
						<td class="">작성일</td>
						<td class="">${article.updateDate.substring(5,16)}</td>
					</tr>
					<tr>
						<td class="">작성자</td>
						<td class="">${article.writerName }</td>
					</tr>
					<tr>
						<td class="">제목</td>
						<td class="">${article.title }</td>
					</tr>
					<tr>
						<td class="">내용</td>
						<td class="">${article.body }</td>
					</tr>
				</table>
			</div>
			<div class="container flex justify-end mx-auto mt-1">
				<button class="btn btn-outline btn-primary ml-2" onclick="history.back();"> 뒤로가기</button>
				<c:if test="${rq.loginedMemberId == article.memberId}">
					<a class="btn btn-outline btn-primary ml-2" href="modify?id=${article.id }">수정</a>
					<a class="btn btn-outline btn-primary ml-2" href="doDelete?id=${article.id }" onclick="return confirm('삭제하시겠습니까?');">삭제</a>
				</c:if>
			</div>
		</div>
	</section>
<%@ include file="../../common/foot.jsp" %>