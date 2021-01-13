package Main;

import UI.Ui;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;

import javafx.application.Application;

public class Main {
    public static void main(String[] args) throws IOException, CsvException {
       Application.launch(Ui.class);
    }
}
