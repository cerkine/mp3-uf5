package excepcions.ActivitatExceptions.Model;

import excepcions.ActivitatExceptions.Control.OperacionsBanc;
import excepcions.ActivitatExceptions.Exceptions.BankAccountException;
import excepcions.ActivitatExceptions.Exceptions.ClientAccountException;
import excepcions.ActivitatExceptions.Exceptions.ExceptionMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    List<CompteEstalvi> listComptes = new ArrayList<>();
    int op=0;
    Scanner sc = new Scanner(System.in);
    public int menuGeneral(){
        System.out.println("HOLA! Bienvenido\n¿Que deseas hacer?\n\n-------\n\n1-Añadir Cuenta\n2-Entrar en Cuenta\n3-Salir");
        op=sc.nextInt();sc.nextLine();

        switch (op){
            case 1:
                try {
                    menuAddCuenta();
                } catch (BankAccountException e) {
                    System.out.println("\033[31m"+e.getMessage()+" \033[0m");
                }
                break;
            case 2:
                try {
                    CompteEstalvi compteEstalvi = entrarCuenta();
                    if(compteEstalvi!=null){
                        menuCuenta(compteEstalvi);
                    }else{
                        throw new BankAccountException(ExceptionMessage.ACCOUNT_NOT_FOUND);
                    }
                }
                catch (BankAccountException e) {
                    System.out.println("\033[31m"+e.getMessage()+" \033[0m");
                    }
                break;
        }
        return op;
    }

    private void menuCuenta(CompteEstalvi compteEstalvi)  {
        int opc=0;

        do {
            System.out.println("¿Que deseas hacer?\n\n------Saldo: "+compteEstalvi.getSaldo()+"----\n\n1-Ingresar\n2-Retirar\n3-Añadir Usuario\n4-Eliminar Usuario\n5-Transferencia\n6-Salir");
            opc=sc.nextInt();sc.nextLine();

            switch (opc){
                case 1:
                    System.out.println("Cuanto deseas ingresar");
                    double canitdad = sc.nextDouble();sc.nextLine();
                    compteEstalvi.ingressar(canitdad);
                    break;
                case 2:

                    try {
                        System.out.println("Cuanto deseas retirar");
                        double cantidad = sc.nextDouble();sc.nextLine();
                        compteEstalvi.treure(cantidad);
                    } catch (BankAccountException e) {
                        System.out.println("\033[31m"+e.getMessage()+" \033[0m");
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Has cogido añadir usuario, te pediremos unos datos");
                        System.out.println("Dime el nombre");
                        String nombre = sc.nextLine();
                        System.out.println("-----------");
                        System.out.println("Dime el apellido");
                        String apellido = sc.nextLine();
                        System.out.println("-----------");
                        System.out.println("Dame el dni");
                        String dni = sc.nextLine();

                        compteEstalvi.addUser(new Client(nombre,apellido,dni));
                    } catch (ClientAccountException e) {
                        System.out.println("\033[31m"+e.getMessage()+" \033[0m");
                    }
                    break;
                case 4:
                    try {
                        System.out.println("El dni del usuario que deseas eliminar");
                        String dni = sc.nextLine();
                        if (!OperacionsBanc.verifyDNI(dni)) {
                            throw new ClientAccountException(ExceptionMessage.WRONG_DNI);
                        } else {
                            compteEstalvi.removeUser(dni);
                        }
                    } catch (ClientAccountException e) {
                        System.out.println("\033[31m"+e.getMessage()+" \033[0m");
                    } catch (BankAccountException e) {
                        System.out.println("\033[31m"+e.getMessage()+" \033[0m");
                    }
                    break;
                case 5:
                    System.out.println("Has elegido transferencia");
                    try {
                        CompteEstalvi destCompte = entrarCuenta();

                        System.out.println("Cuanto dinero envias");
                        double cantidad = sc.nextDouble();

                        compteEstalvi.treure(cantidad);

                        compteEstalvi.transferencia(destCompte,cantidad);
                    } catch (BankAccountException e) {

                        System.out.println("\033[31m"+e.getMessage()+" \033[0m");
                        e=new BankAccountException(ExceptionMessage.TRANSFER_ERROR);
                        System.out.println("\033[31m"+e.getMessage()+" \033[0m");

                    }


                    break;
            }
        }while(opc!=6);

    }

    private CompteEstalvi entrarCuenta() throws BankAccountException {
        CompteEstalvi compteEstalvi = null;


        System.out.println("Introduce el numero de cuenta");
        String numCompte = sc.nextLine();
        for (CompteEstalvi p : listComptes) {
            if (p.getNumCompte().equals(numCompte)) {
                return p;
            }
        }
        throw new BankAccountException(ExceptionMessage.ACCOUNT_NOT_FOUND);



    }

    void menuAddCuenta() throws BankAccountException {
        System.out.println("Hola lo primero sera decir los datos de almenos una dueño");
        System.out.println("--------------");
        System.out.println("Dime el nombre");
        String nombre = sc.nextLine();
        System.out.println("-----------");
        System.out.println("Dime el apellido");
        String apellido = sc.nextLine();
        System.out.println("-----------");
        System.out.println("Dame el dni");
        String dni = sc.nextLine();
        try {
            System.out.println("-----------");
            System.out.println("Dame el numero que deseas tener de cuenta");
            String numCuenta = sc.nextLine();
            CompteEstalvi cuenta = new CompteEstalvi(numCuenta);
            cuenta.addUser(new Client(nombre,apellido,dni));
            listComptes.add(cuenta);

        } catch (ClientAccountException e) {
            System.out.println("\033[31m"+e.getMessage()+" \033[0m");
            throw (new BankAccountException(ExceptionMessage.ACCOUNT_NOT_CREATE));
        }
    }
}
