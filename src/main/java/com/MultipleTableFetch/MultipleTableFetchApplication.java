package com.MultipleTableFetch;

import lombok.var;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.Properties;


@EnableSwagger2
@SpringBootApplication
//@Import(springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class)
public class MultipleTableFetchApplication {

    private static final String KEYNAME = "Authorization";

    public static void main(String[] args) {
        SpringApplication.run(MultipleTableFetchApplication.class, args);
    }

    /*@Bean
    public ResourceBundleMessageSource bundleMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("message.properties");
        return messageSource;
    }*/

    @Bean
    public ResourceBundleMessageSource messageSource() {
        var source = new ResourceBundleMessageSource();
        source.setBasenames("messages/label");
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(25);
        mailSender.setUsername("admin@gmail.com");
        mailSender.setPassword("password");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return mailSender;
    }
/*
    Parameter authHeader = new ParameterBuilder()
            .parameterType("header")
            .name("Authorization")
            .modelRef(new ModelRef("string"))
            .build();*/
@Bean
public SimpleMailMessage emailTemplate()
{
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo("somebody@gmail.com");
    message.setFrom("admin@gmail.com");
    message.setText("FATAL - Application crash. Save your job !!");
    return message;
}
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.MultipleTableFetch.Controller"))
                .paths(PathSelectors.any())
                .build();
        //.globalOperationParameters(Collections.singletonList(authHeader));
    }
}