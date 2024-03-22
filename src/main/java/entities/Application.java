package entities;

import dao.CatalogoDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogo");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        CatalogoDAO catalogoDAO = new CatalogoDAO(em);

        // Esempio di aggiunta di un libro al catalogo
        Libri libro = new Libri(9788804678007L, "Il vecchio e il mare", 1952, 127, "Ernest Hemingway", "Romanzo");
        catalogoDAO.aggiungiLibro(libro);

        // Esempio di aggiunta di una rivista al catalogo
        Riviste rivista = new Riviste(9771726230018L, "National Geographic", 1888, 150, Riviste.Periodicita.MENSILE);
        catalogoDAO.aggiungiRivista(rivista);

        // Esempio di ricerca di un elemento per ISBN
        long isbnDaCercare = 9788804678007L;
        Object elemento = catalogoDAO.cercaElementoPerISBN(isbnDaCercare);
        System.out.println("Elemento trovato: " + elemento);

        // Esempio di ricerca di elementi per anno di pubblicazione
        int annoPubblicazioneDaCercare = 1952;
        List<Object> elementiPerAnnoPubblicazione = catalogoDAO.cercaElementoPerAnnoPubblicazione(annoPubblicazioneDaCercare);
        System.out.println("Elementi trovati per anno di pubblicazione " + annoPubblicazioneDaCercare + ": " + elementiPerAnnoPubblicazione);



        em.close();
        emf.close();
    }
}