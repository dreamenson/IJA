package back;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileHandler {
    private final String path;
    private int classType;  // 1=class diagram, 2=sequence diagram
    private ClassDiagram classd;
    private UMLClass umlClass;

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
                ClassHandle(words);
                break;
            default:
                if (words[0].matches("^[-+#~].*")) {
                    AttrHandle(words);
                }
                if (words[0].equals("operation")) {
                    FuncHandle(words);
                }
        }
    }

    private void ClassHandle(String[] words) {
        try {
            UMLClass aClass = classd.createClass(words[1]);
            umlClass = null;
            if (words.length == 3 && Objects.equals(words[2], "{")) {
                umlClass = aClass;
            }
        }
        catch(Exception e) {
            System.err.println(e);
        }
    }

    private void AttrHandle(String[] words) {
        UMLAttribute ua = new UMLAttribute(words[1], new UMLClassifier(words[0]));
        umlClass.addAttribute(ua);
    }

    private void FuncHandle(String[] words) {
        System.out.println(Arrays.toString(words));
        String[] fun = words[2].split("\\(");
        System.out.println(Arrays.toString(fun));
        UMLOperation uo = new UMLOperation(fun[0], new UMLClassifier(words[1]));
        if (!fun[1].equals(")")) {
            System.out.println(fun[1] + ":" + words[3].replace(")", ""));
            UMLAttribute ua = new UMLAttribute(words[3].replace(")", ""), new UMLClassifier(fun[1]));
            uo.addArgument(ua);
        }
        umlClass.addOperation(uo);
    }
}
