package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsrArticleController {
	
	private ArticleService articleService;
	private Rq rq;
	
	public UsrArticleController(ArticleService articleService, Rq rq) {
		this.articleService = articleService;
		this.rq = rq;
	}
	
	@GetMapping("/usr/article/write")
	public String write() {
		
		return "usr/article/write";
	}
	
	@PostMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(int boardId, String title, String body) {
		
		articleService.writeArticle(rq.getLoginedMemberId(), boardId, title, body);
		
		int id = articleService.getLastInsertId();
		
		return Util.jsReplace(String.format("%d번 게시물을 작성했습니다.", id), String.format("detail?id=%d", id));
	}
	
	@GetMapping("/usr/article/list")
	public String list(Model model, int boardId) {
		String boardName = articleService.getBoardNameById(boardId);
		
		List<Article> articles = articleService.getArticles(boardId);
		
		int articlesCnt = articleService.getArticlesCnt(boardId);
		
		model.addAttribute("boardName", boardName);
		model.addAttribute("articlesCnt", articlesCnt);
		model.addAttribute("articles", articles);
		
		return "usr/article/list";
	}
	
	@GetMapping("/usr/article/detail")
	public String detail(Model model, int id) {
		
		Article article = articleService.forPrintArticle(id);
		
		model.addAttribute("article", article);
		
		return "usr/article/detail";
	}
	
	@GetMapping("/usr/article/modify")
	public String modify(Model model, int id) {
		Article article = articleService.forPrintArticle(id);
		
		model.addAttribute("article", article);
		
		return "usr/article/modify";
	}
	
	@PostMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
		
		articleService.modifyArticle(id, title, body);
		
		return Util.jsReplace(String.format("%d번 게시물을 수정햇습니다.", id), String.format("detail?id=%d", id));
	}
	
	@GetMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		articleService.deleteArticle(id);
		
		return Util.jsReplace(String.format("%d번 게시물을 삭제햇습니다.", id), "list");
	}
}
