package races;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CasualRace extends Race {

    public CasualRace() {
    }

    public CasualRace(int length, String route, int prizePool) {
        super(length, route, prizePool);
    }
}