package pl.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class InfoBox extends Box {
    @Override
    public void display(String title, String message) {
        window.setTitle(title);
        label.setText(message);
        label.setPadding(new Insets(15));
        label.setAlignment(Pos.CENTER);
        Button okButton = new Button("Ok");
        okButton.setOnAction(e -> window.close());

        VBox layout = new VBox(25);
        layout.getChildren().addAll(label, okButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 200, 250);
        window.setScene(scene);
        window.showAndWait();
    }
}
