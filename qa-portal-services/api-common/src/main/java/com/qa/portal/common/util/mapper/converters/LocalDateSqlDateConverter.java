package com.qa.portal.common.util.mapper.converters;

import org.dozer.DozerConverter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

public class LocalDateSqlDateConverter extends DozerConverter<LocalDate, Date> {

    public LocalDateSqlDateConverter() {
        super(LocalDate.class, Date.class);
    }

    public Date convertTo(LocalDate localDate,Date date) {
        return Optional.ofNullable(localDate).map(i -> Date.valueOf(i)).orElseGet(() -> null);
    }

    @Override
    public LocalDate convertFrom(Date date, LocalDate localDate) {
        return Optional.ofNullable(date).map(t -> t.toLocalDate()).orElseGet(() -> null);
    }
}
