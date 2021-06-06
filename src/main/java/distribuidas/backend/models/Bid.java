package distribuidas.backend.models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import distribuidas.backend.enums.Admited;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pujos")
@Getter @Setter
public class Bid {
    /*
        create table pujos(
            identificador int not null identity,
            asistente int not null,
            item int not null,
            importe decimal(18,2) not null constraint chkI check (importe > 0.01),
            ganador varchar(2) constraint chkG check (ganador in ('si','no')) default 'no',
            constraint pk_pujos primary key (identificador),
            constraint fk_pujos_asistentes foreign key (asistente) references asistentes,
            constraint fk_pujos_itemsCatalogo foreign key (item) references itemsCatalogo
        )
        go
    */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "identificador", nullable = false)
    private int id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "asistente")
    private Assistant assistant;
    @OneToOne
    @MapsId
    @JoinColumn(name = "item")
    private CatalogItem item;
    @Column(name = "importe", columnDefinition = "decimal(18,2) not null check (importe > 0.01)")
    private BigDecimal ammount;
    @Enumerated(EnumType.STRING)
    @Column(name = "ganador", columnDefinition = "varchar(2) check (ganador in ('si','no')) default 'no'")
    private Admited winner;
    @Column(name = "fechaDePujo", nullable = false)
    private Date created;
}