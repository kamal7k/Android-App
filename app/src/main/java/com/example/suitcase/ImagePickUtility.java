package com.example.suitcase;

import android.app.Activity;
import android.view.View;

import com.github.dhaval2404.imagepicker.ImagePicker;
// This class, ImagePickUtility, provides a utility method for picking an image from the device's storage.
public class ImagePickUtility {
    public static void pickImage(View view,Activity activity){
        ImagePicker.with(activity)
                .crop()
                .compress(1024)
                .maxResultSize(1080,1080)
                .start();
    }
}
