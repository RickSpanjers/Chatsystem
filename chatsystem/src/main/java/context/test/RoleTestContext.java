package context.test;

import domain.Permission;
import domain.Role;
import repository.IRole;

import java.util.List;

public class RoleTestContext implements IRole {

    @Override
    public boolean addRole(Role r) {
        return false;
    }

    @Override
    public boolean deleteRole(Role r) {
        return false;
    }

    @Override
    public boolean updateRole(Role r) {
        return false;
    }

    @Override
    public List<Role> retrieveAllRoles() {
        return null;
    }

    @Override
    public Role getRoleById(int id) {
        return null;
    }

    @Override
    public boolean addPermissionToRole(Permission p, Role r) {
        return false;
    }

    @Override
    public boolean deletePermissionFromRole(Permission p, Role r) {
        return false;
    }
}
