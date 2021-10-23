package com.ir.cranfield;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.*;
import java.nio.file.Paths;
import java.util.Map;

public class DocQueries {
    public void QueryDocuments(Analyzer analyzer) throws Exception {
        IndexSearcher searcher = createSearcher();

        File fout = new File("../CranfieldCollection/result-file"+analyzer);
        FileOutputStream fos = new FileOutputStream(fout);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        Integer queryID = 1;
        for (Map query: CranfieldDocs.getQueries()) {

            TopDocs results = search( query.get("query").toString(), searcher, analyzer);

            String resultLine = "";

            Integer rank = 1;
            for (ScoreDoc sd : results.scoreDocs) {
                resultLine += queryID + " " + "Q0 " + (sd.doc + 1) + " " + rank +" " + sd.score + " " + analyzer + "\n";
                rank++;
            }

            bw.write(resultLine);
            queryID++;
        }

        bw.close();

        System.out.println("Queries Done");
    }

    private static IndexSearcher createSearcher() throws IOException
    {
        Directory dir = FSDirectory.open(Paths.get(SearchEngine.INDEX_DIR));
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        return searcher;
    }

    private static TopDocs search(String term, IndexSearcher searcher, Analyzer analyzer) throws Exception
    {
        QueryParser qp = new QueryParser("content", analyzer);
        qp.setAllowLeadingWildcard(true);
//        String special = "title:" + term + " OR content:" + term;
//        Query idQuery = qp.parse(special);
        Query idQuery = qp.parse(qp.parse("content").toString());
        TopDocs hits = searcher.search(idQuery, 50);
        return hits;
    }
}
