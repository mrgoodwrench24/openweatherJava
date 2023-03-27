package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class WeatherViewController implements Initializable {

    private HashMap cityInfo;

    private HashMap currentConditions;
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

    @FXML
    void onActionSearchButton(ActionEvent event) throws IOException {
        String zipCode = zipCodeTextBox.getText();
        cityInfo = Model.OpenWeatherMapApi.getCityInfo(zipCode);
        cityLabel.setText("Current conditions for " + cityInfo.get("name"));
        String lat = (String) cityInfo.get("lat");
        String lon = (String) cityInfo.get("lon");

        currentConditions = Model.OpenWeatherMapApi.getCurrentConditions(lat,lon);
        currentConditionsLabel.setText((String) currentConditions.get("description"));
        currentTempLabel.setText(String.format("%.1f", currentConditions.get("temp")) + "F");
        feelsLikeTempLabel.setText(String.format("%.1f", currentConditions.get("feels_like")) + "F");
        highTempLabel.setText(String.format("%.1f", currentConditions.get("temp_max")) + "F");
        lowTempLabel.setText(String.format("%.1f", currentConditions.get("temp_min")) + "F");





    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
