package loginpeg.skt.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.engine.transaction.spi.JoinStatus;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.util.Date;


@Data
public class UserInfoDto {

    @NotEmpty(message = "아이디를 입력하세요.")
    @Pattern(regexp = "[a-zA-Z]{1}\\w{7,19}", message = "아이디는 8~20 영문대소문자로 입력하세요.")
    private String id;          //아이디

    @NotEmpty(message = "비밀번호를 입력하세요")
    private String userPw;  //비밀번호

    private String userAuth;      //등급

    @NotEmpty(message = "이메일을 입력하세요")
    private String userLocalPart;    //이메일(아이디)

    private String userDomainPart;    //이메일(직접입력)

    private String userDomainChoicePart;    //이메일(홈페이지)

    @NotEmpty(message = "이름을 입력하세요")
    private String userName;    //이름


    @NotEmpty(message = "핸드폰 앞자리번호를 입력하세요")
    private String userCarrierNum;

    @NotEmpty(message = "핸드폰 중간번호를 입력하세요")
    private String userAssignmentNum;

    @NotEmpty(message = "핸드폰 끝번호를 입력하세요")
    private String userRanNum;

    @NotEmpty(message = "키를 입력하세요")
    private String userHeight;     // 키

    @NotEmpty(message = "나이를 입력하세요")
    private String userAge;     //나이

    @NotEmpty(message = "성별을 체크하세요")
    private String userSex;     //성별

    @NotEmpty(message = "혈액형을 체크하세요")
    private String userBlood;  //혈액형

//    @NotEmpty(message = "혈액형을 체크하세요")
//    private String bloodTypeA;  //혈액형
//
//    @NotEmpty(message = "혈액형을 체크하세요")
//    private String bloodTypeB;  //혈액형
//
//    @NotEmpty(message = "혈액형을 체크하세요")
//    private String bloodTypeO;  //혈액형
//
//    @NotEmpty(message = "혈액형을 체크하세요")
//    private String bloodTypeAB;  //혈액형

    @NotEmpty(message = "종교를 입력하세요")
    private String userReligion; //종교

    @NotEmpty(message = "음주량을 체크하세요")
    private String userDrunk;  //음주량

    @NotEmpty(message = "흡연량을 체크하세요")
    private String userSigar;     // 흡연여부

    @NotEmpty(message = "학력을 체크하세요")
    private String userEducation;  //학력

    @NotEmpty(message = "직업을 체크하세요")
    private String userJob;       // 직업

    @NotEmpty(message = "성격을 체크하세요")
    private String userPersonality;    //성격

    @NotEmpty(message = "자기소개를 적어주세요")
    private String userIntroduce;      //자기소개


//    private JoinStatus urLoading;  //가입상태


}
