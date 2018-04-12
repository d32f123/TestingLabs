package st.lab2.tables;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.sun.media.jfxmedia.logging.Logger;
import org.mockito.ArgumentMatcher;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class TableGenerator {
    private static final double EPSILON = 0.000001;
    public void generate(List<Double> breakPoints, double step, Mapper mapper, String outFile) {
        ArrayList<Point> points = new ArrayList<>();

        points.add(new Point(breakPoints.get(0), mapper.map(breakPoints.get(0))));

        for (int i = 0; i < breakPoints.size() - 1; ++i) {
            double beg = breakPoints.get(i),
                    end = breakPoints.get(i + 1),
                    curr = beg + step,
                    mid = (beg + end) / 2.;

            points.add(new Point(beg + EPSILON, mapper.map(beg + EPSILON)));

            while (curr < mid) {
                points.add(new Point(curr, mapper.map(curr)));
                curr += step;
            }

            points.add(new Point(mid, mapper.map(mid)));

            while (curr < end - EPSILON) {
                points.add(new Point(curr, mapper.map(curr)));
                curr += step;
            }

            points.add(new Point(end - EPSILON, mapper.map(end - EPSILON)));
            points.add(new Point(end, mapper.map(end)));
        }

        Writer writer;
        try {
            writer = new FileWriter(outFile);
            StatefulBeanToCsv<Point> beanToCsv = new StatefulBeanToCsvBuilder<Point>(writer).build();
            beanToCsv.write(points);
            writer.close();
        } catch (Exception ex) {
            Logger.logMsg(0, ex.getMessage());
        }
    }
}

@FunctionalInterface
interface Mapper {
    double map(double x);
}