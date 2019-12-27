package ru.otus.homework.bills;

public enum Bill {

    RUB10(10),
    RUB50(50),
    RUB100(100),
    RUB200(200),
    RUB500(500),
    RUB1000(1000),
    RUB2000(2000),
    RUB5000(5000);

    Bill(int nominal) {
        this.nominal = nominal;
    }

    private int nominal;

    public int getNominal() {
        return nominal;
    }
}