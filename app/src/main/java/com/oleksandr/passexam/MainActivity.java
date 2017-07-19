package com.oleksandr.passexam;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return TestFragment.newInstance();
    }

}
