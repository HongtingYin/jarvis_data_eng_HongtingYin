package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepImp implements JavaGrep {

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  private String regex;
  private String rootPath;
  private String outFile;

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }
    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);

    try{
      javaGrepImp.process();
    } catch (Exception e) {
      javaGrepImp.logger.error(e.getMessage(), e);
    }
  }

  @Override
  public void process() throws IOException {
    List<String> matechedLines = new LinkedList<>();
    for (File file : listFiles(this.rootPath)) {
      for (String str : readLines(file)) {
        if (containsPattern(str)) {
          matechedLines.add(str);
        }
      }
    }
    writeToFile(matechedLines);
  }

  @Override
  public List<File> listFiles(String rootDir) {
    List<File> listOfFiles = new LinkedList<>();
    final File rootFile = new File(rootDir);
    try {
      listFiles(rootFile, listOfFiles);
    } catch (Exception e){
      logger.error(e.getMessage(), e);
    }
    return listOfFiles;
  }

  //Helper function to facilitate recursice calls to subdirectories
  private void listFiles(File folderFile, List<File> fileList) {
    for (final File file : folderFile.listFiles()) {
      if (file.isDirectory()) {
        listFiles(file, fileList);
      }
      if (file.isFile()) {
        fileList.add(file);
      }
    }
  }

  @Override
  public List<String> readLines(File inputFile) {
    List<String> inputLines = new LinkedList<>();
    try {
      BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
      String line = bufferedReader.readLine();
      while (line != null) {
        inputLines.add(line);
        line = bufferedReader.readLine();
      }
      bufferedReader.close();
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
    return inputLines;
  }

  @Override
  public boolean containsPattern(String line) {
    Pattern pattern = Pattern.compile(this.regex);
    Matcher matcher = pattern.matcher(line);
    return matcher.matches();
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.getOutFile()));
    for (String line : lines) {
      bufferedWriter.write(line + System.lineSeparator());
    }
    bufferedWriter.close();
  }

  @Override
  public String getRegex() {
    return regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getRootPath() {
    return rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  public String getOutFile() {
    return outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }
}