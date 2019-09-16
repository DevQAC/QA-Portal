package com.qa.portal.user.services;

import com.qa.portal.common.dto.QaCohortDto;
import com.qa.portal.common.dto.TechnologyDto;
import com.qa.portal.common.dto.TraineeDto;
import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.entity.TrainerEntity;
import com.qa.portal.common.persistence.repository.QaTraineeRepository;
import com.qa.portal.common.persistence.repository.QaTrainerRepository;
import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.common.util.mapper.CohortMapper;
import com.qa.portal.user.dto.UserSkillsDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    private QaTrainerRepository trainerRepo;

	private QaTraineeRepository traineeRepo;

	private GetSkillsForTraineeOperation getSkillsForTraineeOperation;

    private CohortMapper cohortMapper;

    private Comparator<QaCohortDto> cohortComparator = (c1, c2) -> c1.getStartDate().isBefore(c2.getStartDate()) ? 1 : -1;

	public UserService(QaTrainerRepository trainerRepo,
					   QaTraineeRepository traineeRepo,
					   GetSkillsForTraineeOperation getSkillsForTraineeOperation,
					   CohortMapper cohortMapper) {
		this.trainerRepo = trainerRepo;
		this.traineeRepo = traineeRepo;
		this.getSkillsForTraineeOperation = getSkillsForTraineeOperation;
		this.cohortMapper = cohortMapper;
	}

	@Transactional
	public UserSkillsDto getSkillsForTrainee(QaSecurityContext qaSecurityContext) {
		return getSkillsForTraineeOperation.getSkillsForTrainee(qaSecurityContext);
	}

	@Transactional
    public QaCohortDto getCohortForTrainee(String name) {
        return cohortMapper.mapToQaCohortDto(this.traineeRepo.findByUserName(name)
                .orElseThrow(() -> new QaResourceNotFoundException("Cohort with that name does not exist")).getCohort());
    }

    @Transactional
    public List<QaCohortDto> getCohortsForTrainer(String userName) {
        TrainerEntity trainer = this.trainerRepo.findByUserName(userName)
                .orElseThrow(() -> new QaResourceNotFoundException("Trainer not found"));
        return trainer.getCohorts()
				.stream()
				.map(this.cohortMapper::mapToQaCohortDto)
				.sorted(cohortComparator)
				.collect(Collectors.toList());
    }

	@Transactional
	public TraineeDto getTraineeById(Integer id) {
		return this.cohortMapper.mapToQaTraineeDto(this.traineeRepo.findById(id)
				.orElseThrow(() -> new QaResourceNotFoundException("Trainee not found")));
	}
}
