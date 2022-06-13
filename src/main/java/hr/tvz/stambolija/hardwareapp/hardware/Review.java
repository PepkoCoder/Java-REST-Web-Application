package hr.tvz.stambolija.hardwareapp.hardware;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="REVIEW")
public class Review implements Serializable {

    @Id
    @Column(name="ID")
    @GeneratedValue
    private long id;

    @Column(name="TITLE")
    private String title;

    @Column(name="TEXT")
    private String text;

    @Column(name="RATING")
    private int rating;

    @ManyToOne
    @JoinColumn(name="HARDWARE_CODE")
    private Hardware hardware;

    @Override
    public String toString(){
        return title + " - " + rating + "/5";
    }

}
