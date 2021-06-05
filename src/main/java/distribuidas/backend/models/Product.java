package distribuidas.backend.models;

import javax.persistence.*;

import distribuidas.backend.enums.Currency;
import distribuidas.backend.enums.State;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "productos")
@Getter @Setter
public class Product {

    @Id
    @Column(name = "identificador", nullable = false)
    private int id;
    private String name;
    private String description;
    private Currency currency;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 15)
    private State state;
    @Transient
    private List<Photo> photos;
}
