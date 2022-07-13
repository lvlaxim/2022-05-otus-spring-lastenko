package ru.lastenko.studenttest.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.lastenko.studenttest.model.Check;
import ru.lastenko.studenttest.model.OfferedAnswer;
import ru.lastenko.studenttest.model.Question;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("Парсер объектов класса Check из строк")
class CsvCheckParserTest {

    @Test
    @DisplayName("должен распарсить объект класса Check из строки")
    void parseStringToCheck() {
        var parser = new CsvCheckParser();
        String checkAsCsvString = "question;rightAnswer;TRUE;wrongAnswer1;FALSE;wrongAnswer2;FALSE";
        var question = new Question("question");
        List<OfferedAnswer> offeredAnswers = List.of(
                new OfferedAnswer("rightAnswer", true),
                new OfferedAnswer("wrongAnswer1", false),
                new OfferedAnswer("wrongAnswer2", false));
        Check expectedCheck = new Check(question, offeredAnswers);

        Check actualCheck = parser.parseCheckFrom(checkAsCsvString);

        assertThat(actualCheck).usingRecursiveComparison().isEqualTo(expectedCheck);
    }

    @Test
    @DisplayName("должен вызвать метод parseStringToCheck для каждой строки и вернуть массив соответствующего размера")
    void parseStringsToChecks() {
        var parserMock = Mockito.mock(CsvCheckParser.class);
        when(parserMock.parseChecksFrom(any())).thenCallRealMethod();
        List<String> checksAsStrings = List.of("string1", "string2", "string3");

        List<Check> checks = parserMock.parseChecksFrom(checksAsStrings);

        checksAsStrings.forEach(checkAsString ->
                verify(parserMock, times(1)).parseCheckFrom(checkAsString));
        assertThat(checks).asList()
                .isNotNull()
                .hasSize(checksAsStrings.size());
    }
}