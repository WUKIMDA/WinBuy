package buy.win.com.winbuy;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        //assertEquals(4, 2 + 2);
        //new ShopCartPresenter().deleteSelectCartProduct("20428",6 + "");
//        HttpURLConnection connection = (HttpURLConnection) new URL("http://www.wukimda.win:8080/market/deleteCart?userId=20428&productId=5").openConnection();
//        connection.connect();

        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("http://www.wukimda.win:8080/market/selectCart?userId=20428&productId=4").openConnection();
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
            }
            reader.close();
            result = sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(result);

    }
}