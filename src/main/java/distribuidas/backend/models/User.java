package distribuidas.backend.models;

import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import distribuidas.backend.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "personas")
@Getter @Setter @NoArgsConstructor
public class User implements Serializable {
    /*
        create table personas(
            identificador int not null identity,
            documento varchar(20) not null,
            nombre varchar(150) not null,
            direccion varchar(250),
            estado varchar(15) constraint chkEstado check (estado in ('activo', 'incativo')),
            foto varbinary(max)
            constraint pk_personas primary key (identificador)
        )
        go
    */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "identificador", nullable = false)
    private int userId;
    @Column(name = "documento", length = 20, nullable = false)
    private String identityNumber;
    @Column(name = "nombre", length = 150, nullable = false)
    private String name;
    @Column(name = "direccion", length = 250)
    private String address;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", columnDefinition = "varchar(15) check (estado in ('activo', 'inactivo'))")
    private Status status;
    @Column(name = "foto")
    private String picture;
    @Column(name = "mail")
    private String email;
    @Column(name = "contrasenia")
    private String password;
    @Column(name = "telefono", length = 20)
    private String phoneNumber;
}
