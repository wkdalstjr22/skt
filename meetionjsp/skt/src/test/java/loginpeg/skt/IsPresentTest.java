package loginpeg.skt;

import loginpeg.skt.config.UserInfoDto;
import loginpeg.skt.domain.UserBlood;
import loginpeg.skt.domain.UserEmail;
import loginpeg.skt.domain.UserInfo;
import loginpeg.skt.domain.UserPhone;
import loginpeg.skt.repository.UserRepository;
import loginpeg.skt.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Optional;

@SpringBootTest
@WebAppConfiguration
public class IsPresentTest {

    private static final Logger log = LoggerFactory.getLogger(IsPresentTest.class);

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    private UserInfo userInfo;




    @BeforeEach
    public void setUp() {
        userInfo= new UserInfo();
        userInfo.setUserSeq(1L);
        userInfo.setId("assdf");
        userInfo.setUserPw("11111");
        userInfo.setUserAuth("USER");
        userInfo.setUserEmail(new UserEmail("fdsafdsa","fdsafdsa",""));
        userInfo.setUserName("dsfaf");
        userInfo.setUserPhone(new UserPhone("111","1111","1111"));
        userInfo.setUserHeight("111");
        userInfo.setUserAge("11");
        userInfo.setUserSex("man");
//        userInfo.setUserBlood(new UserBlood("A"));
        userInfo.setUserReligion("무교");
        userInfo.setUserDrunk("가끔");
        userInfo.setUserSigar("흡연");
        userInfo.setUserEducation("중학교졸업");
        userInfo.setUserJob("학생");
        userInfo.setUserPersonality("cute");
        userInfo.setUserIntroduce("안녕");
        log.info("userInfo.setter : " + userInfo);
    }


    @Test
    public void test() {
        log.info("userInfo.setter : " + userInfo);
//        userService.valdateDuplicateUser(userInfo);
        log.info("userInfo.id : "+ "fdsaf");
        log.info("userInfo : " + userInfo);
        Optional<UserInfo> findUsers = userRepository.findById(userInfo.getId());
//        log.info("findUsers : "+findUsers);
//        if (!findUsers.isPresent()) {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }
        Assert.assertFalse(!findUsers.isPresent());
    }
}
