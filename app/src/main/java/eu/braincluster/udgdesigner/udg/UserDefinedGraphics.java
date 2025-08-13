package eu.braincluster.udgdesigner.udg;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDefinedGraphics
{
    public static final int NUM_ROWS = 8;
    public static final int NUM_COLS = 8;

    private boolean[][] characterMap;

    public UserDefinedGraphics()
    {
        initializeCharacterMap();
    }

    private void initializeCharacterMap()
    {
        characterMap = new boolean[NUM_ROWS][NUM_COLS];

        for (int i = 0; i < NUM_ROWS; i++)
        {
            for (int j = 0; j < NUM_COLS; j++)
            {
                characterMap[i][j] = false;
            }
        }
    }

    public void reset()
    {
        initializeCharacterMap();
    }

    public void toggle(int row, int column)
    {
        characterMap[row][column] = !characterMap[row][column];
    }

    public boolean getStatus(int row, int column)
    {
        return characterMap[row][column];
    }

    public List<Integer> getUdgData()
    {
        var udgData = new ArrayList<Integer>();

        for (int i = 0; i < NUM_ROWS; i++)
        {
            int data = 0;
            int pow = 128;

            for (int j = 0; j < NUM_COLS; j++)
            {
                if (characterMap[i][j])
                {
                    data += pow;
                }

                pow /= 2;
            }

            udgData.add(data);
        }

        return udgData;
    }

    public String getUdgDataAsString()
    {
        return getUdgData()
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    public void invert()
    {
        for (int i = 0; i < NUM_ROWS; i++)
        {
            for (int j = 0; j < NUM_COLS; j++)
            {
                characterMap[i][j] = !characterMap[i][j];
            }
        }
    }

    public void mirrorX()
    {
        for (int i = 0; i < NUM_ROWS / 2; i++)
        {
            for (int j = 0; j < NUM_COLS; j++)
            {
                boolean tmp = characterMap[i][j];
                characterMap[i][j] = characterMap[NUM_ROWS - i - 1][j];
                characterMap[NUM_ROWS - i - 1][j] = tmp;
            }
        }
    }

    public void mirrorY()
    {
        for (int i = 0; i < NUM_ROWS; i++)
        {
            for (int j = 0; j < NUM_COLS / 2; j++)
            {
                boolean tmp = characterMap[i][j];
                characterMap[i][j] = characterMap[i][NUM_COLS - j - 1];
                characterMap[i][NUM_COLS - j - 1] = tmp;
            }
        }
    }

    public void rotateLeft()
    {
        var newMap = new boolean[NUM_ROWS][NUM_COLS];

        for (int i = 0; i < NUM_ROWS; i++)
        {
            for (int j = 0; j < NUM_COLS; j++)
            {
                newMap[i][j] = characterMap[j][NUM_ROWS - i - 1];
            }
        }

        characterMap = newMap;
    }

    public void rotateRight()
    {
        var newMap = new boolean[NUM_ROWS][NUM_COLS];

        for (int i = 0; i < NUM_ROWS; i++)
        {
            for (int j = 0; j < NUM_COLS; j++)
            {
                newMap[i][j] = characterMap[NUM_COLS - j - 1][i];
            }
        }

        characterMap = newMap;
    }
}
