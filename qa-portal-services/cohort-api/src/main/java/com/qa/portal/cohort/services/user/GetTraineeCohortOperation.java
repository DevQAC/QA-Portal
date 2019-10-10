package com.qa.portal.cohort.services.user;

import com.qa.portal.common.dto.QaCohortDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.repository.QaTraineeRepository;
import com.qa.portal.cohort.services.mapper.CohortMapper;
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

    public QaCohortDto getCohortForTrainee(String traineeUserName) {
        return traineeRepository.findByUserName(traineeUserName)
                .map(t -> t.getCohort())
                .map(c -> cohortMapper.mapToQaCohortDto(c))
                .orElseThrow(() -> new QaPortalBusinessException("Trainee not assigned to a cohort. Contact the Portal administrator."));
    }
}
