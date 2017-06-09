package com.eduarda.game.Social;

import com.badlogic.gdx.Gdx;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.scope.ScopeBuilder;

/**
 * Created by eduardacunha on 06/06/2017.
 */

public class Facebook {

    FacebookClient c = new DefaultFacebookClient(Version.VERSION_2_9);

    String idApp = "1540930469320233";
    String a = "https://www.facebook.com/connect/login_success.html";

    public void logIn() {
        ScopeBuilder sb = new ScopeBuilder();
        String link = c.getLoginDialogUrl(idApp, a, sb);

        Gdx.net.openURI(link);
    }
}
