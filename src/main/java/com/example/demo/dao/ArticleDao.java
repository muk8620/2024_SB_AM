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
					, boardId = #{boardId}
					, title = #{title}
					, `body` = #{body}
			""")
	public void writeArticle(int loginedMemberId, int boardId, String title, String body);
	
	@Select("""
			<script>
			with pointTable as
				(
					SELECT relId 
					, SUM(point) point
					FROM likePoint 
					WHERE relTypeCode = 'article'
					GROUP BY relId
				)
			SELECT a.* 
					, m.nickname writerName
					, IFNULL(pt.point, 0) point
				FROM article a 
				inner join `member` m
				on a.memberId = m.id
				LEFT JOIN pointTable pt
				ON a.id = pt.relId
				WHERE A.boardId = #{boardId}
				<if test="searchKeyword != ''">
					<choose>
						<when test="searchKeywordType == 'title'">
							AND a.title like CONCAT('%', #{searchKeyword}, '%')
						</when>
						<when test="searchKeywordType == 'body'">
							AND a.body like CONCAT('%', #{searchKeyword}, '%') 
						</when>
						<otherwise>
							AND (
								a.title like CONCAT('%', #{searchKeyword}, '%')
								OR a.body like CONCAT('%', #{searchKeyword}, '%')
							) 
						</otherwise>
					</choose>
				</if>
				ORDER BY a.id DESC
				LIMIT #{limitFrom}, #{itemsInAPage}
			</script>
			""")
	
	public List<Article> getArticles(int boardId, String searchKeywordType, String searchKeyword, int limitFrom, int itemsInAPage);
	
	@Select("""
			with pointTable as
				(
					SELECT relId 
					, SUM(point) point
					FROM likePoint 
					WHERE relTypeCode = 'article'
					GROUP BY relId
				)
			SELECT a.* 
					, m.nickname writerName
					, IFNULL(pt.point, 0) point
				FROM article a 
				inner join `member` m
				on a.memberId = m.id
				LEFT JOIN pointTable pt
				ON a.id = pt.relId
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
			<script>
			select count(id)
				from article
				where boardId = #{boardId}
				<if test="searchKeyword != ''">
					<choose>
						<when test="searchKeywordType == 'title'">
							AND title like CONCAT('%', #{searchKeyword}, '%')
						</when>
						<when test="searchKeywordType == 'body'">
							AND `body` like CONCAT('%', #{searchKeyword}, '%') 
						</when>
						<otherwise>
							AND (
								title like CONCAT('%', #{searchKeyword}, '%')
								OR `body` like CONCAT('%', #{searchKeyword}, '%')
							) 
						</otherwise>
					</choose>
				</if>
			</script>
			""")
	public int getArticlesCnt(int boardId, String searchKeywordType, String searchKeyword);

	@Update("""
			UPDATE article 
				SET views = views + 1
				where id = #{id}
			""")
	public void increaseView(int id);

	@Insert("""
			insert into likePoint
				set memberId = ${memberId}
					, relTypeCode = #{relTypeCode}
					, relId = #{relId}
			""")
	public void increasePoint(int memberId, String relTypeCode, int relId);

}
