<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="${boardName } 게시판" />

<%@ include file="../../common/head.jsp" %>
	<section class="mt-8">
		<div class="container mx-auto px-3">
			<div class="table-box-type">
				<span>총 : ${ articlesCnt} 개</span>
				<table class="table">
					<thead class="border-y-2 border-sky-600">
						<tr> 
							<th></th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="article" items="${articles }">
							<tr class="hover:bg-zinc-400 cursor-pointer" onClick="location.href='detail?id=${article.id }'">
								<td>${article.id }</td>
								<td>${article.title }</td>
								<td>${article.writerName }</td>
								<td>${article.updateDate.substring(5 , 16)}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<c:if test="${rq.loginedMemberId != 0}">
					<div class="flex justify-end">
						<a class="btn btn-outline btn-primary" href="/usr/article/write"><span>글쓰기</span></a>
					</div>
				</c:if>
			</div>
		</div>
	</section>
<%@ include file="../../common/foot.jsp" %>