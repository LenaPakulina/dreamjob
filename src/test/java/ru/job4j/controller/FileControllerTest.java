package ru.job4j.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.ConcurrentModel;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.dto.FileDto;
import ru.job4j.model.City;
import ru.job4j.model.Vacancy;
import ru.job4j.service.CityService;
import ru.job4j.service.FileService;
import ru.job4j.service.VacancyService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class FileControllerTest {
    private FileService fileService;

    private FileController fileController;

    private MultipartFile testFile;

    @BeforeEach
    public void initServices() {
        fileService = mock(FileService.class);
        fileController = new FileController(fileService);
        testFile = new MockMultipartFile("testFile.img", new byte[] {1, 2, 3});
    }

    @Test
    public void whenGetByIdWithSuccess() throws IOException {
        Optional<FileDto> fileDto = Optional.of(new FileDto(testFile.getName(), testFile.getBytes()));
        when(fileService.getFileById(1)).thenReturn(fileDto);
        var entity = fileController.getById(1);
        assertThat(entity).isEqualTo(ResponseEntity.ok(fileDto.get().getContent()));
    }

    @Test
    public void whenGetByIdWithErrorMsg() throws IOException {
        when(fileService.getFileById(1)).thenReturn(Optional.empty());
        var entity = fileController.getById(1);
        assertThat(entity).isEqualTo(ResponseEntity.notFound().build());
    }
}