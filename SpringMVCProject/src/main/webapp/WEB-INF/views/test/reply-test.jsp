<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<jsp:include page="../include/static-head.jsp" />

<body class="hold-transition skin-blue sidebar-mini layout-boxed">

<div class="wrapper">
    
    <jsp:include page="../include/main-header.jsp" />
	<jsp:include page="../include/side-bar.jsp"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                AJAX 댓글 테스트 페이지
            </h1>
            <ol class="breadcrumb">
                <li><i class="fa fa-edit"></i> reply test</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content container-fluid">

            <div class="col-lg-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">댓글 작성</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <label for="newReplyText">댓글 내용</label>
                            <input class="form-control" id="newReplyText" name="replyText" placeholder="댓글 내용을 입력해주세요">
                        </div>
                        <div class="form-group">
                            <label for="newReplyWriter">댓글 작성자</label>
                            <input class="form-control" id="newReplyWriter" name="replyWriter" placeholder="댓글 작성자를 입력해주세요">
                        </div>
                        <div class="pull-right">
                            <button type="button" id="replyAddBtn" class="btn btn-primary"><i class="fa fa-save"></i> 댓글 저장</button>
                        </div>
                    </div>
                    <!-- 댓글 목록이 배치될 박스 -->
                    <div class="box-footer">
                        <ul id="replies">

                        </ul>
                    </div>
                    <!-- 댓글 페이지 화면이 들어올 박스 -->
                    <div class="box-footer">
                        <div class="text-center">
                            <ul class="pagination pagination-sm no-margin">

                            </ul>
                        </div>
                    </div>
                </div>
            </div>
			<!-- 댓글 수정 모달 화면 창 -->
            <div class="modal fade" id="modifyModal" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">댓글 수정창</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="replyNo">댓글 번호</label>
                                <input class="form-control" id="replyNo" name="replyNo" readonly>
                            </div>
                            <div class="form-group">
                                <label for="replyText">댓글 내용</label>
                                <input class="form-control" id="replyText" name="replyText" placeholder="댓글 내용을 입력해주세요">
                            </div>
                            <div class="form-group">
                                <label for="replyWriter">댓글 작성자</label>
                                <input class="form-control" id="replyWriter" name="replyWriter" readonly>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default pull-left" data-dismiss="modal">닫기</button>
                            <button type="button" class="btn btn-success modalModBtn">수정</button>
                            <button type="button" class="btn btn-danger modalDelBtn">삭제</button>
                        </div>
                    </div>
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    
    <jsp:include page="../include/main-footer.jsp"/>
    <jsp:include page="../include/plugin-js.jsp"/>
</div>
<script>
$(document).ready(function () {
   
	const boardNo = 2997;
	let pageNum = 1;
	
	//getReplies();
	getRepliesPaging(pageNum);
	
	//댓글 목록 불러오기 함수 정의
	//function getReplies(){
		
   //댓글목록 비동기로 가져오기
   //getJSON은 GET요청이 서버로 갔다가 서버에서 function함수로  return
   		/*$.getJSON("/replies/all/" + boardNo, function(data) {
	  		//console.log(data);
	  		let str = "";
	  		$(data).each(function(){
	  			str += "<li data-replyNo='" + this.replyNo + "' class='replyLi'>"
                +   "<p class='replyText'>" + this.replyText + "</p>"
                +   "<p class='replyWriter'>" + this.replyWriter + "</p>"
                +   "<button type='button' class='btn btn-xs btn-success modifyModal' data-toggle='modal' data-target='#modifyModal'>댓글 수정</button>"
                + "</li>"
                + "<hr>";
	  		});
	  		 		
	  		$("#replies").html(str);
   		});
	} *///getReplies() 함수의 끝
	
	//페이징 처리 된 댓글 목록 불러오기 함수 정의
	function getRepliesPaging(page){
		$.getJSON("/replies/"+boardNo+"/"+page, function(data){
			let str = "";
			$(data.replies).each(function(){
	  			str += "<li data-replyNo='" + this.replyNo + "' class='replyLi'>"
                +   "<p class='replyText'>" + this.replyText + "</p>"
                +   "<p class='replyWriter'>" + this.replyWriter + "</p>"
                +   "<button type='button' class='btn btn-xs btn-success modifyModal' data-toggle='modal' data-target='#modifyModal'>댓글 수정</button>"
                + "</li>"
                + "<hr>";
	  		});	//end $(data).each(function(){})
			$("#replies").html(str);
	  		
	  		printPageElement(data.pageCreator);
			
		});	//end $.getJSON
		
	} //end function define: getRepliesPaging
	
	//페이지 목록 태그를 만드는 함수 정의
	function printPageElement(pageCreator){
		
		let element = "";
		
		//이전버튼 태그 만들기
		if(pageCreator.prev){
			element = element + "<li><a href='"+(pageCreator.beginPage-1)+"'>이전</a></li>";
		}	// end if(data.pageCreator.prev)
		//페이지 번호 리스트 만들기 
		const begin = pageCreator.beginPage;
		const end = pageCreator.endPage;
		for(let i=begin; i<=end; i++){
			const active = (pageCreator.criteria.page === i) ? 'class=active' : '';
			element = element + "<li "+active+"><a href='"+i+"'>"+i+"</a></li>"
		}
		
		if(pageCreator.next){
			element = element + "<li><a href='"+(pageCreator.endPage+1)+"'>다음</a></li>";
		}
		
		$(".pagination").html(element);
	} //end printPageElement
	
	//페이지 클릭 이벤트
	/*
		# 클래스 이름이 pagination인 ul 태그는 실존하는 요소이지만
		  ul태그의 자식요소들인 li와 a는 자바스크립트 구문으로 만든
		  가상요소입니다. 따라서 직접적인 클릭 이벤트 처리를 할 수 없습니다.
		# 그래서 실존요소인 ul태그에 이벤트 처리를 걸고 on함수의 2번째 요소로
		    실제로 이벤트 처리 할 가상 요소를 적습니다.
	*/
	$(".pagination").on("click", "li a",function(e){
		e.preventDefault(); //a태그의 본래의 기능을 막음.
		//console.log("페이지 클릭됨");
		//console.log($(this).attr("href"));
		pageNum = $(this).attr("href");
		console.log(pageNum);
		getRepliesPaging(pageNum);
		
	}); // end page click

   //댓글 등록하기
   $("#replyAddBtn").on("click",function(){
	   	const rt = $("#newReplyText").val();
		const rw = $("#newReplyWriter").val();
   		$.ajax({ // ajax start
   			type: "POST",
   			url: "/replies",	//요청하고자 하는 URI 작성
			headers: {
				"content-type": "application/json",
				"X-HTTP-METHOD-Override": "POST"
			},
			datatype: "text",
			data: JSON.stringify({
				boardNo: boardNo,
				replyText: rt,
				replyWriter: rw
			}),
			success: function(result){
				if(result === "RegiSuccess"){	//Controller에서의 Return값
					alert("댓글 등록 완료");	
				}
				getRepliesPaging(1);
				//getReplies();
				$("#newReplyText").val("");		//댓글 내용 초기화
				$("#newReplyWriter").val("");	//작성자 이름 초기화
			} // success function
   		}); // ajax end
	});	//end 댓글작성 클릭이벤트
	
	//댓글 수정창 클릭 이벤트
	$("#replies").on("click","li button",function(){
		//console.log("수정창클릭");
		console.log("댓글번호: "+$(this).parent().attr("data-replyNo"));
		console.log("작성자 이름: "+$(this).parent().find(".replyWriter").text());
		console.log("댓글내용: "+$(this).parent().find(".replyText").text());
		const rNo = $(this).parent().attr("data-replyNo");
		const rWriter = $(this).parent().find(".replyWriter").text();
		const rText = $(this).parent().find(".replyText").text();
		//const replyWriter = $(this).parent()
		$("#replyNo").val(rNo);
		$("#replyWriter").val(rWriter);
		$("#replyText").val(rText);
	});	// 댓글 수정 클릭 이벤트 종료
	
	//댓글 수정하기
	$(".modalModBtn").on("click",function(){
		const replyNo = $(this).parent().parent().find("#replyNo").val();
		const rText = $(this).parent().parent().find("#replyText").val();
		//console.log(replyNo);
		$.ajax( //ajax start
			{
				type: "PUT", 	//요청방식
				url: "/replies/"+replyNo, 
				headers:{
					"content-type": "application/json",
					"X-HTTP-METHOD-Override": "PUT"
				},
				data: JSON.stringify( // data start
					{
						replyText: rText
					}
						
				), // data end
				dataType: "text",
				success: function(result){
					if(result === "updateSuccess"){
						alert("댓글수정 완료!");
						$("#modifyModal").modal("hide"); //수정창을 감추는 함수
						getRepliesPaging(pageNum);
					}
				}
				
			}
		
		
		) // ajax end
		
	});	// 댓글 수정하기 종료
	
	//댓글 삭제하기 
	$(".modalDelBtn").on("click", function(){
		const replyNo = $(this).parent().parent().find("#replyNo").val();
		$.ajax(	//ajax start
			{
				type: "DELETE",
				url: "/replies/" + replyNo,
				headers:{
					"content-type": "application/json",
					"X-HTTP-METHOD-Override": "DELETE"
				},
				dataType: "text",
				success: function(result){
					if(result === "delSuccess"){
						alert("댓글 삭제 완료!");
						$("#modifyModal").modal("hide"); //수정창을 감추는 함수
						getRepliesPaging(pageNum);
					}
				}
			}
		)	//ajax end
	});	//댓글 삭제하기 종료
});//JQuery의 끝

</script>
</body>
</html>
