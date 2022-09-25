package org.geepacific.daniel.countingpoints;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CountingPoints {
    public int countingPointsMain(String s1, String s2) {
        // [VALIDATING PROCESS]
        // If the 1st string is null, do nothing as there is nothing to calculate.
        if (s1 == null || "".equals(s1)) return -1;
        // If the 1st string has only 1 part, do nothing as it's unable to combine.
        if (!s1.contains("|"))           return -1;
        // If the 2nd string is null, do nothing as no number takes any point. Total point is 0.
        if (s2 == null || "".equals(s2)) return 0;

        // [1ST STRING PROCESS]
        // Combine 2 parts in the 1st string
        List<String> s1Seperated  = List.of(s1.split("\\|"));
        List<String> s1FirstPart  = List.of(s1Seperated.get(0).split("&"));
        List<String> s1SecondPart = List.of(s1Seperated.get(1).split("&"));
        List<Integer> s1NumberList = new ArrayList<>(); // Numbers that are combined from 2 parts of the 1st string
        // Combine
        for (String s1FirstPartElement: s1FirstPart) {
            if (!"".equals(s1FirstPartElement)) {
                for (String s1SecondPartElement: s1SecondPart) {
                    if (!"".equals(s1SecondPartElement))
                        s1NumberList.add(Integer.parseInt(s1FirstPartElement + s1SecondPartElement));
                }
            }
        }

        // [2ND STRING PROCESS]
        // Extract numbers and their values from the 2nd string
        List<String> pairNumberAndPointTemp = List.of(s2.split(","));
        // Remove duplicates
        List<String> pairNumberAndPoint = new ArrayList<>(); // Official list will be used for later processes
        for (String pair : pairNumberAndPointTemp) {
            if (!"".equals(pair) && !pairNumberAndPoint.contains(pair)) {
                pairNumberAndPoint.add(pair);
            }
        }
        // Separate those number-value pairs
        List<Integer> s2FirstPart = new ArrayList<>();
        List<Integer> s2SecondPart = new ArrayList<>();
        for (String pair: pairNumberAndPoint) {
            if (pair.contains("-")) {
                List<String> pairSplit = List.of(pair.split("-"));
                if (pairSplit.size() == 2) { // Process valid pairs only
                    if (!"".equals(pairSplit.get(0)))  s2FirstPart.add(Integer.parseInt(pairSplit.get(0)));
                    if (!"".equals(pairSplit.get(1))) s2SecondPart.add(Integer.parseInt(pairSplit.get(1)));
                }
            } else {
                s2FirstPart.add(Integer.parseInt(pair));
                // Default point for a number is 1 if it stands alone
                // Add -1 to distinguish from number 1 in the s1NumberList if exists
                // A later process will add 1 point on this case.
                s2SecondPart.add(-1);
            }
        }
        // Simply 2 parts of the 2nd string
        Set<Integer> toBeRemovedS2FirstPart  = new HashSet<>();
        Set<Integer> toBeRemovedS2SecondPart = new HashSet<>();
        for (int number: s2FirstPart) {
            if (s2SecondPart.contains(number)) {
                int idx1 =  s2FirstPart.indexOf(number);
                int idx2 = s2SecondPart.indexOf(number);
                // duplicate detection
                if ((s2FirstPart.get(idx2) == s2SecondPart.get(idx1))) {
                    toBeRemovedS2FirstPart.add(s2FirstPart.get(idx1));
                    toBeRemovedS2SecondPart.add(s2SecondPart.get(idx1));
                    // Make them really duplicated
                    int temp = s2FirstPart.get(idx2);
                    s2FirstPart.set(idx2, s2SecondPart.get(idx2));
                    s2SecondPart.set(idx2, temp);
                }
            }
        }
        if (toBeRemovedS2FirstPart.size() > 0 && toBeRemovedS2SecondPart.size() > 0) {
            // remove duplicates
            s2FirstPart.removeAll(toBeRemovedS2FirstPart);
            s2SecondPart.removeAll(toBeRemovedS2SecondPart);
            // each duplicated pair was deleted 2 times by removeAll above, add it again 1 time
            s2FirstPart.addAll(toBeRemovedS2FirstPart);
            s2SecondPart.addAll(toBeRemovedS2SecondPart);
        }
        // Point calculating
        List<Integer> points = new ArrayList<>();
        for (int number: s1NumberList) {
            // If the number is in the 1st list but not in the 2nd list, consider the element in the 2nd list is its value.
            if (s2FirstPart.contains(number) && !s2SecondPart.contains(number)) {
                int idx = s2FirstPart.indexOf(number);
                // add to point array for calculating
                if (s2SecondPart.get(idx) == -1) points.add(1);
                else points.add(s2SecondPart.get(idx));
            }
            // If the number is in the 2nd list but not in the 1st list, consider the element in the 1st list is its value.
            if (!s2FirstPart.contains(number) && s2SecondPart.contains(number)) {
                int idx = s2SecondPart.indexOf(number);
                // add to point array for calculating
                points.add(s2FirstPart.get(idx));
            }
            // If the number is in both lists, consider the elements that has the same index of the other list of a list are its values.
            // Then compute their sum
            if (s2FirstPart.contains(number) && s2SecondPart.contains(number)) {
                int idx1 = s2FirstPart.indexOf(number);
                int idx2 = s2SecondPart.indexOf(number);
                // add to point array for calculating
                if (s2SecondPart.get(idx1) == -1) points.add(s2FirstPart.get(idx2) + 1);
                else points.add(s2FirstPart.get(idx2) + s2SecondPart.get(idx1));
            }
        }
        // Calculate total point
        int totalPoints = points.stream().mapToInt(Integer::intValue).sum();
        return totalPoints;
    }
}