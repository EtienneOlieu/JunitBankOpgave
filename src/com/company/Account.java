package com.company;

public class Account {

    private String kontoNummer;
    private String ejerNavn;
    private double saldo;

    public Account (String kontoNummer, String ejerNavn, double saldo){
        this.kontoNummer = kontoNummer;
        this.ejerNavn = ejerNavn;
        this.saldo = saldo;
    }

    public Account (String kontoNummer, String ejerNavn){
        this.kontoNummer = kontoNummer;
        this.ejerNavn = ejerNavn;
        this.saldo = 0;
    }

    public String getKontoNummer() {
        return kontoNummer;
    }

    public void setKontoNummer(String kontoNummer) {
        this.kontoNummer = kontoNummer;
    }

    public String getEjerNavn() {
        return ejerNavn;
    }

    public void setEjerNavn(String ejerNavn) {
        this.ejerNavn = ejerNavn;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double insert (double insert){
        if (insert <= 0){
            throw new IllegalArgumentException();
        }
        saldo += insert;

        return saldo;
    }

    public double withdraw (double withdraw){
    try {
        if (saldo - withdraw < 0){
            throw new IllegalArgumentException();
        }
        else {
        saldo -= withdraw;
        }
    }catch (IllegalArgumentException e){
        System.out.println("Your account limit has been exceeded.");
    }
        return saldo;
    }

    public double transfer(double transfer, Account destination){
       try {
           if (saldo - (transfer + 5) < 0) {
                throw new IllegalArgumentException();
           } else {
               saldo -= transfer + 5;
               destination.saldo += transfer;
           }
       }catch (IllegalArgumentException e){
           System.out.println("Your account limit has been exceeded.");
       }
           return saldo;
    }

    public String toString (){
        return "Account number: " + kontoNummer + "\nOwned by: " + ejerNavn + "\nWith a balance of: " + saldo+" DKK\n";
    }
}
