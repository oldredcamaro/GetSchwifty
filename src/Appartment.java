package ua.kiev.prog;

/**
 * Created by Ilya on 07.02.2017.
 */
public class Appartment {

    private long appId;
    private String appDistrict;
    private String appAdress;
    private float appSquare;
    private int appRoomAmount;
    private float appPrice;

    public Appartment() {
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public String getAppDistrict() {
        return appDistrict;
    }

    public void setAppDistrict(String appDistrict) {
        this.appDistrict = appDistrict;
    }

    public String getAppAdress() {
        return appAdress;
    }

    public void setAppAdress(String appAdress) {
        this.appAdress = appAdress;
    }

    public float getAppSquare() {
        return appSquare;
    }

    public void setAppSquare(float appSquare) {
        this.appSquare = appSquare;
    }

    public int getAppRoomAmount() {
        return appRoomAmount;
    }

    public void setAppRoomAmount(int appRoomAmount) {
        this.appRoomAmount = appRoomAmount;
    }

    public float getAppPrice() {
        return appPrice;
    }

    public void setAppPrice(float appPrice) {
        this.appPrice = appPrice;
    }
}
