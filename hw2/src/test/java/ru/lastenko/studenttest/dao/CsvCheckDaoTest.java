package ru.lastenko.studenttest.dao;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.core.io.Resource;
import ru.lastenko.studenttest.service.CheckParser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("DAO класс для работы с *.csv файлами")
class CsvCheckDaoTest {

    @Test
    @SneakyThrows
    @DisplayName("должен получить записи с проверками из ресурсов и распарсить их")
    void shouldGetAll() {
        String checkAsCsvString = "question;rightAnswer;TRUE;wrongAnswer1;FALSE;wrongAnswer2;FALSE";
        InputStream inputStream = new ByteArrayInputStream(checkAsCsvString.getBytes(StandardCharsets.UTF_8));
        var resource = mock(Resource.class);
        when(resource.getInputStream()).thenReturn(inputStream);
        var parser = mock(CheckParser.class);
        CsvCheckDao csvCheckDao = new CsvCheckDao(resource, parser);

        csvCheckDao.getAll();

        verify(resource, times(1)).getInputStream();
        ArgumentCaptor<List<String>> argumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(parser, times(1)).parseChecksFrom(argumentCaptor.capture());
        List<String> checksAsCsvStrings = argumentCaptor.getValue();
        assertEquals(checkAsCsvString, checksAsCsvStrings.get(0));
    }
}