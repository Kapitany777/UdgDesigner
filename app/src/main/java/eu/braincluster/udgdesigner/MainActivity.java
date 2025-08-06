package eu.braincluster.udgdesigner;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import eu.braincluster.udgdesigner.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createPowersOfTwo();
        createButtons();
    }

    private void createPowersOfTwo()
    {
        int pow = 128;

        var row = new TableRow(this);

        for (int i = 0; i < 8; i++)
        {
            var textView = new TextView(this);
            textView.setText(Integer.toString(pow));
            textView.setTextAppearance(R.style.TextViewPowerOfTwo);
            textView.setGravity(Gravity.CENTER);

            var params = new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1);

            textView.setLayoutParams(params);

            row.addView(textView);

            pow /= 2;
        }

        binding.tableLayoutDesigner.addView(row);
    }

    private void createButtons()
    {
        for (int i = 0; i < 8; i++)
        {
            var row = new TableRow(this);

            for (int j = 0; j < 8; j++)
            {
                var button = new Button(this);
//                button.setText("0");

                var params = new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1);

                button.setLayoutParams(params);

                row.addView(button);
            }

            binding.tableLayoutDesigner.addView(row);
        }
    }
}