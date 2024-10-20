package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {

        @Autowired
        private AdminConfig adminConfig;

        @Autowired
        @Qualifier("templateEngine")
        private TemplateEngine templateEngine;

        public String buildTrelloCardEmail(String message) {
            Context context = new Context();
            context.setVariable("message", message);
            context.setVariable("tasks_url", "https://twardy78.github.io/");
            context.setVariable("button", "Visit website");
            context.setVariable("admin_name", adminConfig.getAdminName());
            context.setVariable("company_name", adminConfig.getCompName());
            context.setVariable("company_email", adminConfig.getCompEmail());
            context.setVariable("company_phone", adminConfig.getCompPhone());
            return templateEngine.process("mail/created-trello-card-mail", context);
        }
}
