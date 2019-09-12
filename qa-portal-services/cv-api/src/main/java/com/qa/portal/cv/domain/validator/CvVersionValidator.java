package com.qa.portal.cv.domain.validator;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.cv.domain.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CvVersionValidator {

    private static final String MAX_WORK_EXPERIENCE_DETAIL_LENGTH_PROP = "cv.workexperience.detail.max";

    private static final String MAX_QUALIFICATION_DETAIL_LENGTH_PROP = "cv.qualification.detail.max";

    private static final String MAX_WORK_EXPERIENCE_ENTRIES_PROP = "cv.workexperience.entries.max";

    private static final String MAX_QUALIFICATION_ENTRIES_PROP = "cv.qualification.entries.max";

    private static final String MAX_PROFILE_DETAIL_LENGTH_PROP = "cv.profile.detail.max";

    private static final String MAX_HOBBIES_DETAIL_LENGTH_PROP = "cv.hobbies.detail.max";

    private Environment environment;

    public CvVersionValidator(Environment environment) {
        this.environment = environment;
    }

    public void validateCvVersion(CvVersion cvVersion) {
        validateProfile(cvVersion.getProfile());
        validateHobbies(cvVersion.getHobbies());
        validateWorkExperiences(cvVersion.getAllWorkExperience());
        validateQualifications(cvVersion.getAllQualifications());
    }

    private void validateProfile(Profile profile) {
        if (profile.getProfileDetails().length() > Integer.parseInt(environment.getProperty(MAX_PROFILE_DETAIL_LENGTH_PROP))) {
            throw new QaPortalBusinessException("Profile details exceed maximum length");
        }
    }

    private void validateHobbies(Hobbies hobbies) {
        if (hobbies.getHobbiesDetails().length() > Integer.parseInt(environment.getProperty(MAX_HOBBIES_DETAIL_LENGTH_PROP))) {
            throw new QaPortalBusinessException("Hobbies details exceed maximum length");
        }
    }

    private void validateWorkExperiences(List<WorkExperience> workExperiences) {
        workExperiences.stream()
                .forEach(w -> validateWorkExperience(w));
        if (workExperiences.stream().count() > Integer.parseInt(environment.getProperty(MAX_WORK_EXPERIENCE_ENTRIES_PROP))) {
            throw new QaPortalBusinessException("Number of work experience entries exceeds the maximum allowed");
        }
    }

    private void validateWorkExperience(WorkExperience workExperience) {
        if (workExperience.getWorkExperienceDetails().length() > Integer.parseInt(environment.getProperty(MAX_WORK_EXPERIENCE_DETAIL_LENGTH_PROP))) {
            throw new QaPortalBusinessException("Work experience detail exceeds maximum length");
        }
    }

    private void validateQualifications(List<Qualification> qualifications) {
        qualifications.stream()
                .forEach(q -> validateQualification(q));
        if (qualifications.stream().count() > Integer.parseInt(environment.getProperty(MAX_QUALIFICATION_ENTRIES_PROP))) {
            throw new QaPortalBusinessException("Number of qualification entries exceeds the maximum allowed");
        }
    }

    private void validateQualification(Qualification qualification) {
        if (qualification.getQualificationDetails().length() > Integer.parseInt(environment.getProperty(MAX_QUALIFICATION_DETAIL_LENGTH_PROP))) {
            throw new QaPortalBusinessException("Qualification detail exceeds maximum length");
        }
    }
}
