package distribuidas.backend.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.springframework.format.annotation.DateTimeFormat;

import distribuidas.backend.enums.Admited;
import lombok.Getter;
import lombok.Setter;

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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "revisor", nullable = false)
    private Employee employee;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "duenio", nullable = false)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    private Owner owner;
    @Column(name = "nombreDeProducto", nullable = false)
    private String name;
    @Column(name = "descripcionDeProducto", nullable = false)
    private String description;
    @Column(name = "moneda", nullable = false)
    private String currency;
    @Enumerated(EnumType.STRING)
    @Column(name = "aprobado", columnDefinition = "varchar(2) not null default 'no'")
    private Admited approved;
    @Transient
    private List<Photo> photos;
    @Transient
    private BigDecimal price;
}
