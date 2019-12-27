package ru.otus.homework.atm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.CashOutException;
import ru.otus.homework.bills.Bill;
import ru.otus.homework.cassettes.Cassette;
import ru.otus.homework.cassettes.EmptyCassetteException;
import ru.otus.homework.dispensers.DispenserImpl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static ru.otus.homework.bills.Bill.*;

class MyATMTest {
    private static int amountRub10 = 12;
    private static int amountRub50 = 64;
    private static int amountRub100 = 32;
    private static int amountRub200 = 23;
    private static int amountRub500 = 13;
    private static int amountRub1000 = 734;
    private static int amountRub2000 = 25;
    private static int amountRub5000 = 42;
    private MyATM myATM;
    private Map<Bill, Integer> expectedBillsCashIn;
    private int expectedSum;


    void setUp() {
        Map<Bill, Cassette> cassettes = new HashMap<>();
        cassettes.put(RUB10, new Cassette());
        cassettes.put(RUB50, new Cassette());
        cassettes.put(RUB100, new Cassette());
        cassettes.put(RUB200, new Cassette());
        cassettes.put(RUB500, new Cassette());
        cassettes.put(RUB1000, new Cassette());
        cassettes.put(RUB2000, new Cassette());
        cassettes.put(RUB5000, new Cassette());

        myATM = new MyATM(new DispenserImpl(cassettes));
        expectedBillsCashIn = new HashMap<>();
        expectedBillsCashIn.put(RUB10, amountRub10);
        expectedBillsCashIn.put(RUB50, amountRub50);
        expectedBillsCashIn.put(RUB100, amountRub100);
        expectedBillsCashIn.put(RUB200, amountRub200);
        expectedBillsCashIn.put(RUB500, amountRub500);
        expectedBillsCashIn.put(RUB1000, amountRub1000);
        expectedBillsCashIn.put(RUB2000, amountRub2000);
        expectedBillsCashIn.put(RUB5000, amountRub5000);

        expectedSum = amountRub10 * RUB10.getNominal() +
                amountRub50 * RUB50.getNominal() +
                amountRub100 * RUB100.getNominal() +
                amountRub200 * RUB200.getNominal() +
                amountRub500 * RUB500.getNominal() +
                amountRub1000 * RUB1000.getNominal() +
                amountRub2000 * RUB2000.getNominal() +
                amountRub5000 * RUB5000.getNominal();
    }

    @DisplayName("Успешное внесение всех валют сразу")
    @Test
    void shouldCashIn() {
        assertEquals(expectedSum, myATM.cashIn(expectedBillsCashIn));
    }

    @DisplayName("Успешное внесение 10 рублей")
    @Test
    void shouldCashInBillRub10() {
        Map<Bill, Integer> billRub10 = new HashMap<>();
        billRub10.put(RUB10, 1);
        assertEquals(10, myATM.cashIn(billRub10));
    }

    @DisplayName("Успешное внесение 50 рублей")
    @Test
    void shouldCashInBillRub50() {
        Map<Bill, Integer> billRub50 = new HashMap<>();
        billRub50.put(RUB50, 1);
        assertEquals(50, myATM.cashIn(billRub50));
    }

    @DisplayName("Успешное внесение 100 рублей")
    @Test
    void shouldCashInBillRub100() {
        Map<Bill, Integer> billRub100 = new HashMap<>();
        billRub100.put(RUB100, 1);
        assertEquals(100, myATM.cashIn(billRub100));
    }

    @DisplayName("Успешное внесение 200 рублей")
    @Test
    void shouldCashInBillRub200() {
        Map<Bill, Integer> billRub200 = new HashMap<>();
        billRub200.put(RUB200, 1);
        assertEquals(200, myATM.cashIn(billRub200));
    }

    @DisplayName("Успешное внесение 500 рублей")
    @Test
    void shouldCashInBillRub500() {
        Map<Bill, Integer> billRub500 = new HashMap<>();
        billRub500.put(RUB500, 1);
        assertEquals(500, myATM.cashIn(billRub500));
    }

    @DisplayName("Успешное внесение 5000 рублей")
    @Test
    void shouldCashInBillRub5000() {
        Map<Bill, Integer> billRub5000 = new HashMap<>();
        billRub5000.put(RUB5000, 1);
        assertEquals(5000, myATM.cashIn(billRub5000));
    }

    @DisplayName("Успешное внесение 1000 рублей")
    @Test
    void shouldCashInBillRub1000() {
        Map<Bill, Integer> billRub1000 = new HashMap<>();
        billRub1000.put(RUB1000, 1);
        assertEquals(1000, myATM.cashIn(billRub1000));
    }


    @DisplayName("Успешная выдача всех валют сразу")
    @Test
    void shouldWithdrawExpectedCash() throws CashOutException, EmptyCassetteException {
        myATM.cashIn(expectedBillsCashIn);
        Map<Bill, Integer> actualBillsCashOut = myATM.cashOut(expectedSum);
        assertEquals(expectedBillsCashIn, actualBillsCashOut);
    }

    @DisplayName("Успешная выдача 10 рублей")
    @Test
    void shouldWithdrawBillRub10() throws CashOutException, EmptyCassetteException {
        Map<Bill, Integer> billRub10 = new HashMap<>();
        billRub10.put(RUB10, 1);
        myATM.cashIn(billRub10);
        assertEquals(billRub10, myATM.cashOut(10));
    }


    @DisplayName("Успешная выдача 50 рублей")
    @Test
    void shouldWithdrawBillRub50() throws CashOutException, EmptyCassetteException {
        Map<Bill, Integer> billRub50 = new HashMap<>();
        billRub50.put(RUB50, 1);
        myATM.cashIn(billRub50);
        assertEquals(billRub50, myATM.cashOut(50));
    }


    @DisplayName("Успешная выдача 100 рублей")
    @Test
    void shouldWithdrawBillRub100() throws CashOutException, EmptyCassetteException {
        Map<Bill, Integer> billRub100 = new HashMap<>();
        billRub100.put(RUB100, 1);
        myATM.cashIn(billRub100);
        assertEquals(billRub100, myATM.cashOut(100));
    }


    @DisplayName("Успешная выдача 200 рублей")
    @Test
    void shouldWithdrawBillRub200() throws CashOutException, EmptyCassetteException {
        Map<Bill, Integer> billRub200 = new HashMap<>();
        billRub200.put(RUB200, 1);
        myATM.cashIn(billRub200);
        assertEquals(billRub200, myATM.cashOut(200));
    }


    @DisplayName("Успешная выдача 200 рублей")
    @Test
    void shouldWithdrawBillRub2000() throws CashOutException, EmptyCassetteException {
        Map<Bill, Integer> billRub2000 = new HashMap<>();
        billRub2000.put(RUB2000, 1);
        myATM.cashIn(billRub2000);
        assertEquals(billRub2000, myATM.cashOut(2000));
    }


    @DisplayName("Успешная выдача 5000 рублей")
    @Test
    void shouldWithdrawBillRub5000() throws CashOutException, EmptyCassetteException {
        Map<Bill, Integer> billRub5000 = new HashMap<>();
        billRub5000.put(RUB5000, 1);
        myATM.cashIn(billRub5000);
        assertEquals(billRub5000, myATM.cashOut(5000));
    }


    @DisplayName("Успешная выдача 1000 рублей")
    @Test
    void shouldWithdrawBillRub1000() throws CashOutException, EmptyCassetteException {
        Map<Bill, Integer> billRub1000 = new HashMap<>();
        billRub1000.put(RUB1000, 1);
        myATM.cashIn(billRub1000);
        assertEquals(billRub1000, myATM.cashOut(1000));
    }


    @DisplayName("Попытка выдачи суммы, меньшей чем минимальный имеющийся номинал - CashOutException")
    @Test
    void shouldCashOutExceptionWhenSmaller() throws CashOutException, EmptyCassetteException {
        Map<Bill, Integer> billRub1000 = new HashMap<>();
        billRub1000.put(RUB1000, 1);
        myATM.cashIn(billRub1000);
        assertThrows(CashOutException.class, () -> myATM.cashOut(1));
    }

    @DisplayName("Попытка выдачи суммы, меньшей того, что есть в банкомате. Затем проверка баланса.")
    @Test
    void brokenCashOut() {
        Map<Bill, Cassette> cassettes = new HashMap<>();
        cassettes.put(RUB10, new Cassette());
        cassettes.put(RUB50, new Cassette());
        cassettes.put(RUB100, new Cassette());
        cassettes.put(RUB200, new Cassette());
        cassettes.put(RUB500, new Cassette());
        cassettes.put(RUB1000, new Cassette());
        cassettes.put(RUB2000, new Cassette());
        cassettes.put(RUB5000, new Cassette());

        ATM atm = new MyATM(new DispenserImpl(cassettes));
        atm.cashIn(Collections.singletonMap(RUB100, 2));

        Map<Bill, Integer> billIntegerMap = null;
        try {
            billIntegerMap = atm.cashOut(150);
        } catch (CashOutException | EmptyCassetteException e) {
            e.printStackTrace();
        }
        assertNull(billIntegerMap);
        assertEquals(200, atm.getBalance());
    }

    @DisplayName("Выдача суммы, большей чем есть в банкомате - CashOutException")
    @Test
    void shouldCashOutExceptionWhenGrater() throws CashOutException, EmptyCassetteException {
        Map<Bill, Integer> billRub10 = new HashMap<>();
        billRub10.put(RUB10, 1);
        myATM.cashIn(billRub10);
        assertThrows(CashOutException.class, () -> myATM.cashOut(1000));
    }

    @DisplayName("Успешная проверка баланса")
    @Test
    void shouldGetBalance() {
        assertEquals(expectedSum, myATM.cashIn(expectedBillsCashIn));
        assertEquals(expectedSum, myATM.getBalance());
    }
}