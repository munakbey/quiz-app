package com.quiz.service;

import java.util.Optional;

public interface BaseService<RETURN_TYPE, PARAMETER_TYPE> {
    RETURN_TYPE save(PARAMETER_TYPE entity);
    Optional<RETURN_TYPE> findById(long id);

}
