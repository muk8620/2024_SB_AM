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
	  
	  const { Editor } = toastui;

	  $(function(){
	    $('.toast-ui-editor').each(function(index, item) {
	      const items = $(item);

	      const editor = new Editor({
	        el: item,
	        height: '600px',
	        initialEditType: 'markdown',
	        previewStyle: 'tab',
	      });

	      items.data('data-toast-editor', editor);
	    });
	  });

	  function writeForm_onSubmit(form){
	    const editorData = $(form).find('.toast-ui-editor').data('data-toast-editor');
	    const markdown = editorData.getMarkdown().trim();
	    
	    let title = form.title.value.trim();
	    if (title.length == 0) {
	      alert('제목을 입력해주세요.');
	      form.title.focus();
	      return;
	    }

	    if(markdown.length == 0){
	      alert('내용을 입력해주세요');
	      editorData.focus();
	      return;
	    }

	    form.body.value = markdown;
	    
	    form.submit();
	  }
	</script>
	
	<section class="mt-8">
		<div class="container mx-auto px-3">
			<form action="doModify" method="post" onsubmit="modifyForm_onSubmit(this); return false;">
				<div class="table-box-type">
					<input type="hidden" name="id" value="${article.id }" />
					<table class="table">
						<tr class="border-t border-slate-400"> 
							<td >번호</td>
							<td>${article.id }</td>
						</tr>
						<tr class="border-t border-slate-400">
							<td>작성일</td>
							<td>${article.updateDate.substring(5,16)}</td>
						</tr>
						<tr class="border-t border-slate-400">
							<td>작성자</td>
							<td>${article.writerName }</td>
						</tr>
						<tr class="border-t border-slate-400">
							<td>제목</td>
							<td><input class="input input-bordered w-full max-w-xs" type="text" name="title" placeholder="제목을 입력해주세요." value="${article.title }"/></td>
						</tr>
						<tr class="border-y border-slate-400">
							<td>내용</td>
							<td><textarea class="textarea textarea-bordered textarea-md w-full max-w-xs text-base" name="body">${article.body }</textarea></td>
						</tr>
					</table>
				</div>
				<div class="container flex mx-auto mt-1">
					<button class="btn btn-outline btn-primary">수정</button>
					<button class="btn btn-outline btn-primary ml-2" type="button" onclick="history.back();"> 뒤로가기</button>
				</div>
			</form>
		</div>
	</section>
<%@ include file="../../common/foot.jsp" %>