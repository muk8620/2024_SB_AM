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
		Article article = articleService.doWrite(title, body);
		
		return article;
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
			return String.format("<script>alert('%d번 게시물은 존재하지 않습니다.'); location.replace('showList');</script>", id);
		}
		
		return foundArticle;
	}
	
	@GetMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(@RequestParam int id) {
		Article foundArticle = articleService.getArticleById(id);
		
		if (foundArticle == null) {
			return String.format("<script>alert('%d번 게시물은 존재하지 않습니다.'); location.replace('showList');</script>", id);
		}
		
		articleService.doDelete(foundArticle);
		return String.format("<script>alert('%d번 게시물이 삭제되었습니다.'); location.replace('showList');</script>", id);
	}
	
	@GetMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(@RequestParam int id, @RequestParam String title, @RequestParam String body) {
		Article foundArticle = articleService.getArticleById(id);
		
		if (foundArticle == null) {
			return String.format("<script>alert('%d번 게시물은 존재하지 않습니다.'); location.replace('showList');</script>", id);
		}
		
		articleService.doModify(foundArticle, title, body);
		
		return String.format("<script>alert('%d번 게시물이 수정되었습니다.'); location.replace('showList');</script>", id);
	}
	
}
