package com.ir.cranfield;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

public class SearchEngine {

    public static final String INDEX_DIR = "./index";

    public static void main(String[] args) throws Exception  {

        // With Standard Analyzer
        Analyzer analyzer = new StandardAnalyzer();

        DocumentIndexer indexer = new DocumentIndexer();
        indexer.IndexDocuments(analyzer);

        DocQueries queries = new DocQueries();
        queries.QueryDocuments(analyzer);
    }
}
