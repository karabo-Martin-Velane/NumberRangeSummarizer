/**
 * numberrangesummarizer Package
 */
package com.kmvelane.rangesummarizer.numberrangesummarizer;

// Importing Utilities
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author K. M. VELANE
 * @version 1.0
 *          The class NumberRange offers concrete implementations of the methods
 *          in the NumberRangeSummarizer Interface
 */
public class NumberRange implements NumberRangeSummarizer {
    @Override
    /**
     * Method to collect Iput
     * 
     * @param input The string input
     * @return
     */
    public Collection<Integer> collect(String input) {
        // Checking for empty input
        if (input == null || input.length() == 0)
            return null;

        /*
         * Implicit casting of subclass object HashSet in superclass variable Set
         * Using a HashSet will allow us to extract only the unique values from the
         * input, This approach is optimized
         */
        Set<Integer> uniqueValues = new HashSet<Integer>();

        // Pattern for detecting non-integer inputs
        Pattern pattern = Pattern.compile("-?\\d+\\.?\\d*");

        /*
         * Split the numbers at the delimiter
         * ASSUMING the delimiter is always a comma (,)
         * we Also ASSUME that any floating point number will be truncated to an Integer
         */
        String[] numbers = input.split(",");
        for (String number : numbers) {
            number = number.trim();
            if (pattern.matcher(number).matches()) {
                if (number.contains("."))
                    uniqueValues.add(Float.valueOf(number).intValue());
                else
                    uniqueValues.add(Integer.valueOf(number));
            } else
                throw new IllegalArgumentException("Only Integer Inputs allowed");
        }

        /*
         * Converting the set to an arraylist hence we can...
         * ...obtain indexes for the items
         * ...sort the items
         */
        ArrayList<Integer> sortedList = new ArrayList<Integer>(uniqueValues);
        Collections.sort(sortedList);

        /*
         * Returns the unique & sorted arraylist
         * the arraylist object is implicitly casted onto the
         * collection return variable
         */
        return sortedList;
    }

    @Override
    /**
     * Method to group the sequential values
     * 
     * @param input List of unique and sorted values
     * @return A string of grouped values
     */
    public String summarizeCollection(Collection<Integer> input) {
        // Checking for empty input
        if (input == null || input.size() == 0)
            return "";

        // Using a StringBuilder is more optimized to mutable strings
        StringBuilder strSummarized = new StringBuilder();

        /*
         * We Explicitly Cast the stored ArrayList object from the collection variable
         * This allows easier access to the stored values
         */
        ArrayList<Integer> numList = (ArrayList<Integer>) input;

        // We append the first value on the string builder
        strSummarized.append(numList.get(0));

        // Indicates if the current value in iteration is part of a sequence
        boolean isSequencial = false;

        // Looping through each value in the list starting from the second one
        for (int index = 1; index < numList.size(); index++) {
            if ((numList.get(index) - numList.get(index - 1)) != 1) {
                // If the current value does not form a sequence with the predecessor
                strSummarized.append(", ");
                strSummarized.append(numList.get(index));
            } else if ((index != (numList.size() - 1)) && (numList.get(index + 1) - numList.get(index)) != 1) {
                // If the current value does not forms a sequence with the successor
                strSummarized.append("-");
                strSummarized.append(numList.get(index));
                isSequencial = false;
            } else {
                // If we are inbetween a sequence
                isSequencial = true;
            }
        }

        // If the last item is part of a sequence
        if (isSequencial) {
            strSummarized.append("-");
            strSummarized.append(numList.get(numList.size() - 1));
        }

        // We return a string version of the String we built
        return strSummarized.toString();
    }
}