package com.ebusiness.ppoo;

import com.ebusiness.ppoo.exceptii.InvalidUserInputException;
import model.FisierMultimedia;

import java.io.*;
import java.util.*;


public class Main {
    public static int alegere = 0;


    public static void main(String[] args) {
        LinkedList<FisierMultimedia> listaFisiere = new LinkedList<>();
        String[] tipuri = {"Imagine", "Audio", "Video"};
        citesteDinFisier("D:\\Java Facultate\\ProiectPPOO\\Fisiere.txt", listaFisiere);

        //region LINKEDLIST
        /*
        LinkedList<FisierMultimedia> setFisiere = new LinkedList<>();
        setFisiere.add(new FisierMultimedia(tipuri[0], "fisiereMultimedia\\caine.jpg", "Caine"));
        setFisiere.add(new FisierMultimedia(tipuri[0], "fisiereMultimedia\\papagal.jpg", "Papagal"));
        setFisiere.add(new FisierMultimedia(tipuri[0], "fisiereMultimedia\\pisica.jpg", "Pisica"));
        setFisiere.add(new FisierMultimedia(tipuri[1], "fisiereMultimedia\\file_example_MP3_1MG.mp3", "Cantec 1 mp3"));
        setFisiere.add(new FisierMultimedia(tipuri[1], "fisiereMultimedia\\file_example_MP3_700KB.wav", "Cantec 2 mp3"));
        setFisiere.add(new FisierMultimedia(tipuri[1], "fisiereMultimedia\\file_example_WAV_1MG.wav", "Cantec 3 wav"));
*/
        //endregion

        String[][] matriceFisiereMultimedia = new String[50][3];
        matriceFisiereMultimedia[0][0] = "Tip";
        matriceFisiereMultimedia[0][1] = "Locatie";
        matriceFisiereMultimedia[0][2] = "Descriere";

        FisierMultimedia[] vectorFisiere = listaFisiere.toArray(new FisierMultimedia[0]);
        System.out.println(listaFisiere.toString());
        asignareValoriMatrice(vectorFisiere, matriceFisiereMultimedia);

        AfisareMeniu();
        Scanner in = new Scanner(System.in);

        try {
            alegere = in.nextInt();
        }
        catch (InputMismatchException ex) {
            System.out.println(ex.getMessage());
        }

        if (alegere < 1 || alegere > 5) {
            try {
                throw new InvalidUserInputException("Nu ati introdus o valoare valida. Va rog alegeti intre 1, 2, 3, 4.");
            } catch (InvalidUserInputException ex) {
                System.out.println(ex.getMesaj());
            }
        }
        else {
            while (alegere != 5) {
                    if (alegere == 1) {
                        afisareMatrice(matriceFisiereMultimedia);
                    }

                    else if (alegere == 2) {
                        int alegereInt = citesteInt("Ce tip de fisier? 0 - Imagine, 1 - Audio, 2 - Video", "Valoare invalida", 0, 2);
                        FisierMultimedia fisierNou = new FisierMultimedia();
                        switch (alegereInt) {
                            case 0:
                                fisierNou.setTip(tipuri[0]);
                                break;
                            case 1:
                                fisierNou.setTip(tipuri[1]);
                                break;
                            case 2:
                                fisierNou.setTip(tipuri[2]);
                                break;
                            default:
                                System.out.println("Alegeti intre 0, 1 , 2");
                                break;
                        }
                        String alegere2 = citesteString("Ce locatie are fisierul in PC?", "Locatie invalida");
                        fisierNou.setLocatie(alegere2);
                        alegere2 = citesteString("O scurta descriere pentru fisier", "Descriere invalida");
                        fisierNou.setDescriere(alegere2);
                        listaFisiere.add(fisierNou);
                        vectorFisiere = listaFisiere.toArray(new FisierMultimedia[0]);
                        asignareValoriMatrice(vectorFisiere, matriceFisiereMultimedia);
                        //adaugaInFisier(fisierNou);//daca vreau sa salveze ce e nou in fisier, in timpul executiei
                    }

                    else if (alegere == 3) {
                        String alegere2 = citesteString("Ce locatie are fisierul pe care doriti sa il stergeti?", "Valoare invalida");
                        //in.nextLine();
                        for (Iterator<FisierMultimedia> iterator = listaFisiere.iterator(); iterator.hasNext(); ) {
                            FisierMultimedia data = iterator.next();

                            if (data.getLocatie().equals(alegere2)) {
                                iterator.remove();
                                vectorFisiere = listaFisiere.toArray(new FisierMultimedia[0]);
                                matriceFisiereMultimedia = stergereDinMatrice(data, matriceFisiereMultimedia);
                                System.out.println("----------------------------");
                                System.out.println("Ati sters fisierele cu locatia " + alegere2);
                            }
                        }
                        System.out.println("----------------------------");
                    }

                    else if (alegere == 4) {
                        String[][] matriceFiltrata = new String[10][3];
                        LinkedList<FisierMultimedia> arrayListFilter = new LinkedList<>();
                        System.out.println("Ce tip de fisiere vreti sa vedeti? Imagine, Audio, Video");
                        in.nextLine();
                        String alegere3 = in.nextLine();
                        for (Iterator<FisierMultimedia> iterator = listaFisiere.iterator(); iterator.hasNext(); ) {
                            FisierMultimedia data = iterator.next();

                            if (data.getTip().equals(alegere3)) {
                                arrayListFilter.add(data);
                            }
                        }
                        FisierMultimedia[] newArrayFilter = arrayListFilter.toArray(new FisierMultimedia[0]);
                        asignareValoriMatrice(newArrayFilter, matriceFiltrata);
                        afisareMatrice(matriceFiltrata);
                        System.out.println("----------------------------");

                    }
                    AfisareMeniu();
                    alegere = in.nextInt();
                }
            }
            scrieInFisier(listaFisiere);//ca sa dea override la ce e in fisier, la inchiderea aplicatiei
        }


    private static void afisareMatrice(String[][] vector) {
        for (int i=0;i<9;i++) {
            for (int j=0;j<3;j++) {
                System.out.print(vector[i][j]+"\t");
                if (j==2) {
                    System.out.print("\n");
                }
                if (i==0 && j==1) {
                    System.out.print("                            ");

                }
            }
        }
    }


    private static void asignareValoriMatrice(FisierMultimedia[] vectorFisiere, String [][] matriceFisiere) {
        for (int i = 0; i < vectorFisiere.length; i++) {
            for (int j=0;j<3;j++) {
                switch(j) {
                    case 0:
                        matriceFisiere[i][j]=vectorFisiere[i].getTip();
                        break;
                    case 1:
                        matriceFisiere[i][j]=vectorFisiere[i].getLocatie();
                        break;
                    case 2:
                        matriceFisiere[i][j]=vectorFisiere[i].getDescriere();
                        break;
                }
            }
        }
    }

    private static String[][] stergereDinMatrice(FisierMultimedia criteriu, String[][] matriceFisiere) {
        List<String[]> temp = new ArrayList<String[]>();
        for (String[] linie : matriceFisiere) {
            boolean gasit = false;
            for (String locatie : linie) {
                if (locatie==criteriu.getLocatie()) {
                    gasit=true;
                    break;
                }
            }
            if (!gasit) {
                temp.add(linie);
            }
            matriceFisiere=new String[temp.size()][3];
            for (int i=0;i<temp.size()-1;i++) {
                matriceFisiere[i]=temp.get(i);
            }
        }
        return matriceFisiere;
    }

    private static void AfisareMeniu() {
        System.out.println("Alegeti o optiune:");
        System.out.println("1 - Afisati toate fisierele existente");
        System.out.println("2 - Adaugati un fisier");
        System.out.println("3 - Stergeti un fisier");
        System.out.println("4 - Vizualizati un raport");
        System.out.println("5 - Iesire");
    }

    private static void scrieInFisier(LinkedList<FisierMultimedia> listaFisiere) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Fisiere.txt")));
            StringBuilder stringBuilder = new StringBuilder();
            for (FisierMultimedia fisier : listaFisiere) {
                stringBuilder.append(fisier.toString()).append("\n");
            }
            //System.out.println(stringBuilder.toString());
            writer.write(stringBuilder.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  static void adaugaInFisier(FisierMultimedia fisier) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Fisiere.txt"), true));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(fisier.toString()).append("\n");
            writer.write(stringBuilder.toString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void citesteDinFisier(String filePath, LinkedList<FisierMultimedia> lista) {

        try {
            Scanner scanner = new Scanner(new File(filePath));
            scanner.useDelimiter(",|\n");
            while (scanner.hasNext()) {
                FisierMultimedia fisierMultimedia = new FisierMultimedia();
                fisierMultimedia.setTip(scanner.next());
                fisierMultimedia.setLocatie(scanner.next());
                fisierMultimedia.setDescriere(scanner.next());
                lista.add(fisierMultimedia);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nu exista fisierul " + filePath);
        } catch (IOException e) {
            System.out.println("Eroare in timpul citirii din fisier");
        }
    }

    public static int citesteInt(String prompt, String msg, int low, int high) {
        Scanner in = new Scanner(System.in);
        int num = -1;
        try {
            System.out.println(prompt);
            num = in.nextInt();
            while (num>low &&num > high) {
                System.out.println(msg);
                System.out.println(prompt);
                num = in.nextInt();
            }
        }
        catch (InputMismatchException ex) {
            System.out.println(ex.getMessage());
        }
        return num;
    }

    public static String citesteString(String prompt, String msg) {
        String strInput = "0";
        boolean valid = false;
        Scanner in = new Scanner(System.in);
        
        while (valid == false) {
            System.out.println(prompt);
            strInput = in.nextLine();

            if (strInput != null && strInput != "0") {
                valid=true;
            }
            else System.out.println(msg);
        }

        return strInput;
    }

}
