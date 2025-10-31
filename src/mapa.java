import java.util.*;
public class mapa {
    private ArrayList<ArrayList<soldado>> mapa;
    private String territorio;
    private int equipo1;
    private String equipoUno;
    private int equipo2;
    private String equipoDos;
    public mapa(){
        Random rand = new Random();
        switch (rand.nextInt(5)) {
            case 0 :
                territorio = "Bosque";
                break;
            case 1 :
                territorio = "Campo abierto";
                break;
            case 2 :
                territorio = "Montaña";
                break;
            case 3 :
                territorio = "Desierto";
                break;
            case 4 :
                territorio = "Playa";
                break;
        }
        System.out.println("Territorio: " + territorio);
        mapa = new ArrayList<ArrayList<soldado>>();
        ArrayList<String> reinos = new ArrayList<String>();
        reinos.add("Inglaterra");
        reinos.add("Francia");
        reinos.add("Castilla-Aragon");
        reinos.add("Moros");
        reinos.add("Sacro Imperio Romano-Germanico");
        equipoUno = reinos.get(rand.nextInt(reinos.size()));
        reinos.remove(equipoUno);
        System.out.println("Equipo 1: " + equipoUno);
        equipoDos = reinos.get(rand.nextInt(reinos.size()));
        System.out.println("Equipo 2: " + equipoDos);
        if((territorio=="Bosque"&&equipoUno=="Inglaterra")||(territorio=="Campo abierto"&&equipoUno=="Francia")||(territorio=="Montaña"&&equipoUno=="Castilla-Aragon")||(territorio=="Desierto"&&equipoUno=="Moros")||((territorio=="Bosque"||territorio=="Playa"||territorio=="Campo abierto")&&equipoUno=="Sacro Imperio Romano-Germanico")){
            System.out.println("el equipo 1 gana uno de ataque gracias al terreno");
        }
        if((territorio=="Bosque"&&equipoDos=="Inglaterra")||(territorio=="Campo abierto"&&equipoDos=="Francia")||(territorio=="Montaña"&&equipoDos=="Castilla-Aragon")||(territorio=="Desierto"&&equipoDos=="Moros")||((territorio=="Bosque"||territorio=="Playa"||territorio=="Campo abierto")&&equipoDos=="Sacro Imperio Romano-Germanico")){
            System.out.println("el equipo 2 gana uno de ataque gracias al terreno");
        }
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
            mapa.add(lista);
        }
        equipo1 = rand.nextInt(10)+1;
        equipo2 = rand.nextInt(10)+1;
        for(int i = 0; i < equipo1; i++){
            int fila = rand.nextInt(10);
            int columna = rand.nextInt(10);
            if(mapa.get(fila).get(columna).isActivo() == false){
                mapa.get(fila).get(columna).setActivo(true);
                mapa.get(fila).get(columna).setEquipo(1);
                mapa.get(fila).get(columna).setPais(equipoUno);
                if(territorio=="Bosque"&&equipoUno=="Inglaterra"){
                    mapa.get(fila).get(columna).setAtaque(mapa.get(fila).get(columna).getAtaque()+1);
                }else if(territorio=="Bampo abierto"&&equipoUno=="Francia"){
                    mapa.get(fila).get(columna).setDefensa(mapa.get(fila).get(columna).getDefensa()+1);
                }else if(territorio=="Montaña"&&equipoUno=="Castilla-Aragon"){
                    mapa.get(fila).get(columna).setVidaactual(mapa.get(fila).get(columna).getVidaactual()+1);
                }else if(territorio=="Desierto"&&equipoUno=="Moros"){
                    mapa.get(fila).get(columna).setAtaque(mapa.get(fila).get(columna).getAtaque()+1);
                }else if((territorio=="Bosque"||territorio=="Playa"||territorio=="Campo abierto")&&equipoUno=="Sacro Imperio Romano-Germanico"){
                    mapa.get(fila).get(columna).setDefensa(mapa.get(fila).get(columna).getDefensa()+1);
                }
            }else{
                i--;
            }
        }for(int i = 0; i < equipo2; i++){
            int fila = rand.nextInt(10);
            int columna = rand.nextInt(10);
            if(mapa.get(fila).get(columna).isActivo() == false){
                mapa.get(fila).get(columna).setActivo(true);
                mapa.get(fila).get(columna).setEquipo(2);
                mapa.get(fila).get(columna).setPais(equipoDos);
                if(territorio=="bosque"&&equipoDos=="Inglaterra"){
                    mapa.get(fila).get(columna).setDefensa(mapa.get(fila).get(columna).getDefensa()+1);
                }else if(territorio=="Campo abierto"&&equipoDos=="Francia"){
                    mapa.get(fila).get(columna).setAtaque(mapa.get(fila).get(columna).getAtaque()+1);
                }else if(territorio=="Montaña"&&equipoDos=="Castilla-Aragon"){
                    mapa.get(fila).get(columna).setVidaactual(mapa.get(fila).get(columna).getVidaactual()+1);
                }else if(territorio=="Desierto"&&equipoDos=="Moros"){
                    mapa.get(fila).get(columna).setDefensa(mapa.get(fila).get(columna).getDefensa()+1);
                }else if((territorio=="Bosque"||territorio=="Playa"||territorio=="Campo abierto")&&equipoDos=="Sacro Imperio Romano-Germanico"){
                    mapa.get(fila).get(columna).setAtaque(mapa.get(fila).get(columna).getAtaque()+1);
                }
            }else{
                i--;
            }
        }
    }
    public void setMapa(ArrayList<ArrayList<soldado>> mapa){
        this.mapa = mapa;
    }
    public void getMapa(){
        for(int i = 0; i < 10; i++){
            System.out.println("----------------------------------------");
            for(int j = 0; j < 10; j++){
                if(mapa.get(i).get(j).isActivo() == true){
                    System.out.print(mapa.get(i).get(j).ID());
                }else{
                    System.out.print(" - ");
                }
            System.out.print("|");
            }
            System.out.println();
        }
    }
    public String getTerritorio(){
        return territorio;
    }
    public int getEquipo1(){
        return equipo1;
    }
    public int getEquipo2(){
        return equipo2;
    }
    public soldado get(int fila, int columna){
        return mapa.get(fila).get(columna);
    }
    public void set(int fila, int columna, soldado soldado){
        mapa.get(fila).set(columna, soldado);
    }
}
