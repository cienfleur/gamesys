package base.gamesys.models;

import base.gamesys.utils.ScratchList;

public class GamePort {
    private Game originalGame;
    private String newGamesMachine;
    private String gameName;
    private String portDeveloper;
    private int releaseYear;
    private String coverArtURL;

    // Constructor
    public GamePort(Game originalGame, String newGamesMachine, String gameName, String portDeveloper,
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

    public String getNewGamesMachine() {
        return newGamesMachine;
    }

    public void setNewGamesMachine(String newGamesMachine) {
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

    public void editGamePort(ScratchList<GamePort> gamePortList, GamePort updatedGamePort) {
        // Remove the existing game port from the list
        gamePortList.removeElement(this.getReleaseYear());

        // Add the updated game port to the list
        gamePortList.addElement(updatedGamePort);
    }

    // Delete game port
    public void deleteGamePort(ScratchList<GamePort> gamePortList) {
        // Remove the game port from the list
        gamePortList.removeElement(this.getReleaseYear());
    }
}

