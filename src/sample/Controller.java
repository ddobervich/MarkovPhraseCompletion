package sample;

import com.sun.javafx.scene.control.skin.TextAreaSkin;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Controller {

    @FXML
    private TextArea textArea;

    @FXML
    private ContextMenu popup;

    @FXML
    private Button trainButton;

    private TextModel predictor;

    public void Controller() {
    }

    @FXML
    public void initialize() {
        predictor = new TextModel();

        FileChooser fileChooser = new FileChooser();
        File workingDirectory = new File(System.getProperty("user.dir"));
        fileChooser.setInitialDirectory(workingDirectory);
        fileChooser.setTitle("Open a training file");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            System.out.println("Opening file: " + file.toString());
            predictor.loadData(file);
        }
    }

    @FXML
    private void processKeyTyped(KeyEvent e) {
        String letter = e.getCharacter();

        if (letter.equals(" ")) {
            String text = textArea.getText();
            String lastWord = text.substring( text.lastIndexOf(" ")+1).trim();

            populatePopup( predictor.predictNext(lastWord));

            popup.show(textArea, Side.BOTTOM, 0, 0);
        } else {
            popup.hide();
        }
        //System.out.println("User hit key: " + e.getCharacter());
        // textArea.setText(textArea.getText() + e.getCharacter());
    }

    private void populatePopup(String[] results) {
        List<CustomMenuItem> menuItems = new LinkedList<>();

        for (int i = 0; i < results.length; i++) {
            final String result = results[i];
            Label entryLabel = new Label(result);
            CustomMenuItem item = new CustomMenuItem(entryLabel, true);

            item.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    textArea.appendText(result);
                    popup.hide();
                }
            });

            menuItems.add(item);
        }
        popup.getItems().clear();
        popup.getItems().addAll(menuItems);
    }

    @FXML
    public void addTrainingDataFromFile(ActionEvent e) {
        System.out.println("CLICKED!");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a text file");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            System.out.println("Opening file: " + file.toString());
            predictor.loadData(file);
        }
    }
}