package com.spring.myapp.boardtest;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.myapp.board.model.BoardVO;
import com.spring.myapp.board.repository.BoardDAO;
import com.spring.myapp.board.repository.IBoardDAO;
import com.spring.myapp.commons.paging.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:/spring/mvc-config.xml"})
public class BoardDAOTest {

	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);

	@Inject
	private IBoardDAO boardDAO;

	//게시물 등록 테스트
	/*@Test
	public void insertTest() throws Exception {

		for (int i = 1; i <= 3000; i++) {
			BoardVO vo = new BoardVO();
			vo.setTitle(i + "번째 테스트 게시물!!");
			vo.setContent(i + "번째 게시물 내용입니다~~");
			vo.setWriter("user" + i);
			boardDAO.insert(vo);
		}

	}*/

	//게시물 개별 조회 테스트
	/*@Test
	public void selectTest() throws Exception {
		logger.info(boardDAO.getArticle(51).toString() + "\n");
	}*/

	//게시물 수정 테스트 : VO의 세터를 사용하여 수정 내용(글제목, 글내용)을 입력하고
	/*@Test
	public void updateTest() throws Exception {
		BoardVO article = new BoardVO();
		article.setBoardNo(1);
		article.setTitle("글 수정 테스트 제목 !");
		article.setContent("글 수정 테스트 내용~~");
		boardDAO.update(article);
		logger.info(boardDAO.getArticle(1).toString()+"\n");
	}*/

	//게시물 삭제 테스트 : 게시글 번호를 통한 삭제를 확인하세요
	/*@Test
	public void deleteTest() throws Exception {
		boardDAO.delete(9);
	}*/
	
	//모든 게시물 조회 테스트
	/*@Test
	public void selectAllTest() throws Exception{
		List<BoardVO> articles  = boardDAO.getAllArticles();
		for(BoardVO article : articles) {
			logger.info(article.toString());
		}
		
	}*/

	//페이징 테스트
	@Test
	public void testListPaging() throws Exception{
		//List<BoardVO> articles = boardDAO.listPaging(1);
		Criteria cri = new Criteria();
		cri.setPage(2);
		cri.setCountPerPage(30);
		for(BoardVO article : boardDAO.listPaging(cri)) {
			logger.info(article.toString());
		}
	}
}











