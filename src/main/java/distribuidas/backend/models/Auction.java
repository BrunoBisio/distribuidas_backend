package distribuidas.backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "subastas")
@Getter @Setter
public class Auction {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "identificador", nullable = false)
    private int id;
    
    public Assistant getAuctioner() {
        return null;
    }
    
}
