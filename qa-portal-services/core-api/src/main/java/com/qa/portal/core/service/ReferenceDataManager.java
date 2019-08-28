package com.qa.portal.core.service;

import java.util.List;
import java.util.Map;

import com.qa.portal.common.persistence.entity.TechnologyEntity;
import com.qa.portal.common.persistence.repository.TechnologyRepository;

public class ReferenceDataManager {
	
	private TechnologyRepository repo;
	
	
	
	public Map<String, List<String>> getReferenceDataForCategories(List<String> refDataCategories) {
		List<TechnologyEntity> p = repo.findAll();
		return p;
	}
	
	
	

//	  private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationService.class);
//
//	    private ApplicationServiceMapper applicationServiceMapper;
//
//	    private DepartmentRoleRepository departmentRoleRepository;
//
//	    @Autowired
//	    public ApplicationService(ApplicationServiceMapper applicationServiceMapper, DepartmentRoleRepository departmentRoleRepository) {
//	        this.applicationServiceMapper = applicationServiceMapper;
//	        this.departmentRoleRepository = departmentRoleRepository;
//	    }
//
//	    @Transactional
//	    public List<DepartmentApplicationsDto> getApplicationsByDepartment(Collection<String> userRoles) {
//	        Set<DepartmentRoleApplicationEntity> dras = getDeptRoleApps(userRoles);
	       
	 
}
