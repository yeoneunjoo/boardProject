package com.yeon.biz.report.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeon.biz.report.ReportVO;

@Repository("ReportDAO")
public class ReportDAO3 {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertReport(ReportVO pVO) { 
		mybatis.insert("ReportDAO.insertReport", pVO);
	}

	public void deleteReport(ReportVO pVO) {
		mybatis.delete("ReportDAO.deleteReport", pVO);
	}

	public ReportVO selectOneBR(ReportVO pVO) {
		return mybatis.selectOne("ReportDAO.selectOneBR", pVO);
	}
	
	public List<ReportVO> selectAllBR(ReportVO pVO) {
		System.out.println("ReportDAO3 selectAllBR 로그");
		return mybatis.selectList("ReportDAO.selectAllBR", pVO);
	}

	public List<ReportVO> selectAllReplyforDelete(ReportVO pVO) {
		return mybatis.selectList("ReportDAO.selectAllReplyforDelete", pVO);
	}
	
	public List<ReportVO> selectAllReportReply(ReportVO pVO) {
		return mybatis.selectList("ReportDAO.selectAllReportReply", pVO);
	}
	
	public void deleteReportRid(ReportVO pVO) {
		mybatis.delete("ReportDAO.deleteReportRid", pVO);
	}
	
	public void deleteReportBid(ReportVO pVO) {
		mybatis.delete("ReportDAO.deleteReportBid", pVO);
	}
	
//	public void deleteReplyforDM(ReportVO pVO) {
//		mybatis.delete("ReportDAO.deleteReplyforDM", pVO);
//	}
	
}
