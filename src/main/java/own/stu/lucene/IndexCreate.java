package own.stu.lucene;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 * lucene 索引创建
 * Created by dell on 2017/4/25.
 */
public class IndexCreate {
    public static void main(String[] args) throws IOException {
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

        Directory directory = null;
        IndexWriter indexWriter = null;

        directory = FSDirectory.open(Paths.get("E:\\own\\lucene\\index"));

        indexWriter = new IndexWriter(directory, indexWriterConfig);

        Document doc1 = new Document();
        doc1.add(new StringField("id", "abcde", Field.Store.YES));
        doc1.add(new TextField("content", "你好中国", Field.Store.YES));

        indexWriter.addDocument(doc1);

        Document doc2 = new Document();
        doc2.add(new StringField("id", "wefwfewfwf", Field.Store.YES));
        doc2.add(new TextField("content", "你好上海", Field.Store.YES));

        indexWriter.addDocument(doc2);

        indexWriter.commit();

        indexWriter.close();
        directory.close();
    }
}
