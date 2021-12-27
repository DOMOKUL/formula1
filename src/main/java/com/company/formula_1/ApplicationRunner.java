package com.company.formula_1;

import com.company.formula_1.formatter.Formatter;
import com.company.formula_1.models.Racer;
import com.company.formula_1.parser.Parser;

import java.util.List;

public class ApplicationRunner {

    public static void main(String[] args) {
        Parser parser = new Parser();
        Formatter formatter = new Formatter();
        List<Racer> racers = parser.racerParse("src/main/resources/abbreviations.txt",
                "src/main/resources/start.log",
                "src/main/resources/end.log");
        racers.stream().forEach(System.out::println);
        System.out.println(formatter.format(racers));
    }
}
