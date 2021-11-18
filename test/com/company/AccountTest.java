package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
        Account account = new Account("tretten", "Frederik Jørgensen",100);

    @Test
        public void testOfInsertPositivNumber() {

        int expectedSaldoAmount = 123;

        account.insert(23);

        assertEquals(expectedSaldoAmount,account.getSaldo());
    }

    @Test
    public void negative_InsertAmount(){

        assertThrows(IllegalArgumentException.class,() -> account.insert(-50));
    }

    @Test
    public void overExceedWithDrawAmount(){
        assertThrows(IllegalArgumentException.class,() -> account.withdraw(7780));
    }

}