package thesis;

import org.junit.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class MainTest {

    private WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("start-maximized");
        options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        options.addArguments("--disable-blink-features=AutomationControlled");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    // add a fresca, then three pollo, delete fresca - free beverages
    // should be offered
    @Test
    public void firstTest() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.pizzaFrescaIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaFrescaDelete();

        System.out.println(mainPage.getBodyText());

        Assert.assertTrue(mainPage.getBodyText().contains("Add free beer"));
        Assert.assertTrue(mainPage.getBodyText().contains("Add free coke"));

    }

    // add a fresca, then four pollo, delete fresca - free beverages
    // should be offered
    @Test
    public void secondTest() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.pizzaFrescaIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaFrescaDelete();

        System.out.println(mainPage.getBodyText());

        Assert.assertTrue(mainPage.getBodyText().contains("Add free beer"));
        Assert.assertTrue(mainPage.getBodyText().contains("Add free coke"));
    }

    // add three pollo, then delete - free beverages shouldn't be
    // offered
    @Test
    public void thirdTest() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloDelete();

        System.out.println(mainPage.getBodyText());

        Assert.assertFalse(mainPage.getBodyText().contains("Add free beer"));
        Assert.assertFalse(mainPage.getBodyText().contains("Add free coke"));
    }

    // add three pizza pollo - free beverages should be offered
    @Test
    public void fourthTest() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();

        System.out.println(mainPage.getBodyText());

        Assert.assertTrue(mainPage.getBodyText().contains("Add free beer"));
        Assert.assertTrue(mainPage.getBodyText().contains("Add free coke"));
    }

    // add three pizzas, select free beer, delete beer - the total price should be
    // correct
    @Test
    public void fifthTest() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaFrescaIncrease();
        mainPage.addFreeBeer();
        mainPage.beerDelete();

        System.out.println(mainPage.getBodyText());

        Assert.assertTrue(mainPage.getBodyText().contains("Total price: 48"));
    }

    // add three pizzas, select free coke, delete coke - the total
    // price should be correct
    @Test
    public void sixthTest() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.pizzaFrescaIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaFrescaIncrease();
        mainPage.addFreeCoke();
        mainPage.cokeDelete();

        System.out.println(mainPage.getBodyText());

        Assert.assertTrue(mainPage.getBodyText().contains("Total price: 51"));
    }

    // add three pizzas, select a free beer, delete beer, delete pizzas, add three pizzas, add a cola - cola is not free(because free drink cant be changed)
    @Test
    public void seventhTest() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaFrescaIncrease();

        mainPage.addFreeBeer();
        mainPage.beerDelete();

        mainPage.pizzaFrescaDelete();
        mainPage.pizzaPolloDelete();

        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaFrescaIncrease();

        mainPage.cokeIncrease();

        System.out.println(mainPage.getBodyText());

        Assert.assertFalse(mainPage.getBodyText().contains("Free coke added"));
    }

    // add three pizzas, select a free coke, delete coke, delete pizzas, add three pizzas, add a beer - beer is not free(because free drink cant be changed)
    @Test
    public void eighthTest() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaFrescaIncrease();

        mainPage.addFreeCoke();
        mainPage.cokeDelete();

        mainPage.pizzaFrescaDelete();
        mainPage.pizzaPolloDelete();

        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaFrescaIncrease();

        mainPage.beerIncrease();

        System.out.println(mainPage.getBodyText());

        Assert.assertFalse(mainPage.getBodyText().contains("Free beer added"));
    }

    // add beer, delete beer, add three pizzas, select free beer - the beer should
    // be free
    @Test
    public void ninethTest() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.beerIncrease();
        mainPage.beerDelete();
        mainPage.pizzaFrescaIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaFrescaIncrease();
        mainPage.addFreeBeer();

        System.out.println(mainPage.getBodyText());

        Assert.assertTrue(mainPage.getBodyText().contains("Free beer added"));
    }

     // add coke, delete coke, add three pizzas, select free coke - the coke should
    // be free
    @Test
    public void tenthTest() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.cokeIncrease();
        mainPage.cokeDelete();
        mainPage.pizzaFrescaIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaFrescaIncrease();
        mainPage.addFreeCoke();

        System.out.println(mainPage.getBodyText());

        Assert.assertTrue(mainPage.getBodyText().contains("Free coke added"));
    }

    // add three pizzas, select free coke, delete coke, delete pizzas, add coke, add
    // three pizzas - the only coke should be free
    @Test
    public void eleventhTest() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.pizzaFrescaIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaFrescaIncrease();
        mainPage.addFreeCoke();

        mainPage.cokeDelete();
        mainPage.pizzaPolloDelete();
        mainPage.pizzaFrescaDelete();

        mainPage.cokeIncrease();
        mainPage.pizzaFrescaIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaFrescaIncrease();

        System.out.println(mainPage.getBodyText());

        Assert.assertTrue(mainPage.getBodyText().contains("Free coke added"));
        Assert.assertTrue(mainPage.getBodyText().contains("Coke: 1"));

    }

    // add three pizzas, select free beer, delete beer, delete pizzas, add beer, add
    // three pizzas - the only beer should be free
    @Test
    public void twelvethTest() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.pizzaFrescaIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaFrescaIncrease();
        mainPage.addFreeBeer();

        mainPage.beerDelete();
        mainPage.pizzaPolloDelete();
        mainPage.pizzaFrescaDelete();

        mainPage.beerIncrease();
        mainPage.pizzaFrescaIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaFrescaIncrease();

        System.out.println(mainPage.getBodyText());

        Assert.assertTrue(mainPage.getBodyText().contains("Free beer added"));
        Assert.assertTrue(mainPage.getBodyText().contains("Beer: 1"));

    }

    // add three pizzas, select free beer, add second beer, delete pizzas - the
    // number of beers should be 1
    @Test
    public void thirteenthTest() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.pizzaFrescaIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaFrescaIncrease();
        mainPage.addFreeBeer();
        mainPage.beerIncrease();

        mainPage.pizzaPolloDelete();
        mainPage.pizzaFrescaDelete();

        System.out.println(mainPage.getBodyText());

        Assert.assertTrue(mainPage.getBodyText().contains("Beer: 1"));
    }

    // add three pizzas, select free coke, add second coke, delete pizzas - the
    // number of cokes should be 1
    @Test
    public void fourteenthTest() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.pizzaFrescaIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaFrescaIncrease();
        mainPage.addFreeCoke();
        mainPage.cokeIncrease();

        mainPage.pizzaPolloDelete();
        mainPage.pizzaFrescaDelete();

        System.out.println(mainPage.getBodyText());

        Assert.assertTrue(mainPage.getBodyText().contains("Coke: 1"));
    }

    // add three pizzas, select a free beer, delete beer, delete pizzas, add three
    // pizzas, add a beer - beer should be free
    @Test
    public void fifteenthtest() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.pizzaFrescaIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaFrescaIncrease();
        mainPage.addFreeBeer();

        mainPage.beerDelete();
        mainPage.pizzaPolloDelete();
        mainPage.pizzaFrescaDelete();

        mainPage.pizzaFrescaIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaFrescaIncrease();
        mainPage.beerIncrease();

        System.out.println(mainPage.getBodyText());

        Assert.assertTrue(mainPage.getBodyText().contains("Free beer added"));
    }

    // add three pizzas, select a free coke, delete coke, delete pizzas, add three
    // pizzas, add a coke - coke should be free
    @Test
    public void sixteenthtest() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.pizzaFrescaIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaFrescaIncrease();
        mainPage.addFreeCoke();

        mainPage.cokeDelete();
        mainPage.pizzaPolloDelete();
        mainPage.pizzaFrescaDelete();

        mainPage.pizzaFrescaIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaFrescaIncrease();
        mainPage.cokeIncrease();

        System.out.println(mainPage.getBodyText());

        Assert.assertTrue(mainPage.getBodyText().contains("Free coke added"));
    }

    // add three pizzas, select a free coke, add pizza - the offering shouldn't
    // be displayed
    @Test
    public void seventeenthtest() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.pizzaFrescaIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaFrescaIncrease();
        mainPage.addFreeCoke();
        mainPage.pizzaFrescaIncrease();

        System.out.println(mainPage.getBodyText());

        Assert.assertFalse(mainPage.getBodyText().contains("Add free beer"));
        Assert.assertFalse(mainPage.getBodyText().contains("Add free coke"));
    }

    // add three pizzas, select a free beer, add pizza - the offering shouldn't
    // be displayed
    @Test
    public void eigthteenthtest() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.pizzaFrescaIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaFrescaIncrease();
        mainPage.addFreeBeer();
        mainPage.pizzaFrescaIncrease();

        System.out.println(mainPage.getBodyText());

        Assert.assertFalse(mainPage.getBodyText().contains("Add free beer"));
        Assert.assertFalse(mainPage.getBodyText().contains("Add free coke"));
    }

    // add coke, add three pizza. the coke doesnt return to free. The price will be 48 instead of 45
    @Test
    public void firstBug() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.cokeIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();

        System.out.println(mainPage.getBodyText());

        Assert.assertTrue(mainPage.getBodyText().contains("Total price: 45"));
    }

    // add beer, add three pizza. the beer doesnt return to free. The price will be 49 instead of 45
    @Test
    public void secondBug() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.beerIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();

        System.out.println(mainPage.getBodyText());

        Assert.assertTrue(mainPage.getBodyText().contains("Total price: 45"));
    }

    // add three pizza, add free coke, delete coke  -> after this it doesn't offer free drinks. 
    @Test
    public void thirdBug() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.addFreeCoke();
        mainPage.cokeDelete();
        
        System.out.println(mainPage.getBodyText());

        Assert.assertTrue(mainPage.getBodyText().contains("Add free beer"));
        Assert.assertTrue(mainPage.getBodyText().contains("Add free coke"));
    }

    // add three pizza, add free beer, delete beer  -> after this it doesn't offer free drinks. 
    @Test
    public void fourthBug() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.addFreeBeer();
        mainPage.beerDelete();
        
        System.out.println(mainPage.getBodyText());

        Assert.assertTrue(mainPage.getBodyText().contains("Add free beer"));
        Assert.assertTrue(mainPage.getBodyText().contains("Add free coke"));
    }

    //  add three pizzas, select a free beer, add beer, add beer, delete pizzas, add three pizzas -> there will be 2 beers: 1 free and 1 paid. It was 3 at the beginning -> bug 
    @Test
    public void fifthBug() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();

        mainPage.addFreeBeer();
        mainPage.beerIncrease();
        mainPage.beerIncrease();

        mainPage.pizzaPolloDelete();

        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();

        System.out.println(mainPage.getBodyText());

        Assert.assertTrue(mainPage.getBodyText().contains("Beer: 3"));
    }

    //  add three pizzas, select a free coke, add coke, add coke, delete pizzas, add three pizzas -> there will be 2 cokes: 1 free and 1 paid -> bug 
    @Test
    public void sixthBug() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();

        mainPage.addFreeCoke();
        mainPage.cokeIncrease();
        mainPage.cokeIncrease();

        mainPage.pizzaPolloDelete();

        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaPolloIncrease();

        System.out.println(mainPage.getBodyText());

        Assert.assertTrue(mainPage.getBodyText().contains(" Coke: 3"));
    }

     // add a coke, then three pizzas - it should not offer free drinks
    @Test
    public void seventhBug() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.cokeIncrease();
        mainPage.pizzaFrescaIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaFrescaIncrease();
        //mainPage.addFreeCoke();

        System.out.println(mainPage.getBodyText());

        Assert.assertFalse(mainPage.getBodyText().contains("Add free coke"));
    }

    // add a beer, then three pizzas, it should not offer free drinks
    @Test
    public void eightBug() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.beerIncrease();
        mainPage.pizzaFrescaIncrease();
        mainPage.pizzaPolloIncrease();
        mainPage.pizzaFrescaIncrease();
        //mainPage.addFreeBeer();

        System.out.println(mainPage.getBodyText());

        Assert.assertFalse(mainPage.getBodyText().contains("Add free beer"));
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
