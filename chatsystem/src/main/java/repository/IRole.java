package repository;

import domain.Permission;
import domain.Role;

import java.util.List;

public interface IRole {
    boolean addRole(Role r);
    boolean deleteRole(Role r);
    boolean updateRole(Role r);
    List<Role> retrieveAllRoles();
    Role getRoleById(int id);
    boolean addPermissionToRole(Permission p, Role r);
    boolean deletePermissionFromRole(Permission p, Role r);

}
