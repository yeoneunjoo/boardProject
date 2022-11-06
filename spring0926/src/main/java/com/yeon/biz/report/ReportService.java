package com.yeon.biz.report;

import java.util.List;

public interface ReportService {
	// crud create read update(신고를 업데이트할 필요는 없으니까 구현x) delete, crd만
	void insertReport(ReportVO pVO); // 신고하기 insert
	void deleteReport(ReportVO pVO); // 들어온 신고를 처리했으면 신고를 삭제 delete
	void deleteReportRid(ReportVO pVO); // rid값으로 신고삭제
	void deleteReportBid(ReportVO pVO); // bid값으로 신고삭제
//	void deleteReplyforDM(ReportVO pVO);
	ReportVO selectOneBR(ReportVO pVO); // 게시글 신고 중 하나 선택하기 selectOne
	List<ReportVO> selectAllBR(ReportVO pVO); // 게시글 신고들 전부 출력 selectAll
	List<ReportVO> selectAllReplyforDelete(ReportVO pVO);
	List<ReportVO> selectAllReportReply(ReportVO pVO); // 댓글 신고들 전부 출력 selectAll
}
