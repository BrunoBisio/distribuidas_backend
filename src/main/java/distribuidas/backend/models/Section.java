package distribuidas.backend.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "productos")
@Getter @Setter
public class Section {
    /*
        create table sectores(
            identificador int not null identity,
            nombreSector varchar(150) not null,
            codigoSector varchar(10) null,
            responsableSector int null,
            constraint pk_sectores primary key (identificador),
            constraint fk_sectores_empleados foreign key (responsableSector) references empleados
        )
        go
    */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "identificador", nullable = false)
    private int id;
    @Column(name = "nombreSector", length = 150)
    private String name;
    @Column(name = "codigoSector", length = 10)
    private String code;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "responsableSector")
    private Employee supervisor;
}
