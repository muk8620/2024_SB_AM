<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE MODIFY" />

<%@ include file="../../common/head.jsp" %>

	<script>
	  const modifyForm_onSubmit = function(form) {
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
			<form action="doModify" method="post" onsubmit="modifyForm_onSubmit(this); return false;">
				<div class="table-box-type">
					<input type="hidden" name="id" value="${article.id }" />
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
							<td class="border border-indigo-500/75"><input class="text-center" type="text" name="title" placeholder="제목을 입력해주세요." value="${article.title }"/></td>
						</tr>
						<tr>
							<td class="border border-indigo-500/75">내용</td>
							<td class="border border-indigo-500/75"><textarea name="body">${article.body }</textarea></td>
						</tr>
						<tr>
							<td colspan="2">
								<div>
									<button class="btn btn-outline btn-primary">수정</button>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</form>
			<div class="container flex justify-end mx-auto mt-1">
				<button class="btn btn-outline btn-primary ml-2" onclick="history.back();"> 뒤로가기</button>
			</div>
		</div>
	</section>
<%@ include file="../../common/foot.jsp" %>