package com.qa.portal.common.service.mapper;

import com.qa.portal.common.dto.QaUserDto;
import com.qa.portal.common.persistence.entity.QaUserEntity;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class BaseMapper {

    private DozerBeanMapper mapper;

    public BaseMapper(DozerBeanMapper mapper) {
        this.mapper = mapper;
    }

    public DozerBeanMapper getMapper() {
        return mapper;
    }

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
