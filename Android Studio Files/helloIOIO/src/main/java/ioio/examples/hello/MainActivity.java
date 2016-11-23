package ioio.examples.hello;

import ioio.examples.hello.MorseHelpers.Alphabet;
import ioio.examples.hello.MorseHelpers.CharHelper;
import ioio.examples.hello.MorseHelpers.DictionaryHelper;
import ioio.examples.hello.MorseHelpers.MorseHelper;
import ioio.examples.hello.MorseHelpers.Letter;
import ioio.lib.api.DigitalOutput;
import ioio.lib.api.IOIO;
import ioio.lib.api.IOIO.VersionType;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.BaseIOIOLooper;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.android.IOIOActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends IOIOActivity {
    private EditText editText;
    private ImageButton button, space, back;
    private String textToConvert;
    private ListView listView;

    private LetterAdapter letterAdapter;

    private CharHelper charHelper;
    private DictionaryHelper dictionaryHelper;
    private MorseHelper morseHelper;
    private Alphabet alphabet;

	Letter[] alphabetArray;



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

        editText = (EditText) findViewById(R.id.editText);
        button = (ImageButton) findViewById(R.id.morseConverter);
        space = (ImageButton) findViewById(R.id.space_key);
        back = (ImageButton) findViewById(R.id.back_key);
        listView = (ListView) findViewById(R.id.listView);


		alphabet= new Alphabet();
		alphabetArray = alphabet.getAlphabet();
		letterAdapter = new LetterAdapter(getApplicationContext(), R.layout.row, alphabetArray);

        if(listView != null){
            listView.setAdapter(letterAdapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				editText.setText(editText.getText() + alphabetArray[position].imageName);

			}
		});

        space.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText() + " ");
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editText.getText().length()>0){
                    editText.setText(editText.getText().subSequence(0,editText.getText().length() - 1));
                }
            }
        });
	}

	class Looper extends BaseIOIOLooper {

		private DigitalOutput led_;

		@Override
		protected void setup() throws ConnectionLostException {
            toast("IOIO connected");
			led_ = ioio_.openDigitalOutput(0, true);

			charHelper = new CharHelper(led_);
			dictionaryHelper = new DictionaryHelper(charHelper, alphabet);
			morseHelper = new MorseHelper(dictionaryHelper);


		}

		@Override
		public void loop() throws ConnectionLostException, InterruptedException {

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    textToConvert = editText.getText().toString();

                    try {
                        morseHelper.convert(textToConvert);
                    } catch (ConnectionLostException e) {
                        e.printStackTrace();
                    }

                    editText.setText("");

                }
            });

			Thread.sleep(100);
		}

		@Override
		public void disconnected() {
			toast("IOIO disconnected");
		}

		@Override
		public void incompatible() {
			showVersions(ioio_, "Incompatible firmware version!");
		}
}
	@Override
	protected IOIOLooper createIOIOLooper() {
		return new Looper();
	}
	private void showVersions(IOIO ioio, String title) {
		toast(String.format("%s\n" +
						"IOIOLib: %s\n" +
						"Application firmware: %s\n" +
						"Bootloader firmware: %s\n" +
						"Hardware: %s",
				title,
				ioio.getImplVersion(VersionType.IOIOLIB_VER),
				ioio.getImplVersion(VersionType.APP_FIRMWARE_VER),
				ioio.getImplVersion(VersionType.BOOTLOADER_VER),
				ioio.getImplVersion(VersionType.HARDWARE_VER)));
	}
	private void toast(final String message) {
		final Context context = this;
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(context, message, Toast.LENGTH_LONG).show();
			}
		});
	}
}