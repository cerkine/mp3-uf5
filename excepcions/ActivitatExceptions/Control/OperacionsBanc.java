package excepcions.ActivitatExceptions.Control;

import excepcions.ActivitatExceptions.Exceptions.BankAccountException;
import excepcions.ActivitatExceptions.Model.CompteEstalvi;


/**
 Clase que permite validar un DNI.
 Se crea un objeto del tipo ValidadorDNI y se le pasa un String a validar.// Esto es el original, en mi caso no creo ese objeto, y no le paso nada en la creación sino en la llamada del metodo
 @return true si DNI es correcto.
 Desarrollado por Manuel Mato.
 */
public class OperacionsBanc {


        public static boolean verifyDNI(String dni) {


            String letraMayuscula = ""; //Guardaremos la letra introducida en formato mayúscula

            // Aquí excluimos cadenas distintas a 9 caracteres que debe tener un dni y también si el último caracter no es una letra
            if(dni.length() != 9 || Character.isLetter(dni.charAt(8)) == false ) {
                return false;
            }


            // Al superar la primera restricción, la letra la pasamos a mayúscula
            letraMayuscula = (dni.substring(8)).toUpperCase();

            // Por último validamos que sólo tengo 8 dígitos entre los 8 primeros caracteres y que la letra introducida es igual a la de la ecuación
            // Llamamos a los métodos privados de la clase soloNumeros() y letraDNI()
            if(soloNumeros(dni) == true && letraDNI(dni).equals(letraMayuscula)) {
                return true;
            }
            else {
                return false;
            }
        }

        private static boolean soloNumeros(String dni) {

            int i, j = 0;
            String numero = ""; // Es el número que se comprueba uno a uno por si hay alguna letra entre los 8 primeros dígitos
            String miDNI = ""; // Guardamos en una cadena los números para después calcular la letra
            String[] unoNueve = {"0","1","2","3","4","5","6","7","8","9"};

            for(i = 0; i < dni.length() - 1; i++) {
                numero = dni.substring(i, i+1);

                for(j = 0; j < unoNueve.length; j++) {
                    if(numero.equals(unoNueve[j])) {
                        miDNI += unoNueve[j];
                    }
                }
            }

            if(miDNI.length() != 8) {
                return false;
            }
            else {
                return true;
            }
        }

        private static String letraDNI(String dni) {
            // El método es privado porque lo voy a usar internamente en esta clase, no se necesita fuera de ella

            // pasar miNumero a integer
            int miDNI = Integer.parseInt(dni.substring(0,8));
            int resto = 0;
            String miLetra = "";
            String[] asignacionLetra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

            resto = miDNI % 23;

            miLetra = asignacionLetra[resto];

            return miLetra;
        }
    }

