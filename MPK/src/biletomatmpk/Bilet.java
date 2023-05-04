package biletomatmpk;

import java.time.LocalDate;

class Bilet{
    private String nazwaBiletu;
    private int cenaBiletow;
    private LocalDate dataZakupu;
    private int liczbaBiletow;


    protected Bilet(String nazwaBiletu, int cenaBiletow, LocalDate dataZakupu, int liczbaBiletow){
        this.nazwaBiletu = nazwaBiletu;
        this.cenaBiletow = cenaBiletow;
        this.dataZakupu = dataZakupu;
        this.liczbaBiletow = liczbaBiletow;
    }
    protected Bilet(String nazwaBiletu, int cenaBiletow){
        this.nazwaBiletu = nazwaBiletu;
        this.cenaBiletow = cenaBiletow;
    }
    public String toString(){
        return dataZakupu + " : " + nazwaBiletu + " : " + liczbaBiletow + " : " + cenaBiletow + " : " + liczbaBiletow * cenaBiletow;
    }
    public String getRodzajBiletu(){
        return "Bilet " + nazwaBiletu + " w cenie " + cenaBiletow;
    }
    public String getNazwaBiletu(){
        return nazwaBiletu;
    }
    public int getCenaBiletow(){
        return cenaBiletow;
    }
    public LocalDate getDataZakupu(){
        return dataZakupu;
    }
}
