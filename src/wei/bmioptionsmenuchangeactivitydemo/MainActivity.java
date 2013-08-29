package wei.bmioptionsmenuchangeactivitydemo;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String TAG = "aBmi";

	public static final String PREF = "BMI_PREF";
	public static final String PREF_FEET = "BMI_Feet";
	public static final String PREF_INCH = "BMI_Inch";
	public static final String PREF_WEIGHT = "BMI_Weight";

	private Spinner field_feet;
	private Spinner field_inch;
	private Spinner field_weight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findviews();
	}

	private void findviews() {
		field_feet = (Spinner) findViewById(R.id.feet);
		field_inch = (Spinner) findViewById(R.id.inch);
		field_weight = (Spinner) findViewById(R.id.weight);
		field_feet.setOnItemSelectedListener(getFeet);
		field_inch.setOnItemSelectedListener(getInch);
		field_weight.setOnItemSelectedListener(getweight);

		ArrayAdapter<CharSequence> adapter_feet = ArrayAdapter
				.createFromResource(this, R.array.feets,
						android.R.layout.simple_spinner_item);
		adapter_feet
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		field_feet.setAdapter(adapter_feet);
		ArrayAdapter<CharSequence> adapter_inch = ArrayAdapter
				.createFromResource(this, R.array.inches,
						android.R.layout.simple_spinner_item);
		adapter_inch
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		field_inch.setAdapter(adapter_inch);
		ArrayAdapter<CharSequence> adapter_weight = ArrayAdapter
				.createFromResource(this, R.array.weight,
						android.R.layout.simple_spinner_item);
		adapter_weight
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		field_weight.setAdapter(adapter_weight);

	}

	private int feet;
	private int inch;
	private int weight;

	OnItemSelectedListener getFeet = new Spinner.OnItemSelectedListener() {
		public void onItemSelected(AdapterView parent, View v, int position,
				long id) {
			feet = parent.getSelectedItemPosition() + 2;

		}

		public void onNothingSelected(AdapterView parent) {
		}
	};

	OnItemSelectedListener getInch = new Spinner.OnItemSelectedListener() {
		public void onItemSelected(AdapterView parent, View v, int position,
				long id) {
			inch = parent.getSelectedItemPosition() + 1;
		}

		public void onNothingSelected(AdapterView parent) {

		}
	};

	OnItemSelectedListener getweight = new Spinner.OnItemSelectedListener() {
		public void onItemSelected(AdapterView parent, View v, int position,
				long id) {
			weight = parent.getSelectedItemPosition() + 1;
			Toast.makeText(parent.getContext(), weight + "", Toast.LENGTH_SHORT)
					.show();
		}

		public void onNothingSelected(AdapterView parent) {

		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case (R.id.opt1):
		
			try{
				double height = (feet*12+inch)*2.54/100;
				double bmi = (weight*100*0.45359) / (height * height);
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, SenconActivity.class);
				Bundle bundle=new Bundle();
				bundle.putDouble("Height", height);
				bundle.putDouble("BMI", bmi);
				bundle.putInt("Feet", feet);
				bundle.putInt("Inch",inch);
				bundle.putInt("Weight", weight);
				
				intent.putExtras(bundle);
				startActivity(intent);
			} catch(Exception obj){
            Toast.makeText(this, getString(R.string.input_error), Toast.LENGTH_SHORT).show();
        }
			
			
			break;
		case (R.id.opt2):
			this.finish();
			break;

		}
		return super.onOptionsItemSelected(item);

	}

}
