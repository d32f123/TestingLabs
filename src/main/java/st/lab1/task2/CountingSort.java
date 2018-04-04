package st.lab1.task2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.stream.IntStream;

public class CountingSort {

    private static final Logger logger = LogManager.getLogger(CountingSort.class);

    private int[] array;
    private int[] countArray;

    CountingSort(int range, int[] array) {
        if (IntStream.of(array).anyMatch(x -> x < 0 || x > range))
            throw new IllegalArgumentException("Array contains elements smaller than zero or greater than range");

        this.countArray = new int[range + 1];
        this.array = array.clone();
        logger.debug("Constructed with range = " + range);
    }

    public int[] getArray() {
        return array.clone();
    }

    public int[] getCountArray() {
        return countArray.clone();
    }

    public void setCountArray(int[] arr) {
        if (arr.length != countArray.length)
            throw new IllegalArgumentException("Invalid count array size");
        if (IntStream.of(arr).anyMatch(x -> x < 0 || x > array.length))
            throw new IllegalArgumentException("Count array contains invalid elements");
        countArray = Arrays.copyOf(arr, arr.length);
    }

    public void calculateCountArray() {
        logger.info("-----------------------ENTERING calculateCountArray METHOD-----------------------");
        logger.debug("Calculating count array");

        logger.debug("Filling count array with zeros");
        Arrays.fill(countArray, 0);
        logger.debug("countArray: " + Arrays.toString(countArray));

        for (Integer c : array) {
            ++countArray[c];
            logger.debug("Met " + c + " value. Increasing countArray[" + c + "] by one");
            logger.debug("countArray: " + Arrays.toString(countArray));
        }

        logger.debug("Count array formed successfully");
        logger.debug("countArray: " + Arrays.toString(countArray));
        logger.info("-----------------------END calculateCountArray-----------------------");
    }

    public void transformCountArrayToIndexArray() {
        logger.info("-----------------------transformCountArrayToIndexArray-----------------------");
        logger.debug("Transforming countArray to index array");
        for (int i = 1; i < countArray.length; ++i) {
            countArray[i] += countArray[i - 1];
            logger.debug("i = " + i + ". countArray: " + Arrays.toString(countArray));
        }
        logger.debug("Transformation complete");
        logger.debug("countArray: " + Arrays.toString(countArray));
        logger.info("-----------------------END transformCountArrayToIndexArray-----------------------");
    }

    public void buildSortedArray() {
        logger.info("-----------------------buildSortedArray-----------------------");
        logger.debug("Copying initial array to a temporary storage");
        int[] tempArray = array.clone();
        logger.debug("Clearing initial array");
        Arrays.fill(array, (char)0);
        logger.debug("tempArray: " + Arrays.toString(tempArray));
        logger.debug("array: " + Arrays.toString(array));

        logger.debug("Building sorted array");
        logger.debug("countArray: " + Arrays.toString(countArray));
        for (int i = 0; i < tempArray.length; ++i) {
            logger.debug("Element " + i + " should go to " + (countArray[tempArray[i]] - 1) + "th position");
            array[countArray[tempArray[i]] - 1] = tempArray[i];
            logger.debug("Array: " + Arrays.toString(array));
            logger.debug("Decrementing " + tempArray[i] + "th value in countArray");
            --countArray[tempArray[i]];
            logger.debug("countArray: " + Arrays.toString(countArray));
            logger.debug("tempArray: " + Arrays.toString(tempArray));
        }

        logger.debug("Sort complete! Sorted array: " + Arrays.toString(array));
        logger.info("-----------------------END buildSortedArray-----------------------");
    }

    public void sort() {
        calculateCountArray();
        transformCountArrayToIndexArray();
        buildSortedArray();
    }
}
