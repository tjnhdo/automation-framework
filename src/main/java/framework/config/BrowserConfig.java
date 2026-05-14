package framework.config;

import framework.enums.BrowserType;

public class BrowserConfig {
    private BrowserType browser = BrowserType.CHROME;
    private boolean headless = false;
    private boolean remote = false;
    private String remoteUrl;

    public BrowserType getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserType browser) {
        this.browser = browser;
    }

    public boolean isHeadless() {
        return headless;
    }

    public void setHeadless(boolean headless) {
        this.headless = headless;
    }

    public boolean isRemote() {
        return remote;
    }

    public void setRemote(boolean remote) {
        this.remote = remote;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }
}
