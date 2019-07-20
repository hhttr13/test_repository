package com.spring.myapp.board.service;

import java.util.List;

import com.spring.myapp.board.model.BoardVO;
import com.spring.myapp.commons.paging.Criteria;
import com.spring.myapp.commons.paging.SearchCriteria;

public interface IBoardService {

	void insert(BoardVO article) throws Exception;
	BoardVO getArticle(int boardNo, boolean flag) throws Exception;
	/*update();*/
	void update(BoardVO article) throws Exception;
	/*delete();*/
	void delete(int boardNo) throws Exception;
	
	List<BoardVO> getAllArticles() throws Exception;
	//페이징 처리
	List<BoardVO> listPaging(Criteria cri) throws Exception;
	//총 게시물 수 가져오기
	int countArticles() throws Exception;
	//동적SQL을 이용한 검색
	List<BoardVO> listSearch(SearchCriteria scri) throws Exception;
	int countSearchArticles(SearchCriteria scri) throws Exception;
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////Depreciated/////////////////////////////////////////////////
	
	//제목으로 검색해서 게시물 불러오기
	List<BoardVO> listSearchByTitle(SearchCriteria scri) throws Exception;
	//제목으로 검색한 총 게시물 수
	int countSearchedArticles(SearchCriteria scri) throws Exception;
	//작성자로 검색해서 게시물 불러오기
	List<BoardVO> listSearchByWriter(SearchCriteria scri) throws Exception;
	//작성자로 검색한 총 게시물 수
	int countSearchedArticlesByWriter(SearchCriteria scri) throws Exception;
}
