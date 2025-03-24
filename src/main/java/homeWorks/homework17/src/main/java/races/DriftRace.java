package races;

import lombok.*;

@Getter
@Setter

public class DriftRace extends Race {

    public DriftRace() {
    }

    public DriftRace(int length, String route, int prizePool) {
        super(length, route, prizePool);
    }

}

