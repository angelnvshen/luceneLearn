package own.stu.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by dell on 2017/4/25.
 */
public class IndexSearch {
    public static void main(String[] args) throws IOException, ParseException {

        Directory directory = FSDirectory.open(Paths.get("E:\\own\\lucene\\index"));

        DirectoryReader reader = DirectoryReader.open(directory);

        IndexSearcher searcher = new IndexSearcher(reader);

        Analyzer analyzer = new StandardAnalyzer();
        QueryParser parser = new QueryParser("content", analyzer);

        Query query = parser.parse("中国");
        TopDocs docs = searcher.search(query, 10);
        if(docs != null){
            System.out.println("符合条件的文档总数为：" + docs.totalHits );
            for(ScoreDoc scoreDoc : docs.scoreDocs){
                Document doc = searcher.doc(scoreDoc.doc);
                System.out.println("id = " + doc.get("id"));
                System.out.println("content = " + doc.get("content"));
            }
        }

        directory.close();
        reader.close();
    }
}
