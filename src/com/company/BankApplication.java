package com.company;

import java.util.ArrayList;

public class BankApplication {
    private Account selectedAccount;
    private Account secondSelection;
    private UserInterface ui;
    private ArrayList<Account> accountList = new ArrayList<>();
    private ArrayList<Account> accountListWithOutSelection = new ArrayList<>();
    boolean goAgain = true;

public void start(){
    loadDefaultAccounts();

    ui = new UserInterface();
    selectedAccount = null;
    secondSelection = null;

    ui.welcomeMessage();

    while (goAgain){
        ui.printMenu();
        switch (ui.userInput()){

            case "1" -> accountsMenu();
            case "2" -> openAccount();
            case "3" -> closeAccount();
            case "0" -> exitApp();

        }
    }
}


    public void accountsMenu() {
        boolean running = true;
        while (running) {
            ui.printAccounts(accountList);
            ui.printAccountsMenu();
            String userChoice = ui.userInput();
            if (userChoice.equals("0")){
                selectedAccount = null;
                running = false;
            }else {
                try {
                    selectedAccount = accountList.get(Integer.parseInt(userChoice)-1);
                    accountActionsMenu();
                }catch (IllegalArgumentException e){
                    ui.statusMessage(Status.WRONGINPUT,userChoice);
                }catch (IndexOutOfBoundsException e){
                    ui.statusMessage(Status.NOTFOUND,userChoice);
                }

            }
        }
    }

    public void accountActionsMenu(){
    boolean running2 = true;
    while (running2) {
        ui.printAccountActionsMenu(selectedAccount);
        String userChoice = ui.userInput();

        switch (userChoice){
            case "1" -> addFunds();
            case "2" -> withdrawFunds();
            case "3" -> transferFunds();
            case "0" -> running2 = false;
        }

    }
    }

    private void addFunds(){
    ui.statusMessage(Status.INSERT,"");
        double untouchedBalance = selectedAccount.getSaldo();
        String userInput = ui.userInput();
    try {
        double insertAmount = Double.parseDouble(userInput);
        selectedAccount.insert(insertAmount);
    }catch(IllegalArgumentException e){
        ui.statusMessage(Status.WRONGINPUT,userInput);
    }
    if (untouchedBalance < selectedAccount.getSaldo()) {
        ui.showAccountBalance(selectedAccount);
     }
    }

    private void withdrawFunds(){ //træk beløb fra konto
        ui.statusMessage(Status.WITHDRAW,"");
        double untouchedBalance = selectedAccount.getSaldo();
        String userInput = ui.userInput();
        try {
            double withdrawAmount = Double.parseDouble(userInput);
            selectedAccount.withdraw(withdrawAmount);
        }catch(IllegalArgumentException e){
            ui.statusMessage(Status.WRONGINPUT,userInput);
        }
        if (untouchedBalance > selectedAccount.getSaldo()) {
            ui.showAccountBalance(selectedAccount);
        }
    }

    private void transferFunds(){
    ui.statusMessage(Status.TRANSFERAMOUNT,"");
    double untouchedBalance = selectedAccount.getSaldo();

    String userInput = ui.userInput();
    try {
        double transferAmount = Double.parseDouble(userInput);

        for (Account account:accountList){
              if(account != selectedAccount){
                  accountListWithOutSelection.add(account);
              }
        }
        ui.statusMessage(Status.TRANSFERTARGET,"");
        ui.printAccounts(accountListWithOutSelection);
        secondSelection = accountListWithOutSelection.get(Integer.parseInt(ui.userInput())-1);
        selectedAccount.transfer(transferAmount,secondSelection);

    }catch (IllegalArgumentException e){
        ui.statusMessage(Status.WRONGINPUT,userInput);
    }catch (IndexOutOfBoundsException e){
        ui.statusMessage(Status.NOTFOUND,userInput);
    }
    accountListWithOutSelection.clear();
    secondSelection = null;
    if (untouchedBalance < selectedAccount.getSaldo()){
        ui.showAccountBalance(selectedAccount);
    }
    }

    private void openAccount() { //TODO Skal finpudses
    String accountNumber;
    String accountOwnerName;
    Double balance = -1.0;
    ui.statusMessage(Status.OPENNUMBER,"");
    accountNumber = ui.userInput();
    ui.statusMessage(Status.OPENNAME,"");
    accountOwnerName = ui.userInput();
    ui.statusMessage(Status.STARTBALANCE,"");
    try {
        balance = Double.parseDouble(ui.userInput());
    }catch (IllegalArgumentException e){
        ui.statusMessage(Status.WRONGINPUT,"");
    }
    if (balance <= 0){
        selectedAccount = new Account(accountNumber,accountOwnerName);

    }else {
    selectedAccount = new Account(accountNumber,accountOwnerName,balance);
    }
    accountList.add(selectedAccount);
    String accountname = selectedAccount.toString();
    ui.statusMessage(Status.CREATED,accountname);
    selectedAccount = null;
    }

    private void closeAccount() { //TODO bedre kommunikation
        boolean running = true;
        while (running) {
            ui.statusMessage(Status.CLOSEACCOUNT,"");
            ui.printAccounts(accountList);
            ui.printAccountsMenu();
            String userChoice = ui.userInput();
            if (userChoice.equals("0")) {
                running = false;
            } else {
                try {
                    selectedAccount = accountList.get(Integer.parseInt(userChoice) - 1);
                } catch (IllegalArgumentException e) {
                    ui.statusMessage(Status.WRONGINPUT, userChoice);
                } catch (IndexOutOfBoundsException e) {
                    ui.statusMessage(Status.NOTFOUND, userChoice);
                }

            }
            if (selectedAccount != null){
                ui.statusMessage(Status.CLOSEACCOUNT2,"");
                ui.printAccount(selectedAccount);
                accountList.remove(selectedAccount);
            }
        }
    }

    public void exitApp() { //luk programmet
    ui.exitMessage();
    goAgain = false;
    }

    public void loadDefaultAccounts(){ //noget at arbejde med til at starte med - vill normalt skulle hentes fra en fil udefra
    accountList.add(new Account("3212","Orlok Nosferatu",100));
    accountList.add(new Account("2190","Vilhelmina Harker",1000));
    accountList.add(new Account("8753","Louis de Pointe Du Lac",20));


    }
}
