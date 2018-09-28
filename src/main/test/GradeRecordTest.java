import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import droideye.pojo.Graderecord;
import droideye.service.GradeRecordService;
import droideye.service.MemberService;

public class GradeRecordTest {

    GradeRecordService gradeRecordService;

    @Before
    public void init() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        gradeRecordService = (GradeRecordService) classPathXmlApplicationContext.getBean("gradeRecordService");

    }

    @Test
    public void queryGradeByPointTest() {
        Graderecord graderecord = gradeRecordService.queryGradeByPoint(300);

        System.out.println(graderecord);
    }

    @Test
    public void queryGradeByPointReturnPointIdTest() {
        Integer id = gradeRecordService.queryGradeByPointReturnId(300);

        System.out.println(id);
    }
}
