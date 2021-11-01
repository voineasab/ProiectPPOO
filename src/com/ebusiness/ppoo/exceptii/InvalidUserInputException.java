package com.ebusiness.ppoo.exceptii;

public class InvalidUserInputException extends Exception {
    private String mesaj;

    public InvalidUserInputException(String mesaj) {
        super();
        this.mesaj = mesaj;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }
}
