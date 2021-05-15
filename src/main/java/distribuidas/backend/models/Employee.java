package distribuidas.backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "empleados")
public class Employee {
    /*
        create table empleados(
            identificador int not null,
            cargo varchar(100),
            sector int null,
            constraint pk_empleados primary key (identificador)
        )
        go
    */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "identificador", nullable = false)
    private User user;
    @Column(name = "cargo", length = 100)
    private String position;
    @Column(name = "sector", nullable = false)
    private int sector;
}
