package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.vo.Article;

@Service
public class ArticleService {
	
	private ArticleDao articleDao;
	
	public ArticleService(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	public Article doWrite(String title, String body) {
		return articleDao.doWrite(title, body);
	}

	public List<Article> showList() {
		return articleDao.showList();
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public void doDelete(Article foundArticle) {
		articleDao.doDelete(foundArticle);
	}

	public void doModify(Article foundArticle, String title, String body) {
		articleDao.doModify(foundArticle, title, body);
	}

}
