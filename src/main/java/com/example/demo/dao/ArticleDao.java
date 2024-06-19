package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Article;

@Mapper
public interface ArticleDao {
	
	public void makeTestData();

	public Article doWrite(String title, String body);
	
	public void doDelete(Article foundArticle);
	
	public Article getArticleById(int id);
		
	public void doModify(Article foundArticle, String title, String body);

	public List<Article> showList();
}
