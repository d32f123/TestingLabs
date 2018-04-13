package st.lab2.tables;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class TableGeneratorTest {

    private List<String> trigValues;

    private List<String> logValues;

    private static final TableGenerator tableGenerator = new TableGenerator();

    @BeforeEach
    void setUp() {
        try {
            trigValues = Files.lines(Paths.get("trigBPs.csv")).collect(Collectors.toList());
            logValues = Files.lines(Paths.get("logBPs.csv")).collect(Collectors.toList());
        } catch (Exception ex) {
            throw new IllegalArgumentException("whoops");
        }
    }

    private static double cos(double x) {
        //if ((Math.abs(x) - Math.PI / 2.) % Math.PI == 0)
        //    return 0.0;
        return Math.cos(x);
    }

    private static double sin(double x) {
        //if (x % Math.PI == 0)
        //    return 0.0;
        return Math.sin(x);
    }

    @Test
    void generateValues() {
        tableGenerator.generate(trigValues, (x) -> (1. / sin(x)) / sin(x) / (1. / cos(x)),
                "values.csv");
        tableGenerator.generateAppend(logValues, x -> ((Math.pow(Math.log(x) / Math.log(3) / Math.log10(x), 3) + (Math.log(x) / Math.log(3)) ) *
                Math.log10(x)) *
                ( Math.pow((Math.log(x) / Math.log(5)) + ((Math.log10(x) * (Math.log(x) / Math.log(2)))
                        + (Math.log(x) / Math.log(2))), 2) ), "values.csv");
    }

    @Test
    void generateCosine() {
        tableGenerator.generate(trigValues, TableGeneratorTest::cos, "cos.csv");
    }

    @Test
    void generateSine() {
        tableGenerator.generate(trigValues, TableGeneratorTest::sin, "sin.csv");
    }

    @Test
    void generateCosecant() {
        tableGenerator.generate(trigValues, x -> 1. / sin(x), "csc.csv");
    }

    @Test
    void generateSecant() {
        tableGenerator.generate(trigValues, x -> 1. / cos(x), "sec.csv");
    }

    @Test
    void generateLn() {
        tableGenerator.generate(logValues, Math::log, "ln.csv");
    }

    @Test
    void generateLog2() {
        tableGenerator.generate(logValues, x -> Math.log(x) / Math.log(2), "log2.csv");
    }

    @Test
    void generateLog3() {
        tableGenerator.generate(logValues, x -> Math.log(x) / Math.log(3), "log3.csv");
    }

    @Test
    void generateLog5() {
        tableGenerator.generate(logValues, x -> Math.log(x) / Math.log(5), "log5.csv");
    }

    @Test
    void generateLog10() {
        tableGenerator.generate(logValues, x -> Math.log(x) / Math.log(10), "log10.csv");
    }
}