package biletomatmpk;


import java.time.LocalDate;
import java.util.Stack;

public class Main {
    public static void main(String[] args){
        Stack<Pieniadz> pieniadzeWPortfelu = new Stack<Pieniadz>();
        LocalDate date1 = LocalDate.of(2023, 4, 12);
        LocalDate date2 = LocalDate.of(2023, 4, 30);
        pieniadzeWPortfelu.push(new Gotowka(1));
        pieniadzeWPortfelu.push(new Gotowka(5));
        pieniadzeWPortfelu.push(new Gotowka(10));

        Biletomat biletomat1 = new Biletomat("Nowa Huta", pieniadzeWPortfelu, new Karta(444, "123456789"));
        biletomat1.sprzedazBiletow();

        //System.out.println("Bilety z 2023-04-12: ");

        biletomat1.wydrukujTransakcje(date1);
        System.out.println("Bilety z dnia 30-04-2023: ");
        biletomat1.wydrukujTransakcje(date2);

        System.out.println(biletomat1);
    }
}
