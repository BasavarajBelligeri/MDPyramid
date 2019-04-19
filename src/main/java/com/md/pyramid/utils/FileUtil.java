package com.md.pyramid.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import com.md.pyramid.exception.MdPyramidException;

public class FileUtil {

    private static final String SPACE_REGX = "\\s+";

    public int[][] prepareArrayFromInputFile(final String inputFilePath) {
        try {
            URL url = getClass().getResource(inputFilePath);
            if (null == url) {
                throw new MdPyramidException("File does not exist for input file name :" + inputFilePath);
            }
            File file = new File(url.getPath());
            return getNumberArray(file);
        } catch (IOException exception) {
            throw new MdPyramidException("Exception occrued while reading file " + inputFilePath, exception);
        }
    }

    private int[][] getNumberArray(final File file) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(file.getPath()));) {
            return lines.map(this::getArrayFromLine)
                    .toArray(int[][]::new);
        }

    }

    private int[] getArrayFromLine(final String line) {
        return Arrays.stream(line.trim().split(SPACE_REGX))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

}
