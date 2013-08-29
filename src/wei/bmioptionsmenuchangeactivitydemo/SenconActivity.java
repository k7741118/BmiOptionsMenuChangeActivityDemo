package wei.bmioptionsmenuchangeactivitydemo;



import java.text.DecimalFormat;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SenconActivity extends MainActivity {
	private TextView view_result;
	private TextView view_suggest;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secondscreen);
		findviews();
	}
	private void findviews(){
		view_result = (TextView) findViewById(R.id.result);
        view_suggest = (TextView) findViewById(R.id.suggest);
        DecimalFormat nf = new DecimalFormat("0.00");
        Intent intent=getIntent();
	    Bundle bundle=intent.getExtras();
	    double height=bundle.getDouble("Height");
	    double  bmi=bundle.getDouble("BMI");
	    int     feet=bundle.getInt("Feet");
	    int     inch=bundle.getInt("Inch");
	    int     weight=bundle.getInt("Weight");
	    try{
	    height = (feet*12+inch)*2.54/100;
	    bmi = (weight*100*0.45359) / (height * height);
	    view_result.setText(getText(R.string.bmi_result) + nf.format(bmi));
	    if(bmi > 27) {
            view_suggest.setText(R.string.advice_fat);
        } else if(bmi > 25) {
             view_suggest.setText(R.string.advice_heavy);
        } else if(bmi < 20) {
             view_suggest.setText(R.string.advice_light);
        } else {
             view_suggest.setText(R.string.advice_average);
        }
	    }catch(Exception obj){
	    	
	    	Toast.makeText(this, getString(R.string.input_error), Toast.LENGTH_SHORT).show();
	    	
	    }
	}
	

}
