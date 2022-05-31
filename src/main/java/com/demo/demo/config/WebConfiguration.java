package com.demo.demo.config;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.TimeZone;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    final Environment environment;

    public WebConfiguration(Environment environment) {
        this.environment = environment;
    }

//    @Bean
//    public ThymeleafViewResolver thymeleafViewResolver(
//            @Autowired SpringTemplateEngine templateEngine
//    ) {
//        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
//        thymeleafViewResolver.setTemplateEngine( templateEngine );
//        thymeleafViewResolver.setCharacterEncoding( "UTF-8" );
//        thymeleafViewResolver.addStaticVariable( "jpegHelper", 1);
//        return thymeleafViewResolver;
//    }
//
//    @Bean
//    public JavaMailSender javaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost(environment.getProperty("spring.mail.host"));
//        mailSender.setPort(587);
//        mailSender.setUsername(environment.getProperty("spring.mail.username"));
//        mailSender.setPassword(environment.getProperty("spring.mail.password"));
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");
//        return mailSender;
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*").exposedHeaders("*");
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
        return jacksonObjectMapperBuilder ->
                jacksonObjectMapperBuilder.timeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
    }
}
