package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;

@Controller
public class UsrMemberController {
	
	private MemberService memberService;
	
	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/usr/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		if (Util.isEmpty(loginId)) {
			return "아이디를 입력해주세요.";
		}
		
		if (Util.isEmpty(loginPw)) {
			return "비밀번호를 입력해주세요.";
		}
		
		if (Util.isEmpty(name)) {
			return "이름을 입력해주세요.";
		}
		
		if (Util.isEmpty(nickname)) {
			return "닉네임을 입력해주세요.";
		}
		
		if (Util.isEmpty(cellphoneNum)) {
			return "전화번호를 입력해주세요.";
		}
		
		if (Util.isEmpty(email)) {
			return "이메일을 입력해주세요.";
		}
		
		Member foundMember = memberService.getMemberByLoginId(loginId);
		
		if (foundMember != null) {
			return loginId + "은(는) 이미 존재하는 아이디입니다.";
		}
		
		memberService.joinMember(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		int id = memberService.getLastInsertId();
		
		return memberService.getMemberById(id);
	}
	
}
