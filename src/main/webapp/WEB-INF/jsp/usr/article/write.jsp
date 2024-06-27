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
					<table>
						<tr>
							<td>제목</td>
							<td><input class="text-center" type="text" name="title" placeholder="제목을 입력해주세요."/></td>
						</tr>
						<tr>
							<td>내용</td>
							<td><textarea name="body" placeholder="내용을 입력해주세요."></textarea></td>
						</tr>
						<tr>
							<td colspan="2">
								<div>
									<button class="btn btn-outline btn-primary">글쓰기</button>
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