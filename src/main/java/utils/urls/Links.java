package utils.urls;

public enum Links {
    ANDERSEN_lAB_REGISTRATION_PAGE("https://qa-course-01.andersenlab.com/registration"),
    ANDERSEN_lAB_AUTHORIZATION_PAGE("https://qa-course-01.andersenlab.com/login"),
    AUTOMATION_PRACTICE("http://www.automationpractice.pl/index.php"),
    ZOO_WAW("https://zoo.waw.pl/"),
    W_3_SCHOOLS("https://www.w3schools.com/"),
    CLICK_SPEED_TESTER("https://www.clickspeedtester.com/click-counter/"),
    ANDERSEN_lAB("https://andersenlab.com/");

    private String link;

    Links(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
