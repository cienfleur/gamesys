package base.gamesys.models;

import base.gamesys.utils.ScratchList;

public class GamePort {
    private Game originalGame;
    private GameMachine newGamesMachine;
    private String gameName;
    private String portDeveloper;
    private int releaseYear;
    private String coverArtURL;

    // Constructor
    public GamePort(Game originalGame, GameMachine newGamesMachine, String portDeveloper,
                    int releaseYear, String coverArtURL) {
        this.originalGame = originalGame;
        this.newGamesMachine = newGamesMachine;
        this.gameName = originalGame.getName();
        this.portDeveloper = portDeveloper;
        setReleaseYear(releaseYear);
        this.coverArtURL = coverArtURL;
    }

    //Getters and setters
    public Game getOriginalGame() {
        return originalGame;
    }

    public void setOriginalGame(Game originalGame) {
        this.originalGame = originalGame;
    }

    public GameMachine getNewGamesMachine() {
        return newGamesMachine;
    }

    public void setNewGamesMachine(GameMachine newGamesMachine) {
        this.newGamesMachine = newGamesMachine;
    }

    public String getPortDeveloper() {
        return portDeveloper;
    }

    public void setPortDeveloper(String portDeveloper) {
        this.portDeveloper = portDeveloper;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = Math.max(releaseYear, originalGame.getYearOfRelease());
    }

    public String getCoverArtURL() {
        return coverArtURL;
    }

    public void setCoverArtURL(String coverArtURL) {
        this.coverArtURL = coverArtURL;
    }

}

