package loginpeg.skt;

import loginpeg.skt.domain.UserEmail;
import loginpeg.skt.domain.UserInfo;
import loginpeg.skt.domain.UserPhone;
import loginpeg.skt.repository.UserRepository;
import loginpeg.skt.service.UserService;
import loginpeg.skt.service.UserValidatorUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@WebAppConfiguration
public class PhoneTest {

    private static final Logger log = LoggerFactory.getLogger(PhoneTest.class);

    @Autowired
    UserValidatorUtil userValidatorUtil;

    private UserInfo userInfo;

    @BeforeEach
    public void setUp() {
        userInfo= new UserInfo();
        userInfo.setUrNo(1L);
        userInfo.setId("assdf");
        userInfo.setUrPassword("11111");
        userInfo.setUrAuth("USER");
        userInfo.setUrEmail(new UserEmail("fdsafdsa","fdsafdsa",""));
        userInfo.setUrName("dsfaf");
        userInfo.setUrPhone(new UserPhone("123","4567","8910"));
        userInfo.setUrHeight(111);
        userInfo.setUrAge("11");
        userInfo.setUrSex("man");
        userInfo.setUrBlood("B");
        userInfo.setUrReligion("무교");
        userInfo.setUrDrunk("가끔");
        userInfo.setUrSigar("흡연");
        userInfo.setUrEducation("중학교졸업");
        userInfo.setUrJob("학생");
        userInfo.setUrPersonality("cute");
        userInfo.setUrIntroduce("안녕");
        log.info("userInfo.setter : " + userInfo);
    }


    @Test
    public void test() {
//        UserPhone userPhone = userValidatorUtil.parsePhone("123","4567","8910");
//        log.info("userPhone : " + userPhone);

    }
}
