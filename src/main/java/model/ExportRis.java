package model;

import entity.*;
import io.github.codexrm.jris.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ExportRis implements Export {

    public ExportRis() {
        // Do nothing
    }

    @Override
    public void writeValue(ArrayList<Reference> referenceList, String path)
            throws IOException {

        RisManager manager = new RisManager();
        for (Reference reference : referenceList) {
          BaseReference entry = identifyType(reference);
            if (entry != null) {
                manager.addReference(entry);
            }
        }
        manager.exportListReference(path);
    }

    private BaseReference identifyType(Reference reference) {

        BaseReference entry = null;

        if (reference instanceof ArticleReference) {
            entry = createJournal((ArticleReference) reference);

        } else {
            if (reference instanceof BookSectionReference) {
                entry = createBookSection((BookSectionReference) reference);

            } else {
                if (reference instanceof BookReference) {
                    entry = createBook((BookReference) reference);

                } else {
                    if (reference instanceof ThesisReference) {
                        entry = createThesis((ThesisReference) reference);

                    } else {
                        if (reference instanceof ConferenceProceedingsReference) {
                            entry = createConferenceProceedings((ConferenceProceedingsReference) reference);

                        } else {
                            if (reference instanceof ConferencePaperReference) {
                                entry = createConferencePaper((ConferencePaperReference) reference);

                            } else {
                                if (reference instanceof WebPageReference) {
                                    entry = createWebPage((WebPageReference) reference);

                                } else {
                                    entry = null;
                                }
                            }
                        }
                    }
                }
            }
        }
        return entry;
    }

    private void addAuthors(String author, ArrayList<String> listAuthorReference) {

        String[] authors = author.split(";");
        Collections.addAll(listAuthorReference, authors);
    }

    private void commonField(Reference reference, BaseReference entry) {
        entry.setNotes(reference.getNote());
    }

    private JournalArticle createJournal(ArticleReference reference) {

        JournalArticle journalArticle = new JournalArticle();
        commonField(reference, journalArticle);
        addAuthors(reference.getAuthor(), journalArticle.getAuthorList());
        journalArticle.setTitle(reference.getTitle());
        journalArticle.setDate(reference.getDate());
        journalArticle.setJournal(reference.getJournal());
        journalArticle.setVolume(reference.getVolume());
        journalArticle.setNumber(reference.getNumber());
        journalArticle.setPages(reference.getPages());

        return journalArticle;
    }

    private Book createBook(BookReference reference) {

        Book book = new Book();
        commonField(reference, book);
        addAuthors(reference.getAuthor(), book.getAuthorList());
        book.setTitle(reference.getTitle());
        book.setDate(reference.getDate());
        book.setPublisher(reference.getPublisher());
        book.setVolume(reference.getVolume());
        book.setSeries(reference.getSeries());
        book.setAddress(reference.getAddress());
        book.setEdition(reference.getEdition());

        return book;
    }

    private BookSection createBookSection(BookSectionReference reference) {

        BookSection section = new BookSection();
        commonField(reference, section);
        addAuthors(reference.getAuthor(), section.getAuthorList());
        section.setTitle(reference.getTitle());
        section.setDate(reference.getDate());
        section.setPublisher(reference.getPublisher());
        section.setVolume(reference.getVolume());
        section.setSeries(reference.getSeries());
        section.setAddress(reference.getAddress());
        section.setEdition(reference.getEdition());
        section.setChapter(reference.getChapter());
        section.setPages(reference.getPages());

        return section;
    }

    private Thesis createThesis(ThesisReference reference) {

        Thesis thesis = new Thesis();
        commonField(reference, thesis);
        addAuthors(reference.getAuthor(), thesis.getAuthorList());
        thesis.setTitle(reference.getTitle());
        thesis.setDate(reference.getDate());
        thesis.setSchool(reference.getSchool());
        thesis.setAddress(reference.getAddress());
        if (reference.getType().equals("MASTERS")) {
            thesis.setThesisType("Masters");
        } else {
            thesis.setThesisType("phd");
        }
        return thesis;
    }

    private ConferenceProceedings createConferenceProceedings(ConferenceProceedingsReference reference) {

        ConferenceProceedings proceedings = new ConferenceProceedings();
        commonField(reference, proceedings);
        addAuthors(reference.getAuthor(), proceedings.getAuthorList());
        proceedings.setTitle(reference.getTitle());
        proceedings.setDate(reference.getDate());
        proceedings.setVolume(reference.getVolume());
        proceedings.setSeries(reference.getSeries());
        proceedings.setAddress(reference.getAddress());

        return proceedings;
    }

   private ConferencePaper createConferencePaper(ConferencePaperReference reference) {

        ConferencePaper paper = new ConferencePaper();
        commonField(reference, paper);
        addAuthors(reference.getAuthor(), paper.getAuthorList());
        paper.setTitle(reference.getTitle());
        paper.setDate(reference.getDate());
        paper.setVolume(reference.getVolume());
        paper.setPublisher(reference.getPublisher());
        paper.setAddress(reference.getAddress());
        paper.setPages(reference.getPages());

        return paper;
    }

    private WebPage createWebPage(WebPageReference reference) {

        WebPage webPage = new WebPage();
        commonField(reference, webPage);
        addAuthors(reference.getAuthor(), webPage.getAuthorList());
        webPage.setTitle(reference.getTitle());
        webPage.setDate(reference.getDate());
        webPage.setUrl(reference.getUrl());
        webPage.setAccessDate(reference.getAccessDate());

        return webPage;
    }
}
