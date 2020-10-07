package com.finder.adapter.mail;

public enum MailTemplates {
    ACTIVATION_CODE("activationCode"),
    RESTORE_CODE("restoreCode");

    private final String templateName;

    MailTemplates(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateName(){
        return templateName;
    }
}
