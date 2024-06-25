<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE DETAIL" />

<%@ include file="../../common/head.jsp" %>
	<section class="mt-8">
		<div class="container mx-auto px-3">
			<div class="table-box-type">
				<table class="border-4 border-indigo-500/100">
					<tr> 
						<td class="border border-indigo-500/75">번호</td>
						<td class="border border-indigo-500/75">${article.id }</td>
					</tr>
					<tr>
						<td class="border border-indigo-500/75">작성일</td>
						<td class="border border-indigo-500/75">${article.updateDate.substring(5,16)}</td>
					</tr>
					<tr>
						<td class="border border-indigo-500/75">작성자</td>
						<td class="border border-indigo-500/75">${article.writerName }</td>
					</tr>
					<tr>
						<td class="border border-indigo-500/75">제목</td>
						<td class="border border-indigo-500/75">${article.title }</td>
					</tr>
					<tr>
						<td class="border border-indigo-500/75">내용</td>
						<td class="border border-indigo-500/75">${article.body }</td>
					</tr>
				</table>
			</div>
			<div class="btns container flex justify-end mx-auto mt-1">
				<button class="bg-indigo-500 rounded-md p-4 ml-2" onclick="history.back();"> 뒤로가기</button>
				<c:if test="${loginedMemberId == article.memberId}">
					<a class="bg-indigo-500 rounded-md p-4 ml-2" href="modify?id=${article.id }">수정</a>
					<a class="bg-indigo-500 rounded-md p-4 ml-2" href="doDelete?id=${article.id }" onclick="return confirm('삭제하시겠습니까?');">삭제</a>
				</c:if>
			</div>
		</div>
	</section>
<%@ include file="../../common/foot.jsp" %>