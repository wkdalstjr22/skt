package loginpeg.skt.service;

import loginpeg.skt.config.UserInfoDto;
import loginpeg.skt.domain.UserInfo;
import loginpeg.skt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Spring Security 필수 메소드 구현
     *
     * @param id 아이디
     * @return UserDetails
     * @throws UsernameNotFoundException 유저가 없을 때 예외 발생
     */
    @Override // 기본적인 반환 타입은 UserDetails, UserDetails를 상속받은 UserInfo로 반환 타입 지정 (자동으로 다운 캐스팅됨)
    public UserInfo loadUserByUsername(String id) throws UsernameNotFoundException { // 시큐리티에서 지정한 서비스이기 때문에 이 메소드를 필수로 구현
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException((id)));
    }

    /**
     * 회원정보 저장
     *
     * @param userInfo 회원정보가 들어있는 DTO
     * @return 저장되는 회원의 PK
     */
    public Long save(UserInfo userInfo) {
        valdateDuplicateUser(userInfo); // 중복회원검증
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userInfo.setUserPw(encoder.encode(userInfo.getUserPw()));
        userRepository.save(userInfo);
        return userInfo.getUserSeq();
    }
    public void valdateDuplicateUser(UserInfo userInfo) {
        Optional<UserInfo> findUsers = userRepository.findById(userInfo.getId());
        log.info("findUsers : "+findUsers);
        if (findUsers.isPresent()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

}
