package loginpeg.skt.config;

import loginpeg.skt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity // 1 Spring Security 를 활성화한다는 의미의 어노테이션입니다.
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { // 2
    //WebSecurityConfigurerAdapter 는 Spring Security 의 설정파일로서의 역할을 하기 위해 상속해야 하는 클래스입니다.

    private final UserService userService; // 3 후에 사용할 유저 정보를 가져올 클래스입니다. 아직 만들어지지 않았습니다.

    @Bean
    public PasswordEncoder passwordEncoder() { // 4 비밀번호를 암호화할 때 사용할 인코더를 미리 빈으로 등록해놓는 과정입니다.

        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) { // 5
     //   WebSecurityConfigurerAdapter를 상속받으면 오버라이드할 수 있습니다.
        //   인증을 무시할 경로들을 설정해놓을 수 있습니다.  •static 하위 폴더 (css, js, img)는 무조건 접근이 가능해야하기 때문에 인증을 무시해야합니다.

                web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // 6
     //   WebSecurityConfigurerAdapter를 상속받으면 오버라이드할 수 있습니다.  •http 관련 인증 설정이 가능합니다


/*        접근에 대한 인증 설정이 가능합니다. •anyMatchers를 통해 경로 설정과 권한 설정이 가능합니다.
        •permitAll() : 누구나 접근이 가능
        •hasRole() : 특정 권한이 있는 사람만 접근 가능
        •authenticated() : 권한이 있으면 무조건 접근 가능
        •anyRequest는 anyMatchers에서 설정하지 않은 나머지 경로를 의미합니다.
*/
                http
                .authorizeRequests() // 7
                .antMatchers("/static/jquery/**").permitAll()
                .antMatchers("/login", "/join", "/user").permitAll() // 누구나 접근 허용
                .antMatchers("/").hasRole("USER") // USER, ADMIN만 접근 가능
                .antMatchers("/admin").hasRole("ADMIN") // ADMIN만 접근 가능
                .anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
                .and()
                .formLogin() // 8
/*
            로그인에 관한 설정을 의미합니다. •loginPage() : 로그인 페이지 링크 설정
            •defaultSuccessUrl() : 로그인 성공 후 리다이렉트할 주소
*/

                .loginPage("/login") // 로그인 페이지 링크
                .defaultSuccessUrl("/") // 로그인 성공 후 리다이렉트 주소
                .and()
                .logout() // 9
/*
             로그아웃에 관한 설정을 의미합니다. •logoutSccessUrl() : 로그아웃 성공 후 리다이렉트할 주소
            •invalidateHttpSession() : 로그아웃 이후 세션 전체 삭제 여부
 */

                .logoutSuccessUrl("/login") // 로그아웃 성공시 리다이렉트 주소
                .invalidateHttpSession(true) // 세션 날리기
        ;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception { // 10
        /*
        로그인할 때 필요한 정보를 가져오는 곳입니다. •유저 정보를 가져오는 서비스를 userService (아직 만들어지지 않음)으로 지정합니다.
        •패스워드 인코더는 아까 빈으로 등록해놓은 passwordEncoder()를 사용합니다. (BCrypt)
        */

                auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
        // 해당 서비스(userService)에서는 UserDetailsService를 implements해서 loadUserByUsername() 구현해야함 (서비스 참고)
    }
}
