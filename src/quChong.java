import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class quChong {
    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();
        //读取
        try {

            FileInputStream in = new FileInputStream("E:/跑数数据/result3.txt");
            InputStreamReader inReader = new InputStreamReader(in, "UTF-8");
            BufferedReader bufReader = new BufferedReader(inReader);

            String line = null;
            int i = 1;
            while ((line = bufReader.readLine()) != null) {
                if (!line.equals("")) {
                    set.add(line);
                }
            }


            bufReader.close();
            inReader.close();
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        //写入
        try {
            FileOutputStream out = new FileOutputStream("E:/跑数数据/newresult3.txt");
            OutputStreamWriter outWriter = new OutputStreamWriter(out, "UTF-8");
            BufferedWriter bufWrite = new BufferedWriter(outWriter);
            System.out.println(set);
            for (String str : set) {
                System.out.println(str);
                bufWrite.write(str + "\r\n");
            }
            bufWrite.close();
            outWriter.close();
            out.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
