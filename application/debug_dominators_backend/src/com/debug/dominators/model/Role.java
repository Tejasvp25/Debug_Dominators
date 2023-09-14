package com.debug.dominators.model;

public class Role {
    private int roleId;
    private String roleName;
    
    public Role(int roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public static String getRole(int role) {
    	switch (role) {
			case 1:
				return "DEVELOPER";
			case 2:
				return "TESTER";
			case 3:
				return "PROJECT_MANAGER";
    	}
		return "";
    }
}