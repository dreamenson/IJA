package back;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Trieda reprezentuje pracu so suborom, ktoreho obsahom je UMLDiagram (pripadne viac). Obsahuje absolutnu cestu k danemu suboru.
 * @author Viliam Holik - xholik14
 */
public class FileHandler {
    private final String path;
    private int classType;  // 1=class diagram, 2=sequence diagram
    private ClassDiagram classd;
    private UMLClass umlClass;
    private UMLInterface umlInterface;
    private final List<SequenceDiagram> sqlist= new ArrayList<>();
    private SequenceDiagram activeSq;

    /**
     * Vytvori objekt pre pracu so suborom
     * @param name retazec absolutnej cesty k suboru s UML diagramom
     */
    public FileHandler(String name) {
        path = name;
    }

    /**
     * Cita postupne riadok za riadkom zo vstupneho suboru a parsuje tieto riadky
     */
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
            if(n instanceof UMLClass) {
                System.out.println("Class " + n.getName());
                UMLClass uc = (UMLClass)n;
                uc.getAttributes().forEach( (a) -> {
                    String type = a.getType().getName();
                    System.out.println(type + ":" + a.getName());
                });
                uc.getOperations().forEach( (o) -> {
                    String type = o.getType().getName();
                    System.out.println("operation " + type + ":" + o.getName());
                    System.out.println("params {");
                    o.getArguments().forEach( (a) -> {
                        String type1 = a.getType().getName();
                        System.out.println(type1 + ":" + a.getName());
                    });
                    System.out.println("}");
                });
            }else {
                System.out.println("Interface " + n.getName());
                UMLInterface ui = (UMLInterface) n;
                ui.getAttributes().forEach((a) -> {
                    String type = a.getType().getName();
                    System.out.println(type + ":" + a.getName());
                });
                ui.getOperations().forEach( (o) -> {
                    String type = o.getType().getName();
                    System.out.println("operation " + type + ":" + o.getName());
                    System.out.println("params {");
                    o.getArguments().forEach( (a) -> {
                        String type1 = a.getType().getName();
                        System.out.println(type1 + ":" + a.getName());
                    });
                    System.out.println("}");
                });
            }
        });
        classd.getRelationList().forEach( (n) -> System.out.println(n.getFirstClass().getName() + " : " + n.getRelation() + " : " + n.getSecondClass().getName()));
        sqlist.forEach( (n) -> {
            System.out.println("\nSequence " + n.getName());
            n.getParticipantList().forEach( (a) -> {
                System.out.println("Participant " + a.getName());
            });
            n.getMessageList().forEach( (a) -> {
                int type = a.getType();
                if (type == 1) {
                    System.out.println("activate " + a.getFirstClass().getName());
                } else if (type == 2) {
                    System.out.println("deactivate " + a.getFirstClass().getName());
                } else {
                    System.out.println(a.getFirstClass().getName() + " : 3 : " + a.getSecondClass().getName() + " " + a.getName());
                }
            });
        });
    }

    public void write(String path) {
        StringBuilder sb = new StringBuilder();
        if (classd != null) {
            addText(sb, "@startclass " + classd.getName(), 2);
            classd.getClassList().forEach( (n) -> {
                if (n instanceof UMLClass) {
                    UMLClass uc = (UMLClass) n;
                    addText(sb, "Class " + uc.getName() + " {", 1);
                    uc.getAttributes().forEach( (a) -> {
                        addText(sb, a.getType().getName() + " " + a.getName(), 1);
                    });
                    uc.getOperations().forEach( (o) -> {
                        addText(sb, "operation " + o.getType().getName() + " " + o.getName() + "(", 0);
                        o.getArguments().forEach( (a) -> {
                            addText(sb, a.getType().getName() + " " + a.getName() + ", ", 0);
                        });
                        int length = sb.length();
                        if (sb.charAt(length - 2) == ',') {
                            sb.delete(length - 2, length);
                        }
                        addText(sb, ")", 1);
                    });
                    addText(sb, "}", 1);
                } else {
                    UMLInterface uc = (UMLInterface) n;
                    addText(sb, "Interface " + uc.getName() + " {", 1);
                    uc.getAttributes().forEach( (a) -> {
                        addText(sb, a.getType().getName() + " " + a.getName(), 1);
                    });
                    uc.getOperations().forEach( (o) -> {
                        addText(sb, "operation " + o.getType().getName() + " " + o.getName() + "(", 0);
                        o.getArguments().forEach( (a) -> {
                            addText(sb, a.getType().getName() + " " + a.getName() + ", ", 0);
                        });
                        int length = sb.length();
                        if (sb.charAt(length - 2) == ',') {
                            sb.delete(length - 2, length);
                        }
                        addText(sb, ")", 1);
                    });
                    addText(sb, "}", 1);
                }
            });
        }
        try {
            FileWriter myWriter = new FileWriter(path);
            myWriter.write(sb.toString());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        classd.getRelationList().forEach( (n) -> System.out.println(n.getFirstClass().getName() + " : " + n.getRelation() + " : " + n.getSecondClass().getName()));
        sqlist.forEach( (n) -> {
            System.out.println("\nSequence " + n.getName());
            n.getParticipantList().forEach( (a) -> {
                System.out.println("Participant " + a.getName());
            });
            n.getMessageList().forEach( (a) -> {
                int type = a.getType();
                if (type == 1) {
                    System.out.println("activate " + a.getFirstClass().getName());
                } else if (type == 2) {
                    System.out.println("deactivate " + a.getFirstClass().getName());
                } else {
                    System.out.println(a.getFirstClass().getName() + " : 3 : " + a.getSecondClass().getName() + " " + a.getName());
                }
            });
        });
    }

    private void addText(StringBuilder sb, String line, int count) {
        sb.append(line);
        for (int i = 0; i < count; i++) {
            sb.append(System.getProperty("line.separator"));
        }
    }

    /**
     * Vracia instanciu triedy ClassDiagram
     * @return instancia triedy ClassDiagram
     */
    public ClassDiagram getClassDiagram() {
        return classd;
    }

    /**
     * Vracia zoznam prvkov sekvencneho diagramu
     * @return zoznam prvkov sekvencneho diagramu
     */
    public List<SequenceDiagram> getSequenceDiagram() {
        return sqlist;
    }

    /**
     * Funkcia parsuje riadok
     * @param line riadok zo vstupneho suboru
     */
    private void parseLine(String line) {
//        System.out.println(line);
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
                if (classType == 0) {
                    classType = 2;
                    if (classd != null) {
                        activeSq = new SequenceDiagram(words[1], classd);
                        sqlist.add(activeSq);
                    }
                }
                break;
            case "@endsq":
                if (classType == 2) {
                    classType = 0;
                    activeSq = null;
                }
                break;
            case "Class":
                if (classType == 1) {
                    ClassHandle(words);
                }
                break;
            case "Interface":
                if (classType == 1) {
                    InterfaceHandle(words);
                }
                break;
            case "Participant":
                if (classType == 2) {
                    participeSQ(words);
                }
                break;
            case "Main":
                if (classType == 2) {
                    activeSq.addMain(words[1]);
                }
                break;
            case "activate":
                if (classType == 2) {
                    activeSq.addMessage(words[0], 1, words[1], null);
                }
                break;
            case "deactivate":
                if (classType == 2) {
                    activeSq.addMessage(words[0], 2, words[1], null);
                }
                break;
            default:
                if (classType == 1) {
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
                } else if (classType == 2) {
                    if (words[0].matches("[A-Za-z].*")) {
                        MessageHandle(words);
                    }
                }
        }
    }

    /**
     * Vytvori Class-u s danym nazvom
     * @param words pole retazcov z parsovania riadku
     */
    private void ClassHandle(String[] words) {
        UMLClass aClass = classd.createClass(words[1]);
        umlClass = null;
        if (words.length == 3 && Objects.equals(words[2], "{")) {
            umlClass = aClass;
        }
    }

    /**
     * Vytvori instanciu atributu a prida ho k danej Class-e
     * @param words pole retazcov z parsovania riadku
     */
    private void AttrHandle(String[] words) {
        UMLAttribute ua = new UMLAttribute(words[1], new UMLClassifier(words[0]));
        if(umlClass != null) {
            umlClass.addAttribute(ua);
        }
        if(umlInterface != null) {
            umlInterface.addAttribute(ua);
        }
    }

    /**
     * Vytvori instanciu operacie a prida k danej Class-e
     * @param words pole retazcov z parsovania riadku
     */
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
        if(umlClass != null) {
            umlClass.addOperation(uo);
        }
        if(umlInterface != null) {
            umlInterface.addOperation(uo);
        }
    }

    /**
     * Vytvori instancie argumentov pre operaciu Class-y a prida ich k danej operacii
     * @param words pole retazcov z parsovania riadku
     * @param uo UML operacia do ktorej sa pridaju argumenty
     */
    private void FuncParamHandle(String[] words, UMLOperation uo) {
        int size = words.length;
        for (int i=4; i < size; i=i+2) {
            String name = words[i+1].replaceAll("[,)]", "");
            UMLAttribute ua = new UMLAttribute(name, new UMLClassifier(words[i]));
            uo.addArgument(ua);
        }
    }

    /**
     * Vytvorenie instancie relacie pre konkretne Class-y
     * @param line riadok zo vstupneho suboru
     */
    private void RelationHandle(String line) {
        String[] relation = line.split("\\s*:\\s*");
        int type;

        switch (relation[1]) {
            case "generalize":
                type = 1;
                break;
            case "associate":
                type = 2;
                break;
            case "agregate":
                type = 3;
                break;
            case "composite":
                type = 4;
                break;
            default:
                type = 1;
        }

        UMLRelation ur = classd.createRelation(relation[0], relation[2], type);
        if (ur == null) {
            System.out.println("chyba pri relacii");
        }
    }

    private void InterfaceHandle(String[] words) {
        UMLInterface anInterface = classd.createInterface(words[1]);
        umlInterface = null;
        if (words.length == 3 && Objects.equals(words[2], "{")) {
            umlInterface = anInterface;
        }
    }

    private void participeSQ(String[] words) {
        UMLClass umlClass = (UMLClass) classd.findClass(words[1]);
        activeSq.addParticipant(umlClass);
    }

    private void MessageHandle(String[] words) {
        activeSq.addMessage(words[3], 3, words[0], words[2]);
    }
}