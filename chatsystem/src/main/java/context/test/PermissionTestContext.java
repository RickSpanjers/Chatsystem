package context.test;

import domain.Permission;
import repository.IPermission;

import java.util.List;

public class PermissionTestContext implements IPermission {
    @Override
    public boolean addPermission(Permission p) {
        return false;
    }

    @Override
    public boolean deletePermission(Permission p) {
        return false;
    }

    @Override
    public boolean updatePermission(Permission p) {
        return false;
    }

    @Override
    public List<Permission> retrieveAllPermissions() {
        return null;
    }

    @Override
    public Permission getPermissionById(int id) {
        return null;
    }
}
