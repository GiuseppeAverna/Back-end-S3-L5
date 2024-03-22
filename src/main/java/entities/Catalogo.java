package entities;


import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    public List<Libri> libri;
    public List<Riviste> riviste;
    public Catalogo() {
        this.libri = new ArrayList<>();
        this.riviste = new ArrayList<>();
    }
    public void aggiungiLibri(List<Libri> listaLibri) {
        this.libri.addAll(listaLibri);
    }

    public void aggiungiRiviste(List<Riviste> listaRiviste) {
        this.riviste.addAll(listaRiviste);
    }



    public void aggiungiLibro(Libri libro) {
        libri.add(libro);
    }
    public void aggiungiRivista(Riviste rivista) {
        riviste.add(rivista);
    }

    public void rimuoviElementoPerISBN(long isbn) {
        libri.removeIf(libro -> libro.getIsbn() == isbn);
        riviste.removeIf(rivista -> rivista.getIsbn() == isbn);
    }
    public void cercaElementoPerISBN(long isbn) {

        boolean libroTrovato = false;
        for (Libri libro : libri) {
            if (libro.getIsbn() == isbn) {
                System.out.println("Libro trovato:");
                System.out.println(libro);
                libroTrovato = true;
                break;
            }
        }


        boolean rivistaTrovata = false;
        for (Riviste rivista : riviste) {
            if (rivista.getIsbn() == isbn) {
                System.out.println("Rivista trovata:");
                System.out.println(rivista);
                rivistaTrovata = true;
                break;
            }
        }


        if (!libroTrovato && !rivistaTrovata) {
            System.out.println("Nessun elemento trovato con ISBN: " + isbn);
        }
    }
    public void cercaElementoPerAnnoPubblicazione(int annoPubblicazione) {

        boolean libroTrovato = false;
        for (Libri libro : libri) {
            if (libro.getAnnoPubblicazione() == annoPubblicazione) {
                System.out.println("Libro trovato:");
                System.out.println(libro);
                libroTrovato = true;
            }
        }


        boolean rivistaTrovata = false;
        for (Riviste rivista : riviste) {
            if (rivista.getAnnoPubblicazione() == annoPubblicazione) {
                System.out.println("Rivista trovata:");
                System.out.println(rivista);
                rivistaTrovata = true;
            }
        }


        if (!libroTrovato && !rivistaTrovata) {
            System.out.println("Nessun elemento trovato per l'anno: " + annoPubblicazione);
        }
    }
    public void cercaElementoPerAutore(String autore) {

        boolean libroTrovato = false;
        for (Libri libro : libri) {
            if (libro.getAutore().contains(autore)) {
                System.out.println("Libro trovato:");
                System.out.println(libro);
                libroTrovato = true;
            }
        }


        if (!libroTrovato) {
            System.out.println("Nessun libro trovato per l'autore: " + autore);
        }
    }





    public static void main(String[] args) {
            List<Libri> libri = new ArrayList<>();
            libri.add(new Libri(5465656578007L, "Il signore degli anelli", 1954, 1170, "J.R.R. Tolkien", "Fantasy"));
            libri.add(new Libri(9788804620830L, "Il nome della rosa", 1980, 553, "Umberto Eco", "Romanzo"));
            libri.add(new Libri(9788806222624L, "La ragazza di Bube", 1960, 285, "Carlo Cassola", "Romanzo"));
            libri.add(new Libri(9788804678007L, "1984", 1949, 328, "George Orwell", "Distopia"));
            libri.add(new Libri(9788804678007L, "Il vecchio e il mare", 1952, 127, "Ernest Hemingway", "Romanzo"));
            libri.add(new Libri(9788804678007L, "La metamorfosi", 1915, 68, "Franz Kafka", "Romanzo"));
            libri.add(new Libri(9788804678007L, "L'isola del tesoro", 1883, 336, "Robert Louis Stevenson", "Avventura"));
            libri.add(new Libri(9788804678007L, "Don Chisciotte della Mancia", 1605, 863, "Stephen King", "Romanzo"));
            libri.add(new Libri(9788804678007L, "Cime tempestose", 1847, 398, "Emily Brontë", "Romanzo"));
            libri.add(new Libri(9788804678007L, "Moby Dick", 1851, 654, "Herman Melville", "Romanzo"));



            System.out.println("Libri:");
            for (Libri libro : libri) {
                System.out.println(libro);
            }

            List<Riviste> riviste = new ArrayList<>();
            riviste.add(new Riviste(9771726230018L, "National Geographic", 1888, 150, Riviste.Periodicita.MENSILE));
            riviste.add(new Riviste(9772281155000L, "The New Yorker", 1925, 80, Riviste.Periodicita.SETTIMANALE));
            riviste.add(new Riviste(9771122028108L, "Scientific American", 1845, 80, Riviste.Periodicita.MENSILE));
            riviste.add(new Riviste(9771726230018L, "Time", 1923, 60, Riviste.Periodicita.SETTIMANALE));
            riviste.add(new Riviste(9772281155000L, "Vanity Fair", 1913, 100, Riviste.Periodicita.SEMESTRALE));
            riviste.add(new Riviste(9771122028108L, "The Economist", 1843, 90, Riviste.Periodicita.SETTIMANALE));
            riviste.add(new Riviste(9771726230018L, "Forbes", 1917, 70, Riviste.Periodicita.SEMESTRALE));
            riviste.add(new Riviste(9772281155000L, "National Geographic Traveler", 1984, 50, Riviste.Periodicita.MENSILE));
            riviste.add(new Riviste(9771122028108L, "Rolling Stone", 1967, 80, Riviste.Periodicita.MENSILE));
            riviste.add(new Riviste(9771726230018L, "Nature", 1869, 40, Riviste.Periodicita.SETTIMANALE));


            System.out.println("\nRiviste");
            for (Riviste rivista : riviste) {
                System.out.println(rivista);



        }

        Catalogo catalogo = new Catalogo();
        catalogo.aggiungiLibri(libri);
        catalogo.aggiungiRiviste(riviste);

        // Aggiungi elementi
        catalogo.aggiungiLibro(new Libri(5465656578007L, "Italian Spiderman", 2000, 1170, "Youtube", "Action"));
        catalogo.aggiungiLibro(new Libri(5465656578008L, "Jojo's bizzarre adventure", 2001, 1170, "Netflix", "Anime"));
        catalogo.aggiungiRivista(new Riviste(9771726230018L, "Metro", 2005, 600, Riviste.Periodicita.MENSILE));
        System.out.println("elementi aggiunti:");
        catalogo.libri.forEach(System.out::println);
        catalogo.riviste.forEach(System.out::println);

        // Rimuovi un libro dal catalogo
        long isbnLibroDaRimuovere = 5465656578007L;
        catalogo.rimuoviElementoPerISBN(isbnLibroDaRimuovere);

        // Rimuovi una rivista dal catalogo
        long isbnRivistaDaRimuovere = 9771726230018L;
        catalogo.rimuoviElementoPerISBN(isbnRivistaDaRimuovere);



        long isbnDaCercare = 5465656578008L;
        catalogo.cercaElementoPerISBN(isbnDaCercare);

        int annoPubblicazioneDaCercare = 1923;
        catalogo.cercaElementoPerAnnoPubblicazione(annoPubblicazioneDaCercare);

        System.out.println("Ricerca per autore:");
        catalogo.cercaElementoPerAutore("J.R.R. Tolkien");
        catalogo.cercaElementoPerAutore("Umberto Eco");
        catalogo.cercaElementoPerAutore("Stephen King");








    }


}