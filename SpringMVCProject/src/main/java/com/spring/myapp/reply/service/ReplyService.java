package com.spring.myapp.reply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.myapp.board.repository.IBoardDAO;
import com.spring.myapp.commons.paging.Criteria;
import com.spring.myapp.reply.model.ReplyVO;
import com.spring.myapp.reply.repository.IReplyDAO;

@Service
public class ReplyService implements IReplyService{
	
	@Autowired
	private IReplyDAO replyDao;
	
	@Autowired
	private IBoardDAO boardDao;

	@Override
	public List<ReplyVO> list(int boardNo) throws Exception {
		return replyDao.list(boardNo);
	}

	@Transactional
	@Override
	public void insert(ReplyVO reply) throws Exception {
		boardDao.updateReplyCnt(reply.getBoardNo(), 1);
		replyDao.insert(reply);
	}

	@Override
	public void update(ReplyVO reply) throws Exception {
		replyDao.update(reply);
	}

	@Transactional
	@Override
	public void delete(int replyNo, int boardNo) throws Exception {
		boardDao.updateReplyCnt(boardNo, -1);
		replyDao.delete(replyNo);
	}

	@Override
	public List<ReplyVO> listPaging(Criteria cri, int boardNo) throws Exception {
		return replyDao.listPaging(cri, boardNo);
	}

	@Override
	public int countReplies(int boardNo) throws Exception {
		return replyDao.countReplies(boardNo);
	}
}
