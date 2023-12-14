// package com.amigoscode.SpringSecuirty.Auth;

// import java.util.List;
// import java.util.Set;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// public class ApplicationUser implements UserDetails {

// private final Set<? extends GrantedAuthority> grantedAuthorities; // ! to
// store the role of the user
// private final String username; // ! to store the username
// private final String password; // ! to store the password
// private final String role; // ! to store the role
// private final boolean isAccountNonExpired; // ! to check if the account is
// expired or not
// private final boolean isAccountNonLocked; // ! to check if the account is
// locked or not
// private final boolean isCredentialsNonExpired;
// private final boolean isEnabled;

// public ApplicationUser(
// String username,
// String password,
// Set<GrantedAuthority> grantedAuthorities,
// String role,
// boolean isAccountNonExpired,
// boolean isAccountNonLocked,
// boolean isCredentialsNonExpired,
// boolean isEnabled) {
// this.username = username;
// this.password = password;
// this.grantedAuthorities = grantedAuthorities;
// this.role = role;
// this.isAccountNonExpired = isAccountNonExpired;
// this.isAccountNonLocked = isAccountNonLocked;
// this.isCredentialsNonExpired = isCredentialsNonExpired;
// this.isEnabled = isEnabled;

// }

// @Override
// public Set<? extends GrantedAuthority> getAuthorities() {
// return grantedAuthorities;
// }

// @Override
// public String getPassword() {
// return password;
// }

// @Override
// public String getUsername() {
// return username;
// }

// public String getRole() {
// return role;
// }

// // ! to check if the account is expired or not
// @Override
// public boolean isAccountNonExpired() {
// return isAccountNonExpired;
// }

// // ! to check if the account is locked or not
// @Override
// public boolean isAccountNonLocked() {

// return isAccountNonLocked;
// }

// // ! to check if the credentials is expired or not
// @Override
// public boolean isCredentialsNonExpired() {

// return isCredentialsNonExpired;
// }

// // ! to check if the account is enabled or not
// @Override
// public boolean isEnabled() {

// return isEnabled;
// }

// }
