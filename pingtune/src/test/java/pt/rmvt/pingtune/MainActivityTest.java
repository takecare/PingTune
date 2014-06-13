import android.support.v4.view.ViewPager;
import android.widget.ListView;

import pt.rmvt.pingtune.R;
import pt.rmvt.pingtune.activity.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.robolectric.Robolectric.clickOn;
import static org.robolectric.Robolectric.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private MainActivity activity;
    private ViewPager viewPager;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class).create().get();
        viewPager = (ViewPager) activity.findViewById(R.id.pager);
    }

    @Test
    public void viewPager() throws Exception {
        assertThat(viewPager.getAdapter().getCount(),equalTo(2));
    }
}