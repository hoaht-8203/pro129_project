/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import carManage.Brand;

/**
 *
 * @author hoaht
 */
public interface IBrandList {
    public boolean loadFromFile(String fileName);
    public boolean saveToFile(String fileName);
    public int searchId(String id);
    public Brand getUserChoice();
    public void addBrand();
    public void updateBrand();
    public void listBrands();
    public boolean isDuplicateId(String id);
}
