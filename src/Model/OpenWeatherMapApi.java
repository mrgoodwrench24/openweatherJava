package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class OpenWeatherMapApi {

    private static String apiKey;

    public static String getApiKey(){
        File keyReader = new File("src/Model/apiKey.txt");
        try {
            Scanner myReader;
            myReader = new Scanner(keyReader);
            apiKey = myReader.nextLine();
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot file apiKey.txt");
            e.printStackTrace();
        }
        return apiKey;
    }
}
