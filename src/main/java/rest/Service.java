package rest;

import Auth.AuthenticationData;
import Auth.TokenRefreshRequest;
import Auth.TokenRefreshResponse;
import Auth.UserLogin;
import dto.ReferencePageDTO;
import entity.Reference;
import enums.SortReference;
import utils.DTOConverter;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Service {

    private RestReference restReference;
    private RestUser restUser;
    private RestAuth restAuth;
    private final DTOConverter dtoConverter;

    public Service() {
        restReference = new RestReference();
        restUser = new RestUser();
        restAuth = new RestAuth();
        dtoConverter = new DTOConverter();
    }

    // Rest Reference
    public ArrayList<Reference> getAllReference(AuthenticationData authenticationData) {

        ArrayList<Reference> referenceList = new ArrayList<>();
        ReferencePageDTO referencePageDTO = new ReferencePageDTO();

        do {
            referencePageDTO = restReference.getAllReference(authenticationData.getId(),referencePageDTO.getPageDTO().getCurrentPage(), SortReference.idAsc, authenticationData.getToken());
            if(referencePageDTO == null){
                break;
            }
            referenceList = (ArrayList<Reference>) dtoConverter.toReferenceList(referencePageDTO.getReferenceDTOList());
        } while (referencePageDTO.getPageDTO().getCurrentPage() + 1 != referencePageDTO.getPageDTO().getTotalPages());;

        return referenceList;
    }

    public Reference getReferenceById(Integer id, String token) {
        return dtoConverter.toReference(restReference.getReferenceById(id,token));
    }

    public boolean addReference(Integer userId,Reference reference, String token) {
        return restReference.addReference(userId, dtoConverter.toReferenceDTO(reference), token);
    }

    public boolean addReferenceGroup(Integer userId, ArrayList<Reference> referenceList, String token) {
        Boolean isAdd = false;
        for(Reference reference: referenceList){
            isAdd = addReference(userId, reference, token);
        }
        return isAdd;
    }

    public boolean updateReference(Integer userId, Reference reference, String token) {
        return restReference.updateReference(userId, dtoConverter.toReferenceDTO(reference), token);
    }

    public boolean deleteReference(Integer id, String token) {
        return restReference.deleteReference(id, token);
    }

    public boolean deleteGroupReference(ArrayList<Integer> idList, String token) {
        return restReference.deleteGroupReference(idList, token);
    }


    // Rest Auth
    public AuthenticationData login(UserLogin userLogin){ return restAuth.userLogin(userLogin); }

    public boolean logout(String token) throws ExecutionException, InterruptedException { return restAuth.userLogout(token); }

    public TokenRefreshResponse refreshToken(TokenRefreshRequest refreshToken) { return restAuth.refreshToken(refreshToken); }


}
