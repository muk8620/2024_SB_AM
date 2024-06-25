<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE LIST" />

<%@ include file="../../common/head.jsp" %>
	<section class="mt-8">
		<div class="container mx-auto px-3">
			<div class="table-box-type">
				<table class="border-4 border-indigo-500/100">
					<thead>
						<tr> 
							<th class="border border-indigo-500/75">번호</th>
							<th class="border border-indigo-500/75">제목</th>
							<th class="border border-indigo-500/75">작성자</th>
							<th class="border border-indigo-500/75">작성일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="article" items="${articles }">
							<tr>
								<td class="border border-indigo-500/75">${article.id }</td>
								<td class="border border-indigo-500/75 hover:underline"><a href="detail?id=${article.id }">${article.title }</a></td>
								<td class="border border-indigo-500/75">${article.writerName }</td>
								<td class="border border-indigo-500/75">${article.updateDate.substring(5 , 16)}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</section>
<%@ include file="../../common/foot.jsp" %>