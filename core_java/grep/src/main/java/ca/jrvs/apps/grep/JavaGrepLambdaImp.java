package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepLambdaImp extends JavaGrepImp {

    final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Usage: JavaGrep [regex] [rootPath] [outFilePath]");
        }

        JavaGrepImp javaGrepLambdaImp = new JavaGrepLambdaImp();
        javaGrepLambdaImp.setRegex(args[0]);
        javaGrepLambdaImp.setRootPath(args[1]);
        javaGrepLambdaImp.setOutFile(args[2]);

        try {
            javaGrepLambdaImp.process();
        } catch (Exception e) {
            javaGrepLambdaImp.logger.error(e.getMessage(), e);
        }
    }

    /**
     * Implement using lambda and stream APIs
     */
    @Override
    public void process() throws IOException {
        writeToFile(
            listFiles(getRootPath())
                .stream()
                .flatMap(
                    f -> readLines(f)
                        .stream()
                        .filter(this::containsPattern)
                )
                .collect(Collectors.toList())
        );
    }

    @Override
    public List<File> listFiles(String rootDir) {
        List<File> files = new LinkedList<>();
        try {
            Files.walk(Paths.get(rootDir))
                .filter(Files::isRegularFile)
                .forEach(f -> files.add(f.toFile()));
                .forEach(f -> ls.add(f.toFile()));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return files;
    }

    @Override
    public List<String> readLines(File inputFile) {
        List<String> listLines = new ArrayList<>();
        try (Stream<String> stream = Files.lines(inputFile.toPath())) {
            listLines = stream.collect(Collectors.toList());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return listLines;
    }
}