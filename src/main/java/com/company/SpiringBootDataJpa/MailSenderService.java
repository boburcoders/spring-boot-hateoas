package com.company.SpiringBootDataJpa;

import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.Message;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MailSenderService {

    private final JavaMailSender mailSender;
    private final Configuration configuration;

    @Async
    public void sendMail(String username) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        mimeMessage.setText("Hello from Bobur");
        mimeMessage.setFrom("from@gmail.com");
        mimeMessage.setRecipients(Message.RecipientType.TO, "to@gmail.com");
        mimeMessage.setSubject("Subject from " + username);

        mailSender.send(mimeMessage);
    }


    @Async
    public void sendHtmlMail(String username) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        mimeMessage.setText("Hello from Bobur");
        Path path = Path.of("/home/bobur/IdeaProjects/SpiringBootDataJpa/src/main/resources/active.html");
        String stringMail = Files.readString(path);
        stringMail = stringMail.formatted(username);
        mimeMessage.setContent(stringMail, "text/html;charset=utf-8");
        mimeMessage.setFrom("from@gmail.com");
        mimeMessage.setRecipients(Message.RecipientType.TO, "to@gmail.com");
        mimeMessage.setSubject("Subject from " + username);

        mailSender.send(mimeMessage);
    }

    @Async
    public void sendAttachmentMail(String username) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setFrom(username + "@gmail.com");
        messageHelper.setTo("to@gmail.com");
        messageHelper.setSubject("Subject from " + username);

        Path path = Path.of("/home/bobur/IdeaProjects/SpiringBootDataJpa/src/main/resources/active.html");
        Path pdfAttachment = Path.of("/home/bobur/IdeaProjects/SpiringBootDataJpa/src/main/resources/file (2).pdf");
        FileSystemResource fileSystemResource = new FileSystemResource(pdfAttachment);
        String stringMail = Files.readString(path);
        stringMail = stringMail.formatted(username);

        messageHelper.setText(stringMail, true);
        messageHelper.addAttachment("PDF.pdf", fileSystemResource);
        mailSender.send(mimeMessage);
    }

    @Async
    public void sendContentImageMail(String username) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setFrom(username + "@gmail.com");
        messageHelper.setTo("to@gmail.com");
        messageHelper.setSubject("Subject from " + username);

        Path path = Path.of("src/main/resources/active.html");
        Path imagePath = Path.of("src/main/resources/image.png");
//        Base64.Encoder encoder = Base64.getEncoder();
//        String imageAsBase64 = encoder.encodeToString(Files.readAllBytes(imagePath));
        String stringMail = Files.readString(path);
//        stringMail = stringMail.formatted(imageAsBase64);

        messageHelper.setText(stringMail, true);
        messageHelper.addInline("image_id", new FileSystemResource(imagePath));
        mailSender.send(mimeMessage);
    }


    @Async
    public void sendFreeMarkerMail(String username) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setFrom(username + "@gmail.com");
        messageHelper.setTo("to@gmail.com");
        messageHelper.setSubject("Subject from " + username);

        Template template = configuration.getTemplate("active_your_account.ftlh");
        String token = Base64.getEncoder().encodeToString(username.getBytes());
        Map<String, String> objectModel = Map.of("username", username, "token", token);
        String stringMail = FreeMarkerTemplateUtils.processTemplateIntoString(template, objectModel);
        messageHelper.setText(stringMail, true);
        mailSender.send(mimeMessage);
    }
}
