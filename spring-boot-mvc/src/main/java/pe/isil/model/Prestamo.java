package pe.isil.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "PRESTAMO")
@Entity
@Data
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPrestamo")
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistro;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaDevolucion;

    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idLibro", referencedColumnName = "idLibro")
    private Libro libro;

    private Integer devolucion;


}
