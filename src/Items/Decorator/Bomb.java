package Items.Decorator;

import Items.*;
import main.Monster;
import main.Player;

public class Bomb extends Component {
    public Bomb(Item i, String n, int p) {
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
        double attack = m.getProperty("attack");
        m.setProperty("attack", (int) (attack - value));

        m.attackText.setText("attack:" + attack);
        return "monster attack-" + Double.toString(value);
    }

    public double getAddition() {
        return 0;
    }
}
