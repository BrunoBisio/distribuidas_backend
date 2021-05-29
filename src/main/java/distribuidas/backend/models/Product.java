package distribuidas.backend.models;

import distribuidas.backend.enums.Currency;
import distribuidas.backend.enums.State;
import distribuidas.backend.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "productos")
@Getter
@Setter
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
}
