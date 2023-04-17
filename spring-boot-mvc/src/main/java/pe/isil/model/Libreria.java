package pe.isil.model;

import lombok.Data;

import javax.persistence.*;

@Table(name = "LIBRERIA")
@Entity
@Data
public class Libreria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLibreria")
    private Integer id;

    @Column(length = 50)
    private String nombre;

    @Column(length = 9)
    private String telefono;

    @Column(length = 100)
    private String direccion;

    @Column(length = 50)
    private String Departamento;

    @Column(length = 50)
    private String Provincia;

    @Column(length = 50)
    private String Distrito;


}
