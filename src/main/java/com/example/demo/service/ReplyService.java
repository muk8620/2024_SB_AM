package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ReplyDao;
import com.example.demo.vo.Reply;

@Service
public class ReplyService {
	
	private ReplyDao replyDao;
	
	public ReplyService(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}
	
	public List<Reply> getReplies(String relTypeCode, int relId) {
		return replyDao.getReplies(relTypeCode, relId);
	}
	
	public void writeReply(int loginedMemberId, String relTypeCode, int relId, String body) {
		this.replyDao.writeReply(loginedMemberId, relTypeCode, relId, body);
	}

	public int getLastInsertId() {
		return this.replyDao.getLastInsertId();
	}

	public void deleteArticle(int id) {
		replyDao.deleteArticle(id);
	}
}