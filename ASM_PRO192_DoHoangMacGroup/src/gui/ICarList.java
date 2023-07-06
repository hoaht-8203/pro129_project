/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

/**
 *
 * @author hoaht
 */
public interface ICarList {
    public boolean loadFromFile(String fileName);
    public boolean saveToFile(String fileName);
    public int searchID(String ID);
    public int searchFrame(String fID);
    public int searchEngine(String eID);
    public void addCar();
    public void printBasedBrandName();
    public boolean removeCar();
    public boolean updateCar();
    public void listCars();
}
