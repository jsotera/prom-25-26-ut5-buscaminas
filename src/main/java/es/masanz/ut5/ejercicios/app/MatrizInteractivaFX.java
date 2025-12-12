package es.masanz.ut5.ejercicios.app;

import es.masanz.ut5.ejercicios.Buscaminas;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MatrizInteractivaFX extends Application {

    // TODO: Tener una clase con constantes igual seria mejor?
    private static final int TAMANO_CELDA = 60;
    private GridPane gridPane;
    private Buscaminas buscaminas;

    @Override
    public void start(Stage primaryStage) {
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        StackPane root = new StackPane(gridPane);

        // TODO: aqui podeis crear el objeto que querais que emplee matrices
        buscaminas = new Buscaminas(10, 10, 10);
        // TODO: este metodo hace todo
        pintarMatriz();
        // TODO: podeis redimensionar en funcion de vuestra matriz
        Scene scene = new Scene(root, buscaminas.getMatriz()[0].length*TAMANO_CELDA, buscaminas.getMatriz().length*TAMANO_CELDA);
        primaryStage.setTitle("Matriz Interactiva JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void pintarMatriz() {
        int[][] matriz = buscaminas.getMatriz();
        boolean[][] casillasSeleccionadas = buscaminas.getCasillasSeleccionadas();
        // TODO: podeis hacer los cambios que querais, pero con cabeza, ya que esto pinta bien
        gridPane.getChildren().clear();

        int filas = matriz.length;
        int cols = matriz[0].length;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < cols; j++) {

                StackPane celda = null;

                if(casillasSeleccionadas[i][j]){
                    int valor = matriz[i][j];

                    Rectangle fondo = new Rectangle(TAMANO_CELDA, TAMANO_CELDA);
                    fondo.setFill(obtenerColorPorNumero(valor));

                    Label texto = new Label(String.valueOf(valor));
                    texto.setTextFill(Color.WHITE);
                    texto.setFont(Font.font("Arial", FontWeight.BOLD, 20));

                    celda = new StackPane(fondo, texto);

                    final int filaActual = i;
                    final int colActual = j;

                    celda.setOnMouseEntered(event -> {
                        fondo.setOpacity(0.7);
                    });

                    celda.setOnMouseExited(event -> {
                        fondo.setOpacity(1);
                    });

                } else {
                    int valor = matriz[i][j];

                    Rectangle fondo = new Rectangle(TAMANO_CELDA, TAMANO_CELDA);
                    fondo.setFill(Color.CORAL);

                    celda = new StackPane(fondo);

                    final int filaActual = i;
                    final int colActual = j;

                    celda.setOnMouseEntered(event -> {
                        fondo.setOpacity(0.7);
                    });

                    celda.setOnMouseExited(event -> {
                        fondo.setOpacity(1);
                    });


                    celda.setOnMouseClicked(event -> {
                        MouseButton botonPulsado = event.getButton();
                        System.out.println(botonPulsado.name());
                        if(botonPulsado.name().equals("SECONDARY")){
                            // bloquear posicion
                        } else {
                            // TODO: Hacer lo que se quiera cuando se haga clic sobre una celda
                            System.out.println("Has hecho clic en -> Fila: " + filaActual + ", Columna: " + colActual + " | Valor: " + valor);
                            boolean seguir = buscaminas.seleccionarCasilla(filaActual, colActual);
                            // TODO: recordar volver a llamar a pintar matriz para ver los cambios
                            if(seguir){
                                pintarMatriz();
                            } else {

                                System.out.println("HAS PERDIDO");
                                System.exit(1);
                            }
                        }
                    });
                }


                gridPane.add(celda, j, i);
            }
        }
    }

    // TODO: ESTO PIDE A GRITOS UN ENUUUUM
    private Color obtenerColorPorNumero(int numero) {
        switch (numero) {
            case -1: return Color.RED;
            case 0: return Color.BLACK;
            case 1: return Color.GREEN;
            case 2: return Color.ROYALBLUE;
            case 3: return Color.LIMEGREEN;
            case 4: return Color.BROWN;
            case 5: return Color.PURPLE;
            case 6: return Color.WHITE;
            case 7: return Color.PINK;
            case 8: return Color.ORANGE;
        }
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
