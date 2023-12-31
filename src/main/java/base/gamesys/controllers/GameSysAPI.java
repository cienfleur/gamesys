package base.gamesys.controllers;

import base.gamesys.models.*;
import base.gamesys.utils.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.Collection;

public class GameSysAPI {

        // fields for general game system management

        public int indexSelected;

        // fields for managing game machines
        public ScratchList<GameMachine> gameMachines = new ScratchList<>();
        public ObservableList<GameMachine> gameMachineView = FXCollections.observableArrayList();
        public ListView<GameMachine> gameMachineListView;

        public TextField machineName, manufacturer, description, type, developer, media, initialLaunchYear, initialRRP, imageUrl;
        // fields for managing games

        public ScratchList<Game> games = new ScratchList<>();
        public ObservableList<Game> gameView = FXCollections.observableArrayList();

        public ListView<Game> gameListView;

        public TextField gameName, gameDescription, gameGenre, gameDeveloper, gamePublisher, gameReleaseYear, gameImageUrl;

        // fields for managing game ports

        public ObservableList<GamePort> gamePortView = FXCollections.observableArrayList();

        public ListView<GamePort> gamePortListView;

        public TextField portName, portDescription, portCountry, portImageUrl;

        // functions for managing game machines

        public void addGameMachine(ActionEvent addGameMachineEvent) {
            GameMachine newGameMachine = new GameMachine(machineName.getText(), manufacturer.getText(), description.getText(), type.getText(), media.getText(), Integer.parseInt(initialLaunchYear.getText()), Double.parseDouble(initialRRP.getText()), imageUrl.getText());
            gameMachines.addElement(newGameMachine);
            gameMachineView.add(newGameMachine);
            gameMachineListView.setItems(gameMachineView);
        }

        public void removeGameMachine(ActionEvent removeGameMachineEvent) {
            if (gameMachines.getLength() > 0) {
                gameMachines.removeElement(indexSelected - 1);
                gameMachineView.remove(indexSelected - 1);
                gameMachineListView.setItems(gameMachineView);
            }
        }

        public void selectGameMachine(MouseEvent selectGameMachineEvent) {
            indexSelected = gameMachineListView.getSelectionModel().getSelectedIndex();
            System.out.println(indexSelected);
        }

        // functions for managing games

        public void addGame(ActionEvent addGameEvent) {
            Game newGame = new Game(gameName.getText(), gameDescription.getText(), gameGenre.getText(), gameDeveloper.getText(), gamePublisher.getText(), Integer.parseInt(gameReleaseYear.getText()), gameImageUrl.getText());
            games.addElement(newGame);
            gameView.add(newGame);
            gameListView.setItems(gameView);
        }

        public void removeGame(ActionEvent removeGameEvent) {
            if (games.getLength() > 0) {
                games.removeElement(indexSelected - 1);
                gameView.remove(indexSelected - 1);
                gameListView.setItems(gameView);
            }
        }

        public void selectGame(MouseEvent selectGameEvent) {
            indexSelected = gameListView.getSelectionModel().getSelectedIndex();
            System.out.println(indexSelected);
        }
}
