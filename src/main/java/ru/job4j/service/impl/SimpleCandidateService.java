package ru.job4j.service.impl;

import org.springframework.stereotype.Service;
import ru.job4j.dto.FileDto;
import ru.job4j.model.Candidate;
import ru.job4j.repository.CandidateRepository;
import ru.job4j.service.CandidateService;
import ru.job4j.service.FileService;

import java.util.Collection;
import java.util.Optional;

@Service
public class SimpleCandidateService implements CandidateService {
    private final CandidateRepository repository;

    private final FileService fileService;

    public SimpleCandidateService(CandidateRepository sql2oCandidateRepository, FileService fileService) {
        this.repository = sql2oCandidateRepository;
        this.fileService = fileService;
    }

    @Override
    public Candidate save(Candidate candidate, FileDto image) {
        saveNewFile(candidate, image);
        return repository.save(candidate);
    }

    private void saveNewFile(Candidate candidate, FileDto image) {
        var file = fileService.save(image);
        candidate.setFileId(file.getId());
    }

    @Override
    public boolean deleteById(int id) {
        return repository.deleteById(id);
    }

    @Override
    public boolean update(Candidate candidate, FileDto image) {
        var isNewFileEmpty = image.getContent().length == 0;
        if (isNewFileEmpty) {
            return repository.update(candidate);
        }
        var oldFileId = candidate.getFileId();
        saveNewFile(candidate, image);
        var isUpdated = repository.update(candidate);
        fileService.deleteById(oldFileId);
        return isUpdated;
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
