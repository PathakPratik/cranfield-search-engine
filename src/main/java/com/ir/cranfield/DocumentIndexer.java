package com.ir.cranfield;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DocumentIndexer {
    public void IndexDocuments(Analyzer analyzer) throws Exception  {

        IndexWriter writer = createWriter(analyzer);
        List<Document> documents = new ArrayList<>();

        for (Map doc: CranfieldDocs.getDocuments()) {
            Document document = createDocument(doc);
            documents.add(document);
        }

        writer.deleteAll();

        writer.addDocuments(documents);
        writer.commit();
        writer.close();

        System.out.println("Index Created");
    }

    private static IndexWriter createWriter(Analyzer analyzer) throws IOException {
        FSDirectory dir = FSDirectory.open(Paths.get(SearchEngine.INDEX_DIR));
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        IndexWriter writer = new IndexWriter(dir, config);
        return writer;
    }

    private static Document createDocument(Map doc)
    {
        Document document = new Document();
        document.add(new TextField("id", doc.get("id").toString() , Field.Store.YES));
        document.add(new TextField("title", doc.get("title").toString() , Field.Store.NO));
        document.add(new TextField("author", doc.get("author").toString() , Field.Store.NO));
        document.add(new TextField("bibliography", doc.get("bibliography").toString() , Field.Store.NO));
        document.add(new TextField("content", doc.get("content").toString() , Field.Store.NO));
        return document;
    }
}

