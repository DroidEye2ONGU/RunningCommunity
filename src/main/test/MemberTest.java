

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.util.List;

import droideye.common.util.JavaMail.JavaMailUtil;
import droideye.common.util.MD5;
import droideye.mapper.MemberInfoMapper;
import droideye.pojo.Memberinfo;
import droideye.service.MemberService;

public class MemberTest {

    MemberInfoMapper memberInfoMapper;
    MemberService memberService;
    MD5 md5;
    JavaMailUtil javaMailUtil;

    @Before
    public void init() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        memberService = (MemberService) classPathXmlApplicationContext.getBean("memberService");
        md5 = classPathXmlApplicationContext.getBean(MD5.class);
        javaMailUtil = classPathXmlApplicationContext.getBean(JavaMailUtil.class);
        memberInfoMapper = classPathXmlApplicationContext.getBean(MemberInfoMapper.class);
    }

    @Test
    public void testAddMember() {
        Memberinfo memberinfo = new Memberinfo(
                null, "DroidEye", "123456", "男", 23, "droideye.ongu@foxmail.com", "我的第一辆车", "k2",
                "山西省", "太原市杏花岭区", "18636600417", null, "DroidEye", new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()), 0, 0, 1);
        boolean result = memberService.addMember(memberinfo);

        System.out.println(memberinfo);
        System.out.println(result);
    }

    @Test
    public void testGetAllMembers() {
        List<Memberinfo> allMembers = memberService.getAllMembers();

        for (Memberinfo m :
                allMembers) {
            System.out.println(m);
        }
    }

    @Test
    public void testQuerySingleMemberByNickName() {
        String nickName = "DroidEye1";
        Memberinfo memberinfo = memberService.querySingleMemberByNickName(nickName);
        System.out.println(memberinfo);
    }

    @Test
    public void updateIsOnlineByNickNameTest() {
        boolean result = memberService.updateIsOnlineByNickName(0, "DroidEye");
        System.out.println(result);
        Memberinfo memberinfo = memberService.querySingleMemberByNickName("DroidEye");
        System.out.println(memberinfo);
    }

    @Test
    public void updateStatusByNickNameTest() {
        boolean result = memberService.updateStatusByNickName(0, "DroidEye");
        Memberinfo memberinfo = memberService.querySingleMemberByNickName("DroidEye");
        System.out.println(memberinfo);
    }

    @Test
    public void addPointByNickNameTest() {
        boolean result = memberService.addPointByNickName(20, "DroidEye");
        System.out.println(result);
    }

    @Test
    public void subPointByNickNameTest() {
        boolean result = memberService.subPointByNickName(20, "DroidEye");
        System.out.println(result);
    }
    //根据用户名查找密码提示问题
    @Test
    public void findPasswordQuestionByNickname() {
        String droidEye = memberService.findPasswordQuestionByNickname("DroidEye");

        System.out.println(droidEye);
    }

    @Test
    //根据用户名查找密码提示问题答案
    public void findPasswordQuestionAnswerByNickName() {
        String droidEye = memberService.findPasswordQuestionAnswerByNickName("DroidEye");

        System.out.println(droidEye);
    }

    @Test
    public void changePasswordByNickNameTest() {
        boolean result = memberService.changePasswordByNickName("DroidEye", MD5.getMD5Str("123456"));

        System.out.println(md5.getMD5ofStr("123456"));
        System.out.println(result);
    }

    @Test
    public void rePasswordTest() {
        String s = memberService.rePassword("DroidEye", "k2");

        System.out.println(s);
    }

    @Test
    public void queryPasswordByNickNameTest() {
        boolean result = memberService.checkPasswordByNickName("DroidEye", "hdmxrodb");

        System.out.println(result);
    }

    @Test
    public void modifyMemberInfoByNickNameTest() {
        Memberinfo memberinfo = new Memberinfo();
        memberinfo.setId(41);
        memberinfo.setNickName("DroidEye1");
        memberinfo.setPassword("E10ADC3949BA59ABBE56E057F20F883E");
        memberinfo.setGender("男");
        memberinfo.setEmail("512892702@qq.com");
        memberinfo.setPasswordQuestion("隔壁老王");
        memberinfo.setPasswordAnswer("老王");
        memberinfo.setProvinceCity("山西");
        memberinfo.setAddress("动物园");
        memberinfo.setPhone("11111111111");

        boolean result = memberInfoMapper.modifyMemberInfoByNickName(memberinfo);
        System.out.println(memberinfo);
        System.out.println(result);
    }

    @Test
    public void querySingleMemberByIdTest() {
        Memberinfo memberinfo = memberService.querySingleMemberById(25);
        System.out.println(memberinfo);
    }
}
