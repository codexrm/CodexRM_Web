package utils;

import enums.Format;
import io.github.codexrm.EILibrary.enums.*;

public class EnumsConverter {

    public EnumsConverter() {}

    public MonthsLibrary getMonthLibrary(String months) {

        if (months != null) {
            switch (months) {
                case "jan":
                    return MonthsLibrary.jan;
                case "feb":
                    return MonthsLibrary.feb;
                case "mar":
                    return MonthsLibrary.mar;
                case "apr":
                    return MonthsLibrary.apr;
                case "may":
                    return MonthsLibrary.may;
                case "jun":
                    return MonthsLibrary.jun;
                case "jul":
                    return MonthsLibrary.jul;
                case "aug":
                    return MonthsLibrary.aug;
                case "sep":
                    return MonthsLibrary.sep;
                case "oct":
                    return MonthsLibrary.oct;
                case "nov":
                    return MonthsLibrary.nov;
                case "dec":
                    return MonthsLibrary.dec;
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    public String getMonth(MonthsLibrary months) {

        if (months != null) {
            switch (months) {
                case jan:
                    return "jan";
                case feb:
                    return "feb";
                case mar:
                    return "mar";
                case apr:
                    return "apr";
                case may:
                    return "may";
                case jun:
                    return "jun";
                case jul:
                    return "jul";
                case aug:
                    return "aug";
                case sep:
                    return "sep";
                case oct:
                    return "oct";
                case nov:
                    return "nov";
                case dec:
                    return "dec";
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    public FormatLibrary getFormatLibrary(Format format) {

        if (format != null) {
            switch (format) {
                case RIS:
                    return FormatLibrary.RIS;
                case BIBTEX:
                    return FormatLibrary.BIBTEX;
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    public Format getFormat(String format) {

        if (format != null) {
            switch (format) {
                case "Ris":
                    return Format.RIS;
                case "BibTeX":
                    return Format.BIBTEX;
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    public BookSectionTypeLibrary getBookSectionTypeLibrary(String type) {

        if (type != null) {
            switch (type) {
                case "AUDIOCD":
                    return BookSectionTypeLibrary.AUDIOCD;
                case "CANDTHESIS":
                    return BookSectionTypeLibrary.CANDTHESIS;
                case "DataCD":
                    return BookSectionTypeLibrary.DataCD;
                case "MATHESIS":
                    return BookSectionTypeLibrary.MATHESIS;
                case "PHDTHESIS":
                    return BookSectionTypeLibrary.PHDTHESIS;
                case "RESREPORT":
                    return BookSectionTypeLibrary.RESREPORT;
                case "SOFTWARE":
                    return BookSectionTypeLibrary.SOFTWARE;
                case "TECHREPORT":
                    return BookSectionTypeLibrary.TECHREPORT;
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    public String getBookSectionType(BookSectionTypeLibrary type) {

        if (type != null) {
            switch (type) {
                case AUDIOCD:
                    return "AUDIOCD";
                case CANDTHESIS:
                    return "CANDTHESIS";
                case DataCD:
                    return "DataCD";
                case MATHESIS:
                    return "MATHESIS";
                case PHDTHESIS:
                    return "PHDTHESIS";
                case RESREPORT:
                    return "RESREPORT";
                case SOFTWARE:
                    return "SOFTWARE";
                case TECHREPORT:
                    return "TECHREPORT";
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    public ThesisTypeLibrary getThesisTypeLibrary(String type) {

        if (type != null) {
            switch (type) {
                case "MASTERS":
                    return ThesisTypeLibrary.MASTERS;
                case "PHD":
                    return ThesisTypeLibrary.PHD;
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    public String getThesisType(ThesisTypeLibrary type) {

        if (type != null) {
            switch (type) {
                case MASTERS:
                    return "MASTERS";
                case PHD:
                    return "PHD";
                default:
                    return null;
            }
        } else {
            return null;
        }
    }
}
