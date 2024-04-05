package ru.job4j.repository;

import ru.job4j.model.Candidate;
import ru.job4j.model.Vacancy;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryCandidateRepository implements CandidateRepository {

    private static final MemoryCandidateRepository INSTANCE = new MemoryCandidateRepository();

    private int nextId = 1;

    private final Map<Integer, Candidate> candidates = new HashMap<>();

    public MemoryCandidateRepository() {
        save(new Candidate(0, "Jack", "1. About", LocalDateTime.now()));
        save(new Candidate(0, "Bill", "2. About", LocalDateTime.now()));
        save(new Candidate(0, "Mie", "3. About", LocalDateTime.now()));
        save(new Candidate(0, "Lo", "4. About", LocalDateTime.now()));
        save(new Candidate(0, "Yen", "5. About", LocalDateTime.now()));
        save(new Candidate(0, "Lena", "6. About", LocalDateTime.now()));
    }

    public static MemoryCandidateRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public Candidate save(Candidate candidate) {
        candidate.setId(nextId++);
        candidates.put(candidate.getId(), candidate);
        return null;
    }

    @Override
    public Candidate deleteById(int id) {
        return candidates.remove(id);
    }

    @Override
    public boolean update(Candidate candidate) {
        return candidates.computeIfPresent(candidate.getId(),
                (id, oldData) -> new Candidate(oldData.getId(),
                        candidate.getName(),
                        candidate.getDescription(),
                        candidate.getCreationDate())) != null;
    }

    @Override
    public Optional<Candidate> findById(int id) {
        return Optional.ofNullable(candidates.get(id));
    }

    @Override
    public Collection<Candidate> findAll() {
        return candidates.values();
    }
}
