import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import droideye.common.util.ip.Utils;


public class Test{


    /*@Before*/
    public void init() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
    }

    @org.junit.Test
    public void ipFileTest() {
        String s = Utils.returnIpFile();
        System.out.println(s);
    }
}
