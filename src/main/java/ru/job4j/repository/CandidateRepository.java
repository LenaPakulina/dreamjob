package ru.job4j.repository;

import ru.job4j.model.Candidate;
import ru.job4j.model.Vacancy;

import java.util.Collection;
import java.util.Optional;

public interface CandidateRepository {
    Candidate save(Candidate candidate);

    boolean deleteById(int id);

    boolean update(Candidate candidate);

    Optional<Candidate> findById(int id);

    Collection<Candidate> findAll();
}
