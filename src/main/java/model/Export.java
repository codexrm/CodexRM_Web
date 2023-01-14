package model;

import entity.Reference;

import java.io.IOException;
import java.util.ArrayList;

public interface Export {

    void writeValue(ArrayList<Reference> referenceList, String path) throws IOException;
}