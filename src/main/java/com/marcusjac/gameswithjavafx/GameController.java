package com.marcusjac.gameswithjavafx;

import com.marcusjac.GalacticFighter.GalacticFighter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GameController {
    @FXML
    private Label welcomeText;
    @FXML
    protected void startSnakeGame(ActionEvent actionEvent) {
    }
    @FXML
    protected void startGalacticFighter(ActionEvent actionEvent) {
        try {
            new GalacticFighter().startGame();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}