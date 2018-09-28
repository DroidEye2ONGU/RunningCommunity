import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import droideye.pojo.Friendrecord;
import droideye.service.FriendRecordService;

public class FriendRecordTest {
    FriendRecordService friendRecordService;

    @Before
    public void init() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:Spring.xml");
        friendRecordService = classPathXmlApplicationContext.getBean(FriendRecordService.class);
    }


    @Test
    public void addOneFriendTest() {
        boolean result = friendRecordService.addOneFriend(new Friendrecord(
                "DroidEye", "qqqqq"));
        friendRecordService.addOneFriend(new Friendrecord(
                "DroidEye", "qqqqqq"));
        friendRecordService.addOneFriend(new Friendrecord(
                "DroidEye", "wwwwww"));
        friendRecordService.addOneFriend(new Friendrecord(
                "DroidEye", "默默"));

        System.out.println(result);
    }

    @Test
    public void queryAllFriendsByNickNameTest() {
        List<Friendrecord> friends = friendRecordService.queryAllFriendsByNickName("DroidEye");

        for (Friendrecord friend :
                friends) {
            System.out.println(friend);
        }
    }

    @Test
    public void deleteOneFriendTest() {
        boolean result = friendRecordService.deleteOneFriend("DroidEye", "DroidEye1");

        System.out.println(result);
    }
}
