package distribuidas.backend.models;

import javax.persistence.Entity;
import javax.persistence.Table;

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
    private int id;
    private Product product;
    private String uri;
}
