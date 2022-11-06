package com.yeon.biz.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.yeon.biz.board.BoardService;
import com.yeon.biz.board.BoardVO;
import com.yeon.biz.board.impl.BoardDAO;
import com.yeon.biz.member.MemberService;
import com.yeon.biz.member.MemberVO;
import com.yeon.biz.reply.ReplyService;
import com.yeon.biz.reply.ReplyVO;
import com.yeon.biz.report.ReportService;
import com.yeon.biz.report.ReportVO;

@Controller
@SessionAttributes("data") // "data"라는 이름의 데이터가 Model 객체에 세팅이된다면, 그것을 session에 기억시키겠다.
//@SessionAttributes("data") 이걸 통해서 selectOneBoardㅇ세션에 저장되고
public class BoardController{
	
	@Autowired
	private BoardService boardService;  // 비즈니스 컴포넌트. DAO를 직접 이용 xxx
	
	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private MemberService memberService;
	
//	@Autowired
//	private ReportService reportService;
	
	@ModelAttribute("scMap")
	public Map<String,String> searchConditionMap(HttpSession session){
		Map<String,String> scMap=new HashMap<String,String>();
		Locale locale_K = new Locale("ko","KR");
		if(session.getAttribute("lang").equals(locale_K)) {
			scMap.put("제목", "TITLE");
			scMap.put("글내용", "CONTENT");
			scMap.put("작성자", "WRITER");
			return scMap;
		}
		else {
			scMap.put("TITLE", "TITLE");
			scMap.put("CONTENT", "CONTENT");
			scMap.put("WRITER", "WRITER");
			return scMap;
		}
	}
	
	@ModelAttribute("scMapB")
	public Map<String,String> searchConditionMapB(HttpSession session){
		Map<String,String> scMapB=new HashMap<String,String>();
		//System.out.println("로그1"); // @RequestMapping 메서드보다 먼저 호출됨
		Locale locale_K = new Locale("ko","KR");
		if(session.getAttribute("lang").equals(locale_K)) {
			scMapB.put("제목", "TITLE");
			scMapB.put("글내용", "CONTENT");
			return scMapB;
		}
		else {
			scMapB.put("TITLE", "TITLE");
			scMapB.put("CONTENT", "CONTENT");
			return scMapB;
		}
	}
	
	@RequestMapping("/main.do")
	public String main(BoardVO bVO, Model model, HttpSession session) {
		if(bVO.getSearchCondition()==null) {
			bVO.setSearchCondition("TITLE");
		}
		if(bVO.getSearchContent()==null) {
			bVO.setSearchContent("");
		}
//		System.out.println("검색조건: "+bVO.getSearchCondition());
//		System.out.println("검색어: "+bVO.getSearchContent());
		
		List<BoardVO> datas=boardService.search(bVO);
		model.addAttribute("datas", datas);
		return "main.jsp";
	}
	
	@RequestMapping("/board.do")
	public String selectOneBoard(BoardVO bVO, ReplyVO rVO, Model model){
//		boardService.updateViews(bVO);
		bVO=boardService.selectOneBoard(bVO);
//		boardService.updateViews(bVO);
		System.out.println("BC selectOneBoard에서 찍은 로그5 :"+bVO.getViews());
//		System.out.println("BC selectOneBoard에서 찍은 로그 vo.getTitle"+bVO.getTitle());
//		System.out.println("BC selectOneBoard에서 찍은 로그 vo.getFileName"+bVO.getUploadFile());
		model.addAttribute("data", bVO);
		// 모델객체에 있는 data에 bVO를 저장해서 나중에 꺼내쓸 건데, 꺼내쓰려면 
		// @SessionAttributes을 통해서 세션에 저장시켜줘야함(28번째 줄 참고)
//		System.out.println("BC selectOneBoard에서 찍은 로그1234 :"+bVO);
//		System.out.println("BC selectOneBoard에서 찍은 로그1234 :"+model);

		// rVO가 주축이 되는 Report 테이블과의 조인이 필요하고
		// 그 조인의 결과를 보드로 보내서
		// 보드에서 reply에 rid값이 있으면 신고댓글 처리로 넘어가야할듯(처리완료)
		rVO.setRbid(bVO.getBid());
		System.out.println("BC selectOneBoard에서 찍은 로그1 :"+rVO);
		List<ReplyVO> reply=replyService.selectAllReply(rVO);
		System.out.println("BC selectOneBoard에서 찍은 로그3 :"+rVO);
		System.out.println("BC selectOneBoard에서 찍은 로그2 :"+reply);
		model.addAttribute("reply", reply);
//		System.out.println("BC selectOneBoard에서 찍은 로그2 :"+reply);
		return "board.jsp";
	}
	@RequestMapping("writeNew.do")
	public String writeNew() {
		return "write.jsp";
	}
	
	@RequestMapping(value="/write.do",method=RequestMethod.POST)
	public String insertBoard(BoardVO bVO) throws IllegalStateException, IOException {
		MultipartFile uploadFile=bVO.getUploadFile();
		System.out.println("BC insertBoard 로그");
		String fileName=uploadFile.getOriginalFilename(); // 업로드한 파일명
		if(!uploadFile.isEmpty()) { // 업로드한 파일의 존재여부 확인
			fileName=uploadFile.getOriginalFilename();
			uploadFile.transferTo(
				new File("/Users/yeoneunju/Desktop/0607/workspace/"
						+ "spring0926/src/main/webapp/images/"+fileName));
		}
		else {
		fileName="pic01.jpg"; // 등록한 사진이 없다면 기본적으로 등록될 사진
	}
		bVO.setFileName(fileName);
//		System.out.println("BC insertBoard 로그 :"+bVO.getFileName());
		boardService.insertBoard(bVO);
		return "redirect:main.do";
	}
	
	@RequestMapping("updateBoard.do")
	public String updateBoard(@ModelAttribute("data")BoardVO bVO) throws IllegalStateException, IOException {
		//@ModelAttribute("data")BoardVO bVO)를 통해서 가져다쓸 수 있는 것....
		String fileName;
//		System.out.println("로그로그"+bVO.getContent());
		MultipartFile uploadFile=bVO.getUploadFile();
//		System.out.println("BC updateBoard에서 찍은 로그9 :"+bVO.getFileName());
		if(!uploadFile.isEmpty()) { // 업로드한 파일의 존재여부 확인
			fileName=uploadFile.getOriginalFilename(); // 업로드한 파일명
//			System.out.println("BC updateBoard 로그7"+fileName);
			uploadFile.transferTo(new File("/Users/yeoneunju/Desktop/0607/workspace/spring0926/src/main/webapp/images/"+fileName));
		}
		else {
			fileName=bVO.getFileName();
			System.out.println("BC updateBoard 로그8"+fileName);
		}
//		System.out.println("BC updateBoard에서 찍은 로그1 :"+bVO.getTitle());
//		System.out.println("BC updateBoard에서 찍은 로그2 :"+bVO.getFileName());
//		System.out.println("BC updateBoard에서 찍은 로그3 :"+bVO.getContent());
		bVO.setFileName(fileName);
//		System.out.println("BC updateBoard 로그10 :"+bVO.getContent());
		boardService.updateBoard(bVO);
//		System.out.println("BC updateBoard에서 찍은 로그4 :"+bVO.getTitle());
//		System.out.println("BC updateBoard에서 찍은 로그5 :"+bVO.getFileName());
//		System.out.println("BC updateBoard에서 찍은 로그6 :"+bVO.getContent());
		return "redirect:main.do";
	}
	
	@RequestMapping("deleteBoard.do")
	public String deleteBoard(@ModelAttribute("data")BoardVO bVO, MemberVO mVO, ReplyVO rVO, ReportVO pVO, HttpSession session) {
		System.out.println("BC deleteBoard에서 찍은 로그3 : "+bVO);
		System.out.println("BC deleteBoard에서 찍은 로그1 : "+session.getAttribute("member"));
		mVO=(MemberVO) session.getAttribute("member");
		if(mVO.getMid().equals("admin")) { // 관리자일 때 신고글 삭제 후, 신고글 리스트로 와야함
			System.out.println("BC deleteBoard에서 찍은 로그2 : "+mVO.getMid());
			//이제 이상한 글 쓴 애한테 경고를 줄 로직
			mVO.setMid(bVO.getMid());
			System.out.println("BC deleteBoard에서 찍은 로그4 : "+mVO.getMid());
			memberService.updatePenalty(mVO);
			
			rVO.setRbid(bVO.getBid());
			replyService.deleteReply_B(rVO); // bid에 달린 댓글들을 bid로 삭제함
			System.out.println("BC deleteBoard에서 찍은 로그4 : "+rVO.getRbid());
			
			boardService.deleteBoard(bVO);
			return "redirect:reportBoardList.do";
		}
		else {
			// 글 작성자가 글을 삭제하면 메인으로 와야함
			rVO.setRbid(bVO.getBid());
			replyService.deleteReply_B(rVO); // 댓글먼저 삭제
			boardService.deleteBoard(bVO);
			return "redirect:main.do";
		}
	}
	
	@RequestMapping("list.do") // 내가 쓴 글
	public String listBoard(BoardVO bVO,MemberVO mVO,HttpSession session,Model model) {
		mVO=(MemberVO) session.getAttribute("member");
		System.out.println("BC listBoard에서 찍은 로그1 : "+mVO);
		bVO.setMid(mVO.getMid());
		if(bVO.getSearchCondition()==null) {
			bVO.setSearchCondition("MID");
			if(bVO.getSearchCondition()=="TITLE") {
				bVO.getSearchCondition();
			} else if(bVO.getSearchCondition()=="CONTENT") {
				bVO.getSearchCondition();
			}
		}
		if(bVO.getSearchContent()==null) {
			bVO.setSearchContent(bVO.getMid());
		}
		
		System.out.println("검색조건: "+bVO.getSearchCondition());
		System.out.println("검색어: "+bVO.getSearchContent());
		
		List<BoardVO> datas=boardService.search(bVO);
		model.addAttribute("datas", datas);
		
		return "listBoard.jsp";
	}
}
