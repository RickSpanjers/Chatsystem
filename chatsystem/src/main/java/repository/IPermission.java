package repository;

import domain.Permission;
import domain.Role;

import java.util.List;

public interface IPermission {
    boolean addPermission(Permission p);
    boolean deletePermission(Permission p);
    boolean updatePermission(Permission p);
    List<Permission> retrieveAllPermissions();
    Permission getPermissionById(int id);
}
