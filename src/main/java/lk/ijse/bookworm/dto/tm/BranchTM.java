package lk.ijse.bookworm.dto.tm;

import javafx.scene.control.Button;

public class BranchTM {
    private String id;
    private String name;
    private String address;
    private Button action;

    public BranchTM() {}

    public BranchTM(String id, String name, String address, Button action) {
        this.id = id;
        this.name = name;
        this.address = address;
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

    public Button getAction() {
        return action;
    }

    public void setAction(Button action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "BranchTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", action=" + action +
                '}';
    }
}
