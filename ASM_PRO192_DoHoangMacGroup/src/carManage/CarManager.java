/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package carManage;

import java.util.Collections;

/**
 *
 * @author hoaht
 */
public class CarManager {
    public static void main(String[] args) {
        BrandList brandList = new BrandList();
        brandList.loadFromFile("brands.txt");
        CarList carList = new CarList(brandList);
        carList.loadFromFile("cars.txt");
        
        String[] options = {"List all brands", "Add a new brand", "Search a brand based on its ID",
                            "Update a brand", "Save brands to the file", "List all cars in ascending order of brand names",
                            "List cars based on a part of brand name", "Add a car", "Remove a car based on its ID",
                            "Update a car based on its ID", "Save cars to file", "Quit"};
        int choice = 0;
        do{
            choice = Menu.int_getChoice(options);
            
            switch(choice){
                case 1: brandList.listBrands(); break;
                case 2: brandList.addBrand(); break;
                case 3: 
                    if(brandList.isEmpty()){
                        System.out.println("Empty list, search cannot be performed!");
                    }else{
                        String searchId = Inputter.inputStr("Enter brand ID to search: ");
                        int pos = brandList.searchId(searchId);
                        if(pos == -1){
                            System.out.println("Not founded!");
                        }else{
                            Brand b = brandList.get(pos);
                            System.out.println("Your brand: ");
                            System.out.println(b.toString());
                        }
                    }
                    break;
                case 4: brandList.updateBrand(); break;
                case 5: 
                    boolean b = brandList.saveToFile("brands.txt"); 
                    if(b) System.out.println("Save brands list to file successfull!");
                    else System.out.println("Save brands list failed!");
                    break;
                case 6: 
                    Collections.sort(carList);
                    carList.listCars();
                    break;
                case 7: carList.printBasedBrandName(); break;
                case 8: carList.addCar(); break;
                case 9: carList.removeCar(); break;
                case 10: carList.updateCar(); break;
                case 11: carList.saveToFile("cars.txt"); break;
                    
                default: System.out.println("Exit the program!");
            }
        }while(choice > 0 && choice < options.length);
    }
}
