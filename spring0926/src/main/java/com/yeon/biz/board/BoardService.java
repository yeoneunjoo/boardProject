package com.yeon.biz.board;

import java.util.List;

public interface BoardService {
	void insertBoard(BoardVO vo);
	void updateBoard(BoardVO vo);
	void deleteBoard(BoardVO vo);
	void deleteBoard_M(BoardVO vo);
	void deleteBoardReportforDM(BoardVO vo);
	BoardVO selectOneBoard(BoardVO vo);
	List<BoardVO> selectAllBoard(BoardVO vo);
	List<BoardVO> search(BoardVO vo);
//	void updateViews(BoardVO vo);
}
