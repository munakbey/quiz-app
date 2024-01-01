package com.quiz.service;

import com.quiz.model.Option;

import java.util.List;
import java.util.Set;

public interface OptionService extends BaseService<Option,Option>{

    List<Option> getAll();
    List<Option> saveAll(List<Option> options);
    Set<Option> getAllById(Set<Long> optionIds);
    Option update(Option entity, long id);
}
