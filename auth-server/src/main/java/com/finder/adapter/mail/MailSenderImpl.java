package com.finder.adapter.mail;

import com.finder.port.out.MailSenderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MailSenderImpl implements MailSenderPort {
    private final TemplateEngine templateEngine;
    private final MailClient mailClient;
/*    @Value("{app.mailServerEnabled}")
    private boolean isMailServerEnabled;*/
    @Value("{app.hostUrl}")
    private String hostUrl;

    @Override
    public void sendActivationMail(String token, String to) {
    //TODO: implement if mail server is not enabled
        /*if(!isMailServerEnabled) {
            throw new MailServerIsNotEnabledException();
        }*/

        Map<String, Object> map = new HashMap<>();
        map.put("hostUrl", hostUrl);
        map.put("token", token);

        mailClient.sendHtmlMail(
                MailRequestModel.builder().emailTo(to).subject("some")
                        .message(buildMail(map, MailTemplates.ACTIVATION_CODE.getTemplateName()))
                        .build());
    }

    @Override
    public void sendRestoreMail(String token, String to) {
        Map<String, Object> map = new HashMap<>();
        map.put("hostUrl", hostUrl);
        map.put("token", token);

        mailClient.sendHtmlMail(
                MailRequestModel.builder().emailTo(to).subject("some")
                        .message(buildMail(map, MailTemplates.RESTORE_CODE.getTemplateName()))
                        .build());
    }

    private String buildMail(Map<String, Object> map, String templateName) {
        Context context = new Context();
        map.forEach(context::setVariable);

        return templateEngine.process(templateName, context);
    }
}
