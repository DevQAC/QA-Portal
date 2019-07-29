package com.qa.portal.reflection.util.mapper.converter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import org.dozer.DozerConverter;

public class DateToLocalDateConverter extends DozerConverter<Date, LocalDate> {

	public DateToLocalDateConverter() {
		super(Date.class, LocalDate.class);
	}

	@Override
	public LocalDate convertTo(Date source, LocalDate destination) {
		return Optional.ofNullable(source).map(Date::toLocalDate)
				.orElseThrow(() -> new IllegalStateException("Unknown value"));
	}

	@Override
	public Date convertFrom(LocalDate source, Date destination) {
		return Optional.ofNullable(source).map(s -> Date.valueOf(s))
				.orElseThrow(() -> new IllegalStateException("Unknown value"));
	}

}
