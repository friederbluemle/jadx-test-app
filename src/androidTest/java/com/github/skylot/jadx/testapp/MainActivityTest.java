package com.github.skylot.jadx.testapp;

import android.content.res.Resources;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.TextView;

import org.hamcrest.Matchers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private final static String TAG = "MainActivityTest";

    private MainActivity mMainActivity;
    private TextView mHelloText;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mMainActivity = getActivity();
        mHelloText = (TextView) mMainActivity.findViewById(R.id.hello_text);
    }

    public void testPreconditions() {
        assertThat(mMainActivity, notNullValue());
        assertThat(mHelloText, Matchers.notNullValue());
    }

    public void testHelloWorldTextView() {
        String expected = mMainActivity.getString(R.string.hello_world);
        String actual = mHelloText.getText().toString();
        assertEquals(expected, actual);
    }

    public void testStringArray() {
        Resources res = mMainActivity.getResources();
        String[] planets = res.getStringArray(R.array.planets_array);
        assertThat(planets, is(new String[]{"Mercury", "Venus", "Earth", "Mars"}));
    }

    public void testQuantityStrings() {
        Resources res = mMainActivity.getResources();
        String one = res.getQuantityString(R.plurals.numberOfSongsFound, 1, 1);
        assertThat(one, is("1 song found."));
        String other = res.getQuantityString(R.plurals.numberOfSongsFound, 100, 100);
        assertThat(other, is("100 songs found."));
    }
}
