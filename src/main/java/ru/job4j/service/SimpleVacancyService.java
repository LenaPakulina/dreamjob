package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.model.Vacancy;
import ru.job4j.repository.MemoryVacancyRepository;
import ru.job4j.repository.VacancyRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class SimpleVacancyService implements VacancyService {

    private final VacancyRepository repository;

    public SimpleVacancyService(VacancyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vacancy save(Vacancy vacancy) {
        return repository.save(vacancy);
    }

    @Override
    public Vacancy deleteById(int id) {
        return repository.deleteById(id);
    }

    @Override
    public boolean update(Vacancy vacancy) {
        return repository.update(vacancy);
    }

    @Override
    public Optional<Vacancy> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Collection<Vacancy> findAll() {
        return repository.findAll();
    }
}
