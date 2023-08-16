package rest;

import dto.UserDTO;
import payload.Request.SignupRequest;
import payload.Request.TokenRefreshRequest;
import payload.Request.UserLoginRequest;
import payload.Response.AuthenticationDataResponse;
import payload.Response.MessageResponse;
import payload.Response.TokenRefreshResponse;
import utils.JsonUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RestAuth {

    private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private static final String authURL = "http://localhost:8081/api/auth/";


    public AuthenticationDataResponse userLogin(UserLoginRequest userLogin) {

        String  inputJson = JsonUtils.convertFromObjectToJson(userLogin);

        HttpRequest req = HttpRequest.newBuilder(URI.create(authURL + "signin"))
                .header("Content-Type","application/json").POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());

        UserDTO userDTO = new UserDTO();

        try {
            if (response.get().statusCode() == 401) {
                response.join();
                return null;
            } else {
                userDTO = JsonUtils.convertFromJsonToObject(response.get().body(), UserDTO.class);
                response.join();
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        response.join();
        String token = userDTO.getTokenType() + " " + userDTO.getAccessToken();

        return new AuthenticationDataResponse( userDTO.getId(),  userDTO.getUsername(),  userDTO.getName(),  userDTO.getLastName(),  userDTO.getEmail(),
                userDTO.isEnabled(), token, userDTO.getRefreshToken(), userDTO.getTokenExpirationDate(), userDTO.getRefreshTokenExpirationDate(), userDTO.getRoles());
    }

    public TokenRefreshResponse refreshToken(TokenRefreshRequest refreshToken) {
        String  inputJson = JsonUtils.convertFromObjectToJson(refreshToken);

        HttpRequest req = HttpRequest.newBuilder(URI.create(authURL + "refreshtoken"))
                .header("Content-Type","application/json").POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());

        TokenRefreshResponse tokenRefreshResponse = new TokenRefreshResponse();

        try {
            tokenRefreshResponse =  JsonUtils.convertFromJsonToObject(response.get().body(), TokenRefreshResponse.class);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        response.join();
        return tokenRefreshResponse;
    }


    public boolean userLogout(String token) throws ExecutionException, InterruptedException {

        HttpRequest req = HttpRequest.newBuilder(URI.create(authURL + "signout"))
                .header("Content-Type","application/json").header("Authorization", token).POST(HttpRequest.BodyPublishers.noBody()).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());

        return response.get().statusCode() == 200;
    }


    public String registerUser(SignupRequest signUpRequest) {

        String  inputJson = JsonUtils.convertFromObjectToJson(signUpRequest);

        HttpRequest req = HttpRequest.newBuilder(URI.create(authURL + "signup"))
                .header("Content-Type","application/json").POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());

        MessageResponse message = new MessageResponse();

        try {
            message = JsonUtils.convertFromJsonToObject(response.get().body(), MessageResponse.class);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        response.join();

        return message.getMessage();

        }
}

