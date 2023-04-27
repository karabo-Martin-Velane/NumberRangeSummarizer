/**
 * com.kmvelane.rangesummarizer Package
 */
package com.kmvelane.rangesummarizer;

// Importing Utilities
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.kmvelane.rangesummarizer.numberrangesummarizer.NumberRange;
import com.kmvelane.rangesummarizer.numberrangesummarizer.NumberRangeSummarizer;

/**
 * Unit test class
 */
public class AppTest {

        // Reference to the interface
        public NumberRangeSummarizer numberRangeSummarizer;

        /*
         * Constructor to instantiate the concrete implimentation
         */
        AppTest() {
                numberRangeSummarizer = new NumberRange();
        }

        /**
         * Testing with an ordered Large List that contains a combination of Sequential
         * and non-sequential Values
         */
        @Test
        public void orderedLargeListOfSequentialAndNonSequentials() {

                Assertions.assertAll(
                                () -> assertEquals("3-6,9,17-22,28,31,34-37,43,45-48",
                                                numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect(
                                                                "3,4,5,6,9,17,18,19,20,21,22,28,31,34,35,36,37,43,45,46,47,48"))),
                                () -> assertEquals("1-5,8,10,13-15,20,23,27-30,38,41-44,49",
                                                numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect(
                                                                "1,2,3,4,5,8,10,13,14,15,20,23,27,28,29,30,38,41,42,43,44,49"))),
                                () -> assertEquals("2,6-8,11,15-16,21-24,26,32-36,40,43-44,46",
                                                numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect(
                                                                "2,6,7,8,11,15,16,21,22,23,24,26,32,33,34,35,36,40,43,44,46"))));
        }

        /**
         * Testing with an unordered Large List that contains a combination of
         * Sequential and non-sequential Values With Duplicates
         */
        @Test
        public void unOrderedLargeListOfSequentialAndNonSequentials() {
                Assertions.assertAll(
                                () -> assertEquals("7,9,12-15,22,28,36,42,47",
                                                numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect(
                                                                "7,15,42,12,13,7,36,47,22,28,12,14,15,36,22,47,9,28,15,9"))),
                                () -> assertEquals("3-6,12,19-21,26,38",
                                                numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect(
                                                                "38,19,6,12,20,19,26,3,38,21,19,5,3,38,12,26,19,3,4,12"))),
                                () -> assertEquals("3-5,12,19,26-29,38",
                                                numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect(
                                                                "26,3,4,19,38,12,29,3,38,12,5,26,27,19,38,28,12,3,38,12"))));
        }

        /**
         * Invalid Input
         */
        @Test
        public void invalidInput() {
                assertThrows(IllegalArgumentException.class,
                                () -> numberRangeSummarizer.summarizeCollection(
                                                numberRangeSummarizer
                                                                .collect("a,#,-,2,1, ,-1")));
        }

        /**
         * Testing with an empty string
         */
        @Test
        public void emptyList() {
                assertEquals("", numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect("")));
        }

        /**
         * Testing with an single number string
         */
        @Test
        public void singleItemList() {
                assertEquals("1", numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect("1")));
        }

        /**
         * Testing with a List of sequential values
         */
        @Test
        public void sequentialList() {
                assertEquals("1-5",
                                numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect("1,2,3,4,5")));
        }

        /**
         * Testing with a List of non sequential values
         */
        @Test
        public void nonSequentialList() {
                assertEquals("1,3,5,7,9",
                                numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect("1,3,5,7,9")));
        }

        /**
         * Testing with a List of Sequential Positive and Negative Integers
         */
        @Test
        public void sequentialIntegers() {
                assertEquals("-3-3", numberRangeSummarizer
                                .summarizeCollection(numberRangeSummarizer.collect("-3,-2,-1,0,1,2,3")));
        }

        /**
         * Testing with a List of Sequential & nonsequential, Positive & Negative Values
         */
        @Test
        public void sequentialAndNonSequentialIntegers() {
                assertEquals("-5--4,-2-1,3-5",
                                numberRangeSummarizer.summarizeCollection(
                                                numberRangeSummarizer.collect("-5,-4,-2,-1,0,1,3,4,5")));
        }

        /**
         * Testing with an unordered List, with a combination of Negatives, Positives
         * and Zero
         */
        @Test
        public void unorderedMix() {
                assertEquals("-10,-3-5,10",
                                numberRangeSummarizer.summarizeCollection(
                                                numberRangeSummarizer
                                                                .collect("-3, -2, -1, 0, 1, 2, 3, 4, 5, -10, 10")));
        }

        /**
         * Reverse list of integers
         */
        @Test
        public void reverseIntegerList() {
                assertEquals("-5-5",
                                numberRangeSummarizer.summarizeCollection(
                                                numberRangeSummarizer
                                                                .collect("5,4,3,2,1,0,-1,-2,-3,-4,-5")));
        }
}
