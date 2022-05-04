package back;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Viliam Holik
 */
public class SequenceDiagram extends Element {
    private List<UMLClass> participants = new ArrayList<>();
    private List<UMLMessage> messageList = new ArrayList<>();
    private ClassDiagram classd;

    public SequenceDiagram(String name, ClassDiagram classDiagram) {
        super(name);
        classd = classDiagram;
    }

    public boolean addParticipant(UMLClass umlClass) {
        if (umlClass == null) {
            System.out.println("Error - addParticipant() umlClass = null");
        }
        String name = umlClass.getName();
        for (UMLClass uc : participants) {
            if (uc.getName().equals(name)) {
                return false;
            }
        }
        return participants.add(umlClass);
    }

    public boolean addMessage(String name, int type, String first, String second) {
        UMLClass firstClass = (UMLClass)classd.findClass(first);
        UMLClass secondClass = (UMLClass)classd.findClass(second);
        UMLMessage umlMessage = new UMLMessage(name, type, firstClass, secondClass);
        return messageList.add(umlMessage);
    }

    public List<UMLClass> getParticipantList() {
        return Collections.unmodifiableList(participants);
    }

    public List<UMLMessage> getMessageList() {
        return Collections.unmodifiableList(messageList);
    }
}
