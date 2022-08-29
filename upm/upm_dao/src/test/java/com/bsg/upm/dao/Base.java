package com.bsg.upm.dao;

import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

public class Base {
    
    private static ClassPathXmlApplicationContext app;

    @BeforeClass
    public static void beforeClass() throws Exception {
        app = new ClassPathXmlApplicationContext("classpath:InitJndi.xml");
        DataSource ds = (DataSource) app.getBean("dataSource");
        SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
        builder.bind("java:comp/env/jdbc/upm", ds);
        builder.activate();
    }
}
