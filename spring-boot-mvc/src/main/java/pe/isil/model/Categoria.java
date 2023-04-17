package pe.isil.model;


import lombok.Data;

import javax.persistence.*;

@Table(name = "CATEGORIA")
@Entity
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategoria")
    private Integer id;

    @Column(length = 50)
    private String nombre;


    @Column(length = 150)
    private String descripcion;
}
