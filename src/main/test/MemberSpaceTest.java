import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import droideye.pojo.Memberspace;
import droideye.service.MemberSpaceService;

public class MemberSpaceTest {

    MemberSpaceService memberSpaceService;

    @Before
    public void init() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
                "classpath:spring.xml");
        memberSpaceService = classPathXmlApplicationContext.getBean(MemberSpaceService.class);
    }

    @Test
    public void addMemberSpaceTest() {
        Memberspace memberspace = new Memberspace("地下室", "大晚上",
                "吃瓜", "阿甘", "note9", "垃圾运动", "/image",
                25);
        boolean result = memberSpaceService.addMemberSpace(memberspace);

        System.out.println(memberspace);

    }

    @Test
    public void queryMemberSpaceByMemberIdTest() {
        Memberspace memberspace = memberSpaceService.queryMemberSpaceByMemberId(1);

        System.out.println(memberspace);
    }

    @Test
    public void modifyMemberSpaceTest() {
        boolean result = memberSpaceService.modifyMemberSpace(
                new Memberspace("地下室二层", "黄昏", "独自", "阿甘",
                        "note10", "垃圾运动!", 25));

        System.out.println(result);
    }
}
