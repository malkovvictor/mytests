package ru.victormalkov.forumtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import ru.victormalkov.forumtest.services.UserService;
import ru.victormalkov.forumtest.services.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ForumtestApplicationTests {
	@Autowired
	ApplicationContext ctx;

	@Test
	void contextLoads() {
		UserService us = ctx.getBean(UserService.class);
		assertTrue(us instanceof UserServiceImpl);

		HomeController hc = ctx.getBean(HomeController.class);
		assertNotNull(hc);

	}

}
