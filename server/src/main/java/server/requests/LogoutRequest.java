package server.requests;

public class LogoutRequest {
    protected String authToken;

    public LogoutRequest(String authToken){
        this.authToken = authToken;
    }

    public String getAuthToken(){
        return authToken;
    }
}
