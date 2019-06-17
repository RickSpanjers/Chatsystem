package repository;

import domain.Permission;

import java.util.List;

public class PermissionRepository {

    private IPermission permissionContext;

    public PermissionRepository(IPermission permissionContext){
        this.permissionContext = permissionContext;
    }

    public boolean addPermission(Permission p){
        return this.permissionContext.addPermission(p);
    }

    public  boolean deletePermission(Permission p){
        return this.permissionContext.deletePermission(p);
    }

    public boolean updatePermission(Permission p){
        return this.permissionContext.updatePermission(p);
    }

    public List<Permission> retrieveAllPermissions(){
        return this.permissionContext.retrieveAllPermissions();
    }

    public Permission getPermissionById(int id){
        return this.permissionContext.getPermissionById(id);
    }
}
