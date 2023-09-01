package utils;

import dto.*;
import entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DTOConverter {

    private final ModelMapper modelMapper;
    private final ValidateReference validation;

    public DTOConverter() {
        this.modelMapper = new ModelMapper();
        this.validation = new ValidateReference();
    }

    //Reference
    public ReferenceDTO toReferenceDTO(Reference reference) {

        if (reference.getClass() == ArticleReference.class) {
            return modelMapper.map(reference, ArticleReferenceDTO.class);
        } else if (reference.getClass() == BookSectionReference.class) {
            return modelMapper.map(reference, BookSectionReferenceDTO.class);
        } else if (reference.getClass() == BookReference.class) {
            return modelMapper.map(reference, BookReferenceDTO.class);
        } else if (reference.getClass() == BookLetReference.class) {
            return modelMapper.map(reference, BookLetReferenceDTO.class);
        } else if (reference.getClass() == ConferenceProceedingReference.class) {
            return modelMapper.map(reference, ConferenceProceedingsReferenceDTO.class);
        } else if (reference.getClass() == ThesisReference.class) {
            return modelMapper.map(reference, ThesisReferenceDTO.class);
        } else if (reference.getClass() == ConferencePaperReference.class) {
            return modelMapper.map(reference, ConferencePaperReferenceDTO.class);
        } else {
            return modelMapper.map(reference, WebPageReferenceDTO.class);
        }

    }

    public Reference toReference(ReferenceDTO referenceDTO) {

        if (referenceDTO.getClass() == ArticleReferenceDTO.class) {
            ArticleReference article = modelMapper.map(referenceDTO, ArticleReference.class);
            return validation.validateRequiredArticle(article);

        } else if (referenceDTO.getClass() == BookSectionReferenceDTO.class) {
            BookSectionReference section = modelMapper.map(referenceDTO, BookSectionReference.class);
            return validation.validateRequiredBookSection(section);

        } else if (referenceDTO.getClass() == BookReferenceDTO.class) {
            BookReference book = modelMapper.map(referenceDTO, BookReference.class);
            return validation.validateRequiredBook(book);

        } else if (referenceDTO.getClass() == BookLetReferenceDTO.class) {
            BookLetReference let = modelMapper.map(referenceDTO, BookLetReference.class);
            return validation.validateRequiredBookLet(let);

        } else if (referenceDTO.getClass() == ConferenceProceedingsReferenceDTO.class) {
            ConferenceProceedingReference proceedings = modelMapper.map(referenceDTO, ConferenceProceedingReference.class);
            return validation.validateRequiredConferenceProceedings(proceedings);

        } else if (referenceDTO.getClass() == ThesisReferenceDTO.class) {
            ThesisReference thesis = modelMapper.map(referenceDTO, ThesisReference.class);
            return validation.validateRequiredThesis(thesis);

        } else if (referenceDTO.getClass() == ConferencePaperReferenceDTO.class) {
            ConferencePaperReference paper = modelMapper.map(referenceDTO, ConferencePaperReference.class);
            return validation.validateRequiredConferencePaper(paper);

        } else {
            return modelMapper.map(referenceDTO, WebPageReference.class);
        }
    }

    public List<Reference> toReferenceList(List<ReferenceDTO> referenceDTOList) {

        List<Reference> referenceList = new ArrayList<>();
        for (ReferenceDTO referenceDTO : referenceDTOList) {
            Reference ref = toReference(referenceDTO);
            if (ref != null) {
                referenceList.add(ref);
            }
        }
        return referenceList;
    }

    //User
    public List<User> toUserList(final List<UserDetailsDTO> userDTOList) {

        List<User> userList = new ArrayList<>();
        userDTOList.forEach(userDTO ->
                userList.add(toUser(userDTO))
        );
        return userList;
    }

    public UserDetailsDTO toUserDTO(final User user) { return modelMapper.map(user, UserDetailsDTO.class); }

    public User toUser(final UserDetailsDTO userDTO) { return modelMapper.map(userDTO, User.class); }
}
