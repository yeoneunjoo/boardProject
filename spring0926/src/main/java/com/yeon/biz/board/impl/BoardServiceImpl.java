package com.yeon.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeon.biz.board.BoardService;
import com.yeon.biz.board.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

//	@Autowired
//	private BoardDAO boardDAO;
	
//	@Autowired
//	private BoardDAO2 boardDAO;
	
	@Autowired
	private BoardDAO3 boardDAO;
	
	@Override
	public void insertBoard(BoardVO vo) {
		boardDAO.insertBoard(vo);
	}

	@Override
	public void updateBoard(BoardVO vo) {
		boardDAO.updateBoard(vo);
	}

	@Override
	public void deleteBoard(BoardVO vo) {
		boardDAO.deleteBoard(vo);
	}
	
	@Override
	public void deleteBoard_M(BoardVO vo) {
		boardDAO.deleteBoard_M(vo);
	}

	@Override
	public BoardVO selectOneBoard(BoardVO vo) {
		//boardDAO.updateViews(vo);
		return boardDAO.selectOneBoard(vo);
	}

	@Override
	public List<BoardVO> selectAllBoard(BoardVO vo) {
		return boardDAO.selectAllBoard(vo);
	}

	@Override
	public List<BoardVO>search(BoardVO vo) {
		return boardDAO.selectAllBoard(vo); // 수정해야함
	}

	@Override
	public void deleteBoardReportforDM(BoardVO vo) {
		boardDAO.deleteBoardReportforDM(vo);
		
	}

//	@Override
//	public void updateViews(BoardVO vo) {
//		boardDAO.updateViews(vo);
//		
//	}

	
}
