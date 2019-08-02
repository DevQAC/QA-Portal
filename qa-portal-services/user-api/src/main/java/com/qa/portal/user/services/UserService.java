package com.qa.portal.common.services;

import java.util.List;
import java.util.stream.Collectors;

import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.persistence.repository.QaTraineeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qa.portal.common.dto.QaCohortDto;
import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.entity.TrainerEntity;
import com.qa.portal.common.persistence.repository.QaTrainerRepository;
import com.qa.portal.common.util.mapper.CohortMapper;

@Service
public class UserService {

    private QaTrainerRepository trainerRepo;

    private CohortMapper mapper;
    private QaTraineeRepository qaTraineeRepository;
    public UserService(QaTrainerRepository trainerRepo, CohortMapper mapper, QaTraineeRepository qaTraineeRepository) {
        super();
        this.trainerRepo = trainerRepo;
        this.mapper = mapper;
        this.qaTraineeRepository = qaTraineeRepository;
    }


    @Transactional
    public QaCohortDto getCohortForTrainee(String name) {
        return mapper.mapToQaCohortDto(this.qaTraineeRepository.findByUserName(name)
                .orElseThrow(() -> new QaResourceNotFoundException("Cohort with that name does not exist")).getCohort());
    }

    @Transactional
    public List<QaCohortDto> getCohortsForTrainer(Integer id) {
        TrainerEntity trainer = this.trainerRepo.findById(id)
                .orElseThrow(() -> new QaResourceNotFoundException("Trainer not found"));
        return trainer.getCohorts().stream().map(this.mapper::mapToQaCohortDto).collect(Collectors.toList());
    }

}
