import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    FuelConverterController controller = new FuelConverterController(this);
    ResourceBundle bundle = ResourceBundle.getBundle("messages", new Locale("en"));
    Insets insets = new Insets(10, 10, 10, 10);

    Button convertButton = new Button();
    Button button1 = new Button();
    Button button2 = new Button();
    Button button3 = new Button();
    Button button4 = new Button();

    TextField textField1 = new TextField();
    TextField textField2 = new TextField();

    Label label1 = new Label();
    Label label2 = new Label();
    Label result = new Label();

    HBox hBox = new HBox();
    VBox vBox = new VBox();



    @Override
    public void start(Stage stage) throws IOException {

        vBox.setPadding(insets);
        hBox.getChildren().addAll(convertButton, result);
        vBox.getChildren().addAll(label1,textField1, label2, textField2, hBox, button1, button2, button3, button4);

        button1.setOnAction(e -> switchLanguage("en"));
        button2.setOnAction(e -> switchLanguage("fr"));
        button3.setOnAction(e -> switchLanguage("jp"));
        button4.setOnAction(e -> switchLanguage("fa"));
        convertButton.setOnAction(e -> calculateFuelConsumption());

        convertButton.setText(bundle.getString("convertButton.text"));
        button1.setText(bundle.getString("button1.text"));
        button2.setText(bundle.getString("button2.text"));
        button3.setText(bundle.getString("button3.text"));
        button4.setText(bundle.getString("button4.text"));
        label1.setText(bundle.getString("label1.text"));
        label2.setText(bundle.getString("label2.text"));

        Scene scene = new Scene(vBox, 400, 300);
        stage.setTitle("Fuel Calculator - Mika Grönroos");
        stage.setScene(scene);
        stage.show();
    }

    private void switchLanguage(String languageCode) {
        bundle = ResourceBundle.getBundle("messages", new Locale(languageCode));
        updateTexts();
    }

    private void updateTexts() {
        convertButton.setText(bundle.getString("convertButton.text"));
        button1.setText(bundle.getString("button1.text"));
        button2.setText(bundle.getString("button2.text"));
        button3.setText(bundle.getString("button3.text"));
        button4.setText(bundle.getString("button4.text"));
        label1.setText(bundle.getString("label1.text"));
        label2.setText(bundle.getString("label2.text"));
    }

    private void calculateFuelConsumption() {
        try {
            double distance = Double.parseDouble(textField1.getText());
            double fuelUsed = Double.parseDouble(textField2.getText());
            double consumption = (fuelUsed / distance) * 100;
            double price = consumption * 1.67;
            result.setText(String.format(bundle.getString("result.text"), consumption, price));
        } catch (NumberFormatException e) {
            result.setText(bundle.getString("error.text"));
        }
    }
}
