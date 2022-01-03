package com.company.formula_1;

import com.company.formula_1.formatter.Formatter;
import com.company.formula_1.models.Racer;
import com.company.formula_1.parser.Parser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationRunnerTest {

    private static final String ABBREVIATIONS_TXT_FILE_ADDRESS = "src/test/resources/abbreviations.txt";
    private static final String START_RACE_LOG_FILE_ADDRESS = "src/test/resources/start.log";
    private static final String END_RACE_LOG_FILE_ADDRESS = "src/test/resources/end.log";
    private static final String RESULT = """
            1. Sebastian Vettel     | FERRARI                      |01:04.415
            2. Daniel Ricciardo     | RED BULL RACING TAG HEUER    |01:12.013
            """;
    private static final List<Racer> RACERS = List.of(new Racer("Sebastian Vettel", "FERRARI", 64415L),
            new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER", 72013L));

    @Mock
    private Parser parser;

    @Mock
    private Formatter formatter;

    @Test
    void makeFormula1Application_shouldCheckOrderAndMethodCalls() {
        String expected = """
                1. Sebastian Vettel     | FERRARI                      |01:04.415
                2. Daniel Ricciardo     | RED BULL RACING TAG HEUER    |01:12.013
                """;

        when(parser.racerParse(ABBREVIATIONS_TXT_FILE_ADDRESS, START_RACE_LOG_FILE_ADDRESS, END_RACE_LOG_FILE_ADDRESS))
                .thenReturn(RACERS);

        when(formatter.format(parser.racerParse(ABBREVIATIONS_TXT_FILE_ADDRESS, START_RACE_LOG_FILE_ADDRESS, END_RACE_LOG_FILE_ADDRESS)))
                .thenReturn(RESULT);

        final String actual = formatter.format(RACERS);

        assertEquals(expected, actual);
    }
}