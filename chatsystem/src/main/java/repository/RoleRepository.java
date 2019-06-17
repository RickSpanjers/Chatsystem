package repository;

import domain.Permission;
import domain.Role;

import java.util.List;

public class RoleRepository {

    private IRole roleContext;

    public RoleRepository(IRole roleContext){
        this.roleContext = roleContext;
    }

    public boolean addRole(Role r){
        return this.roleContext.addRole(r);
    }

    public boolean deleteRole(Role r){
        return this.roleContext.deleteRole(r);
    }

    public boolean updateRole(Role r){
        return this.roleContext.updateRole(r);
    }

    public List<Role> retrieveAllRoles(){
        return this.roleContext.retrieveAllRoles();
    }

    public Role getRoleById(int id){
        return this.roleContext.getRoleById(id);
    }

    public boolean addPermissionToRole(Permission p, Role r){
        return this.roleContext.addPermissionToRole(p, r);
    }

    public boolean deletePermissionFromRole(Permission p, Role r){
        return this.roleContext.deletePermissionFromRole(p, r);
    }



}
