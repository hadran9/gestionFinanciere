package model;

import java.util.Date;

public class Depense {

    public int id_depense;
    private double depense;
    private String description;
    public Date date_depense;

    public int getId_depense() {
        return id_depense;
    }
    
    public void setId_depense(int id_depense) {
        this.id_depense = id_depense;
    }    

    public double getDepense() {
        return depense;
    }

    public void setDepense(double depense) {
        this.depense = depense;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_depense() {
        return date_depense;
    }

    public void setDate_depense(Date date_depense) {
        this.date_depense = date_depense;
    }

    @Override
    public String toString() {
        return "Depense{" +
                "id_depense=" + id_depense +
                ", depense=" + depense +
                ", description='" + description + '\'' +
                ", date_depense=" + date_depense +
                '}';
    }
}
