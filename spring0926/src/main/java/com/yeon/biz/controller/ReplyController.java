package com.yeon.biz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yeon.biz.board.BoardVO;
import com.yeon.biz.member.MemberVO;
import com.yeon.biz.reply.ReplyService;
import com.yeon.biz.reply.ReplyVO;


@Controller
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	
	@RequestMapping("/insertReply.do")
	public String insertReply(ReplyVO rVO, MemberVO mVO, BoardVO bVO, HttpSession session) {
		mVO=(MemberVO) session.getAttribute("member");
		bVO=(BoardVO) session.getAttribute("data");
		System.out.println("RC insertReply 로그1"+session.getAttribute("data"));
		System.out.println("RC insertReply 로그2"+session.getAttribute("member"));
		rVO.setRbid(bVO.getBid());
		rVO.setRname(mVO.getName());
		rVO.setRmid(mVO.getMid());
		System.out.println("RC insertReply 로그3"+rVO);
		replyService.insertReply(rVO);
		System.out.println("RC insertReply 로그2");
		return "redirect:board.do?bid="+rVO.getRbid();
	}
	
	@RequestMapping("/updateReply.do")
	public String updateReply(ReplyVO rVO) {
		System.out.println("RC updateReply 로그1"+rVO);
		replyService.updateReply(rVO);
		return "redirect:board.do?bid="+rVO.getRbid();
	}
	
	
	@RequestMapping("/deleteReply.do")
	public String deleteReply(ReplyVO rVO) {
		System.out.println("RC deleteReply 로그1"+rVO);
		replyService.deleteReply(rVO);
		System.out.println("RC deleteReply 로그2"+rVO);
		return "redirect:board.do?bid="+rVO.getRbid();
		
	}
	
}
