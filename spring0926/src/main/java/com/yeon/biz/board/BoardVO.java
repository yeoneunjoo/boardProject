package com.yeon.biz.board;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO {
	private int bid; // 게시글 번호
	private String title; // 글제목
	private String writer; // 작성자 닉네임
	private String mid; // 작성자 아이디(MemberVO의 PK)
	private String content; // 글내용
	private int views; // 조회수
	private String regdate; // 작성날짜
	private String searchCondition; // 검색조건, DB에 저장x
	private String searchContent; // 검색내용, DB에 저장x
	private MultipartFile uploadFile; // 업로드한 파일을 저장하기 위해 생성, DB에 저장x
	private String fileName; // 업로드한 파일의 이름을 DB에 저장하기 위해 사용
	
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchContent() {
		return searchContent;
	}
	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "BoardVO [bid=" + bid + ", title=" + title + ", writer=" + writer + ", mid=" + mid + ", content=" + content + ", regdate=" + regdate + ", uploadFile="+uploadFile+"]"+", fileName="+fileName+"]";
	}
	
}
