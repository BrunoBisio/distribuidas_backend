package distribuidas.backend.models;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "fotos")
@Getter @Setter
public class Photo {
    /*
        create table fotos(
            identificador int not null identity,
            producto int not null,
            foto varbinary (max) not null,
            constraint pk_fotos primary key (identificador),
            constraint fk_fotos_productos foreign key (producto) references productos(identificador)
        )
        go
    */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "identificador", nullable = false)
    private int id;
    @OneToOne
    @JoinColumn(name = "producto")
    private Product product;
    private String uri;
}
