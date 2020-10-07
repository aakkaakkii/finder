package com.finder.mail.adapter.web;

import com.finder.mail.port.in.MailService;
import com.finder.mail.port.models.request.MailRequestModel;
import com.netflix.client.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/v1/mail")
@RequiredArgsConstructor
public class MailRestController {
    private final MailService mailService;

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody MailRequestModel mail) {
        mailService.sendMail(mail);
        return new ResponseEntity<String>("", HttpStatus.OK);
    }

    @PostMapping("/send/html")
    public ResponseEntity<String> sendHtml(@RequestBody MailRequestModel mail) {
        mailService.sendMail(mail);
        return new ResponseEntity<String>("", HttpStatus.OK);
    }
}
