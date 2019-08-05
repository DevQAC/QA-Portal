package com.qa.portal.common.util.mapper.converters;

import org.dozer.DozerConverter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

public class SqlTimestampInstantConverter extends DozerConverter<Timestamp, Instant> {

    public SqlTimestampInstantConverter() {
        super(Timestamp.class, Instant.class);
    }

    @Override
    public Instant convertTo(Timestamp timestamp, Instant instant) {
        return Optional.ofNullable(timestamp).map(t -> t.toInstant()).orElseGet(() -> null);
    }

    @Override
    public Timestamp convertFrom(Instant instant, Timestamp timestamp) {
        return Optional.ofNullable(instant).map(i -> Timestamp.from(i)).orElseGet(() -> null);
    }
}
