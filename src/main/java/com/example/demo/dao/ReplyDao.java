package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Reply;

@Mapper
public interface ReplyDao {
	
	@Select("""
			SELECT r.*
					, m.nickname writerName
				FROM reply r
				inner join `member` m
				on r.memberId = m.id
				WHERE relTypeCode = #{relTypeCode}
				AND relId = #{relId}
			""")
	public List<Reply> getReplies(String relTypeCode, int relId);
}
