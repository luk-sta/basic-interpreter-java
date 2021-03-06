package hyperion.basic;

import hyperion.basic.command.Command;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Lukas Stanek
 * @since 06.03.21
 */
public class ProgramLinesHolder {
    private final Map<Integer, Line> lineMap = new HashMap<>();
    private List<Integer> linesNumbers;

    public void add(int lineNumber, List<Command> commands, String rawText) {
        Line line = new Line(lineNumber, commands, rawText);
        lineMap.put(lineNumber, line);
    }

    public void init() {
        linesNumbers = lineMap.keySet().stream().sorted().collect(Collectors.toList());
    }

    public Iterator<Integer> lineNumberIterator(int from) {
        return lineNumberStream(from).iterator();
    }

    public Stream<Integer> lineNumberStream(int from) {
        return linesNumbers.stream().dropWhile(l -> l < from);
    }

    public Line get(int lineNum) {
        return lineMap.get(lineNum);
    }

    public void clear() {
        lineMap.clear();
    }
}
