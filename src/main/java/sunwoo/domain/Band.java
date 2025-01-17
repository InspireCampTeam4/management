package sunwoo.domain;

import lombok.Data;
import lombok.Getter;
import sunwoo.service.IdGenerator;

@Getter
public class Band extends Item{
    private String bandName;
    private int memberCapacity;

    public Band(String bandName, int memberCapacity){
        super(IdGenerator.generateBandId(bandName));
        this.bandName = bandName;
        this.memberCapacity = memberCapacity;
    }


}
