package model;

public class FisierMultimedia {
    String tip;
    String locatie;
    String descriere;

    public FisierMultimedia(String tip, String locatie, String descriere) {
        this.tip = tip;
        this.locatie = locatie;
        this.descriere = descriere;
    }

    public FisierMultimedia() {

    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    @Override
    public String toString() {
        return
                tip + "," + locatie + "," + descriere;
    }
}
