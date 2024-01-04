module base.gamesys {
    requires javafx.controls;
    requires javafx.fxml;
    requires xstream;
    exports base.gamesys.models;
    exports base.gamesys.utils;

    opens base.gamesys.models to xstream;
    opens base.gamesys.utils to xstream;


    opens base.gamesys to javafx.fxml;
    exports base.gamesys;
    exports base.gamesys.controllers;
    opens base.gamesys.controllers to javafx.fxml;
}