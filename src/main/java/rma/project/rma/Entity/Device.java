package rma.project.rma.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by gerli on 06/04/2018.
 */
public class Device {

    private int id;
    private String deviceId;
    private String productId;
    private String productCat;
    private String productName;

    public Device(){

    }
    public Device(String productName, String productId, String productCat, String deviceId){
        this.productId = productId;
        this.productCat = productCat;
        this.productName = productName;
        this.deviceId = deviceId;
    }

    public int getId() {
        return id;
    }
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductCat() {
        return productCat;
    }

    public void setProductCat(String productCat) {
        this.productCat = productCat;
    }


}
