package distribuidas.backend.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "subastadores")
@Getter @Setter
public class Auctioner {
    /*
        create table subastadores(
            identificador int not null,
            matricula varchar(15),
            region varchar(50),
            constraint pk_subastadores primary key (identificador),
            constraint fk_subastadores_personas foreign key (identificador) references personas
        )
        go
    */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "identificador", nullable = false)
    private int id;
    @OneToOne
    @JoinColumn(name = "identificador")
    private User user;
    @Column(name = "matricula", columnDefinition = "varchar(15)")
    private String registration;
    @Column(name = "region", columnDefinition = "varchar(50)")
    private String region;
}
