package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.vo.Article;

@Controller
public class UsrArticleController {
	
	private ArticleService articleService;
	
	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@GetMapping("/usr/article/doWrite")
	@ResponseBody
	public Article doWrite(@RequestParam String title, @RequestParam String body) {
		articleService.doWrite(title, body);
		
		int id = articleService.getLastInsertId();
		
		return articleService.getArticleById(id);
	}
	
	@GetMapping("/usr/article/showList")
	@ResponseBody
	public List<Article> showList() {
		return articleService.showList();
	}
	
	@GetMapping("/usr/article/showDetail")
	@ResponseBody
	public Object showDetail(@RequestParam int id) {
		Article foundArticle = articleService.getArticleById(id);
		
		if (foundArticle == null) {
			return id + "번 게시물은 존재하지 않습니다.";
		}
		
		return foundArticle;
	}
	
	@GetMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(@RequestParam int id, @RequestParam String title, @RequestParam String body) {
		Article foundArticle = articleService.getArticleById(id);
		
		if (foundArticle == null) {
			return id + "번 게시물은 존재하지 않습니다.";
		}
		
		articleService.doModify(id, title, body);
		
		return id + "번 게시물이 수정되었습니다.";
	}
	
	@GetMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(@RequestParam int id) {
		Article foundArticle = articleService.getArticleById(id);
		
		if (foundArticle == null) {
			return id + "번 게시물은 존재하지 않습니다.";
		}
		
		articleService.doDelete(id);
		return id + "번 게시물이 삭제되었습니다.";
	}
	
	
}
