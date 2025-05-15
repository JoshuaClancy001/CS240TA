package server.requests;

public class AuthedRequest {
    protected String authToken;

    public String getAuthToken(){
        return authToken;
    }

    public AuthedRequest(String authToken){
        this.authToken = authToken;
    }
}
