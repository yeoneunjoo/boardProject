package com.yeon.biz.report;

public class ReportReplyJoinVO {
	private int rid; // 댓글의 PK
	private int rbid; // 댓글과 게시글을 조인해줄 Board 테이블의 PK 
	private String rmid; // 댓글과 작성자를 조인해줄 Member 테이블의 PK
	private String rname; // 댓글작성자의 닉네임 
	private String rcontent; // 댓글 내용
	private int rreport; // 신고된 여부
	private int rblind; // 관리자의 블라이드 처리 여부
	
	private int sid; // 신고pk
	private int bid; // 게시글pk
	private int ridR; // 댓글pk
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getRbid() {
		return rbid;
	}
	public void setRbid(int rbid) {
		this.rbid = rbid;
	}
	public String getRmid() {
		return rmid;
	}
	public void setRmid(String rmid) {
		this.rmid = rmid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getRcontent() {
		return rcontent;
	}
	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}
	public int getRreport() {
		return rreport;
	}
	public void setRreport(int rreport) {
		this.rreport = rreport;
	}
	public int getRblind() {
		return rblind;
	}
	public void setRblind(int rblind) {
		this.rblind = rblind;
	}
	
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
	public int getRidR() {
		return ridR;
	}
	public void setRidR(int ridR) {
		this.ridR = ridR;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ReplyVO [rid=" + rid + ", rbid=" + rbid + ", rmid=" + rmid + ", rname=" + rname + 
				", rcontent=" + rcontent + ", rreport="+rreport+", rblind: "+ rblind + "], ReportVO 로그 sid: "+sid+", bid: "+bid+", ridR: "+ridR;
	}
	
	
	
}
