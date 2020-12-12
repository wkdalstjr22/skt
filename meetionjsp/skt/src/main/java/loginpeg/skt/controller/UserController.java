package loginpeg.skt.controller;

import loginpeg.skt.config.UserInfoDto;
import loginpeg.skt.domain.UserInfo;
import loginpeg.skt.domain.UserPhone;
import loginpeg.skt.service.UserService;
import loginpeg.skt.service.UserValidatorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserValidatorUtil userValidatorUtil;

    @Autowired
    private final UserService userService;

    @GetMapping("/login")
    public String LonginForm() {
        return "login";
    }

    @GetMapping("/join")
    public String createForm(Model model) {
        model.addAttribute("infoDTO", new UserInfoDto());
        return "join";
    }



    @PostMapping("/user")
    public String signup(@Valid @ModelAttribute(name = "infoDTO") UserInfoDto infoDTO, BindingResult result, Model model) { // 회원 추가
        log.info("##################");
        log.info("## joinForm:" + infoDTO);

//    if (null != errors && errors.getErrorCount() > 0) {
        if (result.hasErrors()) {
            log.info("폼점검 에러");
            return "join";

        } else {
            log.info("정상");
            //(userValidatorUtil.parseEmail(joinForm.getEmail1(),joinForm.getEmail2(), joinForm.getEmail3()));
            //userValidatorUtil.parsePhone(joinForm.getPhone1(), joinForm.getPhone2(),joinForm.getPhone3()));
            UserInfo userInfo = new UserInfo();
            userInfo.setId(infoDTO.getId());
            userInfo.setUserPw(infoDTO.getUserPw());
            userInfo.setUserAuth(infoDTO.getUserAuth());
            userInfo.setUserEmail(userValidatorUtil.parseEmail(infoDTO.getUserLocalPart(), infoDTO.getUserDomainPart(), infoDTO.getUserDomainChoicePart()));
            userInfo.setUserName(infoDTO.getUserName());
            userInfo.setUserPhone(new UserPhone(infoDTO.getUserCarrierNum(), infoDTO.getUserAssignmentNum(), infoDTO.getUserRanNum()));
            userInfo.setUserHeight(infoDTO.getUserHeight());
            userInfo.setUserAge(infoDTO.getUserAge());
            userInfo.setUserSex(infoDTO.getUserSex());
            userInfo.setUserBlood(infoDTO.getUserBlood());
            userInfo.setUserReligion(infoDTO.getUserReligion());
            userInfo.setUserDrunk(infoDTO.getUserDrunk());
            userInfo.setUserSigar(infoDTO.getUserSigar());
            userInfo.setUserEducation(infoDTO.getUserEducation());
            userInfo.setUserJob(infoDTO.getUserJob());
            userInfo.setUserPersonality(infoDTO.getUserPersonality());
            userInfo.setUserDate(new Date());
            userInfo.setUserIntroduce(infoDTO.getUserIntroduce());
            log.info("userInfo :" + userInfo);

            userService.save(userInfo);
            return "redirect:/login";
        }

    }

    // 추가
    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }
}
