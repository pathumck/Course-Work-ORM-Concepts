package lk.ijse.bookworm.dto;

public class UserDTO {
    private String userId;
    private String userName;
    private String userAddress;
    private String userBirthDay;
    private String userTp;

    public UserDTO() {
    }

    public UserDTO(String userId, String userName, String userAddress, String userBirthDay, String userTp) {
        this.userId = userId;
        this.userName = userName;
        this.userAddress = userAddress;
        this.userBirthDay = userBirthDay;
        this.userTp = userTp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserBirthDay() {
        return userBirthDay;
    }

    public void setUserBirthDay(String userBirthDay) {
        this.userBirthDay = userBirthDay;
    }

    public String getUserTp() {
        return userTp;
    }

    public void setUserTp(String userTp) {
        this.userTp = userTp;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userBirthDay='" + userBirthDay + '\'' +
                ", userTp='" + userTp + '\'' +
                '}';
    }
}
