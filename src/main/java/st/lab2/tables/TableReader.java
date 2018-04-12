package st.lab2.tables;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.sun.media.jfxmedia.logging.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

public class TableReader {
    List<Point> readCsv(String filename) {
        try {
            Reader reader = new FileReader(filename);
            CsvToBean<Point> csvToBean = new CsvToBeanBuilder<Point>(reader).withType(Point.class).build();

            return csvToBean.parse();
        } catch(Exception ex) {
            Logger.logMsg(0, ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
}
