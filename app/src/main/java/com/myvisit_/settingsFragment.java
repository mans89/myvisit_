package com.myvisit_;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class settingsFragment extends Fragment {

    //private SwitchCompat nightSwitch;

    public View onCreateView(LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        /*if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.NightTheme);
        } else
            setTheme(R.style.AppTheme);
*/
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        /*nightSwitch = (SwitchCompat) view.findViewById(R.id.nightLight_switch);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            nightSwitch.setChecked(true);
        }

        nightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    restartApp();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    restartApp();
                }
            }
        });

*/
        return view;
    }

    /*public void restartApp() {

        Intent i = new Intent(getApplicationContext(), settingsFragment.class);
        startActivity(i);
        finish();

    }*/
}