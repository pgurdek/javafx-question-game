package pl.ui;

import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class Box {

    Stage window = new Stage();
    Label label = new Label();

    public Box() {
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);
        label.setWrapText(true);
    }

    public  abstract  void display(String title, String message);
}
