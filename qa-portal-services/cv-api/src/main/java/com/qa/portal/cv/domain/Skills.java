package com.qa.portal.cv.domain;

import java.util.List;

public class Skills {

    private List<String> programmingLanguages;

    private List<String> ides;

    private List<String> operatingSystems;

    private List<String> devops;

    private List<String> databases;

    private List<String> platforms;

    private List<String> other;

	public Skills() {
		super();
	}

    public List<String> getProgrammingLanguages() {
        return programmingLanguages;
    }

    public void setProgrammingLanguages(List<String> programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }

    public List<String> getIdes() {
        return ides;
    }

    public void setIdes(List<String> ides) {
        this.ides = ides;
    }

    public List<String> getOperatingSystems() {
        return operatingSystems;
    }

    public void setOperatingSystems(List<String> operatingSystems) {
        this.operatingSystems = operatingSystems;
    }

    public List<String> getDevops() {
        return devops;
    }

    public void setDevops(List<String> devops) {
        this.devops = devops;
    }

    public List<String> getDatabases() {
        return databases;
    }

    public void setDatabases(List<String> databases) {
        this.databases = databases;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    public List<String> getOther() {
        return other;
    }

    public void setOther(List<String> other) {
        this.other = other;
    }
}
