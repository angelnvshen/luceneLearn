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
import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.cfg.DefaultConfig;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import org.wltea.analyzer.lucene.IKAnalyzer;
import own.stu.util.analyzer.MyIkAnalyzer;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/4/25.
 */
public class AnalyzerStu {

//    private static String str = "极客学院，Lucene 案例开发";
    private static String str = "java是一个好语言从到2015年12月1日它已经有20年的历史了";

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
        /*analyzer = new StandardAnalyzer();
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
        AnalyzerStu.print(analyzer);*/

        analyzer = new MyIkAnalyzer();
        AnalyzerStu.print(analyzer);

        analyzer = new MyIkAnalyzer(true);
        AnalyzerStu.print(analyzer);

    }
}
