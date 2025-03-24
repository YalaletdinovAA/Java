package races;

import lombok.*;

@Getter
@Setter

public class DragRace extends Race {

    public DragRace() {
    }

    public DragRace(int length, String route, int prizePool) {
        super(length, route, prizePool);
    }
}
