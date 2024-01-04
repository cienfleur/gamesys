package base.gamesys.models;

import static org.junit.jupiter.api.Assertions.*;

import org.testng.annotations.Test;

public class GamePortTest {

    // Test case for the constructor and getters
    @Test
    public void testConstructorAndGetters() {
        GameMachine originalGameMachine = new GameMachine("ConsoleX", "ManufacturerX", "DescriptionX", "Console", "DeveloperX", "Cartridge", 2020, 299.99f, "imageX.jpg");
        Game originalGame = new Game("GameX", "PublisherX", "DescriptionX", "DeveloperX", originalGameMachine, 2021, "coverX.jpg");
        GameMachine newGameMachine = new GameMachine("ConsoleY", "ManufacturerY", "DescriptionY", "Console", "DeveloperY", "Disk", 2022, 399.99f, "imageY.jpg");

        GamePort gamePort = new GamePort(originalGame, newGameMachine, "PortDeveloperX", 2023, "portCoverX.jpg");

        assertEquals(originalGame, gamePort.getOriginalGame());
        assertEquals(newGameMachine, gamePort.getNewGamesMachine());
        assertEquals("GameX", gamePort.getOriginalGame().getName());
        assertEquals("PortDeveloperX", gamePort.getPortDeveloper());
        assertEquals(2023, gamePort.getReleaseYear());
        assertEquals("portCoverX.jpg", gamePort.getCoverArtURL());
    }

    // Test case for setters
    @Test
    public void testSetters() {
        GameMachine originalGameMachine = new GameMachine("ConsoleZ", "ManufacturerZ", "DescriptionZ", "Console", "DeveloperZ", "Cartridge", 2021, 349.99f, "imageZ.jpg");
        Game originalGame = new Game("GameZ", "PublisherZ", "DescriptionZ", "DeveloperZ", originalGameMachine, 2022, "coverZ.jpg");
        GameMachine newGameMachine = new GameMachine("ConsoleW", "ManufacturerW", "DescriptionW", "Console", "DeveloperW", "Disk", 2023, 449.99f, "imageW.jpg");

        GamePort gamePort = new GamePort(originalGame, newGameMachine, "PortDeveloperZ", 2024, "portCoverZ.jpg");

        gamePort.setOriginalGame(originalGame); // Updating originalGame
        gamePort.setNewGamesMachine(newGameMachine); // Updating newGameMachine
        gamePort.setPortDeveloper("NewPortDeveloper");
        gamePort.setReleaseYear(2025);
        gamePort.setCoverArtURL("newPortCover.jpg");

        assertEquals(originalGame, gamePort.getOriginalGame()); // Check if originalGame is correctly updated
        assertEquals(newGameMachine, gamePort.getNewGamesMachine()); // Check if newGameMachine is correctly updated
        assertEquals("NewPortDeveloper", gamePort.getPortDeveloper());
        assertEquals(2025, gamePort.getReleaseYear());
        assertEquals("newPortCover.jpg", gamePort.getCoverArtURL());
    }

    // Test case for setReleaseYear considering originalGame's release year
    @Test
    public void testSetReleaseYearWithOriginalGameReleaseYear() {
        GameMachine originalGameMachine = new GameMachine("ConsoleY", "ManufacturerY", "DescriptionY", "Console", "DeveloperY", "Disk", 2022, 399.99f, "imageY.jpg");
        Game originalGame = new Game("GameY", "PublisherY", "DescriptionY", "DeveloperY", originalGameMachine, 2023, "coverY.jpg");
        GameMachine newGameMachine = new GameMachine("ConsoleX", "ManufacturerX", "DescriptionX", "Console", "DeveloperX", "Cartridge", 2024, 499.99f, "imageX.jpg");

        // GamePort's release year should be at least the same as the originalGame's release year
        GamePort gamePort = new GamePort(originalGame, newGameMachine, "PortDeveloperY", 2021, "portCoverY.jpg");
        assertEquals(originalGame.getYearOfRelease(), gamePort.getReleaseYear());
    }
}
