package carManage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import gui.IBrandList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hoaht
 */
public class BrandList extends ArrayList<Brand> implements IBrandList{

    public BrandList() {
        super();
    }

    @Override
    public boolean loadFromFile(String fileName) {
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while(true) {
                line = br.readLine();
                
                if(line == null){
                    break;
                }
                
                String data[] = line.split("[,:]");
                
                String brandId = data[0];
                String brandName = data[1];
                String brandSound = data[2];
                double price = Double.parseDouble(data[3]);
                
                Brand b = new Brand(brandId, brandName, brandSound, price);
                this.add(b);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }

    @Override
    public boolean saveToFile(String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Brand b : this) {
                bw.write(b.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return false;
    }

    @Override
    public int searchId(String id) {
        for (Brand b : this) {
            if(b.getBrandId().equalsIgnoreCase(id)){
                return this.indexOf(b);
            }
        }
        
        return -1;
    }

    @Override
    public Brand getUserChoice() {
        Menu m = new Menu();
        System.out.println("Chose brand: ");
        Brand b = (Brand) m.ref_getChoice(this);
        
        return b;
    }

    @Override
    public void addBrand() {
        String brandId, brandName, brandSound;
        double price;
        
        boolean duplicateId = false;
        do {
            brandId = Inputter.inputNonBlankStr("Enter brand ID: ");
            duplicateId = isDuplicateId(brandId);
        }while(duplicateId == true);
        
        brandName = Inputter.inputNonBlankStr("Enter brand name: ");
        brandSound = Inputter.inputNonBlankStr("Enter brand sound: ");
        price = Inputter.inputDouble("Enter price: ");
        
        Brand b = new Brand(brandId, brandName, brandSound, price);
        this.add(b);
        System.out.println("Add " + brandId + " brand succesfully!");
    }

    @Override
    public void updateBrand() {
        if(this.isEmpty()){
            System.out.println("Empty list! update can not be performed!");
        }else{
            String brandId = Inputter.inputStr("Enter brand ID to update: ");
            int pos = searchId(brandId);
            if(pos == -1){
                System.err.println("This brand is not exsited!");
            }else{
                Brand brand = this.get(pos);
                String newBrandName, newBrandSound;
                double newPrice;

                newBrandName = Inputter.inputNonBlankStr("Old brand name: " + brand.getBrandName() + ", New brand name: ");
                newBrandSound = Inputter.inputNonBlankStr("Old brand sound " + brand.getSoundBrand() + ", New brand sound: ");
                newPrice = Inputter.inputDouble("Old price: " + brand.getPrice() + ", New price: ");

                brand.setBrandName(newBrandName);
                brand.setSoundBrand(newBrandSound);
                brand.setPrice(newPrice);
                System.out.println("Update " + brand.getBrandId() + " brand successfully");
            }
        }
    }

    @Override
    public void listBrands() {
        if (this.isEmpty()) {
            System.out.println("Empty list!");
        }else{
            System.out.println("--------------------------------");
            for (Brand b : this) {
                System.out.println(b.toString());
            }
            System.out.println("--------------------------------");
        }
    }

    @Override
    public boolean isDuplicateId(String id) {
        int searchId = searchId(id);
        
        if(searchId!=-1){
            return true;
        }
        
        return false;
    }
    
    
}
