package Controller;

import Model.OpenWeatherMapApi;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class WeatherViewController implements Initializable {

    @FXML
    private Label cityLabel;

    @FXML
    private Label currentConditionsLabel;

    @FXML
    private Label currentTempLabel;

    @FXML
    private Label feelsLikeTempLabel;

    @FXML
    private Label highTempLabel;

    @FXML
    private Label lowTempLabel;

    @FXML
    private TextField zipCodeTextBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String apiKey = OpenWeatherMapApi.getApiKey();
        System.out.println(apiKey);

    }
}
