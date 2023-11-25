package shop.springbootapp.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import shop.springbootapp.validation.PasswordValidator;

@PasswordValidator(password = "password", confirmPassword = "confirmPassword")
public class RegisterUserDTO {
    @NotNull(message = "Enter a valid username")
    @Size(min = 3, message = "Username must contains minimum 3 character")
    @Size(max = 20, message = "Username must contains maximum 20 character")
    @Pattern(regexp = "^[a-zA-Z0-9]+$",
    message = "Username must contains letters and number only")
    private String username;
    @Size(min = 3, max = 15, message = "Password must be in 3 to 15 character range")
    private String password;
    private String firstName;
    private String lastName;
    @Size(min = 3, max = 15, message = "Password must be in 3 to 15 character range")
    private String confirmPassword;
    @Email(message = "Please enter a valid email address")
    private String email;
    @Size(min = 10, max = 10)
    @Pattern(regexp = "^[a-zA-Z0-9]{10}$", message = "Please enter a valid phone number")
    private String phoneNumber;

    public RegisterUserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
