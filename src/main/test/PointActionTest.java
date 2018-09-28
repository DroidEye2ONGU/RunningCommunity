import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import droideye.pojo.Pointaction;
import droideye.service.MemberService;
import droideye.service.PointActionService;

public class PointActionTest {

    PointActionService pointActionService;

    @Before
    public void init() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        pointActionService = classPathXmlApplicationContext.getBean(PointActionService.class);
    }

    @Test
    public void queryPointActionByActionNameTest() {
        Pointaction pointaction = pointActionService.queryPointActionByActionName("register");
        System.out.println(pointaction);
    }

    @Test
    public void queryPointActionByIdTest() {
        Pointaction pointaction = pointActionService.queryPointActionById(1);
        System.out.println(pointaction);
    }

}
