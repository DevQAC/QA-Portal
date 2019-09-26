package com.qa.portal.application.scheduler;

import com.qa.portal.common.persistence.repository.CohortCourseRepository;
import com.qa.portal.common.email.QaEmailClient;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Date;
import java.time.LocalDate;

public class QaEmailScheduler {

    private QaEmailClient qec;

    private CohortCourseRepository cohortCourseRepository;

    public QaEmailScheduler(QaEmailClient qec,
                            CohortCourseRepository cohortCourseRepository) {
        this.qec = qec;
        this.cohortCourseRepository = cohortCourseRepository;
    }

    @Scheduled(cron = "0 1 1 * * *")
    public void sendEvaluationFormEmailReminder(Date endDate){
        LocalDate currentDate = LocalDate.now();
        cohortCourseRepository.findByEndDate(Date.valueOf(currentDate))
                .stream()
                .flatMap(cce -> cce.getCohort().getTrainees().stream())
                .forEach(t -> qec.sendEmail(t.getUserName(), "Course Evaluation", "Please fill in your course evaluation"));
    }
}