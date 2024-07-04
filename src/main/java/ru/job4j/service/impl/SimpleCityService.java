package ru.job4j.service.impl;

import org.springframework.stereotype.Service;
import ru.job4j.model.City;
import ru.job4j.repository.CityRepository;
import ru.job4j.service.CityService;

import java.util.Collection;

@Service
public class SimpleCityService implements CityService {
    private final CityRepository repository;

    public SimpleCityService(CityRepository sql2oCityRepository) {
        this.repository = sql2oCityRepository;
    }

    @Override
    public Collection<City> findAll() {
        return repository.findAll();
    }
}
