<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="${boardName } 게시판" />
<%@ include file="../../common/head.jsp" %>
	<section class="my-8">
		<div class="container mx-auto px-3">
			<div class="table-box-type">
				<div class="mb-2 flex justify-between items-end">
					<div>
						<span>총 : ${ articlesCnt} 개</span>
					</div>
					<form>
						<input type="hidden" name="boardId" value="${param.boardId }"/>
						<input type="hidden" name="page" value="${page }"/>
						<div>
							<select data-value="${param.searchKeywordType }" class="select select-bordered select-sm" name="searchKeywordType">
								<option value="all">제목+내용</option>
								<option value="title">제목</option>
								<option value="body">내용</option>
							</select>
		<!-- 					색깔 바꿔야됨 -->
							<input class="input input-bordered input-sm" type="text" name="searchKeyword" value="${param.searchKeyword }" placeholder="검색어를 입력해주세요."/>	
							<button class="hidden">검색</button>
						</div>				
					</form>
				</div>
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
							<tr class="hover:bg-zinc-400 cursor-pointer border-b border-slate-400" onClick="location.href='detail?id=${article.id }'">
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
				
				<c:set var="baseUri" value="boardId=${param.boardId }&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}"/>
				
				<div class="mt-3 flex justify-center">
					<div class="join">
						<a class="join-item btn btn-sm ${from == 1 ? 'btn-disabled' : '' }" href="?page=1&${baseUri }"><i class="fa-solid fa-angles-left"></i></a>
						<a class="join-item btn btn-sm ${from == 1 ? 'btn-disabled' : '' }" href="?page=${from - 1 }&${baseUri }"><i class="fa-solid fa-caret-left"></i></a>
					
						<c:forEach begin="${from }" end="${end }" var="i">
							<a class="join-item btn btn-sm ${cPage == i ? 'btn-active' : '' }" href="?page=${i }&${baseUri }">${i }</a>
						</c:forEach>
						
						<a class="join-item btn btn-sm ${end == totalPageCnt ? 'btn-disabled' : '' }" href="?page=${end + 1 }&${baseUri }"><i class="fa-solid fa-caret-right"></i></a>
						<a class="join-item btn btn-sm ${end == totalPageCnt ? 'btn-disabled' : '' }" href="?page=${totalPageCnt }&${baseUri }"><i class="fa-solid fa-angles-right"></i></a>
					</div>
				</div>
				
			</div>
		</div>
	</section>
<%@ include file="../../common/foot.jsp" %>