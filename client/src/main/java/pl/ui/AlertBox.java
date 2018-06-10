package pl.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


public class AlertBox extends Box {

    @Override
    public void display(String title, String message) {
        window.setTitle(title);
        label.setText(message);
        label.setWrapText(true);
        label.setPadding(new Insets(10));
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 150, 200);
        window.setScene(scene);
        window.showAndWait();
    }
}
