import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.ParallelLayeredWordCloud;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.bg.PixelBoundryBackground;
import com.kennycason.kumo.bg.RectangleBackground;
import com.kennycason.kumo.font.KumoFont;
import com.kennycason.kumo.font.scale.LinearFontScalar;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.image.AngleGenerator;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.nlp.normalize.*;
import com.kennycason.kumo.nlp.tokenizer.WordTokenizer;
import com.kennycason.kumo.nlp.tokenizers.ChineseWordTokenizer;
import com.kennycason.kumo.palette.ColorPalette;
import com.kennycason.kumo.palette.LinearGradientColorPalette;
import com.kennycason.kumo.wordstart.CenterWordStart;


import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import static java.awt.Color.*;

public class worldcloud {
    public static void main(String[] args)throws IOException{
        buildwcpng();
//        buildStopwordList();
    }
//
    public static void buildwcpng() throws IOException {
        //建立词频分析器，设置词频，以及词语最短长度，此处的参数配置视情况而定即可
        FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        List<String> stopwordList = new ArrayList<>();
        stopwordList.add("工作");
        stopwordList.add("以上");
        stopwordList.add("岗位");
        stopwordList.add("客户");
        stopwordList.add("公司");
        stopwordList.add("优先");
        stopwordList.add("良好");
        stopwordList.add("产品");
        stopwordList.add("要求");
        stopwordList.add("负责");
        stopwordList.add("相关");
        stopwordList.add("能力");
        stopwordList.add("广告");
        stopwordList.add("职位");
        stopwordList.add("完成");
        stopwordList.add("员工");
        stopwordList.add("团队");
        stopwordList.add("管理");
        stopwordList.add("需求");
        stopwordList.add("行业");
        stopwordList.add("进行");
        stopwordList.add("具备");
        stopwordList.add("熟悉");
        stopwordList.add("la");
        stopwordList.add("+六");
        stopwordList.add("用户");
        stopwordList.add("具有");
        stopwordList.add("方案");
        stopwordList.add("了解");
        stopwordList.add("每个");
        stopwordList.add("销售");
        stopwordList.add("++");
        frequencyAnalyzer.setStopWords(stopwordList);
        frequencyAnalyzer.setWordFrequenciesToReturn(800);
        frequencyAnalyzer.setMinWordLength(2);
        //引入中文解析器
        WordTokenizer wordTokenizer = new ChineseWordTokenizer();

//        frequencyAnalyzer.addNormalizer();
        frequencyAnalyzer.setWordTokenizer(wordTokenizer);
        //指定文本文件路径，生成词频集合
        final List<WordFrequency> wordFrequencyList = frequencyAnalyzer.load("E:\\爬虫/tagwordlist.txt");
        //设置图片分辨率
        Dimension dimension = new Dimension(600,600);
        //此处的设置采用内置常量即可，生成词云对象
        WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
        //设置边界及字体
        wordCloud.setPadding(2);
        java.awt.Font font = new java.awt.Font("STSong-Light", 2, 30);
//        //设置词云显示的三种颜色，越靠前设置表示词频越高的词语的颜色
        wordCloud.setColorPalette(new LinearGradientColorPalette(Color.RED, Color.BLUE, Color.GREEN, 30, 30));
//        wordCloud.setColorPalette(new ColorPalette(new Color(0xD5CFFA), new Color(0xBBB1FA), new Color(0x9A8CF5), new Color(0x806EF5)));

        wordCloud.setKumoFont(new KumoFont(font));
        //设置背景色
        wordCloud.setBackgroundColor(new Color(255,255,255));
        //设置背景图片
        //wordCloud.setBackground(new PixelBoundryBackground("E:\\爬虫/google.jpg"));
        //设置背景图层为圆形
        wordCloud.setBackground(new CircleBackground(300));
        wordCloud.setFontScalar(new SqrtFontScalar(12, 45));

        File filestopword = new File("E:\\\\爬虫/wordcloudlist.txt");
        FileWriter fw = null;
        BufferedWriter bw = null;
        Iterator<WordFrequency> iter = wordFrequencyList.iterator();
        try {
            fw = new FileWriter(filestopword);
            bw = new BufferedWriter(fw);
            while(iter.hasNext()) {
                bw.write(String.valueOf(iter.next()));
                bw.newLine();
            }
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //生成词云
        wordCloud.build(wordFrequencyList);
        wordCloud.writeToFile("E:\\爬虫/result6.png");

    }
//    public static List<String> buildStopwordList() throws IOException {
//        File file = new File("E:\\\\爬虫/stopwordList.txt");
//        List<String> stopwordList = new ArrayList<>();
//        String lineinfo = "";
//        if(file.isFile()&&file.exists()){
//            InputStreamReader read = new InputStreamReader(new FileInputStream(file));
//            BufferedReader br = new BufferedReader(read);
//            while((lineinfo=br.readLine())!=null){
//                String str = lineinfo;
//                stopwordList.add(str);
//            }
//            read.close();
//        }
//        System.out.println(stopwordList);
//        return stopwordList;
//    }
}


