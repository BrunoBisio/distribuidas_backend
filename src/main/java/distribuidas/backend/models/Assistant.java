package distribuidas.backend.models;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "identificador", nullable = false)
    private int assistantId;
    @Column(name = "numeroPostor", nullable = false)
    private int bidderNumber;
    @OneToOne
    @MapsId
    @JoinColumn(name = "cliente", referencedColumnName = "", nullable = false)
    private Client client;
    @OneToOne
    @MapsId
    @JoinColumn(name = "subasta", nullable = false)
    private Auction auction;
}
