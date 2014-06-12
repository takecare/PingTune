/**
 * @date Jun 12, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.datamanager
 */
package pt.rmvt.pingtune.datamanager;

import android.content.Context;
import android.util.Log;

import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pt.rmvt.pingtune.bus.PingTuneBus;
import pt.rmvt.pingtune.model.Author;
import pt.rmvt.pingtune.model.Commit;
import pt.rmvt.pingtune.network.PingTuneRequestManager;
import pt.rmvt.pingtune.network.requests.CommitRequest;
import pt.rmvt.pingtune.network.requests.PingTuneRequest;

public class PingTuneDataManager {

    public static final String LOG_TAG = "PingTuneDataManager";

    private PingTuneRequestManager mRequestManager;

    private HashMap<Author, List<Commit>> mCommitsByAuthor;
    private String mLastSha = null;

    private static PingTuneDataManager sDataManager;

    private PingTuneDataManager() {}

    public static PingTuneDataManager getInstance() {
        if (sDataManager == null) {
            sDataManager = new PingTuneDataManager();
        }
        return sDataManager;
    }

    public void setup(Context context) {
        mCommitsByAuthor = new HashMap<Author, List<Commit>>();

        mRequestManager = new PingTuneRequestManager();
        mRequestManager.setup(context);
    }

    public void updateFromNetwork() {

        // TODO 1. check if network connection

        // TODO 2. launch request(s)
        fetchCommitsFromNetwork(null);

        // TODO 3. update db (through provider)

    }

    // -

    private void fetchCommitsFromNetwork(String lastSha) {
        CommitRequest request = lastSha == null ? new CommitRequest() : new CommitRequest(lastSha);
        mRequestManager.executeRequest(
                request,
                new PingTuneRequest.PingTuneResponseListener<List<Commit>>() {
                    @Override
                    public void onResponse(List<Commit> list) {

                        if (mLastSha == null) {
                            mLastSha = list.get(list.size()-1).getSha();
                            fetchCommitsFromNetwork(mLastSha);
                        }

                        HashMap<Author, List<Commit>> commitsByAuthor = groupCommitsByAuthor(list);
                        mCommitsByAuthor.putAll(commitsByAuthor);

                        //PingTuneBus.getBusInstance().post(PingTuneBus.UpdateType.NETWORK_UPDATE);
                        PingTuneBus.getBusInstance().post(commitsByAuthor);
                    }
                },
                new PingTuneRequest.PingTuneErrorListener() {
                    @Override
                    public void onError(String errorMessage, int statusCode) {
                        Log.d(LOG_TAG, statusCode + ": " + errorMessage);
                    }
                }
        );

    }

    // HELPER METHODS
    private HashMap<Author,List<Commit>> groupCommitsByAuthor(List<Commit> commits) {

        // TODO: improve this so it adds new commits (2nd request/response)

        HashMap<Author,List<Commit>> commitsByAuthor = new HashMap<Author, List<Commit>>();
        for (Commit commit : commits) {
            if (!commitsByAuthor.containsKey(commit.getAuthor())) {
                commitsByAuthor.put(commit.getAuthor(),new ArrayList<Commit>());
            } else {
                commitsByAuthor.get(commit.getAuthor()).add(commit);
            }
        }
        return commitsByAuthor;
    }
}