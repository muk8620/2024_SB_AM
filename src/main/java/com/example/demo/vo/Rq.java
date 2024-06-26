package com.example.demo.vo;

import com.example.demo.util.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;

public class Rq {
	
	@Getter
	private int loginedMemberId;
	private HttpServletResponse resp;
	private HttpSession session;
	
	public Rq(HttpServletRequest req, HttpServletResponse resp) {
		session = req.getSession();
		
		int loginedMemberId = 0;
		
		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		
		this.loginedMemberId = loginedMemberId;
		this.resp = resp;
	}

	public void jsPrintReplace(String msg, String uri) {
		resp.setContentType("text/html; charset=UTF-8;");
		
		try {
			resp.getWriter().append(Util.jsReplace(msg, uri));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void login(Member member) {
		this.session.setAttribute("loginedMemberId", member.getId());
	}

	public void logout() {
		this.session.removeAttribute("loginedMemberId");
	}
	
}