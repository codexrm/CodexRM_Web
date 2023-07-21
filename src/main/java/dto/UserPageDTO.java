package dto;

import java.util.ArrayList;
import java.util.List;

public class UserPageDTO {

    private List<UserDetailsDTO> userDTOList;
    private PageDTO pageDTO;

    public UserPageDTO() {
        userDTOList = new ArrayList<>();
        pageDTO = new PageDTO();
    }

    public UserPageDTO(List<UserDetailsDTO> userList, PageDTO pageDTO) {
        this.userDTOList = userList;
        this.pageDTO = pageDTO;
    }

    public List<UserDetailsDTO> getUserDTOList() { return userDTOList; }

    public void setUserDTOList(List<UserDetailsDTO> userDTOList) { this.userDTOList = userDTOList; }

    public PageDTO getPageDTO() { return pageDTO; }

    public void setPageDTO(PageDTO pageDTO) { this.pageDTO = pageDTO; }
}
