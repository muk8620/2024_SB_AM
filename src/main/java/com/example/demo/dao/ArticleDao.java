package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.vo.Article;

@Component
public class ArticleDao {
	
	private int lastArticleId;
	private List<Article> articles;
	
	public ArticleDao() {
		this.lastArticleId = 0;
		this.articles = new ArrayList<>();
		makeTestData();
	}
	
	public Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}
	
	public void makeTestData() {
		for (int i = 1; i <= 5; i++) {
			doWrite("제목" + i, "내용" + i);
		}
	}

	public Article doWrite(String title, String body) {
		Article article = new Article(++lastArticleId, title, body);
		articles.add(article);
		return article;
	}
	
	public void doDelete(Article foundArticle) {
		articles.remove(foundArticle);
	}
		
	public void doModify(Article foundArticle, String title, String body) {
		foundArticle.setTitle(title);
		foundArticle.setBody(body);
	}

	public List<Article> showList() {
		return articles;
	}
}
