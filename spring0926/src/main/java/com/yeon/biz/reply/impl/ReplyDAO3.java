package com.yeon.biz.reply.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeon.biz.reply.ReplyVO;
import com.yeon.biz.report.ReportVO;

@Repository("ReplyDAO")
public class ReplyDAO3 {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	
	void insertReply(ReplyVO rVO) {
		// 댓글 등록
		mybatis.insert("ReplyDAO.insertReply", rVO);
	}
	void updateReply(ReplyVO rVO) {
		// 댓글 수정
		mybatis.update("ReplyDAO.updateReply", rVO);
	}
	void updateRblind(ReplyVO rVO) {
		// 관리자가 블라인드 처리를 완료했을 시, 블라인드칼럼update
		mybatis.update("ReplyDAO.updateRblind", rVO);
	}
	void updateRreport(ReplyVO rVO) {
		// 관리자가 블라인드 처리를 완료했을 시, 블라인드칼럼update
		mybatis.update("ReplyDAO.updateRreport", rVO);
	}
	void resetRreport(ReplyVO rVO) {
		// 관리자가 블라인드 처리를 완료했을 시, 블라인드칼럼update
		mybatis.update("ReplyDAO.resetRreport", rVO);
	}
	
	void deleteReply(ReplyVO rVO) {
		mybatis.delete("ReplyDAO.deleteReplyReport",rVO);// 신고된 댓글의 신고번호 삭제
		mybatis.delete("ReplyDAO.deleteReply", rVO); // 댓글 삭제
	}
	
	ReplyVO selectOneReply(ReplyVO rVO) {
		return mybatis.selectOne("ReplyDAO.selectOneReply", rVO);
	}
	
	List<ReplyVO> selectAll(ReplyVO rVO) {
		// 댓글불러오기(?)
		return mybatis.selectList("ReplyDAO.selectAllReply", rVO);
	}
	
	void deleteReply_M(ReplyVO rVO) {
		// 회원 탈퇴시 댓글 삭제
		mybatis.delete("ReplyDAO.deleteReplyforDM", rVO); // 신고번호 삭제 
		mybatis.delete("ReplyDAO.deleteReply_M", rVO); // 댓글 삭제
	}
	
	void deleteReply_B(ReplyVO rVO) {
		// 글삭 시 댓삭
		mybatis.delete("ReplyDAO.deleteReply_B", rVO);
	}
	
	ReplyVO selectOneReplyforReport(ReplyVO rVO) {
		return mybatis.selectOne("ReplyDAO.selectOneReplyforReport",rVO);
	}
	
	public void deleteReplyforDM(ReplyVO rVO) {
		mybatis.delete("ReplyDAO.deleteReplyforDM", rVO);
	}
	
//	List<ReplyVO> search(ReplyVO rVO) {
//		// 댓글 검색
//		return mybatis.selectList("ReplyDAO.selectAll", rVO);
//	} // 일단 보면서 제거
	
}
