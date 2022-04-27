package back;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Trieda reprezentuje pracu so suborom, ktoreho obsahom je UMLDiagram (pripadne viac). Obsahuje absolutnu cestu k danemu suboru.
 *
 * @author Viliam Holik - xholik14
 */
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
                parseLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ClassDiagram - " + classd.getName());
        classd.getClassList().forEach( (n) -> {
            System.out.println("Class " + n.getName());
            n.getAttributes().forEach( (a) -> {
                String type = a.getType().getName();
                System.out.println(type + ":" + a.getName());
            });
            n.getOperations().forEach( (o) -> {
                String type = o.getType().getName();
                System.out.println("operation " + type + ":" + o.getName());
                System.out.println("params {");
                o.getArguments().forEach( (a) -> {
                    String type1 = a.getType().getName();
                    System.out.println(type1 + ":" + a.getName());
                });
                System.out.println("}");
            });
        });
        classd.getRelationList().forEach( (n) -> System.out.println(n.getFirstClass().getName() + " : " + n.getRelation() + " : " + n.getSecondClass().getName() + " : " + n.getName()));
//        System.out.println(Arrays.toString(classd.getClassList()));
    }

    public ClassDiagram getClassDiagram() {
        return classd;
    }

    private void parseLine(String line) {
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
                    break;
                }
                if (words[0].equals("operation")) {
                    FuncHandle(words);
                    break;
                }
                if (words[0].matches("[A-Za-z].*")) {
                    RelationHandle(line);
                }
        }
    }

    private void ClassHandle(String[] words) {
        UMLClass aClass = classd.createClass(words[1]);
        umlClass = null;
        if (words.length == 3 && Objects.equals(words[2], "{")) {
            umlClass = aClass;
        }
    }

    private void AttrHandle(String[] words) {
        UMLAttribute ua = new UMLAttribute(words[1], new UMLClassifier(words[0]));
        umlClass.addAttribute(ua);
    }

    private void FuncHandle(String[] words) {
        String[] fun = words[2].split("\\(");
        UMLOperation uo = new UMLOperation(fun[0], new UMLClassifier(words[1]));
        if (!fun[1].equals(")")) {
            String name = words[3].replaceAll("[,)]", "");
            UMLAttribute ua = new UMLAttribute(name, new UMLClassifier(fun[1]));
            uo.addArgument(ua);
            if (words.length > 4) {
                FuncParamHandle(words, uo);
            }
        }
        umlClass.addOperation(uo);
    }

    private void FuncParamHandle(String[] words, UMLOperation uo) {
        int size = words.length;
        for (int i=4; i < size; i=i+2) {
            String name = words[i+1].replaceAll("[,)]", "");
            UMLAttribute ua = new UMLAttribute(name, new UMLClassifier(words[i]));
            uo.addArgument(ua);
        }
    }

    private void RelationHandle(String line) {
        String[] relation = line.split("\\s*:\\s*");
        UMLRelation ur = classd.createRelation(relation[3], relation[0], relation[2], relation[1]);
        if (ur == null) {
            System.out.println("chyba pri relacii");
        }
    }
}
