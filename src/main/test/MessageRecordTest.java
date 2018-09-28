import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.util.List;

import droideye.pojo.Messagerecord;
import droideye.service.MessageRecordService;

public class MessageRecordTest {
    MessageRecordService messageRecordService;

    @Before
    public void init() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:Spring.xml");
        messageRecordService = classPathXmlApplicationContext.getBean(MessageRecordService.class);
    }

    @Test
    public void addOneMessageTest() {
        Messagerecord messagerecord = new Messagerecord("DroidEye", "默默", new Timestamp(System.currentTimeMillis()),
                "老王2", "我跟你说,我发现老王在隔壁2", 0, 0, 0);
        boolean result = messageRecordService.addOneMessage(messagerecord);

        System.out.println(result);
        System.out.println(messagerecord);
    }

    @Test
    public void queryAllSendersMessages() {
        List<Messagerecord> messagerecordList = messageRecordService.queryAllSendersMessages("DroidEye");
        for (Messagerecord record :
                messagerecordList) {
            System.out.println(record);
        }
    }

    @Test
    public void queryAllReceiversMessagesTest() {
        List<Messagerecord> messagerecordList = messageRecordService.queryAllReceiversMessages("默默");
        for (Messagerecord record :
                messagerecordList) {
            System.out.println(record);
        }
    }

    @Test
    public void updateMessageStatusTest() {
        boolean result = messageRecordService.updateMessageStatus(21, 1);
        System.out.println(result);
    }

    @Test
    public void updateSenderStatusTest() {
        messageRecordService.updateSenderStatus(new String[]{"21"}, 1);
    }

    @Test
    public void updateReceiverStatusTest() {
        messageRecordService.updateReceiverStatus(new String[]{"21"}, 1);
    }

    @Test
    public void querySingleMessageRecordByMessageIdTest() {
        Messagerecord messagerecord = messageRecordService.querySingleMessageRecordByMessageId(21);
        System.out.println(messagerecord);
    }

}
