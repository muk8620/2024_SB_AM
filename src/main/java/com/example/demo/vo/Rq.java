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
	
	public Rq(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		
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
}