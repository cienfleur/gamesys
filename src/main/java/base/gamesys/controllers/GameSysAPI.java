package base.gamesys.controllers;

import base.gamesys.models.Game;
import base.gamesys.models.GameMachine;
import base.gamesys.models.GamePort;
import base.gamesys.utils.ScratchList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Integer.parseInt;

public class GameSysAPI {

        // fields for general game system management

        public int indexSelected;

        // fields for managing game machines
        public ScratchList<GameMachine> gameMachines = new ScratchList<>();
        public ObservableList<GameMachine> gameMachineView = FXCollections.observableArrayList();
        public ListView<GameMachine> gameMachineListView;

        public GameMachine selectedGameMachine;

        public TextField machineName, manufacturer, description, type, developer, media, initialLaunchYear, initialRRP, imageUrl;
        // fields for managing games

        public ScratchList<Game> games = new ScratchList<>();
        public ObservableList<Game> gameView = FXCollections.observableArrayList();

        public ListView<Game> gameListView;

        public Game selectedGame;

        public TextField gameName, gameDescription, gameGenre, gameDeveloper, gamePublisher, gameReleaseYear, gameImageUrl;

        // fields for managing game ports

        public ScratchList<GamePort> gamePorts = new ScratchList<>();
        public ObservableList<GamePort> gamePortView = FXCollections.observableArrayList();

        public ListView<GamePort> gamePortListView;

        public GamePort selectedGamePort;

        public TextField portMachine, portName, portDescription, portReleaseYear, portImageUrl;

        // fields for searching for game machines

        public TextField searchMachineName, searchManufacturer, SearchType;

        // fields for searching for games

        public TextField searchGameName, searchGameMachine, searchGameDeveloper, searchGamePublisher;

        // fields for searching for game ports

        public TextField searchPortMachine, searchPortName, searchPortGame;

        // functions for managing game machines

        public void addGameMachine(ActionEvent addGameMachineEvent) {
            GameMachine newGameMachine = new GameMachine(machineName.getText(), manufacturer.getText(), description.getText(), type.getText(), media.getText(), developer.getText(), parseInt(initialLaunchYear.getText()), Float.parseFloat(initialRRP.getText()), imageUrl.getText());
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
            selectedGameMachine = gameMachines.accessIndex(indexSelected - 1);
            System.out.println(indexSelected - 1);
        }

        // functions for managing games

        public void addGame(ActionEvent addGameEvent) {
            Game newGame = new Game(gameName.getText(), gameDescription.getText(), gameGenre.getText(), gameDeveloper.getText(), gamePublisher.getText(), parseInt(gameReleaseYear.getText()), gameImageUrl.getText());
            games.addElement(newGame);
            gameView.add(newGame);
            gameListView.setItems(gameView);
        }

        public void removeGame(ActionEvent removeGameEvent) {
            if (games.getLength() > 0) {
                games.accessIndex(indexSelected - 1);
                for (int i=0; i<gamePorts.getLength(); i++) {
                    if (gamePorts.accessIndex(i).getOriginalGame().equals(selectedGame)) {
                        gamePorts.removeElement(i);
                    }
                }
                games.removeElement(indexSelected - 1);
                gameView.remove(indexSelected - 1);
                gameListView.setItems(gameView);
            }
        }

        public void selectGame(MouseEvent selectGameEvent) {
            indexSelected = gameListView.getSelectionModel().getSelectedIndex();
            System.out.println(indexSelected);
        }

        // functions for managing game ports

        public void addGamePort(ActionEvent addGamePortEvent) {
            GamePort newGamePort = new GamePort(selectedGame, portMachine.getText(), portName.getText(), portName.getText(), Integer.parseInt(portReleaseYear.getText()), portImageUrl.getText());
            gamePorts.addElement(newGamePort);
            gamePortView.add(newGamePort);
            gamePortListView.setItems(gamePortView);
        }

        public void removeGamePort(ActionEvent removeGamePortEvent) {
            if (gamePorts.getLength() > 0) {
                gamePorts.removeElement(indexSelected - 1);
                gamePortView.remove(indexSelected - 1);
                gamePortListView.setItems(gamePortView);
            }
        }

        public void setSelectedGamePort(MouseEvent selectGamePortEvent) {
            indexSelected = gamePortListView.getSelectionModel().getSelectedIndex();
            selectedGamePort = gamePorts.accessIndex(indexSelected - 1);
            System.out.println(indexSelected - 1);
        }

        // functions for searching

        public void searchGameMachineByName(ActionEvent searchGameMachineEvent) {
            ScratchList<GameMachine> searchResults = new ScratchList<>();
            for (GameMachine gameMachine : gameMachines) {
                if ((searchMachineName.getText() != null && gameMachine.getName().toLowerCase().contains(searchMachineName.getText().toLowerCase()))) {
                    searchResults.addElement(gameMachine);
                }
            }
            gameMachineView.clear();
            for (GameMachine gameMachine : searchResults) {
                gameMachineView.add(gameMachine);
            }
            gameMachineListView.setItems(gameMachineView);
        }

        // functions for sorting

        public void sortGameMachineByNameAsc(ActionEvent sortGameMachineEvent) {
            ScratchList<GameMachine> sortedGameMachines = new ScratchList<>();
            GameMachine lowestGameMachine = gameMachines.getHead();
            while (sortedGameMachines.getLength() != gameMachines.getLength()) {
                for (GameMachine gameMachine : gameMachines) {
                    if (gameMachine.getName().compareTo(lowestGameMachine.getName()) < 0) {
                        lowestGameMachine = gameMachine;
                    }
                }
            }
            gameMachineView.clear();
            for (GameMachine gameMachine : sortedGameMachines) {
                gameMachineView.add(gameMachine);
            }
            gameMachineListView.setItems(gameMachineView);
        }

        // serialization functions

    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[] {ScratchList.class, Game.class, GameMachine.class, GamePort.class};
        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        xstream.allowTypes(classes);
        //doing the actual serialisation to an XML file
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("GameSys.xml"));
        gameMachines = (ScratchList<GameMachine>) is.readObject();
        games = (ScratchList<Game>) is.readObject();
    is.close();
    }
    // The save method uses the XStream component to write the ports list to the ports.xml file
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("Ports.xml"));
        out.writeObject(gameMachines);
        out.writeObject(games);
        out.close();
    }
}
