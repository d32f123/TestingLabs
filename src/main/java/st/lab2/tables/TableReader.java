package st.lab2.tables;

import com.sun.media.jfxmedia.logging.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TableReader {
    List<Point> readCsv(String filename) {
        ArrayList<Point> points = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filename));
            scanner.useDelimiter("[,\n]");

            double x, y;
            while (scanner.hasNext()) {
                x = Double.parseDouble(scanner.next());
                y = Double.parseDouble(scanner.next());
                points.add(new Point(x, y));
            }
            scanner.close();
            return points;
        } catch (Exception ex) {
            Logger.logMsg(0, ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
}
