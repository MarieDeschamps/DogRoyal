import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CardDB {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id = null;

    int value;
    boolean type;
    boolean pickable;
    int player;

    public CardDB(){

    }

    public CardDB(int value, boolean type, boolean pickable, int player) {
        this.value = value;
        this.type = type;
        this.pickable = pickable;
        this.player = player;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public boolean isPickable() {
        return pickable;
    }

    public void setPickable(boolean pickable) {
        this.pickable = pickable;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }


}
