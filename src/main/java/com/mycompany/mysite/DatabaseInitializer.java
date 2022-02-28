package com.mycompany.mysite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Transactional
@Component
public class DatabaseInitializer {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate.update("declare\n" +
                "      num   number;\n" +
                "begin\n" +
                "    select count(1) into num from user_tables where table_name = upper('users') ;\n" +
                "    if num > 0 then\n" +
                "        execute immediate 'drop table users' ;\n" +
                "    end if;\n" +
                "end;\n");
        jdbcTemplate.update(
                "CREATE TABLE users\n" +
                        "(\n" +
                        "    id number  NOT NULL,\n" +
                        "   account varchar2(30) not null,\n" +
                        "   grander number,\n" +
                        "   brithday date,\n" +
                        "   mobile varchar2(11),\n" +
                        "   lasttime number,\n" +
                        "   logincount number,\n" +
                        "   validstate number,\n" +
                        "    email varchar2(100) not null,\n" +
                        "    password varchar2(30) not null,\n" +
                        "    name varchar2(30) not null,\n" +
                        "    PRIMARY KEY (id),\n" +
                        "    UNIQUE (email)\n" +
                        ")");
        jdbcTemplate.update("Create or Replace Trigger STUDENT_AUTOINCREMENT \n" +
                "Before Insert on USERS\n" +
                "For Each Row \n" +
                "When (NEW.ID IS NULL) \n" +
                "Begin \n" +
                "Select SEQ_STUDENT.NEXTVAL INTO :NEW.ID FROM DUAL; \n" +
                "End; ");
    }
}
