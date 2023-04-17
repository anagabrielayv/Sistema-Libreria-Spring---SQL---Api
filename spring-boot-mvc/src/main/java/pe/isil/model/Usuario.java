package pe.isil.model;

import lombok.Data;

import javax.persistence.*;

@Table(name = "USUARIO")
@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Integer id;

    @Column(length = 50)
    private String nombres;

    @Column(length = 50)
    private String apellidos;

    @Column(length = 100)
    private String nom_completo;

    @Column(length = 50)
    private String email;

    @Column(length = 250)
    private String contrasena;

    @Column(length = 50)
    private String rol;

    @PrePersist
    @PreUpdate
    void asignarNombreCompleto(){
        nom_completo = nombres + " " + apellidos;
    }

}
