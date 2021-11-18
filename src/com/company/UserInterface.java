package com.company;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    Scanner sc = new Scanner(System.in);

    public UserInterface(){
    }

    public String userInput(){
        return sc.nextLine();
    }

    public void welcomeMessage(){
        System.out.println("""
    ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---
            Welcome to Banko Bank's account management portal
    ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---
    """);
    }

    public void statusMessage(Status status, String userInput){ // god idé: oprette flere metoder til håndtere forskellige grupper af meddelelser
        switch (status){
            case OK -> System.out.println("Success!");
            case NO -> System.out.println("Could not be done!");
            case NOTFOUND -> System.out.println("'"+ userInput + "' could not be found.");
            case WRONGINPUT -> System.out.println("'"+ userInput + "' is not a valid input.");
            case OPENNAME -> System.out.println("Please enter the name of the owner of the account you wish to make");
            case OPENNUMBER -> System.out.println("Please enter the number for the new account");
            case CREATED -> System.out.println("You have successfully created '" + userInput +"'.");
            case INSERT -> System.out.println("Type the amount you wish to add to the account.");
            case WITHDRAW -> System.out.println("Type the amount you wish to withdraw from the account.");
            case TRANSFERAMOUNT -> System.out.println("Type the amount you wish to transfer to the target account.");
            case TRANSFERTARGET -> System.out.println("Please choose the account to receive the transfered amount");
            case SELECT -> System.out.println("Please make your selection below");
            case CLOSEACCOUNT -> System.out.println("You are about to close an account. Be careful as this cannot be undone.");
            case CLOSEACCOUNT2 -> System.out.println("Deleting: ");
            case STARTBALANCE -> System.out.println("Please enter a starting balance for the account. Or press enter for no balance.");
            default -> System.out.println("Your input was not registered.");
        }

    }

    public void exitMessage(){
        System.out.println("""
    ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---
    Thank you for using Banko Bank's account management portal.
    We hope to see you soon.
    ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---
    """);
    }

    public void printMenu(){
        System.out.println("""
               ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---
               
                1 - Display open accounts.
                2 - Open a new account.
                3 - Close an account.
                
                0 - Exit the program.
                
                ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---
                
                """);
    }

    public void printAccountsMenu(){
        System.out.println("""
                ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---
                
                Select an account by pressing its corresponding number.
                
                0 - Back to main menu.
                
                ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---
                """);
    }

    public void printAccountActionsMenu(Object account){
        System.out.println(account);

        System.out.println("""
                ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---
                
                1 - Add funds to account balance
                2 - Withdraw funds from account balance
                3 - Transfer funds from this account to another account.
                
                0 - back to account selections.
                ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---
                """);
    }

    public void printAccounts(List list){
            int counter = 0;
        for (Object object: list) {
            counter ++;
            System.out.println(counter + " - " + object);
        }
    }

    public void showAccountBalance(Account account){
        System.out.println("The balance for account number: "+account.getKontoNummer() +" is = " + account.getSaldo() +"DKK");
    }

    public void printAccount (Object account){
        System.out.println(account);
    }
}
