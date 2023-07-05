package dto;

public class PageDTO {

    private Integer currentPage;
    private Long totalElement;
    private Integer totalPages;

    public PageDTO() {
        currentPage = 0;
    }

    public PageDTO(Integer currentPage, Long totalElement, Integer totalPages) {
        this.currentPage = currentPage;
        this.totalElement = totalElement;
        this.totalPages = totalPages;
    }

    public Integer getCurrentPage() { return currentPage; }

    public void setCurrentPage(Integer currentPage) { this.currentPage = currentPage; }

    public Long getTotalElement() { return totalElement; }

    public void setTotalElement(Long totalElement) { this.totalElement = totalElement; }

    public Integer getTotalPages() { return totalPages; }

    public void setTotalPages(Integer totalPages) { this.totalPages = totalPages; }
}
