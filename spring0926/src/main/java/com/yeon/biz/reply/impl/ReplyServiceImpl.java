package com.yeon.biz.reply.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeon.biz.reply.ReplyService;
import com.yeon.biz.reply.ReplyVO;
import com.yeon.biz.report.ReportVO;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyDAO3 replyDAO;
	
	@Override
	public void insertReply(ReplyVO rVO) {
		replyDAO.insertReply(rVO);
	}

	@Override
	public void updateReply(ReplyVO rVO) {
		replyDAO.updateReply(rVO);
	}
	
	@Override
	public void updateRblind(ReplyVO rVO) {
		replyDAO.updateRblind(rVO);
	}

	@Override
	public void deleteReply(ReplyVO rVO) {
		replyDAO.deleteReply(rVO);
	}

	@Override
	public ReplyVO selectOneReply(ReplyVO rVO) {
		return replyDAO.selectOneReply(rVO);
	}
	
	@Override
	public List<ReplyVO> selectAllReply(ReplyVO rVO) {
		return replyDAO.selectAll(rVO);
	}

	@Override
	public List<ReplyVO> search(ReplyVO rVO) {
		return replyDAO.selectAll(rVO);
	}

	@Override
	public void deleteReply_M(ReplyVO rVO) {
		replyDAO.deleteReply_M(rVO);
	}

	@Override
	public void deleteReply_B(ReplyVO rVO) {
		replyDAO.deleteReply_B(rVO);
		
	}

	@Override
	public ReplyVO selectOneReplyforReport(ReplyVO rVO) {
		System.out.println("악악,서비스임플리");
		return replyDAO.selectOneReplyforReport(rVO);
	}

	@Override
	public void updateRreport(ReplyVO rVO) {
		replyDAO.updateRreport(rVO);
	}
	
	@Override
	public void resetRreport(ReplyVO rVO) {
		replyDAO.resetRreport(rVO);
	}
	
	@Override
	public void deleteReplyforDM(ReplyVO rVO) {
		replyDAO.deleteReplyforDM(rVO);
	}
}
