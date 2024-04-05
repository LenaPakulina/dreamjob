package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.model.City;
import ru.job4j.repository.CityRepository;
import ru.job4j.repository.MemoryCityRepository;

import java.util.Collection;

@Service
public class SimpleCityService implements CityService {

    private final CityRepository repository;

    public SimpleCityService(CityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<City> findAll() {
        return repository.findAll();
    }
}
