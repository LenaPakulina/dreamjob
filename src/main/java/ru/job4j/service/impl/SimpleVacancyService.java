package ru.job4j.service.impl;

import org.springframework.stereotype.Service;
import ru.job4j.dto.FileDto;
import ru.job4j.model.Vacancy;
import ru.job4j.repository.VacancyRepository;
import ru.job4j.service.FileService;
import ru.job4j.service.VacancyService;

import java.util.Collection;
import java.util.Optional;

@Service
public class SimpleVacancyService implements VacancyService {
    private final VacancyRepository repository;

    private final FileService fileService;

    public SimpleVacancyService(VacancyRepository sql2oVacancyRepository, FileService fileService) {
        this.repository = sql2oVacancyRepository;
        this.fileService = fileService;
    }

    @Override
    public Vacancy save(Vacancy vacancy, FileDto image) {
        saveNewFile(vacancy, image);
        return repository.save(vacancy);
    }

    private void saveNewFile(Vacancy vacancy, FileDto image) {
        var file = fileService.save(image);
        vacancy.setFileId(file.getId());
    }

    @Override
    public boolean deleteById(int id) {
        return repository.deleteById(id);
    }

    @Override
    public boolean update(Vacancy vacancy, FileDto image) {
        var isNewFileEmpty = image.getContent().length == 0;
        if (isNewFileEmpty) {
            return repository.update(vacancy);
        }
        /* если передан новый не пустой файл, то старый удаляем, а новый сохраняем */
        var oldFileId = vacancy.getFileId();
        saveNewFile(vacancy, image);
        var isUpdated = repository.update(vacancy);
        fileService.deleteById(oldFileId);
        return isUpdated;
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
