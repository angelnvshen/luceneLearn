package own.stu.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by dell on 2017/4/25.
 */
public class AnalyzerStu {

    private static String str = "极客学院，Lucene 案例开发";

    public static void print(Analyzer analyzer) throws IOException {
        StringReader reader = new StringReader(str);
        TokenStream tokenStream = analyzer.tokenStream("", reader);
        tokenStream.reset();

        CharTermAttribute termAttribute = tokenStream.getAttribute(CharTermAttribute.class);

        System.out.println("分词技术：" + analyzer.getClass());
        while (tokenStream.incrementToken()){
            System.out.print(termAttribute.toString() + "|");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        Analyzer analyzer = null;
        analyzer = new StandardAnalyzer();
        AnalyzerStu.print(analyzer);

        analyzer = new WhitespaceAnalyzer();
        AnalyzerStu.print(analyzer);

        analyzer = new SimpleAnalyzer();
        AnalyzerStu.print(analyzer);

        analyzer = new CJKAnalyzer();
        AnalyzerStu.print(analyzer);

        analyzer = new KeywordAnalyzer();
        AnalyzerStu.print(analyzer);

        analyzer = new StopAnalyzer();
        AnalyzerStu.print(analyzer);

        analyzer = new IKAnalyzer();
        AnalyzerStu.print(analyzer);

    }
}
