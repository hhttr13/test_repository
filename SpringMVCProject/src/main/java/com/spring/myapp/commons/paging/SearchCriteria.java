package com.spring.myapp.commons.paging;

public class SearchCriteria extends Criteria{

	private String condition; 	//검색조건
	private String keyword;		//검색어
	
	//생성자를 따로 호출하지 않는경우 부모 클래스의 생성자를 그대로 가져온다.
	//기본 생성자에 super(); 메서드가 숨어있기 때문.
	public SearchCriteria() {
		super();
		this.keyword = "";
		this.condition = "";
	}
	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Override
	public String toString() {
		return "SearchCriteria [condition=" + condition + ", keyword=" + keyword + "]";
	}
	
	
}
