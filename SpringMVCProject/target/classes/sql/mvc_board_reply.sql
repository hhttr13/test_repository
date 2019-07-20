CREATE TABLE mvc_board_reply(
	reply_no INT PRIMARY KEY AUTO_INCREMENT,
	board_no INT NOT NULL DEFAULT 0,
	reply_text VARCHAR(1000) NOT NULL,
	reply_writer VARCHAR(50) NOT NULL,
	reg_date TIMESTAMP NOT NULL DEFAULT NOW(),
	update_date TIMESTAMP NOT NULL DEFAULT NOW()
);

-- 댓글 참조키 설정
ALTER TABLE mvc_board_reply ADD CONSTRAINT FK_BOARD
FOREIGN KEY(board_no) REFERENCES mvc_board(board_no);
