package base.gamesys.models;

import base.gamesys.utils.ScratchList;

public class GamePort {
    private Game originalGame;
    private GameMachine newGamesMachine;
    private String portDeveloper;
    private int releaseYear;
    private String coverArtURL;

    // Constructor
    public GamePort(Game originalGame, GameMachine newGamesMachine, String portDeveloper,
                    int releaseYear, String coverArtURL) {
        this.originalGame = originalGame;
        this.newGamesMachine = originalGame.getGameMachine();
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

    //Search and filter
    public static ScratchList<GamePort> searchGamePorts(ScratchList<GamePort> gamePorts, String partialName,
                                                        String portDeveloper, String originalGameName,
                                                        int releaseYear, String coverArtURL) {
        ScratchList<GamePort> result = new ScratchList<>();

        for (GamePort gamePort : gamePorts) {
            if ((partialName == null || gamePort.getOriginalGame().getName().toLowerCase().contains(partialName.toLowerCase())) &&
                    (portDeveloper == null || gamePort.getPortDeveloper().toLowerCase().contains(portDeveloper.toLowerCase())) &&
                    (originalGameName == null || gamePort.getOriginalGame().getName().toLowerCase().contains(originalGameName.toLowerCase())) &&
                    (releaseYear < 0 || gamePort.getReleaseYear() == releaseYear) &&
                    (coverArtURL == null || gamePort.getCoverArtURL().toLowerCase().contains(coverArtURL.toLowerCase()))) {
                result.addElement(gamePort);
            }
        }
        return result;
    }
}

