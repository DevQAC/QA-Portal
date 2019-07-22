package com.qa.portal.common.util.mapper;

import com.qa.portal.common.dto.QaUserDto;
import com.qa.portal.common.persistence.entity.QaUserEntity;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseMapper {

    @Autowired
    private DozerBeanMapper mapper;


    public <S, T> T mapObject(S object, Class<T> clazz) {
        return mapper.map(object, clazz);
    }

    public QaUserEntity mapToQaUserEntity(QaUserDto qaUserDto) {
        return mapper.map(qaUserDto, QaUserEntity.class);
    }

    public QaUserDto mapToQaUserDto(QaUserEntity qaUserEntity) {
        return mapper.map(qaUserEntity, QaUserDto.class);
    }
}
