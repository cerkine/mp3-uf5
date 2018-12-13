package excepcions.ActivitatExceptions.Model;

public class Main {
    public static void main(String[] args) {
        Menu m = new Menu();
        int op=0;
        do{
            op=m.menuGeneral();
        }while (op!=3);
    }
}
