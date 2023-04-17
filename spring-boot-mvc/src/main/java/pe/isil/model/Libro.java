package pe.isil.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "LIBRO")
@Entity
@Data
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLibro")
    private Integer id;

    @Column(length = 13)
    private String isbn;

    @Column(length = 100)
    private String nombre;

    @Column(length = 100)
    private String autor;

    @Column(length = 100)
    private String paginas;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaPublicacion;

    @Column(length = 1)
    private String editorial;

    private boolean disponible;

    @Column(length = 20)
    private String precio;

    @ManyToOne
    @JoinColumn(name = "idCategoria", referencedColumnName = "idCategoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "idLibreria", referencedColumnName = "idLibreria")
    private Libreria libreria;
}
