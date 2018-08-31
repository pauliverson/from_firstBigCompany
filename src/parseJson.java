import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class parseJson {
    public static void main(String args[]){
        loadImgURL();
    }

    public static String getFieldByUrl(){
        String tecUrl = "https://www.zhipin.com/common/data/positionSkill?positonLv2=100100";
        String productUrl = "https://www.zhipin.com/common/data/positionSkill?positonLv2=110100";
        String operationsUrl = "https://www.zhipin.com/common/data/positionSkill?positonLv2=130100";
        String Jsonurl = "https://www.zhipin.com/common/data/position.json";
        String result = "";
        BufferedReader in = null;

        try {
            String urlNameString = Jsonurl;
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.connect();
            Map<String,List<String>> map = connection.getHeaderFields();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while((line = in.readLine())!=null)
                result+=line;
        } catch (MalformedURLException e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        finally {
            try{
                if(in != null)
                    in.close();
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }
        //得到的json数据
        //System.out.println(result);
        //解析
        HashSet<String> operationsSet = new HashSet<>();
        HashSet<String> tecSet = new HashSet<>();
        HashSet<String> productSet = new HashSet<>();
        String title = "";
        String position = "";
        JSONObject jsonObj = JSON.parseObject(result);
        JSONObject object_1=null;
        JSONObject object_2=null;
        JSONObject object_3=null;
        JSONArray jarr =  jsonObj.getJSONArray("data");
        for(int i = 0;i < jarr.size();i++){
            object_1 = jarr.getJSONObject(i);
            JSONArray jarr_sub =  object_1.getJSONArray("subLevelModelList");
            for(int j = 0;j < jarr_sub.size();j++){
                object_2 = jarr_sub.getJSONObject(j);
                if(object_2.getString("name").equals("产品经理"))
                {
                    System.out.println(object_2.getString("name"));
                    title=object_2.getString("name");
                    JSONArray jarr_sub_sub =  object_2.getJSONArray("subLevelModelList");
                    for(int k = 0;k < jarr_sub_sub.size();k++){
                        object_3 = jarr_sub_sub.getJSONObject(k);
//                    System.out.println(object_3.getString("name"));
                        position=object_3.getString("name");
                        productSet.add(title+"##"+position);
                    }
                }
                if(object_2.getString("name").equals("运营"))
                {
                    System.out.println(object_2.getString("name"));
                    title=object_2.getString("name");
                    JSONArray jarr_sub_sub =  object_2.getJSONArray("subLevelModelList");
                    for(int k = 0;k < jarr_sub_sub.size();k++){
                        object_3 = jarr_sub_sub.getJSONObject(k);
//                    System.out.println(object_3.getString("name"));
                        position=object_3.getString("name");
                        operationsSet.add(title+"##"+position);
                    }
                }
                if(object_2.getString("name").equals("后端开发")||object_2.getString("name").equals("移动开发")||
                        object_2.getString("name").equals("前端开发"))
                {
                    System.out.println(object_2.getString("name"));
                    title=object_2.getString("name");
                    JSONArray jarr_sub_sub =  object_2.getJSONArray("subLevelModelList");
                    for(int k = 0;k < jarr_sub_sub.size();k++){
                        object_3 = jarr_sub_sub.getJSONObject(k);
//                    System.out.println(object_3.getString("name"));
                        position=object_3.getString("name");
                        operationsSet.add("软件开发"+"##"+position);
                    }
                }
            }
        }

        JSONObject j0 = (JSONObject)jarr.get(0);
        System.out.println(j0);
        return result;
    }
    public static String getImgByUrl(){
        String baseUrl = "https://pixabay.com/zh/editors_choice/?media_type=photo&pagi=";
        BufferedReader in = null;
        String img = "";
        for(int i=1; i <= 1; i++){
            try {
                String result = "";
                String urlNameString = baseUrl+i;
                URL realUrl = new URL(urlNameString);
                URLConnection connection = realUrl.openConnection();
                connection.connect();
                Map<String,List<String>> map = connection.getHeaderFields();
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while((line = in.readLine())!=null)
                    result+=line;

                img = result.substring(result.indexOf("flex_grid credits"),result.indexOf("footer"));


            } catch (MalformedURLException e) {
                System.out.println("发送GET请求出现异常！" + e);
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("发送GET请求出现异常！" + e);
                e.printStackTrace();
            }
            finally {
                try{
                    if(in != null)
                        in.close();
                }catch(Exception e2){
                    e2.printStackTrace();
                }
            }
        }
        System.out.println(img);
        return img;
    }
    public static void loadImgURL() {
        Document doc = null;
        String res = "";
        try {
            doc = Jsoup.connect("https://pixabay.com/zh/editors_choice/?media_type=photo&pagi="+1).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements rows = doc.getElementsByClass("flex_grid credits");
        for (int i = 0; i < rows.size(); i++) {
            Element row = rows.get(i);
            //String content = row.select("#content > div:nth-child(2) > div > div").text();
            Elements items =  row.select("div > a > img");
            for (int j = 0; j < items.size(); j++) {
                String img = "";
                if(items.get(j).getElementsByAttribute("data-lazy")==null)
                    img = items.get(j).getElementsByAttribute("src").text();
                else img = items.get(j).getElementsByAttribute("data-lazy").text();
                res += img;
                System.out.println(img);
            }
        }
        System.out.println(res);
    }
}
