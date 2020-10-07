package com.finder.adapter.mail;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("mail-server")
public interface MailClient {
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/rest/v1/send"
    )
    void sendMail(@RequestParam("token") MailRequestModel token);
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/rest/v1/send/html"
    )
    void sendHtmlMail(@RequestParam("token") MailRequestModel token);
}
