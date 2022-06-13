package hr.tvz.stambolija.hardwareapp.hardware;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="HARDWARE")
public class Hardware {

    @Id
    @Column(name="CODE")
    private String code;

    @Column(name="NAME")
    private String name;

    @Column(name="PRICE")
    private float price;

    @Column(name="TYPE")
    @Enumerated(EnumType.STRING)
    private HardwareType type;

    @Column(name="AMOUNTINSTORAGE")
    private int amountInStorage;

    @OneToMany(mappedBy="hardware",fetch=FetchType.EAGER)
    private List<Review> reviewList;


    public Hardware(){

    }

    public Hardware(String code, String name, float price, HardwareType type, int amountInStorage){
        this.code = code;
        this.name = name;
        this.price = price;
        this.type = type;
        this.amountInStorage = amountInStorage;
    }

    @Override
    public String toString(){
        return name + " - " + code;
    }

}
