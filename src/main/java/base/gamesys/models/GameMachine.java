package base.gamesys.models;

//machine name, manufacturer, desc, type (console/computer), developer, media used (cartridge, disk, etc.), launch date, launch MSRP, image url
public class GameMachine {

    private String name;
    private String manufacturer;
    private String desc;
    private String type;
    private String developer;
    private String media;
    private int launchYear;
    private float MSRP;
    private String urlImage;

    // Constructor

    public GameMachine(String name, String manufacturer, String desc, String type, String developer, String media, int launchYear, float MSRP, String urlImage) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.desc = desc;
        this.type = type;
        this.developer = developer;
        this.media = media;
        this.launchYear = launchYear;
        this.MSRP = MSRP;
        this.urlImage = urlImage;
    }

    //Generate getters and setters for all fields

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public int getLaunchYear() {
        return launchYear;
    }

    public void setLaunchYear(int launchYear) {
        this.launchYear = launchYear;
    }

    public float getMSRP() {
        return MSRP;
    }

    public void setMSRP(float MSRP) {
        this.MSRP = MSRP;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
