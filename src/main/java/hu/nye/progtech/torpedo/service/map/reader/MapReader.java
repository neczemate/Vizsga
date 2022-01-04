package hu.nye.progtech.torpedo.service.map.reader;

import java.util.List;

import hu.nye.progtech.torpedo.service.exception.MapReadException;

/**
 * Interface for map reader.
 */
public interface MapReader {

    List<String> readMap() throws MapReadException;
}
