package st.lab2.tables;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableGeneratorTest {

    @Test
    void generate() {
        List<Double> values = Arrays.asList(0., 1., 2., 3.);
        TableGenerator tableGenerator = new TableGenerator();
        tableGenerator.generate(values, 0.2, (x) -> 2*x, "ok.csv");

        List<Point> points = new TableReader().readCsv("ok.csv");
        assertTrue(true);
    }
}