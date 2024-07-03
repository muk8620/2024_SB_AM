package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ReplyService;
import com.example.demo.util.Util;
import com.example.demo.vo.Rq;

@Controller
public class UsrReplyController {

	private ReplyService replyService;
	private Rq rq;

	public UsrReplyController(ReplyService replyService, Rq rq) {
		this.replyService = replyService;
		this.rq = rq;
	}

	@PostMapping("/usr/reply/doWrite")
	@ResponseBody
	public String doWrite(String body, String relTypeCode, int relId) {

		replyService.writeReply(rq.getLoginedMemberId(), relTypeCode, relId, body);

		int id = replyService.getLastInsertId();

		return Util.jsReplace(String.format("%d번 댓글을 작성했습니다", id), String.format("../article/detail?id=%d", relId));
	}
	
//	@GetMapping("/usr/reply/modify")
//	public String modify(Model model, int id) {
//		Article article = articleService.forPrintArticle(id);
//
//		model.addAttribute("article", article);
//
//		return "usr/reply/modify";
//	}
//
//	@PostMapping("/usr/reply/doModify")
//	@ResponseBody
//	public String doModify(int id, String title, String body) {
//
//		articleService.modifyArticle(id, title, body);
//
//		return Util.jsReplace(String.format("%d번 게시물을 수정햇습니다.", id), String.format("detail?id=%d", id));
//	}

	@GetMapping("/usr/reply/doDelete")
	@ResponseBody
	public String doDelete(int replyId, int articleId) {
		replyService.deleteArticle(replyId);
		
		return Util.jsReplace("댓글을 삭제햇습니다.", String.format("../article/detail?id=%d", articleId));
	}
}