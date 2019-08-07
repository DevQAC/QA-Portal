import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

public class QaEmailClient {

    private JavaMailSender javaMailSender;
    
    @Autowired
	public void sendEmail(String emailAddress,String emailSubject,String emailBody) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailAddress);

        msg.setSubject(emailSubject);
        msg.setText(emailBody);

        javaMailSender.send(msg);

    }

}