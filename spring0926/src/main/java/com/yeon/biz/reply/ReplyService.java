package com.yeon.biz.reply;

import java.util.List;

import com.yeon.biz.report.ReportVO;


public interface ReplyService {
	void insertReply(ReplyVO rVO); // 댓글 등록
	void updateReply(ReplyVO rVO); // 댓글 수정
	void updateRblind(ReplyVO rVO); // 관리자가 블라인드 처리를 완료했을 시, 블라인드칼럼update
	void updateRreport(ReplyVO rVO); // 댓글이 신고되면 rreport update
	void resetRreport(ReplyVO rVO);
	void deleteReply(ReplyVO rVO); // rid로 댓삭
	void deleteReply_M(ReplyVO rVO); // 댓글 작성자의 rmid값으로 댓삭
	void deleteReply_B(ReplyVO rVO); // 댓글이 작성된 bid값으로 댓삭
	void deleteReplyforDM(ReplyVO rVO);
	ReplyVO selectOneReply(ReplyVO rVO); // 댓글 하나만 선택해서 확인
	ReplyVO selectOneReplyforReport(ReplyVO rVO);
	List<ReplyVO> selectAllReply(ReplyVO rVO); // 댓글 전부 출력
	List<ReplyVO> search(ReplyVO rVO); // 댓글 검색
}
