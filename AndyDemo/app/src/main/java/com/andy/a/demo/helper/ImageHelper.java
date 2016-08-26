package com.andy.a.demo.helper;


import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class ImageHelper {
    private static final int DEFAULT_IMAGE_WIDTH = ResourcesHelper.getWidthPixels();
    private static final int DEFAULT_IMAGE_HEIGHT = ResourcesHelper.getHeightPixels();


    public ImageHelper() {
    }

    public void displayImage(SimpleDraweeView draweeView, Uri uri) {
        displayImage(draweeView, uri, DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HEIGHT);
    }

    public void displayImage(SimpleDraweeView draweeView, Uri uri, int width, int height) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(width, height))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(draweeView.getController())
                .build();
        draweeView.setController(controller);
    }
}
