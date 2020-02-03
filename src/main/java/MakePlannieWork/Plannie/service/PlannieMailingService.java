package MakePlannieWork.Plannie.service;

import MakePlannieWork.Plannie.model.Gebruiker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Service
@Component
public class PlannieMailingService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MessageSource messages;

    public void maakWachtwoordResetTokenEmail(final String contextPath, final Locale locale, final String token, final Gebruiker gebruiker) throws MessagingException {
        final String url = contextPath + "/wijzigWachtwoord?id=" + gebruiker.getIdentifier() + "&token=" + token;
        final String message = "Reset wachtwoord";
        sendEmail(gebruiker.getEmail(), message + " \r\n" + url, "Wachtwoord resetten");
    }

    public void sendEmail(String toWhom, String content, String subject) throws MessagingException {
        final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        message.setFrom(from);
        message.setTo(toWhom);
        message.setSubject(subject);
        message.setText(content, true);

        javaMailSender.send(mimeMessage);
    }

    public String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    public void maakGebruikerVerificatieTokenEmail(final String contextPath, final Locale locale, final String token, final Gebruiker gebruiker) throws MessagingException {
        final String url = contextPath + "/maakRegistratieCompleet?id=" + gebruiker.getIdentifier() + "&token=" + token;
        final String message = "Maak je registratie compleet";
        sendEmail(gebruiker.getEmail(), message + " \r\n" + url, "Registratie Plannie");
    }

}
