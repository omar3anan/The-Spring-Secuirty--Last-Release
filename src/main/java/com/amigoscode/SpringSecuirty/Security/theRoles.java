package com.amigoscode.SpringSecuirty.Security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

public enum theRoles {
    // what i do here is that i create a set of permissions for each role
    // and then i create a constructor for each role that takes in a set of
    // permissions
    // and then i create a getter for the permissions
    USER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(
            thePermissions.COURSE_READ,
            thePermissions.COURSE_WRITE,
            thePermissions.STUDENT_WRITE,
            thePermissions.STUDENT_READ)),
    ADMINTRAINEE(Sets.newHashSet(
            thePermissions.COURSE_READ,
            thePermissions.STUDENT_READ));

    private final Set<thePermissions> permissions;

    theRoles(Set<thePermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<thePermissions> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrandtedAuthorties() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;

    }
}
