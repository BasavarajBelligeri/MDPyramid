package com.md.pyramid.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import com.md.pyramid.exception.MdPyramidException;

public final class FileUtil {

    private FileUtil() {
    }

    private static final String SPACE_REGX = "\\s+";

    public static int[][] prepareArrayFromInputFile(final String inputFilePath) {
        try {
            URL url = FileUtil.class.getResource(inputFilePath);
            if (null == url) {
                throw new MdPyramidException("File does not exist for input file name :" + inputFilePath);
            }
            File file = new File(url.getPath());
            return getNumberArray(file);
        } catch (IOException exception) {
            throw new MdPyramidException("Exception occrued while reading file " + inputFilePath, exception);
        }
    }

    private static int[][] getNumberArray(final File file) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(file.getPath()));) {
            return lines.map(FileUtil::getArrayFromLine)
                    .toArray(int[][]::new);
        }

    }

    private static int[] getArrayFromLine(final String line) {
        return Arrays.stream(line.trim().split(SPACE_REGX))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

}
