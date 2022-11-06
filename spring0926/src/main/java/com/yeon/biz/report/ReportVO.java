package com.yeon.biz.report;

public class ReportVO {
	private int sid; // 신고pk
	private int bid; // 게시글pk
	private int rid; // 댓글pk
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ReportVO 로그 sid: "+sid+", bid: "+bid+", rid: "+rid;
	}
	
	
}
