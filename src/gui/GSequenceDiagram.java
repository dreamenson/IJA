package gui;

import back.SequenceDiagram;
import javafx.event.Event;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class GSequenceDiagram {
    private SequenceDiagram sequenceDiagram;
    private Tab tab;
    private Pane mainPane;
    private List<GParticipant> participantList = new ArrayList<>();

    public GSequenceDiagram(SequenceDiagram sq, TabPane tabPane) {
        sequenceDiagram = sq;
        createTab(tabPane);
        handleContent();
    }

    private void handleContent() {
        sequenceDiagram.getParticipantList().forEach( p -> {
            participantList.add(new GParticipant(p, mainPane));
        });
    }

    private void createTab(TabPane tabPane) {
        mainPane = new Pane();
        tab = new Tab(sequenceDiagram.getName(), mainPane);
        tab.setOnSelectionChanged(this::refresh);
        tab.setClosable(false);
        tabPane.getTabs().add(tab);
    }

    private void refresh(Event event) {
        for (GParticipant gParticipant : participantList) {
            gParticipant.refresh();
        }
    }
}
