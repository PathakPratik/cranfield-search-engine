package com.ir.cranfield;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

public class SearchEngine {

    public static final String INDEX_DIR = "./index";

    public static void main(String[] args) throws Exception  {

        // With Standard Analyzer
        Analyzer StdAnalyzer = new StandardAnalyzer();

        DocumentIndexer indexer = new DocumentIndexer();
        indexer.IndexDocuments(StdAnalyzer);

        DocQueries queries = new DocQueries();
        queries.QueryDocuments(StdAnalyzer);

        // With WhitespaceAnalyzer Analyzer
        Analyzer WhtSpcAnalyzer = new WhitespaceAnalyzer();

        DocumentIndexer WhtSpcIndexer = new DocumentIndexer();
        WhtSpcIndexer.IndexDocuments(WhtSpcAnalyzer);

        DocQueries WhtSpcQueries = new DocQueries();
        WhtSpcQueries.QueryDocuments(WhtSpcAnalyzer);

        // With EnglishAnalyzer Analyzer
        Analyzer EnglishAnalyzer = new EnglishAnalyzer();

        DocumentIndexer EnglishIndexer = new DocumentIndexer();
        EnglishIndexer.IndexDocuments(EnglishAnalyzer);

        DocQueries EnglishQueries = new DocQueries();
        EnglishQueries.QueryDocuments(EnglishAnalyzer);

        // With SimpleAnalyzer Analyzer
        Analyzer SimpleAnalyzer = new SimpleAnalyzer();

        DocumentIndexer SimpleIndexer = new DocumentIndexer();
        SimpleIndexer.IndexDocuments(SimpleAnalyzer);

        DocQueries SimpleQueries = new DocQueries();
        SimpleQueries.QueryDocuments(SimpleAnalyzer);
    }
}
