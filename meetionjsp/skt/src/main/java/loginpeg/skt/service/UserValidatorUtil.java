package loginpeg.skt.service;




import loginpeg.skt.domain.UserEmail;
import loginpeg.skt.domain.UserPhone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@Slf4j
public class UserValidatorUtil {

    /**
     * 회원필드 유효성 점검
     * @param field 회원필드 ex) email
     * @param regex 정규표현식
     * @return 유효성여부
     */
    public boolean isValidField(String field, String regex) {
        return Pattern.matches(regex,field);
    }

    public UserEmail parseEmail(String userLocalPart, String userDomainPart, String userDomainChoicePart) {
        UserEmail userEmail = new UserEmail();
        userEmail.setUserLocalPart(userLocalPart);

        if(userDomainChoicePart.equals("notSelected") || userDomainChoicePart.equals("etc")) {
            userEmail.setUserDomainPart(userDomainPart);
        }else {
            userEmail.setUserDomainPart(userDomainChoicePart);
        }

        return userEmail;
    }

    public String parseStringEmail(String userLocalPart, String userDomainPart, String userDomainChoicePart) {
        String email;

        userDomainPart=userDomainPart==null ? "": userDomainPart;
        if(userDomainChoicePart.equals("notSelected") || userDomainChoicePart.equals("etc")) {
            email=userLocalPart + "@" + userDomainPart;
        }else {
            email=userLocalPart + "@" + userDomainChoicePart;
        }
        log.info("email :" + email);
        return email;
    }

//    public UserPhone parsePhone(String phone1, String phone2, String phone3) {
//        String addUserPhone = phone1+phone2+phone3;
//        UserPhone userPhone = new UserPhone();
//
//        userPhone.setPhone1(addUserPhone);
//
//        return userPhone;
//    }
}
