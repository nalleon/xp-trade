package es.iespuertodelacruz.xptrade.shared.config;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

@Configuration
public class MailConfig{

    @Value("${mail.from}") private String mailfrom;
    @Value("${mail.password}") private String mailpassword;

    @Bean
    public JavaMailSender getJavaMailSender(){

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.gmail.com");
        sender.setPort(587);
        sender.setUsername(mailfrom);
        sender.setPassword(mailpassword);

        Properties props = sender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.starttls.enable", "true");

        return sender;
    }
}