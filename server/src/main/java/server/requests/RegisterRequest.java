package server.requests;

public class RegisterRequest extends UnauthedRequest{
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
