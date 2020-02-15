package ba.unsa.etf.rpr;

import java.util.*;
import java.util.function.Function;

public class Preduzece {
    private int osnovica;
    private ArrayList<RadnoMjesto> radnaMjesta=new ArrayList<>();

    public Preduzece(int osnovica) throws NeispravnaOsnovica {
        if(osnovica<=0)throw new NeispravnaOsnovica("Neispravna osnovica "+osnovica);
        this.osnovica = osnovica;
    }

    public int dajOsnovicu() {
        return osnovica;
    }

    public void postaviOsnovicu(int osnovica) throws NeispravnaOsnovica {
        if(osnovica<=0)throw new NeispravnaOsnovica("Neispravna osnovica "+osnovica);
        this.osnovica = osnovica;
    }

    public void novoRadnoMjesto(RadnoMjesto rm) {
        radnaMjesta.add(rm);
    }

    public void zaposli(Radnik r, String naziv) {
        boolean kontrola=false;
        for(RadnoMjesto rm:radnaMjesta){
            if(rm.getNaziv().equals(naziv) && rm.getRadnik()==null){
                rm.setRadnik(r);
                kontrola=true;
                break;
            }
        }
        if(kontrola==false)throw new IllegalStateException("Nijedno radno mjesto tog tipa nije slobodno");
    }

    public void obracunajPlatu() {
        for(RadnoMjesto rm:radnaMjesta){
            if(rm.getRadnik()!=null){
                rm.getRadnik().dodajPlatu(rm.getKoeficijent()*this.osnovica);
            }
        }
    }

    public double iznosPlate() {
        double vracam=0;
        for(RadnoMjesto rm:radnaMjesta){
            if(rm.getRadnik()!=null){
                vracam+=rm.getKoeficijent()*this.osnovica;
            }
        }
        return vracam;
    }

    public Set<Radnik> radnici() {
        Set<Radnik> vracam=new TreeSet<>();
        for(RadnoMjesto rm:radnaMjesta){
            if(rm.getRadnik()!=null){
                vracam.add(rm.getRadnik());
            }
        }
        return vracam;
    }

    public HashMap<RadnoMjesto, Integer> sistematizacija() {
        HashMap<RadnoMjesto,Integer> vracam=new HashMap<>();
        for(RadnoMjesto rm:radnaMjesta) {
            if (vracam.get(rm) != null) {
                Integer broj = vracam.get(rm) + 1;
                vracam.put(rm, broj);
            } else if (vracam.get(rm) == null) {
                Integer broj = 1;
                vracam.put(rm, broj);
            }
        }

        return vracam;
    }

    public List<Radnik> filterRadnici(Function<Radnik,Boolean>func) {
        ArrayList<Radnik>vracam=new ArrayList<>();
        for(RadnoMjesto rm:radnaMjesta){
            if(func.apply(rm.getRadnik()) && rm.getRadnik()!=null)vracam.add(rm.getRadnik());
        }
        return vracam;
    }

    public List<Radnik> vecaProsjecnaPlata(double vrijednost) {
        return filterRadnici((Radnik r)->{
            return r.prosjecnaPlata()>vrijednost;
        });

    }
}
