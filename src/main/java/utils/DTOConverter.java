package utils;

import dto.*;
import entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DTOConverter {

    private final ModelMapper modelMapper;

    @Autowired
    public DTOConverter() {this.modelMapper = new ModelMapper();}

    //Reference
    public ReferenceDTO toReferenceDTO(final Reference reference) {

        reference.getUser().setReferenceList(new ArrayList<>());

        if(reference.getClass() == ArticleReference.class){
            return modelMapper.map(reference,ArticleReferenceDTO.class);
        }
        else if(reference.getClass() == BookSectionReference.class){
            return modelMapper.map(reference,BookSectionReferenceDTO.class);
        }
        else if(reference.getClass() == BookReference.class){
            return modelMapper.map(reference,BookReferenceDTO.class);
        }
        else if(reference.getClass() == BookLetReference.class){
            return modelMapper.map(reference,BookLetReferenceDTO.class);
        }
        else if(reference.getClass() == ConferenceProceedingReference.class){
            return modelMapper.map(reference,ConferenceProceedingsReferenceDTO.class);
        }
        else if(reference.getClass() == ConferencePaperReference.class) {
            return modelMapper.map(reference,ConferencePaperReferenceDTO.class);
        }
        else if(reference.getClass() == WebPageReference.class) {
            return modelMapper.map(reference,WebPageReferenceDTO.class);
        }
        else
            return modelMapper.map(reference,ThesisReferenceDTO.class);
    }

    public Reference toReference(final ReferenceDTO referenceDTO) {

        if(referenceDTO.getClass() == ArticleReferenceDTO.class){
            return modelMapper.map(referenceDTO,ArticleReference.class);
        }
        else if(referenceDTO.getClass() == BookSectionReferenceDTO.class){
            return modelMapper.map(referenceDTO,BookSectionReference.class);
        }
        else if(referenceDTO.getClass() == BookReferenceDTO.class){
            return modelMapper.map(referenceDTO,BookReference.class);
        }
        else if(referenceDTO.getClass() == BookLetReferenceDTO.class){
            return modelMapper.map(referenceDTO,BookLetReference.class);
        }
        else if(referenceDTO.getClass() == ConferenceProceedingsReferenceDTO.class){
            return modelMapper.map(referenceDTO,ConferenceProceedingReference.class);
        }
        else if(referenceDTO.getClass() == ConferencePaperReferenceDTO.class){
            return modelMapper.map(referenceDTO,ConferencePaperReference.class);
        }
        else if(referenceDTO.getClass() == WebPageReferenceDTO.class){
            return modelMapper.map(referenceDTO,WebPageReference.class);
        }
        else{
            return modelMapper.map(referenceDTO,ThesisReference.class);
        }
    }

    public List<ReferenceDTO> toReferenceDTOList(final List<Reference> referenceList) {

        List<ReferenceDTO> referenceDTOList = new ArrayList<>();
        referenceList.forEach(reference ->
                referenceDTOList.add(toReferenceDTO(reference))
        );
        return referenceDTOList;
    }

    public List<Reference> toReferenceList(final List<ReferenceDTO> referenceDTOList) {

        List<Reference> referenceList = new ArrayList<>();
        if(referenceDTOList.size()!= 0) {
            referenceDTOList.forEach(referenceDTO ->
                    referenceList.add(toReference(referenceDTO)));
        }

        return referenceList;
    }

   /* public Reference createReference(final ReferenceDTO referenceDTO) {


        if(referenceDTO.getClass() == ArticleReferenceDTO.class){

            ArticleReferenceDTO articleDTO = (ArticleReferenceDTO) referenceDTO;
            return new ArticleReference(articleDTO.getAuthor(), articleDTO.getTitle(),
                    articleDTO.getLocalDate(), articleDTO.getNote(), articleDTO.getUser(),
                    articleDTO.getJournal(), articleDTO.getVolume(), articleDTO.getNumber(),
                    articleDTO.getPages());
        }
        else if(referenceDTO.getClass() == BookSectionReferenceDTO.class){

            BookSectionReferenceDTO sectionDTO = (BookSectionReferenceDTO) referenceDTO;
            return new BookSectionReference(sectionDTO.getAuthor(), sectionDTO.getTitle(),
                    sectionDTO.getLocalDate(), sectionDTO.getNote(), sectionDTO.getUser(),
                    sectionDTO.getPublisher(), sectionDTO.getVolume(), sectionDTO.getSeries(),
                    sectionDTO.getAddress(), sectionDTO.getEdition(), sectionDTO.getChapter(),
                    sectionDTO.getPages());
        }
        else if(referenceDTO.getClass() == BookReferenceDTO.class){

            BookReferenceDTO bookDTO = (BookReferenceDTO) referenceDTO;
            return new BookReference(bookDTO.getAuthor(), bookDTO.getTitle(), bookDTO.getLocalDate(),
                    bookDTO.getNote(), bookDTO.getUser(), bookDTO.getPublisher(),
                    bookDTO.getVolume(), bookDTO.getSeries(), bookDTO.getAddress(), bookDTO.getEdition());
        }
        else if(referenceDTO.getClass() == BookLetReferenceDTO.class){

            BookLetReferenceDTO letDTO = (BookLetReferenceDTO) referenceDTO;
            return new BookLetReference(letDTO.getAuthor(), letDTO.getTitle(), letDTO.getLocalDate(),
                    letDTO.getNote(), letDTO.getUser(), letDTO.getHowpublished(),
                    letDTO.getAddress());
        }
        else if(referenceDTO.getClass() == ConferenceProceedingsReferenceDTO.class){

            ConferenceProceedingsReferenceDTO proceedingsDTO = (ConferenceProceedingsReferenceDTO) referenceDTO;
            return new ConferenceProceedingsReference(proceedingsDTO.getAuthor(), proceedingsDTO.getTitle(),
                    proceedingsDTO.getLocalDate(), proceedingsDTO.getNote(), proceedingsDTO.getUser(),
                    proceedingsDTO.getVolume(), proceedingsDTO.getSeries(), proceedingsDTO.getAddress());
        }
        else if(referenceDTO.getClass() == ConferencePaperReferenceDTO.class){

            ConferencePaperReferenceDTO paperDTO = (ConferencePaperReferenceDTO) referenceDTO;
            return new ConferencePaperReference(paperDTO.getAuthor(), paperDTO.getTitle(),
                    paperDTO.getLocalDate(), paperDTO.getNote(), paperDTO.getUser(),
                    paperDTO.getPublisher(), paperDTO.getVolume(), paperDTO.getAddress(),
                    paperDTO.getPages());
        }
        else if(referenceDTO.getClass() == WebPageReferenceDTO.class){

            WebPageReferenceDTO webDTO = (WebPageReferenceDTO) referenceDTO;
            return new WebPageReference(webDTO.getAuthor(), webDTO.getTitle(), webDTO.getLocalDate(),
                    webDTO.getNote(), webDTO.getUser(), webDTO.getAccessDateLocal(), webDTO.getUrl());
        }
        else{
            ThesisReferenceDTO thesisDTO = (ThesisReferenceDTO) referenceDTO;
            return new ThesisReference(thesisDTO.getAuthor(), thesisDTO.getTitle(), thesisDTO.getLocalDate(),
                    thesisDTO.getNote(), thesisDTO.getUser(), thesisDTO.getSchool(),
                    thesisDTO.getType(), thesisDTO.getAddress());
        }
    }
    public ReferenceDTO createReferenceDTO(final Reference reference) {


        if(reference.getClass() == ArticleReference.class){

            ArticleReference article = (ArticleReference) reference;
            return new ArticleReferenceDTO(article.getAuthor(), article.getTitle(),
                    article.getDate(), article.getNote(), article.getUser(), article.getJournal(),
                    article.getNumber(), article.getPages() , article.getVolume());
        }
        else if(reference.getClass() == BookSectionReference.class){

            BookSectionReference section = (BookSectionReference) reference;
            return new BookSectionReferenceDTO(section.getAuthor(), section.getTitle(),
                    section.getDate(), section.getNote(), section.getUser(), section.getAddress(),
                    section.getEdition(), section.getPublisher(), section.getSeries(), section.getVolume(),
                    section.getChapter(), section.getPages());
        }
        else if(reference.getClass() == BookReference.class){

            BookReference book = (BookReference) reference;
            return new BookReferenceDTO(book.getAuthor(), book.getTitle(), book.getDate(), book.getNote(),
                    book.getUser(), book.getAddress(), book.getEdition(), book.getPublisher(), book.getSeries(),
                    book.getVolume());
        }
        else if(reference.getClass() == BookLetReference.class){

            BookLetReference let = (BookLetReference) reference;
            return new BookLetReferenceDTO(let.getAuthor(), let.getTitle(), let.getDate(), let.getNote(),
                    let.getUser(), let.getAddress(), let.getHowpublished());
        }
        else if(reference.getClass() == ConferenceProceedingsReference.class){

            ConferenceProceedingsReference proceedings = (ConferenceProceedingsReference) reference;
            return new ConferenceProceedingsReferenceDTO(proceedings.getAuthor(), proceedings.getTitle(),
                    proceedings.getDate(), proceedings.getNote(), proceedings.getUser(),
                    proceedings.getAddress(), proceedings.getSeries(), proceedings.getVolume());
        }
        else if(reference.getClass() == ConferencePaperReference.class){

            ConferencePaperReference paper = (ConferencePaperReference) reference;
            return new ConferencePaperReferenceDTO(paper.getAuthor(), paper.getTitle(), paper.getDate(),
                    paper.getNote(), paper.getUser(), paper.getAddress(), paper.getPages(),
                    paper.getPublisher(), paper.getVolume());
        }
        else if(reference.getClass() == WebPageReference.class){

            WebPageReference web = (WebPageReference) reference;
            return new WebPageReferenceDTO(web.getAuthor(), web.getTitle(), web.getDate(), web.getNote(),
                    web.getUser(), web.getUrl(), web.getAccessDate());
        }
        else{
            ThesisReference thesis = (ThesisReference) reference;
            return new ThesisReferenceDTO(thesis.getAuthor(), thesis.getTitle(), thesis.getDate(),
                    thesis.getNote(), thesis.getUser(), thesis.getAddress(), thesis.getSchool(),
                    thesis.getType());
        }
    }*/


    //User
    public List<UserDTO> toUserDTOList(final List<User> userList) {

        List<UserDTO> userDTOList = new ArrayList<>();
        userList.forEach(user ->
                userDTOList.add(toUserDTO(user))
        );
        return userDTOList;
    }

    public List<User> toUserList(final List<UserDTO> userDTOList) {

        List<User> userList = new ArrayList<>();
        userDTOList.forEach(userDTO ->
                userList.add(toUser(userDTO))
        );
        return userList;
    }

    public UserDTO toUserDTO(final User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User toUser(final UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
