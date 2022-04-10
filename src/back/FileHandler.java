package back;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileHandler {
    private final String path;
    private int classType;  // 1=class diagram, 2=sequence diagram
    private ClassDiagram classd;

    public FileHandler(String name) {
        path = name;
    }

    public void read() {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(path));
            for (String line : allLines) {
                ParseLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ParseLine(String line) {
        String[] words = line.split("\\s+");
        switch (words[0]) {
            case "@startclass":
                if (classType == 0) {
                    classType = 1;
                    System.out.println("SSSS "+words[1]);
                    classd = new ClassDiagram(words[1]);
                }
                break;
            case "@endclass":
                if (classType == 1) classType = 0;
                break;
            case "@startsq":
                if (classType == 0) classType = 2;
                break;
            case "@endsq":
                if (classType == 2) classType = 0;
                break;
            case "Class":
                try {
                    UMLClass aClass = classd.createClass(words[1]);
                    System.out.println("SSSEEES "+ words[1] +":"+ aClass.getName());
                }
                catch(Exception e) {
                    System.err.println(e);
                }
        }
    }
}
