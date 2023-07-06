/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package carManage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import gui.ICarList;
/**
 *
 * @author hoaht
 */
public class CarList extends ArrayList<Car> implements ICarList{
    BrandList brandList;

    public CarList() {
        super();
    }
    
    public CarList(BrandList brandList) {
        this.brandList = brandList;
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
                
                String data[] = line.split("[,]");
                
                String carId = data[0];
                String brandId = data[1];
                int searchId = brandList.searchId(brandId.trim());
                Brand brand = brandList.get(searchId);
                String color = data[2];
                String frameId = data[3];
                String engineId = data[4];
                
                Car c = new Car(carId, brand, color, frameId, engineId);
                this.add(c);
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
            for (Car c : this) {
                bw.write(c.toString());
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
    public int searchID(String id) {
        for (Car c : this) {
            if(c.getCarId().equalsIgnoreCase(id)){
                return this.indexOf(c);
            }
        }
        
        return -1;
    }

    @Override
    public int searchFrame(String fID) {
        for (Car c : this) {
            if(c.getFrameId().equalsIgnoreCase(fID)){
                return this.indexOf(c);
            }
        }
        
        return -1;
    }

    @Override
    public int searchEngine(String eID) {
        for (Car c : this) {
            if(c.getEngineId().equalsIgnoreCase(eID)){
                return this.indexOf(c);
            }
        }
        
        return -1;
    }
    
    public boolean isDuplicateCarId(String cId){
        int searchID = searchID(cId);
        
        if(searchID != -1){
            return true;
        }
        
        return false;
    }
    
    public boolean isDuplicateFrameId(String fId){
        int searchFrame = searchFrame(fId);
        
        if(searchFrame != - 1){
            return true;
        }
        
        return false;
    }
    
    public boolean isDuplicateEngineId(String eId){
        int searchEngine = searchEngine(eId);
        
        if(searchEngine != -1){
            return true;
        }
        
        return false;
    }

    @Override
    public void addCar() {
        String carId, color, frameId, engineId;
        Brand brand;
        
        boolean isDuplicateCarId;
        do{
            carId = Inputter.inputNonBlankStr("Enter car ID: ");
            isDuplicateCarId = isDuplicateCarId(carId);
        }while(isDuplicateCarId == true);
        
        brand = brandList.getUserChoice();
        color = Inputter.inputNonBlankStr("Enter color: ");
        
        boolean isDuplicateFrameId;
        do{
            frameId = Inputter.inputStrPattern("Enter frame ID: ", "[Ff][\\d]{5}");
            isDuplicateFrameId = isDuplicateFrameId(frameId);
        }while(isDuplicateFrameId == true);
        
        boolean isDuplicateEngineId;
        do{
            engineId = Inputter.inputStrPattern("Enter engine ID: ", "[Ee][\\d]{5}");
            isDuplicateEngineId = isDuplicateEngineId(engineId);
        }while(isDuplicateEngineId == true);
        
        Car c = new Car(carId, brand, color, frameId, engineId);
        this.add(c);
        System.out.println("Add " + carId + " car successfully!");
    }

    @Override
    public void printBasedBrandName() {
        String brandNameSearch = Inputter.inputStr("Enter brand name to search: ");
        int count = 0;
        
        System.out.println("----------------------------------------");
        for (Car c : this) {
            if(c.getBrand().getBrandName().contains(brandNameSearch)){
                System.out.println(c.screenString());
                count++;
            }
        }
        if(count == 0){
            System.out.println("No car is detected!");
        }else{
            System.out.println("Total: " + count + "result(s)");
        }
        System.out.println("----------------------------------------");
    }

    @Override
    public boolean removeCar() {
        if(this.isEmpty()){
            System.out.println("Empty list, remove car cannot performed!");
            return false;
        }else{
            String removedId = Inputter.inputStr("Enter car ID to remove: ");
        
            int pos = searchID(removedId);
            if(pos==-1){
                System.out.println("Not found!");
                return false;
            }

            this.remove(pos);
            return true;
        }
    }

    @Override
    public boolean updateCar() {
        if(this.isEmpty()){
            System.out.println("Empty list, update car cannot performed!");
            return false;
        }else{
            String updatedId = Inputter.inputStr("Enter car ID to update: ");
            
            int pos = searchID(updatedId);
            if(pos==-1){
                System.out.println("Not found!");
                return false;
            }
            
            String newColor, newFrameId, newEngineId;
            Brand newBrand;
            
            Car cUpdate = this.get(pos);

            newBrand = brandList.getUserChoice();
            cUpdate.setBrand(newBrand);
            newColor = Inputter.inputNonBlankStr("Enter color: ");
            cUpdate.setColor(newColor);

            boolean isDuplicateFrameId;
            do{
                newFrameId = Inputter.inputStrPattern("Enter frame ID: ", "[Ff][\\d]{5}");
                isDuplicateFrameId = isDuplicateFrameId(newFrameId);
            }while(isDuplicateFrameId == true);
            cUpdate.setFrameId(newFrameId);

            boolean isDuplicateEngineId;
            do{
                newEngineId = Inputter.inputStrPattern("Enter engine ID: ", "[Ee][\\d]{5}");
                isDuplicateEngineId = isDuplicateEngineId(newEngineId);
            }while(isDuplicateEngineId == true);
            cUpdate.setEngineId(newEngineId);
            
            return true;
        }
    }

    @Override
    public void listCars() {
        if(this.isEmpty()){
            System.out.println("Empty list!");
        }else{
            System.out.println("-----------List of car-----------");
            for (Car c : this) {
                System.out.println(c.toString());
            }
            System.out.println("-----------End of list ----------");
        }
    }
}
