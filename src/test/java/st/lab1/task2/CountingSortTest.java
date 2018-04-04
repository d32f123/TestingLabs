package st.lab1.task2;

import org.junit.jupiter.api.Test;
import st.lab1.task2.CountingSort;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CountingSortTest {

    private static final int[] referenceArray = new int[] { 3, 1, 2, 2};
    private static final int[] referenceCountArray = new int[] {
            0, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0
    };
    private static final int[] referenceIndexArray = new int[] {
            0, 1, 3, 4, 4, 4, 4, 4, 4, 4, 4
    };
    private static final int[] referenceSortedArray = new int[] {
            1, 2, 2, 3
    };

    private static final int[] invalidNegativeArray = new int[] {
            -5, 1
    };

    private static final int[] invalidOutOfRangeArray = new int[] {
            1, 12
    };

    private static final int[] referenceSingleElementArray = new int[] { 2 };

    private static final int[] referenceEmptyArray = new int[] {};

    private static final int[] referenceNullArray = null;

    private final CountingSort sorter = new CountingSort(10, referenceArray);

    @Test
    void InvalidNegativeArrayTest() {
        assertThrows(IllegalArgumentException.class, () -> new CountingSort(10, invalidNegativeArray),
                "Failed to throw exception for negative array elements");
    }

    @Test
    void InvalidOutOfRangeArrayTest() {
        assertThrows(IllegalArgumentException.class, () -> new CountingSort(10, invalidOutOfRangeArray),
                "Failed to throw exception for out of range array elements");
    }

    @Test
    void EmptyArrayTest() {
        CountingSort sorter = new CountingSort(10, referenceEmptyArray);
        sorter.sort();
        assertArrayEquals(referenceEmptyArray, sorter.getArray());
    }

    @Test
    void NullArrayTest() {
        assertThrows(NullPointerException.class, () -> {
            CountingSort sorter = new CountingSort(10, referenceNullArray);
            sorter.sort();
        });
    }

    @Test
    void SingleElementArrayTest() {
        CountingSort sorter = new CountingSort(10, referenceSingleElementArray);
        sorter.sort();
        assertArrayEquals(referenceSingleElementArray, sorter.getArray());
    }

    @Test
    void FullSortTest() {

        assertTrue(Arrays.equals(referenceArray, sorter.getArray()), "Arrays are not equal for some reason");

        sorter.calculateCountArray();
        assertTrue(Arrays.equals(referenceCountArray, sorter.getCountArray()), "Count arrays are not equal");

        sorter.transformCountArrayToIndexArray();
        assertTrue(Arrays.equals(referenceIndexArray, sorter.getCountArray()), "Index arrays are not equal");

        sorter.buildSortedArray();
        assertTrue(Arrays.equals(referenceSortedArray, sorter.getArray()), "Sorted arrays are not equal");

    }

    @Test
    void InitialArraySetTest() {

        assertTrue(Arrays.equals(referenceArray, sorter.getArray()), "Initial arrays are not equal for some " +
                "very interesting and probably stupid reason");

    }

    @Test
    void CalculateCountArrayTest() {

        sorter.calculateCountArray();
        assertTrue(Arrays.equals(referenceCountArray, sorter.getCountArray()), "Count array calculation failed");

    }

    @Test
    void IndexArrayTest() {

        sorter.setCountArray(referenceCountArray);

        sorter.transformCountArrayToIndexArray();
        assertTrue(Arrays.equals(referenceIndexArray, sorter.getCountArray()), "Index array calculation failed");

    }

    @Test
    void SortArrayTest() {

        sorter.setCountArray(referenceIndexArray);

        sorter.buildSortedArray();
        assertTrue(Arrays.equals(referenceSortedArray, sorter.getArray()), "Array sort failed");

    }
}