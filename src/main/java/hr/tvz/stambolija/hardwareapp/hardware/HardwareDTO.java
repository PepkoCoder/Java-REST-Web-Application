package hr.tvz.stambolija.hardwareapp.hardware;

import lombok.Data;

@Data
public class HardwareDTO {

    private final String name;
    private final float price;
    private final String code;

    public HardwareDTO (String name, float price, String code) {
        this.name = name;
        this.price = price;
        this.code = code;
    }

    @Override
    public String toString(){
        return "HardwareDTO{name=" + this.name + "" +
                ",code=" + this.code +
                ",price=" + this.price + '}';
    }
}
