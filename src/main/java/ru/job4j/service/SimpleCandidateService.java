package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.model.Candidate;
import ru.job4j.repository.CandidateRepository;
import ru.job4j.repository.MemoryCandidateRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class SimpleCandidateService implements CandidateService {

    private final CandidateRepository repository;

    public SimpleCandidateService(CandidateRepository repository) {
        this.repository = repository;
    }

    @Override
    public Candidate save(Candidate candidate) {
        return repository.save(candidate);
    }

    @Override
    public Candidate deleteById(int id) {
        return repository.deleteById(id);
    }

    @Override
    public boolean update(Candidate candidate) {
        return repository.update(candidate);
    }

    @Override
    public Optional<Candidate> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Collection<Candidate> findAll() {
        return repository.findAll();
    }
}
