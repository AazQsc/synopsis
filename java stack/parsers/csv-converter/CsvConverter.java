package com.home.databinding.csv;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import com.home.databinding.exception.CsvException;

/**
 * Class for converting data from csv.
 */
@Slf4j
@Component
public class CsvConverter {

    private final CsvMapper csvMapper;

    private final Validator validator;

    public CsvConverter() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        this.csvMapper = new CsvMapper();

        csvMapper.enable(CsvParser.Feature.FAIL_ON_MISSING_COLUMNS);
    }

    /**
     * Converting csv data to dtos.
     *
     * @param csvStream InputStream with data
     * @param clazz     using like a schema
     * @return list of dtos form file rows
     */
    public <T> List<T> toDtos(InputStream csvStream, Class<T> clazz) throws IOException, CsvException {
        log.debug("Start mapping csv to dto {}", clazz);

        CsvSchema emptySchema = CsvSchema.emptySchema().withHeader();
        MappingIterator<Map<String, String>> it = csvMapper.readerFor(Map.class).with(emptySchema).readValues(csvStream);
        List<Map<String, String>> csvRecords = it.readAll();

        if (!csvRecords.isEmpty()) {
            validateCsvFromSchema(clazz, csvRecords.get(0).keySet());
        }
        return csvRecords.stream().map(e -> csvMapper.convertValue(e, clazz)).map(this::validateFromConstraints).collect(Collectors.toList());
    }

    /**
     * Validation rule: headers from csv must be matching to schema of target class
     *
     * @param clazz      class on the basis of which schema will built
     * @param csvHeaders headers from csv file
     * @throws CsvException if the headers do not matching to the schema of class
     */
    private <T> void validateCsvFromSchema(Class<T> clazz, Set<String> csvHeaders) throws CsvException {
        CsvSchema schemaFromClass = csvMapper.schemaFor(clazz).withHeader();
        String fieldsFromDto = schemaFromClass.getColumnDesc();

        if (!getAsLowerCase(fieldsFromDto).equals(getAsLowerCase(csvHeaders.toString()))) {
            throw new CsvException(String.format("CSV headers is invalid. Headers must looks like: %s", fieldsFromDto));
        }
    }

    /**
     * Validation from javax annotations
     *
     * @return T if validation was successful
     */
    private <T> T validateFromConstraints(T t) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
        return t;
    }

    private String getAsLowerCase(String stringWithSpecSymbols) {
        return stringWithSpecSymbols.replaceAll("[^A-Za-z]", "").toLowerCase().trim();
    }
}
