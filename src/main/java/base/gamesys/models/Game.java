package base.gamesys.models;

//

import base.gamesys.utils.ScratchList;

public class Game {

    private String name;
    private String publisher;
    private String description;
    private String developer;
    private GameMachine gameMachine;
    private int yearOfRelease;
    private String coverArtURL;

    private ScratchList<GamePort> gamePorts;

    // Constructor
    public Game(String name, String publisher, String description, String originalDeveloper,
                GameMachine originalGameMachine, int yearOfRelease, String coverArtURL) {
        this.name = name;
        this.publisher = publisher;
        this.description = description;
        this.developer = originalDeveloper;
        this.gameMachine = originalGameMachine;
        setYearOfRelease(yearOfRelease);
        this.coverArtURL = coverArtURL;
        this.gamePorts = new ScratchList<>();
    }

    //Getters and setters

    public GameMachine getGameMachine() {
        return gameMachine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        if (yearOfRelease > gameMachine.getLaunchYear()) {
            this.yearOfRelease = yearOfRelease;
        }
        else {
            this.yearOfRelease = gameMachine.getLaunchYear();
        }
    }

    public String getCoverArtURL() {
        return coverArtURL;
    }

    public void setCoverArtURL(String coverArtURL) {
        this.coverArtURL = coverArtURL;
    }

    public ScratchList<GamePort> getGamePorts() {
        return gamePorts;
    }

    public void setGamePorts(ScratchList<GamePort> gamePorts) {
        this.gamePorts = gamePorts;
    }

    public void addGamePort(GamePort gamePort) {
        gamePorts.addElement(gamePort);
    }
}
