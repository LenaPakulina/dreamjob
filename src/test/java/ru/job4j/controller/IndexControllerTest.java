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

class IndexControllerTest {
    private IndexController indexController;

    @BeforeEach
    public void initServices() {
        indexController = new IndexController();
    }

    @Test
    public void whenGetIndex() {
        assertThat(indexController.getIndex()).isEqualTo("index");
    }
}