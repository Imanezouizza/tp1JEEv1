/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import bean.Compte;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import service.CompteFacade;

/**
 *
 * @author Imane Zouizza
 */
@ManagedBean
@SessionScoped
public class CompteController {

    private Compte compte;
    private List<Compte> comptes = new ArrayList();
    private @EJB
    CompteFacade compteFacade;
    private double montant;
    private String rib1;
    private String rib2;

    public String forwardCreate() {
        return "Create";
    }

    public String forwardDebitCredit() {
        return "DebitCredit";
    }

    public String forwardList() {
        return "List";
    }
    public String forwardTransferer() {
        return "Transferer";
    }

    public String debiter() {
        compteFacade.debiter(compte.getId(), montant);
        return "List";
    }

    public String create() {
        compteFacade.create(compte);
        compte = null;
        return "List";
    }

    public String crediter() {
        compteFacade.crediter(compte.getId(), montant);
        return "List";
    }

    public String transferer() {
        compteFacade.transferer(rib1, rib2, montant);
        return "List";
    }

    /**
     * Creates a new instance of CompteController
     */
    public CompteController() {
    }

    public Compte getCompte() {
        if (compte == null) {
            compte = new Compte();
        }
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public CompteFacade getCompteFacade() {
        return compteFacade;
    }

    public void setCompteFacade(CompteFacade compteFacade) {
        this.compteFacade = compteFacade;
    }

    public List<Compte> getComptes() {
        comptes = compteFacade.findAll();
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getRib1() {
        return rib1;
    }

    public void setRib1(String rib1) {
        this.rib1 = rib1;
    }

    public String getRib2() {
        return rib2;
    }

    public void setRib2(String rib2) {
        this.rib2 = rib2;
    }

}
