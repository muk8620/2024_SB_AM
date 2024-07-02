<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE DETAIL" />

<%@ include file="../../common/head.jsp" %>
	<script>
		$(document).ready(function(){
			getLikePoint();
			
			$('#likePointBtn').click(async function(){
				let liked = $('#likePointBtn').hasClass('btn-outline') === false;
				try{
					await $.ajax({
						url : '../likePoint/doLikePoint',
						type : 'GET',
						data : {
							relTypeCode : 'article',
							relId : ${article.id },
							liked : liked
						},
					})
					
					let totalCnt = await getLikePoint();
					
					$('#likePointCnt').html(totalCnt.data);
				} catch (error) {
					console.log('Error : ', error);
				}
			})
		})
		
		const getLikePoint = async function(){
			return $.ajax({
				url : '../likePoint/getLikePoint',
				type : 'GET',
				data : {
					relTypeCode : 'article',
					relId : ${article.id }
				},
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$('#likePointBtn').removeClass('btn-outline');
					} else {
						$('#likePointBtn').addClass('btn-outline');
					}
				},
				error : function(xhr, status, error) {
					console.log(error);
				}
			})
		}
	</script>
	<section class="mt-8">
		<div class="container mx-auto px-3">
			<div class="table-box-type">
				<table class="table">
					<tr class="border-t border-slate-400"> 
						<td>번호</td> 
						<td>${article.id }</td>
					</tr>
					<tr class="border-t border-slate-400"> 
						<td>조회수</td> 
						<td>${article.views }</td>
					</tr>
					<tr class="border-t border-slate-400"> 
						<td>추천</td> 
						<td id="likePointCnt">${article.likePoint }</td>
					</tr>
					<tr class="border-t border-slate-400">
						<td>작성일</td>
						<td>${article.updateDate.substring(2 , 16)}</td>
					</tr>
					<tr class="border-t border-slate-400">
						<td>작성자</td>
						<td>${article.writerName }</td>
					</tr>
					<tr class="border-t border-slate-400">
						<td>제목</td>
						<td>${article.title }</td>
					</tr>
					<tr class="border-y border-slate-400">
						<td>내용</td>
						<td>${article.body }</td>
					</tr>
					<c:if test="${rq.loginedMemberId != 0}">
						<tr class="border-y border-slate-400">
							<td colspan="2">
								<div class="flex justify-center">
									<button class="btn btn-outline btn-primary" id="likePointBtn">추천</button>
								</div>
							</td>
						</tr>
					</c:if>
				</table>
			</div>
			
			<label class="form-control">
			  <textarea class="textarea textarea-bordered h-24"></textarea>
			</label>
			
			<div>
				<c:forEach var="reply" items="${replies }">
					<label class="form-control">
					  <div class="label">
					    <span class="label-text">${reply.writerName }</span>
					    <span class="label-text-alt">${reply.regDate.substring(5 , 16) }</span>
					  </div>
					  <textarea class="textarea textarea-bordered h-24">${reply.body }</textarea>
					</label>
				</c:forEach>
			</div>
			
			<div class="flex mx-auto mt-1">
				<c:if test="${rq.loginedMemberId == article.memberId}">
					<a class="btn btn-outline btn-primary mr-2" href="modify?id=${article.id }">수정</a>
					<a class="btn btn-outline btn-primary mr-2" href="doDelete?id=${article.id }" onclick="return confirm('삭제하시겠습니까?');">삭제</a>
				</c:if>
				<a class="btn btn-outline btn-primary mr-2" href="list?boardId=${article.boardId }">목록</a>
			</div>
		</div>
	</section>
<%@ include file="../../common/foot.jsp" %>