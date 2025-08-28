package com.example.librarymgt.services;
//this is business logic layer
//perform CRUD operations on User data
import com.example.librarymgt.repository.UserRepository;
import com.example.librarymgt.repository.RoleRepository;
import com.example.librarymgt.dto.UserDTO;
import com.example.librarymgt.model.Role;
import com.example.librarymgt.model.User;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.SecretKey;

@Service //indicates that this class is a service component
public class UserServices {

	@Autowired //to inject the UserRepository bean
	private UserRepository userr; //repository to access User data
	
	@Autowired
    private PasswordEncoder passwordEncoder; // Inject the password encoder
	
	@Autowired //to inject the RoleRepository bean
	private RoleRepository roleRepository; //repository to access Role data
	
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    private SecretKey getSigningKey() {
        byte[] keyBytes = io.jsonwebtoken.io.Decoders.BASE64.decode(jwtSecret);
        return io.jsonwebtoken.security.Keys.hmacShaKeyFor(keyBytes);
    }
    
	public User saveUser(UserDTO userDTO) {
        // Create new User entity
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setContactNo(userDTO.getContactNo());
        user.setAddress(userDTO.getAddress());
        user.setMembershipStartDate(userDTO.getMembershipStartDate());
        user.setMembershipEndDate(userDTO.getMembershipEndDate());
        
        if (userDTO.getRoleId() == null) {
            throw new RuntimeException("Role ID cannot be null");
        }
        Role role = roleRepository.findById(userDTO.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);
        
        System.out.println("User added: " + user);
        return userr.save(user);
    }
	
	public Optional<User> getUserById(Long id) {
		// Use UserRepository to find the user by ID
		return userr.findById(id); //returns the user if found, otherwise null
	}
	
	public Optional<User> getUserByEmail(String email) {
		// Use UserRepository to find the user by email
		return userr.findByEmail(email); //returns the user if found, otherwise null
	}
	
	public Optional<User> getUserByUsername(String username) {
		// Use UserRepository to find the user by username
		return userr.findByUsername(username); //returns the user if found, otherwise null
	}
	
	public List<User> searchByUsernameOrEmail(String query) {
	    return userr.findByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query);
	}
	
	public List<User> getAllUsers() {
		// Use UserRepository to find all users
		return userr.findAll(); //returns a list of all users
	}	
	
	// LOGIN: return JWT if valid
	public String loginUser(String username, String password) {
	    User user = userr.findByUsername(username)
	                     .orElseThrow(() -> new RuntimeException("Invalid username or password"));

	    if (!passwordEncoder.matches(password, user.getPassword())) {
	        throw new RuntimeException("Invalid username or password");
	    }

	    return generateJwtToken(user);
	}

    // LOGOUT (JWT is stateless → just inform client to discard token)
    public String logoutUser(String username) {
        return "Logout successful for user: " + username;
    }

    // JWT generator
    private String generateJwtToken(User user) {
        return Jwts.builder()
        		.setSubject(user.getUsername())
    	        .claim("role", user.getRole().getRoleType().name()) // include role
    	        .setIssuedAt(new Date())
    	        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
    	        .signWith(getSigningKey(), SignatureAlgorithm.HS512) // secure
    	        .compact();
    	}
	
	public User updateUser(Long id, User userDetails) {
		// Use UserRepository to find the user by ID
		User existingUser = userr.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
		if (userDetails.getUsername() != null) //check if name is not null
			existingUser.setUsername(userDetails.getUsername());
		if (userDetails.getEmail() != null) //check if email is not null
			existingUser.setEmail(userDetails.getEmail());
		
		// HASH THE PASSWORD IF IT IS BEING UPDATED
	    if (userDetails.getPassword() != null) {
	        String hashedPassword = passwordEncoder.encode(userDetails.getPassword());
	        existingUser.setPassword(hashedPassword);
	    }
		
	    if (userDetails.getContactNo() != null) //check if contact number is not null
			existingUser.setContactNo(userDetails.getContactNo());
		if (userDetails.getAddress() != null) //check if address is not null
			existingUser.setAddress(userDetails.getAddress());
		if (userDetails.getRole() != null) //check if role is not null
			existingUser.setRole(userDetails.getRole());
		return userr.save(existingUser); //returns the updated user object
	}
	
	public void deleteUser(Long id) {
		userr.deleteById(id); //deletes the user by ID
	}
}
