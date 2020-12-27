package Items.Decorator;

import Items.Item;
import main.Player;
import main.*;

public class Pork extends Component {
    public Pork(Item i, String n, int p) {
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
        p.attack += value;
        p.attackText.setText("attack:" + p.attack);
        return "attack+" + Double.toString(value);

    }

    public double getAddition() {
        return 0;
    }
}