package dao;

import entities.Role;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IRole extends Remote {
    public int delete(int id) throws RemoteException;
    public int update(Role role) throws RemoteException;
    public Role get(int id) throws RemoteException;
    public List<Role> getAll() throws RemoteException;
    public int add(Role role) throws RemoteException;
}
