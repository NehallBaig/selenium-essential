package core;

public class TestContext {

    private PageObjectManager pageObjectManager;
    private DriverManager driverManager;

    private void contextSetup() {
        driverManager = new DriverManager();
        pageObjectManager = new PageObjectManager(driverManager.launchDriver());
    }

    public DriverManager getDriverManager() {
        return driverManager;
    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }

    public void init() {
        this.contextSetup();
    }



}
