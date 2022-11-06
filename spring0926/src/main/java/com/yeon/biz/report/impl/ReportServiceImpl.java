package com.yeon.biz.report.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeon.biz.report.ReportService;
import com.yeon.biz.report.ReportVO;

@Service("reportService") // report에 대한 여러 DAO 처리에 대한 어노테이션
public class ReportServiceImpl implements ReportService {

	@Autowired
	// ReportDAO3 클래스에 있는 메서드들을 reportDAO라는 이름의 객체로 사용할 수 있도록
	//(ReportDAO3가 reportDAO로 일할 수 있도록) 자동 연결해주겠다
	private ReportDAO3 reportDAO;
	
	@Override
	public void insertReport(ReportVO pVO) {
		reportDAO.insertReport(pVO);
	}

	@Override
	public void deleteReport(ReportVO pVO) {
		reportDAO.deleteReport(pVO);
	}

	@Override
	public ReportVO selectOneBR(ReportVO pVO) {
		return reportDAO.selectOneBR(pVO);
	}

	@Override
	public List<ReportVO> selectAllBR(ReportVO pVO) {
		System.out.println("RSImpl selectAllBR 로그");
		return reportDAO.selectAllBR(pVO);
	}
	
	@Override
	public List<ReportVO> selectAllReplyforDelete(ReportVO pVO) {
		return reportDAO.selectAllReplyforDelete(pVO);
	}

	@Override
	public List<ReportVO> selectAllReportReply(ReportVO pVO) {
		return reportDAO.selectAllReportReply(pVO);
	}

	@Override
	public void deleteReportRid(ReportVO pVO) {
		reportDAO.deleteReportRid(pVO);
	}

	@Override
	public void deleteReportBid(ReportVO pVO) {
		reportDAO.deleteReportBid(pVO);
	}

//	@Override
//	public void deleteReplyforDM(ReportVO pVO) {
//		reportDAO.deleteReplyforDM(pVO);
//	}

}
