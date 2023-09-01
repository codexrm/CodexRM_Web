package utils;

import entity.*;

public class ValidateReference {

    public Reference validateRequiredArticle(ArticleReference article){
        if (article.getAuthor() == null || article.getTitle() == null || article.getJournal() == null || article.getYear() == null ||
        article.getAuthor().equals("") || article.getTitle().equals("") || article.getJournal().equals("") || article.getYear().equals("")) {
            return null;
        } else {
            return article;
        }
    }

    public Reference validateRequiredBookSection(BookSectionReference section) {
        if (section.getChapter() == null || section.getPages() == null || section.getAuthor() == null || section.getEditor() == null || section.getTitle() == null || section.getPublisher() == null || section.getYear() == null ||
                section.getChapter().equals("") || section.getPages().equals("") || section.getAuthor().equals("") || section.getEditor().equals("") || section.getTitle().equals("") || section.getPublisher().equals("") || section.getYear().equals("")) {
            return null;
        } else {
            return section;
        }
    }

    public Reference validateRequiredBook(BookReference book){
        if (book.getAuthor() == null || book.getEditor() == null || book.getTitle() == null || book.getPublisher() == null || book.getYear() == null ||
                book.getAuthor().equals("") || book.getEditor().equals("") || book.getTitle().equals("") || book.getPublisher().equals("") || book.getYear().equals("")) {
            return null;
        } else {
            return book;
        }
    }

    public Reference validateRequiredBookLet(BookLetReference let){
        if (let.getTitle() == null || let.getAuthor() == null || let.getTitle().equals("") || let.getAuthor().equals("")) {
            return null;
        } else {
            return let;
        }
    }

    public Reference validateRequiredConferenceProceedings(ConferenceProceedingReference proceedings){
        if (proceedings.getTitle() == null || proceedings.getYear() == null || proceedings.getTitle().equals("") || proceedings.getYear().equals("")) {
            return null;
        } else {
           return proceedings;
        }
    }

    public Reference validateRequiredConferencePaper(ConferencePaperReference paper){
        if (paper.getAuthor() == null || paper.getTitle() == null  || paper.getBookTitle() == null  || paper.getYear() == null ||
                paper.getAuthor().equals("") || paper.getTitle().equals("") || paper.getBookTitle().equals("") || paper.getYear().equals("")) {
            return null;
        } else {
            return paper;
        }
    }

    public Reference validateRequiredThesis(ThesisReference thesis){
        if (thesis.getAuthor() == null || thesis.getTitle() == null || thesis.getSchool() == null || thesis.getYear() == null ||
                thesis.getAuthor().equals("") || thesis.getTitle().equals("") || thesis.getSchool().equals("") || thesis.getYear().equals("")) {
            return null;
        } else {
            return thesis;
        }
    }
}

