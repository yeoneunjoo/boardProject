package com.yeon.biz.board.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeon.biz.board.BoardVO;
import com.yeon.biz.common.SqlSessionBean;


@Repository("boardDAO")
public class BoardDAO3 {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
//	public BoardDAO3() {
//		// 생성자주입
//		mybatis=SqlSessionBean.getSqlSessionInstance();
//	}
	
	public void insertBoard(BoardVO vo) {
		mybatis.insert("BoardDAO.insertBoard",vo);
//		mybatis.commit();
	}
	public void updateBoard(BoardVO vo) {
		mybatis.update("BoardDAO.updateBoard",vo);
//		mybatis.commit();
	}
	public void deleteBoard(BoardVO vo) { // 글삭제
		mybatis.delete("BoardDAO.deleteBoardReport",vo); // 게시글의 bid를 갖고있는 신고번호 삭제
		mybatis.delete("BoardDAO.deleteBoard",vo); // 게시글 삭제
	}
	
	public BoardVO selectOneBoard(BoardVO vo) {
		mybatis.update("BoardDAO.updateViews",vo);
		return mybatis.selectOne("BoardDAO.selectOneBoard",vo);
	}
	
	public List<BoardVO> selectAllBoard(BoardVO vo) {
		//System.out.println("로그: 마이바티스 쓰는 중");
		return mybatis.selectList("BoardDAO.selectAllBoard",vo);
	}
	
	public void deleteBoard_M(BoardVO vo) {
		mybatis.delete("BoardDAO.deleteBoardReplyforDM", vo);
		mybatis.delete("BoardDAO.deleteBoardReportforDM", vo);
		mybatis.delete("BoardDAO.deleteBoard_M",vo);
		System.out.println("BD3 deleteBoard_M 로그2");
	}
	
	public void updateViews(BoardVO vo) {
		mybatis.update("BoardDAO.updateViews",vo);
	}
	
	public void updateFav(BoardVO vo) {
		mybatis.update("BoardDAO.updateFav",vo);
	}
	
	public void deleteBoardReportforDM(BoardVO vo) {
		mybatis.delete("BoardDAO.deleteBoardReportforDM", vo);
	}
	
	public void deleteBoardReplyforDM(BoardVO vo) {
		mybatis.delete("BoardDAO.deleteBoardReplyforDM", vo);
	}
}
