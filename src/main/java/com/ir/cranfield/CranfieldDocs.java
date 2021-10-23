package com.ir.cranfield;

import java.io.*;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CranfieldDocs {
    public static ArrayList<Map<String, String>> getDocuments() throws FileNotFoundException {

        Scanner scan = new Scanner(CranfieldDocs.class.getResourceAsStream("/CranfieldCollection/cran.all.1400"));

        ArrayList<Map<String, String>> docs = new ArrayList<>();

        scan.useDelimiter(Pattern.compile(".I"));
        while (scan.hasNext()) {
            String docRaw = scan.next();
            docs.add(formatDocument(docRaw));
        }

        return docs;
    }

    private static Map formatDocument(String docRaw){

        String[] rem = docRaw.split(".T");
        String id = rem[0];
        rem = rem[1].split(".A");
        String title = rem[0];
        rem = rem[1].split(".B");
        String author = rem[0];
        rem = rem[1].split(".W");
        String bibliography = rem[0];
        String content = rem[1];

        Map<String, String> doc = new HashMap<>();

        doc.put("id", id);
        doc.put("title", title);
        doc.put("author", author);
        doc.put("bibliography", bibliography);
        doc.put("content", content);

        return doc;
    }

    public static ArrayList<Map<String, String>> getQueries() throws FileNotFoundException {

        Scanner scan = new Scanner(CranfieldDocs.class.getResourceAsStream("/CranfieldCollection/cran.all.1400"));

        ArrayList<Map<String, String>> queries = new ArrayList<>();

        scan.useDelimiter(Pattern.compile(".I"));
        while (scan.hasNext()) {
            String queryRaw = scan.next();
            queries.add(formatQuery(queryRaw));
        }

        return queries;
    }

    private static Map formatQuery(String queryRaw){

        String[] rem = queryRaw.split(".W");

        Map<String, String> query = new HashMap<>();

        query.put("id", rem[0]);
        query.put("query", rem[1]);

        return query;
    }
}



