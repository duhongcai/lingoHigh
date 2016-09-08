package lingoHigh.entry;

import java.io.Serializable;

/**
 * Created by DuHongcai on 2016/9/8.
 */
public class Account implements Serializable {
    private String id;
    private String name;
    private String money;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
