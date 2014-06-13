import java_cup.Main;
import pt.rmvt.pingtune.activity.MainActivity;
import pt.rmvt.pingtune.adapter.AuthorAdapter;
import pt.rmvt.pingtune.model.Author;

@RunWith(RobolectricTestRunner.class)
public class AuthorAdapterTest {

    private ArrayList<Author> authors;
    private AuthorAdapter adapter;

    @Before
    public void setUp() throws Exception {
        MainActivity activity = Robolectric.buildActivity(MainActivity.class).create().visible().get();
        authors = new ArrayList<Author>();
        strings.add(new Author("name1","email1","avatarUrl1","textDate1"));
        strings.add(new Author("name2","email2","avatarUrl2","textDate2"));
        strings.add(new Author("name3","email3","avatarUrl3","textDate3"));
        adapter = new AuthorAdapter(activity,authors);
    }

    @Test
    public void getCount_shouldReturnListSize() throws Exception {
        assertThat(adapter.getCount(), equalTo(3));
        strings.add(new Author("name4","email4","avatarUrl4","textDate4"));
        assertThat(adapter.getCount(), equalTo(4));
    }
}