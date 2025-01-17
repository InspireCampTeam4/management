package sunwoo.domain;

import lombok.Data;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import sunwoo.Role;
import sunwoo.service.IdGenerator;

import java.util.EnumMap;
import java.util.Map;


@Getter
public class DiscussionItem extends Item{


    private Band band;
    private User writeBy;
    private Song song;
    @Nullable
    private Map<Role,String> session;


    public DiscussionItem(Band band, User writeBy, Song song){
        super(IdGenerator.generateDiscussionItemId(band.getBandName().toLowerCase()));
        this.band = band;
        this.writeBy = writeBy;
        this.song = song;
        this.session = new EnumMap<>(Role.class);
        for (Role role : Role.values()) {
            session.put(role, null);
        }
    }


}
