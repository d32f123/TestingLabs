package st.lab2.tables;
import com.sun.media.jfxmedia.logging.Logger;

import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TableGenerator {

    private double step;
    private List<BreakPoint> breakPoints;
    private void readCsv(List<String> list) {
        try {

            step = Double.parseDouble(list.get(0));


            Stream<String> stream = list.stream().skip(1);
            breakPoints = stream.map(str -> {
                int prevIndex, currIndex;
                double x = Double.parseDouble(str.substring(0, prevIndex = str.indexOf(',')));
                boolean bool = Boolean.parseBoolean(str.substring(prevIndex + 1, currIndex = str.indexOf(',',  prevIndex + 1)));
                prevIndex = currIndex;
                double epsilon = Double.parseDouble(str.substring(prevIndex + 1, str.length()));
                return new BreakPoint(x, epsilon, bool);
            }).collect(Collectors.toList());
        } catch (Exception ex) {
            Logger.logMsg(0, ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    private void do_generate(List<String> inStrings, Mapper mapper, String outFile, boolean append) {
        readCsv(inStrings);

        ArrayList<Point> points = new ArrayList<>();


        BreakPoint begPoint = breakPoints.get(0);
        if (!begPoint.isSkip())
            points.add(new Point(begPoint.getX(), mapper.map(begPoint.getX())));

        for (int i = 0; i < breakPoints.size() - 1; ++i) {
            begPoint = breakPoints.get(i);
            BreakPoint endPoint = breakPoints.get(i + 1);
            double beg = begPoint.getX(), end = endPoint.getX(),
                    curr = beg + begPoint.getEpsilon() + step,
                    mid = (beg + end) / 2.;

            if (beg == end)
                continue;

            points.add(new Point(beg + begPoint.getEpsilon(), mapper.map(beg + begPoint.getEpsilon())));

            while (curr < mid) {
                points.add(new Point(curr, mapper.map(curr)));
                curr += step;
            }

            points.add(new Point(mid, mapper.map(mid)));

            while (curr < end - endPoint.getEpsilon()) {
                points.add(new Point(curr, mapper.map(curr)));
                curr += step;
            }

            points.add(new Point(end - endPoint.getEpsilon(), mapper.map(end - endPoint.getEpsilon())));
            if (!endPoint.isSkip())
                points.add(new Point(end, mapper.map(end)));
        }

        Writer writer;
        try {
            writer = new FileWriter(outFile);

            for (Point point : points)
                writer.write("" + point.getX() + "," + point.getY() + "\n");

            writer.close();
        } catch (Exception ex) {
            Logger.logMsg(0, ex.getMessage());
        }
    }

    public void generate(List<String> inStrings, Mapper mapper, String outFile) {
        do_generate(inStrings, mapper, outFile, false);
    }

    public void generateAppend(List<String> inStrings, Mapper mapper, String outFile) {
        do_generate(inStrings, mapper, outFile, true);
    }
}

@FunctionalInterface
interface Mapper {
    double map(double x);
}