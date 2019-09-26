package com.qa.portal.application.service.page;

import com.qa.portal.application.dto.ProjectPageDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectPageManagementService {

    private CreateProjectPageOperation createProjectPageOperation;

    private UpdateProjectPageOperation updateProjectPageOperation;

    public ProjectPageManagementService(CreateProjectPageOperation createProjectPageOperation,
                                        UpdateProjectPageOperation updateProjectPageOperation) {
        this.createProjectPageOperation = createProjectPageOperation;
        this.updateProjectPageOperation = updateProjectPageOperation;
    }

    @Transactional
    public ProjectPageDto createProjectPage(ProjectPageDto projectPageDto) {
        return createProjectPageOperation.createProjectPage(projectPageDto);
    }

    @Transactional
    public ProjectPageDto updateProjectPage(ProjectPageDto projectPageDto) {
        return updateProjectPageOperation.updateProjectPage(projectPageDto);
    }
}
