package com.exam.notification.email.helper;

import com.exam.notification.email.constant.TemplatePaths;
import com.exam.notification.email.exception.TemplateNotFoundException;
import com.exam.notification.email.service.EmailService;

public class TemplateFinder {

    public static String findTemplatePathByEmailType(EmailService.EmailType emailType) throws TemplateNotFoundException{
        switch (emailType) {
            case PASSWORD_RESET: return TemplatePaths.PASSWORD_RESET;
            case PASSWORD_UPDATED:return TemplatePaths.PASSWORD_UPDATED;
            default: throw new TemplateNotFoundException("Template not found");
        }
    }
}
