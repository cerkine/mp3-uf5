package excepcions.ActivitatExceptions.Model;

import excepcions.ActivitatExceptions.Exceptions.BankAccountException;
import excepcions.ActivitatExceptions.Exceptions.ClientAccountException;
import excepcions.ActivitatExceptions.Exceptions.ExceptionMessage;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    List<CompteEstalvi> listComptes = new ArrayList<>();
    int op=0;
    Scanner sc = new Scanner(System.in);
    public int menuGeneral(){
        System.out.println("HOLA! Bienvenido\n¿Que deseas hacer?\n\n-------\n\n1-Añadir Cuenta\n2-Entrar en Cuenta");
        op=sc.nextInt();sc.nextLine();

        switch (op){
            case 1:
                try {
                    menuAddCuenta();
                } catch (BankAccountException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
//                menuEntrarCuenta();
                break;
        }
        return op;
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
            Client cliente = new Client(nombre,apellido,dni);
            System.out.println("-----------");
            System.out.println("Dame el numero que deseas tener de cuenta");
            String numCuenta = sc.nextLine();
            CompteEstalvi cuenta = new CompteEstalvi(numCuenta);
            cuenta.addUser(new Client(nombre,apellido,dni));
            listComptes.add(cuenta);

        } catch (ClientAccountException e) {
            e.printStackTrace();
            throw (new BankAccountException(ExceptionMessage.ACCOUNT_NOT_CREATE));
        }
    }
}
