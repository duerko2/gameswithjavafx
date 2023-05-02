module com.marcusjac.gameswithjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.marcusjac.gameswithjavafx to javafx.fxml;
    exports com.marcusjac.gameswithjavafx;
}