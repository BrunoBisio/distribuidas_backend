package distribuidas.backend.models;

import javax.persistence.*;

import distribuidas.backend.enums.Admited;
import distribuidas.backend.enums.Currency;
import distribuidas.backend.enums.State;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "productos")
@Getter @Setter
public class Product {
    /*
        create table productos(
            identificador int not null identity,
            fecha date,
            disponible varchar(2) constraint chkD check (disponible in ('si','no')),
            --se obtiene despues que un empleado realiza la revision.
            descripcionCatalogo varchar(500) null default 'No Posee',
            --url que apunta a un documento PDF firmado que contiene la descripción del producto.
            descripcionCompleta varchar(300) not null,
            revisor int not null,
            duenio int not null,
            constraint pk_productos primary key (identificador),
            constraint fk_productos_empleados foreign key (revisor) references empleados(identificador),
            constraint fk_productos_duenios foreign key (duenio) references duenios(identificador)
        )
        go
    */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "identificador", nullable = false)
    private int id;
    @Column(name = "fecha")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date created;
    @Enumerated(EnumType.STRING)
    @Column(name = "disponible", columnDefinition = "varchar(2) check (disponible in ('si','no'))")
    private Admited available;
    // --se obtiene despues que un empleado realiza la revision.
    @Column(name = "descripcionCatalogo", columnDefinition = "varchar(500) null default 'No Posee'")
    private String catalogDescription;
    @Column(name = "descripcionCompleta", columnDefinition = "varchar(300) not null")
    private String fullDescription;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "revisor", nullable = false)
    private Employee employee;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "duenio", nullable = false)
    private Owner owner;
    @Column(name = "nombreDeProducto", nullable = false)
    private String name;
    @Column(name = "descripcionDeProducto", nullable = false)
    private String description;
    @Column(name = "moneda", nullable = false)
    private String currency;
    @Transient
    private List<Photo> photos;
}
