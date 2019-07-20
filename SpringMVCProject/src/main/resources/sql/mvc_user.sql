CREATE TABLE mvc_user(
	user_id CHAR(100) PRIMARY KEY,
	user_pw CHAR(100) NOT NULL,
	user_name CHAR(120) NOT NULL,
	user_reg_date TIMESTAMP DEFAULT NOW()
);

--컬럼 추가
ALTER TABLE mvc_user ADD COLUMN session_id CHAR(50) NOT NULL DEFAULT 'none';
ALTER TABLE mvc_user ADD COLUMN session_limit TIMESTAMP;
