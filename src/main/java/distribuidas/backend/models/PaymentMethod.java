package distribuidas.backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import distribuidas.backend.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mediosDePago")
@Getter @Setter
public class PaymentMethod {
    /*
        create table mediosDePago(
            identificador int not null identity,
            tipo varchar(20),
            moneda varchar(10),
            numeroCuenta varchar(18),
            numeroTarjeta varchar(16),
            cvv varchar(4),
            fechaVencimiento varchar(4),
            cliente int not null,
            constraint pk_mediosDePago primary key (identificador),
            constraint fk_mediosDePago_cliente foreign key (cliente) references clientes
        )
        go
    */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "identificador", nullable = false)
    private int id;
    @Column(name = "tipo", length = 20)
    private String tipo;
    @Column(name = "moneda", length = 10)
    private String currency;
    @Column(name = "numeroCuenta", length = 18)
    private String accountNumber;
    @Column(name = "numeroTarjeta", length = 16)
    private String cardNumber;
    @Column(name = "cvv", length = 4)
    private String cvv;
    @Column(name = "fechaVencimiento", length = 5)
    private String expirationDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente")
    private Client client;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Status status;
}
