package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsrArticleController {
	
	private ArticleService articleService;
	
	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@GetMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData<Article> doWrite(HttpSession session, String title, String body) {
		
		if (session.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-L", "로그인 후 이용해주세요");
		}
		
		if (Util.isEmpty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요");
		}
		if (Util.isEmpty(body)) {
			return ResultData.from("F-2", "내용을 입력해주세요");
		}
		
		articleService.writeArticle((int) session.getAttribute("loginedMemberId"), title, body);
		
		int id = articleService.getLastInsertId();
		
		return ResultData.from("S-1", String.format("%d번 게시물을 작성했습니다.", id), articleService.forPrintArticle(id));
	}
	
	@GetMapping("/usr/article/list")
	public String showList(Model model) {
		List<Article> articles = articleService.getArticles();
		
		model.addAttribute("articles", articles);
		return "usr/article/list";
	}
	
	@GetMapping("/usr/article/showDetail")
	@ResponseBody
	public ResultData<Article> showDetail(int id) {
		Article foundArticle = articleService.forPrintArticle(id);
		
		if (foundArticle == null) {
			return ResultData.from("F-1", String.format("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		return ResultData.from("S-1", String.format("%d번 게시물 상세보기", id), foundArticle);
	}
	
	@GetMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(HttpSession session, int id, String title, String body) {
		Article foundArticle = articleService.getArticleById(id);
		
		if (foundArticle == null) {
			return ResultData.from("F-1", String.format("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		int loginedMemberId = (int) session.getAttribute("loginedMemberId");
		
		if (foundArticle.getMemberId() != loginedMemberId) {
			return ResultData.from("F-A", "해당 게시물에 대한 권한이 없습니다.");
		}
		
		articleService.modifyArticle(id, title, body);
		
		return ResultData.from("S-1", String.format("%d번 게시물이 수정되었습니다.", id));
	}
	
	@GetMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(HttpSession session, int id) {
		Article foundArticle = articleService.getArticleById(id);
		
		if (foundArticle == null) {
			return ResultData.from("F-1", String.format("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		int loginedMemberId = (int) session.getAttribute("loginedMemberId");
		
		if (foundArticle.getMemberId() != loginedMemberId) {
			return ResultData.from("F-A", "해당 게시물에 대한 권한이 없습니다.");
		}
		
		articleService.deleteArticle(id);
		return ResultData.from("S-1", String.format("%d번 게시물이 삭제되었습니다.", id));
	}
}
