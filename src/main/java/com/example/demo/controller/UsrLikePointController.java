package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.LikePointService;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

@Controller
public class UsrLikePointController {

	private LikePointService likePointService;
	private Rq rq;

	public UsrLikePointController(LikePointService likePointService, Rq rq) {
		this.likePointService = likePointService;
		this.rq = rq;
	}

	@GetMapping("/usr/likePoint/getLikePoint")
	@ResponseBody
	public ResultData<Integer> getLikePoint(String relTypeCode, int relId) {
		return likePointService.getLikePoint(rq.getLoginedMemberId(), relTypeCode, relId);
	}
	
	@GetMapping("/usr/likePoint/doLikePoint")
	@ResponseBody
	public String doLikePoint(String relTypeCode, int relId, boolean liked) {
		
		if (liked) {
			likePointService.deleteLikePoint(rq.getLoginedMemberId(), relTypeCode, relId);
			return "좋아요 취소";
		}
		
		likePointService.insertLikePoint(rq.getLoginedMemberId(), relTypeCode, relId);
		return "좋아요 성공";
	}
}
