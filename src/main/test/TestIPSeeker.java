import org.junit.Test;

import droideye.common.util.ip.IPSeeker;

public class TestIPSeeker {

    @Test
    public void IPSeekerTest() {
        IPSeeker seeker = IPSeeker.getInstance();
        String ip = "223.11.111.116";
        String country = seeker.getCountry(ip);
        String area = seeker.getArea(ip);
        System.out.println( country+"*******" + area);
    }
}
