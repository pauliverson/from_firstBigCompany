import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;

public class parseString {
    public static void main(String[] args) throws IOException {
        String text = "可是，你是谁啊，你的小孩不听话怎么办？吃东坡肉啊！";

        //创建分词对象
        Analyzer anal = new IKAnalyzer(true);
        StringReader reader = new StringReader(text);
        //分词
        TokenStream ts = anal.tokenStream("", reader);
        CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
        //遍历分词数据
        int count = 0;
        while(ts.incrementToken()){
            if(term.toString().equals("东坡肉")) count++;
            System.out.print(term.toString() + "\n");

        }
        reader.close();
        System.out.println();
        System.out.println(count);
    }
}
