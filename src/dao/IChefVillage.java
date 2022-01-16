package dao;

import entities.ChefVillage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IChefVillage extends Remote {
    public int addCV(ChefVillage chefVillage)throws RemoteException;
    public int deleteCV(int id)throws RemoteException;
    public int updateCV(ChefVillage chefVillage)throws RemoteException;
    public List<ChefVillage> getlistCVillage()throws RemoteException;

}
