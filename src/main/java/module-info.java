module base.gamesys {
    requires javafx.controls;
    requires javafx.fxml;
    requires xstream;


    opens base.gamesys to javafx.fxml;
    exports base.gamesys;
}