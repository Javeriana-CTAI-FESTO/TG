package co.edu.javeriana.ctai.tgsecurity.security.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String username;
    private String password;
    private String name;
    private String lastName;
    private String email;
    private Long identification;
    private Long phone;
    private boolean admin;
    private boolean student;
    private boolean teacher;
}
