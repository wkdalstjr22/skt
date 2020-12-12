package loginpeg.skt.controller;

import loginpeg.skt.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class MailSenderController {

    private final EmailService emailService;

    @RequestMapping(value = "/mail", method = RequestMethod.POST)
    public String home(HttpServletRequest req){
        emailService.sendSimpleMessage("메일주소","제목","내용");
        return "index";
    }
}
