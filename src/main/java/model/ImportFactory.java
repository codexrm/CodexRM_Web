package model;

import enums.Format;

public class ImportFactory {

    public ImportFactory() {
        // Do nothing
    }

    public Import getImport(Format type) {

        if (type.equals(Format.RIS)) {
            return new ImportRis();
        } else {
            return new ImportBibTex();
        }
    }
}
