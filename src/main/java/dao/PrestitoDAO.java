package dao;

import entities.Prestito;


import javax.persistence.*;
import java.time.LocalDate;


public class PrestitoDAO {
    private EntityManager em;

    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Prestito prestito) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(prestito);
            t.commit();
            System.out.println("Prestito salvato con successo!");
        } catch (Exception e) {
            System.out.println("Errore durante il salvataggio del prestito: " + e.getMessage());
        }
    }
    public LocalDate calcolaDataRestituzionePrevista(LocalDate dataInizioPrestito) {
        return dataInizioPrestito.plusDays(30);
    }

    public Prestito findById(long id) {
        return em.find(Prestito.class, id);
    }

    public void deleteById(long id) {
        try {
            EntityTransaction t = em.getTransaction();
            Prestito found = em.find(Prestito.class, id);
            if (found != null) {
                t.begin();
                em.remove(found);
                t.commit();
                System.out.println("Prestito eliminato con successo!");
            } else {
                System.out.println("Prestito non trovato.");
            }
        } catch (Exception e) {
            System.out.println("Errore durante l'eliminazione del prestito: " + e.getMessage());
        }
    }



}