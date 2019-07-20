package com.spring.myapp.board.model;

import java.util.Date;

public class BoardVO {
	
	//사용할 DATABASE TABLE 컬럼과 1:1로 매핑되는
	//필드들을 캡슐화를 통해 프로퍼티화 시킨다.
	private int boardNo;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	private int viewCnt;
	private int replyCnt;
	private boolean newMark;	//신규 게시물에 new를 띄울지 말지 결정
	
	
	public int getReplyCnt() {
		if(replyCnt <0) {
			replyCnt = 0;
		}
		return replyCnt;
	}

	public void setReplyCnt(int replyCnt) {
		if(replyCnt <0) {
			replyCnt = 0;
		}
		this.replyCnt = replyCnt;
	}

	public boolean isNewMark() {
		return newMark;
	}
	
	public void setNewMark(boolean newMark) {
		this.newMark = newMark;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	
	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", regDate=" + regDate + ", viewCnt=" + viewCnt + "]";
	}
	
	

}






