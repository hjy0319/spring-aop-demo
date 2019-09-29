package com.hujy.springaopdemo;

import com.hujy.springaopdemo.pojo.User;
import com.hujy.springaopdemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAopDemoApplicationTests {

	@Autowired
	private UserService userService;

	/**
	 * 测试@Before @After @AfterReturning
	 *
	 * @author hujy
	 * @date 2019-09-29 17:36
	 * @param
	 * @return void
	 */
	@Test
	public void testBeforeAfter() {
		String userCode = "10006";
		User user = userService.getUser(userCode);
		System.out.println("【调用者打印结果】:" + user);
	}

	/**
	 * 测试@Before @After @AfterThrowing
	 *
	 * @author hujy
	 * @date 2019-09-29 17:37
	 * @param
	 * @return void
	 */
	@Test
	public void testThrowing() {
		userService.getUser(null);
	}

	/**
	 * 测试@around
	 *
	 * @author hujy
	 * @date 2019-09-29 17:38
	 * @param
	 * @return void
	 */
	@Test
	public void testAround() {
		User u = new User();
		u.setUserCode("20080");
		u.setUserName("LiMing");
		u.setAge(30);
		String result = userService.saveUser(u);
		System.out.println("【调用者打印结果】" + result);
	}

	/**
	 * 测试around异常处理
	 *
	 * @author hujy
	 * @date 2019-09-29 17:38
	 * @param
	 * @return void
	 */
	@Test
	public void testAroundThrowing() {
		String result = userService.saveUser(null);
		System.out.println("【调用者打印结果】" + result);
	}

}
