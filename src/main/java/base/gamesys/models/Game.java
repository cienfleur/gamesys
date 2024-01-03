package base.gamesys.models;

//

public class Game {

    private String name;
    private String publisher;
    private String description;
    private String developer;
    private String gameMachine;
    private int yearOfRelease;
    private String coverArtURL;

    // Constructor
    public Game(String name, String publisher, String description, String originalDeveloper,
                String originalGameMachine, int yearOfRelease, String coverArtURL) {
        this.name = name;
        this.publisher = publisher;
        this.description = description;
        this.developer = originalDeveloper;
        this.gameMachine = originalGameMachine;
        setYearOfRelease(yearOfRelease);
        this.coverArtURL = coverArtURL;
    }

    //Getters and setters

    public String getGameMachine() {
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
        this.yearOfRelease = yearOfRelease;
    }

    public String getCoverArtURL() {
        return coverArtURL;
    }

    public void setCoverArtURL(String coverArtURL) {
        this.coverArtURL = coverArtURL;
    }
}
