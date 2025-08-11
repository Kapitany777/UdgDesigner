package eu.braincluster.udgdesigner;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import eu.braincluster.udgdesigner.databinding.ActivityMainBinding;
import eu.braincluster.udgdesigner.udg.UserDefinedGraphics;

public class MainActivity extends AppCompatActivity
{
    private ActivityMainBinding binding;
    private UserDefinedGraphics udg;
    private Button[][] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createPowersOfTwo();
        createButtons();

        udg = new UserDefinedGraphics();

        binding.buttonCalculate.setOnClickListener(v -> {
            binding.textViewUdgDataList.setText(udg.getUdgDataAsString());
        });

        binding.buttonReset.setOnClickListener(v -> {
            udg.reset();
            redraw();
        });

        binding.buttonInvert.setOnClickListener(v -> {
            udg.invert();
            redraw();
        });

        binding.buttonMirrorX.setOnClickListener(v -> {
            udg.mirrorX();
            redraw();
        });

        binding.buttonMirrorY.setOnClickListener(v -> {
            udg.mirrorY();
            redraw();
        });
    }

    private void createPowersOfTwo()
    {
        int pow = 128;

        var row = new TableRow(this);

        for (int i = 0; i < 8; i++)
        {
            var textView = new TextView(this);

            textView.setText(String.format("%d", pow));
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
        buttons = new Button[UserDefinedGraphics.NUM_ROWS][UserDefinedGraphics.NUM_COLS];

        for (int i = 0; i < UserDefinedGraphics.NUM_ROWS; i++)
        {
            var row = new TableRow(this);

            for (int j = 0; j < UserDefinedGraphics.NUM_COLS; j++)
            {
                var button = new Button(this);

                button.setBackgroundColor(Color.GRAY);

                var params = new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1);

                params.setMargins(5, 5, 5, 5);

                button.setLayoutParams(params);

                button.setTag(new ButtonCoordinate(i, j));

                button.setOnClickListener(v -> {
                    var coordinate = (ButtonCoordinate) v.getTag();

                    udg.toggle(coordinate.row(), coordinate.column());

                    if (udg.getStatus(coordinate.row(), coordinate.column()))
                    {
                        button.setBackgroundColor(Color.BLUE);
                    }
                    else
                    {
                        button.setBackgroundColor(Color.GRAY);
                    }
                });

                row.addView(button);

                buttons[i][j] = button;
            }

            binding.tableLayoutDesigner.addView(row);
        }
    }

    private void redraw()
    {
        binding.textViewUdgDataList.setText("");

        for (int i = 0; i < UserDefinedGraphics.NUM_ROWS; i++)
        {
            for (int j = 0; j < UserDefinedGraphics.NUM_COLS; j++)
            {
                if (udg.getStatus(i, j))
                {
                    buttons[i][j].setBackgroundColor(Color.BLUE);
                }
                else
                {
                    buttons[i][j].setBackgroundColor(Color.GRAY);
                }
            }
        }
    }
}