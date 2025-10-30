import java.util.Random;
import java.util.Scanner;
public class verificaciondemetodos {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        soldado soldado = new soldado("Soldado1", rand.nextInt(5)+1, rand.nextInt(5)+1, rand.nextInt(5)+1, 0);
        soldado.setActivo(true);
        MostarDatos(soldado);
        while(true){
            System.out.println("Que quieres hacer?");
            System.out.println("1. Atacar");
            System.out.println("2. Defensa");
            System.out.println("3. Retroceder");
            System.out.println("4. Avanzar");
            System.out.println("5. Huir");
            System.out.println("6. Ser atacado");
            System.out.println("7. Morir");
            System.out.println("8. Salir");
            int opcion = sc.nextInt();
            switch(opcion){
                case 1:
                    soldado.atacar();
                    MostarDatos(soldado);
                    break;
                case 2:
                    soldado.defender();
                    MostarDatos(soldado);
                    break;
                case 3:
                    soldado.retroceder();
                    MostarDatos(soldado);
                    break;
                case 4:
                    soldado.avanzar();
                    MostarDatos(soldado);
                    break;
                case 5:
                    soldado.huir();
                    MostarDatos(soldado);
                    break;
                case 6:
                    soldado.serAtacado();
                    MostarDatos(soldado);
                    break;
                case 7:
                    soldado.morir();
                    MostarDatos(soldado);
                    System.out.println("El soldado ha muerto");
                    System.exit(0);
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }
    public static void MostarDatos(soldado soldado){
        System.out.println("Nombre: " + soldado.getNombre());
        System.out.println("Vida: " + soldado.getVida());
        System.out.println("Ataque: " + soldado.getAtaque());
        System.out.println("Defensa: " + soldado.getDefensa());
        System.out.println("Velocidad: " + soldado.getVelocidad());
        System.out.println("Actividad: " + soldado.getActitud());
        System.out.println("Activo: " + soldado.isActivo());
    }
}
