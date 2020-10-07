package com.finder.adapter.mail;

import lombok.Builder;
import lombok.Data;

@Builder
public class MailRequestModel {
    public String emailTo;
    public String message;
    public String subject;
}
