package distribuidas.backend.models;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

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
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "identificador", nullable = false)
    private int catalogItemId;
    @Column(name = "precioBase", nullable = false)
    private BigDecimal basePrice;

}
