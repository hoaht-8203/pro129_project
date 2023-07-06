package carManage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hoaht
 */
public class Brand {
    private String brandId;
    private String brandName;
    private String soundBrand;
    private double price;

    public Brand() {
    }

    public Brand(String brandId, String brandName, String soundBrand, double price) {
        brandId = brandId.trim().toUpperCase();
        brandName = brandName.trim();
        soundBrand = soundBrand.trim();
        
        this.brandId = brandId;
        this.brandName = brandName;
        this.soundBrand = soundBrand;
        this.price = price;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSoundBrand() {
        return soundBrand;
    }

    public void setSoundBrand(String soundBrand) {
        this.soundBrand = soundBrand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return brandId + ", " + brandName + ", " + soundBrand + ": " + price;
    }
}
