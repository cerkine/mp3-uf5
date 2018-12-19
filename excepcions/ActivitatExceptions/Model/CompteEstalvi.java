package excepcions.ActivitatExceptions.Model;

import excepcions.ActivitatExceptions.Exceptions.BankAccountException;
import excepcions.ActivitatExceptions.Exceptions.ExceptionMessage;

import java.util.ArrayList;
import java.util.List;

public class CompteEstalvi {
    private String numCompte;
    private double saldo;
    private List<Client> llista_usuaris;

    public CompteEstalvi(String numCompte) {
        llista_usuaris = new ArrayList<>();
        this.numCompte = numCompte;
        saldo = 0;
    }

    /**
        Afegeix un usuari d'aquest compte
        @param client
        @return quantitat d'usuaris que té el compte

     **/
    public int addUser(Client client) {
        llista_usuaris.add(client);
        return llista_usuaris.size();
    }

    /**
     Elimina un usuari d'aquest compte
     @param dni
     @return quantitat d'usuaris que té el compte
     @throws BankAccountException
     **/
    public int removeUser(String dni) throws BankAccountException {
        if (llista_usuaris.size()==1){
            throw new BankAccountException(ExceptionMessage.ACCOUNT_ZERO_USER);
        }else{
            llista_usuaris.removeIf(u -> dni.equals(u.getDNI()));
        }
        return llista_usuaris.size();
    }

    /**
     * Afegeix m diners al saldo
     * @param m
     */
    public void ingressar(double m) {
        saldo += m;
    }

    /**
     * Treu m diners del compte si n'hi han suficient
     * @param m
     * @throws BankAccountException
     */
    public void treure(double m) throws BankAccountException {
        if(saldo>=m){
            saldo-=m;
        }else {
            throw new BankAccountException(ExceptionMessage.ACCOUNT_OVERDRAFT);
        }
    }

    public String getNumCompte() {
        return numCompte;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Client> getLlista_usuaris() {
        return llista_usuaris;
    }

    public void transferencia( CompteEstalvi desti, double suma) {

        desti.ingressar(suma);
    }

}
