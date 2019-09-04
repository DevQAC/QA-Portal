package com.qa.portal;

import com.qa.portal.admin.services.KeyCloakAdminClient;

//@SpringBootApplication()
public class AdminApiApplication {

    public static void main(String[] args) {
    	KeyCloakAdminClient kcac = new KeyCloakAdminClient();
    	kcac.build();
//    	kcac.createUser("newtest2", "test", "test", "test2@test.test");
//    	kcac.createRole("new-rolez");
//    	kcac.addRoleToUser("newtest", "new-rolez");
//    	kcac.createCohort("July_Intake");
    	kcac.test("hr-admin");
    }
}
