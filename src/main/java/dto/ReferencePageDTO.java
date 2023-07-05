package dto;

import java.util.ArrayList;
import java.util.List;

public class ReferencePageDTO {

    private List<ReferenceDTO> referenceDTOList;
    private PageDTO pageDTO;

    public ReferencePageDTO() {
        referenceDTOList = new ArrayList<>();
        pageDTO = new PageDTO();
    }

    public ReferencePageDTO(List<ReferenceDTO> referenceDTOList, PageDTO pageDTO) {
        this.referenceDTOList = referenceDTOList;
        this.pageDTO = pageDTO;
    }

    public List<ReferenceDTO> getReferenceDTOList() { return referenceDTOList; }

    public void setReferenceDTOList(List<ReferenceDTO> referenceDTOList) { this.referenceDTOList = referenceDTOList; }

    public PageDTO getPageDTO() { return pageDTO; }

    public void setPageDTO(PageDTO pageDTO) { this.pageDTO = pageDTO; }
}
