package com.company.formula_1.parser;

import com.company.formula_1.models.Racer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parser {

    private static final SimpleDateFormat FORMAT_TIME =
            new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");

    public List<Racer> racerParse(String nameAndCommandFilePath, String startTimeFilePath, String endTimeFilePath) {
        try {
            Map<String, Map<String, String>> nameAndCommand = nameAndCommandParse(Paths.get(nameAndCommandFilePath));
            Map<String, Calendar> startTime = parseTime(Paths.get(startTimeFilePath));
            Map<String, Calendar> endTime = parseTime(Paths.get(endTimeFilePath));
            return nameAndCommand.entrySet().stream()
                    .map(e -> {
                        String racerName = getName(e.getValue());
                        String racerCommand = getCommand(e.getValue());
                        Long lapTime = endTime.get(e.getKey()).getTimeInMillis()
                                - startTime.get(e.getKey()).getTimeInMillis();

                        return new Racer(racerName, racerCommand, lapTime);
                    })
                    .collect(Collectors.toList());
        } catch (NullPointerException e) {
            throw new NullPointerException("FilePath is null");
        }
    }

    private Map<String, Map<String, String>> nameAndCommandParse(Path filePath) {
        List<String> abbreviations = logParse(filePath);
        Map<String, Map<String, String>> result = new HashMap<>();

        abbreviations.forEach(s -> {
            String[] splitLine = s.split("_");
            Map<String, String> nameAndCommand = new HashMap<>();
            nameAndCommand.put(splitLine[1], splitLine[2]);
            result.put(splitLine[0], nameAndCommand);
        });

        return result;
    }

    private Map<String, Calendar> parseTime(Path filePath) {
        List<String> startOrEndTimeParser = logParse(filePath);
        Map<String, Calendar> result = new HashMap<>();
        startOrEndTimeParser.forEach(s ->
                result.put(s.substring(0, 3), getTime(s.substring(3, 26)))
        );
        return result;
    }

    private List<String> logParse(Path filePath) {
        List<String> result = new ArrayList<>();
        try (Stream<String> lineStream = Files.lines(filePath)) {
            result = lineStream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Calendar getTime(String time) {
        Calendar calendar = new GregorianCalendar();
        try {
            calendar.setTime(FORMAT_TIME.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return calendar;
    }

    private String getName(Map<String, String> nameAndCommandMap) {
        StringBuilder name = new StringBuilder();
        nameAndCommandMap.forEach((k, v) -> name.append(k));

        return name.toString();
    }

    private String getCommand(Map<String, String> nameAndCommandMap) {
        StringBuilder command = new StringBuilder();
        nameAndCommandMap.forEach((k, v) -> command.append(v));

        return command.toString();
    }
}
