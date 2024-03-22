
package dao;
import entities.Libri;
import entities.Prestito;
import entities.Riviste;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class CatalogoDAO {
    private EntityManager em;

    public CatalogoDAO(EntityManager em) {
        this.em = em;
    }

    // Aggiunta di un libro al catalogo
    public void aggiungiLibro(Libri libro) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(libro);
        transaction.commit();
    }

    // Aggiunta di una rivista al catalogo
    public void aggiungiRivista(Riviste rivista) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(rivista);
        transaction.commit();
    }

    // Rimozione di un elemento del catalogo dato un codice ISBN
    public void rimuoviElementoPerISBN(long isbn) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Libri libro = em.find(Libri.class, isbn);
        if (libro != null) {
            em.remove(libro);
            transaction.commit();
        } else {
            Riviste rivista = em.find(Riviste.class, isbn);
            if (rivista != null) {
                em.remove(rivista);
                transaction.commit();
            } else {
                System.out.println("Nessun elemento trovato con ISBN: " + isbn);
                transaction.rollback();
            }
        }
    }

    // Ricerca per ISBN
    public Object cercaElementoPerISBN(long isbn) {
        Libri libro = em.find(Libri.class, isbn);
        if (libro != null) {
            return libro;
        } else {
            return em.find(Riviste.class, isbn);
        }
    }

    // Ricerca per anno pubblicazione
    public List<Object> cercaElementoPerAnnoPubblicazione(int annoPubblicazione) {
        Query query = em.createQuery("SELECT e FROM Libro e WHERE e.annoPubblicazione = :anno", Libri.class);
        query.setParameter("anno", annoPubblicazione);
        List<Object> libri = query.getResultList();

        query = em.createQuery("SELECT e FROM Rivista e WHERE e.annoPubblicazione = :anno", Riviste.class);
        query.setParameter("anno", annoPubblicazione);
        List<Object> riviste = query.getResultList();

        libri.addAll(riviste);
        return libri;
    }

    // Ricerca per autore
    public List<Object> cercaElementoPerAutore(String autore) {
        Query query = em.createQuery("SELECT e FROM Libro e WHERE e.autore = :autore", Libri.class);
        query.setParameter("autore", autore);
        List<Object> libri = query.getResultList();

        query = em.createQuery("SELECT e FROM Rivista e WHERE e.autore = :autore", Riviste.class);
        query.setParameter("autore", autore);
        List<Object> riviste = query.getResultList();

        libri.addAll(riviste);
        return libri;
    }

    // Ricerca per titolo o parte di esso
    public List<Object> cercaElementoPerTitolo(String titolo) {
        Query query = em.createQuery("SELECT e FROM Libro e WHERE e.titolo LIKE :titolo", Libri.class);
        query.setParameter("titolo", "%" + titolo + "%");
        List<Object> libri = query.getResultList();

        query = em.createQuery("SELECT e FROM Rivista e WHERE e.titolo LIKE :titolo", Riviste.class);
        query.setParameter("titolo", "%" + titolo + "%");
        List<Object> riviste = query.getResultList();

        libri.addAll(riviste);
        return libri;
    }

    // Ricerca degli elementi attualmente in prestito dato un numero di tessera utente
    public List<Prestito> cercaElementiInPrestitoPerTesseraUtente(int numeroTessera) {
        Query query = em.createQuery("SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :numeroTessera AND p.dataRestituzioneEffettiva IS NULL", Prestito.class);
        query.setParameter("numeroTessera", numeroTessera);
        return query.getResultList();
    }

    // Ricerca di tutti i prestiti scaduti e non ancora restituiti
    public List<Prestito> cercaPrestitiScadutiENonRestituiti() {
        LocalDate oggi = LocalDate.now();
        Query query = em.createQuery("SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < :oggi AND p.dataRestituzioneEffettiva IS NULL", Prestito.class);
        query.setParameter("oggi", oggi);
        return query.getResultList();
    }
}
