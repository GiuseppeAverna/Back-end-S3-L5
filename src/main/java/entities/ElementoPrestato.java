package entities;

import javax.persistence.*;

@Entity
public class ElementoPrestato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne // un elemento prestato è associato ad un utente
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @ManyToOne // l elemento prestato fa parte di piu prestiti
    @JoinColumn(name = "elemento_prestato_id")
    private ElementoPrestato elementoPrestato;
    private String tipo;
    private Long isbn;
    private String titolo;

    public ElementoPrestato() {}

    public ElementoPrestato(String tipo, Long isbn, String titolo) {
        this.tipo = tipo;
        this.isbn = isbn;
        this.titolo = titolo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    @Override
    public String toString() {
        return "ElementoPrestato{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", isbn=" + isbn +
                ", titolo='" + titolo + '\'' +
                '}';
    }
}
