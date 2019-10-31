package com.qa.portal.common.persistence.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "TRAINING_ADMIN")
public class TrainingAdminEntity extends QaUserEntity {

}
