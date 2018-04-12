package st.lab2.tables;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class PointXConverter extends AbstractBeanField<Double> {
    @Override
    protected Object convert(String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        try {
            if (s.charAt(0) == 'p') {
                if (s.charAt(1) == '*')
                    return Math.PI * Double.parseDouble(s.substring(2));
                else if (s.charAt(1) == '/')
                    return Math.PI / Double.parseDouble(s.substring(2));
                else
                    return Math.PI * Double.parseDouble(s.substring(2));
            }
            return Double.parseDouble(s);
        } catch (Exception ex) {
            throw new CsvDataTypeMismatchException("Whoops?");
        }
    }
}
