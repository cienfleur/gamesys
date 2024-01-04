package base.gamesys.models;

import static org.junit.jupiter.api.Assertions.*;

import org.testng.annotations.Test;

public class GameMachineTest {

    // Test case for the constructor and getters
    @Test
    public void testConstructorAndGetters() {
        GameMachine gameMachine = new GameMachine("ConsoleX", "ManufacturerX", "DescriptionX", "Console", "DeveloperX", "Cartridge", 2020, 299.99f, "imageX.jpg");

        assertEquals("ConsoleX", gameMachine.getName());
        assertEquals("ManufacturerX", gameMachine.getManufacturer());
        assertEquals("DescriptionX", gameMachine.getDesc());
        assertEquals("Console", gameMachine.getType());
        assertEquals("DeveloperX", gameMachine.getDeveloper());
        assertEquals("Cartridge", gameMachine.getMedia());
        assertEquals(2020, gameMachine.getLaunchYear());
        assertEquals(299.99f, gameMachine.getMSRP(), 0.001); // Float comparison needs delta
        assertEquals("imageX.jpg", gameMachine.getUrlImage());
    }

    // Test case for setters
    @Test
    public void testSetters() {
        GameMachine gameMachine = new GameMachine("ConsoleY", "ManufacturerY", "DescriptionY", "Console", "DeveloperY", "Disk", 2022, 399.99f, "imageY.jpg");

        gameMachine.setName("NewConsole");
        gameMachine.setManufacturer("NewManufacturer");
        gameMachine.setDesc("NewDescription");
        gameMachine.setType("Computer");
        gameMachine.setDeveloper("NewDeveloper");
        gameMachine.setMedia("Digital Download");
        gameMachine.setLaunchYear(2023);
        gameMachine.setMSRP(499.99f);
        gameMachine.setUrlImage("newImage.jpg");

        assertEquals("NewConsole", gameMachine.getName());
        assertEquals("NewManufacturer", gameMachine.getManufacturer());
        assertEquals("NewDescription", gameMachine.getDesc());
        assertEquals("Computer", gameMachine.getType());
        assertEquals("NewDeveloper", gameMachine.getDeveloper());
        assertEquals("Digital Download", gameMachine.getMedia());
        assertEquals(2023, gameMachine.getLaunchYear());
        assertEquals(499.99f, gameMachine.getMSRP(), 0.001);
        assertEquals("newImage.jpg", gameMachine.getUrlImage());
    }

    // Test case for toString() method
    @Test
    public void testToString() {
        GameMachine gameMachine = new GameMachine("ConsoleZ", "ManufacturerZ", "DescriptionZ", "Console", "DeveloperZ", "Cartridge", 2021, 349.99f, "imageZ.jpg");

        assertEquals("ConsoleZ (2021)", gameMachine.toString());
    }
}
