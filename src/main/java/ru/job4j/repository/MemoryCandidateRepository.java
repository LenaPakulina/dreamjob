package ru.job4j.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.model.Candidate;
import ru.job4j.model.Vacancy;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryCandidateRepository implements CandidateRepository {
    private int nextId = 1;

    private final Map<Integer, Candidate> candidates = new HashMap<>();

    public MemoryCandidateRepository() {
        save(new Candidate(0, "Jack", "1. About", LocalDateTime.now(), 1, 0));
        save(new Candidate(0, "Bill", "2. About", LocalDateTime.now(), 2, 0));
        save(new Candidate(0, "Mie", "3. About", LocalDateTime.now(), 2, 0));
        save(new Candidate(0, "Lo", "4. About", LocalDateTime.now(), 3, 0));
        save(new Candidate(0, "Yen", "5. About", LocalDateTime.now(), 3, 0));
        save(new Candidate(0, "Lena", "6. About", LocalDateTime.now(), 1, 0));
    }

    @Override
    public Candidate save(Candidate candidate) {
        candidate.setId(nextId++);
        candidates.put(candidate.getId(), candidate);
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return candidates.remove(id) != null;
    }

    @Override
    public boolean update(Candidate candidate) {
        return candidates.computeIfPresent(candidate.getId(),
                (id, oldData) -> new Candidate(oldData.getId(),
                        candidate.getName(),
                        candidate.getDescription(),
                        oldData.getCreationDate(),
                        candidate.getCityId(),
                        candidate.getFileId())) != null;
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
