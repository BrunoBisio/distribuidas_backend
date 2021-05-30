package distribuidas.backend.models;

import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import distribuidas.backend.enums.Category;
import distribuidas.backend.enums.State;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "subastas")
@Getter
@Setter
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
    private Date openDate;
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 15)
    private State state;
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", length = 15)
    private Category category;
    @OneToMany(fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    @JoinColumn(name = "verificador", nullable=false)
    private List<Product> products;
    private String photo;
    private String name;
}
