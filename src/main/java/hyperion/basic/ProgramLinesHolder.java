package hyperion.basic;

import hyperion.basic.command.Command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author Lukas Stanek
 * @since 06.03.21
 */
public class ProgramLinesHolder {
    private final Map<Integer, Line> lineMap = new HashMap<>();
    // private final List<Integer> linesNumbers;

    public void add(int lineNumber, List<Command> commands, String rawText) {
        Line line = new Line(lineNumber, commands, rawText);
        lineMap.put(lineNumber, line);
    }

    public Stream<Line> listLines() {
        return lineMap.keySet().stream().sorted().map(lineMap::get);
    }
}