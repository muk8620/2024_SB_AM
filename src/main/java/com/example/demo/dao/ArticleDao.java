package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.Article;

@Mapper
public interface ArticleDao {
	
	@Insert("""
			insert into article
				set regDate = now()
					, updateDate = now()
					, memberId = #{loginedMemberId}
					, title = #{title}
					, `body` = #{body}
			""")
	public void writeArticle(int loginedMemberId, String title, String body);
	
	@Select("""
			SELECT a.* 
					, m.nickname writerName
				FROM article a 
				inner join `member` m
				on a.memberId = m.id
				WHERE A.boardId = #{boardId}
				ORDER BY a.id DESC
			""")
	public List<Article> getArticles(int boardId);
	
	@Select("""
			SELECT a.* 
					, m.nickname writerName
				FROM article a 
				inner join `member` m
				on a.memberId = m.id
				WHERE a.id = #{id}
			""")
	public Article forPrintArticle(int id);
	
	@Select("""
			SELECT *
				FROM article 
				WHERE id = #{id}
			""")
	public Article getArticleById(int id);
	
	@Update("""
			<script>
			UPDATE article 
				SET updateDate = now()
					<if test="title != null and title != ''">
						, title = #{title}
					</if>
					<if test="body != null and body != ''">
						, `body` = #{body} 
					</if>
				where id = #{id}
			</script>
			""")
	public void modifyArticle(int id, String title, String body);
	
	@Delete("""
			DELETE FROM article 
				WHERE id = #{id}
			""")
	public void deleteArticle(int id);
	
	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();
	
	@Select("""
			SELECT `name`
				FROM board
				WHERE id = #{boardId}
			""")
	public String getBoardNameById(int boardId);
	
	@Select("""
			select count(id)
				from article
				where boardId = #{boardId}
			""")
	public int getArticlesCnt(int boardId);

}
