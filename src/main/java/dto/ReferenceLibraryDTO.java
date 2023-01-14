package dto;

import java.util.ArrayList;
import java.util.List;

public class ReferenceLibraryDTO {

    private List<ReferenceDTO> newReferencesList;
    private List<ReferenceDTO> updatedReferencesList;
    private List<Integer> deletedReferencesList;
    private String username;

    public ReferenceLibraryDTO() {
        this.newReferencesList = new ArrayList<>();
        this.updatedReferencesList = new ArrayList<>();
        this.deletedReferencesList = new ArrayList<>();
    }

    public ReferenceLibraryDTO(List<ReferenceDTO> newReferencesList, List<ReferenceDTO> updatedReferencesList, List<Integer> deletedReferencesList, String username) {
        this.newReferencesList = newReferencesList;
        this.updatedReferencesList = updatedReferencesList;
        this.deletedReferencesList = deletedReferencesList;
        this.username = username;
    }

    public List<ReferenceDTO> getNewReferencesList() {
        return newReferencesList;
    }

    public void setNewReference(ReferenceDTO newReference) {
        this.newReferencesList.add(newReference);
    }

    public List<ReferenceDTO> getUpdatedReferencesList() {
        return updatedReferencesList;
    }

    public void setUpdatedReference(ReferenceDTO updatedReference) {
        this.updatedReferencesList.add(updatedReference);
    }

    public List<Integer> getDeletedReferencesList() {
        return deletedReferencesList;
    }

    public void setDeletedReference(Integer deletedReference) {
        this.deletedReferencesList.add(deletedReference);
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }
}
