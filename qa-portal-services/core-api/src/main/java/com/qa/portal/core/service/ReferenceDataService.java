package com.qa.portal.core.service;

import static com.qa.portal.core.CoreConstants.SUPER_USER_ROLE;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.qa.portal.common.persistence.entity.TechnologyEntity;
import com.qa.portal.common.persistence.repository.TechnologyRepository;


public class ReferenceDataService {
	

	  //TODO - Maybe Cache this info for performance
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationService.class);
    
    private TechnologyRepository Techrepo;

    @Autowired
    public void ReferenceDataService(TechnologyRepository Techrepo) {
        this.Techrepo = Techrepo;
    }

    @Transactional
//    public List<DepartmentApplicationsDto> getApplicationsByDepartment(Collection<String> userRoles) {
//        Set<DepartmentRoleApplicationEntity> dras = getDeptRoleApps(userRoles);
//        return applicationServiceMapper.createDepartmentsApplicationsDto(dras, userRoles);
//    }
//
//    private Set<DepartmentRoleApplicationEntity> getDeptRoleApps(Collection<String> userRoles) {
//        return departmentRoleRepository.findAll().stream()
//                .filter(dr -> userRoles.contains(dr.getDepartmentRoleName()) || userRoles.contains(SUPER_USER_ROLE))
//                .flatMap(dr -> dr.getDeptRoleApplications().stream())
//                .collect(Collectors.toSet());
//    }
//    private List<TechnologyEntity> getReferenceData(Collection<String> userRoles) {
//        return Techrepo.findAll().stream()
//                .filter(dr -> userRoles.contains(dr.getDepartmentRoleName()) || userRoles.contains(SUPER_USER_ROLE))
//                .flatMap(dr -> dr.getReferenceData().stream())
//                .collect(Collectors.toSet());
//    }
     private List<TechnologyEntity> getReferenceDataForCategories(List<String> refDataCategories) {
		List<TechnologyEntity> p = Techrepo.findAll();
		return p;
}
}
