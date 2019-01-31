package cn.iustu.site;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SiteApplicationTests {

    @Value("${iustu.domain}")
    private String domain;

    @Value("${iustu.upload-path}")
    private String uploadPath;

    @Value("${iustu.login-view-url}")
    private String loginViewUrl;

    @Test
    public void contextLoads() {

        System.out.println(domain);
        System.out.println(uploadPath);
        System.out.println(loginViewUrl);
    }

}

