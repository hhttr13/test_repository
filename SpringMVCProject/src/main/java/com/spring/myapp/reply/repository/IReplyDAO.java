package com.spring.myapp.reply.repository;

import java.util.List;

import com.spring.myapp.commons.paging.Criteria;
import com.spring.myapp.reply.model.ReplyVO;

public interface IReplyDAO {
	//댓글 목록 불러오기
	List<ReplyVO> list(int boardNo) throws Exception;
	//댓글 쓰기 기능
	void insert(ReplyVO reply) throws Exception;
	//댓글 수정 기능
	void update(ReplyVO reply) throws Exception;
	//댓글 삭제 기능
	void delete(int replyNo) throws Exception;
	//페이징처리된 댓글목록 불러오기
	List<ReplyVO> listPaging(Criteria cri, int boardNo) throws Exception;
	//특정 게시물의 총 댓글 갯수 가져오기
	int countReplies(int boardNo) throws Exception;
	//특정 게시물의 모든 댓글 지우기
	void deleteAllReply(int boardNo) throws Exception;
}
