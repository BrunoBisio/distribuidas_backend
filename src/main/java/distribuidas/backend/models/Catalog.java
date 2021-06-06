package distribuidas.backend.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "catalogos")
@Getter @Setter
public class Catalog {
    /*
        create table catalogos(
            identificador int not null identity,
            descripcion varchar(250) not null,
            subasta int null,
            responsable int not null,
            constraint pk_catalogos primary key (identificador),
            constraint fk_catalogos_empleados foreign key (responsable) references empleados(identificador),
            constraint fk_catalogos_subastas foreign key (subasta) references subastas(identificador),
        )
        go
    * */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "identificador", nullable = false)
    private int id;
    @Column(name = "descripcion", columnDefinition = "varchar(250) not null")
    private String description;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "subasta")
    private Auction auction;
    @OneToOne
    @JoinColumn(name = "responsable")
    private Employee employee;
}
