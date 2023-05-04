package biletomatmpk;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;

public class Biletomat{
    private ArrayList<Bilet> zakupioneBilety = new ArrayList<>();
    private ArrayList<Bilet> biletyWOfercie = new ArrayList<>();

    private Stack<Pieniadz> pieniadzeWPortfelu;
    private Karta pieniadzeNaKarcie;
    private Scanner input = new Scanner(System.in);
    private String lokalizacja;

    public Biletomat(String lokalizacja, Stack pieniadzeWPortfelu, Karta pieniadzeNaKarcie){
        this.lokalizacja = lokalizacja;
        biletyWOfercie.add(new Bilet("Ulgowy 20-minutowy", 1));
        biletyWOfercie.add(new Bilet("Ulgowy 60-minutowy", 2));
        biletyWOfercie.add(new Bilet("Ulgowy 24-godzinny", 6));
        biletyWOfercie.add(new Bilet("Normalny 20-minutowy", 2));
        biletyWOfercie.add(new Bilet("Normalny 60-minutowy", 4));
        biletyWOfercie.add(new Bilet("Normalny 24-godzinny", 12));
        this.pieniadzeWPortfelu = pieniadzeWPortfelu;
        this.pieniadzeNaKarcie = pieniadzeNaKarcie;
    }

    private boolean zaplata(int doZaplaty){
        int wybranaOpcja;
        Stack<Pieniadz> pieniadzeWBiletomacie = new Stack<>();
        int[] nominaly = {1,2,5,10,20,50,100,200,500};
        int i;

        System.out.println(pieniadzeNaKarcie);
        System.out.println("Pieniądze w portfelu: " + pieniadzeWPortfelu);
        while(true) {
            System.out.println("\nWYBIERZ SPOSÓB ZAPŁATY");
            System.out.println("-----------------------");
            System.out.println("1. Karta płatnicza");
            System.out.println("2. Gotówka");
            wybranaOpcja = input.nextInt();

            if (wybranaOpcja == 1) {
                if (pieniadzeNaKarcie.getValue() >= doZaplaty){
                    pieniadzeNaKarcie.payment(doZaplaty);
                    return true;
                }
                else{
                    System.out.println("Za mało środków! Transakcja odrzucona!");
                    return false;
                }

            }
            else if(wybranaOpcja == 2){
                while(!pieniadzeWPortfelu.empty()){
                    if(doZaplaty >= 0) {
                        doZaplaty -= pieniadzeWPortfelu.peek().getValue();
                        pieniadzeWBiletomacie.push(pieniadzeWPortfelu.peek());
                        pieniadzeWPortfelu.pop();
                    }
                    else if(doZaplaty == 0)
                        return true;
                    else{
                        doZaplaty *= (-1);
                        i = 8;
                        while(i!=0){
                            if(nominaly[i] <= doZaplaty ) {
                                pieniadzeWPortfelu.push(new Gotowka(nominaly[i]));
                                doZaplaty -= nominaly[i];
                            }
                            else i--;
                        }
                        return true;
                    }
                }
                System.out.println("Za mało środków! Transakcja odrzucona!");
                while(!pieniadzeWBiletomacie.empty()) {
                    pieniadzeWPortfelu.push(pieniadzeWBiletomacie.peek());
                    pieniadzeWBiletomacie.pop();
                }
                return false;
            }
            else System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
        }
    }
    public void sprzedazBiletow(){
        int cenaBiletu=0, biletOpcja;
        LocalDate dataZakupu;
        LinkedList<Bilet> tempZakupioneBilety = new LinkedList<>();
        int doZaplaty=0, kupnoOpcja;
        int i;

        while(true) {
            System.out.println("\nBILETY W OFERCIE");
            System.out.println("------------------");
            for(i = 0; i < biletyWOfercie.size(); i++)
                System.out.println((i+1) + ". " + biletyWOfercie.get(i).getRodzajBiletu() + "zł");
            System.out.println((++i) + ". Anuluj");
            biletOpcja = input.nextInt();

            if(biletOpcja > i){
                System.out.println("Nieprawidłowa opcja!");
                continue;
            }
            if(biletOpcja == 7 && doZaplaty == 0)
                break;
            else if(biletOpcja == 7){
                if(zaplata(doZaplaty))
                    System.out.println("Bilety zakupione pomyślnie!");
                else
                    System.out.println("Błąd podczas zakupu biletów!");
                break;
            }
            dataZakupu = LocalDate.now();
            System.out.print("Wybierz liczbę biletów: ");
            kupnoOpcja = input.nextInt();
            if(kupnoOpcja>0) {
                tempZakupioneBilety.push(new Bilet(biletyWOfercie.get(biletOpcja - 1).getNazwaBiletu(), biletyWOfercie.get(biletOpcja - 1).getCenaBiletow(), dataZakupu, kupnoOpcja));
                doZaplaty += (kupnoOpcja * biletyWOfercie.get(biletOpcja - 1).getCenaBiletow());
            }else System.out.println("Nieprawidłowa liczba biletów!");
            System.out.println("Chcesz dodać kolejny bilet?");
            System.out.println("1. Tak");
            System.out.println("2. Nie");
            kupnoOpcja = input.nextInt();
            if (kupnoOpcja == 1)
                continue;

            System.out.println("\nKupujesz bilety w cenie " + doZaplaty);
            if(zaplata(doZaplaty)) {
                System.out.println("Bilety zakupione pomyślnie!\n");
                while(!tempZakupioneBilety.isEmpty()) {
                    zakupioneBilety.add(tempZakupioneBilety.getFirst());
                    tempZakupioneBilety.removeFirst();
                }

            }
            else
                System.out.println("Błąd podczas zakupu biletów!");

            break;
        }


    }
    public void wydrukujTransakcje(LocalDate date){
        LocalDate tempDate;
        for(int i = 0; i < zakupioneBilety.size(); i++){
            tempDate = zakupioneBilety.get(i).getDataZakupu();
            if(tempDate == date)
                System.out.println(tempDate);
        }
    }

    //funkcja umożliwiająca wydruk informacji o wszystkich sprzedanych biletach (w formie: data : rodzaj biletu : liczba biletów : dochód)
    public String toString(){
        //return zakupioneBilety;
       for (Bilet bilet : zakupioneBilety) {
            System.out.println(bilet);
        }
        return "";
    }
}
