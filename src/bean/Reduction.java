/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author simob
 */
public class Reduction {

    private Long id;
    private Float pourcentage;
    private int nbPointMin;
    private int nbPointMax;

    public Reduction() {
    }

    public Reduction(Long id, Float pourcentage) {
        this.id = id;
        this.pourcentage = pourcentage;

    }

    public Reduction(Float pourcentage, int nbPointMin, int nbPointMax) {
        this.pourcentage = pourcentage;
        this.nbPointMin = nbPointMin;
        this.nbPointMax = nbPointMax;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(Float pourcentage) {
        this.pourcentage = pourcentage;
    }

    public int getNbPointMin() {
        return nbPointMin;
    }

    public void setNbPointMin(int nbPointMin) {
        this.nbPointMin = nbPointMin;
    }

    public int getNbPointMax() {
        return nbPointMax;
    }

    public void setNbPointMax(int nbPointMax) {
        this.nbPointMax = nbPointMax;
    }

    @Override
    public String toString() {
        return "Reduction{" + "id=" + id + ", pourcentage=" + pourcentage + ", nbPointMin=" + nbPointMin + ", nbPointMax=" + nbPointMax + '}';
    }

}
