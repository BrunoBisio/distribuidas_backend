package distribuidas.backend.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "registroDeSubasta")
@Getter @Setter
public class AuctionRegistry {
    /*
        create table registroDeSubasta(
            identificador int not null identity,
            subasta int not null,
            duenio int not null,
            producto int not null,
            cliente int not null,
            medioDePago int not null,
            importe decimal(18,2) not null constraint chkImportePagado check (importe > 0.01),
            comision decimal(18,2) not null constraint chkComisionPagada check (comision > 0.01),
            constraint pk_registroDeSubasta primary key (identificador),
            constraint fk_registroDeSubasta_subastas foreign key (subasta) references subastas,
            constraint fk_registroDeSubasta_duenios foreign key (duenio) references duenios,
            constraint fk_registroDeSubasta_producto foreign key (producto) references productos,
            constraint fk_registroDeSubasta_cliente foreign key (cliente) references clientes,
            constraint fk_registroDeSubasta_medioDePago foreign key (medioDePago) references mediosDePago
        )
        go
    */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "identificador", nullable = false)
    private int id;
    @OneToOne
    @JoinColumn(name = "subasta", nullable = false)
    private Auction auction;
    @OneToOne
    @JoinColumn(name = "duenio", nullable = false)
    private Owner owner;
    @OneToOne
    @JoinColumn(name = "producto", nullable = false)
    private Product product;
    @OneToOne
    @JoinColumn(name = "cliente", nullable = false)
    private Client client;
    @OneToOne
    @JoinColumn(name = "medioDePago", nullable = false)
    private PaymentMethod paymentMethod;
    @Column(name = "importe", columnDefinition = "decimal(18,2) not null check (importe > 0.01)")
    private BigDecimal ammount;
    @Column(name = "comision", columnDefinition = "decimal(18,2) not null check (comision > 0.01)")
    private BigDecimal commission;
}
