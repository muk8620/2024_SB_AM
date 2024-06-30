<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE MODIFY" />

<%@ include file="../../common/head.jsp" %>

	<script>
	  const writeForm_onSubmit = function(form) {
	    let title = form.title.value.trim();
	    if (title.length == 0) {
	      alert('제목을 입력해주세요.');
	      form.title.focus();
	      return;
	    }
	    
	    let body = form.body.value.trim();
	    if (body.length == 0) {
	      alert('내용을 입력해주세요.');
	      form.body.focus();
	      return;
	    }
	    
	    form.submit();
	  }
	</script>
	
	<section class="mt-8">
		<div class="container mx-auto px-3">
			<form action="doWrite" method="post" onsubmit="writeForm_onSubmit(this); return false;">
				<div class="table-box-type">
					<table class="table">
						<tr class="border-b border-slate-400 mt">
							<th>게시판</th>
							<td>
								<div class="flex">
									<div>
										<label class="flex items-center">
											<input class="radio radio-sm" type="radio" name="boardId" value="1" checked />
											&nbsp;&nbsp;공지사항
										</label>
									</div>
									<div class="w-20"></div>
									<div>
										<label class="flex items-center">
											<input class="radio radio-sm" type="radio" name="boardId" value="2" />
											&nbsp;&nbsp;자유
										</label>
									</div>
								</div>
							</td>
						</tr>
						<tr class="border-b border-slate-400">
							<td>제목</td>
							<td><input class="input input-bordered w-full max-w-xs" type="text" name="title" placeholder="제목을 입력해주세요." value="${article.title }"/></td>
						</tr>
						<tr class="border-b border-slate-400">
							<td>내용</td>
							<td><textarea class="textarea textarea-bordered textarea-md w-full max-w-xs text-base" name="body" placeholder="내용을 입력해주세요.">${article.body }</textarea></td>
						</tr>
					</table>
				</div>
				<div class="flex mt-1">
					<button class="btn btn-outline btn-primary mr-2">글쓰기</button>
					<button class="btn btn-outline btn-primary mr-2" type="button" onclick="history.back();"> 뒤로가기</button>
				</div>
			</form>
		</div>
	</section>
<%@ include file="../../common/foot.jsp" %>