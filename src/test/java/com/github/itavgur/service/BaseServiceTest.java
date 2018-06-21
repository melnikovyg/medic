package com.github.itavgur.service;


import com.github.itavgur.config.SpringContext;
import com.github.itavgur.config.SpringDb;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringContext.class, SpringDb.class}, loader = AnnotationConfigContextLoader.class)
//@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@Transactional
@ActiveProfiles("dev")
abstract public class BaseServiceTest {
}
