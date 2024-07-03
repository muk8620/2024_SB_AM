package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Reply;

@Mapper
public interface ReplyDao {
	
	@Insert("""
			INSERT INTO reply
				SET regDate = NOW()
					, updateDate = NOW()
					, memberId = #{loginedMemberId}
					, relTypeCode = #{relTypeCode}
					, relId = #{relId}
					, `body` = #{body}
			""")
	public void writeReply(int loginedMemberId, String relTypeCode, int relId, String body);
	
	@Select("""
			SELECT r.*
					, m.nickname writerName
				FROM reply r
				inner join `member` m
				on r.memberId = m.id
				WHERE r.relTypeCode = #{relTypeCode}
				AND r.relId = #{relId}
			""")
	public List<Reply> getReplies(String relTypeCode, int relId);

	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();
	
	@Delete("""
			DELETE FROM reply 
				WHERE id = #{id}
			""")
	public void deleteArticle(int id);
}