package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.LikePointDao;
import com.example.demo.vo.LikePoint;
import com.example.demo.vo.ResultData;

@Service
public class LikePointService {
	
	private LikePointDao LikePointDao;
	
	public LikePointService(LikePointDao LikePointDao) {
		this.LikePointDao = LikePointDao;
	}
	
	public ResultData<Integer> getLikePoint(int loginedMemberId, String relTypeCode, int relId) {
		
		LikePoint likePoint = this.LikePointDao.getLikePoint(loginedMemberId, relTypeCode, relId);
		
		int totalCnt = this.LikePointDao.getTotalCnt(relTypeCode, relId);
		
		if (likePoint == null) {
			return ResultData.from("F-1", "좋아요 기록 없음", totalCnt);
		}
		
		return ResultData.from("S-1", "좋아요 기록 있음", totalCnt);
	}

	public void insertLikePoint(int loginedMemberId, String relTypeCode, int relId) {
		this.LikePointDao.insertLikePoint(loginedMemberId, relTypeCode, relId);
	}

	public void deleteLikePoint(int loginedMemberId, String relTypeCode, int relId) {
		this.LikePointDao.deleteLikePoint(loginedMemberId, relTypeCode, relId);
	}
}
