package com.zxw.childhood.jojoauth.redisTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zxw
 * @date 2019-12-13 14:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CrudTest {

    @Before
    public  void setUp() throws Exception{

    }


    @Test
    public void contextLoads() throws Exception {


    }

}


//============================note
/*
@SpringBootTest会加载spring的上下文，这样可以使用@Autowired注入Bean

 @Before：初始化方法
  @After：释放资源
  @Test：测试方法（包括：期望异常和超时时间）

  @Test(timeout = 1000)，超时会失败。

    @Test(expected = NullPointerException.class) 希望抛出空指针异常

 @Ignore：忽略的测试方法

  @BeforeClass：针对所有测试，只执行一次，且必须为static void

   @AfterClass：针对所有测试，只执行一次，且必须为static void
   @RunWith：可以更改测试运行器

   执行顺序：

   @BeforeClass ==> @Before ==> @Test ==> @After ==> @AfterClass


   1.事务管理
   在@Test注解下添加@Transactional
    使用的数据库要支持事务（如：mysql的innodb）
    关闭回滚加注解@Rollback(false)

    2.
      1、BeforeClass修饰的方法会在所有方法被调用前执行
      而且该方法是静态的，所以当测试类被加载后接着就执行它
      在内存中它只会存在一份，适合加载配置文件
      2、AfterClass修饰的方法用来对资源的清理，如关闭数据库的连接
      befoer和after修饰的方法在每个test修饰的方法执行前会被各执行一次，假如有两个
      test文件，before和after会被各执行两次；




*/
