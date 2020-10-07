package com.finder.mail.port.in;

import com.finder.mail.port.models.request.MailRequestModel;

public interface MailService {
    void sendMail(MailRequestModel mail);
    void sendHtmlMail(MailRequestModel mail);
}
