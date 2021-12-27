package com.company.formula_1.parser;

import com.company.formula_1.models.Racer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    Parser parser;

    @BeforeEach
    void setUp() {
        parser = new Parser();
    }

    @Test
    void racerParse_shouldReturnRacerList_whenInputRacerData() {
        List<Racer> expected = List.of(new Racer("Sebastian Vettel", "FERRARI", 64415L),
                new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER", 72013L));
        List<Racer> actual = parser.racerParse("src/test/resources/abbreviations.txt",
                "src/test/resources/start.log", "src/test/resources/end.log");

        assertEquals(expected, actual);
    }

    @Test
    void racerParse_shouldThrowNullPointerException_whenInputNull(){
        NullPointerException exception = assertThrows(NullPointerException.class,()->
                parser.racerParse(null,null,null));
        assertThat("FilePath is null",equalTo(exception.getMessage()));
    }

}