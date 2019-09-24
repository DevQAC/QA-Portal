package com.qa.portal.cohort.services.user;

import com.qa.portal.common.dto.QaCohortDto;
import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.repository.QaTraineeRepository;
import com.qa.portal.common.util.mapper.CohortMapper;
import org.springframework.stereotype.Component;

@Component
public class GetTraineeCohortOperation {

    private CohortMapper cohortMapper;

    private QaTraineeRepository traineeRepository;

    public GetTraineeCohortOperation(CohortMapper cohortMapper,
                                     QaTraineeRepository traineeRepository) {
        this.cohortMapper = cohortMapper;
        this.traineeRepository = traineeRepository;
    }

    public QaCohortDto getCohortForTrainee(String name) {
        return cohortMapper.mapToQaCohortDto(traineeRepository.findByUserName(name)
                .orElseThrow(() -> new QaResourceNotFoundException("Cohort with that name does not exist")).getCohort());
    }
}
