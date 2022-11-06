package com.yeon.biz.controller;

import java.io.IOException;
import java.util.Locale;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.LocaleResolver;

import com.mysql.cj.jdbc.DatabaseMetaDataUsingInfoSchema;
import com.yeon.biz.board.BoardService;
import com.yeon.biz.board.BoardVO;
import com.yeon.biz.member.MemberService;
//import com.yeon.biz.board.BoardVO;
//import com.yeon.biz.board.impl.BoardDAO;
import com.yeon.biz.member.MemberVO;
import com.yeon.biz.member.impl.MemberDAO;
import com.yeon.biz.reply.ReplyService;
import com.yeon.biz.reply.ReplyVO;

@Controller
@SessionAttributes("member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private ReplyService replyService;
	
	@ModelAttribute("scMapMemRole")
	public Map<String,String> scMapMemRoleMap(){
		Map<String,String> scMap=new HashMap<String,String>();
		scMap.put("회원", "MEMBER");
		scMap.put("관리자", "ADMIN");
		return scMap;
	}
	
	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public String index() {
			return "login.jsp";
	}
	
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public String selectOneMember(MemberVO mVO,HttpSession session) {
		mVO=memberService.selectOneMember(mVO);
		if(mVO==null) {
			return "login.jsp";
		}
		else {
			session.setAttribute("member", mVO);
//			System.out.println(mVO.getMid());
//			System.out.println(mVO.getName());
			System.out.println(mVO);
			System.out.println("/login.do"+session.getAttribute("member"));
			return "redirect:main.do";
		}
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index.jsp";
	}
	
	@RequestMapping("/signuppage.do")
	public String signup(HttpSession session) {
		return "signin.jsp?lang="+session.getAttribute("lang");
	}
	
	@RequestMapping("/signup.do")
	public String insertMember(MemberVO mVO) {
		memberService.insertMember(mVO);
		return "redirect:login.do";
	}
	// 수행되어지는 로직들이 선후관계가 있을 때에는 모델에 저장되어있는 mVO를 사용하는 것이 좋고
	// 선후관계x, 로직들이 독립적으로 존재할 때에는 세션 저장값을 사용하는 것이 좋다, 쇼핑몰의 장바구니와 로그인이 이에 속함
	// 쇼핑몰의 장바구니는 어느 페이지를 방문하는지와 관계없이 독립적으로 존재하는(로직들의 전후관계에 영향받지 않는) 페이지
	// 로그인 또한 로그인 여부와 관계없이 쇼핑몰을 둘러볼 수 있기때문임
	
	@RequestMapping("/mypage.do")
	public String mypage(MemberVO mVO,HttpSession session) {
//		System.out.println("/mypage.do"+session.getAttribute("member"));
		session.setAttribute("member", session.getAttribute("member"));
		System.out.println("로그"+session.getAttribute("member"));
		return "mypage.jsp?lang="+session.getAttribute("lang");
		
	}
	
	@RequestMapping("/update.do")
	public String updateMember(MemberVO mVO,HttpSession session) {
		memberService.updateMember(mVO);
//		System.out.println("새로운로그"+mVO.getMpw());
//		System.out.println("새로운로그"+mVO.getName());
		session.invalidate();
		return "redirect:login.do";
	}
	
	@RequestMapping("/check.do")
	public void check(MemberVO mVO,HttpServletResponse response) throws IOException {
		System.out.println("MC check 로그1"+mVO.getMid());
		String check="0";
		if(mVO.getMid()!=null) {
			mVO.setMid(mVO.getMid());
	//		System.out.println(mVO.getMid());
			check=memberService.check(mVO);
		}
		else {
			mVO.setName(mVO.getName());
			check=memberService.check(mVO);
		}
		System.out.println("MC check 로그2");
		System.out.println("MC check 로그3 check"+check);
		PrintWriter out = response.getWriter();
		out.write(check);
		// 사용가능 - 0 , 사용불가 1
	}
	
	@RequestMapping("/delete.do")
	public String deleteMember(MemberVO mVO,BoardVO bVO,ReplyVO rVO,HttpServletRequest request) {
		HttpSession session=request.getSession();
//		System.out.println("MC deleteMember 로그1");
		mVO=(MemberVO)session.getAttribute("member");
//		System.out.println("MC deleteMember 로그2");
//		System.out.println("MC deleteMember 로그3 : "+session.getAttribute("member"));
		
		rVO.setRmid(mVO.getMid());
		System.out.println("RC deleteMember 로그1"+rVO);
		replyService.deleteReply_M(rVO); // 멤버가 쓴 댓글, 댓글의 신고번호 삭제
		
		bVO.setMid(mVO.getMid());
		boardService.deleteBoard_M(bVO);
		memberService.deleteMember(mVO);
		return "redirect:login.do";
	}
	
//	@ModelAttribute("scMap")
//	public Map<String,String> searchConditionMap(){
//		Map<String,String> scMap=new HashMap<String,String>();
//		scMap.put("제목", "TITLE");
//		scMap.put("작성자", "WRITER");
//		return scMap;
//	}
	
//	@RequestMapping("/main.do")
//	public String main(@RequestParam(value="searchCondition",defaultValue="TITLE",required=false)String searchCondition,@RequestParam(value="searchContent",defaultValue="",required=false)String searchContent,BoardVO bVO,BoardDAO bDAO,Model model)  {
//		// 이 상태에서 검색어 자동매핑이 되지 않음(public String selectAllBoard(BoardVO bVO, BoardDAO bDAO, Model model))
//		// 그렇기때문에 자바에서만 사용할 목적으로 BoardVO에서 검색어를 만들어도 되지만
//		// 더 경제적인 방법으로 어노테이션을 사용해도 됨!!!
//		System.out.println("검색조건: "+searchCondition);
//		System.out.println("검색어: "+searchContent);
//
//		List<BoardVO> datas=bDAO.selectAllBoard(bVO);
//		model.addAttribute("datas", datas);
//		return "main.jsp";
//	}
	
//	@RequestMapping("/board.do")
//	public String selectOneBoard(BoardVO bVO, BoardDAO bDAO, Model model){
//		bVO=bDAO.selectOneBoard(bVO);
//		model.addAttribute("data", bVO);
//		return"board.jsp";
//	}
	
//	@RequestMapping(value="/write.do",method=RequestMethod.POST)
//	public String insertBoard(BoardVO bVO,BoardDAO bDAO) {
//		//1) CRUD에서 CUD의 반환타입은 void, R만 반환값이 존재하는데
//		//2) selectOne는 BoardVO, selectAll은 List<BoardVO>
//		bDAO.insertBoard(bVO);
//		System.out.println("새로운로그"+bVO.getTitle());
//		return "redirect:main.do";
//	}
}
