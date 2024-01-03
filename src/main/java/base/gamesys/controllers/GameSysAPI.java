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
import java.util.Collection;

import static java.lang.Integer.parseInt;

public class GameSysAPI {

        // fields for general game system management

        public int indexSelected;

        // fields for managing game machines
        public ScratchList<GameMachine> gameMachines = new ScratchList<>();
        public ObservableList<GameMachine> gameMachineView = FXCollections.observableArrayList();
        public ListView<GameMachine> gameMachineListView = new ListView<GameMachine>();

        public GameMachine selectedGameMachine;

        public TextField machineName, manufacturer, description, type, developer, media, initialLaunchYear, initialRRP, imageUrl;
        // fields for managing games

        public ScratchList<Game> games = new ScratchList<>();
        public ObservableList<Game> gameView = FXCollections.observableArrayList();

        public ListView<Game> gameListView = new ListView<Game>();

        public Game selectedGame;

        public TextField gameName, gameDescription, gameGenre, gameDeveloper, gamePublisher, gameReleaseYear, gameImageUrl;

        // fields for managing game ports

        public ScratchList<GamePort> gamePorts = new ScratchList<>();
        public ObservableList<GamePort> gamePortView = FXCollections.observableArrayList();

        public ListView<GamePort> gamePortListView = new ListView<GamePort>();

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
            gameMachineListView.getItems().add(newGameMachine);
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

        public void searchGameMachineByType(ActionEvent searchGameMachineEvent) {
            ScratchList<GameMachine> searchResults = new ScratchList<>();
            for (GameMachine gameMachine : gameMachines) {
                if ((SearchType.getText() != null && gameMachine.getType().toLowerCase().contains(SearchType.getText().toLowerCase()))) {
                    searchResults.addElement(gameMachine);
                }
            }
            gameMachineView.clear();
            for (GameMachine gameMachine : searchResults) {
                gameMachineView.add(gameMachine);
            }
            gameMachineListView.setItems(gameMachineView);
        }

        public void searchGameMachineByYear(ActionEvent searchGameMachineEvent) {
            ScratchList<GameMachine> searchResults = new ScratchList<>();
            for (GameMachine gameMachine : gameMachines) {
                if ((initialLaunchYear.getText() != null && gameMachine.getLaunchYear() == parseInt(initialLaunchYear.getText()))) {
                    searchResults.addElement(gameMachine);
                }
            }
            gameMachineView.clear();
            for (GameMachine gameMachine : searchResults) {
                gameMachineView.add(gameMachine);
            }
            gameMachineListView.setItems(gameMachineView);
        }

        public void searchGameMachineByManufacturer(ActionEvent searchGameMachineEvent) {
            ScratchList<GameMachine> searchResults = new ScratchList<>();
            for (GameMachine gameMachine : gameMachines) {
                if ((searchManufacturer.getText() != null && gameMachine.getManufacturer().toLowerCase().contains(searchManufacturer.getText().toLowerCase()))) {
                    searchResults.addElement(gameMachine);
                }
            }
            gameMachineView.clear();
            for (GameMachine gameMachine : searchResults) {
                gameMachineView.add(gameMachine);
            }
            gameMachineListView.setItems(gameMachineView);
        }

    public void searchGameMachineByMedia(ActionEvent searchGameMachineEvent) {
        ScratchList<GameMachine> searchResults = new ScratchList<>();
        for (GameMachine gameMachine : gameMachines) {
            if ((media.getText() != null && gameMachine.getMedia().toLowerCase().contains(media.getText().toLowerCase()))) {
                searchResults.addElement(gameMachine);
            }
        }
        gameMachineView.clear();
        for (GameMachine gameMachine : searchResults) {
            gameMachineView.add(gameMachine);
        }
        gameMachineListView.setItems(gameMachineView);
    }

    public void searchGameMachineByDescription(ActionEvent searchGameMachineEvent) {
        ScratchList<GameMachine> searchResults = new ScratchList<>();
        for (GameMachine gameMachine : gameMachines) {
            if ((description.getText() != null && gameMachine.getDesc().toLowerCase().contains(description.getText().toLowerCase()))) {
                searchResults.addElement(gameMachine);
            }
        }
        gameMachineView.clear();
        for (GameMachine gameMachine : searchResults) {
            gameMachineView.add(gameMachine);
        }
        gameMachineListView.setItems(gameMachineView);
    }

    public void searchGameMachineByDeveloper(ActionEvent searchGameMachineEvent) {
            ScratchList<GameMachine> searchResults = new ScratchList<>();
            for (GameMachine gameMachine : gameMachines) {
                if ((developer.getText() != null && gameMachine.getDeveloper().toLowerCase().contains(developer.getText().toLowerCase()))) {
                    searchResults.addElement(gameMachine);
                }
            }
            gameMachineView.clear();
            for (GameMachine gameMachine : searchResults) {
                gameMachineView.add(gameMachine);
            }
            gameMachineListView.setItems(gameMachineView);
    }

    public void searchGameByName(ActionEvent searchGameEvent) {
        ScratchList<Game> searchResults = new ScratchList<>();
        for (Game game : games) {
            if ((searchGameName.getText() != null && game.getName().toLowerCase().contains(searchGameName.getText().toLowerCase()))) {
                searchResults.addElement(game);
            }
        }
        gameView.clear();
        for (Game game : searchResults) {
            gameView.add(game);
        }
        gameListView.setItems(gameView);
    }

    public void searchGameByMachine(ActionEvent searchGameEvent) {
        ScratchList<Game> searchResults = new ScratchList<>();
        for (Game game : games) {
            if ((searchGameMachine.getText() != null && game.getGameMachine().toLowerCase().contains(searchGameMachine.getText().toLowerCase()))) {
                searchResults.addElement(game);
            }
        }
        gameView.clear();
        for (Game game : searchResults) {
            gameView.add(game);
        }
        gameListView.setItems(gameView);
    }

    public void searchGameByDeveloper(ActionEvent searchGameEvent) {
        ScratchList<Game> searchResults = new ScratchList<>();
        for (Game game : games) {
            if ((searchGameDeveloper.getText() != null && game.getDeveloper().toLowerCase().contains(searchGameDeveloper.getText().toLowerCase()))) {
                searchResults.addElement(game);
            }
        }
        gameView.clear();
        for (Game game : searchResults) {
            gameView.add(game);
        }
        gameListView.setItems(gameView);
    }

    public void searchGameByPublisher(ActionEvent searchGameEvent) {
        ScratchList<Game> searchResults = new ScratchList<>();
        for (Game game : games) {
            if ((searchGamePublisher.getText() != null && game.getPublisher().toLowerCase().contains(searchGamePublisher.getText().toLowerCase()))) {
                searchResults.addElement(game);
            }
        }
        gameView.clear();
        for (Game game : searchResults) {
            gameView.add(game);
        }
        gameListView.setItems(gameView);
    }

    public void searchGameByYear(ActionEvent searchGameEvent) {
        ScratchList<Game> searchResults = new ScratchList<>();
        for (Game game : games) {
            if ((gameReleaseYear.getText() != null && game.getYearOfRelease() == parseInt(gameReleaseYear.getText()))) {
                searchResults.addElement(game);
            }
        }
        gameView.clear();
        for (Game game : searchResults) {
            gameView.add(game);
        }
        gameListView.setItems(gameView);
    }

    public void searchGameByDescription(ActionEvent searchGameEvent) {
        ScratchList<Game> searchResults = new ScratchList<>();
        for (Game game : games) {
            if ((gameDescription.getText() != null && game.getDescription().toLowerCase().contains(gameDescription.getText().toLowerCase()))) {
                searchResults.addElement(game);
            }
        }
        gameView.clear();
        for (Game game : searchResults) {
            gameView.add(game);
        }
        gameListView.setItems(gameView);
    }

    public void searchPortByGame(ActionEvent searchGameEvent) {
        ScratchList<GamePort> searchResults = new ScratchList<>();
        for (GamePort gamePort : gamePorts) {
            if ((searchPortGame.getText() != null && gamePort.getOriginalGame().getName().toLowerCase().contains(searchPortGame.getText().toLowerCase()))) {
                searchResults.addElement(gamePort);
            }
        }
        gamePortView.clear();
        for (GamePort gamePort : searchResults) {
            gamePortView.add(gamePort);
        }
        gamePortListView.setItems(gamePortView);
    }

    public void searchPortByMachine(ActionEvent searchGameEvent) {
        ScratchList<GamePort> searchResults = new ScratchList<>();
        for (GamePort gamePort : gamePorts) {
            if ((searchPortMachine.getText() != null && gamePort.getNewGamesMachine().toLowerCase().contains(searchPortMachine.getText().toLowerCase()))) {
                searchResults.addElement(gamePort);
            }
        }
        gamePortView.clear();
        for (GamePort gamePort : searchResults) {
            gamePortView.add(gamePort);
        }
        gamePortListView.setItems(gamePortView);
    }

    public void searchPortByDeveloper(ActionEvent searchGameEvent) {
        ScratchList<GamePort> searchResults = new ScratchList<>();
        for (GamePort gamePort : gamePorts) {
            if ((searchPortName.getText() != null && gamePort.getPortDeveloper().toLowerCase().contains(searchPortName.getText().toLowerCase()))) {
                searchResults.addElement(gamePort);
            }
        }
        gamePortView.clear();
        for (GamePort gamePort : searchResults) {
            gamePortView.add(gamePort);
        }
        gamePortListView.setItems(gamePortView);
    }

    public void searchPortByYear(ActionEvent searchGameEvent) {
        ScratchList<GamePort> searchResults = new ScratchList<>();
        for (GamePort gamePort : gamePorts) {
            if ((portReleaseYear.getText() != null && gamePort.getReleaseYear() == parseInt(portReleaseYear.getText()))) {
                searchResults.addElement(gamePort);
            }
        }
        gamePortView.clear();
        for (GamePort gamePort : searchResults) {
            gamePortView.add(gamePort);
        }
        gamePortListView.setItems(gamePortView);
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

        public void sortGameMachineByNameDesc(ActionEvent sortGameMachineEvent) {
            ScratchList<GameMachine> sortedGameMachines = new ScratchList<>();
            GameMachine highestGameMachine = gameMachines.getHead();
            while (sortedGameMachines.getLength() != gameMachines.getLength()) {
                for (GameMachine gameMachine : gameMachines) {
                    if (gameMachine.getName().compareTo(highestGameMachine.getName()) > 0) {
                        highestGameMachine = gameMachine;
                    }
                }
            }
            gameMachineView.clear();
            for (GameMachine gameMachine : sortedGameMachines) {
                gameMachineView.add(gameMachine);
            }
            gameMachineListView.setItems(gameMachineView);
        }

        public void sortGameMachineByTypeAsc(ActionEvent sortGameMachineEvent) {
            ScratchList<GameMachine> sortedGameMachines = new ScratchList<>();
            GameMachine lowestGameMachine = gameMachines.getHead();
            while (sortedGameMachines.getLength() != gameMachines.getLength()) {
                for (GameMachine gameMachine : gameMachines) {
                    if (gameMachine.getType().compareTo(lowestGameMachine.getType()) < 0) {
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

        public void sortGameMachineByTypeDesc(ActionEvent sortGameMachineEvent) {
            ScratchList<GameMachine> sortedGameMachines = new ScratchList<>();
            GameMachine highestGameMachine = gameMachines.getHead();
            while (sortedGameMachines.getLength() != gameMachines.getLength()) {
                for (GameMachine gameMachine : gameMachines) {
                    if (gameMachine.getType().compareTo(highestGameMachine.getType()) > 0) {
                        highestGameMachine = gameMachine;
                    }
                }
            }
            gameMachineView.clear();
            for (GameMachine gameMachine : sortedGameMachines) {
                gameMachineView.add(gameMachine);
            }
            gameMachineListView.setItems(gameMachineView);
        }

        public void sortGameMachineByYearAsc(ActionEvent sortGameMachineEvent) {
            ScratchList<GameMachine> sortedGameMachines = new ScratchList<>();
            GameMachine lowestGameMachine = gameMachines.getHead();
            while (sortedGameMachines.getLength() != gameMachines.getLength()) {
                for (GameMachine gameMachine : gameMachines) {
                    if (gameMachine.getLaunchYear() < lowestGameMachine.getLaunchYear()) {
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

        public void sortGameMachineByYearDesc(ActionEvent sortGameMachineEvent) {
            ScratchList<GameMachine> sortedGameMachines = new ScratchList<>();
            GameMachine highestGameMachine = gameMachines.getHead();
            while (sortedGameMachines.getLength() != gameMachines.getLength()) {
                for (GameMachine gameMachine : gameMachines) {
                    if (gameMachine.getLaunchYear() > highestGameMachine.getLaunchYear()) {
                        highestGameMachine = gameMachine;
                    }
                }
            }
            gameMachineView.clear();
            for (GameMachine gameMachine : sortedGameMachines) {
                gameMachineView.add(gameMachine);
            }
            gameMachineListView.setItems(gameMachineView);
        }


        public void sortGameMachineByManufacturerAsc(ActionEvent sortGameMachineEvent) {
            ScratchList<GameMachine> sortedGameMachines = new ScratchList<>();
            GameMachine lowestGameMachine = gameMachines.getHead();
            while (sortedGameMachines.getLength() != gameMachines.getLength()) {
                for (GameMachine gameMachine : gameMachines) {
                    if (gameMachine.getManufacturer().compareTo(lowestGameMachine.getManufacturer()) < 0) {
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

        public void sortGameMachineByManufacturerDesc(ActionEvent sortGameMachineEvent) {
            ScratchList<GameMachine> sortedGameMachines = new ScratchList<>();
            GameMachine highestGameMachine = gameMachines.getHead();
            while (sortedGameMachines.getLength() != gameMachines.getLength()) {
                for (GameMachine gameMachine : gameMachines) {
                    if (gameMachine.getManufacturer().compareTo(highestGameMachine.getManufacturer()) > 0) {
                        highestGameMachine = gameMachine;
                    }
                }
            }
            gameMachineView.clear();
            for (GameMachine gameMachine : sortedGameMachines) {
                gameMachineView.add(gameMachine);
            }
            gameMachineListView.setItems(gameMachineView);
        }

        public void sortGameMachineByPriceAsc(ActionEvent sortGameMachineEvent) {
            ScratchList<GameMachine> sortedGameMachines = new ScratchList<>();
            GameMachine lowestGameMachine = gameMachines.getHead();
            while (sortedGameMachines.getLength() != gameMachines.getLength()) {
                for (GameMachine gameMachine : gameMachines) {
                    if (gameMachine.getMSRP() < lowestGameMachine.getMSRP()) {
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

        public void sortGameMachineByPriceDesc(ActionEvent sortGameMachineEvent) {
            ScratchList<GameMachine> sortedGameMachines = new ScratchList<>();
            GameMachine highestGameMachine = gameMachines.getHead();
            while (sortedGameMachines.getLength() != gameMachines.getLength()) {
                for (GameMachine gameMachine : gameMachines) {
                    if (gameMachine.getMSRP() > highestGameMachine.getMSRP()) {
                        highestGameMachine = gameMachine;
                    }
                }
            }
            gameMachineView.clear();
            for (GameMachine gameMachine : sortedGameMachines) {
                gameMachineView.add(gameMachine);
            }
            gameMachineListView.setItems(gameMachineView);
        }

        public void sortGameByNameAsc(ActionEvent sortGameEvent) {
            ScratchList<Game> sortedGames = new ScratchList<>();
            Game lowestGame = games.getHead();
            while (sortedGames.getLength() != games.getLength()) {
                for (Game game : games) {
                    if (game.getName().compareTo(lowestGame.getName()) < 0) {
                        lowestGame = game;
                    }
                }
            }
            gameView.clear();
            for (Game game : sortedGames) {
                gameView.add(game);
            }
            gameListView.setItems(gameView);
        }

        public void sortGameByNameDesc(ActionEvent sortGameEvent) {
            ScratchList<Game> sortedGames = new ScratchList<>();
            Game highestGame = games.getHead();
            while (sortedGames.getLength() != games.getLength()) {
                for (Game game : games) {
                    if (game.getName().compareTo(highestGame.getName()) > 0) {
                        highestGame = game;
                    }
                }
            }
            gameView.clear();
            for (Game game : sortedGames) {
                gameView.add(game);
            }
            gameListView.setItems(gameView);
        }

        public void sortGameByPublisherAsc(ActionEvent sortGameEvent) {
            ScratchList<Game> sortedGames = new ScratchList<>();
            Game lowestGame = games.getHead();
            while (sortedGames.getLength() != games.getLength()) {
                for (Game game : games) {
                    if (game.getPublisher().compareTo(lowestGame.getPublisher()) < 0) {
                        lowestGame = game;
                    }
                }
            }
            gameView.clear();
            for (Game game : sortedGames) {
                gameView.add(game);
            }
            gameListView.setItems(gameView);
        }

        public void sortGameByPublisherDesc(ActionEvent sortGameEvent) {
            ScratchList<Game> sortedGames = new ScratchList<>();
            Game highestGame = games.getHead();
            while (sortedGames.getLength() != games.getLength()) {
                for (Game game : games) {
                    if (game.getPublisher().compareTo(highestGame.getPublisher()) > 0) {
                        highestGame = game;
                    }
                }
            }
            gameView.clear();
            for (Game game : sortedGames) {
                gameView.add(game);
            }
            gameListView.setItems(gameView);
        }

        public void sortGameByDeveloperAsc(ActionEvent sortGameEvent) {
            ScratchList<Game> sortedGames = new ScratchList<>();
            Game lowestGame = games.getHead();
            while (sortedGames.getLength() != games.getLength()) {
                for (Game game : games) {
                    if (game.getDeveloper().compareTo(lowestGame.getDeveloper()) < 0) {
                        lowestGame = game;
                    }
                }
            }
            gameView.clear();
            for (Game game : sortedGames) {
                gameView.add(game);
            }
            gameListView.setItems(gameView);
        }

        public void sortGameByDeveloperDesc(ActionEvent sortGameEvent) {
            ScratchList<Game> sortedGames = new ScratchList<>();
            Game highestGame = games.getHead();
            while (sortedGames.getLength() != games.getLength()) {
                for (Game game : games) {
                    if (game.getDeveloper().compareTo(highestGame.getDeveloper()) > 0) {
                        highestGame = game;
                    }
                }
            }
            gameView.clear();
            for (Game game : sortedGames) {
                gameView.add(game);
            }
            gameListView.setItems(gameView);
        }

        public void sortGameByMachineAsc(ActionEvent sortGameEvent) {
            ScratchList<Game> sortedGames = new ScratchList<>();
            Game lowestGame = games.getHead();
            while (sortedGames.getLength() != games.getLength()) {
                for (Game game : games) {
                    if (game.getGameMachine().compareTo(lowestGame.getGameMachine()) < 0) {
                        lowestGame = game;
                    }
                }
            }
            gameView.clear();
            for (Game game : sortedGames) {
                gameView.add(game);
            }
            gameListView.setItems(gameView);
        }

        public void sortGameByMachineDesc(ActionEvent sortGameEvent) {
            ScratchList<Game> sortedGames = new ScratchList<>();
            Game highestGame = games.getHead();
            while (sortedGames.getLength() != games.getLength()) {
                for (Game game : games) {
                    if (game.getGameMachine().compareTo(highestGame.getGameMachine()) > 0) {
                        highestGame = game;
                    }
                }
            }
            gameView.clear();
            for (Game game : sortedGames) {
                gameView.add(game);
            }
            gameListView.setItems(gameView);
        }

        public void sortGameByYearAsc(ActionEvent sortGameEvent) {
            ScratchList<Game> sortedGames = new ScratchList<>();
            Game lowestGame = games.getHead();
            while (sortedGames.getLength() != games.getLength()) {
                for (Game game : games) {
                    if (game.getYearOfRelease() < lowestGame.getYearOfRelease()) {
                        lowestGame = game;
                    }
                }
            }
            gameView.clear();
            for (Game game : sortedGames) {
                gameView.add(game);
            }
            gameListView.setItems(gameView);
        }

        public void sortGameByYearDesc(ActionEvent sortGameEvent) {
            ScratchList<Game> sortedGames = new ScratchList<>();
            Game highestGame = games.getHead();
            while (sortedGames.getLength() != games.getLength()) {
                for (Game game : games) {
                    if (game.getYearOfRelease() > highestGame.getYearOfRelease()) {
                        highestGame = game;
                    }
                }
            }
            gameView.clear();
            for (Game game : sortedGames) {
                gameView.add(game);
            }
            gameListView.setItems(gameView);
        }

        public void sortPortByOriginalGameAsc(ActionEvent sortGameEvent) {
            ScratchList<GamePort> sortedGamePorts = new ScratchList<>();
            GamePort lowestGamePort = gamePorts.getHead();
            while (sortedGamePorts.getLength() != gamePorts.getLength()) {
                for (GamePort gamePort : gamePorts) {
                    if (gamePort.getOriginalGame().getName().compareTo(lowestGamePort.getOriginalGame().getName()) < 0) {
                        lowestGamePort = gamePort;
                    }
                }
            }
            gamePortView.clear();
            for (GamePort gamePort : sortedGamePorts) {
                gamePortView.add(gamePort);
            }
            gamePortListView.setItems(gamePortView);
        }

        public void sortPortByOriginalGameDesc(ActionEvent sortGameEvent) {
            ScratchList<GamePort> sortedGamePorts = new ScratchList<>();
            GamePort highestGamePort = gamePorts.getHead();
            while (sortedGamePorts.getLength() != gamePorts.getLength()) {
                for (GamePort gamePort : gamePorts) {
                    if (gamePort.getOriginalGame().getName().compareTo(highestGamePort.getOriginalGame().getName()) > 0) {
                        highestGamePort = gamePort;
                    }
                }
            }
            gamePortView.clear();
            for (GamePort gamePort : sortedGamePorts) {
                gamePortView.add(gamePort);
            }
            gamePortListView.setItems(gamePortView);
        }

        public void sortPortByNewMachineAsc(ActionEvent sortGameEvent) {
            ScratchList<GamePort> sortedGamePorts = new ScratchList<>();
            GamePort lowestGamePort = gamePorts.getHead();
            while (sortedGamePorts.getLength() != gamePorts.getLength()) {
                for (GamePort gamePort : gamePorts) {
                    if (gamePort.getNewGamesMachine().compareTo(lowestGamePort.getNewGamesMachine()) < 0) {
                        lowestGamePort = gamePort;
                    }
                }
            }
            gamePortView.clear();
            for (GamePort gamePort : sortedGamePorts) {
                gamePortView.add(gamePort);
            }
            gamePortListView.setItems(gamePortView);
        }

        public void sortPortByNewMachineDesc(ActionEvent sortGameEvent) {
            ScratchList<GamePort> sortedGamePorts = new ScratchList<>();
            GamePort highestGamePort = gamePorts.getHead();
            while (sortedGamePorts.getLength() != gamePorts.getLength()) {
                for (GamePort gamePort : gamePorts) {
                    if (gamePort.getNewGamesMachine().compareTo(highestGamePort.getNewGamesMachine()) > 0) {
                        highestGamePort = gamePort;
                    }
                }
            }
            gamePortView.clear();
            for (GamePort gamePort : sortedGamePorts) {
                gamePortView.add(gamePort);
            }
            gamePortListView.setItems(gamePortView);
        }

        public void sortPortByYearAsc(ActionEvent sortGameEvent) {
            ScratchList<GamePort> sortedGamePorts = new ScratchList<>();
            GamePort lowestGamePort = gamePorts.getHead();
            while (sortedGamePorts.getLength() != gamePorts.getLength()) {
                for (GamePort gamePort : gamePorts) {
                    if (gamePort.getReleaseYear() < lowestGamePort.getReleaseYear()) {
                        lowestGamePort = gamePort;
                    }
                }
            }
            gamePortView.clear();
            for (GamePort gamePort : sortedGamePorts) {
                gamePortView.add(gamePort);
            }
            gamePortListView.setItems(gamePortView);
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
