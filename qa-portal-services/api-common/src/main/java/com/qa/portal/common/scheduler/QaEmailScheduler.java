package com.qa.portal.common.scheduler;
import com.qa.portal.common.emails.QaEmailClient;
import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import com.qa.portal.common.persistence.repository.CohortCourseRepository;
import com.qa.portal.common.util.mapper.converters.LocalDateSqlDateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Date;
import java.util.List;


public class QaEmailScheduler {

    private QaEmailClient qec;

    private CohortCourseRepository cohortCourseRepository;

    private LocalDateSqlDateConverter localDateSqlDateConverter;

    @Autowired
    public QaEmailScheduler(QaEmailClient qec) {
        this.qec = qec;
    }

    //scheduler that should run by 1am and send out emails
    @Scheduled(cron = "0 1 1 * * *")//cron 9am
    public void sendScheduledEmails(String emailAddress, String emailSubject, String emailBody){
        //method call to send email
        qec.sendEmail(emailAddress,emailSubject,emailBody);
    }



    public List<CohortCourseRepository> checkDateAndSendEmail(Date endDate){
        List<CohortCourseEntity> ccr = cohortCourseRepository.findByEndDate(endDate);

        for(int i=0;i<ccr.size();i++){
            ccr.get(i).getCohort().getTrainees().stream()
                    .forEach(t -> sendScheduledEmails(t.getUserName(),"",""));
        }


        for(int i=0;i<ccr.size();i++){
            ccr.get(i).getCohort().getTrainees().stream()
        .forEach(t -> System.out.println(t.getUserName()));
        }
//        if(ccr.contains()){
//
//        }

        return null;
    }

}