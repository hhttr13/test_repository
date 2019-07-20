package com.spring.myapp.replytest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.myapp.commons.paging.Criteria;
import com.spring.myapp.reply.model.ReplyVO;
import com.spring.myapp.reply.repository.IReplyDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:/spring/mvc-config.xml"})
public class ReplyDAOTest {
	
	@Autowired
	private IReplyDAO dao;
	/*
	@Test
	public void testReplyInsert() throws Exception{
		for(int i=1; i<=300; i++) {
			ReplyVO reply = new ReplyVO();
			reply.setBoardNo(2997);
			reply.setReplyText("댓글 "+i);
			reply.setReplyWriter("왈왈이 "+i);
			
			dao.insert(reply);
		}
		System.out.println("댓글 등록 성공");*/
	/*@Test
	public void replyListTest() throws Exception{
		for(ReplyVO vo: dao.list(2997)) {
			if(vo.getReplyNo() == 251)
				break;
			System.out.println(vo);
		}
	}*/
	
	//2997번의 99번 댓글을 수정
	/*@Test
	public void replyUpdateTest() throws Exception{
		ReplyVO reply = new ReplyVO();
		reply.setBoardNo(2997);
		reply.setReplyNo(99);
		reply.setReplyText("곰돌이");
		dao.update(reply);
	}*/
	//2997번의 250번 댓글을 삭제
	/*@Test
	public void replyDeleteTest() throws Exception{
		ReplyVO reply = new ReplyVO();
		reply.setBoardNo(2997);
		dao.delete(250);
	}*/
	
	//댓글 페이징 테스트
	@Test
	public void pagintTest() throws Exception{
		Criteria cri = new Criteria();
		dao.listPaging(cri, 2997);
		
		cri.setPage(2);
		cri.setCountPerPage(20);
		
		for(ReplyVO reply: dao.listPaging(cri, 2997)) {
			System.out.println(reply);
			
		}
	}
	
	
	
	
	
	
}
