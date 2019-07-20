<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div id="reply-box">

</div>
<p>
# 댓글 작성자:<br> <input id="newReplyWriter" type="text" name="replyWriter"> <br>
# 댓글 내용:<br> <textarea id="newReplyText" rows="3" name="replyText"></textarea><br>
<input id="replyAddBtn" type="button" value="댓글작성">
</p>

<jsp:include page="../include/plugin-js.jsp"/>
<script type="text/javascript">
$(document).ready(function(){
	
	let boardNo = 2997; //게시글 번호
	/*
	# REST방식은 클라이언트와 서버간에 JSON데이터를 주고받기 때문에
	서버가 전달해준 JSON데이터를 클라이언트 측에서 받을 수 있어야 합니다.
	# JQuery 라이브러리가 제공하는 $.getJSON()이라는 함수를 사용하면
	서버가 넘겨준 JSON데이터를 받아낼 수 있습니다(GET방식만 전달가능).
	# getJSON()함수의 첫번째 매개값으로 요청 URI, 두번째 매개값으로
	받은 JSON데이터를 사용하는 익명함수를 정의합니다.
	*/
	//$.getJSON("/replies/all/"+boardNo, function(data){
		/*for(let i=0; i<10; i++){
			console.log((i+1)+"번쨰 댓글 내용: "+data[i].replyText);
			console.log((i+1)+"번쨰 댓글 작성자: "+data[i].replyWriter);
			console.log("==================================");
		}*/
		//let str = "";
		//this는 $()안의 data를 가리킨다.
		//controller의 return형이 List<ReplyVO>이기 때문에 객체안의 내용들을 가져올 수 있음. (getJSON에서는 java의 List를 배열 형태로 가져올 수 있음.)
		/*$(data).each(function() {
		str = str + "<p>댓글 내용: "+this.replyText +"<br>"+"댓글번호: "+this.replyNo+"<br>"
					+"댓글 작성자: "+this.replyWriter+"</p><hr>";
		});*/
		//html태그를 추가하면 jQuery의 html()함수를 사용합니다.
		//$("#reply-box").html(str);
		
		//댓글쓰기 버튼 이벤트 처리
		$("#replyAddBtn").on("click", function(){
			//id가 newReplyText인 요소의 값을 상수 text에 저장
			const text = $("#newReplyText").val();
			//id가 newReplyWriter인 요소의 값을 상수 writer에 저장
			const writer = $("#newReplyWriter").val();
			
			//POST요청 비동기 통신 시작
			$.ajax({
				type: "POST", 		//http 요청 메서드
				url: "/replies",	//요청하고자 하는 URI 작성
				headers: {
					"content-type": "application/json",
					"X-HTTP-METHOD-Override": "POST"
					
				},
				dataType: "text",	//응답시 return 받는 데이터의 타입
				//Server로 전송할 데이터
				//JSON.stringfy(): 매개값으로 자바스크립트의 객체를 전달하면 JSON형식으로 파싱하여 전송
				data: JSON.stringify({
					boardNo: boardNo,
					replyText: text,
					replyWriter: writer
				}),
				//통신 성공시 실행할 문장 작성
				//익명함수의 매개값으로 서버가 리턴한 데이터가 들어옵니다.
				success: function(result){
					if(result === "RegiSuccess"){	//Controller에서의 Return값
						alert("댓글 등록 완료");	
					}
					$("#newReplyText").val("");		//댓글 내용 초기화
					$("#newReplyWriter").val("");	//작성자 이름 초기화
				}
			
		});
	});
});

</script>
</body>
</html>