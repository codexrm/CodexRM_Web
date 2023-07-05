package rest;

import dto.*;
import enums.SortReference;
import utils.JsonUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RestReference {

    private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private static final String ReferenceURL = "http://localhost:8081/api/Reference/";


    //sending request to retrieve all reference from a user available.
    public ReferencePageDTO getAllReference(Integer userId, Integer page, SortReference sort, String token){
        String  inputJson = JsonUtils.convertFromObjectToJson(sort);
        HttpRequest req = HttpRequest.newBuilder(URI.create(ReferenceURL + "GetAll?page=" + page + "&userId=" + userId))
                .header("Content-Type","application/json").header("Authorization", token).POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());

        ReferencePageDTO referencePageDTO = new ReferencePageDTO();
        try {
            referencePageDTO = JsonUtils.convertFromJsonToObject(response.get().body(),ReferencePageDTO.class) ;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        response.join();
        return referencePageDTO;
    }

    //sending request to retrieve the reference by id.
    public ReferenceDTO getReferenceById(Integer id, String token) {
        HttpRequest req = HttpRequest.newBuilder(URI.create(ReferenceURL + "Get/" + id))
                .header("Content-Type","application/json").header("Authorization", token).GET().build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        ReferenceDTO referenceDTO = null;

        try {
            if (response.get().statusCode() == 500) {
                response.join();
                return null;
            } else {
                referenceDTO = JsonUtils.convertFromJsonToObject(response.get().body(), ReferenceDTO.class);
                response.join();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return referenceDTO;
    }

    //sending request to add the Reference details.
    public boolean addReference(Integer userId, ReferenceDTO referenceDTO, String token) {
        String inputJson = null;
        inputJson = JsonUtils.convertFromObjectToJson(referenceDTO);
        HttpRequest req = HttpRequest.newBuilder(URI.create(ReferenceURL + "Add?userId=" + userId)).header("Content-Type","application/json")
                .header("Authorization", token).POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        try {
            if (response.get().statusCode() == 500){
                return false;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //sending request to update a Reference details.
    public boolean updateReference(Integer userId, ReferenceDTO referenceDTO, String token) {
        String inputJson = null;
        inputJson = JsonUtils.convertFromObjectToJson(referenceDTO);
        HttpRequest req = HttpRequest.newBuilder(URI.create(ReferenceURL + "Update?userId=" + userId))
                .header("Content-Type","application/json").header("Authorization", token).PUT(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        try {
            if (response.get().statusCode() == 500){
                response.join();
                return false;
            }
            else{
                referenceDTO = JsonUtils.convertFromJsonToObject(response.get().body(), ReferenceDTO.class);
                response.join();
                return true;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    //sending request to delete the reference by its id.
    public boolean deleteReference(Integer id, String token) {
        HttpRequest req = HttpRequest.newBuilder(URI.create(ReferenceURL + "Delete/" + id))
                .header("Content-Type","application/json").header("Authorization", token).DELETE().build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        try {
            if (response.get().statusCode() == 500){
                response.join();
                return false;
            }
            else{
                ReferenceDTO referenceDTO = JsonUtils.convertFromJsonToObject(response.get().body(), ReferenceDTO.class);
                response.join();
                return true;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }


    //sending request to delete Group of reference from a user.
    public boolean deleteGroupReference(ArrayList<Integer> idList, String token) {
        String inputJson = null;
        inputJson = JsonUtils.convertFromObjectToJson(idList);
        HttpRequest req = HttpRequest.newBuilder(URI.create(ReferenceURL + "DeleteGroup"))
                .header("Content-Type","application/json").header("Authorization", token).POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        try {
            if (response.get().statusCode() == 500){
                response.join();
                return false;
            }
            else{
                ReferenceDTO referenceDTO = JsonUtils.convertFromJsonToObject(response.get().body(), ReferenceDTO.class);
                response.join();
                return true;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }
}
