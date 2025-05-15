package com.company.SpiringBootDataJpa;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailSendController {
    private final MailSenderService mailSenderService;

    @PostMapping
    public String sendMail(String username) throws Exception {
        mailSenderService.sendMail(username);
        return "Mail sent!!!!!";
    }

    @PostMapping("/html")
    public String sendHTMLMail(String username) throws Exception {
        mailSenderService.sendHtmlMail(username);
        return "HTMLMail sent!!!!!";
    }

    @PostMapping("/pdf")
    public String sendAttachmentMail(String username) throws Exception {
        mailSenderService.sendAttachmentMail(username);
        return "HTMLMail sent!!!!!";
    }

    @PostMapping("/imageContent")
    public String sendContentImageMail(String username) throws Exception {
        mailSenderService.sendContentImageMail(username);
        return "HTMLMail sent!!!!!";
    }

    @PostMapping("/freemarker")
    public String sendFreeMarkerMail(String username) throws Exception {
        mailSenderService.sendFreeMarkerMail(username);
        return "FreeMarker sent!!!!!";
    }
}
