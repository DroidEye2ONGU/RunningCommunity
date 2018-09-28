import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import droideye.pojo.Blackrecord;
import droideye.service.BlackRecordService;

public class BlackRecordTest {

    BlackRecordService blackRecordService;

    @Before
    public void init() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:Spring.xml");
        blackRecordService = classPathXmlApplicationContext.getBean(BlackRecordService.class);
    }

    @Test
    public void addOneBlackRecordTest() {
        boolean result = blackRecordService.addOneBlackRecord(new Blackrecord("DroidEye",
                "DroidEye2"));
        System.out.println(result);
    }

    @Test
    public void queryAllBlackRecordsBySelfNameTest() {
        List<Blackrecord> records = blackRecordService.queryAllBlackRecordsBySelfName("DroidEye");
        for (Blackrecord record :
                records) {
            System.out.println(record);
        }
    }

    @Test
    public void deleteFromBlackRecordTest() {
        boolean result = blackRecordService.deleteFromBlackRecord("DroidEye", "DroidEye2");

        System.out.println(result);
    }
}
