package app.example.com.windowtiledemo.Adapter;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Created by mnkj on 2018/3/23.
 */

public class ViewBinder {



    public static void bind(Activity activity) {

        bind(activity, activity.getWindow().getDecorView());

    }



    public static void bind(Object target, View source) {

        Field[] fields = target.getClass().getDeclaredFields();

        if (fields != null && fields.length > 0) {

            for (Field field : fields) {

                try {

                    field.setAccessible(true);

                    if (field.get(target) != null) {

                        continue;

                    }



                    Bind bind = field.getAnnotation(Bind.class);

                    if (bind != null) {

                        int viewId = bind.value();

                        field.set(target, source.findViewById(viewId));

                    }

                } catch (Exception e) {

                    e.printStackTrace();

                }

            }

        }

    }

}
