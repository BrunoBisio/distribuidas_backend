package distribuidas.backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "empleados")
@Getter @Setter @NoArgsConstructor
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
    private int employeeId;
    @Column(name = "cargo", length = 100)
    private String position;
    @Column(name = "sector", nullable = false)
    private int sector;
}
