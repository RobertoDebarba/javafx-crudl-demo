module br.com.cedup.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens br.com.cedup.javafx to javafx.fxml;
    opens br.com.cedup.javafx.controller to javafx.fxml;
    opens br.com.cedup.javafx.model.produto to javafx.base;
    opens br.com.cedup.javafx.model.usuario to javafx.base;
    exports br.com.cedup.javafx;
}