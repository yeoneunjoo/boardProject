package com.yeon.biz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yeon.biz.board.BoardService;
import com.yeon.biz.board.BoardVO;
import com.yeon.biz.member.MemberService;
import com.yeon.biz.member.MemberVO;
import com.yeon.biz.reply.ReplyService;
import com.yeon.biz.reply.ReplyVO;
import com.yeon.biz.report.ReportService;
import com.yeon.biz.report.ReportVO;

@Controller
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/report.do")
	public String insertReport(ReportVO pVO, ReplyVO rVO) {
		System.out.println("RC insertReport 로그1"+pVO);
		reportService.insertReport(pVO);
		if(pVO.getRid()!=0) {
			rVO.setRid(pVO.getRid());
			System.out.println("RC insertReport 로그3"+rVO);
			replyService.updateRreport(rVO);
		}
		System.out.println("RC insertReport 로그2"+pVO);
		return "redirect:board.do?bid="+pVO.getBid();
	}
	
	@RequestMapping("/reportBoardList.do")
	public String selectAllBR(ReportVO pVO, Model model) {
		// 마이페이지에서 관리자모드로 전환, 신고글 확인
		System.out.println("RC selectAllBR 로그1 :"+pVO);
		List<ReportVO> datas=reportService.selectAllBR(pVO);
		model.addAttribute("datas", datas);
		System.out.println("RC selectAllBR 로그2 :"+datas);
		return "reportBoardList.jsp";
	}
	
	@RequestMapping("/reportReplyList.do")
	public String selectAllRR(ReportVO pVO, ReplyVO rVO, Model model) {
		// 신고 댓글 확인
		System.out.println("RC selectAllRR 로그1"+pVO);
		List<ReportVO> datas=reportService.selectAllReportReply(pVO);
		model.addAttribute("datas", datas);
		System.out.println("RC selectAllRR 로그2"+datas);
		return "reportReplyList.jsp";
	}
	
	@RequestMapping("/blindReply.do")
	public void blindReply(ReplyVO rVO, MemberVO mVO, ReportVO pVO, HttpServletResponse response) throws IOException {
		// board.jsp에서 여기로 들어오는 신고처리 유무에 대한 데이터값을 받고
		// 신고처리를 진행한다는 데이터값이 들어오면 댓글신고확정칼럼에 추가
		// 회원에게 패널티를 준다
		System.out.println("ReportC blindReply 로그1 : "+rVO.getRid());
		replyService.updateRblind(rVO);
//		mVO.setMid(rVO.getRmid());
//		replyService.selectOneReply(rVO);
		System.out.println("ReportC blindReply 로그4 : "+rVO);
		// 여기까지는 정상적으로 들어옴
		rVO=replyService.selectOneReplyforReport(rVO); // 얘 이상한 거같은데
		System.out.println("ReportC blindReply 로그2 : "+rVO);
		mVO.setMid(rVO.getRmid());
		memberService.updatePenalty(mVO);
			
		pVO.setRid(rVO.getRid());
		System.out.println("ReportC blindReply 로그3 : "+pVO.getRid());
		reportService.deleteReportRid(pVO);
		System.out.println("ReportC blindReply 로그5 : "+pVO.getRid());
		
		String confirm="0";
		PrintWriter out = response.getWriter();
		out.write(confirm);
	}
	
	@RequestMapping("/notBlindReply.do")
	public void notblindReply(ReplyVO rVO, MemberVO mVO, ReportVO pVO, HttpServletResponse response) throws IOException {
		System.out.println("ReportC notblindReply 로그1 : "+rVO);
		replyService.resetRreport(rVO);
		pVO.setRid(rVO.getRid());
		System.out.println("ReportC notblindReply 로그2 : "+pVO);
		reportService.deleteReportRid(pVO);
		
		String confirm="0";
		PrintWriter out = response.getWriter();
		out.write(confirm);
		// 신고처리를 진행하지않는다는 데이터값을 받으면
		// 신고된 댓글의 rid값을 가지고 있는 신고번호sid를 모두 삭제
	}
	
	@RequestMapping("/reportMemberList.do")
	public String selectPenaltyMember(MemberVO mVO, Model model) {
		// 신고 댓글 확인
		System.out.println("RC selectPenaltyMember 로그1"+mVO);
		List<MemberVO> datas=memberService.selectPenaltyMember(mVO);
		model.addAttribute("datas", datas);
		System.out.println("RC selectAllRR 로그2"+datas);
		return "reportMemberList.jsp";
	}
	
	@RequestMapping("/deleteMember.do")
	public String deleteMember(MemberVO mVO, ReplyVO rVO, BoardVO bVO, ReportVO pVO) {
		rVO.setRmid(mVO.getMid());
		System.out.println("RC deleteMember 로그1"+rVO);
		replyService.deleteReply_M(rVO); // 멤버가 쓴 댓글, 댓글의 신고번호 삭제
		
		bVO.setMid(mVO.getMid());
		boardService.deleteBoard_M(bVO);
		
		System.out.println("RC deleteMember 로그2");
		
		memberService.deleteAdmin(mVO);		
		return "reportMemberList.jsp";
	}
	
}


