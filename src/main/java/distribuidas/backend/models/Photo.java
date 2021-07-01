package distribuidas.backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "identificador", nullable = false)
    private int id;
    @OneToOne
    @JoinColumn(name = "producto")
    private Product product;
    @Column(name = "foto")
    private String photo;

    public Photo(Product product, String photo) {
        this.product = product;
        this.photo = photo;
    }
}
