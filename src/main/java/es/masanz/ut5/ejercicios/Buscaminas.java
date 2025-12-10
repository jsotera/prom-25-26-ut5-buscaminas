package es.masanz.ut5.ejercicios;

public class Buscaminas {

    private int[][] matriz;
    private int bombas;

    public Buscaminas(int filas, int columnas, int bombas){
        this.matriz = new int[filas][columnas];
        this.bombas = bombas;
        colocarBombas();
        colocarNumeros();
    }

    private void colocarBombas(){
        int contador = 0;
        while(contador!=bombas) {
            int filaRandom = (int) (Math.random() * matriz.length);
            int columnaRandom = (int) (Math.random() * matriz[0].length);
            if(!comprobarBomba(filaRandom, columnaRandom)){
                matriz[filaRandom][columnaRandom] = -1;
                contador++;
            }
        }
    }

    public boolean comprobarBomba(int fila, int columna){
        if(matriz[fila][columna] == -1){
            return true;
        }
        return false;
    }

    public void mostrarBuscaminas(){
        for (int fila = 0; fila < matriz.length; fila++) {
            for (int col = 0; col < matriz[0].length; col++) {
                System.out.printf("%3d",matriz[fila][col]);
            }
            System.out.println();
        }
    }

    private void colocarNumeros(){
        for (int fila = 0; fila < matriz.length; fila++) {
            for (int col = 0; col < matriz[0].length; col++) {
                int cantidad = contarBombas(fila, col);
                matriz[fila][col] = cantidad;
            }
        }
    }

    private int contarBombas(int fila, int col) {
        int cantidadBombas = 0;
        if(comprobarBomba(fila, col)){
            return -1;
        } else {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if(fila+i == fila && col+j == col){
                        continue;
                    } else if (
                                    fila+i < 0 ||
                                    fila+i >= matriz.length ||
                                    col+j < 0 ||
                                    col+j >= matriz[0].length
                    ) {
                        continue;
                    }
                    if(comprobarBomba(fila+i, col+j)){
                        cantidadBombas++;
                    }
                }
            }
        }
        return cantidadBombas;
    }

}
