package com.spring.myapp.board.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.myapp.board.model.BoardVO;
import com.spring.myapp.board.service.IBoardService;
import com.spring.myapp.commons.paging.PageCreator;
import com.spring.myapp.commons.paging.SearchCriteria;

@Controller
@RequestMapping("/board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private IBoardService service;
	
	//게시글 목록요청 처리 메서드
	//페이징 처리 전
	/*@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) throws Exception{
		System.out.println("게시물 목록 페이지 열람요청이 들어옴!");

		logger.info("/board/list request!");
		
		model.addAttribute("articles", service.getAllArticles());
		
		return "board/list";
	}*/
	
	//페이징 처리 후 게시글 목록 요청
	/*@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model, Criteria cri) throws Exception{
		logger.info("/board/list: GET요청 발생");
		PageCreator pc = new PageCreator();
		pc.setCriteria(cri);
		pc.setArticleTotalCount(service.countArticles());
		model.addAttribute("articles", service.listPaging(cri));
		model.addAttribute("pageCreator", pc);
		
		return "board/list";
	}*/
	//insert 폼
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write(HttpSession session) {
		if(session.getAttribute("login") == null) {
			return "redirect:../";
		}
		
		logger.info("method: get > /board/write");
		return "board/write";
	}
	//insert 실행
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(BoardVO article, RedirectAttributes redirectAttr) throws Exception {
		logger.info("method: post > /board/write");
		logger.info(article.toString());
		service.insert(article);
		redirectAttr.addFlashAttribute("message", "regSuccess");
		return "redirect:../board/list";
	}
	//게시글 내용 확인 content
	@RequestMapping(value="/content", method=RequestMethod.GET)
	public String content(Model model,@RequestParam("boardNo") int boardNo, 
			@ModelAttribute("criteria") SearchCriteria cri) throws Exception {
	    logger.info("method: get > /board/content");
		model.addAttribute("article", service.getArticle(boardNo, true));
		return "/board/content";
	}
	//update 폼
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String update(Model model, @RequestParam("boardNo")int boardNo,
			@ModelAttribute("criteria")SearchCriteria cri) throws Exception {
		logger.info("method: get > /board/modify");
		model.addAttribute("article", service.getArticle(boardNo, false));
		return "board/modify";
	}
	//update 실행
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String update(BoardVO article, RedirectAttributes redirectAttr, 
			SearchCriteria cri) throws Exception {
		logger.info("method: post > /board/modify");
		service.update(article);
		redirectAttr.addAttribute("page",cri.getPage());
		redirectAttr.addAttribute("countPerPage", cri.getCountPerPage());
		redirectAttr.addAttribute("condition", cri.getCondition());
		redirectAttr.addAttribute("keyword", cri.getKeyword());
		redirectAttr.addFlashAttribute("message", "modSuccess");
		return "redirect:../board/list";
	}
	//delete 실행
	@RequestMapping(value="/delete")
	public String delete(@RequestParam("boardNo")int boardNo, RedirectAttributes redirectAttr,
			SearchCriteria cri) throws Exception {
		logger.info("/board/delete request!");
		service.delete(boardNo);
		redirectAttr.addAttribute("page",cri.getPage());
		redirectAttr.addAttribute("countPerPage", cri.getCountPerPage());
		redirectAttr.addAttribute("condition", cri.getCondition());
		redirectAttr.addAttribute("keyword", cri.getKeyword());
		redirectAttr.addFlashAttribute("message", "delSuccess");
		
		return "redirect:../board/list";
	}
	//검색 처리 후 게시글 목록 불러오기
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model, 
			@ModelAttribute("scriteria")SearchCriteria scri) throws Exception{
		logger.info("/board/list: GET요청 발생");
		PageCreator pc = new PageCreator();
		pc.setCriteria(scri);	//SearchCriteria가 Criteria의 자식 객체이기 때문에 자동 형변환 가능.;
		//String condition = scri.getCondition();
		//동적 SQL사용 안하고 제목, 작성자로 검색
		/*if(condition.equals("title")) {
			model.addAttribute("articles", service.listSearchByTitle(scri));
			pc.setArticleTotalCount(service.countSearchedArticles(scri));
		}else if(condition.equals("writer")){
			model.addAttribute("articles", service.listSearchByWriter(scri));
			pc.setArticleTotalCount(service.countSearchedArticlesByWriter(scri));
		}else {
			model.addAttribute("articles", service.listPaging(scri));
			pc.setArticleTotalCount(service.countArticles());
		}*/
		
		//동적 SQL사용
		model.addAttribute("articles",service.listSearch(scri));
		pc.setArticleTotalCount(service.countSearchArticles(scri));
		
		model.addAttribute("pageCreator", pc);
		
		return "board/list";
	}
}
