package com.spring.myapp.boardtest;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.myapp.board.model.BoardVO;
import com.spring.myapp.board.repository.IBoardDAO;
import com.spring.myapp.commons.paging.SearchCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:/spring/mvc-config.xml"})
public class BoardSearchTest {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardSearchTest.class);

	@Inject
	private IBoardDAO boardDAO;
	
	@Test
	public void titleSearchTest() throws Exception {
		//제목으로 검색 테스트
		SearchCriteria scri = new SearchCriteria();
		scri.setCondition("title");
		scri.setKeyword("30");
		
		logger.info("====================================================");
		logger.info("검색 후 게시물 수: "+boardDAO.countSearchedArticles(scri)+"개");
		for( BoardVO article : boardDAO.listSearch(scri)) {
			logger.info(article.toString());
		}
		logger.info("====================================================");
		
	}
	
	
}
