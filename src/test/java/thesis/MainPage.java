package thesis;

import org.openqa.selenium.*;

public class MainPage extends PageBase {
    private final By pizzaFrescaIncrease = By.xpath("/html/body/div/div/header[2]/p[3]/button[1]");
    private final By pizzaFrescaDelete = By.xpath("/html/body/div/div/header[2]/p[3]/button[2]");
    private final By pizzaPolloIncrease = By.xpath("/html/body/div/div/header[2]/p[3]/button[3]");
    private final By pizzaPolloDelete = By.xpath("/html/body/div/div/header[2]/p[3]/button[4]");
    private final By beerIncrease = By.xpath("/html/body/div/div/header[2]/p[3]/button[5]");
    private final By beerDelete = By.xpath("/html/body/div/div/header[2]/p[3]/button[6]");
    private final By cokeIncrease = By.xpath("/html/body/div/div/header[2]/p[3]/button[7]");
    private final By cokeDelete = By.xpath("/html/body/div/div/header[2]/p[3]/button[8]");
    private final By addFreeBeer = By.xpath("/html/body/div/div/header[2]/p[3]/button[9]");
    private final By addFreeCoke = By.xpath("/html/body/div/div/header[2]/p[3]/button[10]");

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://exercises.test-design.org/pizza/");
    }

    public void pizzaFrescaIncrease() {
        try {
            this.waitVisibiiltyAndFindElement(pizzaFrescaIncrease).click();
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
    }

    public void pizzaFrescaDelete() {
        try {
            this.waitVisibiiltyAndFindElement(pizzaFrescaDelete).click();
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
    }

    public void pizzaPolloIncrease() {
        try {
            this.waitVisibiiltyAndFindElement(pizzaPolloIncrease).click();
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
    }

    public void pizzaPolloDelete() {
        try {
            this.waitVisibiiltyAndFindElement(pizzaPolloDelete).click();
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
    }

    public void beerIncrease() {
        try {
            this.waitVisibiiltyAndFindElement(beerIncrease).click();
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
    }

    public void beerDelete() {
        try {
            this.waitVisibiiltyAndFindElement(beerDelete).click();
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
    }

    public void cokeIncrease() {
        try {
            this.waitVisibiiltyAndFindElement(cokeIncrease).click();
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
    }

    public void cokeDelete() {
        try {
            this.waitVisibiiltyAndFindElement(cokeDelete).click();
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
    }

    public void addFreeBeer() {
        try {
            this.waitVisibiiltyAndFindElement(addFreeBeer).click();
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
    }

    public void addFreeCoke() {
        try {
            this.waitVisibiiltyAndFindElement(addFreeCoke).click();
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
    }

}
