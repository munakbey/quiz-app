package com.quiz.service.serviceImpl;

import com.quiz.model.Option;
import com.quiz.repository.OptionRepository;
import com.quiz.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {
    private final OptionRepository repository;
    @Override
    public Option save(Option entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<Option> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Option update(Option entity, long id) {
        return repository.findById(id).map(item -> {
            item.setContent(entity.getContent());

            return repository.save(item);
        }).orElseThrow();
    }

    @Override
    public List<Option> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Option> saveAll(List<Option> options) {
        return repository.saveAll(options);
    }

    @Override
    public Set<Option> getAllById(Set<Long> optionIds) {
        return new HashSet<>(repository.findAllById(optionIds));
    }
}
