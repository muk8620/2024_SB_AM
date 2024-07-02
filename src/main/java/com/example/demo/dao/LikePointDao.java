package com.example.demo.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.LikePoint;

@Mapper
public interface LikePointDao {
	
	@Select("""
			SELECT *
				FROM likePoint
				WHERE memberId = #{loginedMemberId}
				AND relTypeCode = #{relTypeCode}
				AND relId = #{relId}
			""")
	public LikePoint getLikePoint(int loginedMemberId, String relTypeCode, int relId);

	@Select("""
			SELECT IFNULL(SUM(`point`), 0) `likePoint`
				FROM likePoint
				WHERE relTypeCode = #{relTypeCode}
				AND relId = #{relId}
			""")
	public int getTotalCnt(String relTypeCode, int relId);
	
	@Insert("""
			INSERT INTO likePoint
				SET memberId = #{loginedMemberId}
					, relTypeCode = #{relTypeCode}
					, relId = #{relId}
			""")
	public void insertLikePoint(int loginedMemberId, String relTypeCode, int relId);

	@Delete("""
			DELETE FROM likePoint
				WHERE memberId = #{loginedMemberId}
				AND relTypeCode = #{relTypeCode}
				AND relId = #{relId}
			""")
	public void deleteLikePoint(int loginedMemberId, String relTypeCode, int relId);
}
