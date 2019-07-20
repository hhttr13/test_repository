package com.spring.myapp.board.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.myapp.board.model.BoardVO;
import com.spring.myapp.commons.paging.Criteria;
import com.spring.myapp.commons.paging.SearchCriteria;


//자동 빈 등록 아노테이션 : @Component, @Controller, @Service, @Repository
@Repository
public class BoardDAO implements IBoardDAO {
	
	private final SqlSession sqlSession;
	
	private static final String NAMESPACE = "BoardMapper";
	
	//의존성 자동주입 아노테이션: @Autowired, @Inject, @Resources
	@Autowired
	public BoardDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void insert(BoardVO article) throws Exception {
		sqlSession.insert(NAMESPACE+".insert", article);
	}

	@Override
	public BoardVO getArticle(int boardNo) throws Exception {
		System.out.println("게시글 번호: " + boardNo);
		return sqlSession.selectOne(NAMESPACE+".getArticle", boardNo);
	}

	@Override
	public void update(BoardVO article) throws Exception {
		sqlSession.update(NAMESPACE+".update", article);
	}

	@Override
	public void delete(int boardNo) throws Exception {
		sqlSession.delete("BoardMapper.delete", boardNo);
	}

	@Override
	public List<BoardVO> getAllArticles() throws Exception {
		return sqlSession.selectList(NAMESPACE+".getAllArticles");
	}

	@Override
	public List<BoardVO> listPaging(Criteria cri) throws Exception {
		//Page 변수에 	1이 저장되어있다면?? LIMIT절의 시작값은 0
		//			2가 저장되어있다면?? LIMIT절의 시작값은 10
		//page = (page -1)*10;
		
		
		return sqlSession.selectList(NAMESPACE+".listPaging", cri);
	}

	@Override
	public int countArticles() throws Exception {
		return sqlSession.selectOne("BoardMapper.countArticles");
	}
	
	@Override
	public List<BoardVO> listSearch(SearchCriteria scri) throws Exception {
		
		return sqlSession.selectList(NAMESPACE+".listSearch",scri);
	}
	
	@Override
	public int countSearchArticles(SearchCriteria scri) throws Exception {
		return sqlSession.selectOne(NAMESPACE+".countSearchArticles",scri);
	}
	
	@Override
	public void updateViewCnt(int boardNo) throws Exception {
		sqlSession.update(NAMESPACE+".updateViewCnt",boardNo);
	}
	
	@Override
	public void updateReplyCnt(int boardNo, int count) throws Exception {
		Map<String, Object> datas = new HashMap<>();
		datas.put("boardNo", boardNo);
		datas.put("count", count);
		sqlSession.update(NAMESPACE+".updateReplyCnt",datas);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////Depreciated/////////////////////////////////////////////////
	
	@Override
	public List<BoardVO> listSearchByTitle(SearchCriteria scri) throws Exception {
		return sqlSession.selectList(NAMESPACE+".listSearchByTitle", scri);
	}

	@Override
	public int countSearchedArticles(SearchCriteria scri) throws Exception {
		return sqlSession.selectOne(NAMESPACE+".countSearchedArticles",scri);
	}

	@Override
	public List<BoardVO> listSearchByWriter(SearchCriteria scri) throws Exception {
		return sqlSession.selectList(NAMESPACE+".listSearchByWriter",scri);
	}

	@Override
	public int countSearchedArticlesByWriter(SearchCriteria scri) throws Exception {
		return sqlSession.selectOne(NAMESPACE+".countSearchedArticlesByWriter",scri);
	}

	



	
	

}






