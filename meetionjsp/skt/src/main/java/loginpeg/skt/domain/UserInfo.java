package loginpeg.skt.domain;

import lombok.*;
import org.hibernate.engine.transaction.spi.JoinStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@SequenceGenerator(
        name="USERINFO_SEQ_GENERATOR",
        sequenceName = "USERINFO_SEQ",
        initialValue = 1,
        allocationSize = 1)

@Entity
@Data
public class UserInfo implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "USERINFO_SEQ_GENERATOR")
    @Column(name = "user_seq")
    private Long userSeq;

    @Column(name = "Id")
    private String id;          //아이디

    @Column(name = "user_pw")
    private String userPw;    //비밀번호

    @Column(name = "user_auth")
    private String userAuth;        //등급

    @Column(name = "user_email")
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="userLocalPart", column=@Column(name = "userLocalPart")),
            @AttributeOverride(name="userDomainPart", column=@Column(name = "userDomainPart"))
    })
    private UserEmail userEmail;    //이메일

    @Column(name = "user_name")
    private String userName;    //이름

    @Column(name = "user_phone")
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "userCarrierNum", column = @Column(name = "userCarrierNum")),
            @AttributeOverride(name = "userAssignmentNum", column = @Column(name = "userAssignmentNum")),
            @AttributeOverride(name = "userRanNum", column = @Column(name = "userRanNum"))
    })
    private UserPhone userPhone; //핸드폰번호

    @Column(name = "user_height")
    private String userHeight;     // 키

    @Column(name = "user_age")
    private String userAge;     //나이

    @Column(name = "user_sex")
    private String userSex;     //성별
    // private String userZipcode;  //번지주소
//    private String detail_address;    //상세주소



//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "bloodTypeA", column = @Column(name = "bloodTypeA")),
//            @AttributeOverride(name = "bloodTypeB", column = @Column(name = "bloodTypeB")),
//            @AttributeOverride(name = "bloodTypeO", column = @Column(name = "bloodTypeO")),
//            @AttributeOverride(name = "bloodTypeAB", column = @Column(name = "bloodTypeAB"))
//    })
    @Column(name = "user_blood")
    private String userBlood;  //혈액형

    @Column(name = "user_religion")
    private String userReligion;  //종교

    @Column(name = "user_drunk")
    private String userDrunk;  //음주량

    @Column(name = "user_sigar")
    private String userSigar;  //흡연여부

    @Column(name = "user_education")
    private String userEducation;  //학력

    @Column(name = "user_job")
    private String userJob;  //직업

    @Column(name = "user_personality")
    private String userPersonality;    //성격

    @Column(name = "user_date")
    private Date userDate;     //가입날짜

    @Column(name = "user_introduce")
    private String userIntroduce;      //자기소개

//    @Enumerated(EnumType.STRING)
//    private JoinStatus urLoading;  //가입상태


    @Builder
    public UserInfo(String id, String userPw, String userAuth,
                    UserEmail userEmail, String userName, UserPhone userPhone, String userHeight, String userAge,
                    String userSex, String userBlood, String userReligion, String userDrunk, String userSigar, String userEducation,
                    String userJob, String userPersonality, Date userDate, String userIntroduce) {
        this.id = id;
        this.userPw = userPw;
        this.userAuth = userAuth;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userHeight = userHeight;
        this.userAge = userAge;
        this.userSex = userSex;
        this.userBlood = userBlood;
        this.userReligion = userReligion;
        this.userDrunk = userDrunk;
        this.userSigar = userSigar;
        this.userEducation = userEducation;
        this.userJob = userJob;
        this.userPersonality = userPersonality;
        this.userDate = userDate;
        this.userIntroduce = userIntroduce;
//        this.urLoading = urLoading;


    }

    // 사용자의 권한을 콜렉션 형태로 반환
    // 단, 클래스 자료형은 GrantedAuthority를 구현해야함
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String role : userAuth.split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    // 사용자의 id를 반환 (unique한 값)
    @Override
    public String getUsername() {
        return id;
    }

    // 사용자의 password 를 반환
    @Override
    public String getPassword() {
        return userPw;
    }

    // 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        // 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않았음
    }

    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠금되었는지 확인하는 로직
        return true; // true -> 잠금되지 않았음
    }

    // 패스워드의 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        // 패스워드가 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않았음
    }

    // 계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        // 계정이 사용 가능한지 확인하는 로직
        return true; // true -> 사용 가능
    }
}
