package com.aronson.jdkbasis;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Sherlock
 */
public class StreamUse {

    public static List<String> stringArrayToList(String[] strings) {
        return Arrays.stream(strings).collect(Collectors.toList());
    }

}
