package com.company.formula_1.formatter;

import com.company.formula_1.models.Racer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class FormatterTest {

    Formatter formatter;

    private static final String EXPECTED_FORMULA_RESULT = """
            1. Sebastian Vettel     | FERRARI                      |01:04.415
            2. Daniel Ricciardo     | RED BULL RACING TAG HEUER    |01:12.013
            3. Valtteri Bottas      | MERCEDES                     |01:12.434
            4. Lewis Hamilton       | MERCEDES                     |01:12.460
            5. Stoffel Vandoorne    | MCLAREN RENAULT              |01:12.463
            6. Kimi Raikkonen       | FERRARI                      |01:12.639
            7. Fernando Alonso      | MCLAREN RENAULT              |01:12.657
            8. Sergey Sirotkin      | WILLIAMS MERCEDES            |01:12.706
            9. Charles Leclerc      | SAUBER FERRARI               |01:12.829
            10. Sergio Perez         | FORCE INDIA MERCEDES         |01:12.848
            11. Romain Grosjean      | HAAS FERRARI                 |01:12.930
            12. Pierre Gasly         | SCUDERIA TORO ROSSO HONDA    |01:12.941
            13. Carlos Sainz         | RENAULT                      |01:12.950
            14. Esteban Ocon         | FORCE INDIA MERCEDES         |01:13.028
            15. Nico Hulkenberg      | RENAULT                      |01:13.065
            ------------------------------------------------------------------------
            16. Brendon Hartley      | SCUDERIA TORO ROSSO HONDA    |01:13.179
            17. Marcus Ericsson      | SAUBER FERRARI               |01:13.265
            18. Lance Stroll         | WILLIAMS MERCEDES            |01:13.323
            19. Kevin Magnussen      | HAAS FERRARI                 |01:13.393
            """;

    @BeforeEach
    void setUp() {
        formatter = new Formatter();
    }

    @Test
    void formatRacersList_shouldReturnCorrectOutput_whenCorrectInput() {
        List<Racer> racerList = List.of(new Racer("Sebastian Vettel", "FERRARI", 64415L),
                new Racer("Valtteri Bottas", "MERCEDES", 72434L),
                new Racer("Carlos Sainz", "RENAULT", 72950L),
                new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER", 72013L),
                new Racer("Kevin Magnussen", "HAAS FERRARI", 73393L),
                new Racer("Sergio Perez", "FORCE INDIA MERCEDES", 72848L),
                new Racer("Stoffel Vandoorne", "MCLAREN RENAULT", 72463L),
                new Racer("Charles Leclerc", "SAUBER FERRARI", 72829L),
                new Racer("Brendon Hartley", "SCUDERIA TORO ROSSO HONDA", 73179L),
                new Racer("Lewis Hamilton", "MERCEDES", 72460L),
                new Racer("Lance Stroll", "WILLIAMS MERCEDES", 73323L),
                new Racer("Romain Grosjean", "HAAS FERRARI", 72930L),
                new Racer("Fernando Alonso", "MCLAREN RENAULT", 72657L),
                new Racer("Sergey Sirotkin", "WILLIAMS MERCEDES", 72706L),
                new Racer("Nico Hulkenberg", "RENAULT", 73065L),
                new Racer("Marcus Ericsson", "SAUBER FERRARI", 73265L),
                new Racer("Esteban Ocon", "FORCE INDIA MERCEDES", 73028L),
                new Racer("Pierre Gasly", "SCUDERIA TORO ROSSO HONDA", 72941L),
                new Racer("Kimi Raikkonen", "FERRARI", 72639L));
        String actual = formatter.format(racerList);
        assertEquals(EXPECTED_FORMULA_RESULT, actual);
    }

    @Test
    void formatRacersList_shouldReturnNullPointerException_whenInputNull() {
        List<Racer> racerList = List.of(new Racer(null, null, null));
        NullPointerException exception = assertThrows(NullPointerException.class, () ->
                formatter.format(racerList));
        assertThat("RacerList is null",equalTo(exception.getMessage()));
    }

}