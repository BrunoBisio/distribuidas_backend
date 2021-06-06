package distribuidas.backend.models;

import java.io.Serializable;

import javax.persistence.*;

import distribuidas.backend.enums.Admited;
import distribuidas.backend.enums.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clientes")
@Getter @Setter @NoArgsConstructor
public class Client implements Serializable {
    /*
        create table clientes(
            identificador int not null,
            numeroPais int,
            admitido varchar(2) constraint chkAdmitido check(admitido in ('si','no')),
            categoria varchar(10) constraint chkCategoria check (categoria in ('comun', 'especial', 'plata', 'oro', 'platino')),
            verificador int not null,
            constraint pk_clientes primary key (identificador),
            constraint fk_clientes_personas foreign key (identificador) references personas,
            constraint fk_clientes_empleados foreign key (verificador) references empleados (identificador),
            constraint fk_clientes_paises foreign key (numeroPais) references paises (numero)
        )
        go
    */
    @Id
    @Column(name = "identificador")
    private int id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "identificador")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "numeroPais")
    private Country country;
    @Enumerated(EnumType.STRING)
    @Column(name = "admitido", columnDefinition = "varchar(2) check(admitido in ('si','no'))")
    private Admited admited;
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", columnDefinition = "varchar(10) check (categoria in ('comun', 'especial', 'plata', 'oro', 'platino'))")
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "verificador", nullable=false)
    private Employee authorizedBy;
}
