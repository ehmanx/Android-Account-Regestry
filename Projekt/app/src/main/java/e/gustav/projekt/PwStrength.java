package e.gustav.projekt;

import android.graphics.Color;

/**
 * Created by Gustav on 2017-12-14.
 */

public interface PwStrength {
    int minLen = 8;
    int strongLen = 12;
    int acceptThresh = 4;

    int measureStrength(String password);
	String getMessage(int value);
	int getStrColor(int value);
}
