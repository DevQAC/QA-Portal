package com.qa.portal.common.scheduler;
import com.qa.portal.common.emails.QaEmailClient;
import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import com.qa.portal.common.persistence.repository.CohortCourseRepository;
import com.qa.portal.common.util.mapper.converters.LocalDateSqlDateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Date;
import java.util.Calendar;
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


    @Scheduled(cron = "0 1 1 * * *")//cron 9am
    public List<CohortCourseRepository> checkDateAndSendEmail(Date endDate){
        List<CohortCourseEntity> ccr = cohortCourseRepository.findByEndDate(endDate);

//      java.util.Date
        java.util.Date currentDate = Calendar.getInstance().getTime();

//      java.sql.Date
        Calendar calendar = Calendar.getInstance();
        java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());

        //gets date and compares them before sending the emails
        for(int i=0;i<ccr.size();i++){
            if(ccr.get(i).getEndDate() == ourJavaDateObject){
                ccr.get(i).getCohort().getTrainees().stream()
                        .forEach(t -> qec.sendEmail(t.getUserName(),"",""));
            }

        }

//        sample for loop to sebd email to each user
//        for(int i=0;i<ccr.size();i++){
//            ccr.get(i).getCohort().getTrainees().stream()
//        .forEach(t -> System.out.println(t.getUserName()));
//        }
////        if(ccr.contains()){
////
////        }

        return null;
    }

}