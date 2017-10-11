/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Compte;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Imane Zouizza
 */
@Stateless
public class CompteFacade extends AbstractFacade<Compte> {

    @PersistenceContext(unitName = "tp1JEEv1PU")
    private EntityManager em;

    public int debiter(String rib, double montant) {
        Compte compte = find(rib);
        double nvSolde = compte.getSolde() - montant;
        if (nvSolde < 0) {
            return -1;
        } else {
            compte.setSolde(nvSolde);
            edit(compte);
            return 1;
        }
    }

    public int crediter(String rib, double montant) {
        Compte compte = find(rib);
        double nvSolde = compte.getSolde() + montant;
        compte.setSolde(nvSolde);
        edit(compte);
        return 1;
    }

    public int transferer(String rib1, String rib2, double montant) {
        int resDebiter = debiter(rib1, montant);
        if (resDebiter < 0) {
            return resDebiter;
        } else  {
            crediter(rib2, montant);
            return 1;
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompteFacade() {
        super(Compte.class
        );
    }

}
