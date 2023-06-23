package rest;

import com.fasterxml.jackson.core.type.TypeReference;
import dto.*;
import entity.Reference;
import entity.User;
import utils.DTOConverter;
import utils.JsonUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RestReference {

    private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private static final String serviceURL = "http://localhost:8081/Reference/";
    DTOConverter dtoConverter = new DTOConverter();


    public static void main(String[] args){
        //User u = new User("mary","mary");
       // UserDTO us = new UserDTO("mary","mary");
       // WebPageReferenceDTO r = new WebPageReferenceDTO( "author",  "title", LocalDate.now(),  "note",  7, u, "url", LocalDate.now());

       // System.out.println(deleteAllReference(u));
    }

    //sending request to retrieve all reference from a user available.
    public List<Reference> getAllReference(User user) {
        String inputJson = null;
        inputJson = JsonUtils.convertFromObjectToJson(dtoConverter.toUserDTO(user));
        HttpRequest req = HttpRequest.newBuilder(URI.create(serviceURL + "getAll")).header("Content-Type","application/json").POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        List<ReferenceDTO> referenceDTOList = null;
        try {
            referenceDTOList = JsonUtils.convertFromJsonToList(response.get().body(), new TypeReference<List<ReferenceDTO>>() {
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        response.join();
        return dtoConverter.toReferenceList(referenceDTOList);
    }

    //sending request to retrieve the reference by id.
    public Reference getReferenceById(Integer id) {
        HttpRequest req = HttpRequest.newBuilder(URI.create(serviceURL + "getById/" + id)).GET().build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        ReferenceDTO referenceDTO = null;
        try {
            if (response.get().statusCode() == 500) {
                response.join();
                return null;
            } else {
                try {
                    referenceDTO = JsonUtils.convertFromJsonToObject(response.get().body(), ReferenceDTO.class);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                response.join();
                return dtoConverter.toReference(referenceDTO);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return dtoConverter.toReference(referenceDTO);
    }

    /*public List<Reference> getReferenceByAuthor(User user, String author) {
        AuthorAndUserDTO authorAndUserDTO = new AuthorAndUserDTO(author,user);
        String inputJson = null;
        inputJson = JsonUtils.convertFromObjectToJson(authorAndUserDTO);
        HttpRequest req = HttpRequest.newBuilder(URI.create(serviceURL + "getByAuthor")).header("Content-Type","application/json").POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        List<ReferenceDTO> referenceDTOList = null;
        try {
            referenceDTOList = JsonUtils.convertFromJsonToList(response.get().body(), new TypeReference<List<ReferenceDTO>>() {
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        response.join();
        return dtoConverter.toReferenceList(referenceDTOList);
    }

    public List<Reference> getReferenceByTitle(User user, String title) {
        TitleAndUserDTO titleAndUserDTO = new TitleAndUserDTO(title,user);
        String inputJson = null;
        inputJson = JsonUtils.convertFromObjectToJson(titleAndUserDTO);
        HttpRequest req = HttpRequest.newBuilder(URI.create(serviceURL + "getByTitle")).header("Content-Type","application/json").POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        List<ReferenceDTO> referenceDTOList = null;
        try {
            referenceDTOList = JsonUtils.convertFromJsonToList(response.get().body(), new TypeReference<List<ReferenceDTO>>() {
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        response.join();
        return dtoConverter.toReferenceList(referenceDTOList);
    }*/

    //sending request to add the Reference details.
    public boolean addReference(Reference reference) {
        String inputJson = null;
        inputJson = JsonUtils.convertFromObjectToJson(dtoConverter.toReferenceDTO(reference));
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

    //sending request to add all Reference details.
    public boolean addReferenceGroup(ArrayList<Reference> referenceList) {
        Boolean isAdd = false;
       for(Reference reference: referenceList){
           isAdd = addReference(reference);
       }
       return isAdd;
    }

    //sending request to update a Reference details.
    public boolean updateReference(Reference reference) {
        String inputJson = null;
        inputJson = JsonUtils.convertFromObjectToJson(dtoConverter.toReferenceDTO(reference));
        HttpRequest req = HttpRequest.newBuilder(URI.create(serviceURL + "update"))
                .header("Content-Type","application/json").PUT(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        try {
            if (response.get().statusCode() == 500){
                response.join();
                return false;
            }
            else{
                reference = dtoConverter.toReference(JsonUtils.convertFromJsonToObject(response.get().body(), ReferenceDTO.class));
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

    //sending request to delete the reference by its id.
    public boolean deleteReference(Integer id) {
        HttpRequest req = HttpRequest.newBuilder(URI.create(serviceURL + "delete/" + id)).DELETE().build();
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    //sending request to delete all reference from a user.
    public boolean deleteAllReference(User user) {
        String inputJson = null;
        inputJson = JsonUtils.convertFromObjectToJson(dtoConverter.toUserDTO(user));
        HttpRequest req = HttpRequest.newBuilder(URI.create(serviceURL + "deleteAll")).header("Content-Type","application/json").POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    //sending request to delete Group of reference from a user.
    public boolean deleteGroupReference(ArrayList<Integer> idList) {
        String inputJson = null;
        inputJson = JsonUtils.convertFromObjectToJson(idList);
        HttpRequest req = HttpRequest.newBuilder(URI.create(serviceURL + "deleteGroup")).header("Content-Type","application/json").POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }
}
