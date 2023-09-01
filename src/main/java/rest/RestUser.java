package rest;

import dto.UserDetailsDTO;
import dto.UserPageDTO;
import enums.SortUser;
import payload.Request.AddUserRequest;
import payload.Request.UpdatePasswordRequest;
import payload.Response.MessageResponse;
import utils.JsonUtils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RestUser {

    private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private static final String userURL = "http://localhost:8081/api/User/";

    //sending request to retrieve all user
    public UserPageDTO getAllUser(Integer page, SortUser sort, String token) {
        String inputJson = JsonUtils.convertFromObjectToJson(sort);
        assert inputJson != null;
        HttpRequest req = HttpRequest.newBuilder(URI.create(userURL + "GetAll?page=" + page))
                .header("Content-Type", "application/json").header("Authorization", token).POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());

        UserPageDTO userPageDTO = new UserPageDTO();
        try {
            if(response.get().statusCode() == 204)
                addMessage();

            else
                userPageDTO = JsonUtils.convertFromJsonToObject(response.get().body(), UserPageDTO.class);

        } catch (InterruptedException | ExecutionException e) {
            addMessage();
        }

        response.join();
        return userPageDTO;
    }

    //sending request to retrieve the user by id.
    public UserDetailsDTO getUserById(Integer id, String token) {
        HttpRequest req = HttpRequest.newBuilder(URI.create(userURL + "Get/" + id))
                .header("Content-Type", "application/json").header("Authorization", token).GET().build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        UserDetailsDTO userDetailsDTO = null;

        try {
            if (response.get().statusCode() == 500 || response.get().statusCode() == 401) {
                response.join();
                return null;
            } else {
                userDetailsDTO = JsonUtils.convertFromJsonToObject(response.get().body(), UserDetailsDTO.class);
                response.join();
            }
        } catch (InterruptedException | ExecutionException e) {
            addMessage();
        }
        return userDetailsDTO;
    }

    //sending request to add a User.
    public String addUser(AddUserRequest addUserRequest, String token) {

        String inputJson;
        inputJson = JsonUtils.convertFromObjectToJson(addUserRequest);

        assert inputJson != null;
        HttpRequest req = HttpRequest.newBuilder(URI.create(userURL + "Add"))
                .header("Content-Type", "application/json").header("Authorization", token).POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        MessageResponse message = new MessageResponse();

        try {
            message = JsonUtils.convertFromJsonToObject(response.get().body(), MessageResponse.class);

        } catch (InterruptedException | ExecutionException e) {
            addMessage();
        }
        response.join();

        assert message != null;
        return message.getMessage();
    }

    //sending request to update a User details.
    public boolean updateUser(UserDetailsDTO userDetailsDTO, String token) {
        String inputJson;
        inputJson = JsonUtils.convertFromObjectToJson(userDetailsDTO);

        assert inputJson != null;
        HttpRequest req = HttpRequest.newBuilder(URI.create(userURL + "Update"))
                .header("Content-Type", "application/json").header("Authorization", token).PUT(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        try {
            response.join();
            return response.get().statusCode() != 500;
        } catch (InterruptedException | ExecutionException e) {
            addMessage();
            return false;
        }
    }

    //sending request to update user preferences.
    public String updateUserPassword(UpdatePasswordRequest updatePasswordDTO, String token) {
        String inputJson;
        inputJson = JsonUtils.convertFromObjectToJson(updatePasswordDTO);

        assert inputJson != null;
        HttpRequest req = HttpRequest.newBuilder(URI.create(userURL + "UpdatePassword"))
                .header("Content-Type", "application/json").header("Authorization", token).PUT(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        MessageResponse message = new MessageResponse();
        try {
            message = JsonUtils.convertFromJsonToObject(response.get().body(), MessageResponse.class);

        } catch (InterruptedException | ExecutionException e) {
            addMessage();
        }
        response.join();

        assert message != null;
        return message.getMessage();
    }

    //sending request to update user preferences.
    public boolean updatePreferences(UserDetailsDTO userDetailsDTO, String token) {
        String inputJson;
        inputJson = JsonUtils.convertFromObjectToJson(userDetailsDTO);

        assert inputJson != null;
        HttpRequest req = HttpRequest.newBuilder(URI.create(userURL + "Preferences"))
                .header("Content-Type", "application/json").header("Authorization", token).PUT(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        try {
            response.join();
            return response.get().statusCode() != 500 || response.get().statusCode() == 401;
        } catch (InterruptedException | ExecutionException e) {
            addMessage();
            return false;
        }
    }

    //sending request to delete the user by its id.
    public boolean deleteUser(Integer id, String token) {
        HttpRequest req = HttpRequest.newBuilder(URI.create(userURL + "Delete/" + id))
                .header("Content-Type", "application/json").header("Authorization", token).DELETE().build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        try {
            response.join();
            return response.get().statusCode() != 500;
        } catch (InterruptedException | ExecutionException e) {
            addMessage();
            return false;
        }
    }

    //sending request to delete Group of user from a user.
    public boolean deleteUserGroup(ArrayList<Integer> idList, String token) {
        String inputJson;
        inputJson = JsonUtils.convertFromObjectToJson(idList);

        assert inputJson != null;
        HttpRequest req = HttpRequest.newBuilder(URI.create(userURL + "DeleteGroup"))
                .header("Content-Type", "application/json").header("Authorization", token).POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        try {
            response.join();
            return response.get().statusCode() != 500;
        } catch (InterruptedException | ExecutionException e) {
            addMessage();
            return false;
        }
    }

    private void addMessage() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hubo un error en el servidor. Inténtelo luego", ""));
    }
}
