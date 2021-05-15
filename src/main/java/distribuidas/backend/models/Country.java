package distribuidas.backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "paises")
public class Country {
    /*
        create table paises(
            numero int not null,
            nombre varchar(250) not null,
            nombreCorto varchar(250) null,
            capital varchar(250) not null,
            nacionalidad varchar(250) not null,
            idiomas varchar(150) not null,
            constraint pk_paises primary key (numero)
        )
        go
    */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "numero", nullable = false)
    private int id;
    @Column(name = "nombre", length = 250, nullable = false)
    private String name;
    @Column(name = "nombreCorto", length = 250, nullable = false)
    private String shortName;
    @Column(name = "capital", length = 250, nullable = false)
    private String capital;
    @Column(name = "nacionalidad", length = 250, nullable = false)
    private String nationality;
    @Column(name = "idiomas", length = 150, nullable = false)
    private String languages;
}
