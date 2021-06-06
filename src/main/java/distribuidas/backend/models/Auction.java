package distribuidas.backend.models;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import distribuidas.backend.enums.Admited;
import distribuidas.backend.enums.Category;
import distribuidas.backend.enums.State;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "subastas")
@Getter @Setter
public class Auction {
    /*
    create table subastas(
	identificador int not null identity,
	--las subastas tiene al menos 10 dias de anticipación al momento de crearlas.
	fecha date constraint chkFecha check (fecha > dateAdd(dd, 10, getdate())),
	hora time not null,
	estado varchar(10) constraint chkES check (estado in ('abierta','carrada')),
	subastador int null,
	--direccion de don de se desarrolla el evento.
	ubicacion varchar(350) null,
	capacidadAsistentes int null,
	--caracteristica del lugar donde se hacen las subastas
	tieneDeposito varchar(2) constraint chkTD check(tieneDeposito in ('si','no')),
	--caracteristica del lugar donde se hacen las subastas
	seguridadPropia varchar(2) constraint chkSP check(seguridadPropia in ('si','no')),
	categoria varchar(10) constraint chkCS check (categoria in ('comun', 'especial', 'plata', 'oro', 'platino')),
	constraint pk_subastas primary key (identificador),
	constraint fk_subastas_subastadores foreign key (subastador) references subastadores(identificador)
     )
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "identificador", nullable = false)
    private int id;
    @Column(name = "fecha") //, columnDefinition = "date check (fecha > dateAdd(dd, 10, getdate()))")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date openDate;
    @Column(name = "hora", nullable = false)
    private Time hour;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", columnDefinition = "varchar(10) check (estado in ('abierta','cerrada'))")
    private State state;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "subastador", referencedColumnName="identificador")
    private Auctioner auctioner;
    // --direccion de donde se desarrolla el evento.
    @Column(name = "ubicacion", columnDefinition = "VARCHAR(350) null")
    private String address;
    @Column(name = "capacidadAsistentes")
    private int maxAssistants;
    // --caracteristica del lugar donde se hacen las subastas
    @Enumerated(EnumType.STRING)
    @Column(name = "tieneDeposito", columnDefinition = "varchar(2) check (tieneDeposito in ('si','no'))")
    private Admited hasStorage;
    @Enumerated(EnumType.STRING)
    @Column(name = "seguridadPropia", columnDefinition = "varchar(2) check (seguridadPropia in ('si','no'))")
    private Admited hasSecurity;
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", columnDefinition = "varchar(10) CHECK (categoria in ('comun', 'especial', 'plata', 'oro', 'platino')) default 'comun'")
    private Category category;
    @Column(name = "nombreDeSubasta", nullable = false)
    private String name;
    @Column(name = "descripcionDeSubasta", nullable = false)
    private String description;
    @Column(name = "fotoDeSubasta")
    private String photo;
    @Transient
    private List<Product> products;
}
