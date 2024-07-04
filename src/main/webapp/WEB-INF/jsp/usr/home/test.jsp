<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/head.jsp" %>
	<script>
		$(function(){
			
			const Editor = toastui.Editor;
			
			const editor = new Editor({
			  el: document.querySelector('#editor'),
			  height: '600px',
			  initialEditType: 'markdown',
			  previewStyle: 'vertical'
			});
				
		})
	</script>
	
	<div id="editor"></div>

<%@ include file="../../common/foot.jsp" %>