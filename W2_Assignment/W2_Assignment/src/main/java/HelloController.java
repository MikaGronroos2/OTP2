import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Locale;
import java.util.ResourceBundle;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Label lbl;

    private ResourceBundle bundle;
    private Locale locale;



    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application");
    }

    @FXML
    private void loadLanguage(String lang, String country) {
        locale = new Locale(lang, country);
        bundle = ResourceBundle.getBundle("message", locale);
        lbl.setText(bundle.getString("greeting"));
    }

    public void onbtnENClick(ActionEvent actionEvent) {
        loadLanguage("en", "UK");
    }

    public void onbtnFRClick(ActionEvent actionEvent) {
        loadLanguage("fr", "FR");
    }

    public void onbtnJPClick(ActionEvent actionEvent) {
        loadLanguage("ja", "JP");
    }

    public void onbtnIRClick(ActionEvent actionEvent) {
        loadLanguage("fa", "IR");
    }
}
