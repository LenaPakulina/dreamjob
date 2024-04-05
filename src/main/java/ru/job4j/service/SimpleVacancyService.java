package ru.job4j.service;

import ru.job4j.model.Vacancy;
import ru.job4j.repository.MemoryVacancyRepository;
import ru.job4j.repository.VacancyRepository;

import java.util.Collection;
import java.util.Optional;

public class SimpleVacancyService implements VacancyService {

    private static final SimpleVacancyService INSTANCE = new SimpleVacancyService();

    private final VacancyRepository repository = MemoryVacancyRepository.getInstance();

    public SimpleVacancyService() {
    }

    public static SimpleVacancyService getInstance() {
        return INSTANCE;
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