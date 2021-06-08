package distribuidas.backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "asistentes")
@Getter @Setter
public class Assistant {
    /*
        create table asistentes(
            identificador int not null identity,
            numeroPostor int not null,
            cliente int not null,
            subasta int not null
            constraint pk_asistentes primary key (identificador),
            constraint fk_asistentes_clientes foreign key (cliente) references clientes,
            constraint fk_asistentes_subasta foreign key (subasta) references subastas
        )
        go
    */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "identificador", nullable = false)
    private int assistantId;
    @Column(name = "numeroPostor", nullable = false)
    private int bidderNumber;
    @OneToOne
    @JoinColumn(name = "cliente", nullable = false)
    private Client client;
    @OneToOne
    @JoinColumn(name = "subasta", nullable = false)
    private Auction auction;
}
