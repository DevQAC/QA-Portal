package com.qa.portal.common.dto;

import java.util.Objects;

public class QaUserDto extends QaBaseDto {
	
	private Integer id;

	private String userName;

	public QaUserDto(Integer id, String userName) {
		super();
		this.id = id;
		this.userName = userName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public QaUserDto() {
		super();
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		QaUserDto qaUserDto = (QaUserDto) o;
		return Objects.equals(id, qaUserDto.id) && Objects.equals(userName, qaUserDto.userName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), id, userName);
	}

	@Override
	public String toString() {
		return "QaUserDto{" + "id=" + id + ", userName='" + userName + '\'' + ", lastUpdatedTimestamp="
				+ lastUpdatedTimestamp + ", lastUpdatedBy='" + lastUpdatedBy + '\'' + '}';
	}
}
