package model;

import entity.Reference;
import org.jbibtex.*;

import java.io.IOException;
import java.util.ArrayList;

public interface Import {

    ArrayList<Reference> readFile(String path) throws IOException, TokenMgrException, ParseException;
}