drop table IF EXISTS book;
create TABLE book(consecutive SERIAL PRIMARY KEY, id VARCHAR(100), name VARCHAR(100), lastname VARCHAR(100), age INT, phone_number VARCHAR(20),  start_date VARCHAR(20),
    end_date VARCHAR(20), house_id VARCHAR(10), discount_code VARCHAR(20));
