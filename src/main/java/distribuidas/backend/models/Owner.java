package distribuidas.backend.models;

import distribuidas.backend.enums.Admited;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "fotos")
@Getter @Setter
public class Owner {
    /*
        create table duenios(
            identificador int not null,
            numeroPais int,
            verificaciónFinanciera varchar(2) constraint chkVF check(verificaciónFinanciera in ('si','no')),
            verificaciónJudicial varchar(2) constraint chkVJ check(verificaciónJudicial in ('si','no')),
            calificacionRiesgo int constraint chkCR check(calificacionRiesgo in (1,2,3,4,5,6)),
            verificador int not null
            constraint pk_duenios primary key (identificador),
            constraint fk_duenios_personas foreign key (identificador) references personas,
            constraint fk_duenios_empleados foreign key (verificador) references empleados (identificador)
        )
        go
    */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "identificador", nullable = false)
    private int id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "numeroPais")
    private Country country;
    @Column(name = "verificaciónFinanciera", columnDefinition = "varchar(2) check(verificaciónFinanciera in ('si','no'))")
    private Admited financialVerification;
    @Column(name = "verificaciónJudicial", columnDefinition = "varchar(2) check(verificaciónJudicial in ('si','no'))")
    private Admited legalVerification;
    @Column(name = "calificacionRiesgo", columnDefinition = "check(calificacionRiesgo in (1,2,3,4,5,6))")
    private int riskCalification;
    @OneToOne
    @MapsId
    @JoinColumn(name = "verificador")
    private Employee employee;
}
