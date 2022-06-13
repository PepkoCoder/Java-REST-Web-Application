package hr.tvz.stambolija.hardwareapp.hardware;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

@Data
public class HardwareCommand {


    @NotBlank(message = "Code must not be empty")
    @Pattern(message = "Code must have 8 digits", regexp="[\\d]{8}")
    private String code;

    @NotBlank(message = "Name must not be empty")
    private String name;

    @NotNull(message = "Price must be entered")
    @PositiveOrZero(message = "Price must be a positive integer")
    private float price;

    @NotNull(message = "Hardware Type must be chosen")
    private HardwareType type;

    @NotNull(message = "Amount must be entered")
    @PositiveOrZero(message = "Amount must be a positive integer")
    private int stock;

    public HardwareCommand(String code, String name, float price, HardwareType type, int stock){
        this.code = code;
        this.name = name;
        this.price = price;
        this.type = type;
        this.stock = stock;
    }

}
