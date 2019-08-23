package com.qa.portal.cv.domain;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cv_version")
public class CvVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String versionNumber;
    private String status;
    private String userName;
    private String firstName;
    private String surname;
    private String fullName = firstName + surname;
    private String jobTitle;

    private Profile profile;
    private List<Skills> allSkills;
    private List<Qualification> allQualifications;
    private List<WorkExperience> allWorkExperience;
    private Hobbies hobbies;
    
	public CvVersion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CvVersion(Integer id, String versionNumber, String status, String userName, String firstName, String surname,
			String fullName, String jobTitle, Profile profile, List<Skills> allSkills,
			List<Qualification> allQualifications, List<WorkExperience> allWorkExperience, Hobbies hobbies) {
		super();
		this.id = id;
		this.versionNumber = versionNumber;
		this.status = status;
		this.userName = userName;
		this.firstName = firstName;
		this.surname = surname;
		this.fullName = fullName;
		this.jobTitle = jobTitle;
		this.profile = profile;
		this.allSkills = allSkills;
		this.allQualifications = allQualifications;
		this.allWorkExperience = allWorkExperience;
		this.hobbies = hobbies;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
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
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
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

}
