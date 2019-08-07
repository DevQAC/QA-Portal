package com.qa.portal.common.scheduler;
import com.qa.portal.common.emails.QaEmailClient;
import org.springframework.scheduling.annotation.Scheduled;

public class QaEmailScheduler {
    private QaEmailClient qec;

    public QaEmailScheduler(){
        this.qec = new QaEmailClient();
    }

    //scheduler that should run by 1am and send out emails
    @Scheduled(cron = "0 1 1 * * *")//cron 9am
    public void sendScheduledEmails(String emailAddress, String emailSubject, String emailBody){
        //method call to send email
        qec.sendEmail(emailAddress,emailSubject,emailBody);
    }


}