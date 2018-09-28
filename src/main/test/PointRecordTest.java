import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.util.List;

import droideye.pojo.Pointrecord;
import droideye.service.PointRecordService;

public class PointRecordTest {

    PointRecordService pointRecordService;

    @Before
    public void init() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
                "classpath:spring.xml");
        pointRecordService = classPathXmlApplicationContext.getBean(PointRecordService.class);
    }

    @Test
    public void addPointRecordTest() {
        Pointrecord pointrecord = new Pointrecord("DroidEye",
                new Timestamp(System.currentTimeMillis()), 2);
        boolean result = pointRecordService.addPointRecord(pointrecord);

        System.out.println(result);
        System.out.println(pointrecord);
    }

    @Test
    public void queryPointRecordsByNickNameTest() {
        List<Pointrecord> pointrecords = pointRecordService.queryPointRecordsByNickName("DroidEye");

        for (Pointrecord p :
                pointrecords) {
            System.out.println(p);
        }
    }
}
