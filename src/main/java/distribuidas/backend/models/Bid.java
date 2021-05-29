package distribuidas.backend.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import distribuidas.backend.enums.Admited;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pujas")
@Getter @Setter
public class Bid {
    /*
        create table pujas(
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
    private int bidId;
    @OneToOne
    @JoinColumn(name = "asistente")
    private Assistant assistant;
    @OneToOne
    @JoinColumn(name = "item")
    private CatalogItem item;
    private BigDecimal ammount;
    private Admited winner;
}