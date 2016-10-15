package cn.lankton.router.library;

import android.os.Bundle;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by taofangxin on 16/10/15.
 */

public class RouteResult {
    private Class<?> backFrom;
    private final Bundle bundle = new Bundle();

    private static volatile RouteResult instance;

    protected static RouteResult getInstance() {
        if (null == instance) {
            synchronized (RouteResult.class) {
                if (null == instance) {
                    instance = new RouteResult();
                }
            }
        }
        return instance;
    }

    private RouteResult() {

    }


    protected Class<?> backFrom() {
        return backFrom;
    }

    protected void setBackFrom(Class<?> clazz) {
        this.backFrom = clazz;
    }

    protected Bundle getData() {
        return bundle;
    }

    protected void setData(Bundle data) {
        this.bundle.clear();
        this.bundle.putAll(data);
    }

    protected void clear() {
        bundle.clear();
        backFrom = null;
    }

//    protected RouteResult put(String key, boolean value) {
//        bundle.putBoolean(key, value);
//        return this;
//    }
//
//    protected RouteResult put(String key, byte value) {
//        bundle.putByte(key, value);
//        return this;
//    }
//
//    protected RouteResult put(String key, char value) {
//        bundle.putChar(key, value);
//        return this;
//    }
//
//    protected RouteResult put(String key, short value) {
//        bundle.putShort(key, value);
//        return this;
//    }
//
//    protected RouteResult put(String key, int value) {
//        bundle.putInt(key, value);
//        return this;
//    }
//
//    protected RouteResult put(String key, long value) {
//        bundle.putLong(key, value);
//        return this;
//    }
//
//    protected RouteResult put(String key, float value) {
//        bundle.putFloat(key, value);
//        return this;
//    }
//
//    protected RouteResult put(String key, double value) {
//        bundle.putDouble(key, value);
//        return this;
//    }
//
//    protected RouteResult put(String key, String value) {
//        bundle.putString(key, value);
//        return this;
//    }
//
//    protected RouteResult put(String key, CharSequence value) {
//        bundle.putCharSequence(key, value);
//        return this;
//    }
//
//    protected RouteResult put(String key, Parcelable value) {
//        bundle.putParcelable(key, value);
//        return this;
//    }
//
//    protected RouteResult put(String key, Parcelable[] value) {
//        bundle.putParcelableArray(key, value);
//        return this;
//    }
//
//    protected RouteResult putParcelableArrayList(String key, ArrayList<? extends Parcelable> value) {
//        bundle.putParcelableArrayList(key, value);
//        return this;
//    }
//
//    protected RouteResult putIntegerArrayList(String key, ArrayList<Integer> value) {
//        bundle.putIntegerArrayList(key, value);
//        return this;
//    }
//
//    protected RouteResult putStringArrayList(String key, ArrayList<String> value) {
//
//    }
//
//    protected RouteResult putCharSequenceArrayList(String key, ArrayList<CharSequence> value) {
//
//    }
//
//    protected RouteResult put(String key, boolean[] value) {
//
//    }
//
//    protected RouteResult put(String key, byte[] value) {
//
//    }
//
//    protected RouteResult put(String key, short[] value) {
//
//    }
//
//
//    protected RouteResult put(String key, char[] value) {
//
//    }
//
//
//    protected RouteResult put(String key, int[] value) {
//
//    }
//
//
//    protected RouteResult put(String key, long[] value) {
//
//    }
//
//
//    protected RouteResult put(String key, float[] value) {
//
//    }
//
//    protected RouteResult put(String key, double[] value) {
//
//    }
//
//    protected RouteResult put(String key, String[] value) {
//
//    }
//
//    protected RouteResult put(String key, CharSequence[] value) {
//
//    }
//
//    protected RouteResult put(String key, Bundle value) {
//
//    }

}
