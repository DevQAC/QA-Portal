package com.qa.portal.cohort.services.user;

import com.qa.portal.cohort.dto.user.UserSkillsDto;
import com.qa.portal.common.dto.TechnologyDto;
import com.qa.portal.common.persistence.entity.CourseEntity;
import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.persistence.entity.TechnologyEntity;
import com.qa.portal.common.persistence.entity.TraineeEntity;
import com.qa.portal.common.persistence.repository.QaTraineeRepository;
import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.common.service.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class GetTraineeSkillsOperation {

    private QaTraineeRepository traineeRepository;

    private BaseMapper baseMapper;


    public GetTraineeSkillsOperation(QaTraineeRepository traineeRepository,
                                     BaseMapper baseMapper) {
        this.traineeRepository = traineeRepository;
        this.baseMapper = baseMapper;
    }

    public UserSkillsDto getSkillsForTrainee(QaSecurityContext qaSecurityContext) {
        UserSkillsDto userSkillsDto = new UserSkillsDto();
        userSkillsDto.setSkills(traineeRepository.findByUserName(qaSecurityContext.getUserName())
                .map(t -> getSkillsForTrainee(t))
                .orElseGet(() -> Collections.emptyMap()));
        userSkillsDto.setUserName(qaSecurityContext.getUserName());
        userSkillsDto.setUserFirstName(qaSecurityContext.getFirstName());
        userSkillsDto.setUserLastName(qaSecurityContext.getSurname());
        return userSkillsDto;
    }

    private Map<String, Set<TechnologyDto>> getSkillsForTrainee(TraineeEntity traineeEntity) {
        return Optional.ofNullable(traineeEntity.getCohort())
                .map(ce -> getSkillsForCohort(ce))
                .orElseGet(() -> Collections.emptyMap());
    }

    private Map<String, Set<TechnologyDto>> getSkillsForCohort(QaCohortEntity cohortEntity) {
        return cohortEntity.getCohortCourses().stream()
                .flatMap(cce -> getTechnologiesForCourse(cce.getCourse()).stream())
                .collect(Collectors.groupingBy(TechnologyWithCategoryDto::getTechnologyCategoryName,
                        Collectors.mapping(TechnologyWithCategoryDto::getTechnologyCategoryDto, Collectors.toSet())));
    }

    private List<TechnologyWithCategoryDto> getTechnologiesForCourse(CourseEntity courseEntity) {
        return courseEntity.getCourseTechnologies().stream()
                .map(cte -> getTechnologyWithCategory(cte.getTechnology()))
                .collect(Collectors.toList());
    }

    private TechnologyWithCategoryDto getTechnologyWithCategory(TechnologyEntity technologyEntity) {
        TechnologyDto technologyDto = baseMapper.mapObject(technologyEntity, TechnologyDto.class);
        return new TechnologyWithCategoryDto(technologyEntity.getTechnologyCategory().getSearchString(), technologyDto);
    }

    // Class created for convenience to make it easier to group the technology categories and get the sets of technologies for those categories.
    // Alternative would have been to write a Collector interface implementation which would have been more complicated.
    private static class TechnologyWithCategoryDto {
        private String technologyCategoryName;

        private TechnologyDto technologyDto;

        private TechnologyWithCategoryDto(String technologyCategoryName, TechnologyDto technologyDto) {
            this.technologyDto = technologyDto;
            this.technologyCategoryName = technologyCategoryName;
        }

        public String getTechnologyCategoryName() {
            return technologyCategoryName;
        }

        public TechnologyDto getTechnologyCategoryDto() {
            return technologyDto;
        }
    }
}
