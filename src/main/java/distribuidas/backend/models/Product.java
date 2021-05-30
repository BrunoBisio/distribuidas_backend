package distribuidas.backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import distribuidas.backend.enums.Currency;
import distribuidas.backend.enums.State;
import lombok.Getter;
import lombok.Setter;

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
}
