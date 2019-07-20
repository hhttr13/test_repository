package com.spring.myapp.board.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.myapp.board.model.BoardVO;
import com.spring.myapp.board.repository.IBoardDAO;
import com.spring.myapp.commons.paging.Criteria;
import com.spring.myapp.commons.paging.SearchCriteria;
import com.spring.myapp.reply.repository.IReplyDAO;

@Service
public class BoardService implements IBoardService {
	
	@Autowired
	private IBoardDAO dao;
	
	@Autowired
	private IReplyDAO replyDao;

	@Override
	public void insert(BoardVO article) throws Exception {
		dao.insert(article);
	}

	@Transactional
	@Override
	public BoardVO getArticle(int boardNo, boolean flag) throws Exception {
		BoardVO article = dao.getArticle(boardNo);
		String content = article.getContent().replace("\n", "<br>").replace("\u0020", "&nbsp;");
		if(flag == true) {
		article.setContent(content);
		}
		dao.updateViewCnt(boardNo);
		return article;
	}

	@Override
	public void update(BoardVO article) throws Exception {
		dao.update(article);
	}

	@Transactional
	@Override
	public void delete(int boardNo) throws Exception {
		replyDao.deleteAllReply(boardNo);
		dao.delete(boardNo);
	}

	@Override
	public List<BoardVO> getAllArticles() throws Exception {
		return dao.getAllArticles();
	}

	@Override
	public List<BoardVO> listPaging(Criteria cri) throws Exception {
		
		return dao.listPaging(cri);
	}

	@Override
	public int countArticles() throws Exception {
		return dao.countArticles();
	}

	@Override
	public List<BoardVO> listSearch(SearchCriteria scri) throws Exception {
		List<BoardVO> list = dao.listSearch(scri);
		//1일 이내에 쓰여진 게시물에만 new를 붙이는 처리
		for(BoardVO boardVO : list) {
			//게시물 목록을 요청한 시간 - 게시물 등록시간 < 1일 => newMark <- true
			long now = System.currentTimeMillis();//현재시간 읽기
			Date date = boardVO.getRegDate();//게시물 등록시간
			long regDate = date.getTime();
			//1일의 밀리초
			long oneDayMillis = 60*60*24*1000;
			if(now - regDate <= oneDayMillis) {
				boardVO.setNewMark(true);
			}
			
		}
		return list;
	}
	
	@Override
	public int countSearchArticles(SearchCriteria scri) throws Exception {
		return dao.countSearchArticles(scri);
	}
	
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////Depreciated/////////////////////////////////////////////////
	
	@Override
	public List<BoardVO> listSearchByTitle(SearchCriteria scri) throws Exception {
		return dao.listSearchByTitle(scri);
	}

	@Override
	public int countSearchedArticles(SearchCriteria scri) throws Exception {
		return dao.countSearchedArticles(scri);
	}

	@Override
	public List<BoardVO> listSearchByWriter(SearchCriteria scri) throws Exception {
		return dao.listSearchByWriter(scri);
	}

	@Override
	public int countSearchedArticlesByWriter(SearchCriteria scri) throws Exception {
		return dao.countSearchedArticlesByWriter(scri);
	}


}
