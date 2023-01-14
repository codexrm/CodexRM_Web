package rest;

import com.fasterxml.jackson.core.type.TypeReference;
import dto.UserDTO;
import entity.User;
import utils.DTOConverter;
import utils.JsonUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RestUser {

    private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private static final String serviceURL = "http://localhost:8081/User/";
    DTOConverter dtoConverter = new DTOConverter();


    public static void main(String[] args){
        //UserDTO u = new UserDTO("manuel","manu123");
       // System.out.println(deleteUser("manuel"));
    }

    //sending request to retrieve all users available.
    public List<User> getAllUser() {
        HttpRequest req = HttpRequest.newBuilder(URI.create(serviceURL + "getAll")).GET().build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        List<UserDTO> userDTOList = null;
        try {
            userDTOList = JsonUtils.convertFromJsonToList(response.get().body(), new TypeReference<List<UserDTO>>() {
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        response.join();
        return dtoConverter.toUserList(userDTOList);
    }

    //sending request to retrieve the user by username.
    public User getUserByUsername(String username) {
        HttpRequest req = HttpRequest.newBuilder(URI.create(serviceURL + "getByUsername/" + username)).GET().build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        UserDTO userDTO = null;
        try {
            if (response.get().statusCode() == 500) {
                response.join();
                return null;
            } else {
                try {
                    userDTO = JsonUtils.convertFromJsonToObject(response.get().body(), UserDTO.class);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                response.join();
                return dtoConverter.toUser(userDTO);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return dtoConverter.toUser(userDTO);
    }

    //sending request to add the User details.
    public boolean addUser(User user) {
        String inputJson = null;
        inputJson = JsonUtils.convertFromObjectToJson(dtoConverter.toUserDTO(user));
        HttpRequest req = HttpRequest.newBuilder(URI.create(serviceURL + "add"))
                .header("Content-Type","application/json").POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        try {
            if (response.get().statusCode() == 500){
                return false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //sending request to update a User details.
    public boolean updateUser(User user) {
        String inputJson = null;
        inputJson = JsonUtils.convertFromObjectToJson(dtoConverter.toUserDTO(user));
        HttpRequest req = HttpRequest.newBuilder(URI.create(serviceURL + "update"))
                .header("Content-Type","application/json").PUT(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        try {
            if (response.get().statusCode() == 500){
                response.join();
                return false;
            }
            else{
                user = dtoConverter.toUser(JsonUtils.convertFromJsonToObject(response.get().body(), UserDTO.class));
                response.join();
                return true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    //sending request to delete the user by its username.
    public boolean deleteUser(String username) {
        HttpRequest req = HttpRequest.newBuilder(URI.create(serviceURL + "delete/" + username)).DELETE().build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        try {
            if (response.get().statusCode() == 500){
                response.join();
                return false;
            }
            else{
                UserDTO userDTO = JsonUtils.convertFromJsonToObject(response.get().body(), UserDTO.class);
                response.join();
                return true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }
}
