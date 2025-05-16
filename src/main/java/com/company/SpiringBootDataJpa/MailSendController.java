package com.company.SpiringBootDataJpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
@Slf4j
public class MailSendController {
    private final MailSenderService mailSenderService;

    @PostMapping
    public String sendMail(String username) throws Exception {
        for (int i = 0; i < 1000; i++) {
            log.info("Sending mail {}| Time is {}", username, new Date());
        }
//        mailSenderService.sendMail(username);
        return "Mail sent!!!!!";
    }

    @PostMapping("/html")
    public String sendHTMLMail(String username) throws Exception {
//        mailSenderService.sendHtmlMail(username);
        return "HTMLMail sent!!!!!";
    }

    @PostMapping("/pdf")
    public String sendAttachmentMail(String username) throws Exception {
//        mailSenderService.sendAttachmentMail(username);
        return "HTMLMail sent!!!!!";
    }

    @PostMapping("/imageContent")
    public String sendContentImageMail(String username) throws Exception {
//        mailSenderService.sendContentImageMail(username);
        return "HTMLMail sent!!!!!";
    }

    @PostMapping("/freemarker")
    public String sendFreeMarkerMail(String username) throws Exception {
//        mailSenderService.sendFreeMarkerMail(username);
        return "FreeMarker sent!!!!!";
    }
}
