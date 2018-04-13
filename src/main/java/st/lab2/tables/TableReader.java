package st.lab2.tables;

import com.sun.media.jfxmedia.logging.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TableReader {
    public List<Point> readCsv(String filename) {

        ArrayList<Point> points = new ArrayList<>();
        try {
            Stream<String> stream = Files.lines(Paths.get(filename));

            return stream.map(str -> {
                double x = Double.parseDouble(str.substring(0, str.indexOf(',')));
                double y = Double.parseDouble(str.substring(str.indexOf(',') + 1, str.length()));
                return new Point(x, y);
            }).collect(Collectors.toList());
        } catch (Exception ex) {
            Logger.logMsg(0, ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
}
