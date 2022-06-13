package hr.tvz.stambolija.hardwareapp.hardware;

import lombok.Data;

@Data
public class ReviewDTO {

    public String title;
    public String text;
    public int rating;

    public ReviewDTO(String title, String text, int rating){
        this.title = title;
        this.rating = rating;
    }

    @Override
    public String toString(){
        return "ReviewDTO{title=" + this.title + ", text=" + this.text + ", rating=" + this.rating + "}";
    }

}
