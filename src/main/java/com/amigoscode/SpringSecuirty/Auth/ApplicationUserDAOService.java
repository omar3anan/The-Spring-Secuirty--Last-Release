// package com.amigoscode.SpringSecuirty.Auth;

// import java.util.List;

// import org.springframework.security.crypto.password.PasswordEncoder;

// import com.amigoscode.SpringSecuirty.Security.theRoles;
// import com.google.common.base.Optional;
// import com.google.common.collect.Lists;

// public class ApplicationUserDAOService implements ApplicationUserDAO {

// private final PasswordEncoder passwordEncoder;

// public ApplicationUserDAOService(PasswordEncoder passwordEncoder) {
// this.passwordEncoder = passwordEncoder;
// }

// @Override
// public Optional<ApplicationUser> selectApplicationUserByUsername(String
// username) {
// // TODO Auto-generated method stub
// return Optional.empty();
// }

// private List<ApplicationUser> getpplicationUsers = Lists.newArrayList(
// new ApplicationUser(
// "annasmith",
// passwordEncoder.encode("password"),
// theRoles.ADMIN.getGrandtedAuthorties(),
// true,
// true,
// true,
// true))
