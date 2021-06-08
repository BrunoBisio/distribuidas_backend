package distribuidas.backend.models;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import distribuidas.backend.enums.Admited;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "itemsCatalogo")
@Getter @Setter
public class CatalogItem {
    /*
        create table itemsCatalogo(
            identificador int not null identity,
            catalogo int not null,
            producto int not null,
            precioBase decimal(18,2) not null constraint chkPB check (precioBase > 0.01),
            comision decimal(18,2) not null constraint chkC check (comision > 0.01),
            subastado varchar(2) constraint chkS check (subastado in ('si','no')),
            constraint pk_itemsCatalogo primary key (identificador),
            constraint fk_itemsCatalogo_catalogos foreign key (catalogo) references catalogos,
            constraint fk_itemsCatalogo_productos foreign key (producto) references productos
        )
        go
    */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "identificador", nullable = false)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "catalogo", nullable = false)
    private Catalog catalog;
    @OneToOne
    @JoinColumn(name = "producto", nullable = false)
    private Product product;
    @Column(name = "precioBase", columnDefinition = "decimal(18,2) not null check (precioBase > 0.01)")
    private BigDecimal basePrice;
    @Column(name = "comision", columnDefinition = "decimal(18,2) not null check (comision > 0.01)")
    private BigDecimal commission;
    @Enumerated(EnumType.STRING)
    @Column(name = "subastado", columnDefinition = "varchar(2) check (subastado in ('si','no'))")
    private Admited auctioned;
}
