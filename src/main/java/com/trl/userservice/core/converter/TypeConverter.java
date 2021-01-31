package com.trl.userservice.core.converter;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public interface TypeConverter<S, T> {

    Class<S> getSourceClass();

    Class<T> getTargetClass();

    T convert(S source);

    default List<T> convert(Collection<S> sourceList) {
        return sourceList
                .stream()
                .map(this::convert)
                .collect(toList());
    }

}

