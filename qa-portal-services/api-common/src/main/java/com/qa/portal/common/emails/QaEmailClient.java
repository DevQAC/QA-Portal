import org.springframework.mail.javamail.javamailsender;
import org.springframework.mail.SimpleMailMessage;

public class QaEmailClient {

    @Autowired
    private JavaMailSender javaMailSender;
	
	void sendEmail(string emailAddress,string emailSubject,string emailBody) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailAddress);

        msg.setSubject(emailSubject);
        msg.setText(emailBody);

        javaMailSender.send(msg);

    }

}