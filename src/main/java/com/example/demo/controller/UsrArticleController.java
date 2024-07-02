package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.service.ReplyService;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
import com.example.demo.vo.Reply;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UsrArticleController {

	private ArticleService articleService;
	private ReplyService replyService;
	private Rq rq;

	public UsrArticleController(ArticleService articleService, ReplyService replyService, Rq rq) {
		this.articleService = articleService;
		this.replyService = replyService;
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
	public String list(Model model, int boardId, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "titleAndBody") String searchKeywordType,
			@RequestParam(defaultValue = "") String searchKeyword) {

		String boardName = articleService.getBoardNameById(boardId);

		int articlesCnt = articleService.getArticlesCnt(boardId, searchKeywordType, searchKeyword);

		int itemsInAPage = 10;
		int limitFrom = (page - 1) * itemsInAPage;

		List<Article> articles = articleService.getArticles(boardId, searchKeywordType, searchKeyword, limitFrom, itemsInAPage);

		int from = ((page - 1) / 10) * 10 + 1;
		int end = (((page - 1) / 10) + 1) * 10;

		int totalPageCnt = (int) Math.ceil((double) articlesCnt / itemsInAPage);

		if (end > totalPageCnt) {
			end = totalPageCnt;
		}

		model.addAttribute("page", page);
		model.addAttribute("from", from);
		model.addAttribute("end", end);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("boardName", boardName);
		model.addAttribute("articlesCnt", articlesCnt);
		model.addAttribute("articles", articles);

		return "usr/article/list";
	}

	@GetMapping("/usr/article/detail")
	public String detail(HttpServletRequest req, HttpServletResponse resp, Model model, int id) {
		
		Cookie[] cookies = req.getCookies();
		
		boolean isViewed = false;
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("viewedArticle_" + id)) {
					isViewed = true;
					break;
				}
			}
		}
		
		if (!isViewed) {
			articleService.increaseView(id);
			Cookie cookie = new Cookie("viewedArticle_" + id, "true");
			cookie.setMaxAge(5);
			resp.addCookie(cookie);
		}
		
		Article article = articleService.forPrintArticle(id);
		
		int point = articleService.getPointByRelIdAndMemberId(rq.getLoginedMemberId(), "article", id);
		
		List<Reply> replies = replyService.getReplies("article", id);
		
		model.addAttribute("article", article);
		model.addAttribute("point", point);
		model.addAttribute("replies", replies);

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
	
	@GetMapping("/usr/article/doPoint")
	@ResponseBody
	public ResultData<String> doPoint(int memberId, String relTypeCode, int relId, int point) {
		
		if (point != 0) {
			articleService.decreasePoint(memberId, relTypeCode, relId);
			return ResultData.from("F-1", "추천수 감소");
		}
		
		articleService.increasePoint(memberId, relTypeCode, relId);

		return ResultData.from("S-1", "추천수 증가 성공");
	}
}
