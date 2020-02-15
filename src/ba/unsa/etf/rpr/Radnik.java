package ba.unsa.etf.rpr;

public class Radnik implements  Comparable{
    private String imePrezime,jmbg;
    private double[] plate=new double[1000];
    int velicina=0;

    public Radnik(String imePrezime, String jmng) {
        this.imePrezime = imePrezime;
        this.jmbg = jmng;
    }

    private Radnik() {
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmng) {
        this.jmbg = jmng;
    }


    public void dodajPlatu(double plata) {
        if(velicina>=1000)throw new IllegalArgumentException("Ne možete registrovati više od 1000 plata za radnika "+this.getImePrezime());
        plate[velicina]=plata;
        velicina++;
    }

    public double prosjecnaPlata() {
        double sum=0;
        if(velicina==0)return 0;
        for(int i=0;i<velicina;i++){
            sum+=plate[i];
        }
        double vracam=sum/velicina;
        return vracam;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int compareTo(Object r) {

            if (((Radnik) r).prosjecnaPlata() > this.prosjecnaPlata()) return 1;

        return -1;
    }
}
