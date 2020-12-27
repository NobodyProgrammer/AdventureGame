package Items.Decorator;

import Items.*;
import main.Player;
import main.*;

public class Coffee extends Component {
    public Coffee(Item i, String n, int p) {
        super(i, n, p);
    }

    public int getPrice() {
        return this.price + this.item.getPrice();
    }

    public String getName() {
        String str = this.name + this.item.getName();
        return str;
    }

    public String characterEffect(Player p, Monster m) {
        double value = 10 * item.getAddition();
        p.blood += value;
        p.blooBar.setValue(p.blood);
        p.bloodText.setText("blood:" + p.blood);
        return "blood+" + Double.toString(value);
    }

    public double getAddition() {
        return 1;
    }
}
