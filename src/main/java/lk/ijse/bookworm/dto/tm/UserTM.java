package lk.ijse.bookworm.dto.tm;

import javafx.scene.control.Button;

public class UserTM {
    private String id;
    private String name;
    private String address;
    private String bDy;
    private String tp;
    private Button action;

    public UserTM() {}

    public UserTM(String id, String name, String address, String bDy, String tp, Button action) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.bDy = bDy;
        this.tp = tp;
        this.action = action;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getbDy() {
        return bDy;
    }

    public void setbDy(String bDy) {
        this.bDy = bDy;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public Button getAction() {
        return action;
    }

    public void setAction(Button action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "UserTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", bDy='" + bDy + '\'' +
                ", tp='" + tp + '\'' +
                ", action=" + action +
                '}';
    }
}
