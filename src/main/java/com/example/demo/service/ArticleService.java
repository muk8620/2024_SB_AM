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

	public void doWrite(String title, String body) {
		articleDao.doWrite(title, body);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}
	
	public List<Article> showList() {
		return articleDao.showList();
	}
	
	public void doModify(int id, String title, String body) {
		articleDao.doModify(id, title, body);
	}

	public void doDelete(int id) {
		articleDao.doDelete(id);
	}

	public int getLastInsertId() {
		return articleDao.getLastInsertId();
	}

}
