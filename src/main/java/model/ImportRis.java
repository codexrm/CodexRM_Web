package model;

import entity.*;
import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;
import utils.ValidateReference;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImportRis {

    private final ValidateReference validation;

    public ImportRis() {
        this.validation = new ValidateReference();
    }

    public ArrayList<Reference> readFile(String path) throws IOException, TokenMgrException, ParseException {
        ArrayList<Reference> referenceList = new ArrayList<>();
        Reader reader = new FileReader(path);

        final ArrayList<String[]> listString = new ArrayList<>();
        final BufferedReader br = new BufferedReader(reader);
        String line;

        while ((line = br.readLine()) != null) {
            String[] partLine = validateExpression(line);
            if (partLine.length != 0) {
                final String field = partLine[0].trim();
                if (field.equals("TY")) {
                    listString.add(partLine);
                    while (!line.equals("ER  - ")) {
                        line = br.readLine();
                        partLine = validateExpression(line);
                        if (partLine.length != 0) {
                            listString.add(partLine);
                        }
                    }
                    final Reference reference = createReference(listString);
                    if (reference != null) {
                        referenceList.add(reference);
                    }
                    listString.clear();
                }
            }
        }
        br.close();



        return referenceList;
    }

    private String[] validateExpression(final String line) {

        final Pattern pat = Pattern.compile("^[A-Z][A-Z1-9]\\s\\s-\\s.*");
        final Matcher mat = pat.matcher(line);
        if (mat.matches()) {
            return line.split("-", 2);
        } else {

            return new String[0];
        }
    }

    private Reference createReference(final ArrayList<String[]> listPartLine) {

        String field = listPartLine.get(0)[0];
        String content = listPartLine.get(0)[1];
        field = field.trim();
        content = content.trim();
        if (field.equals("TY")) {
            final Reference reference;
            switch (content) {
                case "ABST":
                case "INPR":
                case "JFULL":
                case "JOUR":
                    reference = createArticle(listPartLine);
                    break;
                case "BOOK":
                    reference = createBook(listPartLine);
                    break;
                case "CHAP":
                    reference = createBookSection(listPartLine);
                    break;
                case "THES":
                    reference = createThesis(listPartLine);
                    break;
                case "CONF":
                    reference = createConferenceProceeding(listPartLine);
                    break;
                case "CPAPER":
                    reference = createConferencePaper(listPartLine);
                    break;
                case "ELEC":
                    reference = createWebPage(listPartLine);
                    break;
                default:
                    reference = null;
            }
            return reference;

        } else return null;
    }

    private Reference createArticle(final ArrayList<String[]> listPartLine) {

        final ArticleReference journal = new ArticleReference();
        for (int i = 1; i < listPartLine.size() - 1; i++) {
            String field = listPartLine.get(i)[0];
            String content = listPartLine.get(i)[1];
            field = field.trim();
            content = content.trim();

            switch (field) {
                case "AU":
                    journal.setAuthor(content);
                    break;
                case "TI":
                    journal.setTitle(content);
                    break;
                case "PY":
                    journal.setYear(content);
                    break;
                case "N1":
                    journal.setNote(content);
                    break;
                case "T2":
                    journal.setJournal(content);
                    break;
                case "VL":
                    journal.setVolume(content);
                    break;
                case "C7":
                    journal.setNumber(content);
                    break;
                case "SP":
                    journal.setPages(content);
                    break;
                case "SN":
                    journal.setIssn(content);
                    break;
                default:
            }
        }
        return validation.validateRequiredArticle(journal);
    }

    private Reference createBook(final ArrayList<String[]> listPartLine) {

        final BookReference book = new BookReference();
        for (int i = 1; i < listPartLine.size(); i++) {
            String field = listPartLine.get(i)[0];
            String content = listPartLine.get(i)[1];
            field = field.trim();
            content = content.trim();

            switch (field) {
                case "AU":
                    book.setAuthor(content);
                    break;
                case "A3":
                    book.setEditor(content);
                    break;
                case "TI":
                    book.setTitle(content);
                    break;
                case "PY":
                    book.setYear(content);
                    break;
                case "N1":
                    book.setNote(content);
                    break;
                case "NV":
                    book.setNumber(content);
                    break;
                case "PB":
                    book.setPublisher(content);
                    break;
                case "VL":
                    book.setVolume(content);
                    break;
                case "T2":
                    book.setSeries(content);
                    break;
                case "CY":
                    book.setAddress(content);
                    break;
                case "SN":
                    book.setIsbn(content);
                    break;
                case "ET":
                    book.setEdition(content);
                    break;
                default:
            }
        }
        return validation.validateRequiredBook(book);
    }

    private Reference createBookSection(final ArrayList<String[]> listPartLine) {

        final BookSectionReference section = new BookSectionReference();
        for (int i = 1; i < listPartLine.size(); i++) {
            String field = listPartLine.get(i)[0];
            String content = listPartLine.get(i)[1];
            field = field.trim();
            content = content.trim();

            switch (field) {
                case "AU":
                    section.setAuthor(content);
                    break;
                case "A2":
                    section.setEditor(content);
                    break;
                case "TI":
                    section.setTitle(content);
                    break;
                case "PY":
                    section.setYear(content);
                    break;
                case "N1":
                    section.setNote(content);
                    break;
                case "PB":
                    section.setPublisher(content);
                    break;
                case "VL":
                    section.setVolume(content);
                    break;
                case "T3":
                    section.setSeries(content);
                    break;
                case "IS":
                    section.setNumber(content);
                    break;
                case "SN":
                    section.setIsbn(content);
                    break;
                case "CY":
                    section.setAddress(content);
                    break;
                case "ET":
                    section.setEdition(content);
                    break;
                case "SE":
                    section.setChapter(content);
                    break;
                case "SP":
                    section.setPages(content);
                    break;
                default:
            }
        }
        return validation.validateRequiredBookSection(section);
    }

    private Reference createThesis(final ArrayList<String[]> listPartLine) {

        final ThesisReference thesis = new ThesisReference();
        for (int i = 1; i < listPartLine.size(); i++) {
            String field = listPartLine.get(i)[0];
            String content = listPartLine.get(i)[1];
            field = field.trim();
            content = content.trim();

            switch (field) {
                case "AU":
                    thesis.setAuthor(content);
                    break;
                case "TI":
                    thesis.setTitle(content);
                    break;
                case "PY":
                    thesis.setYear(content);
                    break;
                case "N1":
                    thesis.setNote(content);
                    break;
                case "PB":
                    thesis.setSchool(content);
                    break;
                case "M3":
                    thesis.setType(content);
                    break;
                case "CY":
                    thesis.setAddress(content);
                    break;
                default:
            }
        }
        return validation.validateRequiredThesis(thesis);
    }

    private Reference createConferenceProceeding(final ArrayList<String[]> listPartLine) {

        final ConferenceProceedingReference proceedings = new ConferenceProceedingReference();
        for (int i = 1; i < listPartLine.size(); i++) {
            String field = listPartLine.get(i)[0];
            String content = listPartLine.get(i)[1];
            field = field.trim();
            content = content.trim();

            switch (field) {
                case "A2":
                    proceedings.setEditor(content);
                    break;
                case "TI":
                    proceedings.setTitle(content);
                    break;
                case "C2":
                    proceedings.setYear(content);
                    break;
                case "N1":
                    proceedings.setNote(content);
                    break;
                case "VL":
                    proceedings.setVolume(content);
                    break;
                case "T3":
                    proceedings.setSeries(content);
                    break;
                case "NV":
                    proceedings.setNumber(content);
                    break;
                case "CY":
                    proceedings.setAddress(content);
                    break;
                case "PB":
                    proceedings.setPublisher(content);
                    break;
                default:
            }
        }
        return validation.validateRequiredConferenceProceedings(proceedings);
    }

    private Reference createConferencePaper(final ArrayList<String[]> listPartLine) {

        final ConferencePaperReference paper = new ConferencePaperReference();
        for (int i = 1; i < listPartLine.size(); i++) {
            String field = listPartLine.get(i)[0];
            String content = listPartLine.get(i)[1];
            field = field.trim();
            content = content.trim();

            switch (field) {
                case "AU":
                    paper.setAuthor(content);
                    break;
                case "A2":
                    paper.setEditor(content);
                    break;
                case "TI":
                    paper.setTitle(content);
                    break;
                case "PY":
                    paper.setYear(content);
                    break;
                case "N1":
                    paper.setNote(content);
                    break;
                case "VL":
                    paper.setVolume(content);
                    break;
                case "PB":
                    paper.setPublisher(content);
                    break;
                case "SP":
                    paper.setPages(content);
                    break;
                case "CY":
                    paper.setAddress(content);
                    break;
                default:
            }
        }
        return validation.validateRequiredConferencePaper(paper);
    }

    private Reference createWebPage(final ArrayList<String[]> listPartLine) {

        final WebPageReference webPage = new WebPageReference();
        for (int i = 1; i < listPartLine.size(); i++) {
            String field = listPartLine.get(i)[0];
            String content = listPartLine.get(i)[1];
            field = field.trim();
            content = content.trim();

            switch (field) {
                case "AU":
                    webPage.setAuthor(content);
                    break;
                case "TI":
                    webPage.setTitle(content);
                    break;
                case "PY":
                    webPage.setYear(content);
                    break;
                case "N1":
                    webPage.setNote(content);
                    break;
                case "UR":
                    webPage.setUrl(content);
                    break;
                default:
            }
        }
        return webPage;
    }
}


