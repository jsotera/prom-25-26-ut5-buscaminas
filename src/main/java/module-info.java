module es.masanz.ut5.ejercicios {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;
    requires jdk.xml.dom;

    // Exportamos los paquetes que deben ser accesibles desde otros módulos
    exports es.masanz.ut5.ejercicios.app;
}