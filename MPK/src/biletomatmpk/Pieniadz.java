package biletomatmpk;

abstract class Pieniadz{ //klasa abstrakcyjna z której dziedziczą pozostałe dwie klasy
    protected int nominal;

    public Pieniadz(int nominal){
        this.nominal = nominal;
    }
    public int getValue(){
        return nominal;
    } //funkcja abstrakcyjna
    public abstract String toString(); //funkcja abstrakcyjna
}

class Gotowka extends Pieniadz {
    public Gotowka(int nominal) {
        super(nominal);
    }

    @Override //przysłonięcie funkcji abstrakcyjnej z klasy Pieniadz
    public String toString(){
        return "" + nominal;
    }
}
class Karta extends Pieniadz {
    private final String numer;

    public Karta(int nominal, String numer) {
        super(nominal);
        this.numer = numer;
    }
    public void payment(int value){
        nominal -= value;
    }

    @Override
    public String toString(){
        return "Stan konta: " + nominal;
    }
}

