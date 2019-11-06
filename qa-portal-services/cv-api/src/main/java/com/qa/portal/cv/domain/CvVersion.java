package com.qa.portal.cv.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cv_version")
public class CvVersion {

    @Id
    private String id;

    private Integer versionNumber;

    private String status;

    private String userName;

    private String firstName;

    private String surname;

    private String fullName;

    private String cohort;

    private Profile profile;

    private List<Skills> allSkills;

    private List<Qualification> allQualifications;

    private List<WorkExperience> allWorkExperience;
    
    private List<WorkExperience> otherWorkExperience;

    private String sourceControlLink;

    private Hobbies hobbies;

    public CvVersion() {
        super();
    }
//
//    public CvVersion(String id, Integer versionNumber, String status, String userName, String firstName, String surname,
//                     String fullName, String cohort, Profile profile, List<Skills> allSkills,
//                     List<Qualification> allQualifications, List<WorkExperience> allWorkExperience, Hobbies hobbies) {
//        super();
//        this.id = id;
//        this.versionNumber = versionNumber;
//        this.status = status;
//        this.userName = userName;
//        this.firstName = firstName;
//        this.surname = surname;
//        this.cohort = cohort;
//        this.profile = profile;
//        this.allSkills = allSkills;
//        this.allQualifications = allQualifications;
//        this.allWorkExperience = allWorkExperience;
//        this.hobbies = hobbies;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public String getUserName() {
		return userName;
	}

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFullName() {
        if (fullName == null) {
            fullName = this.firstName + " " + this.surname;
        }
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCohort() {
        return cohort;
    }

    public void setCohort(String cohort) {
        this.cohort = cohort;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Skills> getAllSkills() {
        return allSkills;
    }

    public void setAllSkills(List<Skills> allSkills) {
        this.allSkills = allSkills;
    }

	public List<Qualification> getAllQualifications() {
		return allQualifications;
	}

	public void setAllQualifications(List<Qualification> allQualifications) {
		this.allQualifications = allQualifications;
	}

	public List<WorkExperience> getAllWorkExperience() {
		return allWorkExperience;
	}

	public void setAllWorkExperience(List<WorkExperience> allWorkExperience) {
		this.allWorkExperience = allWorkExperience;
	}

	public Hobbies getHobbies() {
		return hobbies;
	}

	public void setHobbies(Hobbies hobbies) {
		this.hobbies = hobbies;
	}

	public List<WorkExperience> getOtherWorkExperience() {
		return otherWorkExperience;
	}

	public void setOtherWorkExperience(List<WorkExperience> otherWorkExperience) {
		this.otherWorkExperience = otherWorkExperience;
	}

	public String getSourceControlLink() {
		return sourceControlLink;
	}

	public void setSourceControlLink(String sourceControlLink) {
		this.sourceControlLink = sourceControlLink;
	}
}
