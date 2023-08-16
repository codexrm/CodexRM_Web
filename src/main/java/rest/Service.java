package rest;

import dto.ReferencePageDTO;
import payload.Request.*;
import dto.UserPageDTO;
import entity.Reference;
import entity.User;
import enums.SortReference;
import enums.SortUser;
import payload.Response.AuthenticationDataResponse;
import payload.Response.TokenRefreshResponse;
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
    public ArrayList<Reference> getAllReference(AuthenticationDataResponse authenticationData) {

        ArrayList<Reference> temporalList = new ArrayList<>();
        ArrayList<Reference> referenceList = new ArrayList<>();
        ReferencePageDTO referencePageDTO = new ReferencePageDTO();

        do {
            referencePageDTO = restReference.getAllReference(authenticationData.getId(),referencePageDTO.getPageDTO().getCurrentPage(), SortReference.idAsc, authenticationData.getToken());
            if(referencePageDTO == null){
                break;
            }
            temporalList = (ArrayList<Reference>) dtoConverter.toReferenceList(referencePageDTO.getReferenceDTOList());
            for(Reference reference: temporalList){
                referenceList.add(reference);
            } referencePageDTO.getPageDTO().setCurrentPage(referencePageDTO.getPageDTO().getCurrentPage() +1);
        } while (referenceList.size() != referencePageDTO.getPageDTO().getTotalElement());

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
    public AuthenticationDataResponse login(UserLoginRequest userLogin){ return restAuth.userLogin(userLogin); }

    public boolean logout(String token) throws ExecutionException, InterruptedException { return restAuth.userLogout(token); }

    public String registerUser(SignupRequest signUpRequest){ return restAuth.registerUser(signUpRequest); }

    public TokenRefreshResponse refreshToken(TokenRefreshRequest refreshToken) { return restAuth.refreshToken(refreshToken); }


    // Rest User
    public ArrayList<User> getAllUser(String token) {

        ArrayList<User> temporalList = new ArrayList<>();
        ArrayList<User> userList = new ArrayList<>();
        UserPageDTO userPageDTO = new UserPageDTO();

        do {
            userPageDTO = restUser.getAllUser(userPageDTO.getPageDTO().getCurrentPage(), SortUser.idAsc, token);
            if(userPageDTO == null){
                break;
            }
            temporalList = (ArrayList<User>) dtoConverter.toUserList(userPageDTO.getUserDTOList());
            for(User user: temporalList){
                userList.add(user);
            }
            userPageDTO.getPageDTO().setCurrentPage(userPageDTO.getPageDTO().getCurrentPage() +1);
        } while (userList.size() != userPageDTO.getPageDTO().getTotalElement());

        return userList;
    }

    public User getUserById(Integer id, String token) { return dtoConverter.toUser(restUser.getUserById(id,token)); }

    public String addUser(AddUserRequest user, String token){ return restUser.addUser(user, token); }

    public boolean updateUser(User user, String token) { return restUser.updateUser(dtoConverter.toUserDTO(user), token); }

    public boolean updatePreferences(User user, String token) { return restUser.updatePreferences(dtoConverter.toUserDTO(user), token); }

    public String updateUserPassword(UpdatePasswordRequest updatePasswordDTO, String token){ return restUser.updateUserPassword(updatePasswordDTO, token); }

    public boolean deleteUser(Integer id, String token) { return restUser.deleteUser(id, token); }

    public boolean deleteUserGroup(ArrayList<Integer> idList, String token) { return restUser.deleteUserGroup(idList, token); }

}
