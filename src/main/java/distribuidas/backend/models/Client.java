package distribuidas.backend.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
    @OneToOne
    @JoinColumn(name = "numeroPais")
    private Country country;
    @Enumerated(EnumType.STRING)
    @Column(name = "admitido", length = 2)
    private Admited admited;
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria")
    private Category category;
    @OneToOne
    @JoinColumn(name = "verificador", nullable=false)
    private Employee authorizedBy;
}
