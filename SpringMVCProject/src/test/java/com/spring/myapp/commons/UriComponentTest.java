package com.spring.myapp.commons;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class UriComponentTest {
	
	private static final Logger logger = LoggerFactory.getLogger(UriComponentTest.class);
	
	@Test
	public void testUriComp() throws Exception{
		
		UriComponents ucp = UriComponentsBuilder.newInstance()
							.path("/board/content")
							.queryParam("boardNo",33)
							.queryParam("page",25)
							.queryParam("countPerPage", 20)
							.build();
		
		logger.info(ucp.toString());
		
	}
	
	@Test
	public void testUriComp2() throws Exception{
		
		UriComponents ucp = UriComponentsBuilder.newInstance()
							.path("/{link1}/{link2}")
							.queryParam("boardNo",33)
							.queryParam("page",25)
							.queryParam("countPerPage", 20)
							.build().expand("board","write")
							//{link1}위치에 board가, {link2}위치에 write가 삽입된다.
							.encode();
							
	
		//자바에서의 ...은 배열을 의미 ex) Object... arr=> Object[] arr를 의미
		// ... => 가변 매개변수
		
		logger.info(ucp.toString());
		
		/*int result = add(new int[] {1,2,3,4,5});
		System.out.println("result: "+result);
		int result2 = add(1,2,3,4,5,6,7);
		System.out.println("result2: "+result2);*/
		
	}
	
	private int add(int ... nums) {
		int total=0;
		for(int n: nums) {
			total = total + n;
		}
		return total;
	}

}
