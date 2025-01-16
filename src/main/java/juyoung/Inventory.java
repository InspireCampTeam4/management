package juyoung;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Inventory {
    private String category;
    private String name;
    private String description;
    private int itemCode;
    private int quantity;
    private double inventoryValuePerUnit;
    private int profit;
}
