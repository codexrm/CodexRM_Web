package rest;

import dto.ReferenceDTO;
import dto.ReferencePageDTO;
import enums.SortReference;
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

public class RestReference {

    private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private static final String ReferenceURL = "http://localhost:8081/api/Reference/";

    //sending request to retrieve all reference from a user available.
    public ReferencePageDTO getAllReference(Integer page, SortReference sort, String token) {
        String inputJson = JsonUtils.convertFromObjectToJson(sort);

        assert inputJson != null;
        HttpRequest req = HttpRequest.newBuilder(URI.create(ReferenceURL + "GetAll?page=" + page))
                .header("Content-Type", "application/json").header("Authorization", token).POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());

        ReferencePageDTO referencePageDTO = new ReferencePageDTO();

        try {
            if(response.get().statusCode() == 204)
                addMessage();

            else
            referencePageDTO = JsonUtils.convertFromJsonToObject(response.get().body(), ReferencePageDTO.class);
        } catch (InterruptedException | ExecutionException e) {
            addMessage();
        }
        response.join();
        return referencePageDTO;
    }

    //sending request to retrieve the reference by id.
    public ReferenceDTO getReferenceById(Integer id, String token) {
        HttpRequest req = HttpRequest.newBuilder(URI.create(ReferenceURL + "Get/" + id))
                .header("Content-Type", "application/json").header("Authorization", token).GET().build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());

        try {
            response.join();
            if (response.get().statusCode() == 500 || response.get().statusCode() == 401)
                return null;

            else
                return JsonUtils.convertFromJsonToObject(response.get().body(), ReferenceDTO.class);

        } catch (InterruptedException | ExecutionException e) {
            addMessage();
            return null;
        }
    }

    //sending request to add the Reference details.
    public boolean addReference(ReferenceDTO referenceDTO, String token) {
        String inputJson;
        inputJson = JsonUtils.convertFromObjectToJson(referenceDTO);

        assert inputJson != null;
        HttpRequest req = HttpRequest.newBuilder(URI.create(ReferenceURL + "Add")).header("Content-Type", "application/json")
                .header("Authorization", token).POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        try {
            return response.get().statusCode() != 500 || response.get().statusCode() != 406;
        } catch (InterruptedException | ExecutionException e) {
            addMessage();
            return false;
        }
    }

    //sending request to update a Reference details.
    public boolean updateReference(ReferenceDTO referenceDTO, String token) {
        String inputJson;
        inputJson = JsonUtils.convertFromObjectToJson(referenceDTO);

        assert inputJson != null;
        HttpRequest req = HttpRequest.newBuilder(URI.create(ReferenceURL + "Update"))
                .header("Content-Type", "application/json").header("Authorization", token).PUT(HttpRequest.BodyPublishers.ofString(inputJson)).build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        try {
            response.join();
            return response.get().statusCode() != 500 || response.get().statusCode() != 304;
        } catch (InterruptedException | ExecutionException e) {
            addMessage();
            return false;
        }
    }

    //sending request to delete the reference by its id.
    public boolean deleteReference(Integer id, String token) {
        HttpRequest req = HttpRequest.newBuilder(URI.create(ReferenceURL + "Delete/" + id))
                .header("Content-Type", "application/json").header("Authorization", token).DELETE().build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        try {
            response.join();
            return response.get().statusCode() != 500 || response.get().statusCode() != 401;
        } catch (InterruptedException | ExecutionException e) {
            addMessage();
            return false;
        }
    }

    //sending request to delete Group of reference from a user.
    public boolean deleteGroupReference(ArrayList<Integer> idList, String token) {
        String inputJson;
        inputJson = JsonUtils.convertFromObjectToJson(idList);

        assert inputJson != null;
        HttpRequest req = HttpRequest.newBuilder(URI.create(ReferenceURL + "DeleteGroup"))
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
