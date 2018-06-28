package project2.ethdeliveryapp;
import org.web3j;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Web3j web3 = Web3j.build(new HttpService("https://morden.infura.io/your-token"));
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.TEXTV1);
        textView.setText("text you want to display");

    }
}
