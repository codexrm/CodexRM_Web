package model;

import entity.*;
import org.jbibtex.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ImportBibTex implements Import {

    public ImportBibTex() {
        super();
    }

    @Override
    public ArrayList<Reference> readFile(String path)
            throws FileNotFoundException, TokenMgrException, ParseException {

        Reader reader = new FileReader(path);
        BibTeXParser bibtexParser = new BibTeXParser();
        BibTeXDatabase database = bibtexParser.parseFully(reader);
        Map<Key, BibTeXEntry> entryMap = database.getEntries();
        Collection<BibTeXEntry> entries = entryMap.values();
        ArrayList<Reference> listReference = new ArrayList<>();

        Reference reference = new Reference();
        for (BibTeXEntry entry : entries) {

            if (entry.getType().toString().equalsIgnoreCase("article")) {
                reference = createArticleReference(entry);
            } else {
                if (entry.getType().toString().equalsIgnoreCase("book")) {
                    reference = createBookReference(entry);
                } else {
                    if (entry.getType().toString().equalsIgnoreCase("inbook")) {
                        reference = createBookSectionReference(entry);
                    } else {
                        if (entry.getType().toString().equalsIgnoreCase("booklet")) {
                            reference = createBookLetReference(entry);
                        } else {
                            if (entry.getType().toString().equalsIgnoreCase("mastersthesis")
                                    || entry.getType().toString().equalsIgnoreCase("phdthesis")) {
                                reference = createThesisReference(entry);
                            } else {
                                if (entry.getType().toString().equalsIgnoreCase("proceedings")) {
                                    reference = createConferenceProceedingsReference(entry);
                                }else{
                                    if (entry.getType().toString().equalsIgnoreCase("conference")) {
                                        reference = createConferencePaperReference(entry);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (reference != null) {
                listReference.add(reference);
            }
            reference = null;
        }
        return listReference;
    }

    private String createAuthorField(String content) {

        String[] authors = content.split(" and ", 2);
        StringBuilder author = new StringBuilder(authors[0]);

        for (int i = 1; i < authors.length; i++) {
           author.append(";").append(authors[i]);
        }
        return author.toString();
    }

    private void commonField(BibTeXEntry entry, Reference reference) {
        Value value = entry.getField(BibTeXEntry.KEY_AUTHOR);
        if (value != null) {
            reference.setAuthor(createAuthorField(value.toUserString()));
        }
        value = entry.getField(BibTeXEntry.KEY_TITLE);
        if (value != null) {
            reference.setTitle(value.toUserString());
        }
        establishDate(entry, reference);

        value = entry.getField(BibTeXEntry.KEY_NOTE);
        if (value != null) {
            reference.setNote(value.toUserString());
        }
    }

    private String getMonth(String date) {

        switch (date) {
            case "January":
                return "01";
            case "February":
                return "02";
            case "March":
                return "03";
            case "April":
                return "04";
            case "May":
                return "05";
            case "June":
                return "06";
            case "July":
                return "07";
            case "August":
                return "08";
            case "September":
                return "09";
            case "October":
                return "10";
            case "November":
                return "11";
            default:
                return "12";
        }
    }

    private boolean isNumber(final String number) {
        try {
            Long.valueOf(number);
            return true;

        } catch (final NumberFormatException e) {
            return false;
        }
    }

    private void establishDate(BibTeXEntry entry, Reference reference) {
        String year = null;
        String month = null;
        String date = null;
        Value value = entry.getField(BibTeXEntry.KEY_AUTHOR);

        value = entry.getField(BibTeXEntry.KEY_MONTH);
        if (value != null && isNumber(value.toUserString())) {
            month = getMonth(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_YEAR);
        if (value != null && isNumber(value.toUserString())) {
            year = value.toUserString();
        }
        if (year != null && month != null) {
            date = year + "/" + month + "/01";
        } else {
            if (month == null) {
                date = year + "/01/02";
            }
        }
        if (date != null)
            reference.setDate(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy/MM/dd")));
    }

    private Reference createArticleReference(BibTeXEntry entry) {
        ArticleReference article = new ArticleReference();
        commonField(entry, article);
        Value value = entry.getField(BibTeXEntry.KEY_JOURNAL);
        if (value != null) {
            article.setJournal(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_NUMBER);
        if (value != null) {
            article.setNumber(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_PAGES);
        if (value != null) {
            article.setPages(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_VOLUME);
        if (value != null) {
            article.setVolume(value.toUserString());
        }
        return article;
    }

    private Reference createBookReference(BibTeXEntry entry) {
        BookReference book = new BookReference();
        commonField(entry, book);
        createBook(entry, book);
        return book;
    }

    private void createBook(BibTeXEntry entry, BookReference book) {
        commonField(entry, book);
        Value value = entry.getField(BibTeXEntry.KEY_ADDRESS);
        if (value != null) {
            book.setAddress(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_EDITION);
        if (value != null) {
            book.setEdition(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_PUBLISHER);
        if (value != null) {
            book.setPublisher(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_SERIES);
        if (value != null) {
            book.setSeries(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_VOLUME);
        if (value != null) {
            book.setVolume(value.toUserString());
        }
    }

    private Reference createBookSectionReference(BibTeXEntry entry) {
        BookSectionReference bookSection = new BookSectionReference();
        commonField(entry, bookSection);
        createBook(entry, bookSection);
        Value value = entry.getField(BibTeXEntry.KEY_CHAPTER);
        if (value != null) {
            bookSection.setChapter(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_PAGES);
        if (value != null) {
            bookSection.setPages(value.toUserString());
        }
        return bookSection;
    }

    private Reference createBookLetReference(BibTeXEntry entry) {
        BookLetReference bookLet = new BookLetReference();
        commonField(entry, bookLet);

        Value value = entry.getField(BibTeXEntry.KEY_ADDRESS);
        if (value != null) {
            bookLet.setAddress(value.toUserString());
        }

        value = entry.getField(BibTeXEntry.KEY_HOWPUBLISHED);
        if (value != null) {
            bookLet.setHowpublished(value.toUserString());
        }
        return bookLet;
    }

    private Reference createThesisReference(BibTeXEntry entry) {
        ThesisReference thesis = new ThesisReference();
        commonField(entry, thesis);

        Value value = entry.getField(BibTeXEntry.KEY_ADDRESS);
        if (value != null) {
            thesis.setAddress(value.toUserString());
        }

        value = entry.getField(BibTeXEntry.KEY_SCHOOL);
        if (value != null) {
            thesis.setSchool(value.toUserString());
        }

        if (entry.getType().getValue().equals("mastersthesis")) {
            thesis.setType("MASTERS");
        } else {
            thesis.setType("PHD");
        }
        return thesis;
    }

    private Reference createConferenceProceedingsReference(BibTeXEntry entry) {

        ConferenceProceedingsReference proceedings = new ConferenceProceedingsReference();
        commonField(entry, proceedings);

        Value value = entry.getField(BibTeXEntry.KEY_ADDRESS);
        if (value != null) {
            proceedings.setAddress(value.toUserString());
        }

        value = entry.getField(BibTeXEntry.KEY_SERIES);
        if (value != null) {
            proceedings.setSeries(value.toUserString());
        }

        value = entry.getField(BibTeXEntry.KEY_VOLUME);
        if (value != null) {
            proceedings.setVolume(value.toUserString());
        }
        return proceedings;
    }

    private Reference createConferencePaperReference(BibTeXEntry entry) {

        ConferencePaperReference paper = new ConferencePaperReference();
        commonField(entry, paper);

        Value value = entry.getField(BibTeXEntry.KEY_VOLUME);
        if (value != null) {
            paper.setAddress(value.toUserString());
        }

        value = entry.getField(BibTeXEntry.KEY_PUBLISHER);
        if (value != null) {
            paper.setPublisher(value.toUserString());
        }

        value = entry.getField(BibTeXEntry.KEY_ADDRESS);
        if (value != null) {
            paper.setAddress(value.toUserString());
        }

        value = entry.getField(BibTeXEntry.KEY_PAGES);
        if (value != null) {
            paper.setPages(value.toUserString());
        }
        return paper;
    }
}
