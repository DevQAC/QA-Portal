package com.qa.portal.core.service;


//public class ReferenceDataService {
	

	  //TODO - Maybe Cache this info for performance
//    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationService.class);
//    
//    private TechnologyRepository Techrepo;
//
//    @Autowired
//    public void ReferenceDataService(TechnologyRepository Techrepo) {
//        this.Techrepo = Techrepo;
//    }
//
//    @Transactional
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
//     private List<TechnologyEntity> getReferenceDataForCategories(List<String> refDataCategories) {
//		List<TechnologyEntity> p = Techrepo.findAll();
//		return p;
//}

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.qa.portal.core.service.ReferenceDataManager;


@Service
public class ReferenceDataService {

    private ReferenceDataManager referenceDataManager;

    public ReferenceDataService(ReferenceDataManager referenceDataManager) {
        this.referenceDataManager = referenceDataManager;
    }


    public Map<String, List<String>> getReferenceDataForCategories(List<String> refDataCategories){
        return referenceDataManager.getReferenceDataForCategories(refDataCategories);
    }

}
