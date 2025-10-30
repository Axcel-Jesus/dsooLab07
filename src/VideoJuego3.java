import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class VideoJuego3{
    public static void main(String[] args) throws Exception {
        ArrayList<ArrayList<soldado>> tablero = new ArrayList<ArrayList<soldado>>();
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int equipo1 = rand.nextInt(10)+1;
        int equipo2 = rand.nextInt(10)+1;
        for(int i = 0; i < 10; i++){
            ArrayList<soldado> lista = new ArrayList<soldado>();
            for(int j = 0; j < 10; j++){
                switch (rand.nextInt(4)) {
                    case 0 :
                        lista.add(new espadachin("espadachin"+(i+1) + "X" + (j+1),rand.nextInt(5)+1, rand.nextInt(5)+1,rand.nextInt(5)+1,0));
                        break;
                    case 1 :
                        lista.add(new caballero("caballero"+(i+1) + "X" + (j+1),rand.nextInt(5)+1, rand.nextInt(5)+1,rand.nextInt(5)+1,0));
                        break;
                    case 2 :
                        lista.add(new arquero("arquero"+(i+1) + "X" + (j+1),rand.nextInt(5)+1, rand.nextInt(5)+1,rand.nextInt(5)+1,0));
                        break;
                    case 3 :
                        lista.add(new lancero("lancero"+(i+1) + "X" + (j+1),rand.nextInt(5)+1, rand.nextInt(5)+1,rand.nextInt(5)+1,0));
                        break;
                }
            }
            tablero.add(lista);
        }for(int i=0; i<equipo1; i++){
            int fila = rand.nextInt(10);
            int columna = rand.nextInt(10);
            if(tablero.get(fila).get(columna).isActivo() == false){
                tablero.get(fila).get(columna).setActivo(true);
                tablero.get(fila).get(columna).setEquipo(1);
            }else{
                i--;
            }
        }for(int i=0; i<equipo2; i++){
            int fila = rand.nextInt(10);
            int columna = rand.nextInt(10);
            if(tablero.get(fila).get(columna).isActivo() == false){
                tablero.get(fila).get(columna).setActivo(true);
                tablero.get(fila).get(columna).setEquipo(2);
            }else{
                i--;
            }
        }
        MostrarTablero(tablero);
        System.out.println("Equipo 1: " + equipo1);
        System.out.println("Equipo 2: " + equipo2);
        BuscarSoldadoVida(tablero, 1);
        BuscarSoldadoVida(tablero, 2);
        PromediodeVida(tablero,1);
        PromediodeVida(tablero,2);
        MostrardatosEquipo(tablero,1);
        MostrardatosEquipo(tablero,2);
        RankingDeSoldadosDeEquipo(tablero,1);
        RankingDeSoldadosDeEquipo(tablero,2);
        System.out.println("el juego a comenzado");
        System.out.println("que equipo quieres jugar?");
        int equipo = sc.nextInt();
        if(equipo == 1){
            equipo = 1;
        }else if(equipo == 2){
            equipo = 2;
        }
        while(true){
            System.out.println("Elige un soldado por casillas, primero fila y luego columna");
            int fila = sc.nextInt()-1;
            int columna = sc.nextInt()-1;
            if(tablero.get(fila).get(columna).isActivo() == true){
                if(tablero.get(fila).get(columna).getEquipo() == equipo){
                    System.out.println("El soldado Existe");
                }else{
                    System.out.println("El soldado Existe pero no es del equipo " + equipo);
                    continue;
                }
            }else{
                System.out.println("No existe soldado en esta casilla");
                continue;
            }
            System.out.println("Que tipo de movimiento quieres hacer?");
            System.out.println("1. Arriba");
            System.out.println("2. Abajo");
            System.out.println("3. Izquierda");
            System.out.println("4. Derecha");
            int movimiento = sc.nextInt();
            switch (VerificarEspacio(tablero, fila, columna, movimiento)) {
                case 0 :
                    System.out.println("No hay espacio disponible en ese lugar.");
                    continue;
                case 1 :
                    System.out.println("No puedes mover a un soldado que tiene el mismo equipo.");
                    continue;
                case 2 :
                    System.out.println("Hay un soldado enemigo... comienza la batalla");
                    if(ProbabilidadDeBatalla(tablero, fila, columna, movimiento, rand)){
                        cambiarposicion(tablero, fila, columna, movimiento);
                        if(equipo == 1){
                            equipo = 2;
                        }else{
                            equipo = 1;
                        }
                    }
                    else{
                        tablero.get(fila).get(columna).setActivo(false);
                    }
                    break;
                case 3 :
                    System.out.println("se cambio la posicion");
                    cambiarposicion(tablero, fila, columna, movimiento);
                    if(equipo == 2){
                        equipo = 1;
                    }else{
                        equipo = 2;
                    }
                    break;
            
                case 4 :
                    System.out.println("Ocurrio un error inesperado");
                    continue;
                }
            MostrarTablero(tablero);
        }
    }
    public static void MostrarTablero(ArrayList<ArrayList<soldado>> tablero){
        for(int i = 0; i < 10; i++){
            System.out.println("----------------------------------------");
            for(int j = 0; j < 10; j++){
                if(tablero.get(i).get(j).isActivo() == true){
                    System.out.print(tablero.get(i).get(j).ID());
                }else{
                    System.out.print(" - ");
                }
            System.out.print("|");
            }
            System.out.println();
        }
    }
    public static void BuscarSoldadoVida(ArrayList<ArrayList<soldado>> tablero, int equipo){
        soldado masVida = new soldado();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(tablero.get(i).get(j).isActivo() == true){
                    if(tablero.get(i).get(j).getEquipo() == equipo){
                        if(tablero.get(i).get(j).getVida() > masVida.getVida()){
                            masVida = tablero.get(i).get(j);
                        }
                    }
                }
            }
        }
        if(masVida.getVida()==0){
            System.out.println("El equipo " + equipo + " no tiene soldados activos");
        } else {
            System.out.println("El soldado con mas vida del equipo " + equipo + " es: " + masVida.getNombre() + " con " + masVida.getVida() + " de vida");
        }
    }
    public static void PromediodeVida(ArrayList<ArrayList<soldado>> tablero, int equipo){
        double promedio = 0;
        int cantidad = 0;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(tablero.get(i).get(j).isActivo() == true){
                    if(tablero.get(i).get(j).getEquipo() == equipo){
                        promedio += tablero.get(i).get(j).getVida();
                        cantidad++;
                    }
                }
            }
        }
        promedio /= cantidad;
        System.out.println("El equipo " + equipo + " tiene un promedio de vida de: " + promedio);
    }
    public static void MostrardatosEquipo(ArrayList<ArrayList<soldado>> tablero, int equipo){
        System.out.println();
        System.out.println("Equipo " + equipo);
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(tablero.get(i).get(j).isActivo() == true){
                    if(tablero.get(i).get(j).getEquipo() == equipo){
                        System.out.println(tablero.get(i).get(j).getNombre() + " con vida: " + tablero.get(i).get(j).getVida());
                    }
                }
            }
        }
    }
    public static void RankingDeSoldadosDeEquipo(ArrayList<ArrayList<soldado>> tablero, int equipo){
        ArrayList<soldado> lista = new ArrayList<soldado>();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(tablero.get(i).get(j).isActivo() == true){
                    if(tablero.get(i).get(j).getEquipo() == equipo){
                        lista.add(tablero.get(i).get(j));
                    }
                }
            }
        }
        boolean ordenado = false;
        while (true) { 
            int cambios = 0;
            // Ordenar
            for (int i = 0; i < lista.size() - 1; i++) {
                if (lista.get(i).getVida() < lista.get(i + 1).getVida()) {
                    // intercambiar
                    soldado aux = lista.get(i);
                    lista.set(i, lista.get(i + 1));
                    lista.set(i + 1, aux);
                    cambios++;
                }
            }
            ordenado = cambios == 0;
            if (ordenado) {
                break;
            }
        }
        
        int posicion = 1;
        System.out.println();
        System.out.println("Ranking de soldados del equipo " + equipo);
        for(int i = 0; i < lista.size(); i++){
            System.out.println(posicion + ". " + lista.get(i).getNombre() + " con vida: " + lista.get(i).getVida());
            posicion++;
        }
    }
    public static void decidirGanador(ArrayList<ArrayList<soldado>> tablero){
        int equipo1 = 0;
        int equipo2 = 0;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(tablero.get(i).get(j).isActivo() == true){
                    if(tablero.get(i).get(j).getEquipo() == 1){
                        equipo1++;
                    }else{
                        equipo2++;
                    }
                }
            }
        }
        double equipo1Vida = 0;
        double equipo2Vida = 0;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(tablero.get(i).get(j).isActivo() == true){
                    if(tablero.get(i).get(j).getEquipo() == 1){
                        equipo1Vida += tablero.get(i).get(j).getVida();
                    }else{
                        equipo2Vida += tablero.get(i).get(j).getVida();
                    }
                }
            }
        }
        equipo1Vida /= equipo1;
        equipo2Vida /= equipo2;
        System.out.println();
        if(equipo1Vida > equipo2Vida){
            System.out.println("El equipo 1 ha ganado");
        }else if(equipo1Vida < equipo2Vida){
            System.out.println("El equipo 2 ha ganado");
        }else{
            System.out.println("Empate");
        }
        System.out.println();
    }
    public static int VerificarEspacio(ArrayList<ArrayList<soldado>> tablero, int fila, int columna, int movimiento){
        switch (movimiento) {
            case 1 :
                if(fila-1<0){
                    return 0;
                }
                else if(tablero.get(fila-1).get(columna).isActivo() == true){
                    if(tablero.get(fila-1).get(columna).getEquipo() == tablero.get(fila).get(columna).getEquipo()){
                        return 1;
                    }else{
                        return 2;
                    }
                }else{
                    return 3;
                }
            case 2 :
                if(fila+1>=10){
                    return 0;
                }
                else if(tablero.get(fila+1).get(columna).isActivo() == true){
                    if(tablero.get(fila+1).get(columna).getEquipo() == tablero.get(fila).get(columna).getEquipo()){
                        return 1;
                    }else{
                        return 2;
                    }
                }
                else{
                    return 3;
                }
            case 3 :
                if(columna-1<0){
                    return 0;
                }
                else if(tablero.get(fila).get(columna-1).isActivo() == true){
                    if(tablero.get(fila).get(columna-1).getEquipo() == tablero.get(fila).get(columna).getEquipo()){
                        return 1;
                    }else{
                        return 2;
                    }
                }
                else{
                    return 3;
                }
            case 4 :
                if(columna+1>=10){
                    return 0;
                }
                else if(tablero.get(fila).get(columna+1).isActivo() == true){
                    if(tablero.get(fila).get(columna+1).getEquipo() == tablero.get(fila).get(columna).getEquipo()){
                        return 1;
                    }else{
                        return 2;
                    }
                }
                else{
                    return 3;
                }
            default:
                return 4;
        }
    }
    public static void cambiarposicion(ArrayList<ArrayList<soldado>> tablero, int fila, int columna, int movimiento){
        int nuevaFila = fila;
        int nuevaColumna = columna;
        switch (movimiento) {
            case 1: // Arriba
                nuevaFila = fila - 1;
                break;
            case 2: // Abajo
                nuevaFila = fila + 1;
                break;
            case 3: // Izquierda
                nuevaColumna = columna - 1;
                break;
            case 4: // Derecha
                nuevaColumna = columna + 1;
                break;
            default:
                System.out.println("Movimiento no válido");
                return;
        }
        if (nuevaFila >= 0 && nuevaFila < 10 && nuevaColumna >= 0 && nuevaColumna < 10) {
            soldado temp = tablero.get(fila).get(columna);
            tablero.get(fila).set(columna, tablero.get(nuevaFila).get(nuevaColumna));
            tablero.get(nuevaFila).set(nuevaColumna, temp);
            System.out.println("Movimiento realizado.");
        } else {
            System.out.println("Movimiento fuera de los límites del tablero.");
        }
    }
    public static boolean ProbabilidadDeBatalla(ArrayList<ArrayList<soldado>> tablero, int fila, int columna, int movimiento, Random rand){
        int vida1=tablero.get(fila).get(columna).getVida();
        System.out.println("Vida de tu soldado: " + vida1);
        int vida2=0;
        switch (movimiento) {
            case 1 :
                vida2=tablero.get(fila-1).get(columna).getVida();
                break;
            case 2 :
                vida2=tablero.get(fila+1).get(columna).getVida();
                break;
            case 3 :
                vida2=tablero.get(fila).get(columna-1).getVida();
                break;
            case 4 :
                vida2=tablero.get(fila).get(columna+1).getVida();
                break;
        }
        System.out.println("Vida de el enemigo: " + vida2);
        int probabilidad = rand.nextInt(vida2+vida1);
        System.out.println("Probabilidad de ganar es: " + vida1*100/(vida1+vida2)+"%");
        System.out.println("numero random: " + probabilidad);
        if(vida1>probabilidad){
            System.out.println("El soldado " + tablero.get(fila).get(columna).getNombre() + " ha ganado");
            System.out.println("Tu soldado gano porque el numero random es: " + probabilidad+" y tu vida es: " + vida1+ " siendo tu vida mayor a la probabilidad");
            return true;
        }else{
            System.out.println("El soldado " + tablero.get(fila).get(columna).getNombre() + " ha perdido");
            System.out.println("Tu soldado perdio porque el numero random es: " + probabilidad+" y tu vida es: " + vida1+ " siendo tu vida menor a la probabilidad");
            return false;
            
        }
    }
}
