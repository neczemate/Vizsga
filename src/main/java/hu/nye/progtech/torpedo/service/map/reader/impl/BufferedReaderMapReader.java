package hu.nye.progtech.torpedo.service.map.reader.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hu.nye.progtech.torpedo.service.exception.MapReadException;
import hu.nye.progtech.torpedo.service.map.reader.MapReader;

/**
 * Class for buffered reader.
 */
public class BufferedReaderMapReader implements MapReader {
    private BufferedReader bufferedReader;

    public BufferedReaderMapReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    @Override
    public List<String> readMap() throws MapReadException {

        String line;
        List<String> result = new ArrayList<>();
        try {
            while ((line = bufferedReader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            throw new MapReadException("Failed to read map");
        }
        return result;
    }
}
