package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldValidations {


    public boolean validateAuthorOrEditor(String author) {
        if(author == null){
            return false;
        }else{
            final Pattern pat = Pattern.compile("^[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+,[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+[;(?=[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+,[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+)[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+,[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+]*");
            final Matcher mat = pat.matcher(author);
            if (mat.matches()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean validateAddress(String address) {
        if(address == null){
            return false;
        }else {
            final Pattern pat = Pattern.compile("^[A-ZÁÉÍÓÚÜÑ][A-ZÁÉÍÓÚÜÑa-záéíóúüñ\\s]*[A-ZÁÉÍÓÚÜÑa-záéíóúüñ]+,\\s[[A-Za-záéíóúüñÁÉÍÓÚÜÑ]+]*");
            final Matcher mat = pat.matcher(address);
            if (mat.matches()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean isNumber(final String number) {
        if(number == null){
            return false;
        }else {
            try {
                Long.valueOf(number);
                return true;

            } catch (final NumberFormatException e) {
                return false;
            }
        }
    }

    public boolean validateEdition(String edition) {
        if(edition == null){
            return false;
        }else {
            final Pattern pat = Pattern.compile("[A-ZÁÉÍÓÚÜÑa-záéíóúüñ0]+|\\d+\\.");
            final Matcher mat = pat.matcher(edition);
            if (mat.matches()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean validateIssn(String issn) {
        if(issn == null){
            return false;
        }else {
            final Pattern pat = Pattern.compile("\\d{4}-\\d{4}|\\d{4}-\\d{3}X");
            final Matcher mat = pat.matcher(issn);
            if (mat.matches()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean validateIsbn(String isbn) {
        if(isbn == null){
            return false;
        }else {
            final Pattern pat = Pattern.compile("^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$");
            final Matcher mat = pat.matcher(isbn);
            if (mat.matches()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean validateNumber(String number) {
        if(number == null){
            return false;
        }else {
            final Pattern pat = Pattern.compile("[A-ZÁÉÍÓÚÜÑa-záéíóúüñ0-9\\s-]+");
            final Matcher mat = pat.matcher(number);
            if (mat.matches()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean validatePages(String pages) {
        if(pages == null){
            return false;
        }else {
            final Pattern pat = Pattern.compile("[IVXMLCD]+|[IVXMLCD]+,[IVXMLCD]+|[IVXMLCD]+-[IVXMLCD]+|[0-9]+|[0-9]+,[0-9]+|[0-9]+-[0-9]+");
            final Matcher mat = pat.matcher(pages);
            if (mat.matches()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean validateSeries(String serie) {
        if(serie == null){
            return false;
        }else {
            final Pattern pat = Pattern.compile("[A-ZÁÉÍÓÚÜÑa-záéíóúüñ\\s]+");
            final Matcher mat = pat.matcher(serie);
            if (mat.matches()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean validateUrl(String url) {
        if(url == null){
            return false;
        }else {
            final Pattern pat = Pattern.compile("^https://.*");
            final Matcher mat = pat.matcher(url);
            if (mat.matches()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean validateYear(String year) {
        if(year == null){
            return false;
        }else {
            final Pattern pat = Pattern.compile("\\d{4}|\\d{4}--\\d{4}");
            final Matcher mat = pat.matcher(year);
            if (mat.matches()) {
                return true;
            } else {
                return false;
            }
        }
    }
}
