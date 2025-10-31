import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class VideoJuego3{
    public static void main(String[] args) throws Exception {
        mapa mapa = new mapa();
        mapa.getMapa();
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Equipo 1: " + mapa.getEquipo1());
        System.out.println("Equipo 2: " + mapa.getEquipo2());
        BuscarSoldadoVida(mapa, 1);
        BuscarSoldadoVida(mapa, 2);
        PromediodeVida(mapa,1);
        PromediodeVida(mapa,2);
        MostrardatosEquipo(mapa,1);
        MostrardatosEquipo(mapa,2);
        RankingDeSoldadosDeEquipo(mapa,1);
        RankingDeSoldadosDeEquipo(mapa,2);
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
            if(mapa.get(fila, columna).isActivo() == true){
                if(mapa.get(fila, columna).getEquipo() == equipo){
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
            switch (VerificarEspacio(mapa, fila, columna, movimiento)) {
                case 0 :
                    System.out.println("No hay espacio disponible en ese lugar.");
                    continue;
                case 1 :
                    System.out.println("No puedes mover a un soldado que tiene el mismo equipo.");
                    continue;
                case 2 :
                    System.out.println("Hay un soldado enemigo... comienza la batalla");
                    if(ProbabilidadDeBatalla(mapa, fila, columna, movimiento, rand)){
                        cambiarposicion(mapa, fila, columna, movimiento);
                        if(equipo == 1){
                            equipo = 2;
                        }else{
                            equipo = 1;
                        }
                    }
                    else{
                        mapa.get(fila,columna).setActivo(false);
                    }
                    break;
                case 3 :
                    System.out.println("se cambio la posicion");
                    cambiarposicion(mapa, fila, columna, movimiento);
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
            mapa.getMapa();
        }
    }
    public static void BuscarSoldadoVida(mapa mapa, int equipo){
        soldado masVida = new soldado();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(mapa.get(i,j).isActivo() == true){
                    if(mapa.get(i,j).getEquipo() == equipo){
                        if(mapa.get(i,j).getVida() > masVida.getVida()){
                            masVida = mapa.get(i,j);
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
    public static void PromediodeVida(mapa mapa, int equipo){
        double promedio = 0;
        int cantidad = 0;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(mapa.get(i,j).isActivo() == true){
                    if(mapa.get(i,j).getEquipo() == equipo){
                        promedio += mapa.get(i,j).getVida();
                        cantidad++;
                    }
                }
            }
        }
        promedio /= cantidad;
        System.out.println("El equipo " + equipo + " tiene un promedio de vida de: " + promedio);
    }
    public static void MostrardatosEquipo(mapa mapa, int equipo){
        System.out.println();
        System.out.println("Equipo " + equipo);
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(mapa.get(i,j).isActivo() == true){
                    if(mapa.get(i,j).getEquipo() == equipo){
                        System.out.println(mapa.get(i,j).getNombre() + " con vida: " + mapa.get(i,j).getVida());
                    }
                }
            }
        }
    }
    public static void RankingDeSoldadosDeEquipo(mapa mapa, int equipo){
        ArrayList<soldado> lista = new ArrayList<soldado>();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(mapa.get(i,j).isActivo() == true){
                    if(mapa.get(i,j).getEquipo() == equipo){
                        lista.add(mapa.get(i,j));
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
    public static void decidirGanador(mapa mapa){
        int equipo1 = 0;
        int equipo2 = 0;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(mapa.get(i,j).isActivo() == true){
                    if(mapa.get(i,j).getEquipo() == 1){
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
                if(mapa.get(i,j).isActivo() == true){
                    if(mapa.get(i,j).getEquipo() == 1){
                        equipo1Vida += mapa.get(i,j).getVida();
                    }else{
                        equipo2Vida += mapa.get(i,j).getVida();
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
    public static int VerificarEspacio(mapa mapa, int fila, int columna, int movimiento){
        switch (movimiento) {
            case 1 :
                if(fila-1<0){
                    return 0;
                }
                else if(mapa.get(fila-1,columna).isActivo() == true){
                    if(mapa.get(fila-1,columna).getEquipo() == mapa.get(fila,columna).getEquipo()){
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
                else if(mapa.get(fila+1,columna).isActivo() == true){
                    if(mapa.get(fila+1,columna).getEquipo() == mapa.get(fila,columna).getEquipo()){
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
                else if(mapa.get(fila,columna-1).isActivo() == true){
                    if(mapa.get(fila,columna-1).getEquipo() == mapa.get(fila,columna).getEquipo()){
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
                else if(mapa.get(fila,columna+1).isActivo() == true){
                    if(mapa.get(fila,columna+1).getEquipo() == mapa.get(fila,columna).getEquipo()){
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
    public static void cambiarposicion(mapa mapa, int fila, int columna, int movimiento){
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
            soldado temp = mapa.get(fila,columna);
            mapa.set(fila,columna, mapa.get(nuevaFila,nuevaColumna));
            mapa.set(nuevaFila,nuevaColumna, temp);
            System.out.println("Movimiento realizado.");
        } else {
            System.out.println("Movimiento fuera de los límites del mapa.");
        }
    }
    public static boolean ProbabilidadDeBatalla(mapa mapa, int fila, int columna, int movimiento, Random rand){
        int vida1=mapa.get(fila,columna).getVida();
        System.out.println("Vida de tu soldado: " + vida1);
        int vida2=0;
        switch (movimiento) {
            case 1 :
                vida2=mapa.get(fila-1,columna).getVida();
                break;
            case 2 :
                vida2=mapa.get(fila+1,columna).getVida();
                break;
            case 3 :
                vida2=mapa.get(fila,columna-1).getVida();
                break;
            case 4 :
                vida2=mapa.get(fila,columna+1).getVida();
                break;
        }
        System.out.println("Vida de el enemigo: " + vida2);
        int probabilidad = rand.nextInt(vida2+vida1);
        System.out.println("Probabilidad de ganar es: " + vida1*100/(vida1+vida2)+"%");
        System.out.println("numero random: " + probabilidad);
        if(vida1>probabilidad){
            System.out.println("El soldado " + mapa.get(fila,columna).getNombre() + " ha ganado");
            System.out.println("Tu soldado gano porque el numero random es: " + probabilidad+" y tu vida es: " + vida1+ " siendo tu vida mayor a la probabilidad");
            return true;
        }else{
            System.out.println("El soldado " + mapa.get(fila,columna).getNombre() + " ha perdido");
            System.out.println("Tu soldado perdio porque el numero random es: " + probabilidad+" y tu vida es: " + vida1+ " siendo tu vida menor a la probabilidad");
            return false;
            
        }
    }
}
